package org.wsi.module;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import java.util.Arrays;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsi.module.constants.ApiConstants;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Modules {
	static Logger logger = LoggerFactory.getLogger("org.wsi.module");

	/* Item-Related Modules */
	/**
	 * Call the manageItem api This api will create a new item line with the
	 * specified xml changes
	 * 
	 * @param input
	 *            A triplet with XML elements and attributes to change
	 * @return
	 * @throws Exception
	 */
	



	
	
	public static Document getNewDocument() throws ParserConfigurationException {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();           
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();                                            
		return doc;
	}

	/**
	 * Call the manageItem api This api will delete the item specified by the id
	 * 
	 * @param item_id
	 * @return
	 * @throws Exception
	 */

	// public static String releaseOrder(List<Triplet<String, String, String>>
	// input) throws Exception {
	// ReleaseOrderXMLDocument xml = new ReleaseOrderXMLDocument(input);
	// // Call the api Release Order
	// return AppModule.callApi("releaseOrder", xml.toString(), false);
	// }

	

	public static String releaseOrder(Document doc) throws Exception {
		return tryReleaseOrder(doc);
	}

	private static String tryReleaseOrder(Document doc) throws InterruptedException {
		String apiOut = "";
		int count = 6;
		String apiName = "releaseOrder()";

		while (!AppModule.API_SUCCESS.equals(apiOut) && count > 0) {
			try {
				count--;
				logger.debug("Trying " + apiName + " : Count : " + count);
				apiOut = AppModule.callApi("releaseOrder", buildStringFromDocument(doc), false);
			} catch (Exception e) {
				logger.error("Caught Exception :" + apiName + " Count : " + count);
			}

			if (!AppModule.API_SUCCESS.equals(apiOut))
				Thread.sleep(20000);
		}
		
		return apiOut;
		// Temporary workaround for the releaseOrder API returning error YFC0001 :
		// Record already exists in the database.
		// See Jira-701
	}



	
	/* Create Pack Item */

	/* Create Order */


	

	public static String createOrder(Document doc) throws Exception {
		return AppModule.callApi("OrderCreate", buildStringFromDocument(doc), true);
	}



	public static String createTransferOrder(Document doc) throws Exception {
		return AppModule.callApi("createOrder", buildStringFromDocument(doc));
	}


	public static String getOrderDetails(Document orderDoc, String template, boolean withTemplate) throws Exception {
		return AppModule.callApi("getOrderDetails", buildStringFromDocument(orderDoc), false, withTemplate ? template : null);
	}

	

	public static String getOrderDetails(Document orderDoc, String template) throws Exception {
		return getOrderDetails(orderDoc, template, true);
	}

	

	/* Order List */
	

	public static String getOrderListService(Document doc) throws Exception {
		return AppModule.callApi("GetOrderListService", buildStringFromDocument(doc), true);
	}

	public static String getAttributeValue(Node node, String attribute_name) {
		return ((Element) node).getAttribute(attribute_name);
	}
	public static String getAttributeValue(String xml_data, String tag_name, String attribute_name) throws Exception {
		XMLDocument xml = new XMLDocument();
		Document doc = Modules.buildDocumentFromString(xml_data);
		return xml.getElement(doc, tag_name, attribute_name);
	}


	public static String getElement(Document doc, String tag_name, String attribute_name) throws Exception {
		NodeList elements = doc.getElementsByTagName(tag_name);
		if (elements.getLength() > 0) {
			Node attribute = elements.item(0).getAttributes().getNamedItem(attribute_name);
			if (attribute != null) {
				return attribute.getNodeValue();
			} else {
				throw new Exception(String.format("Attribute: %s does not exist in document: %s", attribute_name,
						buildStringFromDocument(doc)));
			}
		} else {
			throw new Exception(String.format("Element: %s does not exist in document: %s", tag_name,
					buildStringFromDocument(doc)));
		}
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

	public static Document buildDocumentFromString(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		StringReader reader = new StringReader(xml);
		InputSource inputSource = new InputSource(reader);
		Document doc = dBuilder.parse(inputSource);

		return doc;
	}

	public static Document documentHelper(String xmlText) {
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader reader = new StringReader(xmlText);
			InputSource inSource = new InputSource(reader);
			document = builder.parse(inSource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}



	public static String GetOrderReleaseList(Document doc) throws Exception {
		return AppModule.callApi("getOrderReleaseList", buildStringFromDocument(doc), false);
	}



	public static String GetOrderReleaseDetails(Document doc) throws Exception {
		return AppModule.callApi("getOrderReleaseDetails", buildStringFromDocument(doc));
	}

	/* Work Order */



	public static String changeWorkOrderStatus(Document doc) throws Exception {
		return AppModule.callApi("changeWorkOrderStatus", buildStringFromDocument(doc), false);
	}


	public static String modifyWorkOrder(Document doc) throws Exception {
		return AppModule.callApi("ModifyWorkOrder", buildStringFromDocument(doc), true);
	}



	/* Return Order */


	public static String createReturnOrder(Document doc) throws Exception {
		return AppModule.callApi("ReturnOrderCreate", buildStringFromDocument(doc), true);
	}

	public static String createReturnOrder(Document doc, String template) throws Exception {
		return AppModule.callApi("ReturnOrderCreate", buildStringFromDocument(doc), true, template);
	}



	// public static String getShipNodeInventory(List<Triplet<String, String,
	// String>> input) throws Exception {
	// GetShipNodeInventoryXMLDocument xml = new
	// GetShipNodeInventoryXMLDocument(input);
	//
	// return AppModule.callApi("getShipNodeInventory", xml.toString());
	// }

	
	public static String executeProofOfDelivery(Document doc) throws Exception {
		return AppModule.callApi("ExecutePOD", buildStringFromDocument(doc), true);
	}

	/* Get Attributes */
	

	public static String getAttributeValueByXPath(String xml_data, String path) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		InputSource inputSource = new InputSource(new StringReader(xml_data));
		return xpath.evaluate(path, inputSource);
	}
	
	

	

	public static String processOrderDropAck(Document doc) throws Exception {
		return AppModule.callApi("ProcessOrderDropAck", buildStringFromDocument(doc), true);
	}



	public static String wismoProcessContainerActivity(Document doc) throws Exception {
		return AppModule.callApi("WISMOProcessContainerActivity", buildStringFromDocument(doc), true);
	}

	

	public static String wismoOrderAndShipmentDetails(Document doc) throws Exception {
		return AppModule.callApi("WISMOOrderAndShipmentDetailsForCOV2_1", buildStringFromDocument(doc), true);
	}

	

	public static String processOrderPayments(Document doc) throws Exception {
		return AppModule.callApi("processOrderPayments", buildStringFromDocument(doc), false);
	}

	

	public static String scheduleOrder(Document doc) throws Exception {
		return AppModule.callApi("scheduleOrder", buildStringFromDocument(doc));
	}
	
	public static String unscheduleOrder(Document doc) throws Exception {
		return AppModule.callApi("unScheduleOrder", buildStringFromDocument(doc), false);
	}

	

	

	

	public static String changeOrder(Document doc) throws Exception {
		return AppModule.callApi("ChangeOrder", buildStringFromDocument(doc), true);
	}


	

	
	
	public static String processNSCancelOrder(Document doc) throws Exception {
		return AppModule.callApi("ProcessNSCancelOrder", buildStringFromDocument(doc), true);
	}

	

	



	// Create Order in Sterling from External Order in DC


	// Quick Search Order/ External Order
	// QuickSearch(CCUI api) --> CC_MasterSearch (Sterling service)
	// doOrderSearch (CCUI api) --> CC_MasterSearch (Sterling service)


	

	public static String getShipmentList(Document inputXML) throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("Shipments");
		root.setAttribute("TotalNumberofRecords", "");
		doc.appendChild(root);

		Element shipment = doc.createElement("Shipment");
		root.appendChild(shipment);
		shipment.setAttribute("Createts", "");
		shipment.setAttribute("RequestedDeliveryDate", "");
		shipment.setAttribute("ExpectedDeliveryDate", "");
		shipment.setAttribute("ActualDeliveryDate", "");
		shipment.setAttribute("ShipmentCharge", "");
		shipment.setAttribute("ShipmentKey", "");
		shipment.setAttribute("Status", "");

		Element shipmentLines = doc.createElement("ShipmentLines");
		shipment.appendChild(shipmentLines);

		Element shipmentLine = doc.createElement("ShipmentLine");
		shipmentLines.appendChild(shipmentLine);

		shipmentLine.setAttribute("ItemDesc", "");
		shipmentLine.setAttribute("ItemID", "");
		shipmentLine.setAttribute("OrderNo", "");
		shipmentLine.setAttribute("OriginalQuantity", "");
		shipmentLine.setAttribute("Quantity", "");
		shipmentLine.setAttribute("ShipmentLineKey", "");

		Element s_extn = doc.createElement("Extn");
		shipmentLine.appendChild(s_extn);
		// s_extn.setAttribute("ExtnASNEtaDate", "");

		Element orderLine = doc.createElement("OrderLine");
		shipmentLine.appendChild(orderLine);

		orderLine.setAttribute("GiftWrap", "");
		orderLine.setAttribute("OrderDate", "");
		orderLine.setAttribute("OrderHeaderKey", "");
		orderLine.setAttribute("OrderLineKey", "");
		orderLine.setAttribute("OrderedQty", "");
		orderLine.setAttribute("OriginalOrderedQty", "");
		orderLine.setAttribute("Status", "");

		Element orderDates = doc.createElement("OrderDates");
		orderLine.appendChild(orderDates);

		Element orderDate = doc.createElement("OrderDate");
		orderDates.appendChild(orderDate);

		orderDate.setAttribute("ActualDate", "");
		orderDate.setAttribute("CommittedDate", "");
		orderDate.setAttribute("DateTypeID", "");
		orderDate.setAttribute("ExpectedDate", "");
		orderDate.setAttribute("OrderHeaderKey", "");
		orderDate.setAttribute("OrderReleaseKey", "");
		orderDate.setAttribute("RequestedDate", "");

		Element extn = doc.createElement("Extn");
		shipment.appendChild(extn);

		Element shipNode = doc.createElement("ShipNode");
		shipment.appendChild(shipNode);

		shipNode.setAttribute("Description", "");
		shipNode.setAttribute("Latitude", "");
		shipNode.setAttribute("Localecode", "");
		shipNode.setAttribute("ShipNode", "");

		Element shipNodePersonInfo = doc.createElement("ShipNodePersonInfo");
		shipNode.appendChild(shipNodePersonInfo);

		shipNodePersonInfo.setAttribute("AdressLine1", "");
		shipNodePersonInfo.setAttribute("AdressLine2", "");
		shipNodePersonInfo.setAttribute("AdressLine3", "");
		shipNodePersonInfo.setAttribute("AddressLine4", "");
		shipNodePersonInfo.setAttribute("AdressLine5", "");
		shipNodePersonInfo.setAttribute("AddressLine6", "");
		shipNodePersonInfo.setAttribute("City", "");
		shipNodePersonInfo.setAttribute("Company", "");
		shipNodePersonInfo.setAttribute("Country", "");
		shipNodePersonInfo.setAttribute("DayFaxNo", "");
		shipNodePersonInfo.setAttribute("DayPhone", "");
		shipNodePersonInfo.setAttribute("FirstName", "");
		shipNodePersonInfo.setAttribute("LastName", "");
		shipNodePersonInfo.setAttribute("MiddleName", "");
		shipNodePersonInfo.setAttribute("MobilePhone", "");
		shipNodePersonInfo.setAttribute("OtherPhone", "");
		shipNodePersonInfo.setAttribute("PersonID", "");
		shipNodePersonInfo.setAttribute("State", "");
		shipNodePersonInfo.setAttribute("TaxGeoCode", "");
		shipNodePersonInfo.setAttribute("ZipCode", "");

		Element toAddress = doc.createElement("ToAddress");
		shipment.appendChild(toAddress);

		toAddress.setAttribute("DayPhone", "");
		toAddress.setAttribute("FirstName", "");
		toAddress.setAttribute("LastName", "");
		toAddress.setAttribute("MiddleName", "");
		toAddress.setAttribute("MobilePhone", "");

		Element billToAddress = doc.createElement("BillToAddress");
		shipment.appendChild(billToAddress);

		billToAddress.setAttribute("DayPhone", "");
		billToAddress.setAttribute("FirstName", "");
		billToAddress.setAttribute("LastName", "");
		billToAddress.setAttribute("MiddleName", "");
		billToAddress.setAttribute("MobilePhone", "");

		return AppModule.callApi("getShipmentList", buildStringFromDocument(inputXML), false,
				buildStringFromDocument(doc));
	}

	
	public static String createShipmentInvoice(Document doc) throws Exception {
		return AppModule.callApi("createShipmentInvoice", buildStringFromDocument(doc), false);
	}


	public static String getChargeTransactionList(Document doc) throws Exception {
		return AppModule.callApi("getChargeTransactionList", buildStringFromDocument(doc), false);
	}

	public static String getOrderInvoiceList(Document doc) throws Exception {
		return AppModule.callApi("getOrderInvoiceList", buildStringFromDocument(doc), false);
	}

	public static String recordInvoiceCreation(Document doc) throws Exception {
		return AppModule.callApi("recordInvoiceCreation", Modules.buildStringFromDocument(doc), false);
	}

	public static String OrderPricing(String input) throws Exception {
		String apiOut = "";
		int i = 1;

		// Retry logic added to attempt call 3 times before throwing the exception for
		// OMSQA-436
		// OMSQA-621 (Updated the Retry logic to 3 times instead of 10)
		while (true) {
			try {
				// When it is a successful call, break out of the loop
				apiOut = OrderPricing(input, null);
				break;
			} catch (Exception e) {
				// After the third attempt, throw the exception
				logger.debug("Order Pricing attempt [{}] failed", i);
				if (i++ < 4) {
					continue;
				}

				throw e;
			}
		}

		return apiOut;
	}

	public static String OrderPricing(String input, String xmlTemplate) throws Exception {
		return AppModule.callApi("OrderPricing", input, true, xmlTemplate);
	}

	public static String getCalendarDetails(Document doc) throws Exception {
		return AppModule.callApi("getCalendarDetails", buildStringFromDocument(doc));
	}

	public static String createCalendar(Document doc) throws Exception {
		return AppModule.callApi("createCalendar", buildStringFromDocument(doc));
	}

	public static String deleteCalendar(Document doc) throws Exception {
		return AppModule.callApi("deleteCalendar", buildStringFromDocument(doc));
	}

	public static String executeReproDCCancelMsg(Document doc) throws Exception {
		return AppModule.callApi("ExecuteReproDCCancelMsg", Modules.buildStringFromDocument(doc), true);
	}

	public static String getShipmentCountNoContainerForDashboard(Document doc) throws Exception {
		return AppModule.callApi("GetShipmentCountNoContainerForDashboard", Modules.buildStringFromDocument(doc), true);
	}

	public static String dropShipPOShipmentSearch2(Document doc) throws Exception {
		return AppModule.callApi("DropShipPOShipmentSearch2", Modules.buildStringFromDocument(doc), true);
	}

	public static String createWSIRoutingGuideDetail(Document doc) throws Exception {
		return AppModule.callApi("CreateWSIRoutingGuideDetail", Modules.buildStringFromDocument(doc), true);
	}

	public static String getOrderInvoiceDetailsWithTemplate(Document docXML) throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("InvoiceDetail");
		root.setAttribute("Action", "");
		doc.appendChild(root);

		Element invoiceHeader = doc.createElement("InvoiceHeader");
		invoiceHeader.setAttribute("AmountCollected", "");
		invoiceHeader.setAttribute("DateInvoiced", "");
		invoiceHeader.setAttribute("InvoiceNo", "");
		invoiceHeader.setAttribute("InvoiceType", "");
		invoiceHeader.setAttribute("TotalDiscount", "");
		invoiceHeader.setAttribute("TotalAmount", "");
		invoiceHeader.setAttribute("OrderInvoiceKey", "");
		root.appendChild(invoiceHeader);

		Element order = doc.createElement("Order");
		order.setAttribute("ShipNode", "");
		order.setAttribute("Status", "");
		order.setAttribute("MinOrderStatus", "");
		order.setAttribute("SellerOrganizationCode", "");
		order.setAttribute("EntryType", "");
		order.setAttribute("DocumentType", "");
		order.setAttribute("MaxOrderStatus", "");
		order.setAttribute("Action", "");
		order.setAttribute("Createts", "");
		order.setAttribute("Division", "");
		order.setAttribute("EnteredBy", "");
		order.setAttribute("EnterpriseCode", "");
		order.setAttribute("Modifyts", "");
		order.setAttribute("OrderDate", "");
		order.setAttribute("OrderNo", "");
		order.setAttribute("OrderType", "");
		order.setAttribute("ReceivingNode", "");
		order.setAttribute("TaxExemptionCertificate", "");
		order.setAttribute("OrderHeaderKey", "");
		order.setAttribute("CustomerRewardsNo", "");
		order.setAttribute("ReturnByGiftRecipient", "");
		order.setAttribute("Createuserid", "");
		invoiceHeader.appendChild(order);

		Element paymentMethods = doc.createElement("PaymentMethods");
		order.appendChild(paymentMethods);

		Element paymentMethod = doc.createElement("PaymentMethod");
		paymentMethod.setAttribute("PaymentReference6", "");
		paymentMethod.setAttribute("CheckNo", "");
		paymentMethod.setAttribute("CreditCardNo", "");
		paymentMethod.setAttribute("CreditCardType", "");
		paymentMethod.setAttribute("PaymentType", "");
		paymentMethod.setAttribute("RequestedAuthAmount", "");
		paymentMethod.setAttribute("RequestedChargeAmount", "");
		paymentMethod.setAttribute("RequestedRefundAmount", "");
		paymentMethod.setAttribute("SvcNo", "");
		paymentMethod.setAttribute("TotalAltRefundedAmount", "");
		paymentMethod.setAttribute("TotalAuthorized", "");
		paymentMethod.setAttribute("TotalCharged", "");
		paymentMethod.setAttribute("TotalRefundedAmount", "");
		paymentMethods.appendChild(paymentMethod);

		Element billTo = doc.createElement("PersonInfoBillTo");
		billTo.setAttribute("AddressLine1", "");
		billTo.setAttribute("AddressLine2", "");
		billTo.setAttribute("AddressLine3", "");
		billTo.setAttribute("AddressLine4", "");
		billTo.setAttribute("AddressLine5", "");
		billTo.setAttribute("AddressLine6", "");
		billTo.setAttribute("AlternateEmailID", "");
		billTo.setAttribute("Beeper", "");
		billTo.setAttribute("City", "");
		billTo.setAttribute("Company", "");
		billTo.setAttribute("Country", "");
		billTo.setAttribute("DayFaxNo", "");
		billTo.setAttribute("DayPhone", "");
		billTo.setAttribute("Department", "");
		billTo.setAttribute("EMailID", "");
		billTo.setAttribute("EveningFaxNo", "");
		billTo.setAttribute("EveningPhone", "");
		billTo.setAttribute("FirstName", "");
		billTo.setAttribute("IsAddressVerified", "");
		billTo.setAttribute("JobTitle", "");
		billTo.setAttribute("LastName", "");
		billTo.setAttribute("MiddleName", "");
		billTo.setAttribute("MobilePhone", "");
		billTo.setAttribute("OtherPhone", "");
		billTo.setAttribute("PersonID", "");
		billTo.setAttribute("State", "");
		billTo.setAttribute("Suffix", "");
		billTo.setAttribute("Title", "");
		billTo.setAttribute("ZipCode", "");
		order.appendChild(billTo);

		Element billTo_Extn = doc.createElement("Extn");
		billTo_Extn.setAttribute("ExtnIsAddressOverridden", "");
		billTo_Extn.setAttribute("ExtnAddressType", "");
		billTo.appendChild(billTo_Extn);

		Element shipTo = doc.createElement("PersonInfoShipTo");
		shipTo.setAttribute("AddressLine1", "");
		shipTo.setAttribute("AddressLine2", "");
		shipTo.setAttribute("AddressLine3", "");
		shipTo.setAttribute("AddressLine4", "");
		shipTo.setAttribute("AddressLine5", "");
		shipTo.setAttribute("AddressLine6", "");
		shipTo.setAttribute("AlternateEmailID", "");
		shipTo.setAttribute("Beeper", "");
		shipTo.setAttribute("City", "");
		shipTo.setAttribute("Company", "");
		shipTo.setAttribute("Country", "");
		shipTo.setAttribute("DayFaxNo", "");
		shipTo.setAttribute("DayPhone", "");
		shipTo.setAttribute("Department", "");
		shipTo.setAttribute("EMailID", "");
		shipTo.setAttribute("EveningFaxNo", "");
		shipTo.setAttribute("EveningPhone", "");
		shipTo.setAttribute("FirstName", "");
		shipTo.setAttribute("IsAddressVerified", "");
		shipTo.setAttribute("JobTitle", "");
		shipTo.setAttribute("LastName", "");
		shipTo.setAttribute("MiddleName", "");
		shipTo.setAttribute("MobilePhone", "");
		shipTo.setAttribute("OtherPhone", "");
		shipTo.setAttribute("PersonID", "");
		shipTo.setAttribute("State", "");
		shipTo.setAttribute("Suffix", "");
		shipTo.setAttribute("Title", "");
		shipTo.setAttribute("ZipCode", "");
		order.appendChild(shipTo);

		Element extn_Order = doc.createElement("Extn");
		extn_Order.setAttribute("Extn51OrderFlag", "");
		extn_Order.setAttribute("ExtnCustomerAccountNo", "");
		extn_Order.setAttribute("ExtnCustomerType", "");
		extn_Order.setAttribute("ExtnOriginalOrderNo", "");
		extn_Order.setAttribute("ExtnStoreNumber", "");
		extn_Order.setAttribute("ExtnRobustOrderIndicator", "");
		extn_Order.setAttribute("ExtnFirstName", "");
		extn_Order.setAttribute("ExtnLastName", "");
		extn_Order.setAttribute("ExtnSourceCode", "");
		order.appendChild(extn_Order);

		Element wsiAddnlOrderDataList = doc.createElement("WSIAddnlOrderDataList");
		extn_Order.appendChild(wsiAddnlOrderDataList);

		Element wsiAddnlOrderData = doc.createElement("WSIAddnlOrderData");
		wsiAddnlOrderData.setAttribute("ExtnCatalog", "");
		wsiAddnlOrderData.setAttribute("ExtnIPAddress", "");
		wsiAddnlOrderData.setAttribute("ExtnRegistryID", "");
		wsiAddnlOrderData.setAttribute("ExtnEmployeeID", "");
		wsiAddnlOrderData.setAttribute("ExtnDiscountGroupCodeAssc", "");
		wsiAddnlOrderData.setAttribute("ExtnLoyaltyId", "");
		wsiAddnlOrderDataList.appendChild(wsiAddnlOrderData);

		Element promotions = doc.createElement("Promotions");
		order.appendChild(promotions);

		Element promotion = doc.createElement("Promotion");
		promotion.setAttribute("Action", "");
		promotion.setAttribute("DenialReason", "");
		promotion.setAttribute("PromotionApplied", "");
		promotion.setAttribute("PromotionId", "");
		promotion.setAttribute("PromotionType", "");
		promotions.appendChild(promotion);

		Element promotion_Extn = doc.createElement("Extn");
		promotion_Extn.setAttribute("ExtnPromotionCancelFlag", "");
		promotion_Extn.setAttribute("ExtnPromotionCodeStatus", "");
		promotion_Extn.setAttribute("ExtnPromotionDescription", "");
		promotion_Extn.setAttribute("ExtnPromotionEffectiveCode", "");
		promotion_Extn.setAttribute("ExtnPromotionEligibility", "");
		promotion_Extn.setAttribute("ExtnPromotionLegal", "");
		promotion_Extn.setAttribute("ExtnPromotionReservation", "");
		promotion_Extn.setAttribute("ExtnPromotionRestriction", "");
		promotion_Extn.setAttribute("ExtnPromotionSeqNo", "");
		promotion_Extn.setAttribute("ExtnPromotionSpec", "");
		promotion.appendChild(promotion_Extn);

		Element orderAudit = doc.createElement("OrderAudit");
		orderAudit.setAttribute("Modifyuserid", "");
		orderAudit.setAttribute("ReasonCode", "");
		orderAudit.setAttribute("ReasonText", "");
		order.appendChild(orderAudit);

		Element overallTotals = doc.createElement("OverallTotals");
		overallTotals.setAttribute("GrandDiscount", "");
		order.appendChild(overallTotals);

		Element orderStatuses = doc.createElement("OrderStatuses");
		order.appendChild(orderStatuses);

		Element orderStatus = doc.createElement("OrderStatus");
		orderStatus.setAttribute("Status", "");
		orderStatus.setAttribute("ShipNode", "");
		orderStatus.setAttribute("ReceivingNode", "");
		orderStatus.setAttribute("OrderHeaderKey", "");
		orderStatus.setAttribute("StatusReason", "");
		orderStatus.setAttribute("OrderLineKey", "");
		orderStatus.setAttribute("PipelineKey", "");
		orderStatus.setAttribute("OrderReleaseKey", "");
		orderStatus.setAttribute("StatusDescription", "");
		orderStatus.setAttribute("OrderLineScheduleKey", "");
		orderStatus.setAttribute("TotalQuantity", "");
		orderStatus.setAttribute("StatusQty", "");
		orderStatus.setAttribute("StatusDate", "");
		orderStatus.setAttribute("OrderReleaseStatusKey", "");
		orderStatuses.appendChild(orderStatus);

		Element orderDates = doc.createElement("OrderDates");
		order.appendChild(orderDates);

		Element orderDate = doc.createElement("OrderDate");
		orderDate.setAttribute("RequestedDate", "");
		orderDate.setAttribute("ExpectedDate", "");
		orderDate.setAttribute("DateTypeId", "");
		orderDate.setAttribute("ActualDate", "");
		orderDates.appendChild(orderDate);

		Element orderLines = doc.createElement("OrderLines");
		order.appendChild(orderLines);

		Element orderLine = doc.createElement("OrderLine");
		orderLine.setAttribute("GiftFlag", "");
		orderLine.setAttribute("LineType", "");
		orderLine.setAttribute("OriginalOrderedQty", "");
		orderLine.setAttribute("PrimeLineNo", "");
		orderLine.setAttribute("Quantity", "");
		orderLine.setAttribute("ReturnReason", "");
		orderLine.setAttribute("SerialNo", "");
		orderLine.setAttribute("SubLineNo", "");
		orderLine.setAttribute("ItemGroupCode", "");
		orderLine.setAttribute("ChangeInOrderedQty", "");
		orderLine.setAttribute("ReturnReasonShortDesc", "");
		orderLine.setAttribute("ReturnReasonLongDesc", "");
		orderLine.setAttribute("CustomerLinePONo", "");
		orderLine.setAttribute("CustomerPONo", "");
		orderLine.setAttribute("OrderLineKey", "");
		orderLine.setAttribute("BundleParentOrderLineKey", "");
		orderLines.appendChild(orderLine);

		Element item = doc.createElement("Item");
		item.setAttribute("ItemDesc", "");
		item.setAttribute("ItemID", "");
		item.setAttribute("ItemShortDesc", "");
		item.setAttribute("ProductClass", "");
		orderLine.appendChild(item);

		Element itemDetails = doc.createElement("ItemDetails");
		itemDetails.setAttribute("ItemID", "");
		itemDetails.setAttribute("OrganizationCode", "");
		orderLine.appendChild(itemDetails);

		Element primaryInfo = doc.createElement("PrimaryInformation");
		primaryInfo.setAttribute("ColorCode", "");
		primaryInfo.setAttribute("DefaultProductClass", "");
		primaryInfo.setAttribute("SizeCode", "");
		itemDetails.appendChild(primaryInfo);

		Element itemExtn = doc.createElement("Extn");
		itemExtn.setAttribute("ExtnClass", "");
		itemExtn.setAttribute("ExtnDept", "");
		itemDetails.appendChild(itemExtn);

		Element lineOverallTotals = doc.createElement("LineOverallTotals");
		lineOverallTotals.setAttribute("Discount", "");
		lineOverallTotals.setAttribute("ExtendedPrice", "");
		lineOverallTotals.setAttribute("LineTotal", "");
		lineOverallTotals.setAttribute("Tax", "");
		orderLine.appendChild(lineOverallTotals);

		Element orderLine_ShipTo = doc.createElement("PersonInfoShipTo");
		orderLine_ShipTo.setAttribute("AddressLine1", "");
		orderLine_ShipTo.setAttribute("AddressLine2", "");
		orderLine_ShipTo.setAttribute("City", "");
		orderLine_ShipTo.setAttribute("Country", "");
		orderLine_ShipTo.setAttribute("DayPhone", "");
		orderLine_ShipTo.setAttribute("EMailID", "");
		orderLine_ShipTo.setAttribute("EveningPhone", "");
		orderLine_ShipTo.setAttribute("FirstName", "");
		orderLine_ShipTo.setAttribute("IsAddressVerified", "");
		orderLine_ShipTo.setAttribute("LastName", "");
		orderLine_ShipTo.setAttribute("MiddleName", "");
		orderLine_ShipTo.setAttribute("MobilePhone", "");
		orderLine_ShipTo.setAttribute("State", "");
		orderLine_ShipTo.setAttribute("ZipCode", "");
		orderLine_ShipTo.setAttribute("Title", "");
		orderLine_ShipTo.setAttribute("Suffix", "");
		orderLine.appendChild(orderLine_ShipTo);

		Element orderLine_ShipTo_Extn = doc.createElement("Extn");
		orderLine_ShipTo_Extn.setAttribute("ExtnIsAddressOverridden", "");
		orderLine_ShipTo_Extn.setAttribute("ExtnAddressType", "");
		orderLine_ShipTo.appendChild(orderLine_ShipTo_Extn);

		Element orderLine_Extn = doc.createElement("Extn");
		orderLine_Extn.setAttribute("ExtnGroupID", "");
		orderLine_Extn.setAttribute("ExtnExternalRegistryID", "");
		orderLine_Extn.setAttribute("ExtnRegistryType", "");
		orderLine_Extn.setAttribute("ExtnInternalRegistryID", "");
		orderLine_Extn.setAttribute("ExtnIsAddressProvidedByRegistrant", "");
		orderLine_Extn.setAttribute("ExtnGiftRegistryID", "");
		orderLine_Extn.setAttribute("ExtnCustLOS", "");
		orderLine_Extn.setAttribute("ExtnDTCSubOrderNo", "");
		orderLine_Extn.setAttribute("ExtnMediaCode", "");
		orderLine.appendChild(orderLine_Extn);

		Element orderLine_wsiVasDataList = doc.createElement("WSIVASDataList");
		orderLine_Extn.appendChild(orderLine_wsiVasDataList);

		Element orderLine_wsiVasData = doc.createElement("WSIVASData");
		orderLine_wsiVasData.setAttribute("ExtnVASGiftWrap", "");
		orderLine_wsiVasData.setAttribute("ExtnVASMono", "");
		orderLine_wsiVasData.setAttribute("ExtnVASPZ", "");
		orderLine_wsiVasData.setAttribute("ExtnVASRecipe", "");
		orderLine_wsiVasData.setAttribute("ExtnVASCareCard", "");
		orderLine_wsiVasData.setAttribute("ExtnVASGiftWrapCode", "");
		orderLine_wsiVasDataList.appendChild(orderLine_wsiVasData);

		Element orderLine_wsiAddnlOrderLineDataList = doc.createElement("WSIAddnlOrderLineDataList");
		orderLine_Extn.appendChild(orderLine_wsiAddnlOrderLineDataList);

		Element orderLine_wsiAddnlOrderLineData = doc.createElement("WSIAddnlOrderLineData");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnActionCode", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnIsRushShipping", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnIsFreeShipping", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnIsFreightTaxable", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnIsStateTaxable", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnFreeMONOPZFlag", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnPriceType", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnIsDCPickup", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnSVCFrom", "");
		orderLine_wsiAddnlOrderLineData.setAttribute("ExtnSVCTo", "");
		orderLine_wsiAddnlOrderLineDataList.appendChild(orderLine_wsiAddnlOrderLineData);

		Element orderLine_wsiPZDataList = doc.createElement("WSIPZDataList");
		orderLine_Extn.appendChild(orderLine_wsiPZDataList);

		Element orderLine_wsiPZData = doc.createElement("WSIPZData");
		orderLine_wsiPZData.setAttribute("ExtnPZType", "");
		orderLine_wsiPZData.setAttribute("ExtnPZSequence", "");
		orderLine_wsiPZDataList.appendChild(orderLine_wsiPZData);

		Element orderLine_wsiPZDDataDetailsList = doc.createElement("WSIPZDDataDetailsList");
		orderLine_wsiPZData.appendChild(orderLine_wsiPZDDataDetailsList);

		Element orderLine_wsiPZDDataDetails = doc.createElement("WSIPZDDataDetails");
		orderLine_wsiPZDDataDetails.setAttribute("ExtnPZType", "");
		orderLine_wsiPZDDataDetails.setAttribute("ExtnPZTypeDescription", "");
		orderLine_wsiPZDDataDetails.setAttribute("ExtnPZTypeValue", "");
		orderLine_wsiPZDDataDetails.setAttribute("ExtnPZTypeKey", "");
		orderLine_wsiPZDDataDetailsList.appendChild(orderLine_wsiPZDDataDetails);

		Element orderLine_Instructions = doc.createElement("Instructions");
		orderLine_Instructions.setAttribute("NumberOfInstructions", "");
		orderLine.appendChild(orderLine_Instructions);

		Element orderLine_Instruction = doc.createElement("Instruction");
		orderLine_Instruction.setAttribute("InstructionText", "");
		orderLine_Instruction.setAttribute("InstructionType", "");
		orderLine_Instructions.appendChild(orderLine_Instruction);

		Element orderLine_Awards = doc.createElement("Awards");
		orderLine.appendChild(orderLine_Awards);

		Element orderLine_Award = doc.createElement("Award");
		orderLine_Award.setAttribute("AwardId", "");
		orderLine_Award.setAttribute("PromotionId", "");
		orderLine_Award.setAttribute("AwardAmount", "");
		orderLine_Award.setAttribute("Action", "");
		orderLine_Award.setAttribute("Description", "");
		orderLine_Award.setAttribute("AwardApplied", "");
		orderLine_Awards.appendChild(orderLine_Award);

		Element orderLine_Award_Extn = doc.createElement("Extn");
		orderLine_Award_Extn.setAttribute("ExtnGiftWrapDisc", "");
		orderLine_Award_Extn.setAttribute("ExtnSurchargeDisc", "");
		orderLine_Award_Extn.setAttribute("ExtnShippingDisc", "");
		orderLine_Award_Extn.setAttribute("ExtnPersDisc", "");
		orderLine_Award_Extn.setAttribute("ExtnMerchDisc", "");
		orderLine_Award_Extn.setAttribute("ExtnShipGroupCode", "");
		orderLine_Award.appendChild(orderLine_Award_Extn);

		Element orderLine_LineCharges = doc.createElement("LineCharges");
		orderLine.appendChild(orderLine_LineCharges);

		Element orderLine_LineCharge = doc.createElement("LineCharge");
		orderLine_LineCharge.setAttribute("ChargeAmount", "");
		orderLine_LineCharge.setAttribute("ChargeCategory", "");
		orderLine_LineCharge.setAttribute("ChargeName", "");
		orderLine_LineCharge.setAttribute("IsDiscount", "");
		orderLine_LineCharges.appendChild(orderLine_LineCharge);

		Element orderLine_OrderStatuses = doc.createElement("OrderStatuses");
		orderLine.appendChild(orderLine_OrderStatuses);

		Element orderLine_OrderStatus = doc.createElement("OrderStatus");
		orderLine_OrderStatus.setAttribute("Status", "");
		orderLine_OrderStatus.setAttribute("ShipNode", "");
		orderLine_OrderStatus.setAttribute("ReceivingNode", "");
		orderLine_OrderStatus.setAttribute("OrderHeaderKey", "");
		orderLine_OrderStatus.setAttribute("StatusReason", "");
		orderLine_OrderStatus.setAttribute("OrderLineKey", "");
		orderLine_OrderStatus.setAttribute("PipelineKey", "");
		orderLine_OrderStatus.setAttribute("OrderReleaseKey", "");
		orderLine_OrderStatus.setAttribute("StatusDescription", "");
		orderLine_OrderStatus.setAttribute("OrderLineScheduleKey", "");
		orderLine_OrderStatus.setAttribute("TotalQuantity", "");
		orderLine_OrderStatus.setAttribute("StatusQty", "");
		orderLine_OrderStatus.setAttribute("StatusDate", "");
		orderLine_OrderStatus.setAttribute("OrderReleaseStatusKey", "");
		orderLine_OrderStatuses.appendChild(orderLine_OrderStatus);

		Element orderLine_OrderDates = doc.createElement("OrderDates");
		orderLine.appendChild(orderLine_OrderDates);

		Element orderLine_OrderDate = doc.createElement("OrderDate");
		orderLine_OrderDate.setAttribute("RequestedDate", "");
		orderLine_OrderDate.setAttribute("ExpectedDate", "");
		orderLine_OrderDate.setAttribute("DateTypeId", "");
		orderLine_OrderDate.setAttribute("ActualDate", "");
		orderLine_OrderDates.appendChild(orderLine_OrderDate);

		Element lineDetails = doc.createElement("LineDetails");
		invoiceHeader.appendChild(lineDetails);

		Element lineDetail = doc.createElement("LineDetail");
		lineDetail.setAttribute("Charges", "");
		lineDetail.setAttribute("ExtendedPrice", "");
		lineDetail.setAttribute("LineTotal", "");
		lineDetail.setAttribute("PrimeLineNo", "");
		lineDetail.setAttribute("Quantity", "");
		lineDetail.setAttribute("Tax", "");
		lineDetail.setAttribute("UnitPrice", "");
		lineDetail.setAttribute("OrderLineKey", "");
		lineDetails.appendChild(lineDetail);

		Element lineDetail_OrderLine = doc.createElement("OrderLine");
		lineDetail_OrderLine.setAttribute("GiftFlag", "");
		lineDetail_OrderLine.setAttribute("LineType", "");
		lineDetail_OrderLine.setAttribute("OrderedQty", "");
		lineDetail_OrderLine.setAttribute("OriginalOrderedQty", "");
		lineDetail_OrderLine.setAttribute("PrimeLineNo", "");
		lineDetail_OrderLine.setAttribute("ReturnReason", "");
		lineDetail_OrderLine.setAttribute("SerialNo", "");
		lineDetail_OrderLine.setAttribute("SubLineNo", "");
		lineDetail_OrderLine.setAttribute("ReasonCode", "");
		lineDetail_OrderLine.setAttribute("BundleParentOrderLineKey", "");
		lineDetail_OrderLine.setAttribute("OrderLineKey", "");
		lineDetail_OrderLine.setAttribute("ItemGroupCode", "");
		lineDetail.appendChild(lineDetail_OrderLine);

		Element line_BundleParentLine = doc.createElement("BundleParentLine");
		line_BundleParentLine.setAttribute("OrderLineKey", "");
		line_BundleParentLine.setAttribute("PrimeLineNo", "");
		line_BundleParentLine.setAttribute("SubLineNo", "");
		lineDetail_OrderLine.appendChild(line_BundleParentLine);

		Element line_Instructions = doc.createElement("Instructions");
		line_Instructions.setAttribute("NumberOfInstructions", "");
		lineDetail_OrderLine.appendChild(line_Instructions);

		Element line_Instruction = doc.createElement("Instuction");
		line_Instruction.setAttribute("InstructionText", "");
		line_Instruction.setAttribute("InstructionType", "");
		line_Instruction.setAttribute("InstructionURL", "");
		line_Instruction.setAttribute("SequenceNo", "");
		line_Instructions.appendChild(line_Instruction);

		Element line_Item = doc.createElement("Item");
		line_Item.setAttribute("ItemDescription", "");
		line_Item.setAttribute("ItemID", "");
		line_Item.setAttribute("ItemShortDesc", "");
		line_Item.setAttribute("ProductClass", "");
		line_Item.setAttribute("UnitOfMeasure", "");
		lineDetail_OrderLine.appendChild(line_Item);

		Element line_ItemDetails = doc.createElement("ItemDetails");
		line_ItemDetails.setAttribute("ItemID", "");
		lineDetail_OrderLine.appendChild(line_ItemDetails);

		Element line_PrimaryInfo = doc.createElement("PrimaryInformation");
		line_PrimaryInfo.setAttribute("ColorCode", "");
		line_PrimaryInfo.setAttribute("DefaultProductClass", "");
		line_PrimaryInfo.setAttribute("SizeCode", "");
		line_ItemDetails.appendChild(line_PrimaryInfo);

		Element line_ItemExtn = doc.createElement("Extn");
		line_ItemExtn.setAttribute("ExtnClass", "");
		line_ItemExtn.setAttribute("ExtnDept", "");
		line_ItemDetails.appendChild(line_ItemExtn);

		Element linePriceInfo = doc.createElement("LinePriceInfo");
		linePriceInfo.setAttribute("UnitPrice", "");
		linePriceInfo.setAttribute("ListPrice", "");
		linePriceInfo.setAttribute("RetailPrice", "");
		lineDetail_OrderLine.appendChild(linePriceInfo);

		Element line_lineOverallTotals = doc.createElement("LineOverallTotals");
		line_lineOverallTotals.setAttribute("Charges", "");
		line_lineOverallTotals.setAttribute("Discount", "");
		line_lineOverallTotals.setAttribute("ExtendedPrice", "");
		line_lineOverallTotals.setAttribute("LineCost", "");
		line_lineOverallTotals.setAttribute("LineTotal", "");
		line_lineOverallTotals.setAttribute("LineTotalWithoutTax", "");
		line_lineOverallTotals.setAttribute("ShippingBaseCharge", "");
		line_lineOverallTotals.setAttribute("ShippingCharges", "");
		line_lineOverallTotals.setAttribute("ShippingDiscount", "");
		line_lineOverallTotals.setAttribute("ShippingTotal", "");
		line_lineOverallTotals.setAttribute("Tax", "");
		line_lineOverallTotals.setAttribute("UnitPrice", "");
		lineDetail_OrderLine.appendChild(line_lineOverallTotals);

		Element line_Awards = doc.createElement("Awards");
		lineDetail_OrderLine.appendChild(line_Awards);

		Element line_Award = doc.createElement("Award");
		line_Award.setAttribute("Action", "");
		line_Award.setAttribute("AwardId", "");
		line_Award.setAttribute("PromotionId", "");
		line_Award.setAttribute("Description", "");
		line_Award.setAttribute("AwardAmount", "");
		line_Award.setAttribute("AwardApplied", "");
		line_Awards.appendChild(line_Award);

		Element line_Award_Extn = doc.createElement("Extn");
		line_Award_Extn.setAttribute("ExtnMerchDisc", "");
		line_Award_Extn.setAttribute("ExtnGiftWrapDisc", "");
		line_Award_Extn.setAttribute("ExtnSurchargeDisc", "");
		line_Award_Extn.setAttribute("ExtnShippingDisc", "");
		line_Award_Extn.setAttribute("ExtnPersDisc", "");
		line_Award_Extn.setAttribute("ExtnShipGroupCode", "");
		line_Awards.appendChild(line_Award_Extn);

		Element line_OrderLineExtn = doc.createElement("Extn");
		line_OrderLineExtn.setAttribute("ExtnGroupID", "");
		line_OrderLineExtn.setAttribute("ExtnRobustLineType", "");
		line_OrderLineExtn.setAttribute("ExtnCustLOS", "");
		line_OrderLineExtn.setAttribute("ExtnMediaCode", "");
		line_OrderLineExtn.setAttribute("ExtnChargeSpec", "");
		lineDetail_OrderLine.appendChild(line_OrderLineExtn);

		Element line_wsiAddnlOrderLineDataList = doc.createElement("WSIAddnlOrderLineDataList");
		line_OrderLineExtn.appendChild(line_wsiAddnlOrderLineDataList);

		Element line_wsiAddnlOrderLineData = doc.createElement("WSIAddnlOrderLineData");
		line_wsiAddnlOrderLineData.setAttribute("ExtnSurcharge", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnFreeMONOPZFlag", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnIsRushShipping", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnIsFreeShipping", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnIsFreightTaxable", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnIsStateTaxable", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnSVCFrom", "");
		line_wsiAddnlOrderLineData.setAttribute("ExtnSVCTo", "");
		line_wsiAddnlOrderLineDataList.appendChild(line_wsiAddnlOrderLineData);

		Element line_wsiVASDataList = doc.createElement("WSIVASDataList");
		line_OrderLineExtn.appendChild(line_wsiVASDataList);

		Element line_wsiVASData = doc.createElement("WSIVASData");
		line_wsiVASData.setAttribute("ExtnVASMono", "");
		line_wsiVASData.setAttribute("ExtnVASPZ", "");
		line_wsiVASData.setAttribute("ExtnVASGiftWrap", "");
		line_wsiVASDataList.appendChild(line_wsiVASData);

		Element line_OrderStatuses = doc.createElement("OrderStatuses");
		lineDetail_OrderLine.appendChild(line_OrderStatuses);

		Element line_OrderStatus = doc.createElement("OrderStatus");
		line_OrderStatus.setAttribute("Status", "");
		line_OrderStatus.setAttribute("StatusQty", "");
		line_OrderStatuses.appendChild(line_OrderStatus);

		Element lineCharges = doc.createElement("LineCharges");
		lineDetail.appendChild(lineCharges);

		Element lineCharge = doc.createElement("LineCharge");
		lineCharge.setAttribute("ChargeAmount", "");
		lineCharge.setAttribute("ChargeCategory", "");
		lineCharge.setAttribute("ChargeName", "");
		lineCharge.setAttribute("ChargePerLine", "");
		lineCharge.setAttribute("ChargePerUnit", "");
		lineCharge.setAttribute("Reference", "");
		lineCharge.setAttribute("IsDiscount", "");
		lineCharges.appendChild(lineCharge);

		Element lineTaxes = doc.createElement("LineTaxes");
		lineDetail.appendChild(lineTaxes);

		Element lineTax = doc.createElement("LineTax");
		lineTax.setAttribute("ChargeCategory", "");
		lineTax.setAttribute("ChargeName", "");
		lineTax.setAttribute("Tax", "");
		lineTax.setAttribute("TaxName", "");
		lineTax.setAttribute("TaxPercentage", "");
		lineTax.setAttribute("TaxableFlag", "");
		lineTaxes.appendChild(lineTax);

		Element shipment = doc.createElement("Shipment");
		shipment.setAttribute("CarrierServiceCode", "");
		shipment.setAttribute("SCAC", "");
		shipment.setAttribute("ShipmentNo", "");
		shipment.setAttribute("TrackingNo", "");
		invoiceHeader.appendChild(shipment);

		Element shipToAddress = doc.createElement("ToAddress");
		shipToAddress.setAttribute("AddressLine1", "");
		shipToAddress.setAttribute("AddressLine2", "");
		shipToAddress.setAttribute("AddressLine3", "");
		shipToAddress.setAttribute("AddressLine4", "");
		shipToAddress.setAttribute("AddressLine5", "");
		shipToAddress.setAttribute("AddressLine6", "");
		shipToAddress.setAttribute("City", "");
		shipToAddress.setAttribute("Company", "");
		shipToAddress.setAttribute("Country", "");
		shipToAddress.setAttribute("DayPhone", "");
		shipToAddress.setAttribute("Department", "");
		shipToAddress.setAttribute("EMailID", "");
		shipToAddress.setAttribute("EveningPhone", "");
		shipToAddress.setAttribute("FirstName", "");
		shipToAddress.setAttribute("LastName", "");
		shipToAddress.setAttribute("MiddleName", "");
		shipToAddress.setAttribute("MobilePhone", "");
		shipToAddress.setAttribute("PersonID", "");
		shipToAddress.setAttribute("State", "");
		shipToAddress.setAttribute("ZipCode", "");
		shipment.appendChild(shipToAddress);

		Element shipNode = doc.createElement("ShipNode");
		shipNode.setAttribute("ShipnodeKey", "");
		shipment.appendChild(shipNode);

		Element shipNodePersonInfo = doc.createElement("ShipNodePersonInfo");
		shipNodePersonInfo.setAttribute("AddressLine1", "");
		shipNodePersonInfo.setAttribute("AddressLine2", "");
		shipNodePersonInfo.setAttribute("AddressLine3", "");
		shipNodePersonInfo.setAttribute("AddressLine4", "");
		shipNodePersonInfo.setAttribute("AddressLine5", "");
		shipNodePersonInfo.setAttribute("AddressLine6", "");
		shipNodePersonInfo.setAttribute("AlternateEmailID", "");
		shipNodePersonInfo.setAttribute("Beeper", "");
		shipNodePersonInfo.setAttribute("City", "");
		shipNodePersonInfo.setAttribute("Company", "");
		shipNodePersonInfo.setAttribute("Country", "");
		shipNodePersonInfo.setAttribute("DayFaxNo", "");
		shipNodePersonInfo.setAttribute("DayPhone", "");
		shipNodePersonInfo.setAttribute("Department", "");
		shipNodePersonInfo.setAttribute("EMailID", "");
		shipNodePersonInfo.setAttribute("EveningFaxNo", "");
		shipNodePersonInfo.setAttribute("EveningPhone", "");
		shipNodePersonInfo.setAttribute("FirstName", "");
		shipNodePersonInfo.setAttribute("HttpUrl", "");
		shipNodePersonInfo.setAttribute("JobTitle", "");
		shipNodePersonInfo.setAttribute("LastName", "");
		shipNodePersonInfo.setAttribute("MiddleName", "");
		shipNodePersonInfo.setAttribute("MobilePhone", "");
		shipNodePersonInfo.setAttribute("OtherPhone", "");
		shipNodePersonInfo.setAttribute("PersonID", "");
		shipNodePersonInfo.setAttribute("State", "");
		shipNodePersonInfo.setAttribute("Suffix", "");
		shipNodePersonInfo.setAttribute("Title", "");
		shipNodePersonInfo.setAttribute("ZipCode", "");
		shipNode.appendChild(shipNodePersonInfo);

		Element collectionDetails = doc.createElement("CollectionDetails");
		collectionDetails.setAttribute("TotalLines", "");
		invoiceHeader.appendChild(collectionDetails);

		Element collectionDetail = doc.createElement("CollectionDetail");
		collectionDetail.setAttribute("AmountCollected", "");
		collectionDetail.setAttribute("AuditTransactionID", "");
		collectionDetail.setAttribute("AuthorizationExpirationDate", "");
		collectionDetail.setAttribute("AuthorizationID", "");
		collectionDetail.setAttribute("BookAmount", "");
		collectionDetail.setAttribute("ChargeTransactionKey", "");
		collectionDetail.setAttribute("ChargeType", "");
		collectionDetail.setAttribute("CollectionDate", "");
		collectionDetail.setAttribute("CreditAmount", "");
		collectionDetail.setAttribute("DebitAmount", "");
		collectionDetail.setAttribute("DistributedAmount", "");
		collectionDetail.setAttribute("ExecutionDate", "");
		collectionDetail.setAttribute("HoldAgainstBook", "");
		collectionDetail.setAttribute("OpenAuthorizedAmount", "");
		collectionDetail.setAttribute("OrderHeaderKey", "");
		collectionDetail.setAttribute("OrderInvoiceKey", "");
		collectionDetail.setAttribute("PaymentKey", "");
		collectionDetail.setAttribute("RequestAmount", "");
		collectionDetail.setAttribute("SettledAmount", "");
		collectionDetail.setAttribute("Status", "");
		collectionDetail.setAttribute("StatusReason", "");
		collectionDetail.setAttribute("TransactionDate", "");
		collectionDetail.setAttribute("UserExitStatus", "");
		collectionDetails.appendChild(collectionDetail);

		Element creditCardTransactions = doc.createElement("CreditCardTransactions");
		collectionDetail.appendChild(creditCardTransactions);

		Element creditCardTransaction = doc.createElement("CreditCardTransaction");
		creditCardTransaction.setAttribute("AuthAmount", "");
		creditCardTransaction.setAttribute("AuthAvs", "");
		creditCardTransaction.setAttribute("AuthCode", "");
		creditCardTransaction.setAttribute("AuthReturnCode", "");
		creditCardTransaction.setAttribute("AuthReturnFlag", "");
		creditCardTransaction.setAttribute("AuthReturnMessage", "");
		creditCardTransaction.setAttribute("AuthTime", "");
		creditCardTransaction.setAttribute("ChargeTransactionKey", "");
		creditCardTransaction.setAttribute("CreditCardTransactionKey", "");
		creditCardTransaction.setAttribute("InternalReturnCode", "");
		creditCardTransaction.setAttribute("InternalReturnFlag", "");
		creditCardTransaction.setAttribute("InternalReturnMessage", "");
		creditCardTransaction.setAttribute("ParentKey", "");
		creditCardTransaction.setAttribute("Reference1", "");
		creditCardTransaction.setAttribute("Reference2", "");
		creditCardTransaction.setAttribute("RequestId", "");
		creditCardTransaction.setAttribute("TranAmount", "");
		creditCardTransaction.setAttribute("TranRequestTime", "");
		creditCardTransaction.setAttribute("TranReturnCode", "");
		creditCardTransaction.setAttribute("TranReturnFlag", "");
		creditCardTransaction.setAttribute("TranReturnMessage", "");
		creditCardTransaction.setAttribute("TranType", "");
		creditCardTransactions.appendChild(creditCardTransaction);

		Element CollectionpaymentMethod = doc.createElement("PaymentMethod");
		CollectionpaymentMethod.setAttribute("AwaitingAuthInterfaceAmount", "");
		CollectionpaymentMethod.setAttribute("AwaitingChargeInterfaceAmount", "");
		CollectionpaymentMethod.setAttribute("ChargeSequence", "");
		CollectionpaymentMethod.setAttribute("CheckNo", "");
		CollectionpaymentMethod.setAttribute("CheckReference", "");
		CollectionpaymentMethod.setAttribute("CreditCardExpDate", "");
		CollectionpaymentMethod.setAttribute("CreditCardName", "");
		CollectionpaymentMethod.setAttribute("CreditCardNo", "");
		CollectionpaymentMethod.setAttribute("CreditCardType", "");
		CollectionpaymentMethod.setAttribute("CustomerAccountNo", "");
		CollectionpaymentMethod.setAttribute("CustomerPONo", "");
		CollectionpaymentMethod.setAttribute("DisplayCreditCardNo", "");
		CollectionpaymentMethod.setAttribute("DisplayPrimaryAccountNo", "");
		CollectionpaymentMethod.setAttribute("FirstName", "");
		CollectionpaymentMethod.setAttribute("IncompletePaymentType", "");
		CollectionpaymentMethod.setAttribute("LastName", "");
		CollectionpaymentMethod.setAttribute("MaxChargeLimit", "");
		CollectionpaymentMethod.setAttribute("MiddleName", "");
		CollectionpaymentMethod.setAttribute("PaymentTypeGroup", "");
		CollectionpaymentMethod.setAttribute("PaymentReference1", "");
		CollectionpaymentMethod.setAttribute("PaymentReference2", "");
		CollectionpaymentMethod.setAttribute("PaymentReference3", "");
		CollectionpaymentMethod.setAttribute("PaymentReference4", "");
		CollectionpaymentMethod.setAttribute("PaymentReference5", "");
		CollectionpaymentMethod.setAttribute("PaymentReference6", "");
		CollectionpaymentMethod.setAttribute("PaymentReference7", "");
		CollectionpaymentMethod.setAttribute("PaymentReference8", "");
		CollectionpaymentMethod.setAttribute("PaymentReference9", "");
		CollectionpaymentMethod.setAttribute("PaymentType", "");
		CollectionpaymentMethod.setAttribute("RequestAmount", "");
		CollectionpaymentMethod.setAttribute("RequestedAuthAmount", "");
		CollectionpaymentMethod.setAttribute("RequestedChargeAmount", "");
		CollectionpaymentMethod.setAttribute("RequestedRefundAmount", "");
		CollectionpaymentMethod.setAttribute("SvcNo", "");
		CollectionpaymentMethod.setAttribute("SuspendAnyMoreCharges", "");
		CollectionpaymentMethod.setAttribute("TotalAltRefundedAmount", "");
		CollectionpaymentMethod.setAttribute("TotalAuthorized", "");
		CollectionpaymentMethod.setAttribute("TotalCharged", "");
		CollectionpaymentMethod.setAttribute("TotalRefundedAmount", "");
		CollectionpaymentMethod.setAttribute("UnlimitedCharges", "");
		collectionDetail.appendChild(CollectionpaymentMethod);

		Element paymentMethodExtn = doc.createElement("Extn");
		paymentMethodExtn.setAttribute("ExtnPaymentTag", "");
		CollectionpaymentMethod.appendChild(paymentMethodExtn);

		Element CollectionBillTo = doc.createElement("PersonInfoBillTo");
		CollectionBillTo.setAttribute("AddressLine1", "");
		CollectionBillTo.setAttribute("AddressLine2", "");
		CollectionBillTo.setAttribute("AddressLine3", "");
		CollectionBillTo.setAttribute("AddressLine4", "");
		CollectionBillTo.setAttribute("AddressLine5", "");
		CollectionBillTo.setAttribute("City", "");
		CollectionBillTo.setAttribute("Country", "");
		CollectionBillTo.setAttribute("FirstName", "");
		CollectionBillTo.setAttribute("IsAddressVerified", "");
		CollectionBillTo.setAttribute("LastName", "");
		CollectionBillTo.setAttribute("MiddleName", "");
		CollectionBillTo.setAttribute("State", "");
		CollectionBillTo.setAttribute("Suffix", "");
		CollectionBillTo.setAttribute("Title", "");
		CollectionBillTo.setAttribute("ZipCode", "");
		CollectionpaymentMethod.appendChild(CollectionBillTo);

		Element CollectionBillToExtn = doc.createElement("Extn");
		CollectionBillToExtn.setAttribute("ExtnIsAddressOverridden", "");
		CollectionBillToExtn.setAttribute("ExtnAddressType", "");
		CollectionBillTo.appendChild(CollectionBillToExtn);

		return AppModule.callApi("getOrderInvoiceDetails", Modules.buildStringFromDocument(docXML), false,
				buildStringFromDocument(doc));
	}

	public static String stampFinancialDateOnInvoice(String getOrderInvoiceDetailsTemplateOutput) throws Exception {
		return AppModule.callApi("StampFinancialDateOnInvoice", getOrderInvoiceDetailsTemplateOutput, true);
	}

	public static String wccGetOrderListForOrderTotal(Document doc) throws Exception {
		return AppModule.callApi("WCC_GetOrderListForOrderTotal", Modules.buildStringFromDocument(doc), true);
	}

	public static String receiveOrder(Document doc) throws Exception {
		return AppModule.callApi("receiveOrder", buildStringFromDocument(doc));
	}

	public static String retailAllocationPreProcessor(Document doc) throws Exception {
		return AppModule.callApi("RetailAllocationPreProcessor", buildStringFromDocument(doc), true);
	}
	
	public static String processAsyncOrderCreate(Document doc) throws Exception {
		return AppModule.callApi("ProcessAsyncOrderCreate", buildStringFromDocument(doc), true);
	}

	public static String getOrderList(Document doc) throws Exception {
		return AppModule.callApi("getOrderList", buildStringFromDocument(doc), false,
				"<OrderList><Order><Extn/><OrderLines><OrderLine><Extn/><Item/></OrderLine></OrderLines></Order></OrderList>");
	}

	public static String manageItem(Document doc) throws Exception {
		return AppModule.callApi("ManageItem", buildStringFromDocument(doc), true);
	}

	public static String processItemEventFeed(Document doc) throws Exception {
		return AppModule.callApi("ProcessItemEventFeed", buildStringFromDocument(doc), true);
	}
	
	public static String processItemPrice(Document doc) throws Exception {
		return AppModule.callApi("ProcessItemPrice", buildStringFromDocument(doc), true);
	}
	
	public static String processPOSnapshot(Document doc) throws Exception {
		return AppModule.callApi("ProcessPOSnapShot", buildStringFromDocument(doc), true);
	}

	public static String getInventorySupply(Document doc) throws Exception {
		return AppModule.callApi("getInventorySupply", buildStringFromDocument(doc));
	}

	public static String unreceiveOrder(Document doc) throws Exception {
		return AppModule.callApi("unreceiveOrder", buildStringFromDocument(doc));
	}

	public static String changeOrderStatus(Document doc) throws Exception {
		return AppModule.callApi("changeOrderStatus", buildStringFromDocument(doc));
	}

	/**
	 * Calls the DropShipPOSearch2 custom service
	 * 
	 * @param doc
	 * @return The output XML from the DropShipPOSearch2 custom service
	 * @throws Exception
	 */
	public static String dropShipPOSearch2(Document doc) throws Exception {
		return AppModule.callApi("DropShipPOSearch2", buildStringFromDocument(doc), true);
	}

	/**
	 * Calls the DSDashboardLatePO custom service
	 * 
	 * @param doc
	 * @return The output XML from the DSDashboardLatePO custom service
	 * @throws Exception
	 */
	public static String dsDashboardLatePO(Document doc) throws Exception {
		return AppModule.callApi("DSDashboardLatePO", buildStringFromDocument(doc), true);
	}

	/**
	 * Calls the NewAndACKMessageForPO custom service
	 * 
	 * @param doc
	 * @return The output XML from the NewAndACKMessageForPO custom service
	 * @throws Exception
	 */
	public static String newAndACKMessageForPO(Document doc) throws Exception {
		return AppModule.callApi("NewAndACKMessageForPO", buildStringFromDocument(doc), true);
	}

	/**
	 * Calls the DSChangeETAUpload custom service
	 * 
	 * @param doc
	 * @return The output XML from the DSChangeETAUpload custom service
	 * @throws Exception
	 */
	public static String dsChangeETAUpload(Document doc) throws Exception {
		return AppModule.callApi("DSChangeETAUpload", buildStringFromDocument(doc), true);
	}

	/**
	 * Calls the getItemList API
	 * 
	 * @param doc
	 * @return The output XML from the getItemList API
	 * @throws Exception
	 */
	public static String getItemList(Document doc) throws Exception {
		return AppModule.callApi("getItemList", buildStringFromDocument(doc), false);
	}

	/**
	 * Calls the DSMarkPOLate custom service
	 * 
	 * @param doc
	 * @return The output XML from the DSMarkPOLate custom service
	 * @throws Exception
	 */
	public static String dsMarkPOLate(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.DSMARKPOLATEAPI, buildStringFromDocument(doc), true);
	}



	public static String processINTLPOCreate(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.PROCESSINTLPOCREATEAPI, Modules.buildStringFromDocument(doc), true);
	}

	public static String dspoCreateShipmentsService(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.DSPOCREATESHIPSVCAPI, Modules.buildStringFromDocument(doc), true);
	}

	public static String getShipmentListForOrder(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.GETSHIPLISTFORORDERAPI, buildStringFromDocument(doc), false);
	}

	public static String getShipmentContainerList(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.GETSHIPCONTLISTAPI, buildStringFromDocument(doc), false);
	}

	public static String dropShipProcessASN(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.DROPSHIPPROCASNAPI, buildStringFromDocument(doc), true);
	}

	public static String dropShipProcessContainerActivity(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.DROPSHIPPROCCONTAINERAPI, buildStringFromDocument(doc), true);
	}

	public static String getOrderHoldTypeList(Document doc) throws Exception {
		return AppModule.callApi(ApiConstants.GETORDERHOLDTYPELISTAPI, buildStringFromDocument(doc), false);
	}

	public static String acknowledgeBOPISOnPrint(Document doc) throws Exception {
		return AppModule.callApi("acknowledgeBOPISOnPrint", buildStringFromDocument(doc), true);
	}

	public static String confirmStorePickup(Document doc) throws TransformerException, Exception {
		return AppModule.callApi("confirmStorePickup", buildStringFromDocument(doc), true);
	}

	public static String confirmCustomerPickup(Document doc) throws Exception {
		return AppModule.callApi("confirmCustomerPickUp", buildStringFromDocument(doc), true);
	}

	public static String confirmDraftOrder(Document doc) throws Exception {
		return AppModule.callApi("confirmDraftOrder", buildStringFromDocument(doc), false);
	}

	public static String changeOrderApi(Document doc) throws Exception {
		return AppModule.callApi("changeOrder", buildStringFromDocument(doc), false);
	}

	public static String createOrderInvoiceService(Document doc) throws Exception {
		return AppModule.callApi("CreateOrderInvoiceService", buildStringFromDocument(doc), true);
	}

	public static String getReceiptList(Document doc) throws Exception {
		return AppModule.callApi("getReceiptList", buildStringFromDocument(doc), false);
	}

	public static String getReceiptList(Document doc, String template) throws Exception {
		return AppModule.callApi("getReceiptList", buildStringFromDocument(doc), false, template);
	}
	
	
	
	public static String createDTCCanadaOrder(Document doc) throws Exception {
		return AppModule.callApi("EComOrderCreate", buildStringFromDocument(doc), true);
	}
	
	public static String processPaymentStatusForCA(String input) throws Exception {
		return AppModule.callApi("ProcessPaymentStatusForCA", input, true);
	}
	
	public static String wsiGetOrderListForBackOrder(Document doc) throws Exception {
		return AppModule.callApi("WSIGetOrderListForBackOrder", buildStringFromDocument(doc), true);
	}
	
	public static String executeDelayedDrop(String wsiGetOrderListForBackOrderOutput) throws Exception {
		return AppModule.callApi("ExecuteDelayedDrop", wsiGetOrderListForBackOrderOutput, true);
	}
	
	public static String processOrderHoldTypeUE(Document doc) throws Exception {
		return AppModule.callApi("ProcessOrderHoldTypeUE", buildStringFromDocument(doc), true);
	}
	
	public static String getSourcingRuleList(Document doc, String template) throws Exception {
		return AppModule.callApi("getSourcingRuleList", buildStringFromDocument(doc), false, template);
	}
	
	public static String getDistributionList(Document doc) throws Exception {
		return AppModule.callApi("getDistributionList", buildStringFromDocument(doc), false);
	}
}
