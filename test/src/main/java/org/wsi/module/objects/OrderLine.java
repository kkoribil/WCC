package org.wsi.module.objects;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsi.module.AppModule;


/**
 * An order line.  An Order can have multiple order lines. 
 * 
 * @author LVansickle1
 *
 */
public class OrderLine implements TestObject {

	private OrderLine parent;
	private List<OrderLine> children = new ArrayList<OrderLine>();
	protected Item item;

	private String shipNode;
	private String receivingNode;
	private String carrierServiceCode;
	private String scac;
	private String fulfillmentType;
	private String containerNo;
	private String trackingNo;
	private String deliveryMethod;
	private String reservationId;
	private String shipmentLineKey;
	protected String primeLineNo;
	protected String subLineNo;
	private String shipment_line_no;
	public String quantity = "1";
	private String orderLineType;
	protected String orderLineKey;
	private String fulFillmentQuantity;
	private String isDropShipContainer = "N";
	private String extnLOS = "Regular";
	
	// Set for packs
	private String kitCode = "";
	private String IsBundleParent = "N";
	private String parentPrimeLineNo = "100";
	private String parentSubLineNo = "1";
	private String lineTax = "8.25";
	
	private String unitPrice = "50";
	private String retailPrice = "100";
	private String shippingSurcharge = "0";
	private String shippingCharge = "10";
	private String shippingEffective = "11.5";
	private String monoPZCharge = "0";
	private String merchEffective = "0";
	
	//private String lineTotalPrice = "0";

	private String action = "MODIFY";
	
	private boolean addressChanged = false;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	
	private String firstName;
	private String lastName;
	
	private String extnVASMono = "N";
	private String extnVASPZ = "N";
	private String isDeluxe = "";
	
	protected String giftWrap = "N";
	protected String extnVASGiftWrapCode = "B";
	protected String levelOfService = "STANDARD";
	protected String labelInstructions = "";	
	protected String giftMessage = "";
	protected String monogramMessage = "";
	protected String personalizationMessage = "";

	protected String rushShipping = "N";

	private boolean shipmentConfirmed = false;  // This is set to true once this line is confirmshipped, this allows us to call the api at different points in the order lifecycle when the orderLine becomes valid
	
	private String dropShipPrimeLineNo;
	private String dropShipSubLineNo;
		
	
	
	private String whseInstruction = "";

	public String getWhseInstruction() {
		return whseInstruction;
	}

	public String getlabelInstructions() {
		return labelInstructions;
	}
		
	public String getgiftMessage() {
		return giftMessage;
	}
	
	public String getmonogramMessage() {
		return monogramMessage;
	}
	
	public String getpersonalizationMessage() {
		return personalizationMessage;
	}
	
