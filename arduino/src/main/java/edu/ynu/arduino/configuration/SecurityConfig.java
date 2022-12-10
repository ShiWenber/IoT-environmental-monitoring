//package edu.ynu.arduino.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
////    @Bean
////    UserDetailsService userDetailsService() {
////        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
////        users.createUser(User.withUsername("swb").password("{noop}123").roles("USER").build());
////        users.createUser(User.withUsername("admin").password("{noop}123").roles("ADMIN").build());
////        return users;
////    }
//
////    @Resource
////    UserService userService;
////
////    @Bean
////    UserDetailsService userDetailsService() {
////        return userService;
////    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        忽略认证的请求，也可以写到application.properties中
//        var getIgnoreToken = new String[] {"/doc.html", "/swagger-ui/**", "/webjars/**", "/v3/**"};
//        http.authorizeRequests()
//                .antMatchers(getIgnoreToken).permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin(Customizer.withDefaults())
////                .loginPage("/login")自定义登录页,需要去除Customizer.withDefaults(),表示不使用默认的登录页
//                .anonymous()
//                .and().csrf().disable();
////        如果不将csrf禁用,则会报错(上传文件时)
//        return http.build();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
