package hcmute.ALOHCMUTE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import hcmute.ALOHCMUTE.config.CustomSiteMeshFilter;
import hcmute.ALOHCMUTE.config.StorageProperties;
import hcmute.ALOHCMUTE.services.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AlohcmuteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlohcmuteApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<CustomSiteMeshFilter> siteMeshFilter(){
		
		FilterRegistrationBean<CustomSiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<CustomSiteMeshFilter>();
		filterRegistrationBean.setFilter(new CustomSiteMeshFilter()); // adding sitemesh filter ??
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
}
