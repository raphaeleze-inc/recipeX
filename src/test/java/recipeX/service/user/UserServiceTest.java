package recipeX.service.user;

import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import recipeX.DefaultSpringBootTest;
import recipeX.domain.Username;
import recipeX.mongo.DbUserRepository;
import recipeX.rest.RestRecipeXUser;


class UserServiceTest extends DefaultSpringBootTest {

  private static final UUID userId = UUID.fromString("f9b3b0ec-8fbb-4b91-9ff1-5b45c6b0e05c");

  @Autowired
  DefaultUserService userService;
  @Autowired
  DbUserRepository dbUserRepository;

  @Test
  void createUser_shouldCreateUserSuccessfully() {
    var testUsername = username();
    var result = userService.createUser(testUsername);

    var createdUser = result.block();

    assert createdUser != null;

    var expectedUser = new RestRecipeXUser()
        .setId(createdUser.getId())
        .setUsername(testUsername)
        .setRecipes(null);

    StepVerifier.create(result)
        .expectNext(expectedUser)
        .verifyComplete();

    StepVerifier.create(dbUserRepository.findById(String.valueOf(createdUser.getId())))
        .verifyComplete();
  }

  @Test
  void getUser_shouldRetrieveUserSuccessfully() {
    var testUsername = username();
    var createdUser = userService.createUser(testUsername).block();

    assert createdUser != null;
    var expectedUser = new RestRecipeXUser()
        .setId(createdUser.getId())
        .setUsername(testUsername)
        .setRecipes(Collections.emptyList());

    var result = userService.getUser(createdUser.getId());

    StepVerifier.create(result)
        .expectNext(expectedUser)
        .verifyComplete();
  }

  @Test
  void deleteUser_shouldDeleteUserSuccessfully() {
    Mono<Void> result = userService.deleteUser(userId);

    StepVerifier.create(result)
        .verifyComplete();

    StepVerifier.create(dbUserRepository.findById(String.valueOf(userId)))
        .verifyComplete();
  }

  Username username() {
    return new Username()
        .setName("name")
        .setSurname("surname");
  }
}