package com.booking.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.booking.dao.BookingDao;
import com.booking.dao.LayananDokumenDao;
import com.booking.dao.NotifikasiDao;
import com.booking.dao.RuanganDao;
import com.booking.dao.UserDao;
import com.booking.model.BookingRuangan;
import com.booking.model.LayananDokumen;
import com.booking.model.Notifikasi;
import com.booking.model.Ruangan;
import com.booking.model.User;

@Controller
public class HomeDosenController {
	private UserDao uDao;
	private BookingDao bDao;
	private User userLogin;
	private LayananDokumenDao lDao;
	private RuanganDao rDao;
	private NotifikasiDao nDao;
	
	
	@Autowired
	public void setnDao(NotifikasiDao nDao) {
		this.nDao = nDao;
	}
	
	@Autowired
	public void setrDao(RuanganDao rDao) {
		this.rDao = rDao;
	}
	public LayananDokumenDao getlDao() {
		return lDao;
	}
	@Autowired
	public void setlDao(LayananDokumenDao lDao) {
		this.lDao = lDao;
	}

	@Autowired
	public void setbDao(BookingDao bDao) {
		this.bDao = bDao;
	}

	@Autowired
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}
		
	@RequestMapping(value="/homedosen/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable Integer id, Model model, HttpServletRequest request){
		
		userLogin = (User) request.getSession().getAttribute("userLogin");
		model.addAttribute("user", uDao.getIdUser(id));
		return "profile";	
	}
	
	@RequestMapping(value = "/createbooking", method = RequestMethod.POST)
	public String bookingRuangan(Model model, BookingRuangan bookingRuangan,HttpServletRequest request){
		String idRuangan = request.getParameter("idRuangan");
		Integer id = Integer.parseInt(idRuangan);
		Ruangan ruangan = rDao.getIdRuangan(id);
		User userLogin = (User) request.getSession().getAttribute("userLogin");
		bookingRuangan.setRuangan(ruangan);
		bookingRuangan.setUser(userLogin);
		bookingRuangan.setStatus("PENDING");
		model.addAttribute("booking", bDao.saveOrUpdate(bookingRuangan));
		return "redirect:/homeDosen";
	}
	
	@RequestMapping(value = "/createbookingg/{id}", method = RequestMethod.POST)
	public String editBookingRuangan(@PathVariable Integer id, Model model,HttpServletRequest request){
		BookingRuangan bookingRuangan = bDao.getIdBooking(id);
		String booking = request.getParameter("tujuanPemakaian");
		System.out.println("->"+booking);
		bookingRuangan.setDeskripsiReservasi(booking);
		model.addAttribute("booking", bDao.saveOrUpdate(bookingRuangan));
		return "redirect:/homeDosen";
	}
	
	@RequestMapping(value="/createLayanan", method = RequestMethod.POST)
	public String createLayanan(Model model, LayananDokumen layanan, HttpServletRequest request,  @RequestParam("filee") MultipartFile file){
		
	
		//Notifikasi notifikasi = new Notifikasi();
		//User user = uDao.getIdUser();
		//notifikasi.setUser(user);
		//notifikasi.setStatusRead(false);
		//notifikasi.setIsiNotifikasi("Permintaan layanan anda sudah di approve");
		//nDao.save(notifikasi);
	
		
		if (!file.isEmpty()) {
			layanan.setFile(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Materi Kuliah/Semester 6/Information System Project/projek/org.bookingruangan/src/main/resources/static/file/"+name)));
				stream.write(bytes);
				stream.close();
				System.out.println(stream);
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "The selected file was empty and could not be uploaded.";
		}
		String tesId = request.getParameter("idUser");
		Integer id = Integer.parseInt(tesId);
		User user = uDao.getIdUser(id);
		layanan.setUser(user);
		layanan.setStatusLayanan("APPROVED");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		layanan.setStartDate(dtf.format(now));
		model.addAttribute("layanan", lDao.saveLayanan(layanan));
		return "redirect:/homeBaak";

		
	}
	@RequestMapping(value="/createeLayanan", method = RequestMethod.POST)
	public String createeLayanan(Model model, LayananDokumen layanan, HttpServletRequest request){
		
		List<User> allUser = uDao.getAllUserByRole("baak");
		
		for (User user : allUser){
			Notifikasi notifikasi = new Notifikasi();
			
			notifikasi.setUser(user);
			notifikasi.setStatusRead(false);
			notifikasi.setIsiNotifikasi("Anda Menerima Permintaan Layanan");
			nDao.save(notifikasi);
		}
		
		
		userLogin = (User) request.getSession().getAttribute("userLogin");
		layanan.setStatusLayanan("PENDING");
		layanan.setUser(userLogin);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		layanan.setStartDate(dtf.format(now));
		model.addAttribute("layanan", lDao.saveLayanan(layanan));
		return "redirect:/homeDosen";
	}
	
	@RequestMapping(value="/updateNotifikasi")
	public String updateNotifikasi(Model model, HttpServletRequest request){
		Notifikasi notifikasi = new Notifikasi();
		User user = (User) request.getSession().getAttribute("userLogin");
		List <Notifikasi> notifikasii =  nDao.getAllNotifikasiByStatusReadByUser(false, user.getId());
		
		for(Notifikasi notifikasin : notifikasii){
			notifikasin.setStatusRead(true);
			nDao.save(notifikasin);
		}
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value="/editBooking/{id}")
	public String editBooking(@PathVariable Integer id, Model model,  HttpServletRequest request){
		userLogin = (User) request.getSession().getAttribute("userLogin");
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("booking", bDao.getIdBooking(id));
		return "editBooking";
		
	}
	
}
