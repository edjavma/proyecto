package cgc.talonarios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/ver/**").access("hasRole('ROLE_LECTURA') or hasRole('ROLE_ESCRITURA')")
				.antMatchers("/descarga/**").access("hasRole('ROLE_LECTURA') or hasRole('ROLE_ESCRITURA')")
				.antMatchers("/filtro/**").access("hasRole('ROLE_LECTURA')")
				.antMatchers("/busqueda/**").access("hasRole('ROLE_ESCRITURA')")
				.antMatchers("/guardar/**").access("hasRole('ROLE_ESCRITURA')")
				.antMatchers("/listar/**").access("hasRole('ROLE_ESCRITURA')")
				.antMatchers("/role/**").access("hasRole('ROLE_ADMIN')")		
				.antMatchers("/usuario/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/asignar/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/password/**").authenticated()
				.antMatchers("/welcome/**").authenticated()
				.antMatchers("/principal.jsp").authenticated()
				.antMatchers("/principal.jsp#!/").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

     /*   auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");*/
    	 auth.userDetailsService(this.userDetailsService);
    	 auth.authenticationProvider(authenticationProvider());
    }

    
    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**","/app/**");
    }
    
    
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
