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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="t_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "*Please provide an username")
	private String username;
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	private String noHp;
	private String role;
	
	@Column(name = "password")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	
	@OneToMany(mappedBy="user")
	private Set<BookingRuangan> bookingRuangans;
	
	@OneToMany(mappedBy = "user")
	private Set<Notifikasi> notifikasis;
	
	
	
	
	@OneToMany(mappedBy="user")
	private Set<LayananDokumen> layananDokumens;
	

	
	
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNoHp() {
		return noHp;
	}
	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<BookingRuangan> getBookingRuangans() {
		return bookingRuangans;
	}
	public void setBookingRuangans(Set<BookingRuangan> bookingRuangans) {
		this.bookingRuangans = bookingRuangans;
	}
	public Set<LayananDokumen> getLayananDokumens() {
		return layananDokumens;
	}
	public void setLayananDokumens(Set<LayananDokumen> layananDokumens) {
		this.layananDokumens = layananDokumens;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public User(int id, String username, String email, String noHp, String role, String password,
			Set<BookingRuangan> bookingRuangans, Set<LayananDokumen> layananDokumens, Integer version) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.noHp = noHp;
		this.role = role;
		this.password = password;
		this.bookingRuangans = bookingRuangans;
		this.layananDokumens = layananDokumens;
		this.version = version;
	}
	public User() {
		super();
	}
	
	
	
	
	
	
}
