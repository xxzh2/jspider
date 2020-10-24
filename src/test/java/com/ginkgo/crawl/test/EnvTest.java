package com.ginkgo.crawl.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class EnvTest {
	private Log log = LogFactory.getLog(this.getClass());

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
