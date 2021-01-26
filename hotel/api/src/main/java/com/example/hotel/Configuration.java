package com.example.hotel;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    /**
     * Code from : https://stackoverflow.com/questions/22568962/how-to-i-get-spring-data-mongodb-to-validate-my-objects/62305301
     * @return
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
