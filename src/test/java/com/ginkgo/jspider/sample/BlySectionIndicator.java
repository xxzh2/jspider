package com.ginkgo.jspider.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;

import com.ginkgo.jspider.crawl.document.PrasedDocument;
import com.ginkgo.jspider.indicator.SectionIndicator;

public class BlySectionIndicator implements SectionIndicator {

	PrasedDocument doc;

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
		Element el = doc.getElementById("yuedu");
//		log.info(el.html());
		for (Iterator<?> iterator = el.getElementsByAttribute("href").iterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
//			log.info(e.html());
//			log.info(e.attr("href"));
//			if (e.html().contains(extendsKey)) {
			Element chapterEl = e;
			chapterEl.attr("href", mergeJoin(doc.getUrl(), e.attr("href")));
			chapterEl.attr("name", e.text());
			resList.add(chapterEl);
//			}
		}
		return resList;
	}

	public String mergeJoin(String a1, String b1) {
//		String a1 = "https://www.xstt5.com/mingzhu/755/";
//		String b1 = "/mingzhu/755/20526.html";
		List<String> a = new ArrayList<String>(Arrays.asList(a1.split("/")));
		List<String> b = new ArrayList<String>(Arrays.asList(b1.split("/")));
		b.remove("");
		for (int i = 1; i < Math.min(a.size(), b.size()); i++) {
			List<String> sa = a.subList(a.size() - i, a.size());
			List<String> sb = b.subList(0, i);
			if (Arrays.equals(sa.toArray(), sb.toArray())) {
				sa.removeAll(sb);
				if (sa.size() == 0) {
					a.addAll(b);
					break;
				}
			}
		}
		return String.join("/", a);
	}

	@Override
	public void setDocument(PrasedDocument doc) {
		this.doc = doc;
	}

}
