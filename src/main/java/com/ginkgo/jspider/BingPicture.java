package com.ginkgo.jspider;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ginkgo.jspider.crawl.Localization;
import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.dl.ImageDownloader;

/**
 * Download Bing Background Image.
 * 
 * @version {@value #VER}
 * @author Asparagus 2016-08-26
 *
 */
public class BingPicture {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * Version: {@value #VER}
	 */
	static final double VER = 1.0;
	private URL picUrl;
	private String[] exts = new String[] { ".jpg", ".png" };

	public BingPicture() {
	}

	public BingPicture(String... ext) {
		exts = (String[]) ArrayUtils.addAll(exts, ext);
	}

	public void download() {
		Optional<URL[]> imgUrls = this.getPicRealPath();
		imgUrls.ifPresent(urls -> {
			for (URL u : urls) {
				String pic = u.toString();
				log.debug(String.format("Image URL: %s", pic));
				String filePath = new ImageDownloader().getRealPath(pic);
				log.debug("filePath->" + filePath);
				Localization fc = new Localization(filePath, u);
				fc.localize();
			}
		});
	}

	private List<String> getPicByKey(Element e, String extendsKey) {
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

	private List<String> getPicByKey(String extendsKey) {
		List<String> picPath = new ArrayList<String>();
		PrasedDocument doc = new PrasedDocument(this.picUrl);
		Elements elements = doc.getElementsByTag("script");
		for (Iterator<?> iterator = elements.iterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			if (e.html().contains(extendsKey)) {
				picPath.addAll(getPicByKey(e, extendsKey));
			}
		}
		return picPath;
	}

	public Optional<URL[]> getPicRealPath() {
		List<String> list = new ArrayList<>();
		if (exts == null) {
			log.warn("No ext is defined, then return.");
			return Optional.ofNullable(null);
		}
		for (String ext : exts) {
			list.addAll(this.getPicByKey(ext));
		}
		URL[] url = null;
		log.info("jpg file num: " + list.size());
		url = list.stream().filter(item -> {
			String s = item.toString();
			return !s.contains("http:");
		}).map(item -> {
			URL u = null;
			String s = item.toString();
			log.info("jpg file s: " + s);
			try {
				s = MessageFormat.format("{0}{1}", "http://cn.bing.com", s);
				u = new URL(s);
			} catch (MalformedURLException e) {
				u = null;
				log.error(e);
			} finally {
				// --
			}
			return u;
		}).toArray(URL[]::new);
		return Optional.ofNullable(url);
	}

	public URL getUrl() {
		return this.picUrl;
	}

	public void searchPathByTag(Document doc) {
		this.searchPathByTag(doc, ".jpg");
	}

	public void searchPathByTag(Document doc, String tagName) {
		log.debug(doc.getElementsContainingText(tagName).html());
	}

	public void setUrl(String arg0) {
		try {
			this.picUrl = new URL(arg0);
		} catch (MalformedURLException e) {
			log.error(e);
			this.picUrl = null;
		}
	}

}
