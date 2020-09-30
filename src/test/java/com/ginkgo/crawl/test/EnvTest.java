package com.ginkgo.crawl.test;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class EnvTest {

	@Test
	public void main() {
		// TODO Auto-generated method stub
		// PropertyConfigurator.configure("log4j.properties") ;
		// BingPicture pic = new BingPicture();
		// // 设置URL
		// pic.setUrl("http://cn.bing.com");
		// // 搜索并下载图片
		// pic.download();

		log.info(System.getProperty("crawl_home"));
		log.info(System.getProperties().keySet());
		log.info(System.getenv("crawl_home"));
	}

}
