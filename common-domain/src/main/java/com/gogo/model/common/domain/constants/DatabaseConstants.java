package com.gogo.model.common.domain.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Database constants
 */
@Component
@ConditionalOnExpression("'${spring.application.name}'=='shopping-ui-main'")
public class DatabaseConstants {

    public static String DB_URL = "jdbc:mysql://localhost:3306/shopping";

    @Value("${spring.datasource.url}")
    public void setDbUrl(String dbUrl) {
        DatabaseConstants.DB_URL = dbUrl;
    }

    public static final String DB_USERNAME = "root";

    public static final String DB_PASSWORD = "password";

    public static final String QUERY_USER_LIST_ALL = "SELECT * FROM user";

    public static final String QUERY_CATEGORY_LIST_ALL = "SELECT * FROM category";

    public static final String QUERY_BRAND_LIST_ALL = "SELECT * FROM brand";

    public static final String QUERY_PRODUCT_LIST_ALL = "SELECT * FROM product";

    public static final String QUERY_PRODUCT_IMAGE_LIST_ALL = "SELECT * FROM product_image";

    public static final String QUERY_USER_PATH_ALREADY_SET = "SELECT count(1) FROM user WHERE image like '%/Users/%';";

    public static final String QUERY_CATEGORY_PATH_ALREADY_SET = "SELECT count(1) FROM category WHERE image like '%/Users/%';";

    public static final String QUERY_BRAND_PATH_ALREADY_SET = "SELECT count(1) FROM brand WHERE image like '%/Users/%';";

    public static final String QUERY_PRODUCT_PATH_ALREADY_SET = "SELECT count(1) FROM product WHERE main_image like '%/Users/%';";
}
