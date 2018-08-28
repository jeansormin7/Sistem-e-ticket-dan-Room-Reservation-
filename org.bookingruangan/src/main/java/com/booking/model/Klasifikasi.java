package com.booking.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_klasifikasi")
public class Klasifikasi {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String namaKlasifikasi;
	
	@OneToMany(mappedBy="klasifikasi")
	private Set<LayananDokumen> layananDokumens;

	public Klasifikasi(int id, String namaKlasifikasi, Set<LayananDokumen> layananDokumens) {
		super();
		this.id = id;
		this.namaKlasifikasi = namaKlasifikasi;
		this.layananDokumens = layananDokumens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaKlasifikasi() {
		return namaKlasifikasi;
	}

	public void setNamaKlasifikasi(String namaKlasifikasi) {
		this.namaKlasifikasi = namaKlasifikasi;
	}

	public Set<LayananDokumen> getLayananDokumens() {
		return layananDokumens;
	}

	public void setLayananDokumens(Set<LayananDokumen> layananDokumens) {
		this.layananDokumens = layananDokumens;
	}

	public Klasifikasi() {
		super();
	}
	
	
		
}
