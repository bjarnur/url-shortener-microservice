package com.bjarni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private int urlCounter = 0;
	
	/**
	 Given an URL, this endpoint maps the URL to a shortened version  and 
	 replies with a shortened URL that can be used to access the original */
	@RequestMapping(value = "/create/{url}", method = RequestMethod.GET)
	public String CreateUrlShortening(@PathVariable("url") String url) {
		String urlKey = Integer.toString(urlCounter++); 
		return urlStorage.addUrlPair(url, urlKey);
	}
	
	/**
	 Looks up previously mapped URLs based on the provided shorthand. If 
	 mapping is found user will be redirected to the original URL */
	@RequestMapping(value = "/lookup/{url}", method = RequestMethod.GET)
	public RedirectView lookup(@PathVariable("url") String url) {	    
		
		String originalUrl = urlStorage.getOriginalUrl(url);
		RedirectView redirectView = new RedirectView();
	    if(originalUrl != null) {
	    	redirectView.setUrl("http://" + originalUrl);
	    } 
	    return redirectView;
	}
	
	/**
	 Returns a collection of all mapped URLs and their shorter versions */
	@RequestMapping("/all-urls")
	public UrlShortening[] allUrls() {
		List<UrlShortening> urls = urlStorage.getAllRegisteredUrls();
		return urls.toArray(new UrlShortening[urls.size()]);		
	}
}
