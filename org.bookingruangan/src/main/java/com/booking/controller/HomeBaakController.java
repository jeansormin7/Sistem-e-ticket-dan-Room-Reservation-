package com.booking.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booking.dao.BookingDao;
import com.booking.dao.KlasifikasiDao;
import com.booking.dao.LayananDokumenDao;
import com.booking.dao.PasswordDaoImpl;
import com.booking.dao.RuanganDao;
import com.booking.dao.UserDao;
import com.booking.model.BookingRuangan;
import com.booking.model.Klasifikasi;
import com.booking.model.Ruangan;
import com.booking.model.User;



@Controller
public class HomeBaakController {
	
	private PasswordDaoImpl password;
	private LayananDokumenDao lDao;
	private RuanganDao rDao;
	private KlasifikasiDao kDao;
	private UserDao uDao;
	private BookingDao bDao;
	private User userLogin;
	
	
	
	
	@Autowired
	public void setbDao(BookingDao bDao) {
		this.bDao = bDao;
	}



	@Autowired
	public void setPassword(PasswordDaoImpl password) {
		this.password = password;
	}
	


	@Autowired
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
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
	
	@RequestMapping(value="/homeBaak/kelolaLayanan/{id}", method = RequestMethod.GET)
	public String kelolaLayanan(@PathVariable Integer id, Model model){
		model.addAttribute("layanan", lDao.getIdLayanan(id));
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value = "/homeBaak/updateLayanan/{id}")
	public String updateStatusLayanan(@PathVariable int id){
		lDao.updateStatusLayananDokumenById(id);
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value="/createRuangan", method = RequestMethod.POST)
	public String tambahRuangan(Model model, Ruangan ruangan){
		model.addAttribute("ruangan", rDao.saveOrUpdate(ruangan));
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value="/createKlasifikasi", method = RequestMethod.POST)
	public String tambahKlasifikasi(Model model, Klasifikasi klasifikasi){
		model.addAttribute("klasifikasi", kDao.saveOrUpdate(klasifikasi));
		return "redirect:/homeBaak";
	}
	
	@RequestMapping(value="/deleteRuangan/{id}")
	public String deleteRuangan(@PathVariable Integer id){
		rDao.deleteRuangan(id);
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value="/editRuangan/{id}", method = RequestMethod.GET)
	public String editRuangan(@PathVariable Integer id, Model model, HttpServletRequest request){
		userLogin = (User) request.getSession().getAttribute("userLogin");
		model.addAttribute("ruangan", rDao.getIdRuangan(id));
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("klasifikasi", new Klasifikasi());
		model.addAttribute("ruangans", rDao.getAllRuangan());
		model.addAttribute("layanan", lDao.getAllLayananDokumen());
		model.addAttribute("layananc", lDao.getAllLayananDokumen());
		model.addAttribute("layanan", lDao.getAllLayananDokumenbyStatus("PENDING"));
		model.addAttribute("history", lDao.getHistoryLayananDokumen());
		return "homeBaak";
		
	}
	
	
	

	
	@RequestMapping(value="/lihatLayanan/{id}", method = RequestMethod.GET)
	public String lihatLayanan(@PathVariable Integer id, Model model){
		model.addAttribute("layanan", lDao.getIdLayanan(id));
		return "lihatLayanan";
	}
	
	

	
	@RequestMapping(value="/detailLayanan/{id}", method = RequestMethod.GET )
	public String detailLayanann(@PathVariable Integer id, Model model,  HttpServletRequest request){
		userLogin = (User) request.getSession().getAttribute("userLogin");
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("layanan" , lDao.getIdLayanan(id));
		return "lihatLayanan";
	}
	
	@RequestMapping(value="/createUser", method = RequestMethod.POST)
	public String tambahUser(Model model, User user){
		System.out.println("->"+user.getPassword());
		String hashedPassword = password.encode(user.getPassword());
		user.setPassword(hashedPassword);
		model.addAttribute("user", uDao.saveOrUpdate(user));
		return "redirect:/homeBaak";
		
	}
	
	@RequestMapping(value="/updateStatusBooking/{id}")
	public String updateStatusBooking(@PathVariable int id){
		bDao.updateStatusBookingById(id);
		return "redirect:/homeBaak";
	}
	
	@RequestMapping(value="/updateStatusBookings/{id}")
	public String updateStatusBookings(@PathVariable int id){
		bDao.updateStatusBookingByIds(id);
		return "redirect:/homeBaak";
	}


}
