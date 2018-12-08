package guru.springframework.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}


	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<Recipe>();
		/*recipeRepository.findAll().iterator().forEachRemaining((o)->{
			recipeSet.add(o);
		});*/
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}


	@Override
	public Recipe findById(Long id) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe not present");
		}
		return recipeOptional.get();
	}
	
	

}
