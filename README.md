>[!NOTE]
> Currently under construction will update when app is done

### Idea: Recipe Management API

**Description:**
This REST API allows users to manage recipes. Users can create, read, update, and delete recipes. Each recipe will have a title, description, list of ingredients, cooking instructions, and optionally, tags for categorization.

**Endpoints:**

1. **Create a Recipe** *Done
   - Endpoint: POST /recipes
   - Request Body: JSON containing recipe details (title, description, ingredients, instructions, tags)
   - Response: Newly created recipe object with ID

2. **Get All Recipes** *Done
   - Endpoint: GET /recipes
   - Response: JSON array containing all recipes

3. **Get Recipe by ID** *Done
   - Endpoint: GET /recipes/{id}
   - Response: JSON object containing recipe details

4. **Update a Recipe** *Done
   - Endpoint: PUT /recipes/{id}
   - Request Body: JSON containing updated recipe details (title, description, ingredients, instructions, tags)
   - Response: Updated recipe object

5. **Delete a Recipe** *Done
   - Endpoint: DELETE /recipes/{id}
   - Response: Success message or HTTP status code

**Additional Endpoints:**

6. **Search Recipes by Title or Tag** *Done
   - Endpoint: GET /recipes/search?query={query}
   - Response: JSON array containing recipes matching the search query

7. **Add Ingredient to a Recipe**
   - Endpoint: POST /recipes/{id}/ingredients
   - Request Body: JSON containing ingredient details
   - Response: Updated recipe object with added ingredient

8. **Remove Ingredient from a Recipe**
   - Endpoint: DELETE /recipes/{id}/ingredients/{ingredientId}
   - Response: Updated recipe object with removed ingredient

**Example Usage:**

- Users can create and manage their recipe collections using the API.
- Frontend applications or other services can utilize the API to search for recipes based on titles, tags, or ingredients.

**Benefits:**

- Offers practical experience in building a CRUD API with search functionality.
- Provides exposure to MongoDB's array operations for managing nested data structures.
- Allows for the exploration of reactive programming concepts with Spring Boot.


## RecipeX API Requests

### Create User
**Method:** POST  
**URL:** `/user`  
**Body:**  
```json
{
    "name": "<required>",
    "surname": "<optional>"
}
```

### Get User
**Method:** GET  
**URL:** `/user/{userId}`

### Create Recipe
**Method:** POST  
**URL:** `/recipes/{userId}`  
**Body:**  
```json
[
    {
        "title": "Caprese Salad",
        "description": "A simple Italian salad made with fresh tomatoes, mozzarella cheese, basil, olive oil, and balsamic glaze.",
        "ingredients": [
            "Mozzarella cheese",
            "Fresh basil leaves",
            "Olive oil"
        ],
        "instructions": [
            "Tuck fresh basil leaves between the tomato and mozzarella slices.",
            "Drizzle olive oil and balsamic glaze over the salad.",
            "Season with salt and pepper to taste.",
            "Serve immediately."
        ],
        "tags": [
            "IDK",
            "Salad",
            "Vegetarian"
        ]
    }
]
```

### Get Recipe
**Method:** GET  
**URL:** `/recipe/{recipeId}`

### Get All Recipes with Tag
**Method:** GET  
**URL:** `/recipes/by-tags?tags=Italian`

**Query Params:**  
- tags: Italian

### Get All Recipes with Title
**Method:** GET  
**URL:** `/recipes/by-title/Caprese Salad`
