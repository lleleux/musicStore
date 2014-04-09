package com.laurent.musicStore.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurent.musicStore.dao.OrderDao;
import com.laurent.musicStore.dao.TrackDao;
import com.laurent.musicStore.dao.UserDao;
import com.laurent.musicStore.model.Order;
import com.laurent.musicStore.model.Track;
import com.laurent.musicStore.model.User;
import com.laurent.musicStore.service.OrderService;
import com.laurent.musicStore.utils.Mail;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TrackDao trackDao;
	
	@Resource(name = "mail")
	private Mail mail;

	@Override
	public List<Order> getAllOrders(String userName) {
		List<Order> orders = this.orderDao.getOrdersForUser(userName);
		for (Order o : orders) {
			this.orderDao.detach(o);
		}
		return orders;
	}

	@Override
	public Order addOrder(String login, int trackId) {
		User user = this.userDao.getByLogin(login);
		Track track = this.trackDao.getById(trackId);
		Order order = new Order(user, track);
		Calendar date = new GregorianCalendar();
		order.setStart(date.getTime());
		date.add(GregorianCalendar.HOUR, 1);
		order.setStop(date.getTime());
		sendConfirmationMail(order);
		return this.orderDao.persist(order);
	}
	
	private void sendConfirmationMail(Order order) {
		User user = order.getUser();
		Track track = order.getTrack();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		
		StringBuffer msg = new StringBuffer();
		msg.append("Thank you for your order !\n");
		msg.append("You just buyed :\n\n");
		msg.append("\t" + track.getTitle() + " - " + track.getArtist() + "\n");
		msg.append("\t" + track.getAlbum() + "\n");
		msg.append("\t" + track.getPrice() + "€\n\n");
		msg.append("You can listen this track until " + sdf.format(order.getStop()) + "\n\n");
		msg.append("Pleased to see you on our MusicStore again !\n\n");
		msg.append("The MusicStore staff");
		
		this.mail.sendMail("sales@musicStore.com", user.getEmail(), "Your order", msg.toString());
	}
	
}
