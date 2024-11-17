package reminder.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // ครอบคลุมทุก endpoint
                        .allowedOrigins("http://localhost:3000") // อนุญาตให้ localhost:3000 เข้าถึงได้
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // อนุญาตทุก method ที่จำเป็น
                        .allowedHeaders("*") // อนุญาต headers ทั้งหมด
                        .allowCredentials(true); // ถ้าจำเป็นต้องใช้ cookies หรือ credentials
            }
        };
    }
}