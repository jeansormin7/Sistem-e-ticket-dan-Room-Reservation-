package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.KlasifikasiDao;
import com.booking.model.Klasifikasi;

@Service
public class KlasifikasiDaoImpl implements KlasifikasiDao {

private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	@Override
	public List<Klasifikasi> getAllKlasifikasi() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from Klasifikasi", Klasifikasi.class).getResultList();
	}

	@Override
	public Klasifikasi saveOrUpdate(Klasifikasi klasifikasi) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Klasifikasi saved = em.merge(klasifikasi);
		em.getTransaction().commit();
		return saved;
	}

}
