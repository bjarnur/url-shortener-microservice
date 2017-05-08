package com.bjarni;

import java.util.List;

/** 
 * Storage to store and retrieve URL data 
 * 
 * @author bjarni
 */
public interface UrlStorage {
	
	/**
	 Creates, persists and returns a {@link ShortenedUrl} that contains
	 information about how the original URL has been mapped to a shorter version */
	public ShortenedUrl createShortenedUrl(String originalUrl, String urlKey);
	
	/**
	 Returns original URL, given the shortened representation */
	public String getOriginalUrl(String urlKey);
	
	/**
	 Returns a list of all currently registered URLs  */
	public List<ShortenedUrl> getAllRegisteredUrls();
}
