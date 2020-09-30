package com.ginkgo.bing;

import org.junit.Before;
import org.junit.Test;

import com.ginkgo.jspider.BingPicture;

import lombok.extern.log4j.Log4j;

@Log4j
public class BingPictureTest {

	@Before
	public void init() {
	}

	@Test
	public void test() {
		BingPicture pic = new BingPicture();
		// 设置URL
		pic.setUrl("https://cn.bing.com");

		// 搜索并下载
		pic.download();
		log.info("end of main");
	}

	@Test
	public void test1() {
		BingPicture pic = new BingPicture(".gif", ".awt");
		// 设置URL
		pic.setUrl("https://www.baidu.com");

		// 搜索并下载
		pic.download();
		log.info("end of main");
	}

}
