package deep.inder.AWS.practice.cron;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class CronConfig {

    @Value("${env.property.cron.expression:* * * ? * *}")
    private String envPropertyCronExpression;

    @Value("${env.property.env:local}")
    private String envPropertyEnvironment;

    @Scheduled(cron ="${env.property.cron.expression}")
    String getPropertyEnvironment(){
        System.out.println("properties are currently being picked from the environment - " + envPropertyEnvironment);
        return "properties are currently being picked from the environment - " + envPropertyEnvironment;
    }
}
