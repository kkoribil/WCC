package org.wsi.module.resthandler;

public class RESTResponse {
	private String message;
	private Integer statusCode;
	
	public RESTResponse(String msg, Integer code) {
		this.message = msg;
		this.statusCode = code;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

}
