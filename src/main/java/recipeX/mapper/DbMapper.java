package recipeX.mapper;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import recipeX.db.DbRecipeXUser;
import recipeX.db.DbUserRecipe;
import recipeX.rest.RestRecipeXUser;
import recipeX.rest.RestUserRecipe;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DbMapper extends UuidMapper {

  DbRecipeXUser toDbDto(RestRecipeXUser restRecipeXUser);


  DbUserRecipe toDbDto(RestUserRecipe restUserRecipe);

  List<DbUserRecipe> toDbDto(List<RestUserRecipe> restUserRecipe);
}
