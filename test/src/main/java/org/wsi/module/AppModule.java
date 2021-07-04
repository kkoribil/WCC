/**
 * 
 */
package org.wsi.module;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.configuration2.BaseHierarchicalConfiguration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsi.module.objects.Brand;
/*import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;*/
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppModule {
	public static final String API_SUCCESS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ApiSuccess/>";
	private static String URL;
	private static Logger logger;
	private static String DB_HOST;
	private static String DB_SID;
	private static String DB_USER;
	private static String DB_PASS;
	private static String TRAINING_DB_HOST;
	private static String TRAINING_DB_SID;
	private static String TRAINING_DB_USER;
	private static String TRAINING_DB_PASS;
	private static String TIBCO_URL;
	private static String TIBCO_USER;
	private static String TIBCO_PASS;
	private static boolean SOURCING_CONTROL;
	private static List<Brand> brands;
	private static OkHttpClient client;
	private static String sourcing_file;
	private static String REST_SERVICE_HOST_URL;
	private static String REST_SERVICE_HOST_URL_APPOINTMENT_PORT;
	private static String REST_SERVICE_HOST_URL_RETURNS_PORT;
	private static String REST_SERVICE_HOST_URL_ORDER_PORT;
	private static String REST_SERVICE_URL_INVENTORY_PORT;
	private static String REST_SERVICE_HOST_URL_HISTORY_PORT;
	private static String REST_SERVICE_HOST_URL_OAUTH_PORT;
	private static String REST_SERVICE_HOST_URL_INVENTORY_PORT;
	private static String REST_SERVICE_HOST_URL_RECEIVE_PORT;

	private static long total_time = 0;
	private static long total_calls = 0;
	private static final String groups;
	// private static HashMap<Integer,PaymentAuth> payment_auth_map;

	private static Random rand = new Random();

	private static Object genIdLock = new Object();

	static {
		logger = LoggerFactory.getLogger("org.wsi.module.AppModule");
		Configurations configs = new Configurations();
		// Pull the -Denv="" property from the CL
		String env = System.getProperty("env");

		// Fallback to dev6 if the env property wasn't set
		if (env == null) {
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
				env = "stst2";
			else 
				env = "stst";
		}

		String tmp;
		groups = (tmp = System.getProperty("groups")) == null ? "" : tmp;
		logger.debug("Using groups: {}", groups);

		sourcing_file = System.getProperty("sr");
		if (sourcing_file == null) {
			sourcing_file = "src/test/resources/sourcing_rules.xml";
		}

		logger.debug("Using environment : {}", env);
		logger.debug("Using sourcing file : {}", sourcing_file);
		try {
			// Set up the environment based on the passed in env
			XMLConfiguration config = configs.xml("properties.xml");
			URL = config.getString("environment." + env + ".url");
			REST_SERVICE_HOST_URL = config.getString("environment." + env + ".RESTService.host");
			REST_SERVICE_HOST_URL_APPOINTMENT_PORT = config
					.getString("environment." + env + ".RESTService.port.appointment");
			REST_SERVICE_HOST_URL_RETURNS_PORT = config.getString("environment." + env + ".RESTService.port.returns");
			REST_SERVICE_HOST_URL_ORDER_PORT = config.getString("environment." + env + ".RESTService.port.order");
			REST_SERVICE_HOST_URL_HISTORY_PORT = config
					.getString("environment." + env + ".RESTService.port.inventoryCheck");
			REST_SERVICE_HOST_URL_OAUTH_PORT = config.getString("environment." + env + ".RESTService.port.oauth");
			REST_SERVICE_HOST_URL_INVENTORY_PORT = config
					.getString("environment." + env + ".RESTService.port.inventory");
			REST_SERVICE_HOST_URL_RECEIVE_PORT = config.getString("");

			logger.debug("URL: {}", URL);
			logger.debug("REST URL: {}", REST_SERVICE_HOST_URL);

			DB_HOST = config.getString("environment." + env + ".hostname");
			DB_SID = config.getString("environment." + env + ".sid");
			DB_USER = config.getString("environment." + env + ".username");
			DB_PASS = config.getString("environment." + env + ".password");

			TRAINING_DB_HOST = config.getString("environment.training.hostname");
			TRAINING_DB_SID = config.getString("environment.training.sid");
			TRAINING_DB_USER = config.getString("environment.training.username");
			TRAINING_DB_PASS = config.getString("environment.training.password");

			// Object out = config.getProperty("environment." + env + ".brands");
			List<HierarchicalConfiguration<ImmutableNode>> brand_list = config
					.configurationsAt("environment." + env + ".brands.brand");
			for (Iterator it = brand_list.iterator(); it.hasNext();) {
				BaseHierarchicalConfiguration sub = (BaseHierarchicalConfiguration) it.next();
				logger.debug("Is brand Live: " + sub.getString("live"));
				logger.debug("Brand Name " + sub.getString("name"));

				if (getBrands() == null) {
					setBrands(new ArrayList<Brand>());
				}
				getBrands().add(new Brand(sub.getString("name"), Boolean.parseBoolean(sub.getString("live")),
						Boolean.parseBoolean(sub.getString("bopis"))));

			}

			TIBCO_URL = config.getString(String.format("environment.%s.tibco.url", env));
			TIBCO_USER = config.getString(String.format("environment.%s.tibco.username", env));
			TIBCO_PASS = config.getString(String.format("environment.%s.tibco.password", env));

			SOURCING_CONTROL = Boolean
					.parseBoolean(config.getString(String.format("environment.%s.sourcingControl", env)));

			// Default to PB in live brand if nothing is set in properties
			if (getBrands() == null) {
				setBrands(new ArrayList<Brand>() {
					{
						add(new Brand("PB", false, false));
					}
				});
			}

			// payment_auth_map = buildPaymentAuthorizations();
			// DBModule.rectifyPaymentAuth(payment_auth_map);
		} catch (Exception e) {
			// TODO probably want to exit
			// instead of just testing on the wrong environment?

			// Fallback to dev6
			URL = "http://scomappgdevrk2v:28080/smcfs/interop/InteropHttpServlet";// Default to dev1
		}

		// Setting read time out to 6 minutes to limit test suite run time
		client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(360, TimeUnit.SECONDS)// Set a long read timeout for the API to return
				.build();

	}

	/*
	 * public static javax.jms.Connection getTIBCOConnection() throws JMSException{
	 * ConnectionFactory factory = new
	 * com.tibco.tibjms.TibjmsConnectionFactory(TIBCO_URL);
	 * 
	 * create the connection return factory.createConnection(TIBCO_USER,
	 * TIBCO_PASS);
	 * 
	 * }
	 */

	/**
	 * This method returns an Object Array of Arrays that contains the configured
	 * brands for this environment You pass in an Array of Arrays of the data you
	 * want in the data provider The first two method parameters need to be String
	 * brand, Boolean live
	 * 
	 * @param data_provider
	 * @return
	 */
	public static Object[][] generateDataProvider(Object[][] data_provider) {
		List<Object[]> out = new ArrayList<Object[]>();

		int i = 0;

		for (; i < data_provider.length; i++) {
			for (Brand brand : getBrands()) {
				Object[] tmp = new Object[1];
				tmp[0] = brand;

				tmp = ArrayUtils.addAll(tmp, data_provider[i]);
				out.add(tmp);
			}

			// If this is BVT, run the first test for each brand
			if (groups != null && groups.toLowerCase().contains("bvt")) {
				return out.toArray(new Object[out.size()][]);
			}
		}
		return out.toArray(new Object[out.size()][]);
	}

	/**
	 * Filters a data provider set, removing invalid BOPIS rows.
	 * 
	 * @param data_provider The data provider to filter
	 * @param objIndex      The index of the text to confirm contains "BOP" for a
	 *                      BOPIS test, 0 if not necessary (is a BOPIS test)
	 * @return The filtered data provider
	 */
	public static Object[][] genBopisFilteredDataProvider(Object[][] data_provider, int objIndex) {
		Object[][] genDp = generateDataProvider(data_provider);
		List<Object[]> filteredObjs = new ArrayList<Object[]>();
		for (Object[] currObj : genDp) {
			Brand brand = (Brand) currObj[0];
			Boolean isBopisTest = false;
			if (objIndex > 0) {
				String typeText = currObj[objIndex].toString();
				if (typeText.contains("BOP")) {
					isBopisTest = true;
				}
			} else {
				isBopisTest = true;
			}

			// Filter FN orders if brand is WS
			Boolean isWSFN = false;
			if (brand.getName().equals("WS") && currObj[2].toString().contains("FN")) {
				isWSFN = true;
			}

			if (!isBopisTest || brand.isBopisEnabled() && !isWSFN) {
				filteredObjs.add(currObj);
			}
		}
		return filteredObjs.toArray(new Object[filteredObjs.size()][]);
	}

	public static Object[][] genProvideValidOrganizationCodes(Object[][] data_provider, int objIndex,
			String... orgCodes) {
		Object[][] filterdp = genBopisFilteredDataProvider(data_provider, objIndex);
		List<Object[]> orgdp = new ArrayList<Object[]>();
		for (Object[] filterdpItem : filterdp) {
			for (String orgCode : orgCodes) {
				if (((Brand) filterdpItem[0]).getName().equals(orgCode)) {
					orgdp.add(filterdpItem);
				}
			}
		}
		return orgdp.toArray(new Object[orgdp.size()][]);
	}

	/**
	 * Filters a data provider set, removing invalid BOPIS rows.
	 * 
	 * @param data_provider The data provider to filter
	 * @return The filtered data provider
	 */
	/*
	 * public static Object[][] genBopisFilteredDataProvider(Object[][]
	 * data_provider) { return genBopisFilteredDataProvider(data_provider, 0); }
	 * 
	 * public static Object[][] genLiveFilteredDataProvider(Object[][]
	 * data_provider) { Object[][] genDp = generateDataProvider(data_provider);
	 * 
	 * Stream str = Arrays.stream(genDp).filter(row -> ((Brand) row[0]).isLive());
	 * return (Object[][]) str.toArray(Object[][]::new); }
	 * 
	 * public static Object[][] genNonLiveFilteredDataProvider(Object[][]
	 * data_provider) { Object[][] genDp = generateDataProvider(data_provider);
	 * 
	 * Stream str = Arrays.stream(genDp).filter(row -> !((Brand) row[0]).isLive());
	 * return (Object[][]) str.toArray(Object[][]::new); }
	 */

	/**
	 * Helper function, internally calls callApi with isFlow set to false
	 * 
	 * @param apiName The api or service name that will be called
	 * @param input   The XML document that will be sent over the wire
	 * @return The output xml from the api call
	 * @throws Exception
	 */
	public static String callApi(String apiName, String input) throws Exception {
		return callApi(apiName, input, false, null);
	}

	/**
	 * Helper function, internally calls callApi with isFlow set to false
	 * 
	 * @param apiName The api or service name that will be called
	 * @param input   The XML document that will be sent over the wire
	 * @param isFlow  Flag to determine if this is a flow or not
	 * @return The output xml from the api call
	 * @throws Exception
	 */
	public static String callApi(String apiName, String input, Boolean isFlow) throws Exception {

		System.out.println(input.toString());

		return callApi(apiName, input, isFlow, null);
	}

	/**
	 * Sets up the call to the Sterling API or Service
	 * 
	 * @param apiName The api or service name that will be called
	 * @param input   The XML document that will be sent over the wire
	 * @param isFlow  Flag to determine if this is a flow or not
	 * @return The output xml from the api call
	 * @throws Exception
	 */
	public static String callApi(String apiName, String input, Boolean isFlow, String templateXML) throws Exception {
		Instant start = Instant.now();

		// Set up the sterling headers
		FormBody.Builder formBuilder = new FormBody.Builder()

				.add("YFSEnvironment.progId", "QEHttpTester").add("YFSEnvironment.userId", "admin")
				.add("InteropApiData", input).add("InteropApiName", apiName).add("IsFlow", isFlow ? "Y" : "N")
				.add("Content-Type", "application/x-www-form-urlencoded").add("Content-Length", input.length() + "");

		if (templateXML != null) {
			formBuilder = formBuilder.add("TemplateData", templateXML);
		}

		// Set up the Flow/Service or api specific headers
		if (isFlow) {
			formBuilder = formBuilder.add("InvokeFlow", "InvokeFlow").add("ServiceName", apiName);
		} else {
			formBuilder = formBuilder.add("ApiName", apiName);
		}
		String Env = System.getProperty("Environment");
		String URL = ""; 
		if(Env.contains("STST2"))
			URL = "http://qa2.wsgc.com:18080/smcfs/interop/InteropHttpServlet";
        else if (Env.contains("STST"))
        	URL = "http://qa1.wsgc.com:18080/smcfs/interop/InteropHttpServlet";
        else if(Env.contains("EQA3"))
        	URL = "http://qa3.wsgc.com:18080/smcfs/interop/InteropHttpServlet";

			
		logger.debug("Calling {}", apiName);
		Request request = new Request.Builder().url(URL).post(formBuilder.build()).build();

		Response response = client.newCall(request).execute();

		Instant end = Instant.now();
		Duration te = Duration.between(start, end);
		long millis = te.toMillis();
		total_time += millis;

		// In practice failing sterling calls are returned with a 200 code
		// This should only get hit if the server is up but not serving sterling?
		String response_body = response.body().string();
		int respCode = response.code();

		if (respCode != 200) {
			logger.error(String.format("Invalid Response Code from Server: %s, %s", respCode, response_body));
			throw new Exception(String.format("Invalid Response Code from Server: %s, %s", respCode, response_body));
		}

		if (response_body.contains("<Errors>")) {
			logger.error("Error recevied from sterling Api Name: {} Api Input: {} IsFlow: {} Error: {}", apiName, input,
					isFlow, response_body);
			throw new SterlingXMLException(
					String.format("Error received from sterling Api Name: %s Api Input: %s IsFlow: %s Error: %s",
							apiName, input, isFlow, response_body));
		}

		// TODO
		logger.debug(
				"Api Name: {} Avg Duration: {}, This Duration:  {} Api Input: {} Template Input: {} IsFlow: {} Api Out: {}",
				apiName, total_time / ++total_calls, millis, input, templateXML, isFlow, response_body);
		return response_body;
	}

	public static String getRESTServiceHostHistoryPort() {
		return REST_SERVICE_HOST_URL_HISTORY_PORT;
	}

	public static String getRESTServiceHostAppointmentPort() {
		return REST_SERVICE_HOST_URL_APPOINTMENT_PORT;
	}

	public static String getRESTServiceHostReturnsPort() {
		return REST_SERVICE_HOST_URL_RETURNS_PORT;
	}

	public static String getRESTServiceHostReceivePort() {
		return REST_SERVICE_HOST_URL_RECEIVE_PORT;
	}

	public static String getRESTServiceHostOrderPort() {
		return REST_SERVICE_HOST_URL_ORDER_PORT;
	}

	public static String getRESTServiceHostInventoryPort() {
		return REST_SERVICE_HOST_URL_INVENTORY_PORT;
	}

	public static String getRESTServiceHostOauthPort() {
		return REST_SERVICE_HOST_URL_OAUTH_PORT;
	}

	public static String getRESTServiceHost() {
		return REST_SERVICE_HOST_URL;
	}
	/*
	 * public static RESTResponse callRestSvcPost(String serviceEndPointFormat,
	 * String port, String version, String param, String body) { String svcEndPoint
	 * = String.format(serviceEndPointFormat, port, version, param); String
	 * serviceURL = String.format(AppModule.getRESTServiceHost() + ":%s",
	 * svcEndPoint); ; Instant start = Instant.now(); RESTResponse restResp = null;
	 * try { io.restassured.response.Response resp =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON)
	 * .body(body).when().post(serviceURL);
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST POST Svc URL: {}; API Input: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, body, total_time / ++total_calls, millis, restResp == null ? ""
	 * : restResp.getMessage()); }
	 * 
	 * return restResp; }
	 */

	/*
	 * public static RESTResponse callRestSvcPost(String serviceEndPointFormat,
	 * String port, String version, String param, String body, Map<String, String>
	 * headers) { String svcEndPoint = String.format(serviceEndPointFormat, port,
	 * version, param); String serviceURL =
	 * String.format(AppModule.getRESTServiceHost() + ":%s", svcEndPoint); ; Instant
	 * start = Instant.now(); RESTResponse restResp = null; RESTServiceTestHelper
	 * rsHelper = new RESTServiceTestHelper(); try { Map<String, String> header =
	 * new HashMap<String, String>(); header.put("WSI-Client-ID", "OPTORO");
	 * System.out.println(header);
	 * 
	 * logger.debug("ServiceURL: " + serviceURL);
	 * 
	 * RequestSpecification rs =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON).headers(header)
	 * ;
	 * 
	 * io.restassured.response.Response resp =
	 * rs.body(body).when().post(serviceURL); System.out.println(serviceURL);
	 * logger.debug(resp.prettyPrint());
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST POST Svc URL: {}; API Input: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, body, total_time / ++total_calls, millis, restResp == null ? ""
	 * : restResp.getMessage()); }
	 * 
	 * return restResp; }
	 */
	/*
	 * public static RESTResponse callRestSvcPost(String serviceEndPointFormat,
	 * String port, String version, String param, String body, Map<String, String>
	 * headers, Boolean isReceipt) { String svcEndPoint =
	 * String.format(serviceEndPointFormat, port, version, param); String serviceURL
	 * = String.format(AppModule.getRESTServiceHost() + ":%s", svcEndPoint); ;
	 * Instant start = Instant.now(); RESTResponse restResp = null; try {
	 * //RequestSpecification rs =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON);
	 * 
	 * for (Map.Entry<String, String> header : headers.entrySet()) { rs =
	 * rs.header(header.getKey(), header.getValue()); }
	 * 
	 * io.restassured.response.Response resp =
	 * rs.body(body).when().post(serviceURL);
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST POST Svc URL: {}; API Input: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, body, total_time / ++total_calls, millis, restResp == null ? ""
	 * : restResp.getMessage()); }
	 * 
	 * return restResp; }
	 */
	/*
	 * public static RESTResponse callRestSvcPut(String serviceEndPointFormat,
	 * String port, String version, String param, String body) { String svcEndPoint
	 * = String.format(serviceEndPointFormat, port, version, param); String
	 * serviceURL = String.format(AppModule.getRESTServiceHost() + ":%s",
	 * svcEndPoint); ; Instant start = Instant.now(); RESTResponse restResp = null;
	 * try { io.restassured.response.Response resp =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON)
	 * .body(body).when().put(serviceURL);
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST PUT Svc URL: {}; API Input: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, body, total_time / ++total_calls, millis, restResp == null ? ""
	 * : restResp.getMessage()); }
	 * 
	 * return restResp; }
	 * 
	 * public static RESTResponse callRestSvcPut(String serviceEndPointFormat,
	 * String port, String version, String param, String body, Map<String, String>
	 * headers) { String svcEndPoint = String.format(serviceEndPointFormat, port,
	 * version, param); String serviceURL =
	 * String.format(AppModule.getRESTServiceHost() + ":%s", svcEndPoint); ; Instant
	 * start = Instant.now(); RESTResponse restResp = null; try {
	 * RequestSpecification rs =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON);
	 * 
	 * for (Map.Entry<String, String> header : headers.entrySet()) { rs =
	 * rs.header(header.getKey(), header.getValue()); }
	 * 
	 * io.restassured.response.Response resp = rs.body(body).when().put(serviceURL);
	 * logger.debug(resp.prettyPrint()); restResp = new
	 * RESTResponse(resp.asString(), resp.getStatusCode()); } finally { Instant end
	 * = Instant.now(); Duration te = Duration.between(start, end); long millis =
	 * te.toMillis(); total_time += millis; logger.
	 * debug("REST PUT Svc URL: {}; API Input: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, body, total_time / ++total_calls, millis, restResp == null ? ""
	 * : restResp.getMessage()); }
	 * 
	 * return restResp; }
	 * 
	 * public static RESTResponse callRestSvcGet(String serviceEndPointFormat,
	 * String port, String version, String param) { String svcEndPoint =
	 * String.format(serviceEndPointFormat, port, version, param); String serviceURL
	 * = String.format(AppModule.getRESTServiceHost() + ":%s", svcEndPoint); ;
	 * Instant start = Instant.now(); RESTResponse restResp = null; try {
	 * io.restassured.response.Response resp =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON).when()
	 * .get(serviceURL);
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST GET Svc URL: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, total_time / ++total_calls, millis, restResp == null ? "" :
	 * restResp.getMessage()); }
	 * 
	 * return restResp; }
	 * 
	 * public static RESTResponse callRestSvcGet(String serviceEndPointFormat,
	 * String port, String version, String param, Map<String, String> headers) {
	 * String svcEndPoint = String.format(serviceEndPointFormat, port, version,
	 * param); String serviceURL = String.format(AppModule.getRESTServiceHost() +
	 * ":%s", svcEndPoint); ; Instant start = Instant.now(); RESTResponse restResp =
	 * null; try { RequestSpecification rs =
	 * RestAssured.given().contentType(ApiConstants.CONTENTTYPEJSON);
	 * 
	 * for (Map.Entry<String, String> header : headers.entrySet()) { rs =
	 * rs.header(header.getKey(), header.getValue()); }
	 * 
	 * io.restassured.response.Response resp = rs.when().get(serviceURL);
	 * 
	 * restResp = new RESTResponse(resp.asString(), resp.getStatusCode()); } finally
	 * { Instant end = Instant.now(); Duration te = Duration.between(start, end);
	 * long millis = te.toMillis(); total_time += millis; logger.
	 * debug("REST GET Svc URL: {}; Avg Duration: {}; This Duration: {}; Api Out: {}"
	 * , serviceURL, total_time / ++total_calls, millis, restResp == null ? "" :
	 * restResp.getMessage()); }
	 * 
	 * return restResp; }
	 */
	public static Connection getConnection() throws SQLException {
		String dbURL = String.format("jdbc:oracle:thin:@%s:1521:%s", DB_HOST, DB_SID);
		return DriverManager.getConnection(dbURL, DB_USER, DB_PASS);
	}

	public static Connection getTRAININGConnection() throws SQLException {
		String dbURL = String.format("jdbc:oracle:thin:@%s:1521:%s", TRAINING_DB_HOST, TRAINING_DB_SID);
		return DriverManager.getConnection(dbURL, TRAINING_DB_USER, TRAINING_DB_PASS);
	}

	@Deprecated
	public static ResultSet runSQL(Connection dbConnection, String stmt) throws SQLException {
		ResultSet resultSet = null;
		try {
			Statement sqlStatement = dbConnection.createStatement();

			resultSet = sqlStatement.executeQuery(stmt);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	/**
	 * Verify that the generated ID doesn't exist in the system for the specified
	 * object type
	 * 
	 * @param len        character length of the desired id
	 * @param objectType ObjectType enum for the object id desired
	 * @return the generated id that is guaranteed unique (at least at the time we
	 *         queried the db)
	 * @throws Exception
	 */

	/**
	 * "Random" id generator Internally uses the MessageDigest library seeded with a
	 * UUID and truncates the result
	 * 
	 * @param len Number of characters in the id to return
	 * @return The generated id
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String generateId(int len) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		synchronized (genIdLock) {
			String id = "";

			String characters = "0123456789";
			for (int i = 0; i < len; i++) {
				id += characters.charAt(rand.nextInt(10));
			}
			return id;
		}
	}

	public static String sourcingFile() {
		return sourcing_file;
	}

	public static boolean getSourcingControl() {
		return SOURCING_CONTROL;
	}

	/**
	 * Partition a list to lists of length partition TODO: Move to a more generic
	 * location TODO: Turn back in to a generic...
	 * 
	 * @param items
	 * @param partition
	 * @return
	 */

	public static <T> List<List<T>> partitionItemsT(List<T> items, int partition) {
		List<List<T>> item_list = new ArrayList<List<T>>();
		for (int i = 0; i < (items.size() + partition - 1) / partition; i++) {
			int end = partition + (i * partition);
			item_list.add(items.subList(i * partition, end >= items.size() ? items.size() : end));
		}

		return item_list;
	}

	public static String manageItem(String input) throws Exception {
		return manageItem(input, null);
	}

	public static String manageItem(String input, String xmlTemplate) throws Exception {
		return callApi("manageItem", input, false, xmlTemplate);
	}

	public static String adjustInventory(String input) throws Exception {
		return adjustInventory(input, null);
	}

	public static String adjustInventory(String input, String xmlTemplate) throws Exception {
		return callApi("adjustInventory", input, false, xmlTemplate);
	}

	public static String OrderCreate(String input) throws Exception {
		return OrderCreate(input, null);
	}

	public static String OrderCreate(String input, String xmlTemplate) throws Exception {
		return callApi("OrderCreate", input, true, xmlTemplate);
	}

	public static String scheduleOrder(String input) throws Exception {
		return scheduleOrder(input, null);
	}

	public static String scheduleOrder(String input, String xmlTemplate) throws Exception {
		return callApi("scheduleOrder", input, false, xmlTemplate);
	}

	public static String releaseOrder(String input) throws Exception {
		return releaseOrder(input, null);
	}

	public static String releaseOrder(String input, String xmlTemplate) throws Exception {
		return callApi("releaseOrder", input, false, xmlTemplate);
	}

	public static String getOrderReleaseList(String input) throws Exception {
		return getOrderReleaseList(input, null);
	}

	public static String getOrderReleaseList(String input, String xmlTemplate) throws Exception {
		return callApi("getOrderReleaseList", input, false, xmlTemplate);
	}

	public static String ProcessOrderDropAck(String input) throws Exception {
		return ProcessOrderDropAck(input, null);
	}

	public static String ProcessOrderDropAck(String input, String xmlTemplate) throws Exception {
		return callApi("ProcessOrderDropAck", input, true, xmlTemplate);
	}

	public static String ConfirmShipment(String input) throws Exception {
		return ConfirmShipment(input, null);
	}

	public static String ConfirmShipment(String input, String xmlTemplate) throws Exception {
		return callApi("ConfirmShipment", input, true, xmlTemplate);
	}

	public static String GetOrderListService(String input) throws Exception {
		return GetOrderListService(input, null);
	}

	public static String GetOrderListService(String input, String xmlTemplate) throws Exception {
		return callApi("GetOrderListService", input, true, xmlTemplate);
	}

	public static String WISMOProcessContainerActivity(String input) throws Exception {
		return WISMOProcessContainerActivity(input, null);
	}

	public static String WISMOProcessContainerActivity(String input, String xmlTemplate) throws Exception {
		return callApi("WISMOProcessContainerActivity", input, true, xmlTemplate);
	}

	public static String getWorkOrderList(String input) throws Exception {
		return getWorkOrderList(input, null);
	}

	public static String getWorkOrderList(String input, String xmlTemplate) throws Exception {
		return callApi("getWorkOrderList", input, false, xmlTemplate);
	}

	public static String changeWorkOrderStatus(String input) throws Exception {
		return changeWorkOrderStatus(input, null);
	}

	public static String changeWorkOrderStatus(String input, String xmlTemplate) throws Exception {
		return callApi("changeWorkOrderStatus", input, false, xmlTemplate);
	}

	public static String ModifyWorkOrder(String input) throws Exception {
		return ModifyWorkOrder(input, null);
	}

	public static String ModifyWorkOrder(String input, String xmlTemplate) throws Exception {
		return callApi("ModifyWorkOrder", input, true, xmlTemplate);
	}

	public static String getShipmentListForOrder(String input) throws Exception {
		return getShipmentListForOrder(input, null);
	}

	public static String getShipmentListForOrder(String input, String xmlTemplate) throws Exception {
		return callApi("getShipmentListForOrder", input, false, xmlTemplate);
	}

	public static String createShipmentInvoice(String input) throws Exception {
		return createShipmentInvoice(input, null);
	}

	public static String createShipmentInvoice(String input, String xmlTemplate) throws Exception {
		return callApi("createShipmentInvoice", input, false, xmlTemplate);
	}

	public static String GetSOForMedic(String input) throws Exception {
		return GetSOForMedic(input, null);
	}

	public static String GetSOForMedic(String input, String xmlTemplate) throws Exception {
		return callApi("GetSOForMedic", input, true, xmlTemplate);
	}

	public static String CreateMedicWorkOrder(String input) throws Exception {
		return CreateMedicWorkOrder(input, null);
	}

	public static String CreateMedicWorkOrder(String input, String xmlTemplate) throws Exception {
		return callApi("CreateMedicWorkOrder", input, true, xmlTemplate);
	}

	public static String ProcessReservedAppointment(String input) throws Exception {
		return ProcessReservedAppointment(input, null);
	}

	public static String ProcessReservedAppointment(String input, String xmlTemplate) throws Exception {
		return callApi("ProcessReservedAppointment", input, true, xmlTemplate);
	}

	public static String ProcessFinalMileDeliveryUpdate(String input) throws Exception {
		return ProcessFinalMileDeliveryUpdate(input, null);
	}

	public static String ProcessFinalMileDeliveryUpdate(String input, String xmlTemplate) throws Exception {
		return callApi("ProcessFinalMileDeliveryUpdate", input, true, xmlTemplate);
	}

	public static String createChainedOrder(String input) throws Exception {
		return createChainedOrder(input, null);
	}

	public static String createChainedOrder(String input, String xmlTemplate) throws Exception {
		return callApi("createChainedOrder", input, false, xmlTemplate);
	}

	public static String DSPOCreateShipmentsService(String input) throws Exception {
		return DSPOCreateShipmentsService(input, null);
	}

	public static String DSPOCreateShipmentsService(String input, String xmlTemplate) throws Exception {
		return callApi("DSPOCreateShipmentsService", input, true, xmlTemplate);
	}

	public static String MonitorItemAvailability(String input, String xmlTemplate) throws Exception {
		return callApi("monitorItemAvailability", input, false, xmlTemplate);
	}

	public static String createOrderInvoice(String input) throws Exception {
		return callApi("createOrderInvoice", input, false, null);
	}

	public static String createOrderInvoice(Document doc) throws Exception {
		return callApi("createOrderInvoice", Modules.buildStringFromDocument(doc), false, null);
	}

	public static String getOrderList(String input, String template) throws Exception {
		return callApi("getOrderList", input, false, template);
	}

	public static Iterable<Node> NodeListIterable(final NodeList n) {
		return new Iterable<Node>() {

			@Override
			public Iterator<Node> iterator() {

				return new Iterator<Node>() {

					int index = 0;

					@Override
					public boolean hasNext() {
						return index < n.getLength();
					}

					@Override
					public Node next() {
						if (hasNext()) {
							return n.item(index++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	public static Iterable<Node> NamedNodeMapIterable(final NamedNodeMap n) {
		return new Iterable<Node>() {

			@Override
			public Iterator<Node> iterator() {

				return new Iterator<Node>() {

					int index = 0;

					@Override
					public boolean hasNext() {
						return index < n.getLength();
					}

					@Override
					public Node next() {
						if (hasNext()) {
							return n.item(index++);
						} else {
							throw new NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	/**
	 * Parses a double or returns the default if the double cannot be parsed
	 * 
	 * @param dbl
	 * @param def
	 * @return
	 */
	public static Double parseDouble(String dbl, Double def) {
		try {
			return Double.parseDouble(dbl);
		} catch (NumberFormatException nfe) {
			return def;
		}
	}

	/**
	 * Parses an integer or returns the default if the integer cannot be parsed
	 * 
	 * @param dbl
	 * @param def
	 * @return
	 */
	public static Integer parseInt(String integer, Integer def) {
		try {
			return Integer.parseInt(integer);
		} catch (NumberFormatException nfe) {
			return def;
		}
	}

	/**
	 * Get the list of brands for the current environment
	 * 
	 * @return
	 */
	public static List<Brand> getBrands() {
		return brands;
	}

	/**
	 * Sets the list of brands for the current environment
	 * 
	 * @param brands
	 */
	private static void setBrands(List<Brand> brands) {
		AppModule.brands = brands;
	}

	public static void overrideRestUrl(String url) {
		REST_SERVICE_HOST_URL = url;
	}
}
