package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.RuanganDao;
import com.booking.model.Ruangan;

@Service
public class RuanganDaoImpl implements RuanganDao {
	
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<Ruangan> getAllRuangan() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from Ruangan", Ruangan.class).getResultList();
	}

	@Override
	public Ruangan saveOrUpdate(Ruangan ruangan) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Ruangan saved = em.merge(ruangan);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public void deleteRuangan(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Ruangan.class, id));
		em.getTransaction().commit();
	}

	@Override
	public Ruangan getIdRuangan(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(Ruangan.class, id); 
	
	}

}
