package org.wsi.module.objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wsi.module.Modules;

public interface Item extends TestObject {

	public void save() throws Exception;

	public Item clone();

	public void adjustInventory(String shipNode, Integer value);
	
	public void adjustInventory(String shipNode, Integer value, String availability, String supplyType, String eta);



	public String getItemId();

	public void setItemId(String id);

	public String getComponentItemId(Integer index);

	public String getOrganizationCode();

	public String getProductLine();

	public void setOrganizationCode(String organizationCode);

	public void setProductLine(String productLine);
	
	public String getReservationId();

	public String getAction();

	public String getUnitOfMeasure();

	public String getInfiniteInv();

	public String getProductClass();

	public String getEndDate();

	public String getStartDate();

	public String getFreezer();

	public String getKitCode();

	public String getManufacturer();
	
	public String getPrimarySupplier();

	public String getSupplier();

	public String getStatus();

	public String getHeight();

	public String getHeightMeasure();

	public String getLength();

	public String getLengthMeasure();

	public String getWeight();

	public String getWeightMeasure();

	public String getWidth();

	public String getWidthMeasure();

	public String getDesc();

	public String getIsPickupAllowed();

	public String getNotificationTime();

	public String getMonitorRule();

	public String getLeadTime();

	public String getMinNotificationTime();

	public String getSafetyFactor();

	public String getTimeSensitive();

	public String getUnplanned();

	public String getNodeLevelInventoryMonitorRule();

	public String getExtACII();

	public String getExtServiceCode();

	public String getExtClass();

	public String getExtDeliverBy();

	public String getExtDept();

	public String getExtDirectShip();

	public String getExtBuffer();

	public String getExtGddEnd();

	public String getExtGddStart();

	public String getExtIsDtc();

	public String getExtIsRtlFulfill();

	public String getExtIsRtl();

	public String getExtOom();

	public String getExtShipDate();

	public String getExtScac();

	public String getExtShipMode();

	public String getExtSubClass();

	public String getExtDropEnd();

	public String getExtDropStart();

	public String getExtItem();

	public String getExtCarton();

	public String getExtOptimized();
	
	public String getProcessingTime();
	
	public String getKitQuantity();

	public void setReservationId(String reservationId);
	
	public void setAction(String action);

	public void setUnitOfMeasure(String unit_of_measure);

	public void setInfiniteInv(String infinite_inv);

	public void setProductClass(String product_class);

	public void setEndDate(String end_date);

	public void setStartDate(String start_date);

	public void setFreezer(String freezer);

	public void setKitCode(String kit_code);

	public void setManufacturer(String manufacturer);

	public void setSupplier(String supplier);

	public void setStatus(String status);

	public void setHeight(String height);

	public void setHeightMeasure(String height_measure);

	public void setLength(String length);

	public void setLengthMeasure(String length_measure);

	public void setWeight(String weight);

	public void setWeightMeasure(String weight_measure);

	public void setWidth(String width);

	public void setWidthMeasure(String width_measure);

	public void setDesc(String desc);

	public void setIsPickupAllowed(String is_pickup_allowed);

	public void setNotificationTime(String notification_time);

	public void setMonitorRule(String monitor_rule);

	public void setLeadTime(String lead_time);

	public void setMinNotificationTime(String min_notification_time);

	public void setSafetyFactor(String safety_factor);

	public void setTimeSensitive(String time_sensitive);

	public void setUnplanned(String unplanned);

	public void setNodeLevelInventoryMonitorRule(String node_level_inventory_monitor_rule);

	public void setExtACII(String ext_acii);

	public void setExtServiceCode(String ext_service_code);

	public void setExtClass(String ext_class);

	public void setExtDeliverBy(String ext_deliver_by);

	public void setExtDept(String ext_dept);

	public void setExtDirectShip(String ext_direct_ship);

	public void setExtBuffer(String ext_buffer);

	public void setExtGddEnd(String ext_gdd_end);

	public void setExtGddStart(String ext_gdd_start);

	public void setExtIsDtc(String ext_is_dtc);

	public void setExtIsRtlFulfill(String ext_is_rtl_fulfill);

	public void setExtIsRtl(String ext_is_rtl);

	public void setExtOom(String ext_oom);

	public void setExtShipDate(String ext_ship_date);

	public void setExtScac(String ext_scac);

	public void setExtShipMode(String ext_ship_mode);

	public void setExtSubClass(String ext_sub_class);

	public void setExtDropEnd(String ext_drop_end);

	public void setExtDropStart(String ext_dro_start);

	public void setExtItem(String ext_item);

	public void setExtCarton(String ext_carton);

	public void setExtOptimized(String ext_optimized);
	
	public void setKitQuantity(Integer kitQty);

	public void setExtnDirectShipInd(String extnDirectShipInd);
	public String getExtnDirectShipInd();
	
	public void setPrimarySupplier(String primarySupplier);
	
	public void setProcessingTime(String processingTime);
	
	public String getIsShippingAllowed();
	
	public void setIsShippingAllowed(String shipping);
	
	public String getATPRule();
	
	public void setATPRule(String atp);
	
	public String getBundleFulfillmentMode();
	
	public void setBundleFulfillmentMode(String bundle_fulfillment_mode);
	
	public void saveInventory(boolean isFlow) throws Exception;

	public String getCarrierServiceCode();
	
	public void setCarrierServiceCode(String carrierServiceCode);

	
}
