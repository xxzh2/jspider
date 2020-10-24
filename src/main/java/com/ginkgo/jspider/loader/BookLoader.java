package com.ginkgo.jspider.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.indicator.ContentIndicator;
import com.ginkgo.jspider.indicator.SectionIndicator;

/**
 * Download Bing Background Image.
 * 
 * @version {@value #VER}
 * @author Asparagus 2016-08-26
 *
 */
public class BookLoader {
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Version: {@value #VER}
	 */
	static final double VER = 1.0;
	private URL thisUrl;
	private String novel = "noname";
	String charset;

	public BookLoader() {
	}

	public BookLoader(String ext, String charset) {
		novel = ext;
		this.charset = charset;
	}

	public BookLoader(String ext) {
		novel = ext;
	}

	List<String> getPicByKey(Element e, String extendsKey) {
		List<String> picPath = new ArrayList<>();
		String str = e.html();
		String st[] = str.split(",");
		String as[];
		int j = (as = st).length;
		for (int i = 0; i < j; i++) {
			String s = as[i];
			if (s.contains(extendsKey)) {
				final String regexp = "[^\"';]*/[^\"';]*?" + extendsKey;
				Pattern p = Pattern.compile(regexp);
				Matcher m = p.matcher(s);
				while (m.find()) {
					String _s = m.group();
					log.debug("FILE: " + _s);
					if (_s.contains(extendsKey)) {
						picPath.add(_s.replace("\\", ""));
					}
				}
			}
		}
		return picPath;
	}

	public void download(SectionIndicator indicator, ContentIndicator ci) {
		PrasedDocument doc = new PrasedDocument(this.thisUrl, charset);
		indicator.setDocument(doc);
		List<Element> list = new ArrayList<>();
		list.addAll(indicator.seekSection());
		log.info("jpg file num: " + list.size());
		list.stream().filter(item -> {
			String s = item.attr("href");
			return s.startsWith("http");
		}).forEach(item -> {
			ChapterLoader chal = new ChapterLoader(ci, novel);
			log.info(item.attr("href"));
			// 设置URL
			chal.setUrl(item.attr("href"));
			// 搜索并下载
			chal.download(String.format("%03d %s", list.indexOf(item) + 1, item.attr("name")));
			log.info(String.format("%03d %s", list.indexOf(item) + 1, item.attr("name")));
		});
	}

	public URL getUrl() {
		return this.thisUrl;
	}

	public void searchPathByTag(Document doc) {
		this.searchPathByTag(doc, ".jpg");
	}

	public void searchPathByTag(Document doc, String tagName) {
		log.debug(doc.getElementsContainingText(tagName).html());
	}

	public void setUrl(String arg0) {
		try {
			this.thisUrl = new URL(arg0);
		} catch (MalformedURLException e) {
			log.error(e);
			this.thisUrl = null;
		}
	}

}
