package com.greenlight.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenlight.shop.domain.Useraccount;
import com.greenlight.shop.dto.TokenData;
import com.greenlight.shop.service.UserServiceImpl;
import com.greenlight.shop.util.Cryption;

import javax.annotation.security.RolesAllowed;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
    UserServiceImpl userService;

    
    @Autowired
    AuthenticationManager authenticationManager;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            	ObjectMapper objectMapper = new ObjectMapper();
            	String subject = tokenProvider.getSubject(jwt);
            	
            	if("".equals(subject)) {
            		filterChain.doFilter(request, response);
            		return;
            	}
            	
            	TokenData tokenData = objectMapper.readValue(subject, TokenData.class); 
            	int userId = Integer.parseInt(Cryption.decryption(tokenData.getUserId()));
            	//System.out.println("User Id " +userId);
            	Useraccount userAccount = userService.getUserAccountById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tokenData.getUserName(), userAccount.getPassword(), userAccount.getAuthorities());
                authentication.setDetails(userAccount);
                //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (BadCredentialsException bce) {
    		bce.printStackTrace();
    	}
        catch (Exception ex) {
        	ex.printStackTrace();
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}