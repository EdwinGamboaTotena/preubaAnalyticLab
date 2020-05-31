package com.edwingamboa.busesApi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.edwingamboa.busesApi.daos.IConcessionaireDao;
import com.edwingamboa.busesApi.models.Concessionaire;
import com.edwingamboa.busesApi.services.Impls.ConcessionaireServiceImpl;

@RunWith(SpringRunner.class)
public class ConcessionaireServiceImplTest {

	@TestConfiguration
	static class ConcessionaireImplTestContextConfiguration {
		@Bean
		public IConcessionaireService ConcessionaireService() {
			return new ConcessionaireServiceImpl();
		}
	}

	@Autowired
	private IConcessionaireService concessionaireService;

	@MockBean
	private IConcessionaireDao concessionaireDao;

	private static final String NAME_ONE = "Concessionaire 1";
	private static final String NAME_TWO = "Concessionaire 2";

	@Before
	public void init() {
		Concessionaire concessionaireOne = new Concessionaire();
		concessionaireOne.setId(1L);
		concessionaireOne.setName(NAME_ONE);

		Concessionaire concessionaireTwo = new Concessionaire();
		concessionaireTwo.setId(2L);
		concessionaireTwo.setName(NAME_TWO);

		List<Concessionaire> lstConcessionaire = new ArrayList<Concessionaire>();
		lstConcessionaire.add(concessionaireOne);
		lstConcessionaire.add(concessionaireTwo);

		Mockito.when(concessionaireDao.findAll()).thenReturn(lstConcessionaire);
		Mockito.when(concessionaireDao.findById(concessionaireOne.getId())).thenReturn(null);
	}

	@Test
	public void findAllTest() {
		List<Concessionaire> lstConcessionaire = concessionaireService.findAll();

		assertEquals(2, lstConcessionaire.size());
		assertEquals(NAME_ONE, lstConcessionaire.get(0).getName());
		assertEquals(2L, lstConcessionaire.get(1).getId());
	}

	@Test
	public void findOneNullTest() {
		Concessionaire concessionaire = concessionaireService.findOne(3L);
		assertEquals(null, concessionaire);
	}
}
