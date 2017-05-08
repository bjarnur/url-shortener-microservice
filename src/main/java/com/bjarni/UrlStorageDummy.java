package com.bjarni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * A dummy implementation of {@link UrlStorage} stores pairs of
 * Original-Shortened URLs in memory.
 * 
 * @author bjarni
 */
@Repository
public class UrlStorageDummy implements UrlStorage {

	Map<String, UrlShortening> shortToOriginalUrlMap = new HashMap<String, UrlShortening>();

	@Override
	public String addUrlPair(String originalUrl, String urlKey) {
		UrlShortening pair = new UrlShortening(originalUrl, urlKey);
		shortToOriginalUrlMap.put(urlKey, pair);
		return pair.getShortenedUrl();
	}

	@Override
	public String getOriginalUrl(String urlKey) {
		UrlShortening pair = shortToOriginalUrlMap.get(urlKey);
		return pair == null ? "" : pair.getOriginal();
	}

	public List<UrlShortening> getAllRegisteredUrls() {
		return new ArrayList<UrlShortening>(shortToOriginalUrlMap.values());
	}

}
