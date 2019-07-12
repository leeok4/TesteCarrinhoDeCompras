package teste.carrinho.config;

import teste.carrinho.autenticadores.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		//Para o usuario no Banco de dados.
		auth.userDetailsService(myDBAauthenticationService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// As paginas precisam de login como EMPLOYEE ou MANAGER.
		// Se nao estiver logado vai ser direcionado a pagina de login.
		http.authorizeRequests().antMatchers("/orderList", "/order", "/accountInfo")//
				.access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

		// Somente para MANAGER.
		http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_MANAGER')");

		// Quando o usuário tiver logado como XX.
		// Mas acesse uma página que requer o papel YY,
		// AccessDeniedException será lançado.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin()//
				// Envie o URL da página de login.
				.loginProcessingUrl("/j_spring_security_check") // Envia URL
				.loginPage("/login")//
				.defaultSuccessUrl("/accountInfo")//
				.failureUrl("/login?error=true")//
				.usernameParameter("userName")//
				.passwordParameter("password")
				// Config para a página de logout
				// (Vá para a página inicial).
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

	}
}