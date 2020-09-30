package com.ginkgo.crawl.test;

import org.junit.Test;

import com.ginkgo.jspider.HTMLDownloader;

import lombok.extern.log4j.Log4j;

@Log4j
public class HTMLDownloaderTest {

	@Test
	public void testGetLocalDir() {
		String base = new HTMLDownloader().getRealPath("aa.c");
		log.info("dir: " + base);
	}

	@Test
	public void testGetLocalFileName() {
	}

	@Test
	public void testGetLocalBase() {
		String base = new HTMLDownloader().getLocalBase();
		log.info(base);

	}

	@Test
	public void testGetFileType() {
	}

}
