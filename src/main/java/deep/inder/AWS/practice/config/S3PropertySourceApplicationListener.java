package deep.inder.AWS.practice.config;

import deep.inder.AWS.practice.utlities.S3PropertiesLoader;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class S3PropertySourceApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();

        S3PropertiesLoader s3PropertiesLoader = new S3PropertiesLoader("eu-north-1", "second.breakfast", activeProfiles[0] + "/application.properties");
        try {
            Properties properties = s3PropertiesLoader.loadProperties();
            PropertiesPropertySource propertySource = new PropertiesPropertySource("s3Properties", properties);
            environment.getPropertySources().addFirst(propertySource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from S3", e);
        }
    }
}
