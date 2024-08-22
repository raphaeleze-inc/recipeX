package recipeX.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Ids {

  @NotBlank
  private String recipeId;
  @NotBlank
  private String userId;
}
