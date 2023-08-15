package br.com.megumin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig() {

    private val authenticationProvider: AuthenticationProvider? = null

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize("**", permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin {  }
            logout {  }
        }
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun users(): UserDetailsService {
        val user = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder().toString())
                .roles("USER")
                .build()
        val admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().toString())
                .roles("USER", "ADMIN")
                .build()
        return InMemoryUserDetailsManager(user, admin)
    }
}