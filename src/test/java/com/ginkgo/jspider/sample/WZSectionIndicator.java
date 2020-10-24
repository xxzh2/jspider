package com.ginkgo.jspider.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;

import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.indicator.SectionIndicator;

public class WZSectionIndicator implements SectionIndicator{
	
	PrasedDocument doc ;
	public PrasedDocument getDocument() {
		return doc;
	}

	@Override
	public List<Element> seekSection() {
		List<Element> resList = new ArrayList<>();
//		Elements elements = doc.getElementsByTag("script");
//		for (Iterator<?> iterator = elements.iterator(); iterator.hasNext();) {
//			Element e = (Element) iterator.next();
//			if (e.html().contains(extendsKey)) {
//				picPath.addAll(getPicByKey(e, extendsKey));
//			}
//		}
//		log.info(doc.html());
		Element el = doc.getElementById("list");
//		log.info(el.html());
		for (Iterator<?> iterator = el.getElementsByAttribute("href").iterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
//			log.info(e.html());
//			log.info(e.attr("href"));
//			if (e.html().contains(extendsKey)) {
			Element chapterEl = e;
			chapterEl.attr("href", doc.getUrl() + e.attr("href"));
			chapterEl.attr("name", e.text());
			resList.add(chapterEl);
//			}
		}
		return resList;
	}
	

	@Override
	public void setDocument(PrasedDocument doc) {
		this.doc = doc;
	}

}
