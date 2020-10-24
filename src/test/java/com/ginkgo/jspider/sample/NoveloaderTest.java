package com.ginkgo.jspider.sample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ginkgo.jspider.loader.BookLoader;
import com.ginkgo.jspider.util.UrlUtils;

public class NoveloaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test() {

		BookLoader pic = new BookLoader("望族风流");
		// 设置URL
		pic.setUrl("https://www.37zw.net/6/6884/");
		// 搜索并下载
		pic.download(new WZSectionIndicator(), new WzContentIndcator());
	}

	@Test
	public void test_chapter() {
		BookLoader pic = new BookLoader("白鹿原");
		// 设置URL
		pic.setUrl("https://www.xstt5.com/mingzhu/755/");
		// 搜索并下载
		pic.download(new BlySectionIndicator(), new BlyContentIndcator());
	}

	public void test_1() {
		String a1 = "https://www.xstt5.com/mingzhu/755/";
		String b1 = "/mingzhu/755/20526.html";
		UrlUtils.mergeJoin(a1, b1);
	}

	
}
