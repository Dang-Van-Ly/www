package iuh.fit.xstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Config để serve static files từ /uploads directory và mở khóa CORS cho Frontend Vercel
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // --- GIỮ NGUYÊN CẤU HÌNH LOAD ẢNH CŨ CỦA BẠN ---
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve files từ uploads/ directory
        registry
                .addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/")
                .setCachePeriod(3600);  // Cache 1 giờ

        // Serve comment files từ uploads/comments/ directory
        registry
                .addResourceHandler("/comments/**")
                .addResourceLocations("file:uploads/comments/")
                .setCachePeriod(3600);  // Cache 1 giờ

        // Serve avatar files từ uploads/avatars/ directory
        registry
                .addResourceHandler("/avatars/**")
                .addResourceLocations("file:uploads/avatars/")
                .setCachePeriod(3600);  // Cache 1 giờ
    }

    // --- THÊM ĐOẠN ĐỂ FIX LỖI CORS Ở ĐÂY ---
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép tất cả các đường dẫn API (như /api/products, /api/auth/login...)
                .allowedOrigins("https://x-store-frontend-liard.vercel.app") // Link frontend Vercel của bạn từ log lỗi
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép các phương thức gọi dữ liệu
                .allowedHeaders("*") // Chấp nhận mọi Header truyền lên từ Frontend
                .allowCredentials(true); // Cho phép truyền Token/Cookie xác thực đăng nhập
    }
}