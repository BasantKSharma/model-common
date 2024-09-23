package com.gogo.model.common.domain.constants;

/**
 * Constants for mail
 */
public class MailConstants {

    /**
     * Mail settings
     ***/
    public static final String MAIL_PLACEHOLDER_FIRST_NAME = "[[firstName]]";

    public static final String MAIL_PLACEHOLDER_USER_NAME = "[[name]]";

    public static final String MAIL_PLACEHOLDER_VERIFY_URL = "[[URL]]";

    public static final String MAIL_PLACEHOLDER_EXPIRATION_LIMIT = "[[expirationLimit]]";

    public static final String MAIL_PLACEHOLDER_ORDER_ID = "[[orderID]]";

    public static final String MAIL_PLACEHOLDER_ORDER_DATE = "[[orderDate]]";

    public static final String MAIL_PLACEHOLDER_PRODUCT_DETAILS = "[[productDetails]]";

    public static final String MAIL_PLACEHOLDER_TOTAL_AMOUNT = "[[totalAmount]]";

    public static final String MAIL_PLACEHOLDER_SHIPPING_ADDRESS = "[[shippingAddress]]";

    public static final String MAIL_PLACEHOLDER_DELIVERY_DATE = "[[deliveryDate]]";

    public static final String MAIL_PLACEHOLDER_TRACKING_URL= "[[trackingURL]]";

    public static final String[] MAIL_CUSTOMER_VERIFY = new String[]{"CUSTOMER_VERIFY_SUBJECT", "CUSTOMER_VERIFY_CONTENT"};

    public static final String[] MAIL_CUSTOMER_FORGOT_PASSWORD = new String[]{"CUSTOMER_FORGOT_PASSWORD_SUBJECT", "CUSTOMER_FORGOT_PASSWORD_CONTENT"};

    public static final String[] MAIL_CUSTOMER_ORDER_CONFIRMATION = new String[]{"CUSTOMER_ORDER_CONFIRMATION_SUBJECT", "CUSTOMER_ORDER_CONFIRMATION_CONTENT"};

    public static final String[] MAIL_CUSTOMER_ORDER_FAILURE = new String[]{"CUSTOMER_ORDER_FAILURE_SUBJECT", "CUSTOMER_ORDER_FAILURE_CONTENT"};
}
