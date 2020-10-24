package com.ginkgo.jspider.loader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.dl.DefaultDownloader;
import com.ginkgo.jspider.indicator.ContentIndicator;

public class ChapterLoader {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * Version: {@value #VER}
	 */
	static final double VER = 1.0;
	private URL thisUrl;
	private String fileExt = ".txt";
	private String name;
	private ContentIndicator indicator;

	public ChapterLoader(ContentIndicator indicator, String name) {
		this.indicator = indicator;
		this.name = name;
	}

	public void download(String fileName) {
		Optional<String[]> chapters = Optional.ofNullable(indicator.seekContent());
		chapters.ifPresent(contents -> {
			for (String u : contents) {
				log.debug("fileName->" + fileName);
				File basefile = new File(new DefaultDownloader().getLocalDefault(), name);
				File fc = new File(basefile, fileName + fileExt);
				try {
					fc.getParentFile().mkdirs();
					fc.createNewFile();
					log.info(fc.getAbsolutePath());
					FileWriter fw = new FileWriter(fc);
					fw.write(u);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
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

	String seekById(String id) {
		PrasedDocument doc = new PrasedDocument(this.thisUrl);
		Element el = doc.getElementById(id);
		log.debug(el.text());
		return el.html();
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
			PrasedDocument doc = new PrasedDocument(this.thisUrl);
			indicator.setDocument(doc);
		} catch (MalformedURLException e) {
			log.error(e);
			this.thisUrl = null;
		}
	}
}
