package com.booking.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_notifikasi")
public class Notifikasi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receiver")
	private User user;
	
	private String isiNotifikasi;
	private boolean statusRead;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIsiNotifikasi() {
		return isiNotifikasi;
	}
	public void setIsiNotifikasi(String isiNotifikasi) {
		this.isiNotifikasi = isiNotifikasi;
	}
	public boolean isStatusRead() {
		return statusRead;
	}
	public void setStatusRead(boolean statusRead) {
		this.statusRead = statusRead;
	}
	public Notifikasi(int id, User user, String isiNotifikasi, boolean statusRead) {
		super();
		this.id = id;
		this.user = user;
		this.isiNotifikasi = isiNotifikasi;
		this.statusRead = statusRead;
	}
	public Notifikasi() {
		super();
	}   
	
	
	
}
