package com.bjarni;

/**
 * Holds an original URL, as well as a short key that 
 * can be used to redirect to the original 
 * 
 * @author bjarni
 */
public class ShortenedUrl {

	private String original;
	private String urlKey;
	private String shortenedUrlString;
	
	public ShortenedUrl() {
	}
	
	public ShortenedUrl(String original, String urlKey) {
		this.original = original;
		this.urlKey = urlKey;
		this.shortenedUrlString = "localhost:2222/lookup/" + urlKey;
	}
	
	public String getOriginal() {
		if(original.startsWith("http://") || original.startsWith("https://")) {
			return original;
		}
		else {
			return "http://"  + original;
		}
	}
	
	public String getUrlKey() {
		return this.urlKey;
	}
	
	public String getShortenedUrlString() {
		return this.shortenedUrlString;
	}
}
