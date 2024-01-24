package ar.juarce.webapp.config;

import ar.juarce.webapp.controllers.HomeController;
import ar.juarce.webapp.controllers.PrayerController;
import ar.juarce.webapp.controllers.PrayerRequestController;
import ar.juarce.webapp.controllers.UserController;
import ar.juarce.webapp.exceptionMappers.*;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void init() {
        /*
        https://github.com/spring-projects/spring-boot/issues/7496#issuecomment-263245202
        https://github.com/spring-projects/spring-boot/issues/7496
        https://github.com/spring-projects/spring-boot/blob/e15b3e463f312524495349673a16cb67cfaa2eae/spring-boot-samples/spring-boot-sample-jersey/src/main/java/sample/jersey/JerseyConfig.java#L27-L28
        Don't use packages to scan for endpoints and register them individually instead.
         */
        register(HomeController.class);
        register(PrayerController.class);
        register(PrayerRequestController.class);
        register(UserController.class);

        register(AlreadyExistsExceptionMapper.class);
        register(ClientErrorExceptionMapper.class);
        register(ConstraintViolationExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(NotFoundExceptionMapper.class);

        register(ObjectMapperProvider.class);
    }
}
