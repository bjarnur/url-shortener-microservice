package com.bjarni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The main interface of <url-shortener-microservice> allows users to produce
 * and use shortened versions of URLs. Additionally the service can provide
 * overview over all currently registered URLs. 
 * 
 * @author bjarni
 */
@RestController
public class UrlShortenerController {

	@Autowired
	UrlStorage urlStorage;	
	
	/**
	 * Given an original URL this endpoint maps it to a hashed value, which can 
	 * be used in other endpoints to redirect to the original URL
	 * 
	 * @param Json representation of {@link {@link ShortenedUrl} where at least 
	 * the <code>original</code> field has been set
	 * @return Json representation of {@link {@link ShortenedUrl} containing 
	 * information about original URL and shortened URL
	 */
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<ShortenedUrl> create (@RequestBody ShortenedUrl requestBody) {

		if(requestBody != null) {
			String originalUrl = requestBody.getOriginal();
			if(originalUrl != null && !originalUrl.isEmpty()) {
				String urlKey = Integer.toString(originalUrl.hashCode());
				ShortenedUrl result = urlStorage.createShortenedUrl(originalUrl, urlKey);
				return new ResponseEntity<ShortenedUrl>(result, HttpStatus.OK); 
			}			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/**
	 Looks up previously mapped URLs based on path argument. If mapping 
	 is found user will be redirected to the original URL, if no 
	 URL is found a BAD_REQUEST response will be sent. */
	@RequestMapping(value = "/lookup/{urlKey}", method = RequestMethod.GET)
	public Object lookup(@PathVariable("urlKey") String urlKey) {	    
		
		String originalUrl = urlStorage.getOriginalUrl(urlKey);
		if(originalUrl == null || originalUrl.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		RedirectView redirectView = new RedirectView();
    	redirectView.setUrl(originalUrl);
	    return redirectView;
	}
	
	/**
	 Returns a collection of all mapped URLs and their shorter versions */
	@RequestMapping("/all-urls")
	public ShortenedUrl[] allUrls() {
		List<ShortenedUrl> urls = urlStorage.getAllRegisteredUrls();
		return urls.toArray(new ShortenedUrl[urls.size()]);		
	}
}
