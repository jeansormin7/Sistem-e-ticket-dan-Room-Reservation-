package com.booking.dao;

import java.util.List;

import com.booking.model.Notifikasi;
import com.booking.model.User;

public interface NotifikasiDao {
	List<Notifikasi> getAllNotifikasi();
	Notifikasi save(Notifikasi notifikasi);
	List <Notifikasi> getAllNotifikasiByStatusReadByUser(Boolean status, int id);
	
	
}
