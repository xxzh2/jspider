package com.ginkgo.jspider.crawl.document;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parsed Document. {@value #log}
 * 
 * @author Asparagus
 *
 */
public class PrasedDocument {

	static final Log log = LogFactory.getLog("PrasedDocument");

	private Document doc;
	private String url;
	private String charset = "UTF-8";

	public PrasedDocument(URL arg0) {
		doc = null;
		url = null;
		url = arg0.toString();
		init();
	}

	public PrasedDocument(URL arg0, String charset) {
		doc = null;
		url = null;
		if (charset != null)
			this.charset = charset;
		url = arg0.toString();
		init();
	}

	public PrasedDocument(String arg0) {
		doc = null;
		url = null;
		url = arg0;
		init();
	}

	public PrasedDocument(String arg0, String charset) {
		doc = null;
		url = null;
		url = arg0;
		this.charset = charset;
		init();
	}

	/**
	 * Check Website Charset Cookie Session and so on.
	 */
	private void init() {
		log.info(String.format("This URL   :[%s]", url));
		Connection _connection = Jsoup.connect(url);
		String _charset = null;
		String _contentType = null;
		String _httpequiv = null;
		Map<?, ?> _headers = null;
		Map<?, ?> _cookies = null;
		_connection.timeout(52000);
		org.jsoup.Connection.Response _response = null;
		try {
			_response = _connection.execute();
			_charset = _response.charset();
			_contentType = _response.contentType();
			_headers = _response.headers();
			_cookies = _response.cookies();
			doc = Jsoup.parse((new URL(url)).openStream(), charset, url);
			_httpequiv = doc.getElementsByAttribute("http-equiv").attr("content");

		} catch (IOException e) {
			log.error(e);
		}
		log.info(new StringBuilder().append("charset:" + _charset)//
				.append("\nresponse:" + _response)//
				.append("\ncontentType:" + _contentType)//
				.append("\nhttpequiv:" + _httpequiv)//
				.append("\nheaders:" + _headers)//
				.append("\ncookies:" + _cookies)//
		);

	}

	public Elements getElementsByTag(String tagName) {
		if (doc != null)
			return doc.getElementsByTag(tagName);
		else
			return null;
	}

	public Elements getElementsByClass(String className) {
		if (doc != null)
			return doc.getElementsByClass(className);
		else
			return null;
	}

	public Element getElementById(String id) {
		if (doc != null)
			return doc.getElementById(id);
		else
			return null;
	}

	public void removeElements(String nodeName) {
		Elements flinks = doc.getAllElements();
		for (Iterator<?> iterator = flinks.iterator(); iterator.hasNext();) {
			Element _element = (Element) iterator.next();
			String _nodeName = _element.nodeName();
			if (_nodeName.endsWith(nodeName))
				_element.remove();
		}

	}

	public String html() {
		String html = null;
		html = doc.html();
		return html;
	}

	public String getUrl() {
		return this.url;
	}

}
