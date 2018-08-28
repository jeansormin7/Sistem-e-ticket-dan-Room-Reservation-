package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.BookingDao;
import com.booking.model.BookingRuangan;

@Service
public class BookingDaoImpl implements BookingDao {
	
private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<BookingRuangan> getAllBooking() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from BookingRuangan", BookingRuangan.class).getResultList();
	}

	@Override
	public BookingRuangan saveOrUpdate(BookingRuangan bookingRuangan) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		BookingRuangan saved = em.merge(bookingRuangan);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public void updateStatusBookingById(int id) {
		BookingRuangan bookingRuangan = findRequestById(id);
		bookingRuangan.setStatus("APROVE");
		bookingRuangan = saveOrUpdate(bookingRuangan);
		
	}

	@Override
	public BookingRuangan findRequestById(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(BookingRuangan.class, id);
	}

	@Override
	public List<BookingRuangan> getAllBookingByStatus(String status) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from BookingRuangan where status='"+status+"'", BookingRuangan.class).getResultList();
	}

	@Override
	public List<BookingRuangan> getAllBookingByIdUser(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from BookingRuangan where t_user_id = "+id, BookingRuangan.class).getResultList();
	}

	@Override
	public void updateStatusBookingByIds(int id) {
		BookingRuangan bookingRuangan = findRequestById(id);
		bookingRuangan.setStatus("REJECT");
		bookingRuangan = saveOrUpdate(bookingRuangan);
		
		
	}

	@Override
	public BookingRuangan getIdBooking(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(BookingRuangan.class, id);
		
	}
	
}
