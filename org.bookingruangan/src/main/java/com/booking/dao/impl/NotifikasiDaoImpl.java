package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.NotifikasiDao;
import com.booking.model.LayananDokumen;
import com.booking.model.Notifikasi;
import com.booking.model.User;

@Service
public class NotifikasiDaoImpl implements NotifikasiDao{
	
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<Notifikasi> getAllNotifikasi() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from Notifikasi", Notifikasi.class).getResultList();
	}

	@Override
	public Notifikasi save(Notifikasi notifikasi) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Notifikasi saved = em.merge(notifikasi);
		em.getTransaction().commit();
		return saved;
		
	}

	@Override
	public List<Notifikasi> getAllNotifikasiByStatusReadByUser(Boolean status, int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from Notifikasi where statusRead= '"+status+"' and  receiver = '"+id+"'", Notifikasi.class).getResultList();
	}

	

}
