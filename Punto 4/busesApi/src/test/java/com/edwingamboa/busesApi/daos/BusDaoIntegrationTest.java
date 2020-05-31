package com.edwingamboa.busesApi.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.models.Bus;
import com.edwingamboa.busesApi.models.Concessionaire;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BusDaoIntegrationTest {

	@Autowired
	private IBusDao busDao;

	private static final String TYPE = "Bi-articulado";
	private static final String MOTOR = "Electric";
	private static final String BRAKES = "Freno Regenerativo";
	private static final Concessionaire CONCESSIONARIRE = null;
	
	@Test
	public void registerTest() {
		Bus bus = new Bus();

		bus.setType(TYPE);
		bus.setMotor(MOTOR);
		bus.setBrakes(BRAKES);
		bus.setConsessionaire(CONCESSIONARIRE);

		Bus busSaved = busDao.save(bus);

		assertEquals(TYPE, busSaved.getType());
		assertEquals(MOTOR, busSaved.getMotor());
		assertEquals(BRAKES, busSaved.getBrakes());
		assertEquals(CONCESSIONARIRE, bus.getConsessionaire());
		assertNotEquals(0, busSaved.getId());
	}
	
	@Test
	public void editedTest() {
		Bus bus = new Bus();
		
		bus.setId(1L);
		bus.setType(TYPE);
		bus.setMotor(MOTOR);
		bus.setBrakes(BRAKES);

		Bus busSaved = busDao.save(bus);

		assertEquals(TYPE, busSaved.getType());
		assertEquals(MOTOR, busSaved.getMotor());
		assertEquals(BRAKES, busSaved.getBrakes());
		assertNotEquals(0, busSaved.getId());
	}
}
