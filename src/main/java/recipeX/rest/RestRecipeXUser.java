package recipeX.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import recipeX.domain.Username;

@Data
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
public class RestRecipeXUser {

  private UUID id;
  private Username username;
  private List<RestUserRecipe> recipes;
}