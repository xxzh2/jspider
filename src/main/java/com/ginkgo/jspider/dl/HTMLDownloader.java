/**
 * File Download.<br>
 * download file for given URL or content.<br>
 * @author Asparagus
 */
package com.ginkgo.jspider.dl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Download for HTML files.
 *
 * @author Asparagus 2016-08-27
 *
 */
public class HTMLDownloader extends Downloader {
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Accepted File Extend.
	 */
	protected final static String[] FILE_TYPE = { ".htm", ".html" };

	public final static String SEPARATOR = System.getProperty("file.separator");

	public final static String ARCHIVED_NAME = "html-archive";

	/**
	 * Default Constructor
	 */
	public HTMLDownloader() {
	}

	@Deprecated
	public String getLocalDir(String pic) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Get file path for new file with extend.
	 *
	 * @return filePath
	 */
	public String getArchivedDirectory(String pic) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		String dateStr = sdf.format(new Date());
		StringBuilder filePath = new StringBuilder();//
		filePath.append(SEPARATOR)//
				.append(ARCHIVED_NAME)//
				.append(SEPARATOR).toString();
		// current file name or path is not valid.
		// then given the specified name.
		if (pic == null) {
			return filePath.toString();
		} else if (pic.contains("*") || pic.contains(":") || pic.contains("/")) {
			String name = dateStr + ".html";
			filePath.append(dateStr)//
					.append(SEPARATOR)//
					.append(name);
		} else {
			String name = "unnamed";
			if (pic.indexOf("/") > 0) {
				String str[] = pic.split("/");
				name = str[str.length - 1];
			} else if (pic.indexOf(".") < 0) {
				name += ".html";
			}
			filePath.append(dateStr)//
					.append(SEPARATOR)//
					.append(name);
		}
		return filePath.toString();
	}

	public String getLocalFileName(String pic) {
		// current file name or path is not valid. then given the specified
		// name.
		if (pic == null) {
			return null;
		} else if (pic.contains("*") || pic.contains(":") || pic.contains("/")) {
			int lastIndex = pic.lastIndexOf(".");
			String name = pic.substring(lastIndex);
			return name;
		} else {
			return pic;
		}
	}

	/**
	 * Local Context Base Path.
	 *
	 * @return basePath of WEB-INF
	 */
	public String getLocalBase() {
		String path = HTMLDownloader.class.getResource("/").getPath();
		if (path != null && path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF"));
		}
		return path;
	}

	@Override
	public List<String> getFileType() {
		return null;
	}

	/**
	 * Return the full path.
	 *
	 * @param pic
	 * @return fullPath
	 */
	@Override
	public String getRealPath(String pic) {
		final String SEPARATOR = System.getProperty("file.separator");
		String basePath = this.getLocalBase();
		if (basePath != null && basePath.endsWith(SEPARATOR)) {
			basePath = basePath.substring(0, basePath.lastIndexOf(SEPARATOR));
		}
		String path = null;
		try {
			path = URLDecoder.decode(basePath + this.getArchivedDirectory(pic), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		log.warn("URLDecode: " + path);
		return path;
	}
}
