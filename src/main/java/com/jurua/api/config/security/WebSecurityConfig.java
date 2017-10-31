package com.jurua.api.config.security;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

    //@Bean
    //public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    //    return new JwtAuthenticationTokenFilter();
    //}
    //
    //@Override
    //protected void configure(HttpSecurity http) throws Exception {
    //    http.csrf().disable()
    //            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    //            .authorizeRequests()
    //            .antMatchers(HttpMethod.GET,"/", "/home", "/swagger-ui.html").permitAll()
    //            .antMatchers("/TestController/**").permitAll().anyRequest().authenticated().and()
    //            //.formLogin().loginPage("/TestController/login").permitAll().and()
    //            .logout().permitAll();
    //    http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    //    http.headers().cacheControl();
    //}
    //
    //@Override
    //public void configure(WebSecurity web) throws Exception {
    //    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    //}
    //
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    //}
}