package recipeX.service.user;

import java.util.UUID;
import reactor.core.publisher.Mono;
import recipeX.domain.Username;
import recipeX.rest.RestRecipeXUser;

public interface DefaultUserService {
  Mono<RestRecipeXUser> createUser(Username username);

  Mono<RestRecipeXUser> getUser(UUID userId);

  Mono<Void> deleteUser(UUID userId);
}
