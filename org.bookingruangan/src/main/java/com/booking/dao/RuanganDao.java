package com.booking.dao;

import java.util.List;

import com.booking.model.Ruangan;

public interface RuanganDao {
	 List<Ruangan> getAllRuangan();
	 Ruangan saveOrUpdate(Ruangan ruangan);
	 void deleteRuangan(Integer id);
	 Ruangan getIdRuangan(Integer id);
}
