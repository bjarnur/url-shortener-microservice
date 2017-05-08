package com.bjarni;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Before;

/**
 * Tests to cover {@link UrlStorage}
 * 
 * @author bjarni
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlStorageTest {

	private static final String TEST_URL_VALUE = "http://www.foo.com";
	private static final String TEST_URL_KEY_0 = "0";
	private static final String TEST_URL_KEY_100 = "100";
	private static final String TEST_URL_RESULT = "localhost:2222/lookup/100";
	
	UrlStorage urlStorage;
	
	@Before
	public void setup() {
		this.urlStorage = new UrlStorageDummy(); //TODO: Replace with real implementation when ready 
		urlStorage.createShortenedUrl(TEST_URL_VALUE, TEST_URL_KEY_0);
	}
	
	@Test
	public void testAddUrlPair() {		
		String expectedRedirectUrl = TEST_URL_RESULT;
		ShortenedUrl result = urlStorage.createShortenedUrl(TEST_URL_VALUE, TEST_URL_KEY_100);
		Assert.assertEquals(expectedRedirectUrl, result.getShortenedUrlString());
	}

	@Test
	public void testGetOriginalUrl() {
		String expectedUrl = TEST_URL_VALUE;
		String actualUrl = urlStorage.getOriginalUrl(TEST_URL_KEY_0);
		Assert.assertEquals(expectedUrl, actualUrl);
	}
	
	@Test
	public void testGetAllRegisteredUrls() {
		List<ShortenedUrl> result = urlStorage.getAllRegisteredUrls();
		Assert.assertEquals(1, result.size());
	}
}
