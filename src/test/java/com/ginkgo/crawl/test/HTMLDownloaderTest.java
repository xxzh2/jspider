package com.ginkgo.crawl.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ginkgo.jspider.dl.HTMLDownloader;

public class HTMLDownloaderTest {
	private Log log = LogFactory.getLog(this.getClass());

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
