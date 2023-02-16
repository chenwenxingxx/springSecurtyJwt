package com.chenwenxing.springsecurityjwt.config;


import com.chenwenxing.springsecurityjwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtAccessDeniedHandle jwtAccessDeniedHandle;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    JWTLogoutSuccessHandle jwtLogoutSuccessHandle;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    LoginFailHandle loginFailHandle;

    @Autowired
    MyFilterInvocation myFilterInvocation;

    @Autowired
    MyAccess myAccess;



    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter authenticationFilter=new JwtAuthenticationFilter(authenticationManager());
        return authenticationFilter;
    }
    /**
     * 用户输入的密码,跟数据库的密码通过PasswordEncoderd对比是否一致
     * @return
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
      return  new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //定义认证
        auth.userDetailsService(userService);
    }

    /**
     * FilterSecurityInterceptor 负责权限校验过滤器
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/logout","/favicon")
                .permitAll()
                .anyRequest()
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(myFilterInvocation);
                        object.setAccessDecisionManager(myAccess);
                        return object;
                    }
                })
                .and()

                .formLogin()
                .failureHandler(loginFailHandle)
                .successHandler(loginSuccessHandler)
                .and()

                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandle)
                .and()

                .sessionManagement()
                //禁用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandle)

                .and()
                .cors()
                .and()
                .csrf().disable()
                .addFilter(jwtAuthenticationFilter());


    }
}
