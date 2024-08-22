package recipeX.service.external;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import recipeX.rest.RestUserRecipe;

@Slf4j
@Service
@AllArgsConstructor
public class S3ExternalService implements DefaultS3ExternalService {


  @Override
  public Mono<RestUserRecipe> uploadImage(String recipeId) {
    return null;
  }
}
