package recipeX.mongo;

import recipeX.db.DbRecipeXUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbUserRepository extends ReactiveMongoRepository<DbRecipeXUser, String> {
}
