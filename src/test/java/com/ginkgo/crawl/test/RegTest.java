package com.ginkgo.crawl.test;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

/**
 * RegExp Tests
 * 
 * @author Think
 *
 */
@Log4j
public class RegTest {

//	@Test
	public void main() {
		String str = "t)})();g_img={url: \"http://s.cn.bing.net/az/hprichbg/rb/NazcaLines_ZH-CN10481196093_1920x1080.jpg\"";
		log.info(str);
		String ragex = "[^\";]*";
		Pattern p = Pattern.compile(ragex);
		Matcher m = p.matcher(str);
	
		while (m.find()) {
			String s = m.group();
			if (s.length() > 0)
				log.debug("-->" + s + "|");
		}
	}
	
	@Test
	public void getName() {
		String str = "/th?id=OHR.BigWindDay_ZH-CN1837859776_1920x1080.jpgu0026rf=LaDigue_1920x1080.jpgu0026pid=hp";
		log.info(str);
		String ragex = "[^\";\\=]+.jpg";
		Pattern p = Pattern.compile(ragex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String s = m.group();
			if (s.length() > 0)
				log.debug(MessageFormat.format("-->{0} '{0}' '{' '}' |", s));
		}
	}

}
