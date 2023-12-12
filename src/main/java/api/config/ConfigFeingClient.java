package api.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFeingClient {

    @Bean()
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .logger(new Slf4jLogger(ConfigFeingClient.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(60000, 60000))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new AuthenticationErrorDecoder());
    }
}
