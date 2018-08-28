package com.booking.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_ruangan")
public class Ruangan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String idRuangan;
	
	@OneToMany(mappedBy="ruangan")
	private Set<BookingRuangan> bookingRuangans;
	
	private String kapasitas;
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdRuangan() {
		return idRuangan;
	}

	public void setIdRuangan(String idRuangan) {
		this.idRuangan = idRuangan;
	}

	public Set<BookingRuangan> getBookingRuangans() {
		return bookingRuangans;
	}

	public void setBookingRuangans(Set<BookingRuangan> bookingRuangans) {
		this.bookingRuangans = bookingRuangans;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getKapasitas() {
		return kapasitas;
	}

	public void setKapasitas(String kapasitas) {
		this.kapasitas = kapasitas;
	}

	public Ruangan(int id, String idRuangan, Set<BookingRuangan> bookingRuangans, String kapasitas, Integer version) {
		super();
		this.id = id;
		this.idRuangan = idRuangan;
		this.bookingRuangans = bookingRuangans;
		this.kapasitas = kapasitas;
		this.version = version;
	}

	public Ruangan() {
		super();
	}

	



	


	
	
	
	
	
	
}
