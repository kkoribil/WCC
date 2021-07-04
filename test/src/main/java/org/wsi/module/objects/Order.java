package org.wsi.module.objects;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wsi.module.AppModule;
import org.wsi.module.Modules;

public class Order implements TestObject {
	public static final String PAYMENT_GIFT_CARD = "GIFT_CARD";
	public static final String PAYMENT_LRC = "LRC";
	public static final String PAYMENT_CREDIT_CARD = "CREDIT_CARD";
	public static final String PAYMENT_CASH = "CASH";
	/*
	 * Object properties
	 */
	protected String orderNumber;
	protected String orderDate;
	protected String organizationCode;
	protected String orderType;
	private String OrderType1 = "REGULAR";
	protected String entryType; // Required to set if orderType not PHONEORDER
	protected String consolidatorAddressCode = "";
	protected String pkmsReceiptTimeStamp;
	protected String actualShipmentDate; // TODO should this be on the OrderLine?
	protected String mergeNode = ""; // TODO should this be on the OrderLine?
	protected String ecddShipDate; // TODO needed for drop ship orders, empty for regular orders
	protected String ecddDeliveryDate; // TODO needed for drop ship orders, empty for regular orders
	protected String currentPromiseDate;
	protected String returnOrderNumber;
	protected String receiptHeaderKey;
	protected String receiptNo;
	protected String extnCustomerOrderNo;
	protected String searchCriteria2 = "";
	protected String orderedQty = "1";
	protected String deliverTogether = "";
	protected String robustIndicator = "N";
	private String ReturnByGiftRecipient = "N";
	protected String shipmentNumber;

	protected String firstName = "QE_CUSTOMER";
	protected String lastName = "TEST";
	
	
	protected String notes = "-";
	protected String customerAccountNumber = "-";

	protected String addressLine1 = "1634 BLUE JAY WAY";
	protected String addressLine2 = "";
	protected String addressLine3 = "";
	protected String city = "LOS ANGELES";
	protected String state = "CA";
	protected String zipCode = "90046";
	protected String billingAddressLine1 = "1634 BLUE JAY WAY";
	protected String billingAddressLine2 = "";
	protected String billingAddressLine3 = "";
	protected String billingCity = "LOS ANGELES";
	protected String billingState = "CA";
	protected String billingZipCode = "90046";
	protected String phone = "4158722743";
//    private   String orderLineKey[] = { "0", "0", "0" };
	protected String orderLineKey;

	protected String sellerOrganizationCode;
	protected String orderHeaderKey;

	protected String maxChangeLimit = "28640.81";
	protected String requestAmount = "28640.81";
	protected String processedAmount = "";
	protected String paymentType = PAYMENT_CASH;
	protected String chargeType = "CHARGE";
	protected String paymentKey = "";
	public static List<String> prepaid_payment_types = Arrays.asList(PAYMENT_CASH, PAYMENT_GIFT_CARD, PAYMENT_LRC);
	public static List<String> postpaid_payment_types = Arrays.asList(PAYMENT_CREDIT_CARD);
	protected List<String> paymentTypes = Stream.concat(prepaid_payment_types.stream(), postpaid_payment_types.stream())
			.collect(Collectors.toList());

	protected String shipmentQuantity = "1";
	protected String totalQuantity = "1";
	protected String TypeOfOrder = ""; // set this to override order.save() and creation of second order when running
										// order.schedule()
	protected boolean hasInventory = true; // set this to N to get backordered item

	protected String workOrderNo;
	protected String workOrderKey;
	protected String workOrderApptKey;

	protected String documentType = "";

	private String shipmentKey;
	private String release_no;
	private String order_release_key;

	protected List<OrderLine> lines;
	protected List<OrderLine> lines1;
	/*
	 *  
	 */
	protected String uniqueId;
	protected String apiOut;
	protected boolean isCreated = false;
	protected boolean isModified = true;
	protected String currentXml = "";
	protected XPathFactory xpathFactory = XPathFactory.newInstance();
	protected XPath xpath = xpathFactory.newXPath();
	protected XPathExpression xpathExpr;
	protected Pattern orderLineKeyPattern = Pattern.compile("\\sOrderLineKey=\"(\\d+)\"");
	protected Integer primeLineNoMultipler = 100;
	protected String firstPrimeLineNo = "1"; // TODO temporary for testing drop ship orders
	protected String secondPrimeLineNo = "2";
	protected String firstSubLineNo = "1";
	protected String secondSubLineNo = "2";

