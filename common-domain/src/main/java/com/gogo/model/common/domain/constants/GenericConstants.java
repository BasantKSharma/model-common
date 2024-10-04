package com.gogo.model.common.domain.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenericConstants {


    public static final String APPLICATION_NAME = "TODO";

    public static String ABSOLUTE_FILE_PATH_IMAGE_FOLDER = "/Users/basantsharma/DriveB/workspace/java/shopping-app/shopping-reference/image/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER =
            ABSOLUTE_FILE_PATH_IMAGE_FOLDER.substring(ABSOLUTE_FILE_PATH_IMAGE_FOLDER.indexOf(APPLICATION_NAME) + APPLICATION_NAME.length());

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_GENERIC = RELATIVE_FILE_PATH_IMAGE_FOLDER + "generic/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_USER = RELATIVE_FILE_PATH_IMAGE_FOLDER + "user/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_CUSTOMER = RELATIVE_FILE_PATH_IMAGE_FOLDER + "customer/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_ADDRESS = RELATIVE_FILE_PATH_IMAGE_FOLDER + "customer/address/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_PRODUCT = RELATIVE_FILE_PATH_IMAGE_FOLDER + "product-catalog/product/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_CATEGORY = RELATIVE_FILE_PATH_IMAGE_FOLDER + "product-catalog/category/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_BRAND = RELATIVE_FILE_PATH_IMAGE_FOLDER + "product-catalog/brand/";

    public static final String RELATIVE_FILE_PATH_IMAGE_FOLDER_SETTING = RELATIVE_FILE_PATH_IMAGE_FOLDER + "setting/";

    public static final String ABSOLUTE_FILE_PATH_IMAGE_FOLDER_USER = ABSOLUTE_FILE_PATH_IMAGE_FOLDER + "user";

    public static final String ABSOLUTE_FILE_PATH_IMAGE_FOLDER_CATEGORY = ABSOLUTE_FILE_PATH_IMAGE_FOLDER + "product-catalog/category/";

    public static final String ABSOLUTE_FILE_PATH_IMAGE_FOLDER_BRAND = ABSOLUTE_FILE_PATH_IMAGE_FOLDER + "product-catalog/brand/";

    public static final String ABSOLUTE_FILE_PATH_IMAGE_FOLDER_PRODUCT = ABSOLUTE_FILE_PATH_IMAGE_FOLDER + "product-catalog/product/";

    public static final String ABSOLUTE_FILE_PATH_IMAGE_FOLDER_SETTING = ABSOLUTE_FILE_PATH_IMAGE_FOLDER + "setting";

    public static final String FILE_PATH_EXTRAS = "extras/";

    public static final String DEFAULT_EXTENSION_IMAGE = ".png";

    public static final String DEFAULT_IMAGE = "unknown.png";

    public static final String DEFAULT_IMAGE_USER = "unknown_user";

    public static final String PARAM_USER_ID = "userId";

    public static final String PARAM_ENTITY_ID = "entityId";

    public static final String CUSTOM_PROPERTY_IMAGE_NAMES = "imageNames";

    public static Integer ENTITIES_PER_PAGE = 10;

    public static Integer PRODUCTS_PER_PAGE = 10;

    public static Integer CUSTOMERS_PER_PAGE = 20;

    public static Integer USERS_PER_PAGE = 20;

    public static Integer SEARCH_RESULTS_PER_PAGE = 10;

    public static final String PREFIX_CHILD = "--";

    public static final String FORWARD_SLASH = "/";

    public static final String NEW_LINE = "\n";

}
