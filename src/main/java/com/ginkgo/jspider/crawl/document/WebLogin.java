// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   WebLogin.java

package com.ginkgo.jspider.crawl.document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebLogin {
	private Log log = LogFactory.getLog(this.getClass());
	
	public WebLogin(URL url) {
		this.setConnection(url);
	}

	public void connect() {
		this.connection.disconnect();
		try {
			this.connection.connect();
		} catch (IOException e) {
			log.error(e);
		}
	}

	private void setConnection(URL url) {
		try {
			this.connection = (HttpURLConnection) url.openConnection();
			this.connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		} catch (IOException e) {
			log.error(e);
		}
	}

	public void read() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.connection.getInputStream(), "UTF8"));
		this.connection.connect();
		for (String line = br.readLine(); line != null; line = br.readLine())
			System.out.println(line);

	}

	public void cookie() throws IOException {
		String cookieVal = this.connection.getHeaderField("Set-Cookie");
		if (cookieVal != null) {
		}

	}

	public void jcookie() {
	}

	public void setPost() throws IOException {
		this.connection.setDoOutput(true);
		this.connection.setRequestMethod("POST");
		this.connection.setRequestProperty("User-Agent", "Mozilla/4.7 [en] (Win98; I)");
		StringBuffer sb = new StringBuffer();
		sb.append("uname=xu.meng");
		sb.append("pass=nneThE");
		OutputStream os = this.connection.getOutputStream();
		os.write(sb.toString().getBytes("UTF8"));
		os.close();
	}

	public HttpURLConnection getConnection() {
		return this.connection;
	}

	private HttpURLConnection connection;
}
