package recipeX.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import recipeX.domain.Username;
import recipeX.rest.RestRecipeXUser;
import recipeX.rest.RestUserRecipe;

class DbMapperTest {

  private final UUID userId = UUID.randomUUID();
  private final UUID recipeId = UUID.randomUUID();

  private final String title = "recipe name ";
  private final DbMapper dbMapper = Mappers.getMapper(DbMapper.class);

  @Test
  void givenRestRecipeXUser_whenMapToDbDto_thenReturnDbRecipeXUser() {
    // GIVEN
    RestRecipeXUser restRecipeXUser = getRestRecipeXUser();
    // WHEN
    var result = dbMapper.toDbDto(restRecipeXUser);

    // THEN
    assertThat(result.getId()).isEqualTo(restRecipeXUser.getId().toString().toUpperCase());
    assertThat(result.getUsername()).isEqualTo(restRecipeXUser.getUsername());
    assertThat(result.getRecipes()).hasSize(restRecipeXUser.getRecipes().size());
  }

  @Test
  void givenRestUserRecipe_whenMapToDbDto_thenReturnDbUserRecipe() {
    // GIVEN
    RestUserRecipe restUserRecipe = getRestUserRecipe();
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipe);

    // THEN
    assertThat(result.getRecipeId()).isEqualTo(restUserRecipe.getRecipeId().toString().toUpperCase());
    assertThat(result.getUserId()).isEqualTo(restUserRecipe.getUserId().toString().toUpperCase());
    assertThat(result.getTitle()).isEqualTo(restUserRecipe.getTitle());
  }

  @Test
  void givenListOfRestUserRecipe_whenMapToDbDto_thenReturnListOfDbUserRecipe() {
    // GIVEN
    var restUserRecipeList = Collections.singletonList(getRestUserRecipe());
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipeList);

    // THEN
    assertThat(result).hasSize(restUserRecipeList.size());
    assertThat(result.get(0).getRecipeId()).isEqualTo(restUserRecipeList.get(0).getRecipeId().toString().toUpperCase());
    assertThat(result.get(0).getUserId()).isEqualTo(restUserRecipeList.get(0).getUserId().toString().toUpperCase());
    assertThat(result.get(0).getTitle()).isEqualTo(restUserRecipeList.get(0).getTitle());
  }

  @Test
  void givenEmptyListRestUserRecipe_whenMapToDbDto_thenReturnEmptyList() {
    // GIVEN
    List<RestUserRecipe> restUserRecipeList = Collections.emptyList();
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipeList);

    // THEN
    assertThat(result).isEmpty();
  }

  @Test
  void givenRestUserRecipeWithNullFields_whenMapToDbDto_thenIgnoreNullFields() {
    // GIVEN
    var restUserRecipe = new RestUserRecipe()
        .setRecipeId(recipeId)
        .setUserId(userId)
        .setTitle(title);
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipe);

    // THEN
    assertThat(result.getRecipeId()).isEqualTo(restUserRecipe.getRecipeId().toString().toUpperCase());
    assertThat(result.getUserId()).isEqualTo(restUserRecipe.getUserId().toString().toUpperCase());
    assertThat(result.getTitle()).isEqualTo(restUserRecipe.getTitle());
    assertThat(result.getDescription()).isNull();
    assertThat(result.getImageUrl()).isNull();
  }

  @Test
  void givenRestUserRecipeWithEmptyLists_whenMapToDbDto_thenMapEmptyLists() {
    // GIVEN
    var restUserRecipe = new RestUserRecipe()
        .setRecipeId(recipeId)
        .setUserId(userId)
        .setTitle(title)
        .setIngredients(Collections.emptyList())
        .setInstructions(Collections.emptyList())
        .setTags(Collections.emptyList());
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipe);

    // THEN
    assertThat(result.getIngredients()).isEmpty();
    assertThat(result.getInstructions()).isEmpty();
    assertThat(result.getTags()).isEmpty();
  }
  @Test
  void givenRestUserRecipeWithNullLists_whenMapToDbDto_thenMapNullLists() {
    // GIVEN
    var restUserRecipe = new RestUserRecipe()
        .setRecipeId(recipeId)
        .setUserId(userId)
        .setTitle(title);
    // WHEN
    var result = dbMapper.toDbDto(restUserRecipe);

    // THEN
    assertThat(result.getIngredients()).isNull();
    assertThat(result.getInstructions()).isNull();
    assertThat(result.getTags()).isNull();
  }

  private Username getUsername() {
    return new Username()
        .setName("user")
        .setSurname("surname");
  }
  private RestRecipeXUser getRestRecipeXUser() {
    return new RestRecipeXUser()
        .setId(userId)
        .setUsername(getUsername())
        .setRecipes(Collections.emptyList());
  }

  private RestUserRecipe getRestUserRecipe() {
    return new RestUserRecipe()
        .setRecipeId(recipeId)
        .setUserId(userId)
        .setTitle(title)
        .setDescription("recipe description")
        .setImageUrl("http://example.com/image.jpg")
        .setIngredients(getIngredients())
        .setInstructions(getInstructions())
        .setTags(getTags());
  }
  private List<String> getIngredients() {
    return Arrays.asList("Ingredient 1", "Ingredient 2", "Ingredient 3");
  }

  private List<String> getTags() {
    return Arrays.asList("Tag1", "Tag2", "Tag3");
  }

  private List<String> getInstructions() {
    return Arrays.asList("Step 1", "Step 2", "Step 3");
  }
}