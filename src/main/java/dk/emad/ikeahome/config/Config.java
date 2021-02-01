package dk.emad.ikeahome.config;

import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${secretip:none}") private String ip;
    @Value("${secretcode:none}") private String code;

    @Bean
    public Gateway gateway() {
        Gateway gateway = new Gateway(ip);
        Credentials credentials = gateway.connect(code);
        gateway.connect(credentials);
        return gateway;
    }
}
