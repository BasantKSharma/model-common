package com.gogo.model.common.domain.constants;

/**
 * Constants for all URL paths
 */
public final class UrlConstants {

    /**
     * Service methods - Generic/All
     * */
    public static final String SERVICE_METHOD_DEFAULT = "/default";

    public static final String SERVICE_METHOD_DETAIL_BY_ID = "/detail/{id}";

    public static final String SERVICE_METHOD_IS_UNIQUE = "/is-unique";

    public static final String SERVICE_METHOD_DELETE_BY_ID = "/delete/{id}";

    public static final String SERVICE_METHOD_FIND_BY_ID = "/id/{id}";

    public static final String SERVICE_METHOD_FIND_BY_IDS = "/find-by-ids";

    public static final String SERVICE_METHOD_CREATE = "/create";

    public static final String SERVICE_METHOD_EDIT = "/edit";

    public static final String SERVICE_METHOD_SAVE = "/save";

    public static final String SERVICE_METHOD_DELETE = "/delete";

    public static final String SERVICE_METHOD_DELETE_BY_IDS = "/delete-by-ids";

    public static final String SERVICE_METHOD_EXPORT = "/export";

    public static final String SERVICE_METHOD_SEARCH_USER = "/search/";

    public static final String SERVICE_METHOD_ROOT = "/";

    public static final String SERVICE_METHOD_HOME = "/home";

    public static final String SERVICE_METHOD_LIST = "/list";

    public static final String SERVICE_METHOD_LIST_ALL = "/listAll";

    public static final String SERVICE_METHOD_LIST_BY_PAGE = "/listByPage";

    public static final String SERVICE_METHOD_LIST_BY_PAGE_NUM = "/listByPage/{pageNum}";

    public static final String SERVICE_METHOD_PAGE_NUM = "/page/{pageNum}";

    /**
     * Service methods - Security
     * */
    public static final String SERVICE_METHOD_AUTH = "/auth";

    public static final String SERVICE_METHOD_LOGIN = "/login-page";

    /**
     * Service methods - User/Customer
     * */
    public static final String SERVICE_METHOD_FIND_BY_NAME = "/name/{name}";

    public static final String SERVICE_METHOD_FIND_BY_EMAIL = "/email/{email}";

    public static final String SERVICE_METHOD_LIST_ROLES = "/roles";

    public static final String SERVICE_METHOD_PROFILE = "/profile";

    public static final String SERVICE_METHOD_LOGOUT = "/logout";

    public static final String SERVICE_METHOD_PASSWORD_FORGOT= "/password/forgot";

    public static final String SERVICE_METHOD_PASSWORD_RESET = "/password/reset";

    /**
     * Service methods - Setting
     * */
    public static final String SERVICE_METHOD_CURRENCY_LIST = "/currency/list";

    public static final String SERVICE_METHOD_COUNTRY_LIST = "/country/list";

    public static final String SERVICE_METHOD_STATE_LIST = "/country/state/list";

    public static final String SERVICE_METHOD_FIND_SHIPPING_RATE_BY_ADDRESS = "/address/{countryId}/{stateId}/{city}";

    public static final String SERVICE_METHOD_SAVE_GENERAL= "/general/save";

    public static final String SERVICE_METHOD_SAVE_MAIL_TEMPLATE = "/mail-template/save";

    public static final String SERVICE_METHOD_SAVE_MAIL_SERVER = "/mail-server/save";

    public static final String SERVICE_METHOD_SAVE_PAYMENT = "/payment/save";

    /**
     * Service methods - Product
     * */
    public static final String SERVICE_METHOD_LIST_CATEGORIES_HIERARCHICAL = "/listHierarchicalCategories";

    public static final String SERVICE_METHOD_FETCH_CATEGORY_PARENT = "/fetchCategoryParent";

    public static final String SERVICE_METHOD_LIST_CATEGORY_USED_IN_FORM = "/listCategoryUsedInForm";

    /**
     * Service methods - Order
     * */
    public static final String SERVICE_METHOD_ORDER_COMPLETE = "/complete";

    /**
     * Service methods - Payment
     * */
    public static final String SERVICE_METHOD_PAYMENT_START = "/start";

    public static final String SERVICE_METHOD_PAYMENT_SUCCESS = "/success";

    public static final String SERVICE_METHOD_PAYMENT_FAILURE = "/failure";

    public static final String SERVICE_METHOD_FIND_BY_TXN_ID = "/txn-id/{txn-id}";

    public static final String SERVICE_METHOD_PAYMENT_COMPLETE = "/complete";

}
