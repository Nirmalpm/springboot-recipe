package guru.springframework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeServiceImpl;

public class RecipeServiceImpltest {

	RecipeServiceImpl recipeServiceImpl;
	@Mock
	RecipeRepository recipeRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeServiceImpl.getRecipes();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetRecipesById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional recipeOptional = Optional.of(recipe);
		when(recipeRepository.findById(1L)).thenReturn(recipeOptional);
		
		Recipe retRecipe = recipeServiceImpl.findById(1L);
		assertNotNull(retRecipe);
		verify(recipeRepository, times(1)).findById(1L);
		verify(recipeRepository, never()).findAll();
		
	}

}
