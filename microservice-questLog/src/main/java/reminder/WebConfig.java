package reminder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // เปิดให้ทุก endpoint สามารถเข้าถึงได้
                .allowedOrigins("http://localhost:3000")  // ระบุ URL ของ frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // ระบุ method ที่อนุญาต
                .allowedHeaders("*")  // อนุญาตให้ใช้ header ทุกประเภท
                .allowCredentials(true);  // เปิดให้ใช้ credentials
    }
}