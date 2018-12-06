package guru.springframework.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String description;
	
	@ManyToMany(mappedBy="categories")
	Set<Recipe> recipies;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Recipe> getRecipies() {
		return recipies;
	}
	public void setRecipies(Set<Recipe> recipies) {
		this.recipies = recipies;
	}
	
	
}
