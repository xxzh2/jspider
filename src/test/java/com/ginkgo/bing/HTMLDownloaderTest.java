package com.ginkgo.bing;

import org.junit.Test;

import com.ginkgo.jspider.HTMLDownloader;

public class HTMLDownloaderTest {

	@Test
	public void test_getLocalDefault() {
		new HTMLDownloader().getLocalDefault();
	}
	
	@Test
	public void test_getLocalBase() {
		new HTMLDownloader().getLocalBase();
	}

}
