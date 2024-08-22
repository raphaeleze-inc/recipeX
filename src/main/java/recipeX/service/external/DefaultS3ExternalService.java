package recipeX.service.external;

import reactor.core.publisher.Mono;
import recipeX.rest.RestUserRecipe;

public interface DefaultS3ExternalService {
  Mono<RestUserRecipe> uploadImage(String recipeId);
}
