package org.wsi.module;

public class SterlingXMLException extends Exception{
	
	/**
	 * https://stackoverflow.com/a/285809/298149 ???
	 */
	private static final long serialVersionUID = 1L;

	public SterlingXMLException(){}
	
	public SterlingXMLException(String message){
		super(message);
	}
}
