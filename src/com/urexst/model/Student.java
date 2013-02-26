package com.urexst.model;

import java.io.Serializable;
import java.util.List;


public class Student implements Serializable{

	private String name;
	private String surname;
	private String email;
	private String password;
	private String country;
	private String housing;
	private List<Group> groupList;
	private MessageInbox inbox; 
	private NotificationInbox notifications;
	
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setHousing(String housing) {
		this.housing = housing;
	}

	public String getHousing() {
		return housing;
	}

	public void setInbox(MessageInbox inbox) {
		this.inbox = inbox;
	}

	public MessageInbox getInbox() {
		return inbox;
	}

	public void setNotifications(NotificationInbox notifications) {
		this.notifications = notifications;
	}

	public NotificationInbox getNotifications() {
		return notifications;
	}

	
 
	
}
