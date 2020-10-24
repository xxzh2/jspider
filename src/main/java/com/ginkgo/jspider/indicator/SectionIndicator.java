package com.ginkgo.jspider.indicator;

import java.util.List;

import org.jsoup.nodes.Element;

import com.ginkgo.jspider.crawl.document.PrasedDocument;

/**
 * 标题解析器
 */
public interface SectionIndicator {
	
	public PrasedDocument getDocument();
	
	public void setDocument(PrasedDocument doc);
	
	public List<Element> seekSection();
}
