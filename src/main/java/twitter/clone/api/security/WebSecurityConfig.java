package twitter.clone.api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import javax.servlet.Filter;
import twitter.clone.api.filters.ExceptionHandlerFilter;
import twitter.clone.api.filters.JwtAuthorizationFIlter;
import twitter.clone.api.services.UserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtAuthorizationFIlter jwtAuthorizationFIlter;
  @Autowired
  ExceptionHandlerFilter exceptionHandlerFilter;
  @Autowired
  UserDetailService userDetailService;

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("**"));
    // configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:4200"));

    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

 

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Configuramos para usar nuestro propio servicio
    auth.userDetailsService(userDetailService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
  

  
    http.csrf().disable().authorizeRequests().antMatchers("/api/authenticate","/api/refreshtoken", "/api/user/register").permitAll()
    .anyRequest().authenticated()
    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        
   // http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
   
    http.addFilterBefore(jwtAuthorizationFIlter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(exceptionHandlerFilter, JwtAuthorizationFIlter.class);
  //  http.cors();


  }
}