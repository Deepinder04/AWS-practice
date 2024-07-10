package deep.inder.AWS.practice.utlities;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class S3PropertiesLoader {

    private static final Logger logger = LoggerFactory.getLogger(S3PropertiesLoader.class);

    private final AmazonS3 s3Client;
    private final String bucketName;
    private final String fileName;

    public S3PropertiesLoader(String region, String bucketName, String fileName) {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        this.bucketName = bucketName;
        this.fileName = fileName;

        logger.info("Initialized S3PropertiesLoader with region: {}, bucket: {}, file: {}", region, bucketName, fileName);
    }

    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        try (S3ObjectInputStream s3InputStream = s3Object.getObjectContent()) {
            properties.load(s3InputStream);
            logger.info("Loaded properties from S3: {}", properties);
        }
        return properties;
    }
}

