package org.deloitte.devops.config;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
 
@Configuration
public class AppConfig implements WebMvcConfigurer {
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
    
    @Bean
    public static PropertyPlaceholderConfigurer properties(){
       PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
       Resource[] resources = new ClassPathResource[ ]
          { new ClassPathResource( "ext-config.properties" ) };
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
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
    }
    
    @Bean
    public RestTemplate restTemplate() {
    	RestTemplate template = new RestTemplateBuilder().build();
    	return template;
    }
}