	public void setWhseInstruction(String whseInstruction) {
		this.whseInstruction = whseInstruction;
	}
	
	
	public void setgiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}
	
	public void setmonogramMessage(String monogramMessage) {
		this.monogramMessage = monogramMessage;
	}
	
	public void setpersonalizationMessage(String personalizationMessage) {
		this.personalizationMessage = personalizationMessage;
	}
	
	public void setLabelInstructions(String labelInstructions) {
		this.labelInstructions = labelInstructions;
	}
	
	protected OrderLine() {}
    
	public OrderLine(Item item, String shipNode, String carrierServiceCode, 
    		         String scac, String fulfillmentType, List<OrderLine>  children) throws Exception {
		this.item = item;
		this.shipNode = shipNode;
		this.carrierServiceCode = carrierServiceCode;
		this.scac = scac;
		this.fulfillmentType = fulfillmentType;
		this.containerNo = null;
		this.primeLineNo = null;
		this.subLineNo = null;
		this.shipment_line_no = "SLN" + AppModule.generateId(11);
		this.quantity = "1";
		this.fulFillmentQuantity = quantity;
		this.orderLineType = "REGULAR";
		this.children = children;
		
	}
	public OrderLine(Item item, String shipNode, String carrierServiceCode, 
	         String scac, String fulfillmentType) throws Exception {
		this(item,shipNode,carrierServiceCode,scac,fulfillmentType,new ArrayList<OrderLine>());

	}
	
    public OrderLine(Item item, String shipNode) throws Exception {
		this(item, shipNode, DEFAULT_CARRIER_SERVICE_CODE, DEFAULT_SCAC, DEFAULT_FULFILLMENT_TYPE);
	}
	
	public OrderLine(Item i) throws Exception {
		this(i, DEFAULT_SHIP_NODE);
	}
	
	public OrderLine(Element olElem) throws Exception {
		// Add attribute assignments as necessary
		
		// Order Line Key
		this.orderLineKey = olElem.getAttribute("OrderLineKey");
		
		// Prime Line No
		this.primeLineNo = olElem.getAttribute("PrimeLineNo");

		// Sub Line No
		this.subLineNo = olElem.getAttribute("SubLineNo");
		
		// Carrier Service Code
		this.carrierServiceCode = olElem.getAttribute("CarrierServiceCode");
		
		// SCAC
		this.scac = olElem.getAttribute("SCAC");
		
		// ShipNode
		this.shipNode = olElem.getAttribute("ShipNode");
		
		// Quantity
		this.quantity = olElem.getAttribute("OrderedQty");
		
		// Item ID -> need to set up item for this
		Element itemElem = (Element)olElem.getElementsByTagName("Item").item(0);
		//this.item = new SimpleItem(itemElem);
	}


	// TODO Should item be saved in constructor here rather than in Order.addOrderLine() ?
	public void saveItem() throws Exception {
		item.save();
	}
	
	
	/*public void updateLineChargesAndTaxes(Document orderPricingDoc) {
		this.lineCharges.clear();
		this.lineTaxes.clear();
		
		for (Node node : AppModule.NodeListIterable(orderPricingDoc.getElementsByTagName("OrderLine"))) {
			Element olElement = (Element)node;
			if (olElement.getAttribute("PrimeLineNo").equals(this.primeLineNo)) {
				NodeList lineChargeNodes = olElement.getElementsByTagName("LineCharge");
				for (int j = 0; j < lineChargeNodes.getLength(); j++) {
					Element lcElement = (Element)lineChargeNodes.item(j);
					LineCharge lc = new LineCharge();
					lc.setChargeCategory(lcElement.getAttribute("ChargeCategory"));
					lc.setChargeName(lcElement.getAttribute("ChargeName"));
					if (lcElement.hasAttribute("ChargePerLine")) {
						lc.setIsPerLine(true);
						lc.setChargeValue(lcElement.getAttribute("ChargePerLine"));
					} else {
						lc.setIsPerLine(false);
						lc.setChargeValue(lcElement.getAttribute("ChargePerUnit"));
					}
					
					this.lineCharges.add(lc);
				}
				
				updateLineTaxes(olElement);
				
				break;
			}
		}
	}*/
	
