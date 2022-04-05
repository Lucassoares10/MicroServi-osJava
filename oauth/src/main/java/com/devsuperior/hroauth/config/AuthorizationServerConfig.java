package com.devsuperior.hroauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer  //habilitando como authorization server do oauth
//para que a classe possa implementar essa configuração fazer o extends do authorization
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${oauth.client.name}")
	private String clientName;
	
	@Value("${oauth.client.secret}")
	private String clientSecret;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	@Autowired
	private JwtTokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	//metodos sobrescritos do authorization
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	//metodo para configurar as autenticações com base nas credenciais do app e do tipo do grantType
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientName) //nome do app : client_id
		.secret(passwordEncoder.encode(clientSecret)) //password app
		.scopes("read", "write") 
		.authorizedGrantTypes("password") // tipo grantType
		.accessTokenValiditySeconds(86400); //tempo de expiração do token
	}

	//configurar o processamento do token
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)
		.accessTokenConverter(accessTokenConverter);
	}
}
