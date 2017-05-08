package com.bjarni;

import org.springframework.stereotype.Component;

/**
 * Holds an original URL, as well as a short key that 
 * can be used to redirect to the original 
 * 
 * @author bjarni
 */
@Component
public class ShortenedUrl {

	private String original;
	private String urlKey;
	private String shortenedUrlString;
	
	public ShortenedUrl() {
	}
	
	public ShortenedUrl(String original, String urlKey, String hostName, String portNumber) {
		this.original = original;
		this.urlKey = urlKey;
		this.shortenedUrlString = hostName + ":" + portNumber + "/lookup/" + urlKey;
	}
	
	public String getOriginal() {
		if(original == null) {
			return null;
		}
		else if(original.startsWith("http://") || original.startsWith("https://")) {
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
