package com.cloudbees.examples.todo.api;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;

/**
 * JAXB xml adapter for Joda LocalDate for Moxy
 * 
 * @author apemberton
 * 
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return new LocalDate(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}
