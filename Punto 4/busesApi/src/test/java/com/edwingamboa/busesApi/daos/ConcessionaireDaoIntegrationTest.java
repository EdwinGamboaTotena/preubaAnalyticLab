package com.edwingamboa.busesApi.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.models.Concessionaire;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ConcessionaireDaoIntegrationTest {

	@Autowired
	private IConcessionaireDao concessionaireDao;
	
	private static final String NAME = "Concessionaire 1";
	
	@Test
	public void registerTest() {
		Concessionaire concessionaire = new Concessionaire();
				
		concessionaire.setName(NAME);

		Concessionaire concessionaireSaved = concessionaireDao.save(concessionaire);
		
		assertEquals(NAME, concessionaireSaved.getName());
		assertNotEquals(0, concessionaireSaved.getId());
	}
}
