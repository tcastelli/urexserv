package com.urexst.model;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Student implements Serializable{


	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	@Id private String email;
	private String password;
	private String country;
	private String housing;
	private List<String> groupList;
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

	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}

	public List<String> getGroupList() {
		return groupList;
	}

	

	
 
	
}
