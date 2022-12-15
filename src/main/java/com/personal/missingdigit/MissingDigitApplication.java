package com.personal.missingdigit;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class MissingDigitApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MissingDigitApplication.class);

        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if(env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }

        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");

        if(Strings.isBlank(contextPath)) {
            contextPath = "/";
        }

        String hostAddress = "localhost";

        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The hostname could not be determined, using localhost as fallback");
        }

        log.info("\n-----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n-----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());

    }
}
