package com.booking.dao;

import java.util.List;

import com.booking.model.LayananDokumen;
import com.booking.model.User;

public interface LayananDokumenDao {
	List<LayananDokumen> getAllLayananDokumen();
	List<LayananDokumen> getHistoryLayananDokumen();
	LayananDokumen saveLayanan(LayananDokumen layananDokumen);
	LayananDokumen getIdLayanan(Integer id);
	List <LayananDokumen> getAllLayakanDokumenByIdUser(int id);
	List <LayananDokumen> getAllLayananDokumenbyStatus(String status);
	LayananDokumen findLayananDokumenByid(int id);
	void updateStatusLayananDokumenById(int id);
	
	
}
