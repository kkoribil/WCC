package org.wsi.module;

import java.util.HashMap;

public class RESTServiceTestHelper {
	private String contentType = "application/json";

	private int responseCode = 200;
	private String responseBody;
	private HashMap<String, String> inputData;
	private HashMap<String, String> headerData;
	private String responseMessage;

	public RESTServiceTestHelper() {
		inputData = new HashMap<String, String>();
		headerData = new HashMap<String, String>();
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HashMap<String, String> getHeaderData() {
		return headerData;
	}

	public String getHeaderData(String key) {
		return headerData.get(key);
	}

	public void setHeaderData(String key, String value) {
		this.headerData.put(key, value);
	}

	public void setHeaderData(String headerString) {
		generateHeaderData(headerString);
	}

	public HashMap<String, String> getInputData() {
		return inputData;
	}

	public String getInputData(String key) {
		return inputData.get(key);
	}

	public void setInputData(String key, String value) {
		this.inputData.put(key, value);
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getFormattedApiPathSegment(String apiPath) {
		apiPath = apiPath.toLowerCase().trim();
		apiPath = apiPath.startsWith("/") ? apiPath : "/"+apiPath;
		return apiPath;
	}

	public String getServiceURL(String serviceURL) {
		return String.format(AppModule.getRESTServiceHost() + ":%s", serviceURL);
	}

	public String getAppointmentServiceURL(String version, String param) {
		return getServiceURL(String.format("%s/appointment/%s/workorders/%s", AppModule.getRESTServiceHostAppointmentPort(), version, param));
	}

	public String getOrderSearchHistoryServiceURL(String version) {
		return getServiceURL(String.format("%s/order/%s/history", AppModule.getRESTServiceHostOrderPort(), version));	}
	
	public String getOrderInventorySearchHistoryServiceURL(String version) {
		return getServiceURL(String.format("%s/transfers/%s/history", AppModule.getRESTServiceHostHistoryPort(), version));
	}
	
	
	public String getOrderSearchWorkOrdersServiceURL(String version) {
		return getServiceURL(String.format("%s/order/%s/workorders", AppModule.getRESTServiceHostOrderPort(), version));
	}

	public String getOrderSearchShipmentssServiceURL(String version) {
		return getServiceURL(String.format("%s/order/%s/shipments", AppModule.getRESTServiceHostOrderPort(), version));
	}
	
	public String getFutureAllocationsCancelOrderLinesURL(String version, String orderHeaderKey) {
		return getServiceURL(String.format("%s/order/%s/%s", AppModule.getRESTServiceHostOrderPort(), version, orderHeaderKey));
	}

	public void generateHeaderData(String headerString) {
		if(!headerString.isEmpty()) {
			//TODO: Use a library to generate header hashmap
			String[] headerValues = headerString.split(":");
			this.setHeaderData(headerValues[0], headerValues[1]);
		}
	}

	public void clearInputData() {
		this.inputData.clear();
	}

	public void clearHeaderData() {
		this.headerData.clear();
	}
}
