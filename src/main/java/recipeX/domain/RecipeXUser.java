package recipeX.domain;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RecipeXUser {

  private UUID id;
  private Username username;
  private List<UserRecipe> userRecipes;
}
