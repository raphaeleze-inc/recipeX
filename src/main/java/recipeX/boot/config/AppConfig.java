package recipeX.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import recipeX.boot.config.properties.S3Properties;

@Configuration
@EnableConfigurationProperties({
    S3Properties.class
})
public class AppConfig {

 /* @Bean
  S3Presigner s3Presigner(
      S3Properties s3Properties,
      AwsCredentialsProvider awsCredentialsProvider {
    return S3Presigner.builder()
        .region(Region.of(s3Properties.getRegion()))
        .credentialsProvider(awsCredentialsProvider)
        .endpointOverride(URI.create(s3Properties.getPublicEndpoint()))
        .build();
  }


  @Bean
  AwsCredentialsProvider awsCredentialsProvider(S3Properties properties) {
    return AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey());
  }*/
}
