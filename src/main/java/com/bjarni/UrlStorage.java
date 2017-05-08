package com.bjarni;

import java.util.List;

/** 
 * Storage to store and retrieve URL data 
 * 
 * @author bjarni
 */
public interface UrlStorage {

	/**
	 Stores a coupling between a key and the original URL, returns 
	 a short URL that will redirect to the original URL */
	public String addUrlPair(String originalUrl, String urlKey);
	
	/**
	 Returns original URL, given the shortened representation */
	public String getOriginalUrl(String urlKey);
	
	/**
	 Returns a list of all currently registered URLs  */
	public List<UrlShortening> getAllRegisteredUrls();
}
