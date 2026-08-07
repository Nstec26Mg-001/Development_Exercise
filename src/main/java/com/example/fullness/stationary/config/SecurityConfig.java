package com.example.fullness.stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //
        http
                // アクセス制限の情報 ↓↓↓ここから
                .authorizeHttpRequests(
                        authz -> authz
                                // permitAll -> Full access
                                // authenticated -> 認証済み
                                // denyAll -> すべて拒否
                                // .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/", "/login").permitAll()
                                // .requestMatchers("/menu", "/logout").authenticated()
                                // .requestMatchers("/employee/**").authenticated()
                                // anyRequest -> 上記以外
                                .anyRequest().denyAll())
                // アクセス制限の情報 ↑↑↑ここまで

                // ログインにかかわる情報 ↓↓↓ここから
                .formLogin(login -> login
                        // ログイン時のPOST先URL
                        .loginProcessingUrl("/authenticate")
                        // ログイン画面表示URL
                        .loginPage("/login")
                        // 認証成功時に表示するページURL
                        .defaultSuccessUrl("/menu")
                        // 認証失敗時のリダイレクト先
                        .failureUrl("/login").permitAll())
                // ログインにかかわる情報 ↑↑↑ここまで

                // ログアウトにかかわる情報 ↓↓↓ここから
                .logout(logout -> logout
                        // ログアウト時のURL（POST先）
                        .logoutUrl("/logout")
                        // ログアウト成功時のリダイレクト先
                        .logoutSuccessUrl("/login")
                        // セッションを破棄するか否か
                        .invalidateHttpSession(true)
                        // 認証情報をクリアするか否か
                        .clearAuthentication(true)
                        // 消すCookieの名前
                        .deleteCookies("JSESSIONID"));
        // ログアウトにかかわる情報 ↑↑↑ここまで
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
