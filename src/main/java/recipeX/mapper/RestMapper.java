package recipeX.mapper;


import recipeX.db.DbRecipeXUser;
import recipeX.db.DbUserRecipe;
import recipeX.rest.RestRecipeXUser;
import recipeX.rest.RestUserRecipe;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RestMapper extends UuidMapper {

  RestRecipeXUser toRestDto(DbRecipeXUser dbRecipeXUser);

  RestUserRecipe toRestDto(DbUserRecipe dbUserRecipe);

  List<RestUserRecipe> toRestDto(List<DbUserRecipe> dbUserRecipe);
}
