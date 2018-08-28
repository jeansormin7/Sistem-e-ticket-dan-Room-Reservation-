package com.booking.dao;

import java.util.List;

import com.booking.model.Klasifikasi;

public interface KlasifikasiDao {
	List<Klasifikasi> getAllKlasifikasi();
	Klasifikasi saveOrUpdate(Klasifikasi klasifikasi);
	
}
