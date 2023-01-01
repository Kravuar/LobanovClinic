package net.kravuar.lobanovclinic;

import net.kravuar.lobanovclinic.app.props.AppProps;
import net.kravuar.lobanovclinic.app.services.HumanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class LobanovClinicApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LobanovClinicApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LobanovClinicApplication.class);
    }
}