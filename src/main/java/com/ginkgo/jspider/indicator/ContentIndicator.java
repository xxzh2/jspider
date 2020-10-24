package com.ginkgo.jspider.indicator;

import com.ginkgo.jspider.crawl.document.PrasedDocument;

/**
 * 正文解析
 * @author Think
 *
 */
public interface ContentIndicator {

	public PrasedDocument getDocument();

	public void setDocument(PrasedDocument doc);

	public String[] seekContent();
}
