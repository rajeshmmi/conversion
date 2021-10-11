package za.org.mmiholding.conversion;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ConversionApplication {


    private static final Logger logger =  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()) ;

    public static void main(String[] args) {
        logger.info("Application execution started .............");
        SpringApplication.run(ConversionApplication.class, args);
    }

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

}