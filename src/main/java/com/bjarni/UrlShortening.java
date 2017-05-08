package com.bjarni;

/**
 * Holds an original URL, as well as a short key that 
 * can be used to redirect to the original 
 * 
 * @author bjarni
 */
public class UrlShortening {

	private String original;
	private String urlKey;
	
	public UrlShortening(String original, String urlKey) {
		this.original = original;
		this.urlKey = urlKey;
	}
	
	public String getOriginal() {
		return this.original;
	}
	
	public String getShortenedUrl() {
		return "localhost:2222/lookup/" + urlKey;
	}
}
