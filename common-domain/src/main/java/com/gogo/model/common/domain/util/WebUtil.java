package com.gogo.model.common.domain.util;

import com.gogo.model.common.domain.constants.SecurityConstants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static com.gogo.model.common.domain.constants.SecurityConstants.AUTHORIZATION_TAG;
import static com.gogo.model.common.domain.constants.SecurityConstants.PREFIX_BEARER;

/**
 * Utility class for web invocations
 */
public class WebUtil {

    /**
     * Get the JWT token
     */
    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String token = request.getHeader(AUTHORIZATION_TAG);
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (SecurityConstants.JWT_TOKEN_NAME.equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }

        if (token != null) {
            token = token.replace(PREFIX_BEARER, "");
        }

        return token;
    }
}
