package com.greenlight.shop.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.greenlight.shop.dto.TokenData;


public class UserToken {
	public static TokenData getTokenData() {
		TokenData data = (TokenData) SecurityContextHolder.getContext().getAuthentication().getDetails();
		return data;
	}
	
	private static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
