package org.wsi.module.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public interface TestObject {

	public static final Logger logger = LoggerFactory.getLogger(TestObject.class.getName());
											
    public static final String DEFAULT_ORGANIZATION_CODE = "PB";
    public static final String DEFAULT_PRODUCT_LINE = "FN";
    public static final String DEFAULT_SHIP_NODE = "LAXDTC";
   // public static final String DEFAULT_PRIMARY_SUPPLIER = "VDR_539";
    public static final String DEFAULT_MANUFACTURER_NAME = "";
    public static final String DEFAULT_AVAILABILITY = "TRACK";
    public static final String DEFAULT_ETA = "";
    public static final String DEFAULT_SUPPLY_TYPE = "ONHAND";
    public static final String DEFAULT_FULFILLMENT_TYPE = "FT_SAC";
    public static final String DEFAULT_CARRIER_SERVICE_CODE = "FURNITURE_REGULAR";
    public static final String DEFAULT_SCAC = "HW_CARRIER";

    public static final String ORDER_STATUS_SHIPPED = "3700";
    public static final String ORDER_STATUS_AWAITING_DS_PO_CREATION = "1600";
	
	default public void debug(String message) {
		logger.debug(message);
	}
	
	default public boolean isLiveBrand(String brand) {
		return "PT".equals(brand) || "MG".equals(brand) || "PK".equals(brand) || "WE".equals(brand);
	}

	default public boolean isNonLiveBrand(String brand) {
		return !isLiveBrand(brand);
	}

}
