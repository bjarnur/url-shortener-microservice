package com.bjarni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * A dummy implementation of {@link UrlStorage} stores pairs of
 * Original-Shortened URLs in memory.
 * 
 * @author bjarni
 */
@Repository
public class UrlStorageDummy implements UrlStorage {

	@Value("${url.micro.hostname}")
	private String hostName;
	
	@Value("${server.port}")
	private String portNumber;
	
	Map<String, ShortenedUrl> shortToOriginalUrlMap = new HashMap<String, ShortenedUrl>();
	
	@Override
	public ShortenedUrl createShortenedUrl(String originalUrl, String urlKey) {
		ShortenedUrl pair = new ShortenedUrl(originalUrl, urlKey, hostName, portNumber);
		shortToOriginalUrlMap.put(urlKey, pair);
		return pair;
	}

	@Override
	public String getOriginalUrl(String urlKey) {
		ShortenedUrl shortenedUrl = shortToOriginalUrlMap.get(urlKey);
		return shortenedUrl == null ? "" : shortenedUrl.getOriginal();
	}

	public List<ShortenedUrl> getAllRegisteredUrls() {
		return new ArrayList<ShortenedUrl>(shortToOriginalUrlMap.values());
	}

}
