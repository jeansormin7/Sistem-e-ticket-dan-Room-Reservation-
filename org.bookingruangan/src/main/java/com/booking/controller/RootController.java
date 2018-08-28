package com.booking.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.SmtpMailSender;
import com.booking.dao.BookingDao;
import com.booking.dao.LayananDokumenDao;
import com.booking.dao.UserDao;
import com.booking.model.BookingRuangan;
import com.booking.model.LayananDokumen;
import com.booking.model.User;

@RestController
public class RootController {
	
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private LayananDokumenDao lDao;
	
	@Autowired
	private BookingDao bDao;
	
	
	
	@RequestMapping("/send-mail/{id}")
	public String sendMail(@PathVariable int id, HttpServletRequest request, Model model) throws MessagingException{
		//smtpMailSender.send("jeanreinhard8@gmail.com", "Test Spring Mail", "Jean");
		
		BookingRuangan bookingRuangan = bDao.getIdBooking(id);
		
		
		String kata = "Kepada Saudara  "+bookingRuangan.getUser().getUsername()+"<br/><br/>"+
						"Permintaan Reservasi Ruangan dengan no ruangan "+bookingRuangan.getRuangan().getIdRuangan()+" Anda dotolak, silahkan booking ruangan lain <br/>"
						+ "Terimakasih";
		
		smtpMailSender.send(bookingRuangan.getUser().getEmail(),"Reservasi Ruangan di tolak", kata);
		return "redirect:/homeBaak";
		
	}
}
