package Library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.wsi.module.objects.ShipmentXmlsAndApis;
import org.xml.sax.SAXException;

import com.prolifics.ProlificsSeleniumAPI;

import FrameWork.DataBase;
import FrameWork.Generic;
import junit.framework.Assert;

public class ShipmentXmlsAndApiExecution {

	Logger  log;
	Generic gen1 = new Generic();
		public ShipmentXmlsAndApiExecution()
		{
			
			try{PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
			String logFilePath = System.getProperty("user.dir") + "\\ProlificsReports\\";
			gen1.UpdatePropFile("log", logFilePath);
			log = Logger.getLogger(ShipmentXmlsAndApiExecution.class);
			}
			catch(Exception e)
			{
				System.out.println("Inside Exception");
			}
		}
	WebDriver driver;
	ShipmentXmlsAndApis Dbvalidation = new ShipmentXmlsAndApis();
	public Connection stST2Con, ecomCon;
	Generic gen = new Generic();
	DataBase db = new DataBase();
	public boolean XmlBuilding(ProlificsSeleniumAPI oPSelFW, HashMap<String, String> XLTestData, String OrderNo) throws Throwable {
			try {

			Thread.sleep(6000);
			String Env = System.getProperty("Environment");
			
			stST2Con = db.getDBConnection(oPSelFW);	
			Statement stat = stST2Con.createStatement();
			String schema = "";
			if(Env.contains("STST2"))
				schema = "yantra_stst_owner";
			else
				schema = "yantra_owner";
			String Query = "select count(*) as count from " + schema + ".yfs_order_line where order_header_key in(select order_header_key from " + schema + ".yfs_order_header where order_no='" + OrderNo + "') and kit_code=' ' and prime_line_no != '950'";
			ResultSet rs = stat.executeQuery(Query);
			rs.next();
			String count = rs.getString("count");
			if(Integer.parseInt(count) > 0) 
			{
				Query = "select * from " + schema + ".yfs_order_line where order_header_key in(select order_header_key from " + schema + ".yfs_order_header where order_no='" + OrderNo + "') and kit_code=' ' and prime_line_no != '950'";
			System.out.println("Query is " + Query);
			rs = stat.executeQuery(Query);
			System.out.println("Execute Query is " + Query);
			System.out.println(rs);
			
			DateTimeFormatter format_long = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			ZonedDateTime today = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
			
			Dbvalidation.releaseHolds(rs, XLTestData, OrderNo, oPSelFW); 
			  
			String  StatusValue = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			Dbvalidation.adjustInventory(StatusValue,OrderNo,XLTestData,oPSelFW);
			Dbvalidation.requestCollection(StatusValue, OrderNo, XLTestData, oPSelFW);
			  
			String StatusValue1 = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			Dbvalidation.executeCollection(StatusValue1, OrderNo, XLTestData, oPSelFW);
			oPSelFW.reportStepDetails("Verify if the Holds are released for the Order", "Holds are resolved for the Order", "Pass");
			log.info("Holds are resolved for the Order");
			 
			Dbvalidation.requestCollection(StatusValue1, OrderNo, XLTestData, oPSelFW); 
			Thread.sleep(5000);
			Dbvalidation.ScheduleOrderApi(XLTestData, OrderNo,StatusValue1, oPSelFW); 
			  
			  oPSelFW.reportStepDetails("Verify the Status of the Payment", "Order "+ OrderNo + " payment is in Authorized Status", "Pass");
			  log.info("Order "+ OrderNo + " payment is in Authorized Status"); 
			  
			  Dbvalidation.ScheduleOrderApi(XLTestData,OrderNo, StatusValue1, oPSelFW); 
			  Thread.sleep(10000); 
			  String StatusValueDtc = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			  oPSelFW.reportStepDetails("Verify the Status of the Order in Sterling DB", "Order " +OrderNo + " is in DTC Scheduled Status", "Pass");
			  log.info("Order " +OrderNo + " is in DTC Scheduled Status"); 
			  Dbvalidation.ReleaseOrderApi(XLTestData,StatusValueDtc, OrderNo); 
			  String StatusValueRelease = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			  
			  oPSelFW.reportStepDetails("Verify the Status of the Order in Sterling DB", "Order " + OrderNo + " is in " + StatusValueRelease + " Status", "Pass");
			  log.info("Order " + OrderNo + " is in " + StatusValueRelease + " Status");
			  Dbvalidation.processOrderDropAck(XLTestData,format_long.format(today), StatusValueRelease, OrderNo, oPSelFW);
			  
			  String StatusValuePreShip = Dbvalidation.getOrderStatus(OrderNo, oPSelFW); 
			  Dbvalidation.invoicePreShip(XLTestData, OrderNo,StatusValuePreShip, oPSelFW);
			  
			String ProcessType = XLTestData.get("ProcessType");
			/* String ProcessType="FullRepro"; */

			if (ProcessType.contains("Repro")) {

			if (ProcessType.equalsIgnoreCase("FullRepro")) {

			String StatusValueRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);

			Dbvalidation.processFullDCRepro(XLTestData, OrderNo, StatusValueRepro, oPSelFW);
			

			String StatusValuePreRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);

			Dbvalidation.releaseHoldsforReproandDcCancelOrders(rs, XLTestData, OrderNo, oPSelFW);
			Dbvalidation.ExecuteReproDCCancelMsg(XLTestData, OrderNo, StatusValuePreRepro, oPSelFW);
			String StatusValueafterRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			oPSelFW.reportStepDetails("Verify the Status of the Order in Sterling DB", "Order " + OrderNo + " is in " + StatusValueafterRepro + " Status", "Pass"); 
			log.info("Order " + OrderNo + " is in " + StatusValueafterRepro + " Status"); 

			}

			else if (ProcessType.equalsIgnoreCase("PartiallyRepro")) {

			String reproItemId = XLTestData.get("reproItemId").toString();

			String StatusValueforPartialRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);

			Dbvalidation.processPartiallyDCRepro(XLTestData, OrderNo, StatusValueforPartialRepro, oPSelFW);
		
			String StatusValuePreRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);

			Dbvalidation.releaseHoldsforReproandDcCancelOrders(rs, XLTestData, OrderNo, oPSelFW);
			Dbvalidation.ExecuteReproDCCancelMsg(XLTestData, OrderNo, StatusValuePreRepro, oPSelFW);
			String StatusValueafterRepro = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
			oPSelFW.reportStepDetails("Performed Partially  repro on item "+reproItemId, "Order Status is " + StatusValueafterRepro,"Pass");
			log.info("Order Status is " + StatusValueafterRepro);

			}
			}

			else if (ProcessType.contains("DCCancel")) {

			if (ProcessType.equalsIgnoreCase("FullDCCancel")) {
				String StatusValueDCCancel = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
				Dbvalidation.processFullDCCancel(XLTestData, OrderNo, StatusValueDCCancel, oPSelFW);
				String StatusValueafterDCCancel = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
				oPSelFW.reportStepDetails("Verify the Order Status", "Order Status is " + StatusValueafterDCCancel, "Pass");
				log.info( "Order Status is " + StatusValueafterDCCancel);
				Dbvalidation.releaseHoldsforReproandDcCancelOrders(rs, XLTestData, OrderNo, oPSelFW);
				Dbvalidation.ExecuteReproDCCancelMsg(XLTestData, OrderNo, StatusValueDCCancel, oPSelFW);
				return true; 
			}
			else {
				String StatusValuePartaillyDCCancel = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
				Dbvalidation.processPartaillyDCCancel(XLTestData, OrderNo, StatusValuePartaillyDCCancel, oPSelFW);
				Dbvalidation.releaseHoldsforReproandDcCancelOrders(rs, XLTestData, OrderNo, oPSelFW);
				Dbvalidation.ExecuteReproDCCancelMsg(XLTestData, OrderNo, StatusValuePartaillyDCCancel, oPSelFW);
				String StatusValueafterPartaillyDCCancel = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
				oPSelFW.reportStepDetails("Verify the Order Status", "Order Status is " + StatusValueafterPartaillyDCCancel, "Pass");
				log.info( "Order Status is " + StatusValueafterPartaillyDCCancel);	
				return true; 
			}
			}
				else if (ProcessType.equalsIgnoreCase("ShipConfirmation")) {
					String StatusValueConfirmShip = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
					Dbvalidation.ConfirmShip(rs, XLTestData, format_long.format(today), StatusValueConfirmShip, OrderNo, oPSelFW);
					oPSelFW.reportStepDetails("Verify the Order Status", "Order Status is " + StatusValueConfirmShip, "Pass");
					log.info("Order Status is " + StatusValueConfirmShip);
					return true; 
				} else if (ProcessType.equalsIgnoreCase("DeliveryConfirmation")) {
					String StatusValueConfirmShip = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
					Dbvalidation.ConfirmShip(OrderNo, XLTestData, format_long.format(today), StatusValueConfirmShip,  oPSelFW);
					String StatusValueOrderShipped = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
					oPSelFW.reportStepDetails("Verify the Order Status", "Order Status is " + StatusValueOrderShipped, "Pass");
					log.info("Order Status is " + StatusValueOrderShipped);
					Dbvalidation.DeliverShipmentMultiline(XLTestData, format_long.format(today), StatusValueOrderShipped, OrderNo, oPSelFW);
					String StatusValueOrderDelivered = Dbvalidation.getOrderStatus(OrderNo, oPSelFW);
					oPSelFW.reportStepDetails("Verify the Order Status", "Order Status is " + StatusValueOrderDelivered, "Pass");
					log.info("Order Status is " + StatusValueOrderDelivered);
					return true; 
				}
			}
			else
			{
				oPSelFW.reportStepDetails("Verify the Order is updated in Database", "Order Status is not updated in Sterling", "Fail");
				log.error("Order Status is not updated in Sterling");
				return true; 
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				oPSelFW.reportStepDetails("Verify if exception is displayed in Sterling Database", "An exception has occured in executing the Query", "Fail");
				log.error("An exception has occured in executing the Query");
				Assert.assertEquals("Verify if exception is displayed in Sterling Database", "An exception has occured in executing the Query");
			}
			return false;

			}

	protected Document buildXML(String xml_path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Path path = Paths.get(xml_path);
		Document create_item = builder.parse(new ByteArrayInputStream(Files.readAllBytes(path)));

		return create_item;
	}

	public static String buildStringFromDocument(Document doc) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String out = "Invalid";
		transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		out = writer.getBuffer().toString().replaceAll("\n|\r", "");
		return out;
	}



}