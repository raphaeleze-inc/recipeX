package recipeX.service.user;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import recipeX.domain.Username;
import recipeX.mapper.DbMapper;
import recipeX.mapper.RestMapper;
import recipeX.mapper.UuidMapper;
import recipeX.mongo.DbRecipeRepository;
import recipeX.mongo.DbUserRepository;
import recipeX.rest.RestRecipeXUser;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements DefaultUserService {
  private static final String USER_DELETED_MESSAGE = "User {} deleted";
  private static final String RECIPE_DELETED_MESSAGE = "Recipes associated with user {} deleted";
  private static final String USER_NOT_DELETED_MESSAGE = "Error deleting user {}";
  private static final String RECIPE_NOT_DELETED_MESSAGE = "Error deleting recipe {}";
  private static final String USER_NOT_SAVED_MESSAGE = "Error saving user {}";
  private final DbUserRepository dbUserRepository;
  private final DbRecipeRepository dbRecipeRepository;
  private final DbMapper dbMapper;
  private final UuidMapper uuidMapper;
  private final RestMapper restMapper;

  @Override
  public Mono<RestRecipeXUser> createUser(Username username) {
    var user = new RestRecipeXUser()
        .setId(UUID.randomUUID())
        .setUsername(username);

    return Mono.just(user)
        .map(dbMapper::toDbDto)
        .flatMap(dbUserRepository::save)
        .doOnError(error -> log.error(USER_NOT_SAVED_MESSAGE, user.getId()))
        .map(restMapper::toRestDto);
  }

  @Override
  public Mono<RestRecipeXUser> getUser(UUID userId) {
    var userStringId = uuidMapper.toString(userId);
    var recipes = dbRecipeRepository.findByUserId(userStringId)
        .collectList().block(); // TODO : REMOVE BLOCK

    return dbUserRepository.findById(userStringId)
        .map(recipeX -> recipeX.setRecipes(recipes))
        .map(restMapper::toRestDto);
  }

  @Override
  public Mono<Void> deleteUser(UUID userId) {
    var user = uuidMapper.toString(userId);

    return dbUserRepository.deleteById(user)
        .doOnError(info -> log.error(USER_NOT_DELETED_MESSAGE, user))
        .doOnSuccess(info -> log.info(USER_DELETED_MESSAGE, user))
        .then(dbRecipeRepository.deleteById(user))
        .doOnError(info -> log.error(RECIPE_NOT_DELETED_MESSAGE, user))
        .doOnSuccess(info -> log.info(RECIPE_DELETED_MESSAGE, user));
  }
}