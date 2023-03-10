package com.greenlight.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.greenlight.shop.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailService userService;
	
	String[] resourceUrl = new String[] {"/css/*","/js/*","/items/*","/img/**","/images/**","/favicon.ico","/fonts/*","/*"};
	String[] restApi = new String[] {"/api/v1/**","/api/*"};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userService;
	}
	
	public AccessDeniedHandler accessDeniedHandler(){
	    return new AdminAccessDeniedHandler();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(resourceUrl).permitAll()
		.antMatchers(restApi).permitAll()
		.antMatchers("/admin/login").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/admin/login").loginProcessingUrl("/admin/adminLogin")
		.and().logout().logoutUrl("/logout")
		.and()
		.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
		
		//.defaultAccessDeniedHandlerFor(new AdminAccessDeniedHandler(), new AntPathRequestMatcher("/admin/*"));
		
	}

//	@Bean
//    public AccessDecisionManager accessDecisionManager() {
//        // @formatter: off
//        List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(new WebExpressionVoter(), new RoleVoter(), new AuthenticatedVoter(), new MinuteBasedVoter());
//        // @formatter: on
//        return new UnanimousBased(decisionVoters);
//    }
	
	
}
