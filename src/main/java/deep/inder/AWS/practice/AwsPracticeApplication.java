package deep.inder.AWS.practice;

import deep.inder.AWS.practice.config.S3PropertySourceApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsPracticeApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AwsPracticeApplication.class);
		application.addListeners(new S3PropertySourceApplicationListener());
		application.run(args);
	}
}
