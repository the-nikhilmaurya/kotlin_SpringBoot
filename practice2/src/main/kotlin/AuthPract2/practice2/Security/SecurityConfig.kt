package AuthPract2.practice2.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val customUser: CustomUserDetails,
    val authenticationEntryPoint:AuthenticationEntryPoint,
    val authenticationFilter: AuthenticationFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain  {
        http

            .csrf{ it.disable()}
            .exceptionHandling { it.authenticationEntryPoint(authenticationEntryPoint) }   // for handlig exception
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }  // for using jwt
            .authorizeHttpRequests { it.requestMatchers("/api/public/**").permitAll() }
            .authorizeHttpRequests{ it.requestMatchers("/api/private/**").hasAuthority("USER") }
            .authorizeHttpRequests{ it.anyRequest().authenticated() }
            .logout { it.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").permitAll(); }
            .httpBasic{}
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return  http.build()
    }



//    @Bean
//    fun users(service: EmployeeService):UserDetailsService{
//
//        val admin: UserDetails = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build()
//
//        val user: UserDetails = User.builder()
//            .username("user")
//            .password("{noop}user")
//            .roles("USER")
//            .build()
//
//        return  InMemoryUserDetailsManager(admin,user)
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(auth:AuthenticationConfiguration): AuthenticationManager {
        return auth.getAuthenticationManager()
    }


}