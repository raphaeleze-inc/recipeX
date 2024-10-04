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
  private static final String USER_SAVED_MESSAGE = "User {} saved successfully";
  private static final String USER_RETRIEVED_MESSAGE = "User {} retrieved successfully";
  private static final String USER_NOT_FOUND_MESSAGE = "User {} not found";

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

    log.info("Creating user with username: {}", username);

    return Mono.just(user)
        .map(dbMapper::toDbDto)
        .flatMap(dbUserRepository::save)
        .doOnSuccess(savedUser -> log.info(USER_SAVED_MESSAGE, user.getId()))
        .doOnError(error -> log.error(USER_NOT_SAVED_MESSAGE, user.getId()))
        .map(restMapper::toRestDto);
  }

  @Override
  public Mono<RestRecipeXUser> getUser(UUID userId) {
    var userStringId = uuidMapper.toString(userId);

    log.info("Retrieving user with ID: {}", userId);

    return dbUserRepository.findById(userStringId)
        .flatMap(dbUser -> dbRecipeRepository.findByUserId(userStringId)
            .collectList()
            .map(dbUser::setRecipes))
        .doOnSuccess(user -> log.info(USER_RETRIEVED_MESSAGE, userId))
        .doOnError(error -> log.error(USER_NOT_FOUND_MESSAGE, userId))
        .map(restMapper::toRestDto);
  }

  @Override
  public Mono<Void> deleteUser(UUID userId) {
    var user = uuidMapper.toString(userId);

    log.info("Deleting user with ID: {}", userId);

    return dbUserRepository.deleteById(user)
        .doOnSuccess(info -> log.info(USER_DELETED_MESSAGE, user))
        .doOnError(info -> log.error(USER_NOT_DELETED_MESSAGE, user))
        .then(dbRecipeRepository.deleteById(user))
        .doOnSuccess(info -> log.info(RECIPE_DELETED_MESSAGE, user))
        .doOnError(info -> log.error(RECIPE_NOT_DELETED_MESSAGE, user));
  }
}
