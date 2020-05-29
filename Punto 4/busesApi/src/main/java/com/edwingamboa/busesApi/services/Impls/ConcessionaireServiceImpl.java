package com.edwingamboa.busesApi.services.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edwingamboa.busesApi.daos.IConcessionaireDao;
import com.edwingamboa.busesApi.models.Concessionaire;
import com.edwingamboa.busesApi.services.IConcessionaireService;

@Service
public class ConcessionaireServiceImpl implements IConcessionaireService {

	@Autowired
	private IConcessionaireDao concessionaireDao;

	@Override
	public Concessionaire findOne(Long id) {
		return concessionaireDao.findById(id).orElse(null);
	}

	@Override
	public List<Concessionaire> findAll() {
		return (List<Concessionaire>) concessionaireDao.findAll();
	}

	@Override
	public Concessionaire save(Concessionaire concessionaire) {
		return concessionaireDao.save(concessionaire);
	}

	@Override
	public void deletOne(Long id) {
		concessionaireDao.deleteById(id);
	}

}
