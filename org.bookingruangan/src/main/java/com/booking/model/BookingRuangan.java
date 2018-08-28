package com.booking.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name="t_bookingruangan")
public class BookingRuangan {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_ruangan_id")
	private Ruangan ruangan;
	
	private String deskripsiReservasi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="t_user_id")
	private User user;
	
	private String startDate;
	private String dueDate;
	private String status;
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ruangan getRuangan() {
		return ruangan;
	}
	public void setRuangan(Ruangan ruangan) {
		this.ruangan = ruangan;
	}
	public String getDeskripsiReservasi() {
		return deskripsiReservasi;
	}
	public void setDeskripsiReservasi(String deskripsiReservasi) {
		this.deskripsiReservasi = deskripsiReservasi;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public BookingRuangan(int id, Ruangan ruangan, String deskripsiReservasi, User user, String startDate,
			String dueDate, String status, Integer version) {
		super();
		this.id = id;
		this.ruangan = ruangan;
		this.deskripsiReservasi = deskripsiReservasi;
		this.user = user;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.status = status;
		this.version = version;
	}
	public BookingRuangan() {
		super();
	}
	

}
