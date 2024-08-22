package recipeX.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import recipeX.db.DbUserRecipe;

@Repository
public interface DbRecipeRepository extends ReactiveMongoRepository<DbUserRecipe, String> {
  Flux<DbUserRecipe> findByTitle(String title);

  Flux<DbUserRecipe> findByTagsContaining(List<String> tags);

  Flux<DbUserRecipe> findByUserId(String userId);
}
