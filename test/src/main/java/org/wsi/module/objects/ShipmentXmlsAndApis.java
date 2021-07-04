package org.wsi.module.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsi.module.AppModule;
import org.wsi.module.Modules;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.DataBase;
import FrameWork.Generic;




public class ShipmentXmlsAndApis {
	public static Connection stST2Con;
	public Connection ecomCon;
	static DataBase db = new DataBase();
	// protected String orderNumber;
	protected String receiptHeaderKey;
	protected String receiptNo;
	
	protected static String apiOut;
	public static Connection OraConn;
	protected String orderNumber;
	static Generic gen = new Generic();
	static DataBase database = new DataBase();
	public String getOrderNumber() {
		return orderNumber;
	}

	public static final Logger logger = LoggerFactory.getLogger(ShipmentXmlsAndApis.class.getName());

	public void debug(String message) {
		logger.debug(message);
	}

	public String readXMl(String filePath) {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		String line;
		StringBuilder sb = new StringBuilder();

		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		XmlObject xmlObject = null;
		try {
			xmlObject = XmlObject.Factory.parse(sb.toString());
		} catch (XmlException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlObject.toString();
	}

	public String readXMLValue(String xpathExpr, Document doc) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		if (xpathExpr.contains(":")) {
			xpath.setNamespaceContext(new NamespaceContext() {

				@Override
				public Iterator getPrefixes(String arg0) {
					return null;
				}

				@Override
				public String getPrefix(String arg0) {
					return null;
				}

				@Override
				public String getNamespaceURI(String arg0) {
					if ("req".equals(arg0)) {
						return "http://www.sterlingcommerce.com/documentation/YFS/CCRefund/Request";
					}
					return null;
				}
			});
		}

		String value = "";

		try {
			XPathExpression expr = xpath.compile(xpathExpr);
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			value = nodes.item(0).getNodeValue();

		} catch (XPathExpressionException e) {
			System.out.println("XML Xpath is not valid.");
			e.printStackTrace();
		}
		return value;
	}

	
	/*
	 * public void releaseOrderLineHold(ResultSet rs, String holdType,
	 * HashMap<String, String> XLTestData) throws Exception {
	 * 
	 * DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder db = df.newDocumentBuilder(); Document doc =
	 * db.newDocument();
	 * 
	 * Element root = doc.createElement("Order"); root.setAttribute("OrderNo",
	 * "992071654600"); root.setAttribute("EnterpriseCode", "WE");
	 * root.setAttribute("DocumentType", XLTestData.get("DocumentType"));
	 * root.setAttribute("Action", "Modify"); root.setAttribute("Override", "Y");
	 * 
	 * Element orderLines = doc.createElement("OrderLines");
	 * 
	 * while (rs.next()) { Element orderLine = doc.createElement("OrderLine");
	 * String PRIMELINENO = rs.getString("PRIME_LINE_NO");
	 * orderLine.setAttribute("PrimeLineNo", PRIMELINENO); String SubLineNo =
	 * rs.getString("SUB_LINE_NO"); orderLine.setAttribute("SubLineNo", SubLineNo);
	 * orderLine.setAttribute("Action", "Modify");
	 * 
	 * Element orderHoldTypes = doc.createElement("OrderHoldTypes");
	 * 
	 * Element orderHoldType = doc.createElement("OrderHoldType");
	 * orderHoldType.setAttribute("HoldType", holdType);
	 * orderHoldType.setAttribute("Status", "1100");
	 * 
	 * orderHoldTypes.appendChild(orderHoldType);
	 * orderLine.appendChild(orderHoldTypes); orderLines.appendChild(orderLine); }
	 * 
	 * root.appendChild(orderLines); doc.appendChild(root);
	 * 
	 * apiOut = AppModule.callApi("changeOrder",
	 * Modules.buildStringFromDocument(doc), false);
	 * 
	 * }
	 */

	
	public void requestCollection(String StatusValue,String OrderNo,HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {
		
		
		if(Authorizedstatus( OrderNo, oPSelFW).contains("AUTHORIZED")== true ) {
			System.out.println("Authorized");
			
		}else {
			String hostName = "", sid = "", username = "", password = "", schema = "";
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@"+hostName+":1521:"+sid, username , password);
		Statement stat = stST2Con.createStatement();
		String Query ="";
		if(gen.getPropertyValue("eComURL").contains("STST2"))
		{
			Query = "select * from yantra_stst_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
				+ OrderNo + "') and kit_code=' '";
		}
		else
		{
			Query = "select * from yantra_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where order_no='"
					+ OrderNo + "') and kit_code=' '";
		}
		ResultSet rs = stat.executeQuery(Query);

		System.out.println(rs);


		
		if (StatusValue.contains("Created")) {
			
			String Brand = XLTestData.get("Brand");
			if(Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
				
			}else if(Brand.contains("Pottery Barn")) {
				Brand = "PB";
			}
			else if(Brand.contains("West Elm")) {
				Brand = "WE";
			}else if(Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			}else if(Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				while(rs.next()) {
				Element root = doc.createElement("Order");
				root.setAttribute("DocumentType", "0001");
				root.setAttribute("EnterpriseCode", Brand);
				
				root.setAttribute("IgnoreTransactionDependencies", "Y");
				root.setAttribute("OrderHeaderKey", rs.getString("ORDER_HEADER_KEY"));
				root.setAttribute("OrderNo", OrderNo);
				doc.appendChild(root);
				}
				
				saveFile(doc);
				
			

				AppModule.callApi("requestCollection", Modules.buildStringFromDocument(doc), false);
		

		
	}
		}
	}
	
public void executeCollection(String StatusValue,String OrderNo,HashMap<String, String> XLTestData,
		ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {
		
		
		if(Authorizedstatus( OrderNo, oPSelFW).contains("AUTHORIZED")== true ) {
			//basetest.test.log(Status.INFO, "Order is in<span style='font-weight:bold;color:blue' >AUTHORIZED Status</span>");
			System.out.println("AUTHORIZED");
			
		}else {
			
			//basetest.test.log(Status.INFO, "Order is in<span style='font-weight:bold;color:blue' >Await AUTHORIZATION Status</span>");
		
			stST2Con = db.getDataBaseConnection();
			Statement stat = stST2Con.createStatement();
			String Query = "";
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2")) {
				Query = "select * from yantra_stst_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
				+ OrderNo + "') and kit_code=' '";
			}
			else
			{
				Query = "select * from yantra_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where order_no='"
					+ OrderNo + "') and kit_code=' '";
			}
			ResultSet rs = stat.executeQuery(Query);
			System.out.println(rs);		
		if (StatusValue.contains("Created")) {
			
			String Brand = XLTestData.get("Brand");
			if(Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
				
			}else if(Brand.contains("Pottery Barn")) {
				Brand = "PB";
			}
			else if(Brand.contains("West Elm")) {
				Brand = "WE";
			}else if(Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			}else if(Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				while(rs.next()) {
				Element root = doc.createElement("Order");
				root.setAttribute("DocumentType", "0001");
				root.setAttribute("EnterpriseCode", Brand);
				
				root.setAttribute("IgnoreTransactionDependencies", "Y");
				root.setAttribute("OrderHeaderKey", rs.getString("ORDER_HEADER_KEY"));
				root.setAttribute("OrderNo", OrderNo);
				doc.appendChild(root);
				}
				saveFile(doc);
				
			

				AppModule.callApi("executeCollection", Modules.buildStringFromDocument(doc), false);
		

		
	}
		}
	}

	
	public String Authorizedstatus (String OrderNumber,ProlificsSeleniumAPI oPSelFW) throws SQLException, IOException {
		
		stST2Con = db.getDataBaseConnection();
		Statement stat;
		
			stat = stST2Con.createStatement();
			String Query = "";
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
				Query = "select payment_status from  yantra_stst_owner.yfs_order_header where order_no='"+OrderNumber+"'";
			else
				Query = "select payment_status from  yantra_owner.yfs_order_header where order_no='"+OrderNumber+"'";
		String DataKey = "";
		
		ResultSet rs = stat.executeQuery(Query);

		System.out.println(rs);
			while (rs.next()) {
				DataKey = rs.getString("payment_status");
			}
			return DataKey;
		
	}
	
	public void ScheduleOrderApi(HashMap<String, String> XLTestData, String OrderNo,String StatusValue)
			throws Exception, Exception {

		//if (StatusValue.contains("Created")) {
		
		try {
			String Brand = "PB";
			if (StatusValue.contains("Created")) {
				/*
				 * String Brand = XLTestData.get("Brand"); if(Brand.contains("Williams-Sonoma"))
				 * { Brand = "WS";
				 * 
				 * }else if(Brand.contains("Pottery Barn")) { Brand = "PB"; } else
				 * if(Brand.contains("West Elm")) { Brand = "WE"; }else
				 * if(Brand.contains("Pottery Barn Kids")) { Brand = "PK"; }else
				 * if(Brand.contains("Pottery Barn Teen")) { Brand = "PT"; }
				 */
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("ScheduleOrder");
				root.setAttribute("DocumentType", "0001");
				root.setAttribute("EnterpriseCode", Brand);
				root.setAttribute("OrderNo", OrderNo);
				doc.appendChild(root);
				saveFile(doc);

				AppModule.callApi("scheduleOrder", Modules.buildStringFromDocument(doc), false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//}

	}

	public void orderStatus(String OrderNumber,HashMap<String, String> envTestData) throws SQLException {
		stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@"+envTestData.get("HostName")+":1521:"+envTestData.get("SID"), envTestData.get("DBUserName") , envTestData.get("DBPassword"));
		Statement stat = stST2Con.createStatement();
		try {
			String sqlQuery = "select Max(Status) from " + envTestData.get("Schema") + "  .yfs_order_release_Status where Order_Header_Key in "
					+ "(select Order_Header_Key from  " + envTestData.get("Schema") +". YFS_ORDER_HEADER WHERE ORDER_NO = '" + orderNumber + "')";
			ResultSet rs = stat.executeQuery(sqlQuery);
			while (rs.next()) {
				String OrderStatus = rs.getString("Max(Status)");
				System.out.println(OrderStatus);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public String getOrderStatus(String orderNumber,ProlificsSeleniumAPI oPSelFW) throws Exception {
		// Class.forName("oracle.jdbc.driver.OracleDriver");
        DataBase db = new DataBase();
        stST2Con = db.getDataBaseConnection();

		Statement stat = stST2Con.createStatement();

		String Stat = "", StatusValue = "", sqlQuery = "";
		String Env = System.getProperty("Environment");
		if(Env.contains("STST2"))
			sqlQuery = "select Max(Status) from yfs_owner_stst.yfs_order_release_Status where Order_Header_Key in "
				+ "(select Order_Header_Key from  yfs_owner_stst. YFS_ORDER_HEADER WHERE ORDER_NO = '" + orderNumber + "')";
		else
			sqlQuery = "select Max(Status) from yfs_owner.yfs_order_release_Status where Order_Header_Key in "
					+ "(select Order_Header_Key from  yfs_owner_stst. YFS_ORDER_HEADER WHERE ORDER_NO = '" + orderNumber + "')";
		
		ResultSet rs = stat.executeQuery(sqlQuery);

		

		while (rs.next()) {
			Stat = rs.getString("Max(Status)");
		}

		if (Stat.contains("1100"))
			StatusValue = "Created";
		else if (Stat.contains("1300"))
			StatusValue = "BackOrder";
		else if (Stat.contains("1000"))
			StatusValue = "Draft Created";
		else if (Stat.contains("1500.101"))
			StatusValue = "DTCSchedule";
		else if (Stat.contains("1500"))
			StatusValue = "Scheduled";
		else if (Stat.contains("1600"))
			StatusValue = "Awaiting DS PO Creation";
		else if (Stat.contains("2100"))
			StatusValue = "DS PO Created";
		else if (Stat.contains("3700.01.540"))
			StatusValue = "Reprocessed";
		else if (Stat.contains("3200.050"))
			StatusValue = "Dropped For Fulfillment";
		else if (Stat.contains("3200.200"))
			StatusValue = "Invoice Preship";
		else if (Stat.contains("3200.100"))
			StatusValue = "Acknowledge";
		else if (Stat.contains("3950.01"))
			StatusValue = "Return Invoiced";
		else if (Stat.contains("3700") && Stat.contains("00.03"))
			StatusValue = "Delivered";
		else if (Stat.contains("3700.01.545"))
			StatusValue = "DCcancel";
		else if (Stat.contains("9000"))
			StatusValue = "cancel";
		else if (Stat.contains("3700") == true && Stat.contains("7777") == false)
			StatusValue = "Shipped";
		else if (Stat.contains("3700.7777"))
			StatusValue = "Delivered";
		else if (Stat.contains("3200.520"))
			StatusValue = "PreReprocessed";
		else if (Stat.contains("3950"))
			StatusValue = "Receipt Closed";
		else if (Stat.contains("3950.01"))
			StatusValue = "Return Invoiced";
		else if (Stat.contains("3900"))
			StatusValue = "Received";
		else if (Stat.contains("3200.520"))
			StatusValue = "PreReprocessed";
		else if (Stat.contains("3200.110"))
			StatusValue = "Awaiting Store Acknowledgement";
		else if (Stat.contains("3200.120"))
			StatusValue = "In Progress with Store";
		else {
			StatusValue = "Invalid status";
		}

		System.out.println(StatusValue);
		 // oPSelFW.reportStepDtlsWithScreenshot("The Current Status of the Order is:",StatusValue, "Pass");
		//basetest.test.log(Status.PASS, "The Current Status of the Order is:<span style='font-weight:bold;color:blue' >"+StatusValue+"</span>");
		

		return (StatusValue);

	}

	public void ReleaseOrderApi(HashMap<String, String> XLTestData, String StatusValue, String OrderNo)
			throws TransformerException, Exception {
		Thread.sleep(3000);

//		if (StatusValue.contains("DTCSchedule") || StatusValue.contains("Scheduled")) {
			String Brand = XLTestData.get("Brand");
			if(Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
				
			}else if(Brand.contains("Pottery Barn")) {
				Brand = "PB";
			}
			else if(Brand.contains("West Elm")) {
				Brand = "WE";
			}else if(Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			}else if(Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}

			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("ReleaseOrder");
			root.setAttribute("DocumentType", "0001");
			root.setAttribute("EnterpriseCode", Brand);
			root.setAttribute("IgnoreReleaseDate", "Y");
			root.setAttribute("OrderNo", OrderNo);
			doc.appendChild(root);
			saveFile(doc);
			AppModule.callApi("releaseOrder", Modules.buildStringFromDocument(doc), false);
		//}
	}

	

	public static void releaseOrderLineHold(String holdType, String OrderNumber, HashMap<String, String> XLTestData)
			throws Exception {

		stST2Con = db.getDataBaseConnection();
		Statement stat = stST2Con.createStatement();
		String Query =  "";
		String Env = System.getProperty("Environment");
		if(Env.contains("STST2"))
			Query = "select * from yantra_stst_owner.yfs_order_line where order_header_key in (select order_header_key from yantra_stst_owner.yfs_order_header where order_no='" + OrderNumber + "')";
		else
			Query = "select * from yantra_owner.yfs_order_line where order_header_key in (select order_header_key from yantra_owner.yfs_order_header where order_no='"+ OrderNumber + "')";
		ResultSet rs = stat.executeQuery(Query);

		System.out.println(rs);

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		String Brand = XLTestData.get("Brand");
		if(Brand.contains("Williams-Sonoma")) {
			Brand = "WS";
			
		}else if(Brand.contains("Pottery Barn")) {
			Brand = "PB";
		}
		else if(Brand.contains("West Elm")) {
			Brand = "WE";
		}else if(Brand.contains("Pottery Barn Kids")) {
			Brand = "PK";
		}else if(Brand.contains("Pottery Barn Teen")) {
			Brand = "PT";
		}
		
		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", OrderNumber);
		root.setAttribute("EnterpriseCode", Brand);
		root.setAttribute("DocumentType", "0001");
		root.setAttribute("Action", "Modify");
		root.setAttribute("Override", "Y");

		Element orderLines = doc.createElement("OrderLines");

		while (rs.next()) {
			Element orderLine = doc.createElement("OrderLine");
			String PRIMELINENO = rs.getString("PRIME_LINE_NO");
			orderLine.setAttribute("PrimeLineNo", PRIMELINENO);
			String SubLineNo = rs.getString("SUB_LINE_NO");
			orderLine.setAttribute("SubLineNo", SubLineNo);
			orderLine.setAttribute("Action", "Modify");

			Element orderHoldTypes = doc.createElement("OrderHoldTypes");

			Element orderHoldType = doc.createElement("OrderHoldType");
			orderHoldType.setAttribute("HoldType", holdType);
			orderHoldType.setAttribute("Status", "1300");

			orderHoldTypes.appendChild(orderHoldType);
			orderLine.appendChild(orderHoldTypes);
			orderLines.appendChild(orderLine);
		}

		root.appendChild(orderLines);
		doc.appendChild(root);

		apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);

	}

	public void releaseholdsnew(String OrderNumber, HashMap<String, String> XLTestData,HashMap<String, String> envTestData) throws Exception {

		stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@"+envTestData.get("HostName")+":1521:"+envTestData.get("SID"), envTestData.get("DBUserName") , envTestData.get("DBPassword"));
		Statement stat = stST2Con.createStatement();
		// Verify if Order Status is not equal to DTCSchedule
		// if (GetOrderStatusFromDB(OrderNumber).contains("1500.101") == false) {

		String HoldExistenceQuery = "SELECT * FROM "+envTestData.get("Schema")+".YFS_ORDER_HEADER where order_no = '" + OrderNumber
				+ "'";
		ResultSet rs = stat.executeQuery(HoldExistenceQuery);
		String HOLD_FLAGExist = "";
		while (rs.next()) {
			HOLD_FLAGExist = rs.getString("HOLD_FLAG");
		}
		if (HOLD_FLAGExist.contains("Y")) {
			String HoldType = "SELECT * FROM "+envTestData.get("Schema")+".YFS_ORDER_HOLD_TYPE where ORDER_HEADER_KEY in (SELECT ORDER_HEADER_KEY FROM "+envTestData.get("Schema")+".YFS_ORDER_HEADER where order_no = '"
					+ OrderNumber + "')";
			rs = stat.executeQuery(HoldType);
			while (rs.next()) {

				String HOLD_TYPE = rs.getString("HOLD_TYPE");

				ShipmentXmlsAndApis.releaseOrderLineHold(HOLD_TYPE, OrderNumber, XLTestData);

				// }
			}

		}
	}

	public String getOrderHoldList(String OrderNumber, HashMap<String, String> XLTestData) throws Exception {

		String result = null;
		stST2Con = db.getDataBaseConnection();
		Statement stat = stST2Con.createStatement();
		String Query = "";
		if(gen.getPropertyValue("eComURL").contains("STST2"))
		{
				Query = "select * from yantra_stst_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_header where order_no='"
						+ OrderNumber + "') and kit_code=' '";
		}
		else
		{
			Query = "select * from yantra_owner.yfs_order_line where order_header_key in(select order_header_key from yantra_owner.yfs_order_header where order_no='"
					+ OrderNumber + "') and kit_code=' '"; 
		}
		ResultSet rs = stat.executeQuery(Query);
			System.out.println(rs);
			while (rs.next()) {
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("OrderHoldType");
				root.setAttribute("HoldType", "");
				root.setAttribute("LastHoldTypeDate", "");
				root.setAttribute("Modifyuserid", "");
				root.setAttribute("OrderAuditKey", "");
				root.setAttribute("OrderHeaderKey", rs.getString("ORDER_HEADER_KEY"));
				root.setAttribute("OrderLineKey", "");
				root.setAttribute("ReasonText", "");
				root.setAttribute("ResolverUserId", "");
				root.setAttribute("Status", "");
				root.setAttribute("StatusDescription", "");
				root.setAttribute("TransactionId", "");
				root.setAttribute("TransactionName", "");
				doc.appendChild(root);
	
				result = AppModule.callApi("getOrderHoldTypeList", Modules.buildStringFromDocument(doc), false);
	
				saveFile(doc);
	
			}
			return result;
	}

	private ArrayList<String> getUresolvedHolds(String OrderNumber, HashMap<String, String> XLTestData)
			throws Exception {
		Node nod;
		NodeList nodes;
		// XMLDocument xdoc = new XMLDocument();
		apiOut = getOrderHoldList(OrderNumber, XLTestData);
		if(apiOut != null)
		{
		ArrayList<String> unresolved_holds = new ArrayList<String>(0);

		if ((nod = Modules.buildDocumentFromString(apiOut).getElementsByTagName("OrderHoldTypes").item(0)) != null) {
			nodes = nod.getChildNodes();

			// Loop through OrderHoldTypes
			for (int i = 0; i < nodes.getLength(); i++) {
				String hold_type = Modules.getAttributeValue(nodes.item(i), "HoldType");
				String hold_status = Modules.getAttributeValue(nodes.item(i), "Status");
				// Only want the unresolved ones
				// Status 1300 indicates Resolved
				if (!"1300".equals(hold_status)) {
					unresolved_holds.add(hold_type);
					// unresolved_holds.add(hold_status);
				}
			}
		}
		return unresolved_holds;
		}
		return null;
	}

	public static void releaseHold(String OrderNumber, String holdType, HashMap<String, String> XLTestData)
			throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", OrderNumber);
		String Brand = XLTestData.get("Concept");
		
		if(Brand.contains("Williams-Sonoma")) {
			Brand = "WS";
			
		}else if(Brand.contains("Pottery Barn")) {
			Brand = "PB";
		}
		else if(Brand.contains("West Elm")) {
			Brand = "WE";
		}else if(Brand.contains("Pottery Barn Kids")) {
			Brand = "PK";
		}else if(Brand.contains("Pottery Barn Teen")) {
			Brand = "PT";
		}
		
		root.setAttribute("EnterpriseCode", Brand);
		root.setAttribute("DocumentType", "0001");
		root.setAttribute("SelectMethod", "WAIT");

		Element orderHoldTypes = doc.createElement("OrderHoldTypes");

		Element orderHoldType = doc.createElement("OrderHoldType");
		orderHoldType.setAttribute("Status", "1300");
		orderHoldType.setAttribute("HoldType", holdType.trim());

		orderHoldTypes.appendChild(orderHoldType);
		root.appendChild(orderHoldTypes);
		doc.appendChild(root);

		apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);
	}

	public String GetOrderStatusFromDB(String OrderNumber,HashMap<String, String> envTestData) throws SQLException {
		stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@"+envTestData.get("HostName")+":1521:"+envTestData.get("SID"), envTestData.get("DBUserName") , envTestData.get("DBPassword"));
		Statement stat = stST2Con.createStatement();

		String sqlQuery = "select Max(Status) from " + envTestData.get("Schema") + "  .yfs_order_release_Status where Order_Header_Key in "
				+ "(select Order_Header_Key from  " + envTestData.get("Schema") +". YFS_ORDER_HEADER WHERE ORDER_NO = '" + orderNumber + "')";
		ResultSet rs = stat.executeQuery(sqlQuery);

		String Status = "";
		while (rs.next()) {
			Status = rs.getString("Max(Status)");
			System.out.println(Status);
		}
		return Status;

	}

	public boolean releaseHolds( ResultSet rs, HashMap<String, String> XLTestData, String OrderNumber) throws Exception {

		Thread.sleep(10000);

		try {
			List<String> unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData);
			if(unresolved_holds != null)
			{
				while (unresolved_holds.size() > 0) {
					for (String holdType : unresolved_holds) {
					// TODO: identify what indicates order line level hold
						if ("VALIDATION_HOLD".equals(holdType)) {
							releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");

						System.out.println("Hold Resolved");

					} else if ("CONTINUITY_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");

						System.out.println("Hold Resolved");
						/*
						 * basetest.test.log(Status.PASS, "<span style='font-weight:bold;color:blue'>" +
						 * holdType + "</span>" + " Hold Resolved Succesfully ");
						 */

					} else if ("EVENT_HOLD_WINDOWED".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("IDS_VALIDATION_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("UPHOLSTERY_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("Concierge_Hold".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("EVENT_HOLD_Automatic".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("REPRO_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("DELAYED_ORDER_DROP".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("WINDOWED_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("FUTURE_REL_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					}

					else {

						releaseHold(OrderNumber, holdType, XLTestData);
						//oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					}
				}
				unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData);
				System.out.println("Hold Resolved");
				return true;
				//oPSelFW.reportStepDetails("Holds should be resolved", "Holds resolved Successfully", "Pass");
			}
		}
		else
				return false;	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}

	

	// isCreated = true;

	
	public void saveFile(Document document) {
		DOMSource source = new DOMSource(document);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StreamResult result = new StreamResult(System.getProperty("user.dir") + "\\Data\\Test.xml");
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Document docFeeder(String xmlData) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(new InputSource(new StringReader(xmlData)));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();
		Node node = root.getElementsByTagName("ReceiptLines").item(0);

		while (node.hasChildNodes()) {
			node.removeChild(node.getFirstChild());
		}

		return doc;
	}
	
	public void processOrderDropAck(HashMap<String, String> XLTestData, String actualShipmentDate, String StatusValue,
			String OrderNo) throws TransformerException, Exception {
		Thread.sleep(3000);
		try {
			if (StatusValue.contains("Dropped For Fulfillment")) {
				stST2Con = db.getDataBaseConnection();
				Statement stat = stST2Con.createStatement();
				String Query = "";
				String Env = System.getProperty("Environment");
				if(Env.contains("STST2"))
					Query = "select * from yantra_stst_owner.yfs_order_release where order_header_key in(select order_header_key from yantra_stst_owner.yfs_order_release where sales_order_no ='"
						+ OrderNo + "')";
				else
					Query = "select * from yantra_owner.yfs_order_release where order_header_key in(select order_header_key from yantra_owner.yfs_order_release where sales_order_no ='"
							+ OrderNo + "')";
				ResultSet rs = stat.executeQuery(Query);
				
				String Brand = XLTestData.get("Brand");
				if(Brand.contains("Williams-Sonoma")) {
					Brand = "WS";
					
				}else if(Brand.contains("Pottery Barn")) {
					Brand = "PB";
				}
				else if(Brand.contains("West Elm")) {
					Brand = "WE";
				}else if(Brand.contains("Pottery Barn Kids")) {
					Brand = "PK";
				}else if(Brand.contains("Pottery Barn Teen")) {
					Brand = "PT";
				}

				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();
				Element root = doc.createElement("OrderRelease");
				while (rs.next()) {

					// OrderLine firstOrderLine = lines.get(0);
					

					root.setAttribute("ConsolidatorAddressCode", "");
					root.setAttribute("DocumentType", "0001");
					root.setAttribute("EnterpriseCode", Brand.trim());
					root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate.trim());
					root.setAttribute("OrderNo", OrderNo);
					String ShipAdviceNo = rs.getString("Ship_Advice_No");
					System.out.println(ShipAdviceNo.trim());
					root.setAttribute("ShipAdviceNo", ShipAdviceNo.trim());
					String ShipNode = rs.getString("SHIPNODE_KEY");
					root.setAttribute("ShipNode", ShipNode.trim());
					
				}
				
				doc.appendChild(root);
				saveFile(doc);
				AppModule.callApi("ProcessOrderDropAck", Modules.buildStringFromDocument(doc), true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
	public void invoicePreShip(HashMap<String, String> XLTestData, String orderNumber, String StatusValue)
			throws TransformerException, Exception {
		Thread.sleep(3000);
		if (StatusValue.contains("Acknowledge")) {
			stST2Con = db.getDataBaseConnection();
			Statement stat = stST2Con.createStatement();
			String sql = "";
			if(gen.getPropertyValue("eComURL").contains("STST2")) {
				sql = String.format("select task_q_key, transaction_key, data_key from yantra_stst_owner.yfs_task_q where transaction_key in (select transaction_key from yantra_stst_owner.yfs_transaction where tranid='WSI_ORD_ACK_INVOICE.0001.ex')"
							+ "and data_key in (select order_header_key from yantra_stst_owner.yfs_order_header where order_no='%s')",orderNumber);
			}
			else
			{
				sql = String.format("select task_q_key, transaction_key, data_key from yantra_owner.yfs_task_q where transaction_key in (select transaction_key from yantra_owner.yfs_transaction where tranid='WSI_ORD_ACK_INVOICE.0001.ex')"
						+ "and data_key in (select order_header_key from yantra_owner.yfs_order_header where order_no='%s')",orderNumber);
			}
			System.out.println(sql);
			ResultSet rs = stat.executeQuery(sql);
		
			while (rs.next()) {
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("Task");
				root.setAttribute("DataKey", rs.getString("data_key"));
				System.out.println(rs.getString("data_key"));
				root.setAttribute("TaskQKey", rs.getString("task_q_key"));
				root.setAttribute("TransactionKey", rs.getString("transaction_key"));
				doc.appendChild(root);
				saveFile(doc);
				AppModule.callApi("CreateOrderInvoiceService", Modules.buildStringFromDocument(doc), true);
			}
		}

	}

	public String ConfirmShip(ResultSet rs, HashMap<String, String> XLTestData, String actualShipmentDate,
			String StatusValue, String OrderNo) throws Exception {
		Thread.sleep(3000);
		String TrackingNo = "";
		if (StatusValue.contains("Invoice Preship")) {
			
			String Brand = XLTestData.get("Brand");
			if(Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
				
			}else if(Brand.contains("Pottery Barn")) {
				Brand = "PB";
			}
			else if(Brand.contains("West Elm")) {
				Brand = "WE";
			}else if(Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			}else if(Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}

			int num = 1101;

			for (int i = 1101; i <= num; i += 1) {

				while (rs.next()) {

					num += 1;
					DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = df.newDocumentBuilder();
					Document doc = db.newDocument();
					// OrderLine firstOrderLine = lines.get(0);
					Element root = doc.createElement("Shipment");

					root.setAttribute("Action", "Create");
					root.setAttribute("DocumentType", "0001");
					// root.setAttribute("CarrierServiceCode", "FURNITURE_REGULAR");
					root.setAttribute("EnterpriseCode", Brand);
					root.setAttribute("SCAC", "WS_CARRIER");
					root.setAttribute("SellerOrganizationCode", Brand + "DTC");
					String ShipNode = rs.getString("SHIPNODE_KEY");
					root.setAttribute("ShipNode", ShipNode.trim());
					root.setAttribute("TrackingNo", OrderNo + num);
					root.setAttribute("ActualShipmentDate", actualShipmentDate);
					root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate);
					// root.setAttribute("ConsolidatorAddressCode", consolidatorAddressCode);
					doc.appendChild(root);

					Element ShipmentLines = doc.createElement("ShipmentLines");
					root.appendChild(ShipmentLines);

					Element ShipmentLine = doc.createElement("ShipmentLine");
					ShipmentLine.setAttribute("Action", "Create");
					ShipmentLine.setAttribute("DocumentType", "0001");
					String ItemId = rs.getString("ITEM_ID");
					ShipmentLine.setAttribute("ItemID", ItemId.trim());
					ShipmentLine.setAttribute("OrderNo", OrderNo);

					String PRIMELINENO = rs.getString("PRIME_LINE_NO");

					ShipmentLine.setAttribute("PrimeLineNo", PRIMELINENO);
					ShipmentLine.setAttribute("ProductClass", "");

					String OrderQuantity = rs.getString("ORDERED_QTY");
					ShipmentLine.setAttribute("Quantity", OrderQuantity);
					ShipmentLine.setAttribute("UnitOfMeasure", "EACH");
					String SubLineNo = rs.getString("SUB_LINE_NO");
					ShipmentLine.setAttribute("SubLineNo", SubLineNo);
					ShipmentLine.setAttribute("ReleaseNo", "1");
					// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
					ShipmentLines.appendChild(ShipmentLine);

					Element Containers = doc.createElement("Containers");
					root.appendChild(Containers);

					Element Container = doc.createElement("Container");
					Container.setAttribute("Action", "Create");
					Container.setAttribute("ContainerNo", OrderNo + num);
					Container.setAttribute("TrackingNo", OrderNo + num);
					// Container.setAttribute("Ucc128code", "");
					Containers.appendChild(Container);

					Element ContainerDetails = doc.createElement("ContainerDetails");
					Container.appendChild(ContainerDetails);

					Element ContainerDetail = doc.createElement("ContainerDetail");
					ContainerDetail.setAttribute("Action", "Create");
					ContainerDetail.setAttribute("Quantity", OrderQuantity);
					ContainerDetails.appendChild(ContainerDetail);

					Element ShipmentLineNode = doc.createElement("ShipmentLine");
					ShipmentLineNode.setAttribute("OrderNo", OrderNo);
					String PRIMELINENO1 = rs.getString("PRIME_LINE_NO");
					ShipmentLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
					String SubLineNo1 = rs.getString("SUB_LINE_NO");
					ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
					ShipmentLineNode.setAttribute("ReleaseNo", "1");
					// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
					ContainerDetail.appendChild(ShipmentLineNode);

					Element ShipmentTagSerials = doc.createElement("ShipmentTagSerials");
					ContainerDetail.appendChild(ShipmentTagSerials);

					
					saveFile(doc);
					AppModule.callApi("ConfirmShipment", Modules.buildStringFromDocument(doc), true);
					
				}
				

			}

		}
		return TrackingNo;
	}

	public void DeliverShipmentMultiline(HashMap<String, String> XLTestData, String actualShipmentDate,
			String StatusValue, String orderNo) throws Exception {
		Thread.sleep(3000);
		//if (StatusValue.contains("Shipped")) {

			stST2Con = db.getDataBaseConnection();
			Statement stat = stST2Con.createStatement();

			String Query = "";
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				Query = "select ysl.order_no, ysl.prime_line_no,ysl.sub_line_no, ysl.item_id, ysl.QUANTITY, ys.tracking_no from yantra_stst_owner.yfs_shipment ys join yantra_stst_owner.yfs_shipment_line ysl on  ys.shipment_key=ysl.shipment_key where ysl.order_no='"
					+ orderNo + "' and prime_line_no != '950'";
			}
			else
			{
				Query = "select ysl.order_no, ysl.prime_line_no,ysl.sub_line_no, ysl.item_id,ysl.QUANTITY, ys.tracking_no from yantra_owner.yfs_shipment ys join yantra_owner.yfs_shipment_line ysl on  ys.shipment_key=ysl.shipment_key where ysl.order_no='"
						+ orderNo + "' and prime_line_no != '950'";
			}
			ResultSet rs = stat.executeQuery(Query);
			String Brand = XLTestData.get("Brand");
			if(Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
				
			}else if(Brand.contains("Pottery Barn")) {
				Brand = "PB";
			}
			else if(Brand.contains("West Elm")) {
				Brand = "WE";
			}else if(Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			}else if(Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}

			System.out.println(rs);

			while (rs.next()) {

				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("MultiApi");
				doc.appendChild(root);

				Element APINode = doc.createElement("API");
				APINode.setAttribute("FlowName", "ExecutePOD");
				root.appendChild(APINode);

				Element Input = doc.createElement("Input");
				APINode.appendChild(Input);

				Element ShipmentNode = doc.createElement("Shipment");
				ShipmentNode.setAttribute("OrderNo", orderNo);
				ShipmentNode.setAttribute("DocumentType", "0001");
				ShipmentNode.setAttribute("EnterpriseCode", Brand);
				ShipmentNode.setAttribute("StatusDate", actualShipmentDate);

				Input.appendChild(ShipmentNode);

				Element ContainersNode = doc.createElement("Containers");
				ShipmentNode.appendChild(ContainersNode);

				Element ContainerNode;

				ContainerNode = doc.createElement("Container");
				String trackingNo = rs.getString("TRACKING_NO");
				ContainerNode.setAttribute("TrackingNo", trackingNo);
				ContainerNode.setAttribute("ContainerNo", trackingNo);
				ContainerNode.setAttribute("ISDropShipContainer", "N");
				ContainersNode.appendChild(ContainerNode);

				Element ContainerDetailsNode = doc.createElement("ContainerDetails");
				ContainerNode.appendChild(ContainerDetailsNode);

				Element ContainerDetailNode = doc.createElement("ContainerDetail");
				String ItemId = rs.getString("ITEM_ID");
				ContainerDetailNode.setAttribute("ItemID", ItemId.trim());
				String OrderQuantity = rs.getString("QUANTITY");
				ContainerDetailNode.setAttribute("Quantity", OrderQuantity);
				ContainerNode.setAttribute("ISDropShipContainer", "N");
				ContainerDetailsNode.appendChild(ContainerDetailNode);

				Element ShipmentLineNode = doc.createElement("ShipmentLine");
				ShipmentLineNode.setAttribute("OrderNo", orderNo);
				String PRIMELINENO1 = rs.getString("PRIME_LINE_NO");
				ShipmentLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
				String SubLineNo1 = rs.getString("SUB_LINE_NO");
				ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
				ContainerDetailsNode.appendChild(ShipmentLineNode);

				// API Second Node
				APINode = doc.createElement("API");
				APINode.setAttribute("FlowName", "ExecuteContainerActivity");
				root.appendChild(APINode);

				Input = doc.createElement("Input");
				APINode.appendChild(Input);

				ContainerNode = doc.createElement("Container");
				ContainerNode.setAttribute("IsDropShipContainer", "N");
				ContainerNode.setAttribute("ContainerNo", trackingNo);
				Input.appendChild(ContainerNode);

				Element ContainerActivitiesNode = doc.createElement("ContainerActivities");
				ContainerNode.appendChild(ContainerActivitiesNode);

				Element ContainerActivityNode = doc.createElement("ContainerActivity");
				ContainerActivityNode.setAttribute("ActivityCode", "DELIVERED TO CUSTOMER");
				ContainerActivityNode.setAttribute("ActivityTimeStamp", actualShipmentDate);
				ContainerActivitiesNode.appendChild(ContainerActivityNode);

				Element ExtnNode = doc.createElement("Extn");
				ExtnNode.setAttribute("ExtnActivityTime", actualShipmentDate);
				ExtnNode.setAttribute("ExtnPkmsStatusDesc", "X1");
				ContainerActivityNode.appendChild(ExtnNode);

				Element ActivityLocationNode = doc.createElement("ActivityLocation");
				ContainerActivityNode.appendChild(ActivityLocationNode);
				ContainerActivityNode.appendChild(ActivityLocationNode);

				Element OrderNode = doc.createElement("Order");
				OrderNode.setAttribute("DocumentType", "0001");
				OrderNode.setAttribute("EnterpriseCode", Brand);
				OrderNode.setAttribute("OrderNo", orderNo);
				ContainerNode.appendChild(OrderNode);

				Element OrderLinesNode = doc.createElement("OrderLines");
				OrderNode.appendChild(OrderLinesNode);

				Element OrderLineNode = doc.createElement("OrderLine");
				OrderLineNode.setAttribute("OrderedQty", OrderQuantity);
				OrderLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
				OrderLineNode.setAttribute("SubLineNo", SubLineNo1);
				OrderLinesNode.appendChild(OrderLineNode);

				Element ItemNode = doc.createElement("Item");
				ItemNode.setAttribute("PackItemID", "");
				ItemNode.setAttribute("ItemID", ItemId.trim());
				OrderLineNode.appendChild(ItemNode);
				saveFile(doc);
				apiOut = AppModule.callApi("multiApi", Modules.buildStringFromDocument(doc), false);
			//}
		}

	}
	public void releaseholdsnew(String OrderNumber, HashMap<String, String> XLTestData,
			HashMap<String, String> envTestData, ProlificsSeleniumAPI oPSelFW) throws Exception {

		stST2Con = DriverManager.getConnection(
				"jdbc:oracle:thin:@" + envTestData.get("HostName") + ":1521:" + envTestData.get("SID"),
				envTestData.get("DBUserName"), envTestData.get("DBPassword"));
		Statement stat = stST2Con.createStatement();
		// Verify if Order Status is not equal to DTCSchedule
		// if (GetOrderStatusFromDB(OrderNumber).contains("1500.101") == false) {

		String HoldExistenceQuery = "SELECT * FROM " + envTestData.get("Schema")
				+ ".YFS_ORDER_HEADER where order_no = '" + OrderNumber + "'";
		ResultSet rs = stat.executeQuery(HoldExistenceQuery);
		String HOLD_FLAGExist = "";
		while (rs.next()) {
			HOLD_FLAGExist = rs.getString("HOLD_FLAG");
		}
		if (HOLD_FLAGExist.contains("Y")) {
			String HoldType = "SELECT * FROM " + envTestData.get("Schema")
					+ ".YFS_ORDER_HOLD_TYPE where ORDER_HEADER_KEY in (SELECT ORDER_HEADER_KEY FROM "
					+ envTestData.get("Schema") + ".YFS_ORDER_HEADER where order_no = '" + OrderNumber + "')";
			rs = stat.executeQuery(HoldType);
			while (rs.next()) {

				String HOLD_TYPE = rs.getString("HOLD_TYPE");

				ShipmentXmlsAndApis.releaseOrderLineHold(HOLD_TYPE, OrderNumber, XLTestData, oPSelFW);

				// }
			}

		}
	}
	public static void releaseOrderLineHold(String holdType, String OrderNumber, HashMap<String, String> XLTestData,
			ProlificsSeleniumAPI oPSelFW) throws Exception {
		String hostName, sid, username, password, schema;
		try {
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			stST2Con = database.getDBConnection(oPSelFW);
			Statement stat = stST2Con.createStatement();
			String Query = "select * from " + schema
					+ ".yfs_order_line where order_header_key in (select order_header_key from " + schema
					+ ".yfs_order_header where order_no='" + OrderNumber + "')";
	
			ResultSet rs = stat.executeQuery(Query);
	
			System.out.println(rs);
	
			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			String Brand = XLTestData.get("Brand");
			if (Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
	
			} else if (Brand.contains("Pottery Barn")) {
				Brand = "PB";
			} else if (Brand.contains("West Elm")) {
				Brand = "WE";
			} else if (Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			} else if (Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}
	
			Element root = doc.createElement("Order");
			root.setAttribute("OrderNo", OrderNumber);
			root.setAttribute("EnterpriseCode", Brand);
			root.setAttribute("DocumentType", "0001");
			root.setAttribute("Action", "Modify");
			root.setAttribute("Override", "Y");
	
			Element orderLines = doc.createElement("OrderLines");
	
			while (rs.next()) {
				Element orderLine = doc.createElement("OrderLine");
				String PRIMELINENO = rs.getString("PRIME_LINE_NO");
				orderLine.setAttribute("PrimeLineNo", PRIMELINENO);
				String SubLineNo = rs.getString("SUB_LINE_NO");
				orderLine.setAttribute("SubLineNo", SubLineNo);
				orderLine.setAttribute("Action", "Modify");
	
				Element orderHoldTypes = doc.createElement("OrderHoldTypes");
	
				Element orderHoldType = doc.createElement("OrderHoldType");
				orderHoldType.setAttribute("HoldType", holdType);
				orderHoldType.setAttribute("Status", "1300");
	
				orderHoldTypes.appendChild(orderHoldType);
				orderLine.appendChild(orderHoldTypes);
				orderLines.appendChild(orderLine);
			}
	
			root.appendChild(orderLines);
			doc.appendChild(root);
	
			apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);
			}
			catch(Exception e)
			{
				oPSelFW.reportStepDetails("Verify if exception is displayed in releaseOrderLineHold", "Exception is displayed in releaseOrderLineHold", "Fail");
				Assert.assertEquals("Verify if exception is displayed in releaseOrderLineHold", "Exception is displayed in releaseOrderLineHold");
			}

	}
	public void releaseHoldsforReproandDcCancelOrders(ResultSet rs, HashMap<String, String> XLTestData,
			String OrderNumber, ProlificsSeleniumAPI oPSelFW) throws Exception {

		Thread.sleep(10000);

		try {
			List<String> unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData, oPSelFW);
			while (unresolved_holds.size() > 0) {
				for (String holdType : unresolved_holds) {
					// TODO: identify what indicates order line level hold
					if ("VALIDATION_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");

						System.out.println("Hold Resolved");

					} else if ("CONTINUITY_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");
					} else if ("EVENT_HOLD_WINDOWED".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("IDS_VALIDATION_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("UPHOLSTERY_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("Concierge_Hold".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("EVENT_HOLD_Automatic".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("REPRO_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("DELAYED_ORDER_DROP".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("WINDOWED_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("FUTURE_REL_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					}

					else if ("PENDING_FRAUD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
					} else if ("PENDING_FRAUD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");
					}
					else {
						releaseHold(OrderNumber, holdType, XLTestData);
						System.out.println("Hold Resolved");
					}
				}
				unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData, oPSelFW);
				System.out.println("Hold Resolved");

				// oPSelFW.reportStepDetails("Holds should be resolved", "Holds resolved
				// Successfully", "Pass");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
	private ArrayList<String> getUresolvedHolds(String OrderNumber, HashMap<String, String> XLTestData, ProlificsSeleniumAPI oPSelFW) throws Exception {
		Node nod;
		NodeList nodes;
		
		ArrayList<String> unresolved_holds = new ArrayList<String>(0);
		// XMLDocument xdoc = new XMLDocument();
		apiOut = getOrderHoldList(OrderNumber, XLTestData, oPSelFW);

		if(apiOut != null)
		{
		if ((nod = Modules.buildDocumentFromString(apiOut).getElementsByTagName("OrderHoldTypes").item(0)) != null) {
			nodes = nod.getChildNodes();

			// Loop through OrderHoldTypes
			for (int i = 0; i < nodes.getLength(); i++) {
				String hold_type = Modules.getAttributeValue(nodes.item(i), "HoldType");
				String hold_status = Modules.getAttributeValue(nodes.item(i), "Status");
				// Only want the unresolved ones
				// Status 1300 indicates Resolved
				if (!"1300".equals(hold_status)) {
					unresolved_holds.add(hold_type);
					// unresolved_holds.add(hold_status);
				}
			}
		}
		}
		return unresolved_holds;
	}
	public String getOrderHoldList(String OrderNumber, HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Exception {

		String result = null;
		String Env = System.getProperty("Environment");
		String schema  = "";
		if(Env.contains("STST2"))
		{
			schema = "yantra_stst_owner";
		}
		else
		{
			schema = "yantra_owner";
		}
		stST2Con = db.getDataBaseConnection();
		Statement stat = stST2Con.createStatement();

		String Query = "select * from " + schema
				+ ".yfs_order_line where order_header_key in(select order_header_key from " + schema
				+ ".yfs_order_header where order_no='" + OrderNumber + "') and kit_code=' '";

		ResultSet rs = stat.executeQuery(Query);

		System.out.println(rs);

		while (rs.next()) {
			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("OrderHoldType");
			root.setAttribute("HoldType", "");
			root.setAttribute("LastHoldTypeDate", "");
			root.setAttribute("Modifyuserid", "");
			root.setAttribute("OrderAuditKey", "");
			root.setAttribute("OrderHeaderKey", rs.getString("ORDER_HEADER_KEY"));
			root.setAttribute("OrderLineKey", "");
			root.setAttribute("ReasonText", "");
			root.setAttribute("ResolverUserId", "");
			root.setAttribute("Status", "");
			root.setAttribute("StatusDescription", "");
			root.setAttribute("TransactionId", "");
			root.setAttribute("TransactionName", "");
			doc.appendChild(root);

			result = AppModule.callApi("getOrderHoldTypeList", Modules.buildStringFromDocument(doc), false);

			saveFile(doc);

		}
		return result;

	}
	public void ExecuteReproDCCancelMsg(HashMap<String, String> XLTestData, String OrderNo, String StatusValue, ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {
		Thread.sleep(3000);
		if (StatusValue.contains("Reprocessed")) {
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			

			/* String orderNo = "901291194339"; */
			
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();

			// String orderNumber = XLTestData.get("orderNumber").toString();

			String sql = String.format("select * from " + schema
					+ ".yfs_task_q where data_key in (select order_header_key from " + schema
					+ ".yfs_order_header where order_no='" + OrderNo + "')");

			System.out.println(sql);
			ResultSet rs = stat.executeQuery(sql);

			/* String ProcessType=XLTestData.get("ProcessType"); */

			while (rs.next()) {
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("Task");
				root.setAttribute("DataKey", rs.getString("data_key"));
				System.out.println(rs.getString("data_key"));
				root.setAttribute("TaskQKey", rs.getString("task_q_key"));
				root.setAttribute("TransactionKey", rs.getString("transaction_key"));
				if (StatusValue.contains("Repro")) {
					root.setAttribute("TransactionId", "REPRO");
				} else {

					root.setAttribute("TransactionId", "DC_CANCEL");
				}
				doc.appendChild(root);
				saveFile(doc);
				AppModule.callApi("ExecuteReproDCCancelMsg", Modules.buildStringFromDocument(doc), true);
				String OrdStatus=getOrderStatus(OrderNo, oPSelFW);
				
				
				oPSelFW.reportStepDetails("DC Cancel or Repro has done", " OrderStatus is " +OrdStatus, "Pass");
				
			
			}
		}
	}
	public void processPartiallyDCRepro(HashMap<String, String> XLTestData, String OrderNo, String StatusValue,
			ProlificsSeleniumAPI oPSelFW)
			throws TransformerException, Exception {
		String SubProcess = XLTestData.get("SubProcess").toString();
		String reproItemId =XLTestData.get("reproItemId").toString();
		String reproItemCount=XLTestData.get("reproItemCount").toString();
		String ItemType = null;

		if (StatusValue.contains("Invoice Preship")) {
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();
			String Query = "select KIT_CODE from " + schema
					+ ".yfs_order_line where order_header_key in(select order_header_key from "
					+ schema + ".yfs_order_header where order_no='" + OrderNo + "') and item_id='"
					+ reproItemId + "'and prime_line_no != '950'";
			System.out.println(Query);
			ResultSet rs = stat.executeQuery(Query);
			while (rs.next()) {
				ItemType = rs.getString("KIT_CODE");
			}

			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("OrderRelease");
			root.setAttribute("Action", "MODIFY");
			root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
			root.setAttribute("ModificationReasonCode", "REPRO_DC");
			root.setAttribute("ModificationReasonText", "INVENTORY_DISCREPANCY");
			root.setAttribute("OrderNo", OrderNo);
			root.setAttribute("Override", "Y");
			Element orderLines = doc.createElement("OrderLines");
			if (ItemType.equalsIgnoreCase("BUNDLE")) {
				String Query_Bundle = "select * from " + schema
						+ ".yfs_order_line where order_header_key in(select order_header_key from "
						+ schema + ".yfs_order_header where order_no='" + OrderNo
						+ "') and kit_code=' ' and prime_line_no != '950'";
				System.out.println(Query_Bundle);
				ResultSet rs1 = stat.executeQuery(Query_Bundle);
				while (rs1.next()) {
					if (SubProcess.equalsIgnoreCase("NonComponent")) {
					Element orderLine = doc.createElement("OrderLine");
					orderLine.setAttribute("ChangeInQuantity", reproItemCount);
					orderLine.setAttribute("PrimeLineNo", rs1.getString("PRIME_LINE_NO"));
					orderLine.setAttribute("SubLineNo", rs1.getString("SUB_LINE_NO"));
					Element item = doc.createElement("Item");
					orderLine.appendChild(item);
					orderLines.appendChild(orderLine);
				}
				else {
					if (rs1.getString("SUB_LINE_NO").contains("9001")) {
						Element orderLine = doc.createElement("OrderLine");
						orderLine.setAttribute("ChangeInQuantity", reproItemCount);
						orderLine.setAttribute("PrimeLineNo", rs1.getString("PRIME_LINE_NO"));
						orderLine.setAttribute("SubLineNo", rs1.getString("SUB_LINE_NO"));
						Element item = doc.createElement("Item");
						orderLine.appendChild(item);
						orderLines.appendChild(orderLine);		
					}
				}
				}
			}
			else {
				String Query_RegulerItem = "select * from " + schema 
						+ ".yfs_order_line where order_header_key in(select order_header_key from "
						+ schema + ".yfs_order_header where order_no='" + OrderNo
						+ "') and kit_code=' ' and item_id='" + reproItemId + "'and prime_line_no != '950'";
				System.out.println(Query);
				ResultSet rs1 = stat.executeQuery(Query_RegulerItem);
				while (rs1.next()) {
					Element orderLine = doc.createElement("OrderLine");
					orderLine.setAttribute("ChangeInQuantity", reproItemCount);
					orderLine.setAttribute("PrimeLineNo", rs1.getString("PRIME_LINE_NO"));
					if (SubProcess.contains("NonComponent")) {
						orderLine.setAttribute("SubLineNo", rs1.getString("SUB_LINE_NO"));
					}
					Element item = doc.createElement("Item");
					orderLine.appendChild(item);
					orderLines.appendChild(orderLine);
				}
			}
			root.appendChild(orderLines);
			doc.appendChild(root);
			AppModule.callApi("ProcessDCReproMsg", Modules.buildStringFromDocument(doc), true);
		}

	}
	public void ScheduleOrderApi(HashMap<String, String> XLTestData, String OrderNo, String StatusValue, ProlificsSeleniumAPI oPSelFW)
			throws Exception, Exception {

		// if (StatusValue.contains("Created")) {

		try {

			if (StatusValue.contains("Created")) {
				String Brand = XLTestData.get("Brand");
				if (Brand.contains("Williams-Sonoma")) {
					Brand = "WS";

				} else if (Brand.contains("Pottery Barn")) {
					Brand = "PB";
				} else if (Brand.contains("West Elm")) {
					Brand = "WE";
				} else if (Brand.contains("Pottery Barn Kids")) {
					Brand = "PK";
				} else if (Brand.contains("Pottery Barn Teen")) {
					Brand = "PT";
				}
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("ScheduleOrder");
				root.setAttribute("DocumentType", "0001");
				root.setAttribute("EnterpriseCode", Brand);
				root.setAttribute("OrderNo", OrderNo);
				doc.appendChild(root);
				saveFile(doc);

				AppModule.callApi("scheduleOrder", Modules.buildStringFromDocument(doc), false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			oPSelFW.reportStepDetails("Verify if exception is displayed in Scheduling Order", "Error is displayed in Scheduling the Order", "Fail");
			
		}
		// }

	}
	public void processOrderDropAck(HashMap<String, String> XLTestData, String actualShipmentDate, String StatusValue,
			String OrderNo,ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {
		Thread.sleep(3000);
		try {
			if (StatusValue.contains("Dropped For Fulfillment")) {
				
				String hostName, sid, username, password, schema;
				String Env = System.getProperty("Environment");
				if(Env.contains("STST2"))
				{
					hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
					sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
					username = gen.getPropertyValue("stst2.db.user", oPSelFW);
					password = gen.getPropertyValue("stst2.db.password", oPSelFW);
					schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
				}
				else
				{
					hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
					sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
					username = gen.getPropertyValue("stst.db.user", oPSelFW);
					password = gen.getPropertyValue("stst.db.password", oPSelFW);
					schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
				}
				stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
				Statement stat = stST2Con.createStatement();

				String Query = "select * from " + schema
						+ ".yfs_order_release where order_header_key in(select order_header_key from "
						+ schema + ".yfs_order_release where sales_order_no ='" + OrderNo + "')";

				ResultSet rs = stat.executeQuery(Query);

				String Brand = XLTestData.get("Brand");
				if (Brand.contains("Williams-Sonoma")) {
					Brand = "WS";

				} else if (Brand.contains("Pottery Barn")) {
					Brand = "PB";
				} else if (Brand.contains("West Elm")) {
					Brand = "WE";
				} else if (Brand.contains("Pottery Barn Kids")) {
					Brand = "PK";
				} else if (Brand.contains("Pottery Barn Teen")) {
					Brand = "PT";
				}

				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();
				Element root = doc.createElement("OrderRelease");
				while (rs.next()) {

					// OrderLine firstOrderLine = lines.get(0);

					root.setAttribute("ConsolidatorAddressCode", "");
					root.setAttribute("DocumentType", "0001");
					root.setAttribute("EnterpriseCode", Brand.trim());
					root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate.trim());
					root.setAttribute("OrderNo", OrderNo);
					String ShipAdviceNo = rs.getString("Ship_Advice_No");
					System.out.println(ShipAdviceNo.trim());
					root.setAttribute("ShipAdviceNo", ShipAdviceNo.trim());
					String ShipNode = rs.getString("SHIPNODE_KEY");
					root.setAttribute("ShipNode", ShipNode.trim());

				}

				doc.appendChild(root);
				saveFile(doc);
				AppModule.callApi("ProcessOrderDropAck", Modules.buildStringFromDocument(doc), true);
				// doc.appendChild(root);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DeliverShipmentMultiline(HashMap<String, String> XLTestData, String actualShipmentDate,
			String StatusValue, String orderNo, ProlificsSeleniumAPI oPSelFW) throws Exception {
		String hostName, sid, username, password, schema;
		DataBase database= new DataBase();
		try {
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			stST2Con = database.getDBConnection(oPSelFW);	
			Statement stat = stST2Con.createStatement();
			Thread.sleep(3000);
			String Query = "select ysl.order_no, ysl.prime_line_no,ysl.sub_line_no, ysl.item_id,ysl.QUANTITY, ys.tracking_no from "
					+ schema + ".yfs_shipment ys join " + schema
					+ ".yfs_shipment_line ysl on  ys.shipment_key=ysl.shipment_key where ysl.order_no='" + orderNo
					+ "' and prime_line_no != '950'";
			ResultSet rs = stat.executeQuery(Query);
			String Brand = XLTestData.get("Brand");
			if (Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
			} else if (Brand.contains("Pottery Barn")) {
				Brand = "PB";
			} else if (Brand.contains("West Elm")) {
				Brand = "WE";
			} else if (Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			} else if (Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}
	
			System.out.println(rs);
	
			while (rs.next()) {
	
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();
	
				Element root = doc.createElement("MultiApi");
				doc.appendChild(root);
	
				Element APINode = doc.createElement("API");
				APINode.setAttribute("FlowName", "ExecutePOD");
				root.appendChild(APINode);
	
				Element Input = doc.createElement("Input");
				APINode.appendChild(Input);
	
				Element ShipmentNode = doc.createElement("Shipment");
				ShipmentNode.setAttribute("OrderNo", orderNo);
				ShipmentNode.setAttribute("DocumentType", "0001");
				ShipmentNode.setAttribute("EnterpriseCode", Brand);
				ShipmentNode.setAttribute("StatusDate", actualShipmentDate);
	
				Input.appendChild(ShipmentNode);
	
				Element ContainersNode = doc.createElement("Containers");
				ShipmentNode.appendChild(ContainersNode);
	
				Element ContainerNode;
	
				ContainerNode = doc.createElement("Container");
				String trackingNo = rs.getString("TRACKING_NO");
				ContainerNode.setAttribute("TrackingNo", trackingNo);
				ContainerNode.setAttribute("ContainerNo", trackingNo);
				ContainerNode.setAttribute("ISDropShipContainer", "N");
				ContainersNode.appendChild(ContainerNode);
	
				Element ContainerDetailsNode = doc.createElement("ContainerDetails");
				ContainerNode.appendChild(ContainerDetailsNode);
	
				Element ContainerDetailNode = doc.createElement("ContainerDetail");
				String ItemId = rs.getString("ITEM_ID");
				ContainerDetailNode.setAttribute("ItemID", ItemId.trim());
				String OrderQuantity = rs.getString("QUANTITY");
				ContainerDetailNode.setAttribute("Quantity", OrderQuantity);
				ContainerNode.setAttribute("ISDropShipContainer", "N");
				ContainerDetailsNode.appendChild(ContainerDetailNode);
	
				Element ShipmentLineNode = doc.createElement("ShipmentLine");
				ShipmentLineNode.setAttribute("OrderNo", orderNo);
				String PRIMELINENO1 = rs.getString("PRIME_LINE_NO");
				ShipmentLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
				String SubLineNo1 = rs.getString("SUB_LINE_NO");
				ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
				ContainerDetailsNode.appendChild(ShipmentLineNode);
	
				// API Second Node
				APINode = doc.createElement("API");
				APINode.setAttribute("FlowName", "ExecuteContainerActivity");
				root.appendChild(APINode);
	
				Input = doc.createElement("Input");
				APINode.appendChild(Input);
	
				ContainerNode = doc.createElement("Container");
				ContainerNode.setAttribute("IsDropShipContainer", "N");
				ContainerNode.setAttribute("ContainerNo", trackingNo);
				Input.appendChild(ContainerNode);
	
				Element ContainerActivitiesNode = doc.createElement("ContainerActivities");
				ContainerNode.appendChild(ContainerActivitiesNode);
	
				Element ContainerActivityNode = doc.createElement("ContainerActivity");
				ContainerActivityNode.setAttribute("ActivityCode", "DELIVERED TO CUSTOMER");
				ContainerActivityNode.setAttribute("ActivityTimeStamp", actualShipmentDate);
				ContainerActivitiesNode.appendChild(ContainerActivityNode);
	
				Element ExtnNode = doc.createElement("Extn");
				ExtnNode.setAttribute("ExtnActivityTime", actualShipmentDate);
				ExtnNode.setAttribute("ExtnPkmsStatusDesc", "X1");
				ContainerActivityNode.appendChild(ExtnNode);
	
				Element ActivityLocationNode = doc.createElement("ActivityLocation");
				ContainerActivityNode.appendChild(ActivityLocationNode);
				ContainerActivityNode.appendChild(ActivityLocationNode);
	
				Element OrderNode = doc.createElement("Order");
				OrderNode.setAttribute("DocumentType", "0001");
				OrderNode.setAttribute("EnterpriseCode", Brand);
				OrderNode.setAttribute("OrderNo", orderNo);
				ContainerNode.appendChild(OrderNode);
	
				Element OrderLinesNode = doc.createElement("OrderLines");
				OrderNode.appendChild(OrderLinesNode);
	
				Element OrderLineNode = doc.createElement("OrderLine");
				OrderLineNode.setAttribute("OrderedQty", OrderQuantity);
				OrderLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
				OrderLineNode.setAttribute("SubLineNo", SubLineNo1);
				OrderLinesNode.appendChild(OrderLineNode);
	
				Element ItemNode = doc.createElement("Item");
				ItemNode.setAttribute("PackItemID", "");
				ItemNode.setAttribute("ItemID", ItemId.trim());
				OrderLineNode.appendChild(ItemNode);
				saveFile(doc);
				apiOut = AppModule.callApi("multiApi", Modules.buildStringFromDocument(doc), false);
				// }
			}
			}
			catch(Exception e)
			{
				oPSelFW.reportStepDetails("Verify if exception is displayed in Delivering Order", "Error is displayed in Order Delivery", "Fail");
				Assert.assertEquals("Verify if exception is displayed in Delivering Order", "Error is displayed in Order Delivery");
			}
	}
	public String ConfirmShip(String OrderNo, HashMap<String, String> XLTestData, String actualShipmentDate, String StatusValue,  ProlificsSeleniumAPI oPSelFW) throws Exception {
		String TrackingNo = "";
		try{
		if (StatusValue.contains("Invoice Preship")) {
		String hostName, sid, username, password, schema;
		String Env = System.getProperty("Environment");
		if(Env.contains("STST2"))
		{
			hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
			sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
			username = gen.getPropertyValue("stst2.db.user", oPSelFW);
			password = gen.getPropertyValue("stst2.db.password", oPSelFW);
			schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
		}
		else
		{
			hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
			sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
			username = gen.getPropertyValue("stst.db.user", oPSelFW);
			password = gen.getPropertyValue("stst.db.password", oPSelFW);
			schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
		}
		String Brand = XLTestData.get("Brand");
		if (Brand.contains("Williams-Sonoma")) {
		Brand = "WS";
		} else if (Brand.contains("Pottery Barn")) {
		Brand = "PB";
		} else if (Brand.contains("West Elm")) {
		Brand = "WE";
		} else if (Brand.contains("Pottery Barn Kids")) {
		Brand = "PK";
		} else if (Brand.contains("Pottery Barn Teen")) {
		Brand = "PT";
		}
		stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);
		 
		Statement stat = stST2Con.createStatement();
		String Query = "select * from " + schema + ".yfs_order_line where order_header_key in(select order_header_key from " + schema + ".yfs_order_header where order_no='" + OrderNo + "') and kit_code=' ' and prime_line_no != '950'";
		System.out.println("Query is " + Query);
		ResultSet rs = stat.executeQuery(Query);
		System.out.println("Execute Query is " + Query);
		System.out.println(rs);
		int num = 1101;
		for (int i = 1101; i <= num; i += 1) {
		while (rs.next()) {
		num += 1;
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();
		// OrderLine firstOrderLine = lines.get(0);
		Element root = doc.createElement("Shipment");

		root.setAttribute("Action", "Create");
		root.setAttribute("DocumentType", "0001");
		// root.setAttribute("CarrierServiceCode", "FURNITURE_REGULAR");
		root.setAttribute("EnterpriseCode", Brand);
		root.setAttribute("SCAC", "WS_CARRIER");
		root.setAttribute("SellerOrganizationCode", Brand + "DTC");
		String ShipNode = rs.getString("SHIPNODE_KEY");
		root.setAttribute("ShipNode", ShipNode.trim());
		root.setAttribute("TrackingNo", OrderNo + num);
		root.setAttribute("ActualShipmentDate", actualShipmentDate);
		root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate);
		// root.setAttribute("ConsolidatorAddressCode", consolidatorAddressCode);
		doc.appendChild(root);

		Element ShipmentLines = doc.createElement("ShipmentLines");
		root.appendChild(ShipmentLines);

		Element ShipmentLine = doc.createElement("ShipmentLine");
		ShipmentLine.setAttribute("Action", "Create");
		ShipmentLine.setAttribute("DocumentType", "0001");
		String ItemId = rs.getString("ITEM_ID");
		ShipmentLine.setAttribute("ItemID", ItemId.trim());
		ShipmentLine.setAttribute("OrderNo", OrderNo);

		String PRIMELINENO = rs.getString("PRIME_LINE_NO");

		ShipmentLine.setAttribute("PrimeLineNo", PRIMELINENO);
		ShipmentLine.setAttribute("ProductClass", "");

		String OrderQuantity = rs.getString("ORDERED_QTY");
		ShipmentLine.setAttribute("Quantity", OrderQuantity);
		ShipmentLine.setAttribute("UnitOfMeasure", "EACH");
		String SubLineNo = rs.getString("SUB_LINE_NO");
		ShipmentLine.setAttribute("SubLineNo", SubLineNo);
		ShipmentLine.setAttribute("ReleaseNo", "1");
		// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
		ShipmentLines.appendChild(ShipmentLine);

		Element Containers = doc.createElement("Containers");
		root.appendChild(Containers);

		Element Container = doc.createElement("Container");
		Container.setAttribute("Action", "Create");
		Container.setAttribute("ContainerNo", OrderNo + num);
		Container.setAttribute("TrackingNo", OrderNo + num);
		// Container.setAttribute("Ucc128code", "");
		Containers.appendChild(Container);

		Element ContainerDetails = doc.createElement("ContainerDetails");
		Container.appendChild(ContainerDetails);

		Element ContainerDetail = doc.createElement("ContainerDetail");
		ContainerDetail.setAttribute("Action", "Create");
		ContainerDetail.setAttribute("Quantity", OrderQuantity);
		ContainerDetails.appendChild(ContainerDetail);

		Element ShipmentLineNode = doc.createElement("ShipmentLine");
		ShipmentLineNode.setAttribute("OrderNo", OrderNo);
		String PRIMELINENO1 = rs.getString("PRIME_LINE_NO");
		ShipmentLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
		String SubLineNo1 = rs.getString("SUB_LINE_NO");
		ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
		ShipmentLineNode.setAttribute("ReleaseNo", "1");
		// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
		ContainerDetail.appendChild(ShipmentLineNode);

		Element ShipmentTagSerials = doc.createElement("ShipmentTagSerials");
		ContainerDetail.appendChild(ShipmentTagSerials);
		saveFile(doc);
		AppModule.callApi("ConfirmShipment", Modules.buildStringFromDocument(doc), true);

		}

		}

		}
		}
		catch(Exception e)
		{
		oPSelFW.reportStepDetails("Verify if exception is displayed in Shipping the Order", e.getMessage() + " is displayed in Shipping the Order", "Fail");
		Assert.assertEquals("Verify if exception is displayed in Shipping the Order", e.getLocalizedMessage() + "Error is displayed in Shipping the Order");
		}
		return TrackingNo;
		}
	public void processPartaillyDCCancel(HashMap<String, String> XLTestData, String OrderNo, String StatusValue,ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {

		String SubProcess = XLTestData.get("SubProcess").toString();
		String reproItemId =XLTestData.get("reproItemId").toString();
		String reproItemCount=XLTestData.get("reproItemCount").toString();
		String ItemType = null;
		
		if (StatusValue.contains("Invoice Preship")||StatusValue.contains("PreReprocessed")) {
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();
			String Query = "select count(*) as count from "
					+ schema + ".yfs_order_header where order_no='" + OrderNo + "'";
			System.out.println(Query);

			ResultSet rs = stat.executeQuery(Query);
			rs.next();
			System.out.println(rs.getString("count"));
			Query = "select * from " + schema
					+ ".yfs_order_line where order_header_key in(select order_header_key from "
					+ schema + ".yfs_order_header where order_no='" + OrderNo + "') and item_id='"
					+ reproItemId + "'and prime_line_no != '950'";

			System.out.println(Query);

			rs = stat.executeQuery(Query);
			
			while (rs.next()) 
			{ 
				ItemType = rs.getString("KIT_CODE"); 
			}
			
			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element root = doc.createElement("OrderRelease");
			root.setAttribute("Action", "MODIFY");
			root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
			root.setAttribute("ModificationReasonCode", "CANCEL_CS");
			root.setAttribute("ModificationReasonText", "CUSTOMER CANCELLATION REASON CODE");
			root.setAttribute("OrderNo", OrderNo);
			root.setAttribute("Override", "Y");
			Element orderLines = doc.createElement("OrderLines");
			if (ItemType.equalsIgnoreCase("BUNDLE")) {
				String Query_Bundle = "select * from " + schema
						+ ".yfs_order_line where order_header_key in(select order_header_key from "
						+ schema + ".yfs_order_header where order_no='" + OrderNo
						+ "') and kit_code=' ' and prime_line_no != '950'";
				System.out.println(Query_Bundle);
				ResultSet rs1 = stat.executeQuery(Query_Bundle);
				while (rs1.next()) {
					if (SubProcess.equalsIgnoreCase("NonComponent")) {
					Element orderLine = doc.createElement("OrderLine");
					orderLine.setAttribute("Action", "CANCEL");
					orderLine.setAttribute("ChangeInQuantity", reproItemCount);
					orderLine.setAttribute("PrimeLineNo", rs.getString("PRIME_LINE_NO")); 
					orderLine.setAttribute("SubLineNo", rs1.getString("SUB_LINE_NO"));
					Element item = doc.createElement("Item");
					orderLine.appendChild(item);
					orderLines.appendChild(orderLine);
				} else { if (rs1.getString("SUB_LINE_NO").contains("9001")) {
					Query = "select * from " + schema
							+ ".yfs_order_line where order_header_key in(select order_header_key from "
							+ schema + ".yfs_order_header where order_no='" + OrderNo + "') and item_id='"
							+ reproItemId + "'and prime_line_no != '950'";
					System.out.println(Query);
					rs = stat.executeQuery(Query);
					rs.next();
						Element orderLine = doc.createElement("OrderLine");
						orderLine.setAttribute("Action", "CANCEL");
						orderLine.setAttribute("ChangeInQuantity", reproItemCount);
						System.out.println("PRIME_LINE_NO "+rs.getString("PRIME_LINE_NO"));
						orderLine.setAttribute("PrimeLineNo", rs.getString("PRIME_LINE_NO"));
						orderLine.setAttribute("SubLineNo", rs.getString("SUB_LINE_NO"));
						System.out.println("SUB_LINE_NO" + rs.getString("SUB_LINE_NO"));
						oPSelFW.reportStepDetails("Perfroming DC Cancel On Component of Bundle item", "DC Cancel Item is"+reproItemId+" With Quantity Is"+reproItemCount , "Pass");
						Element item = doc.createElement("Item");
						orderLine.appendChild(item);
						orderLines.appendChild(orderLine);	}
				}
				}
			}
			else {
				String Query_RegulerItem1 = "select * from " + schema
						+ ".yfs_order_line where order_header_key in(select order_header_key from "
						+ schema + ".yfs_order_header where order_no='" + OrderNo
						+ "') and kit_code=' ' and item_id='" + reproItemId + "'and prime_line_no != '950'";
				System.out.println(Query);
				ResultSet rs1 = stat.executeQuery(Query_RegulerItem1);
				while (rs1.next()) {
					Element orderLine = doc.createElement("OrderLine");
					orderLine.setAttribute("Action", "CANCEL");
					orderLine.setAttribute("ChangeInQuantity", reproItemCount);
					orderLine.setAttribute("PrimeLineNo", rs1.getString("PRIME_LINE_NO"));
					orderLine.setAttribute("SubLineNo", rs1.getString("SUB_LINE_NO"));
					Element item = doc.createElement("Item");
					orderLine.appendChild(item);
					orderLines.appendChild(orderLine);
				}
			}

			root.appendChild(orderLines);
			doc.appendChild(root);

			AppModule.callApi("ProcessDCReproMsg", Modules.buildStringFromDocument(doc), true);
			String OrdStatus=getOrderStatus(OrderNo, oPSelFW);
			
			
			oPSelFW.reportStepDetails("Partiall Pre DC Cancel has done", "Pre DC Cancel has Completed Succesfully and OrderStatus is"+OrdStatus, "Pass");
		}

	}
	public String ConfirmShip(ResultSet rs, HashMap<String, String> XLTestData, String actualShipmentDate,
			String StatusValue, String OrderNo, ProlificsSeleniumAPI oPSelFW) throws Exception {
		String TrackingNo = "";
		try{
		if (StatusValue.contains("Invoice Preship")) {
			String Brand = XLTestData.get("Brand");
			if (Brand.contains("Williams-Sonoma")) {
				Brand = "WS";
			} else if (Brand.contains("Pottery Barn")) {
				Brand = "PB";
			} else if (Brand.contains("West Elm")) {
				Brand = "WE";
			} else if (Brand.contains("Pottery Barn Kids")) {
				Brand = "PK";
			} else if (Brand.contains("Pottery Barn Teen")) {
				Brand = "PT";
			}
			int num = 1101;
			for (int i = 1101; i <= num; i += 1) {
				while (rs.next()) {
					num += 1;
					DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = df.newDocumentBuilder();
					Document doc = db.newDocument();
					// OrderLine firstOrderLine = lines.get(0);
					Element root = doc.createElement("Shipment");

					root.setAttribute("Action", "Create");
					root.setAttribute("DocumentType", "0001");
					// root.setAttribute("CarrierServiceCode", "FURNITURE_REGULAR");
					root.setAttribute("EnterpriseCode", Brand);
					root.setAttribute("SCAC", "WS_CARRIER");
					root.setAttribute("SellerOrganizationCode", Brand + "DTC");
					String ShipNode = rs.getString("SHIPNODE_KEY");
					root.setAttribute("ShipNode", ShipNode.trim());
					root.setAttribute("TrackingNo", OrderNo + num);
					root.setAttribute("ActualShipmentDate", actualShipmentDate);
					root.setAttribute("PkMSShipmentTimestamp", actualShipmentDate);
					// root.setAttribute("ConsolidatorAddressCode", consolidatorAddressCode);
					doc.appendChild(root);

					Element ShipmentLines = doc.createElement("ShipmentLines");
					root.appendChild(ShipmentLines);

					Element ShipmentLine = doc.createElement("ShipmentLine");
					ShipmentLine.setAttribute("Action", "Create");
					ShipmentLine.setAttribute("DocumentType", "0001");
					String ItemId = rs.getString("ITEM_ID");
					ShipmentLine.setAttribute("ItemID", ItemId.trim());
					ShipmentLine.setAttribute("OrderNo", OrderNo);

					String PRIMELINENO = rs.getString("PRIME_LINE_NO");

					ShipmentLine.setAttribute("PrimeLineNo", PRIMELINENO);
					ShipmentLine.setAttribute("ProductClass", "");

					String OrderQuantity = rs.getString("ORDERED_QTY");
					ShipmentLine.setAttribute("Quantity", OrderQuantity);
					ShipmentLine.setAttribute("UnitOfMeasure", "EACH");
					String SubLineNo = rs.getString("SUB_LINE_NO");
					ShipmentLine.setAttribute("SubLineNo", SubLineNo);
					ShipmentLine.setAttribute("ReleaseNo", "1");
					// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
					ShipmentLines.appendChild(ShipmentLine);

					Element Containers = doc.createElement("Containers");
					root.appendChild(Containers);

					Element Container = doc.createElement("Container");
					Container.setAttribute("Action", "Create");
					Container.setAttribute("ContainerNo", OrderNo + num);
					Container.setAttribute("TrackingNo", OrderNo + num);
					// Container.setAttribute("Ucc128code", "");
					Containers.appendChild(Container);

					Element ContainerDetails = doc.createElement("ContainerDetails");
					Container.appendChild(ContainerDetails);

					Element ContainerDetail = doc.createElement("ContainerDetail");
					ContainerDetail.setAttribute("Action", "Create");
					ContainerDetail.setAttribute("Quantity", OrderQuantity);
					ContainerDetails.appendChild(ContainerDetail);

					Element ShipmentLineNode = doc.createElement("ShipmentLine");
					ShipmentLineNode.setAttribute("OrderNo", OrderNo);
					String PRIMELINENO1 = rs.getString("PRIME_LINE_NO");
					ShipmentLineNode.setAttribute("PrimeLineNo", PRIMELINENO1);
					String SubLineNo1 = rs.getString("SUB_LINE_NO");
					ShipmentLineNode.setAttribute("SubLineNo", SubLineNo1);
					ShipmentLineNode.setAttribute("ReleaseNo", "1");
					// ShipmentLine.setAttribute("ShipAdviceNo", shipAdviceNumber);
					ContainerDetail.appendChild(ShipmentLineNode);

					Element ShipmentTagSerials = doc.createElement("ShipmentTagSerials");
					ContainerDetail.appendChild(ShipmentTagSerials);
					saveFile(doc);
					AppModule.callApi("ConfirmShipment", Modules.buildStringFromDocument(doc), true);

				}

			}

		}
		}
		catch(Exception e)
		{
			oPSelFW.reportStepDetails("Verify if exception is displayed in Shipping the Order", "Error is displayed in Shipping the Order", "Fail");
			Assert.assertEquals("Verify if exception is displayed in Shipping the Order", "Error is displayed in Shipping the Order");
		}
		return TrackingNo;
	}
	public void processFullDCCancel(HashMap<String, String> XLTestData, String OrderNo, String StatusValue,
			ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {

		
		
		if (StatusValue.contains("Invoice Preship")) {
			
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			

			/* String orderNo = "901291194339"; */
			
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();

			// String orderNo = "990939477007";

			String Query = "select * from " + schema
					+ ".yfs_order_line where order_header_key in(select order_header_key from "
					+ schema + ".yfs_order_header where order_no='" + OrderNo
					+ "') and kit_code=' ' and prime_line_no != '950'";

			ResultSet rs = stat.executeQuery(Query);

			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("OrderRelease");
			root.setAttribute("Action", "MODIFY");
			root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
			root.setAttribute("ModificationReasonCode", "CANCEL_CS");
			root.setAttribute("ModificationReasonText", "CUSTOMER CANCELLATION REASON CODE");
			root.setAttribute("OrderNo", OrderNo);
			root.setAttribute("Override", "Y");

			Element orderLines = doc.createElement("OrderLines");

			while (rs.next()) {
				Element orderLine = doc.createElement("OrderLine");

				String orderedQty = rs.getString("ORDERED_QTY");
				String changeQty = "-" + orderedQty;
				orderLine.setAttribute("Action", "CANCEL");
				orderLine.setAttribute("ChangeInQuantity", changeQty);
				orderLine.setAttribute("PrimeLineNo", rs.getString("PRIME_LINE_NO"));
				orderLine.setAttribute("SubLineNo", rs.getString("SUB_LINE_NO"));

				Element item = doc.createElement("Item");

				orderLine.appendChild(item);

				orderLines.appendChild(orderLine);
			}

			root.appendChild(orderLines);
			doc.appendChild(root);

			AppModule.callApi("ProcessDCReproMsg", Modules.buildStringFromDocument(doc), true);
		}

	}
	public void processFullDCRepro(HashMap<String, String> XLTestData, String OrderNo, String StatusValue,
			ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {

		if (StatusValue.contains("Invoice Preship")) {
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			

			/* String orderNo = "901291194339"; */
			
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();

			// String orderNo = "990939477007";

			String Query = "select * from " + schema
					+ ".yfs_order_line where order_header_key in(select order_header_key from "
					+ schema + ".yfs_order_header where order_no='" + OrderNo
					+ "') and kit_code=' ' and prime_line_no != '950'";

			ResultSet rs = stat.executeQuery(Query);

			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("OrderRelease");
			root.setAttribute("Action", "MODIFY");
			root.setAttribute("EnterpriseCode", XLTestData.get("Brand").toString());
			root.setAttribute("ModificationReasonCode", "REPRO_DC");
			root.setAttribute("ModificationReasonText", "INVENTORY_DISCREPANCY");
			root.setAttribute("OrderNo", OrderNo);
			root.setAttribute("Override", "Y");

			Element orderLines = doc.createElement("OrderLines");

			while (rs.next()) {
				Element orderLine = doc.createElement("OrderLine");

				String orderedQty = rs.getString("ORDERED_QTY");
				String changeQty = "-" + orderedQty;

				orderLine.setAttribute("ChangeInQuantity", changeQty);
				orderLine.setAttribute("PrimeLineNo", rs.getString("PRIME_LINE_NO"));
				orderLine.setAttribute("SubLineNo", rs.getString("SUB_LINE_NO"));

				Element item = doc.createElement("Item");

				orderLine.appendChild(item);

				orderLines.appendChild(orderLine);
			}

			root.appendChild(orderLines);
			doc.appendChild(root);

			AppModule.callApi("ProcessDCReproMsg", Modules.buildStringFromDocument(doc), true);
		}

	}
	public void invoicePreShip(HashMap<String, String> XLTestData, String orderNumber, String StatusValue,
			ProlificsSeleniumAPI oPSelFW) throws TransformerException, Exception {
		Thread.sleep(3000);
		if (StatusValue.contains("Acknowledge")) {
			String hostName, sid, username, password, schema;
			String Env = System.getProperty("Environment");
			if(Env.contains("STST2"))
			{
				hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst2.db.user", oPSelFW);
				password = gen.getPropertyValue("stst2.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
			}
			else
			{
				hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
				sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
				username = gen.getPropertyValue("stst.db.user", oPSelFW);
				password = gen.getPropertyValue("stst.db.password", oPSelFW);
				schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
			}
			stST2Con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostName + ":1521:" + sid, username, password);	
			Statement stat = stST2Con.createStatement();

			String sql = String.format("select task_q_key, transaction_key, data_key from " + schema
					+ ".yfs_task_q where transaction_key in (select transaction_key from " + schema
					+ ".yfs_transaction where tranid='WSI_ORD_ACK_INVOICE.0001.ex')"
					+ "and data_key in (select order_header_key from " + schema
					+ ".yfs_order_header where order_no='%s')", orderNumber);
			System.out.println(sql);
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = df.newDocumentBuilder();
				Document doc = db.newDocument();

				Element root = doc.createElement("Task");
				root.setAttribute("DataKey", rs.getString("data_key"));
				System.out.println(rs.getString("data_key"));
				root.setAttribute("TaskQKey", rs.getString("task_q_key"));
				root.setAttribute("TransactionKey", rs.getString("transaction_key"));
				doc.appendChild(root);
				saveFile(doc);
				AppModule.callApi("CreateOrderInvoiceService", Modules.buildStringFromDocument(doc), true);
			}
			String StatusValuePreShipafterApi = getOrderStatus(orderNumber, oPSelFW); 
			oPSelFW.reportStepDetails("Verify the Status of the Order in Sterling DB", "Order " + orderNumber + " is in " + StatusValuePreShipafterApi + " Status", "Pass");
		}

	}
	public void adjustInventory(String StatusValue,String OrderNo,HashMap<String, String> XLTestData,ProlificsSeleniumAPI oPSelFW) throws Throwable   {		
		//basetest.test.log(Status.INFO, "Order is in<span style='font-weight:bold;color:blue' >Await AUTHORIZATION Status</span>");
	String hostName, sid, username, password, schema;
	DataBase database = new DataBase();
	String Env = System.getProperty("Environment");
	if(Env.contains("STST2"))
	{
		hostName = gen.getPropertyValue("stst2.db.hostname", oPSelFW);
		sid = gen.getPropertyValue("stst2.db.sid", oPSelFW);
		username = gen.getPropertyValue("stst2.db.user", oPSelFW);
		password = gen.getPropertyValue("stst2.db.password", oPSelFW);
		schema = gen.getPropertyValue("stst2.db.schema", oPSelFW);
	}
	else
	{
		hostName = gen.getPropertyValue("stst.db.hostname", oPSelFW);
		sid = gen.getPropertyValue("stst.db.sid", oPSelFW);
		username = gen.getPropertyValue("stst.db.user", oPSelFW);
		password = gen.getPropertyValue("stst.db.password", oPSelFW);
		schema = gen.getPropertyValue("stst.db.schema", oPSelFW);
	}
	stST2Con = database.getDBConnection(oPSelFW);
	Statement stat = stST2Con.createStatement();
	String Query = "select * from "+schema+".yfs_order_line where order_header_key in(select order_header_key from "+schema+".yfs_order_header where order_no='"
			+ OrderNo + "') and kit_code=' '";
	ResultSet rs = stat.executeQuery(Query);
	String Concept=XLTestData.get("Brand").toString();	
	DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = df.newDocumentBuilder();
	Document doc = db.newDocument();
	Element root = doc.createElement("Items");			
	while (rs.next()) {
		Element Item = doc.createElement("Item");
		Item.setAttribute("AdjustmentType", "ADJUSTMENT");
		Item.setAttribute("Availability", "TRACK");
		Item.setAttribute("ETA", "");
		Item.setAttribute("ItemID", rs.getString("ITEM_ID").trim());
		Item.setAttribute("OrganizationCode", Concept);
		Item.setAttribute("ProductClass", "");
		Item.setAttribute("Quantity", "100");
		Item.setAttribute("RemoveInventoryNodeControl", "Y");
		Item.setAttribute("ShipByDate", "");
		if(rs.getString("PRODUCT_LINE").trim().contains("FN")) {
		Item.setAttribute("ShipNode", "LAXDTC");
		System.out.println("Inventry got updated in FN DC ");
		}
		if(rs.getString("PRODUCT_LINE").trim().contains("CMO")) {
			Item.setAttribute("ShipNode", "OB4DTC");
			System.out.println("Inventry got updated in CMO DC ");
			}
		if(rs.getString("PRODUCT_LINE").trim().contains("SS")) {
			Item.setAttribute("ShipNode", "SCCDTC");
			System.out.println("Inventry got updated in SS DC ");
			}
		Item.setAttribute("SupplyType", "ONHAND");
		Item.setAttribute("TransactionTimestamp", "null");
		Item.setAttribute("UnitOfMeasure", "EACH");
		Item.setAttribute("FromSupplyLineReference", "");
		Item.setAttribute("FromSupplyType", "");
		Item.setAttribute("FromShipByDate", " ");
		Item.setAttribute("FromSegment", " ");
		Item.setAttribute("FromSegmentType", " ");
		Item.setAttribute("FromSupplyReference", " ");
		Item.setAttribute("FromSupplyReferenceType", " ");
		Item.setAttribute("FromETA", " ");
		root.appendChild(Item);
		}
		doc.appendChild(root);
		
	saveFile(doc);
	apiOut = AppModule.callApi("adjustInventory", Modules.buildStringFromDocument(doc), false);
	

}
	public void releaseHolds(ResultSet rs, HashMap<String, String> XLTestData, String OrderNumber, ProlificsSeleniumAPI oPSelFW) throws Exception {
		Thread.sleep(10000);
		try {
			List<String> unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData, oPSelFW);
			while (unresolved_holds.size() > 0) {
				for (String holdType : unresolved_holds) {
					// TODO: identify what indicates order line level hold
					if ("VALIDATION_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					} else if ("CONTINUITY_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					} else if ("EVENT_HOLD_WINDOWED".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("IDS_VALIDATION_HOLD".equals(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("UPHOLSTERY_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					} else if ("Concierge_Hold".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("EVENT_HOLD_Automatic".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("REPRO_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					} else if ("DELAYED_ORDER_DROP".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					} else if ("WINDOWED_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						System.out.println("Hold Resolved");

					} else if ("FUTURE_REL_HOLD".equalsIgnoreCase(holdType)) {
						releaseOrderLineHold(holdType, OrderNumber, XLTestData, oPSelFW);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");
					}

					else {

						releaseHold(OrderNumber, holdType, XLTestData);
						// oPSelFW.reportStepDetails(holdType, "Hold resolved Successfully", "Pass");
						System.out.println("Hold Resolved");

					}
				}				unresolved_holds = getUresolvedHolds(OrderNumber, XLTestData, oPSelFW);
				System.out.println("Hold Resolved");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			oPSelFW.reportStepDetails("Verify if exception is displayed in Release Holds", e.getMessage() + "Exception is displayed in Release Holds of the Order", "Fail");
			Assert.assertEquals("Verify if exception is displayed in Release Holds", e.getMessage() + "Exception is displayed in Release Holds of the Order");			
		}

	}

}
