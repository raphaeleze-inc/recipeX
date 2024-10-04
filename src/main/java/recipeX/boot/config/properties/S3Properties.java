package recipeX.boot.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "recipex.aws.s3")
public class S3Properties {

  private @NotNull String accessKey;
  private @NotNull String secretKey;
  private String region;
  private String publicEndpoint;
  private Long urlExpiration;
  private String bucket;
  private String folder;
}
