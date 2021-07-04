package org.wsi.test.Order;

import static org.testng.Assert.fail;

//import static org.testng.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.wsi.module.AppModule;
import org.wsi.module.Modules;
import org.xml.sax.SAXException;

public class ReturnReceiveApiTest{

	/*
	 * public void testCreateOrder(BaseTest basetest, HashMap<String, String>
	 * XLTestData) throws Exception {
	 * 
	 * try { basetest.test.log(Status.INFO,
	 * "Started Execution for <span style='font-weight:bold;color:blue'> Receive the Return Created </span> Scenario"
	 * );
	 * 
	 * Document xml = buildXML(System.getProperty("user.dir") + "\\Data\\Test.xml");
	 * 
	 * basetest.test.log(Status.PASS, "Passing the Xml to Sterling API:  " +
	 * "<span style='font-weight:bold;color:blue'>ReturnOrderReciept</span>");
	 * 
	 * AppModule.callApi("ReturnOrderReceipt", Modules.buildStringFromDocument(xml),
	 * true); basetest.test.log(Status.PASS, "Return Order is  Received "); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
	 * //basetest.test.log(Status.FAIL, "Return Order is Not Recevied"); }
	 * 
	 * }
	 */

		protected Document buildXML(String xml_path) throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Path path = Paths.get(xml_path);
			Document create_item = builder.parse(new ByteArrayInputStream(Files.readAllBytes(path)));

			return create_item;
		}
	}
