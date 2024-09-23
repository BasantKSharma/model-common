package com.gogo.model.common.domain.constants;

import java.util.List;

/**
 * Constants for security module
 */
public final class SecurityConstants {

    public static String FORM_EMAIL = "email";

    public static String FORM_PASSWORD = "password";

    public static String REGISTRATION_ID = "registrationId";

    public static String ENTITY_TYPE = "entityType";

    public static String USER_NAME = "userName";

    public static String FIRST_NAME = "firstName";

    public static String LAST_NAME = "lastName";

    public static String EMAIL = "email";
    public static String USER_ID = "userId";

    public static String ROLES = "roles";

    public static String ROLE_PUBLIC = "Public";

    public static final String DEFAULT_HOME_PAGE = "index";

    public static final String HEADER_ATTRIBUTE_LINKS = "links";

    public static final String DEFAULT_USER_NAME = "anonymousUser";

    public static final String ANONYMOUS_USER_NAME = "anonymousUser";

    public static List<String> DEFAULT_ROLES = List.of("Admin");

    public static final String[] FILES_WHITELIST = {
            "/images/**",
            "**/image/**",
            "/richtext/**",
            "/**/*.ico",
            "/**/js/**",
            "/**/css/**",
            "/webfonts/**",
            "/**/*.png",
            "/**/*.js",
            "/**/*.css",
//            "/customer-ui/customer/forgot-password",
//            "/customer-ui/customer/reset-password",
            "/**/backend/service/api/v1/security/auth",
            "/health",
            "/**/backend/service/api/v1/customer/security/auth",
            "/**/backend/service/api/v1/user/module/list",
            "/**/backend/service/api/v1/setting/list",
            "/**/backend/service/api/v1/customer/password/forgot",
            "/**/backend/service/api/v1/customer/password/reset",
//            "/backend/service/api/v1/customer/password/forgot/action",
            "/backend/service/api/v1/setting/mail/list",
            "/login/oauth2/code/google",
            "/**/password/forgot",
            "/**/password/forgot/action",
            "/**/password/reset",
            "service/customer/password/forgot",
            "service/customer/password/reset",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
    };

    public static final String[] EXTENSION_WHITELIST = {
            ".ico",
            "*.png",
            ".jpg",
            ".js",
            ".css"
    };

    public static final String JWT_TOKEN_NAME = "JWT-TOKEN";

    public static final String AUTHORIZATION_TAG = "Authorization";

    public static final String PREFIX_BEARER = "Bearer ";
}
