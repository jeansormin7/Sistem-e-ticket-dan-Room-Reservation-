package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.LayananDokumenDao;
import com.booking.model.LayananDokumen;
import com.booking.model.User;

@Service
public class LayananDokumenDaoImpl implements LayananDokumenDao {
	
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


	@Override
	public List<LayananDokumen> getAllLayananDokumen() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from LayananDokumen", LayananDokumen.class).getResultList();
	}

	@Override
	public LayananDokumen saveLayanan(LayananDokumen layananDokumen) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		LayananDokumen saved = em.merge(layananDokumen);
		em.getTransaction().commit();
		return saved;
	}


	@Override
	public LayananDokumen getIdLayanan(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(LayananDokumen.class, id);
	}


	@Override
	public List<LayananDokumen> getAllLayakanDokumenByIdUser(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from LayananDokumen where t_user_id="+id, LayananDokumen.class).getResultList();
	}


	@Override
	public List<LayananDokumen> getAllLayananDokumenbyStatus(String status) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from LayananDokumen where statusLayanan='"+status+"'", LayananDokumen.class).getResultList();
	}
	
	@Override
	public LayananDokumen findLayananDokumenByid(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(LayananDokumen.class, id);
	}



	@Override
	public void updateStatusLayananDokumenById(int id) {
		LayananDokumen layanan = findLayananDokumenByid(id);
		layanan.setStatusLayanan("APPROVED");
		layanan = saveLayanan(layanan);
		
	}


	@Override
	public List<LayananDokumen> getHistoryLayananDokumen() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from LayananDokumen", LayananDokumen.class).getResultList();
	}


	




	

}