	protected String packItemPrimeLineNo = "100";
	protected String packItemSubLineNo = "1";
	protected String packItemFirstComponentSubLineNo = "9000";
	protected String packItemSecondComponentSubLineNo = "9001";

	protected String itemAvailabilityDate;
	protected String quotedItemAvailabilityDate;

	protected String actualOrderDate = "";
	protected String order_no_dspo;

	// for Drop Ship
	protected String orderHeaderKeyDSPO;
	protected String orderLineKeyDSPO;
	protected String orderNoDSPO;
	private boolean isDSPOModified = false;

	protected String paymentToken = "lBBkEwTcUdm6y6Xq2O6c";

	protected Boolean useOrderPricing = false;

	protected String auth_code;

	protected Boolean execute_collection = true;// Unless we manually set this to false, then exeucte the collection
	protected ZonedDateTime auth_expiration = ZonedDateTime.now().plusDays(31);// This needs to be at the payment level
																				// eventually
	// for payment methods
	

	// For Invoicing (BORA)
	protected String invoiceKey;
	protected String invoiceNumber;

	
	public String shipAdviceNumber;
	private String ExtnActionCode = "CR";

	String DSPOTrackingNo = "1Z9Y33141260240420";

	String promoCode = "";

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getDSPOTrackingNo() {
		return DSPOTrackingNo;
	}

	public void setDSPOTrackingNo(String dSPOTrackingNo) {
		DSPOTrackingNo = dSPOTrackingNo;
	}

	/*
	 * Constructors
	 */
	public Order() throws Exception {
		initialize(true);
	}

	public void setOrderType1(String ordertype) {
		OrderType1 = ordertype;
	}

	public String getOrderType1() {
		return OrderType1;
	}

	
	public String getnotes() {
		return notes;
	}
	
	public String getcustomerAccountNumber() {
		return customerAccountNumber;
	}
	
	public List<OrderLine> getReturnOrderLines() {
		return this.lines1;
	}

	public String getExtnActionCode() {
		return ExtnActionCode;
	}
	protected void initialize(boolean useDbCheck) throws Exception {
		lines = new ArrayList<OrderLine>();
		
		pkmsReceiptTimeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format(LocalDateTime.now());
		ecddShipDate = "";
		ecddDeliveryDate = "";
		currentPromiseDate = "";
		
	}

	public Order(Item i) throws Exception {

		initialize(true);
		i.save();
		addOrderLine(i);
	}

	/*
	 * Constructors
	 */
	

	

	public void setUseOrderPricing(Boolean useOrderPricing) {
		this.useOrderPricing = useOrderPricing;
	}

	public void addOrderLine(OrderLine orderLine) throws Exception {
		orderLine.setContainerNoIfNotSet(orderNumber);
		orderLine.saveItem();
		// addOrderLineToLines(orderLine);
		lines.add(orderLine);
	}

	public void addOrderLine(Item item, String shipNode) throws Exception {
		// item.save();
		OrderLine orderLine = new OrderLine(item, shipNode);
		orderLine.setContainerNo(orderNumber);

		orderLine.setAddressLine1(addressLine1);
		orderLine.setCity(city);
		orderLine.setState(state);
		orderLine.setZipCode(zipCode);

		// Set OrderLine address to or
		addOrderLineToLines(orderLine);
	}

	public void ProcessOrderMgmtReq1(String ItemID, String enterpriseCode) throws Exception {
		ProcessOrderMgmtReq1("MODIFY", ItemID, enterpriseCode);
	}

	public void ProcessOrderMgmtReq1(String order_action, String ItemID, String enterpriseCode) throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();

		Document doc = db.newDocument();
		Element root = doc.createElement("TriggerAgent");
		root.setAttribute("Action", "cancel".equals(order_action.toLowerCase()) ? "MODIFY" : order_action);
//		root.setAttribute("DocumentType", "0001");
		root.setAttribute("BaseTransactionId", "FulfillmentType_MESSAGE.2001.ex");
		root.setAttribute("CriteriaId", "FulfillmentType");
		root.setAttribute("IgnoreOrdering", "Y");

		Element CriteriaAttributes = doc.createElement("CriteriaAttributes");
		root.appendChild(CriteriaAttributes);

		Element AttributeName = doc.createElement("Attribute");
		AttributeName.setAttribute("Name", "ItemID");
		AttributeName.setAttribute("Value", ItemID);
		CriteriaAttributes.appendChild(AttributeName);

		Element AttributeName1 = doc.createElement("Attribute");
		AttributeName1.setAttribute("Name", "EnterpriseCode");
		AttributeName1.setAttribute("Value", enterpriseCode);
		CriteriaAttributes.appendChild(AttributeName1);

