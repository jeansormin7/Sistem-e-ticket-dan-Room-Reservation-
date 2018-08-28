package com.booking.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_layanan_dokumen")
public class LayananDokumen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int id_user;
	private String judul;
	private String deskripsiDokumen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="t_user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_klasifikasi_id")
	private Klasifikasi klasifikasi;
	
	
	
	@Column(name="startDate")
	private String startDate;	
	private String dueDate;
	private String statusLayanan;
	private String file;
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getDeskripsiDokumen() {
		return deskripsiDokumen;
	}

	public void setDeskripsiDokumen(String deskripsiDokumen) {
		this.deskripsiDokumen = deskripsiDokumen;
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

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getStatusLayanan() {
		return statusLayanan;
	}

	public void setStatusLayanan(String statusLayanan) {
		this.statusLayanan = statusLayanan;
	}

	public Klasifikasi getKlasifikasi() {
		return klasifikasi;
	}

	public void setKlasifikasi(Klasifikasi klasifikasi) {
		this.klasifikasi = klasifikasi;
	}
	
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public LayananDokumen(int id, int id_user, String judul, String deskripsiDokumen, User user,
			Klasifikasi klasifikasi, String startDate, String dueDate, String statusLayanan, String file) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.judul = judul;
		this.deskripsiDokumen = deskripsiDokumen;
		this.user = user;
		this.klasifikasi = klasifikasi;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.statusLayanan = statusLayanan;
		this.file = file;
	}

	public LayananDokumen() {
		super();
	}

	


}
