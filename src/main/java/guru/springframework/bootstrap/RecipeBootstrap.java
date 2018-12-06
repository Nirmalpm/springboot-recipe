package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMesaureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMesaureRepository unitOfMesaureRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMesaureRepository unitOfMesaureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMesaureRepository = unitOfMesaureRepository;
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<Recipe>();
		//Get UOMS
		Optional<UnitOfMeasure> eachUomOptional = unitOfMesaureRepository.findByUom("Each");
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMesaureRepository.findByUom("Teaspoon");
		if(!teaspoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMesaureRepository.findByUom("Tablespoon");
		if(!tablespoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> pinchUomOptional = unitOfMesaureRepository.findByUom("Pinch");
		if(!pinchUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> ounceUomOptional = unitOfMesaureRepository.findByUom("Ounce");
		if(!ounceUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> cupUomOptional = unitOfMesaureRepository.findByUom("Cup");
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		Optional<UnitOfMeasure> pintUomOptional = unitOfMesaureRepository.findByUom("Pint");
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not found");
			
		}
		
		
		UnitOfMeasure each = eachUomOptional.get();
		UnitOfMeasure cup = cupUomOptional.get();
		UnitOfMeasure pint = pintUomOptional.get();
		UnitOfMeasure dash = pinchUomOptional.get();
		UnitOfMeasure teaspoon = teaspoonUomOptional.get();
		UnitOfMeasure tablespoon = tablespoonUomOptional.get();
		UnitOfMeasure ounce = ounceUomOptional.get();
		
		//Get caetogories
		Optional<Category> americanCategory = categoryRepository.findByDescription("American");
		if(!americanCategory.isPresent()) {
			throw new RuntimeException("Required Category not found.");
		}
		Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");
		if(!mexicanCategory.isPresent()) {
			throw new RuntimeException("Required Category not found.");
		}
		
		Category american = americanCategory.get();
		Category mexican = mexicanCategory.get();
		
		//Yummy guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setCookTime(10);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setPrepTime(10);
		guacRecipe.setDescription("Perfect Guacomole");
		guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed.\n "+
				"Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.\n "+
				"Place in a bowl.\n "+
				"2 Mash with a fork: Using a fork, roughly mash the avocado. \n "+
				"(Don't overdo it! The guacamole should be a little chunky.)\n "+
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. \n "+
				"The acid in the lime juice will provide some balance to the richness of the avocado \n "+
				"and will help delay the avocados from turning brown. Add the chopped onion, cilantro, black pepper, and chiles. \n "+
				"Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n "+
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n "+
				"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it.\n "+
				"(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n "+
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"+
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it \n"+
				"(a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n"+
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop\n"+
				"you from making guacamole.\n"+
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. \n"+
				"Purists may be horrified, but so what? It tastes great.");
		
		guacRecipe.setNotes(guacNotes);
		
		//Ingredients setting
		guacRecipe.addIngredient(new Ingredient("Ripe Avacodes", new BigDecimal(2), each));
		guacRecipe.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(.5), teaspoon));
		guacRecipe.addIngredient(new Ingredient("Fresh lemon juice", new BigDecimal(2), tablespoon));
		guacRecipe.addIngredient(new Ingredient("Minced red onions", new BigDecimal(2), tablespoon));
		guacRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), each));
		guacRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon));
		guacRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), dash));
		
		guacRecipe.getCategories().add(american);
		guacRecipe.getCategories().add(mexican);
		
		recipes.add(guacRecipe);
		
		return recipes;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
	}
	
	
}
