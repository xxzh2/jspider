// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Localization.java

package com.ginkgo.jspider.crawl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LocalizationHTML extends Localization {
	public LocalizationHTML(File file, String string) {
		super(file, null);
		local = file;
		str = string;

	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	final static Log log = LogFactory.getLog(LocalizationHTML.class);

	@Override
	public boolean localize() {
		if (local == null) {
			log.debug("Local can't null, error!");
			return false;
		}
		if (!local.exists()) {
			log.debug(
					(new StringBuilder("Create  parent director:")).append(local.getParentFile().getPath()).toString());
			local.getParentFile().mkdirs();
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(local, true);
			writer.write(str);
			writer.write("\n");
			writer.flush();
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e);
				}
		}

		return true;
	}

	@Override
	protected InputStream getInputStream() throws IOException {
		if (str != null)
			return new ByteArrayInputStream(str.getBytes());
		else
			return null;
	}

	private String str;
	private File local;

}
