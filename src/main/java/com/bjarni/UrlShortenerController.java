package com.bjarni;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlShortenerController {

	@RequestMapping("/foo")
	@ResponseBody
	public String foo() {
		return "bar";
	}
	
	@RequestMapping(value = "/visit/{url}",method = RequestMethod.GET)
	public RedirectView forward(@PathVariable("url") String url) {	    
		RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://" + url);
	    return redirectView;
	}
	
	@RequestMapping("/to-be-redirected")
	public RedirectView localRedirect() {
	    RedirectView redirectView = new RedirectView();
	    redirectView.setUrl("http://www.yahoo.com");
	    return redirectView;
	}
}
