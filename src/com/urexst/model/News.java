package com.urexst.model;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class News {
	
	@Id private Long date;
	@Index private String author;
	private String subject;
	private List<String> groupList;
	private String type; //text, html
	private String EnglishTxt;
	private String FrenchTxt;
	private String SpanishTxt;
	private String GermanTxt;
	private String ItalianTxt;
	public void setDate(Long date) {
		this.date = date;
	}
	public Long getDate() {
		return date;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}
	
	public void setEnglishTxt(String englishTxt) {
		EnglishTxt = englishTxt;
	}
	public String getEnglishTxt() {
		return EnglishTxt;
	}
	public void setFrenchTxt(String frenchTxt) {
		FrenchTxt = frenchTxt;
	}
	public String getFrenchTxt() {
		return FrenchTxt;
	}
	public void setSpanishTxt(String spanishTxt) {
		SpanishTxt = spanishTxt;
	}
	public String getSpanishTxt() {
		return SpanishTxt;
	}
	public void setGermanTxt(String germanTxt) {
		GermanTxt = germanTxt;
	}
	public String getGermanTxt() {
		return GermanTxt;
	}
	public void setItalianTxt(String italianTxt) {
		ItalianTxt = italianTxt;
	}
	public String getItalianTxt() {
		return ItalianTxt;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setGroupList(List<String> groupList) {
		this.groupList = groupList;
	}
	public List<String> getGroupList() {
		return groupList;
	}
	
	
	
	
	
	

}
