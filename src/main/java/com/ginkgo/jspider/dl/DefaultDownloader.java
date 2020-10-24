/**
 * File Download.<br>
 * download file for given URL or content.<br>
 * @author Asparagus
 */
package com.ginkgo.jspider.dl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultDownloader extends Downloader {
	private Log log = LogFactory.getLog(this.getClass());

	static final String DEFAULT_CATEGORY = "/Documents/jspider";
	private String defaultStorePath = System.getProperty("user.home", "/");

	public DefaultDownloader() {
	}

	/**
	 * get relative file path.
	 */
	@Override
	public String getLocalDefault() {
		return new File(defaultStorePath, DEFAULT_CATEGORY).getPath();
	}

	@Override
	public List<String> getFileType() {
		log.warn("Unsupport method.");
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
		return getLocalDir(pic);
	}

	@Override
	public String getLocalDir(String pic) {
		// TODO Auto-generated method stub
		return null;
	}
}
