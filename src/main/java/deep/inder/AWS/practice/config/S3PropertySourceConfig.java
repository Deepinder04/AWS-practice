package deep.inder.AWS.practice.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


import java.io.IOException;
import java.util.Properties;

@Configuration
public class S3PropertySourceConfig {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.file.name}")
    private String fileName;

    private final ConfigurableEnvironment environment;

    public S3PropertySourceConfig(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
        InputStreamResource resource = new InputStreamResource(s3Client.getObject(bucketName, fileName).getObjectContent());
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("s3Properties", properties));
    }
}