/*	public void setMockCharges() {
		this.lineCharges.clear();
		this.lineTaxes.clear();
		
		LineCharge lc1 = new LineCharge();
		lc1.setChargeCategory("LineInfo");
		lc1.setChargeName("LineMerchEffective");
		lc1.setIsPerLine(true);
		lc1.setChargeValue("50.00");
		this.lineCharges.add(lc1);
					
		LineCharge lc2 = new LineCharge();
		lc2.setChargeCategory("LineCharges");
		lc2.setChargeName("LineShippingEffective");
		lc2.setIsPerLine(true);
		lc2.setChargeValue("10.50");
		this.lineCharges.add(lc2);
	}*/
	
	/*private void updateLineTaxes(Element lineCharge) {
		for (Node taxNode : AppModule.NodeListIterable(lineCharge.getElementsByTagName("LineTax"))) {
			Element ltElement = (Element)taxNode;
			LineTax lt = new LineTax();
			lt.setChargeCategory(ltElement.getAttribute("ChargeCategory"));
			lt.setChargeName(ltElement.getAttribute("ChargeName"));
			lt.setTax(ltElement.getAttribute("Tax"));
			lt.setTaxName(ltElement.getAttribute("TaxName"));
			if (ltElement.hasAttribute("TaxPercentage")) {
				lt.setTaxPercentage(ltElement.getAttribute("TaxPercentage"));
			}
			
			this.lineTaxes.add(lt);
		}
	}*/
	
	// ====================================================
	
	public String getGiftWrap() {
		return giftWrap;
	}

	public void setGiftWrap(String giftWrap) {
		this.giftWrap = giftWrap;
	}
	
	public String getExtnVASGiftWrapCode() {
		return extnVASGiftWrapCode;
	}

	public void setExtnVASGiftWrapCode(String extnVASGiftWrapCode) {
		this.extnVASGiftWrapCode = extnVASGiftWrapCode;
	}
	
	public String getLevelOfService() {
		return levelOfService;
	}

	public void setLevelOfService(String levelOfService) {
		this.levelOfService = levelOfService;
	}

	public String getRushShipping() {
		return rushShipping;
	}

	public void setRushShipping(String rushShipping) {
		this.rushShipping = rushShipping;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	
	
    public String getItemId() {
    	return item.getItemId();
    }
    
    public String getItemDescription() {
    	return item.getDesc();
    }
    
    public String getItemProductLine() {
    	return item.getProductLine();
    }

	public String getShipNode() {
		return shipNode;
	}

	public String getCarrierServiceCode() {
		return carrierServiceCode;
	}

	public String getScac() {
		return scac;
	}

	public String getFulfillmentType() {
		return fulfillmentType;
	}

	public String getContainerNo() {
		return containerNo;
	}
	
	public String getTrackingNo(){
		return trackingNo;
	}
	
    public String getPrimeLineNo() {
		return primeLineNo;
	}

    public String getSubLineNo() {
		return subLineNo;
	}

    public String getDeliveryMethod() {
		return deliveryMethod;
	}

    public String getReservationId() {
		return reservationId;
	}

    public String getShipmentLineKey(){
		return this.shipmentLineKey;
	}

    public String getShipmentLineNo(){
		return this.shipment_line_no;
	}

    public String getOrderLineType() {
		return orderLineType;
	}

    public String getQuantity() {
		return quantity;
	}

    public String getOrderLineKey(){
		return this.orderLineKey;
	}

	public String getFulFillmentQuantity(){
		return this.fulFillmentQuantity;
	}
	
	public void setShipNode(String shipNode) {
		this.shipNode = shipNode;
	}

	public void setCarrierServiceCode(String carrierServiceCode) {
		this.carrierServiceCode = carrierServiceCode;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public void setFulfillmentType(String fulfillmentType) {
		this.fulfillmentType = fulfillmentType;
	}
    
    // TODO does this violate Law of Demeter? caller knows there is a list of item ids.
   	

	public void setContainerNoIfNotSet(String containerNo) {
		if (this.containerNo == null ) { this.containerNo = containerNo; }
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	
	public void setTrackingNo(String trackingNo){
		this.trackingNo = trackingNo;
	}
	
	public void setPrimeLineNoIfNotSet(String primeLineNo) {
		if (this.primeLineNo == null ) { this.primeLineNo = primeLineNo; }
	}

	public void setPrimeLineNo(String primeLineNo) {
		this.primeLineNo = primeLineNo;
	}

	public void setSubLineNo(String subLineNo) {
		this.subLineNo = subLineNo;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public void setShipmentLineKey(String shipmentLineKey){
		this.shipmentLineKey = shipmentLineKey;
	}

	public void setShipmentLineNo(String shipment_line_no){
		this.shipment_line_no = shipment_line_no;
	}

	public void setOrderLineType(String orderLineType){
		this.orderLineType = orderLineType;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
		this.fulFillmentQuantity = quantity;
	}

	public void setOrderLineKey(String key){
		this.orderLineKey = key;
	}

	public void setFulFillmentQuantity(String quantity){
		this.fulFillmentQuantity = quantity;
	}

	public String getKitCode() {
		return kitCode;
	}

	public void setKitCode(String kitCode) {
		this.kitCode = kitCode;
	}

	public String getIsBundleParent() {
		return IsBundleParent;
	}

	public void setIsBundleParent(String isBundleParent) {
		IsBundleParent = isBundleParent;
	}

	public String getParentPrimeLineNo() {
		return this.parent.getPrimeLineNo();
	}


	public String getParentSubLineNo() {
		return this.parent.getSubLineNo();
	}

	
	public void setParent(OrderLine ol){
		this.parent = ol;
	}
	
	public OrderLine getParent(){
		return this.parent;
	}

	public String getIsDropShipContainer() {
		return isDropShipContainer;
	}

	public void setIsDropShipContainer(String isDropShipContainer) {
		this.isDropShipContainer = isDropShipContainer;
	}
	
	public void setAction(String action){
		this.action = action;
	}
	
	public String getAction(){
		return this.action;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		addressChanged = true;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		addressChanged = true;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
		addressChanged = true;
	}

	public String getCity() {
		return city;
		
	}

	public void setCity(String city) {
		this.city = city;
		addressChanged = true;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		addressChanged = true;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		addressChanged = true;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		addressChanged = true;
	}
	
	public boolean getAddressChanged(){
		return this.addressChanged;
	}

	public String getLineTax() {
		return lineTax;
	}

	public void setLineTax(String lineTax) {
		this.lineTax = lineTax;
	}

	public String getTaxAmount(String amount, String tax) {
		return new Float(
				(Float.parseFloat(this.getQuantity()) * Float.parseFloat(amount)) * (Float.parseFloat(tax) / 100))
						.toString();
	}

	public String getLineTotalTax() {
		return (new Double(Math.round(new Float(Float.parseFloat(getTaxAmount(this.shippingSurcharge, this.lineTax))
				+ Float.parseFloat(getTaxAmount(this.shippingEffective, this.lineTax))
				+ Float.parseFloat(getTaxAmount(this.merchEffective, this.lineTax)))  * 100.0)/100.0)).toString();
	}

	public String getLineTotalPrice() {
		return new Float(Float.parseFloat(quantity) * Float.parseFloat(unitPrice)
				+ Float.parseFloat(this.shippingEffective) + Float.parseFloat(this.merchEffective) + Float.parseFloat(this.shippingSurcharge)).toString();
	}

	public String getUnitPrice(){
		return unitPrice;
	}
	
	public void setUnitPrice(String unitPrice){
		this.unitPrice = unitPrice;
	}
	
	public String getRetailPrice(){
		return this.retailPrice;
	}
	
	public void setRetailPrice(String retailPrice){
		this.retailPrice = retailPrice;
	}
	
	public String getShippingSurcharge(){
		return this.shippingSurcharge;
	}
	
	public void setShippingSurcharge(String shippingSurcharge){
		this.shippingSurcharge = shippingSurcharge;
	}
	
	public String getShippingCharge(){
		return this.shippingCharge;
	}
	
	public void setShippingCharge(String shippingCharge){
		this.shippingCharge = shippingCharge;
	}
	
	public String getShippingEffective(){
		return this.shippingEffective;
	}
	
	public void setShippingEffective(String shippingEffective){
		this.shippingEffective = shippingEffective;
	}
	
	public String getMonoPZCharge(){
		return this.monoPZCharge;
	}
	
	public void setMonoPZCharge(String monoPZCharge){
		this.monoPZCharge = monoPZCharge;
	}

	public String getMerchEffective(){
		return this.merchEffective;
	}
	
	public void setMerchEffective(String merchEffective){
		this.merchEffective = merchEffective;
	}

	public String getExtnVASMono() {
		return extnVASMono;
	}

	public void setExtnVASMono(String extnVASMono) {
		this.extnVASMono = extnVASMono;
	}
	
	public boolean getShipmentConfirmed(){
		return this.shipmentConfirmed;
	}
	
	public void setShipmentConfirmed(boolean shipmentConfirmed){
		this.shipmentConfirmed = shipmentConfirmed;
	}
	
	public String getDropShipPrimeLineNo(){
		return this.dropShipPrimeLineNo;
	}
	
	public void setDropShipPrimeLineNo(String dropShipPrimeLineNo){
		this.dropShipPrimeLineNo = dropShipPrimeLineNo;
	}
	
	public String getDropShipSubLineNo(){
		return this.dropShipSubLineNo;
	}
	
	public void setDropShipSubLineNo(String dropShipSubLineNo){
		this.dropShipSubLineNo = dropShipSubLineNo;
	} 
	
	public void addChild(OrderLine line){
		if(children == null){
			children = new ArrayList<OrderLine>();
		}
		children.add(line);
	}
	
	
	
	private String shipToAddressType;
	
	public String getShipToAddressType() {
		if (this.shipToAddressType == null) {
			this.setShipToAddressType("RS");
		}
		
		return this.shipToAddressType;
	}
	
	public void setShipToAddressType(String value) {
		this.shipToAddressType = value;
	}
	
	public String getReceivingNode() {
		return this.receivingNode;
	}
	
	public void setReceivingNode(String receivingNode) {
		this.receivingNode = receivingNode;
	}
	public String getExtnLOS() {
		return extnLOS;
	}
	public void setExtnLOS(String extnLOS) {
		this.extnLOS = extnLOS;
	}
	public String getExtnVASPZ() {
		return extnVASPZ;
	}
	public void setExtnVASPZ(String extnVASPZ) {
		this.extnVASPZ = extnVASPZ;
	}
	public String getIsDeluxe() {
		return isDeluxe;
	}
	public void setIsDeluxe(String isDeluxe) {
		this.isDeluxe = isDeluxe;
	}
	
}
