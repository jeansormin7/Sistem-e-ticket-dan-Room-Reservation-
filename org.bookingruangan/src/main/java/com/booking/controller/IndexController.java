package com.booking.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.booking.dao.*;
import com.booking.model.BookingRuangan;
import com.booking.model.Klasifikasi;
import com.booking.model.LayananDokumen;
import com.booking.model.Notifikasi;
import com.booking.model.Ruangan;
import com.booking.model.User;
@Controller
public class IndexController {
	
	private LayananDokumenDao lDao;
	private UserDao uDao;
	private RuanganDao rDao;
	private KlasifikasiDao kDao;
	private NotifikasiDao nDao;
	private BookingDao bDao;
	
	private User userLogin;
	
	

	
	@Autowired
	public void setnDao(NotifikasiDao nDao) {
		this.nDao = nDao;
	}

	@Autowired
	public void setbDao(BookingDao bDao) {
		this.bDao = bDao;
	}


	@Autowired
	public void setkDao(KlasifikasiDao kDao) {
		this.kDao = kDao;
	}


	@Autowired
	public void setrDao(RuanganDao rDao) {
		this.rDao = rDao;
	}


	@Autowired
	public void setlDao(LayananDokumenDao lDao) {
		this.lDao = lDao;
	}


	@Autowired
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}


	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("userLogin", new User());
		return"login";
	}
	@RequestMapping("/coba")
	public String coba(Model model){
		return"coba";
	}
	
	@RequestMapping("/homeDosen")
	public String homeDosen(Model model){
		LayananDokumen layanan = new LayananDokumen();
		layanan.setId_user(userLogin.getId());		
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("allLayanan", lDao.getAllLayakanDokumenByIdUser(userLogin.getId()));
		model.addAttribute("allBooking", bDao.getAllBookingByIdUser(userLogin.getId()));
		model.addAttribute("booking", new BookingRuangan());
		model.addAttribute("layanan", new LayananDokumen());
		model.addAttribute("ruangan", rDao.getAllRuangan());
		model.addAttribute("klasifikasi", kDao.getAllKlasifikasi());
		return"homeDosen";
	}
	
	
	
	@RequestMapping("/homeBaak")
	public String homeBaak(Model model, HttpServletRequest request){
		Notifikasi notifikasi = new Notifikasi();
		User user = (User) request.getSession().getAttribute("userLogin");
		model.addAttribute("notifikasi", nDao.getAllNotifikasiByStatusReadByUser(false, user.getId()));
		
		model.addAttribute("userLogin", userLogin);		
		model.addAttribute("ruangan", new Ruangan());
		model.addAttribute("user", new User());
		model.addAttribute("klasifikasi", new Klasifikasi());
		model.addAttribute("booking", new BookingRuangan());
	
		
		model.addAttribute("booking", bDao.getAllBooking());
		model.addAttribute("bookings", bDao.getAllBookingByStatus("PENDING"));
		model.addAttribute("ruangans", rDao.getAllRuangan());
		model.addAttribute("layanan", lDao.getAllLayananDokumen());
		model.addAttribute("layananc", lDao.getAllLayananDokumen());
		model.addAttribute("layanan", lDao.getAllLayananDokumenbyStatus("PENDING"));
		model.addAttribute("history", lDao.getHistoryLayananDokumen());
		
		return"homeBaak";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userLogin");
		return "redirect:/index";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request){
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		if(uDao.login(username, password) != null){
			request.getSession().setAttribute("userLogin", uDao.login(username, password));
			userLogin = (User) request.getSession().getAttribute("userLogin");
			if(userLogin.getRole().equals("dosen")){
				return "redirect:/homeDosen";
			}else if(userLogin.getRole().equals("baak")){
				return "redirect:/homeBaak";
			}
			
		}
		return "redirect:/index";
		
	}
	
	@RequestMapping("/editBookingg")
	public String editBooking(){
		return"editBooking";
	}
}
