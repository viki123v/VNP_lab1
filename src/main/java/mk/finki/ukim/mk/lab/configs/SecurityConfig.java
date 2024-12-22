package mk.finki.ukim.mk.lab.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain chain1(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .rememberMe(AbstractHttpConfigurer::disable)
            .formLogin(config -> config.loginPage("/login")
                .successHandler(new SimpleUrlAuthenticationSuccessHandler("/listSongs"))
            );
        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationProvider provider) {
        return new ProviderManager(provider);
    }

    @Bean
    public AuthenticationProvider provider(PasswordEncoder encoder, UserDetailsService service) {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(encoder);
        dao.setUserDetailsService(service);
        return dao;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        User.UserBuilder builder = User.builder();

        UserDetails plain = builder
            .username("viki")
            .password(
                encoder.encode("wee")
            ).build();

        UserDetails admin = builder
            .username("admin")
            .password(
                encoder.encode("glaven")
            ).roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(plain, admin);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

}
