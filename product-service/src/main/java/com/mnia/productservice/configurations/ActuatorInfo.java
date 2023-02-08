package com.mnia.productservice.configurations;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ActuatorInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", "Spring Boot Actuator Example")
                .withDetail("author", "Marcos Ferreira");
    }
}
