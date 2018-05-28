package com.custom.security;


import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	@Bean 
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails());

	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.tokenStore(tokenStore());
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(endpoints.getTokenStore());
		tokenServices.setSupportRefreshToken(false);
		tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
		tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
		tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30天
		endpoints.tokenServices(tokenServices);
	}



	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("permitAll()");
		oauthServer.allowFormAuthenticationForClients();
	}
}
