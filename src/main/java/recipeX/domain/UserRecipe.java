package recipeX.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRecipe {

  private UUID recipeId;
  private UUID userId;
  private String title;
  private String description;
  private List<String> ingredients;
  private List<String> instructions;
  private List<String> tags;
  private String imageUrl;
  private String imageUploadUrl;
  private LocalDateTime createdAt;
}
