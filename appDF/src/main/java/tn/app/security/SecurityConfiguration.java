package tn.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(encodingPassword());
		auth.inMemoryAuthentication().withUser("user").password("Pass").roles("USER");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().formLogin().permitAll();
		http.cors();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll()
        .anyRequest().authenticated().and().httpBasic();
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	/*@Bean
	public BCryptPasswordEncoder encodingPassword() {
		return new BCryptPasswordEncoder();
	} */


}
