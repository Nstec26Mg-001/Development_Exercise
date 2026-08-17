package com.example.fullness.stationary.config;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // uploadDir末尾にスラッシュがあるか確認して正規化
        String location = uploadDir.endsWith("/") ? uploadDir : uploadDir + "/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + location)
                .setCachePeriod(0); // 開発中はキャッシュ無効化(本番では適宜調整)
    }
}
