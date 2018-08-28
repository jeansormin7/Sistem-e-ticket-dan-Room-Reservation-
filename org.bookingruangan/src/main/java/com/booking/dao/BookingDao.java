package com.booking.dao;

import java.util.List;

import com.booking.model.BookingRuangan;

public interface BookingDao {
	List<BookingRuangan> getAllBooking();
	List<BookingRuangan>getAllBookingByStatus(String status);
	List<BookingRuangan>getAllBookingByIdUser(int id);
	BookingRuangan getIdBooking(Integer id);
	BookingRuangan saveOrUpdate(BookingRuangan bookingRuangan);
	void updateStatusBookingById(int id);
	void updateStatusBookingByIds(int id);
	BookingRuangan findRequestById(int id);
	
}
