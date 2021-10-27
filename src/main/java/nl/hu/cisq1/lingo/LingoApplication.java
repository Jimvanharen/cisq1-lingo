package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.trainer.domain.Hint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LingoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LingoApplication.class, args);
    }

    /*
    @Deprecated
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/turn").allowedOrigins("http://localhost:3000");
            }
        };
    }
     */
}
