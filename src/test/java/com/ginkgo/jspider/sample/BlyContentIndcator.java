package com.ginkgo.jspider.sample;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.indicator.ContentIndicator;

public class BlyContentIndcator implements ContentIndicator {
	private Log log = LogFactory.getLog(this.getClass());

	PrasedDocument doc ;
	public PrasedDocument getDocument() {
		return doc;
	}

	@Override
	public String[] seekContent() {
		List<String> list = new ArrayList<>();
		list.add(doc.getElementsByClass("zw").html());
		String[] url = null;
		log.info("jpg file num: " + list.size());
		url = list.stream().filter(item -> {
			String s = item.toString();
			return s.length() > 10;
		}).map(item -> {
			return item.replace("&nbsp;", "").replaceAll("<br\\s+/>", "\n").replace("(三七中文 www.37zw.net)", "").replaceAll("[\n\r]{1,}", "\n");
		}).toArray(String[]::new);
		return url;
	}
	

	@Override
	public void setDocument(PrasedDocument doc) {
		this.doc = doc;
	}
}
