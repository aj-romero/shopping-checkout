package com.ajromero.webapp.spring;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan({ "com.ajromero.webapp.web"})
public class ShoppingWebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final Optional<HttpMessageConverter<?>> converterFound = converters.stream()
                .filter(AbstractJackson2HttpMessageConverter.class::isInstance)
                .findFirst();
        if (converterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter =
                    (AbstractJackson2HttpMessageConverter) converterFound.get();
            converter.getObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper()
                    .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            converter.getObjectMapper()
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
    }
}
