package com.eprocurement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JasperReportConfig
 */
@Configuration
public class JasperReportConfig {

    @Value("${jasperserver.url}")
    private String serverUrl;

    @Value("${report.url}")
    private String reportUrl;

    @Bean
    public String jasperserverUrl(){
        return serverUrl;
    }

    @Bean
    public String reportUrl(){
        return reportUrl;
    }
    
}