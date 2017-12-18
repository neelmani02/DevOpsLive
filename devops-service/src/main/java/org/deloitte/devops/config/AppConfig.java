package org.deloitte.devops.config;


import org.deloitte.devops.jira.integration.JiraIntegration;
import org.deloitte.devops.jira.service.JiraService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.deloitte.devops")
public class AppConfig extends WebMvcConfigurerAdapter{
 
    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
    
   /* @Bean
    public InternalResourceViewResolver viewResolver() {
    	InternalResourceViewResolver vr = new InternalResourceViewResolver();
    	vr.setPrefix("/WEB-INF/views/jsp/");
    	vr.setSuffix(".jsp");
    	return vr;
    	
    }*/
    
    @Bean
    public static PropertyPlaceholderConfigurer properties(){
       PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
       Resource[] resources = new ClassPathResource[ ]
          { new ClassPathResource( "application.properties" ) };
       ppc.setLocations( resources );
       ppc.setIgnoreUnresolvablePlaceholders( true );
       return ppc;
    }
 
    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
     
    
    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
   /* <bean id="environment"
    	    class="org.deloitte.devops.config.EnivironmentConfig"></bean>
    	 <bean id="service"
    	    class="org.deloitte.devops.jira.service.JiraService"></bean>
    	 <bean id="integration"
    	    class="org.deloitte.devops.jira.integration.JiraIntegration"></bean>*/
     
    
  /*  @Bean
    public EnivironmentConfig environment() {
        return new EnivironmentConfig();
    }
    @Bean
    public JiraService jiraService() {
        return new JiraService();
    }
    @Bean
    public JiraIntegration jiraIntegration() {
        return new JiraIntegration();
    }*/
}