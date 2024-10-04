package recipeX.service.recipe;

import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import recipeX.db.DbUserRecipe;
import recipeX.domain.Ids;
import recipeX.rest.RestUserRecipe;

public interface DefaultRecipeService {
 Flux<DbUserRecipe> createRecipes(UUID userId, List<RestUserRecipe> recipes);

  Mono<RestUserRecipe> getRecipe(String recipeId);

  Flux<RestUserRecipe> getRecipeByName(String name);

  Flux<RestUserRecipe> getRecipeByTags(List<String> tags);

  Mono<RestUserRecipe> updateRecipe(RestUserRecipe recipe);

  Mono<Void> deleteRecipe(Ids ids);
}
