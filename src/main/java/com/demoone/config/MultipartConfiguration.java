/**
 *
 */
package com.demoone.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * springboot里默认使用tomcat的上传文件大小限制，即1MB
 *
 * @author macula
 *
 * @created 2017-01-18 下午3:33:38
 * @version $Revision: 1.0 $
 */

@Configuration
@Slf4j
public class MultipartConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        log.info("MultipartConfigElement.multipartConfigElement : init");
        factory.setMaxFileSize("1024MB");
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

}