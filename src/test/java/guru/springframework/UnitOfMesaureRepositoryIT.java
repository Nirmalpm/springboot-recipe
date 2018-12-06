package guru.springframework;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMesaureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesaureRepositoryIT {
	
	@Autowired
	UnitOfMesaureRepository unitOfMesaureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTeaspoon() {
		UnitOfMeasure  uom = unitOfMesaureRepository.findByUom("Teaspoon").get();
		assertEquals("Teaspoon",uom.getUom());
	}
	
	@Test
	@DirtiesContext
	public void testCup() {
		UnitOfMeasure  uom = unitOfMesaureRepository.findByUom("Cup").get();
		assertEquals("Cup",uom.getUom());
	}

}