		Element AttributeName2 = doc.createElement("Attribute");
		AttributeName2.setAttribute("Name", "ModificationReasonCode");
		AttributeName2.setAttribute("Value", "BODS");
		CriteriaAttributes.appendChild(AttributeName2);

		Element AttributeName3 = doc.createElement("Attribute");
		AttributeName3.setAttribute("Name", "UserId");
		AttributeName3.setAttribute("Value", "admin");
		CriteriaAttributes.appendChild(AttributeName3);

		Element AttributeName4 = doc.createElement("Attribute");
		AttributeName4.setAttribute("Name", "KitCode");
		AttributeName4.setAttribute("Value", "");
		CriteriaAttributes.appendChild(AttributeName4);

		Element AttributeName5 = doc.createElement("Attribute");
		AttributeName5.setAttribute("Name", "TimeStamp");
		AttributeName5.setAttribute("Value", "2018-11-30T23:09:20");
		CriteriaAttributes.appendChild(AttributeName5);

		doc.appendChild(root);
		AppModule.callApi("ProcessOrderMgmtReq", Modules.buildStringFromDocument(doc), true);

		isDSPOModified = true;
		isModified = true;

	}
	

	public void ProcessOrderMgmtReq(String ItemID, String enterpriseCode) throws Exception {
		ProcessOrderMgmtReq("MODIFY", ItemID, enterpriseCode);
	}

	public void ProcessOrderMgmtReq(String order_action, String ItemID, String enterpriseCode) throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();

		Document doc = db.newDocument();
		Element root = doc.createElement("TriggerAgent");
		root.setAttribute("Action", "cancel".equals(order_action.toLowerCase()) ? "MODIFY" : order_action);
		// root.setAttribute("DocumentType", "0001");
		root.setAttribute("BaseTransactionId", "FulfillmentType_MESSAGE.2001.ex");
		root.setAttribute("CriteriaId", "FulfillmentType");
		root.setAttribute("IgnoreOrdering", "Y");

		Element CriteriaAttributes = doc.createElement("CriteriaAttributes");
		root.appendChild(CriteriaAttributes);

		Element AttributeName = doc.createElement("Attribute");
		AttributeName.setAttribute("Name", "ItemID");
		AttributeName.setAttribute("Value", ItemID);
		CriteriaAttributes.appendChild(AttributeName);

		Element AttributeName1 = doc.createElement("Attribute");
		AttributeName1.setAttribute("Name", "EnterpriseCode");
		AttributeName1.setAttribute("Value", enterpriseCode);
		CriteriaAttributes.appendChild(AttributeName1);

		Element AttributeName2 = doc.createElement("Attribute");
		AttributeName2.setAttribute("Name", "ModificationReasonCode");
		AttributeName2.setAttribute("Value", "DSBO");
		CriteriaAttributes.appendChild(AttributeName2);

		Element AttributeName3 = doc.createElement("Attribute");
		AttributeName3.setAttribute("Name", "UserId");
		AttributeName3.setAttribute("Value", "admin");
		CriteriaAttributes.appendChild(AttributeName3);

		Element AttributeName4 = doc.createElement("Attribute");
		AttributeName4.setAttribute("Name", "KitCode");
		AttributeName4.setAttribute("Value", "");
		CriteriaAttributes.appendChild(AttributeName4);

		Element AttributeName5 = doc.createElement("Attribute");
		AttributeName5.setAttribute("Name", "TimeStamp");
		AttributeName5.setAttribute("Value", "2018-11-30T23:09:20");
		CriteriaAttributes.appendChild(AttributeName5);

		doc.appendChild(root);
		AppModule.callApi("ProcessOrderMgmtReq", Modules.buildStringFromDocument(doc), true);

		isDSPOModified = true;
		isModified = true;

	}

	
	

	public void addOrderLine(Item item) throws Exception {
		item.save();
		OrderLine orderLine = new OrderLine(item);
		orderLine.setContainerNo(orderNumber);

		orderLine.setAddressLine1(addressLine1);
		orderLine.setCity(city);
		orderLine.setState(state);
		orderLine.setZipCode(zipCode);

		addOrderLineToLines(orderLine);
	}

	

	protected void addOrderLineToLines(OrderLine orderLine) {
		Integer primeLineNo = (lines.size() + 1) * primeLineNoMultipler;
		orderLine.setPrimeLineNo(primeLineNo.toString());
		lines.add(orderLine);
	}

	public List<OrderLine> getOrderLines() {
		return this.lines;
	}



	
	public boolean hasLiveBrand() {
		return isLiveBrand(this.organizationCode);
	}

	public boolean hasNonLiveBrand() {
		return !isLiveBrand(this.organizationCode);
	}

	/*public void returnreleaseHold(String holdType) throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", this.getReturnOrderNumber());
		root.setAttribute("EnterpriseCode", this.getOrganizationCode());
		root.setAttribute("DocumentType", "0003");
		root.setAttribute("SelectMethod", "WAIT");

		Element orderHoldTypes = doc.createElement("OrderHoldTypes");

		Element orderHoldType = doc.createElement("OrderHoldType");
		orderHoldType.setAttribute("Status", "1300");
		orderHoldType.setAttribute("HoldType", holdType);

		orderHoldTypes.appendChild(orderHoldType);
		root.appendChild(orderHoldTypes);
		doc.appendChild(root);

		apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);
	}*/

	

	

	public void setinfiniteInventory(String itemId, String concept) throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("Items");

		Element Item = doc.createElement("Item");
		Item.setAttribute("AdjustmentType", "ADJUSTMENT");
		Item.setAttribute("Availability", "INFINITE");
		Item.setAttribute("ETA", "2018-11-01");
		Item.setAttribute("ItemID", itemId);
		Item.setAttribute("Quantity", "0");
		Item.setAttribute("ShipNode", "VDR_4425");
		Item.setAttribute("SupplyType", "ONHAND");
		Item.setAttribute("UnitOfMeasure", "EACH");
		Item.setAttribute("OrganizationCode", concept);

		root.appendChild(Item);
		doc.appendChild(root);
		System.out.println("testing" + Modules.buildStringFromDocument(doc) + "Hello api xml");
		AppModule.callApi("adjustInventory", Modules.buildStringFromDocument(doc));

	}

	public String createOrderInvoice1(List<OrderLine> ol) throws Exception {
		String result = "";
		try {
			ol = (ol == null || ol.isEmpty()) ? lines : ol; // Default to all order lines if is null
			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("Order");
			root.setAttribute("OrderNo", returnOrderNumber);
			root.setAttribute("DocumentType", "0003");
			root.setAttribute("EnterpriseCode", "");
			root.setAttribute("TransactionId", "CREATE_RETURN_INVOICE.0003.ex");

			Element orderlines = doc.createElement("OrderLines");

			for (OrderLine line : ol) {
				Element orderline = doc.createElement("OrderLine");

				orderline.setAttribute("PrimeLineNo", line.getPrimeLineNo());
				orderline.setAttribute("SubLineNo", line.getSubLineNo());
				orderline.setAttribute("Quantity", line.getQuantity());

				orderlines.appendChild(orderline);
			}
			root.appendChild(orderlines);
			doc.appendChild(root);

			result = AppModule.createOrderInvoice(Modules.buildStringFromDocument(doc));
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	
	 
	

	public void changeOrderPayment(String orderNumber, String enterpriseCode) throws Exception {
		changeOrderPayment("MODIFY", orderNumber, enterpriseCode);
	}

	public void changeOrderPayment(String order_action, String orderNumber, String enterpriseCode) throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();

		Document doc = db.newDocument();
		Element root = doc.createElement("Order");
		root.setAttribute("Action", "cancel".equals(order_action.toLowerCase()) ? "MODIFY" : order_action);
		// root.setAttribute("DocumentType", "0001");
		root.setAttribute("OrderNo", orderNumber);
		root.setAttribute("EnterpriseCode", enterpriseCode);
		// root.setAttribute("PaymentStatus","NOT_APPLICABLE"); // Disabled for Live
		// brand issue, ErrorCode="YFS10137"
		root.setAttribute("SelectMethod", "WAIT");

		Element PaymentMethods = doc.createElement("PaymentMethods");
		root.appendChild(PaymentMethods);

		Element PaymentMethod = doc.createElement("PaymentMethod");

		PaymentMethod.setAttribute("ChargeSequence", "100");
		PaymentMethod.setAttribute("MaxChargeLimit", "50");
		PaymentMethod.setAttribute("PaymentReference1", "1");
		PaymentMethod.setAttribute("PaymentReference2", "RPL/NC");
		PaymentMethod.setAttribute("PaymentReference3", "OE");
		PaymentMethod.setAttribute("UnlimitedCharges", "N");
		PaymentMethod.setAttribute("CreditCardExpDate", "");
		PaymentMethod.setAttribute("CreditCardNo", "");
		PaymentMethod.setAttribute("PaymentType", "CASH");
		PaymentMethod.setAttribute("CreditCardType", "");
		PaymentMethod.setAttribute("CreditCardName", "");
		PaymentMethods.appendChild(PaymentMethod);

		Element paymentDetails = doc.createElement("PyamentDetails");
		paymentDetails.setAttribute("ChargeType", "CHARGE");
		paymentDetails.setAttribute("RequestAmount", "50");
		paymentDetails.setAttribute("ProcessedAmount", "50");

		// PaymentMethods.appendChild(PaymentMethods);
		// root.appendChild(PaymentMethods);

		doc.appendChild(root);

		// TODO: Disable payment data, as change in payment for Order is handled by
		// CapturePayment api?
		/*
		 * if (!"Cancel".equals(order_action)) { Element changePayments =
		 * paymentSetup(doc, paymentType); root.appendChild(PaymentMethod1); }
		 */
		AppModule.callApi("ChangeOrder", Modules.buildStringFromDocument(doc), true);

		isDSPOModified = true;
		isModified = true;
	}

	

	public String getChargeTransactionDetails() throws Exception {
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("ChargeTransactionDetail");
		root.setAttribute("OrderHeaderKey", orderHeaderKey);
		doc.appendChild(root);

		return Modules.getChargeTransactionList(doc);
		// return
		// deserializeChargeTransactionDetails(Modules.getChargeTransactionList(doc));
	}

	// Deprecating in case any others crop up - delete when no longer used
	/*@Deprecated
	public void releaseShafterHold() throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", this.getOrderNumber());
		root.setAttribute("EnterpriseCode", this.getOrganizationCode());
		root.setAttribute("DocumentType", "0001");

		Element orderHoldTypes = doc.createElement("OrderHoldTypes");

		Element orderHoldType = doc.createElement("OrderHoldType");
		orderHoldType.setAttribute("Status", "1300");
		orderHoldType.setAttribute("HoldType", "SHAFTER_HOLD");

		orderHoldTypes.appendChild(orderHoldType);
		root.appendChild(orderHoldTypes);
		doc.appendChild(root);

		apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);
	}*/

	// Get all the unresolved holds associated with the Order
	/*private ArrayList<String> getUresolvedHolds() throws Exception {
		Node nod;
		NodeList nodes;
		
		apiOut = getOrderHoldList();

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
				}
			}
		}
		return unresolved_holds;
	}*/

	/**
	 * Use this to remove any holds on order
	 *
	 * @throws Exception
	 */
	/*public void releaseHolds(ResultSet rs) throws Exception {
		List<String> unresolved_holds = getUresolvedHolds();
		while (unresolved_holds.size() > 0) {
			for (String holdType : unresolved_holds) {
				// TODO: identify what indicates order line level hold
				if ("IDS_VALIDATION_HOLD".equals(holdType)) {
					releaseOrderLineHold(rs, holdType);
				} else {
					releaseHold(holdType);
				}
			}
			unresolved_holds = getUresolvedHolds();
		}
	}*/

	/**
	 * Use this to remove any hold type on order level
	 * 
	 * @param holdType
	 * @throws Exception
	 */
	public void releaseHold(String holdType) throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", "992071654600");
		root.setAttribute("EnterpriseCode", "WE");
		root.setAttribute("DocumentType", "0001");
		root.setAttribute("SelectMethod", "WAIT");

		Element orderHoldTypes = doc.createElement("OrderHoldTypes");

		Element orderHoldType = doc.createElement("OrderHoldType");
		orderHoldType.setAttribute("Status", "1300");
		orderHoldType.setAttribute("HoldType", holdType);

		orderHoldTypes.appendChild(orderHoldType);
		root.appendChild(orderHoldTypes);
		doc.appendChild(root);

		apiOut = AppModule.callApi("changeOrder", Modules.buildStringFromDocument(doc), false);
	}

	/*public void releaseOrderLineHold(ResultSet rs,String holdType) throws Exception {

		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = df.newDocumentBuilder();
		Document doc = db.newDocument();

		Element root = doc.createElement("Order");
		root.setAttribute("OrderNo", this.getOrderNumber());
		root.setAttribute("EnterpriseCode", this.getOrganizationCode());
		root.setAttribute("DocumentType", "0001");
		root.setAttribute("Action", "Modify");
		root.setAttribute("Override", "Y");

		Element orderLines = doc.createElement("OrderLines");

	while(rs.next()) {
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
*/
	




}
