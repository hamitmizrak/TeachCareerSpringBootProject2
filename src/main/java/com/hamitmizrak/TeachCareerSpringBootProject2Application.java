package com.hamitmizrak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//securit: inactive exclude:dahil etmemek
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
}
)

//Audit:
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

public class TeachCareerSpringBootProject2Application {

    public static void main(String[] args) {


        //DevTools
        System.setProperty("spring.devtools.restart.enabled","false");

        // AWT: JOptionPanel set ayarı
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(TeachCareerSpringBootProject2Application.class, args);
    }

}
