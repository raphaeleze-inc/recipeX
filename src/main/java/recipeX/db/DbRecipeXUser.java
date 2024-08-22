package recipeX.db;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import recipeX.domain.Username;

@Data
@Accessors(chain = true)
@Document(collection = "RecipeX_Users")
public class DbRecipeXUser {

  @Id
  private String id;
  private Username username;
  private List<DbUserRecipe> recipes;
}