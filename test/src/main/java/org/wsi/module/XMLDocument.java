package org.wsi.module;

import org.wsi.module.Modules;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsi.module.Modules;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XMLDocument {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Document doc;
	public String template = null;

	protected Document buildXML(String xml_path) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Path path = Paths.get(xml_path);
	    Document create_item = builder.parse(new ByteArrayInputStream(Files.readAllBytes(path)));

		return create_item;
	}
	
	protected String readXMLFromFile(String xml_path) throws IOException{
	    Path path = Paths.get(xml_path);
	    byte[] encoded = Files.readAllBytes(path);
	    String xml_data = new String(encoded, Charset.defaultCharset());
		return xml_data;
	}

	public void setElement(Document doc, String tag_name, String attribute_name, String attribute_value) throws Exception {
		NodeList nList = doc.getElementsByTagName(tag_name);
		Node n = null;
		if(nList.getLength() > 0){
			
			// set starting iteration to 0 (if we are looping through the whole list) or the item we care about
			for (int i = 0; i < nList.getLength(); i++) {
	 			n = nList.item(i).getAttributes().getNamedItem(attribute_name);
	 			if(n != null) {
	 				n.setNodeValue(attribute_value);
	 			}
			}
		}
	}
	
	public void setElementByItem(Document doc, String tag_name, String attribute_name, String attribute_value, Integer childNo) throws Exception {  
		NodeList nList = doc.getElementsByTagName(tag_name);
		Node n = null;
		if(nList.getLength() > 0){
			
			// set starting iteration to 0 (if we are looping through the whole list) or the item we care about
			int i = childNo == null ? 0 : childNo.intValue();
			do{
				
				n = nList.item(i).getAttributes().getNamedItem(attribute_name);
				if(n != null) {
					n.setNodeValue(attribute_value);
				}else{
					throw new Exception(String.format("Attribute %s does not exist",attribute_name));
				}
				i++;
				
			// loop through the whole list if childNo is null
			// do not  loop (just edit the starting iteration) if childNo is not null
			}while((childNo == null || i < childNo) && i < nList.getLength());		  
		}
	}
	
	/**
	 * Add a new element to an XML document
	 * Will append to the last child of the root element if there is more than one
	 * Will throw an exception if the root element does not exist
	 * @param doc XML Document object to append to
	 * @param root String name of the root element.  Must exist
	 * @param element String name of the element to create
	 * @param attributes List<Entry<String,String>> of attributes and their values to add to the new element
	 * @throws Exception
	 */
	public void setNewElement(Document doc, String root, String element, List<Entry<String,String>> attributes) throws Exception{
		Element elem = doc.createElement(element);
		for(Entry entry : attributes){
			elem.setAttribute((String)entry.getKey(),(String)entry.getValue());
		}
		
		NodeList nList = doc.getElementsByTagName(root);
		
		// ensure root exists
		if(nList.getLength() == 0){
			throw new Exception(String.format("Root element %s does not exist.", root));
		}else{
			nList.item(nList.getLength() - 1).appendChild(elem);// Append child to last element
			
		}
	}
	
	public String getElement(Document doc, String tag_name, String attribute_name) throws Exception {
		NodeList elements = doc.getElementsByTagName(tag_name);
		if(elements.getLength() > 0){
			Node attribute = elements.item(0).getAttributes().getNamedItem(attribute_name);
			if(attribute != null){
				return attribute.getNodeValue();
			}else{
				throw new Exception(String.format("Attribute: %s does not exist in document: %s", attribute_name, stringHelper(doc)));
			}
		}else{
			throw new Exception(String.format("Element: %s does not exist in document: %s", tag_name, stringHelper(doc)));
		}
	}
	
	public String getElementByItem(Document doc, String tag_name, String attribute_name, int childNo) {
		 return doc.getElementsByTagName(tag_name)
		 .item(childNo)
		 .getAttributes()
		 .getNamedItem(attribute_name)
		 .getNodeValue();
	}
	
	public int getElementCount(Document doc, String tag_name){
		return doc.getElementsByTagName(tag_name).getLength();
	}
	
	public NodeList getElementChildren(Document doc, String tag_name){
		Node nod;
		if((nod = doc.getElementsByTagName(tag_name).item(0)) != null){
			return nod.getChildNodes();
		}else{
			return null;
		}
	}
	
	private String stringHelper(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String out = "Invalid";
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			out = writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}
	
	public String toString(){
		try {
			return Modules.buildStringFromDocument(this.doc);
		} catch (TransformerException e) {
			logger.error("Exception caught while building string from doc");
			throw new RuntimeException(e);
		}
	}

	protected String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (Exception e) {
			logger.error("nodeToString Transformer Exception");
			throw new RuntimeException(e);
		}
		return sw.toString();
	}

	public String getRootPath(){
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}
	
	public String GetAttrValueByTagName(String xml, String NameOfNode, String NameofAttr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			Node node = doc.getElementsByTagName(NameOfNode).item(0);
			Attr attr = (Attr) node.getAttributes().getNamedItem(NameofAttr);
			return (attr.getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String GetAttrValueByTagName1(String xml, String NameOfNode, String NameofAttr, int NodeIndex) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xml)));
			System.out.println(doc.getElementsByTagName(NameOfNode).getLength());
			Node node = doc.getElementsByTagName(NameOfNode).item(NodeIndex);
			Attr attr = (Attr) node.getAttributes().getNamedItem(NameofAttr);
			return (attr.getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
