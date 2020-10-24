/**
 * File Download.<br>
 * download file for given URL or content.<br>
 * @author Asparagus
 */
package com.ginkgo.jspider.dl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ImageDownloader extends Downloader {
	private Log log = LogFactory.getLog(this.getClass());

	final static String[] FILE_TYPE = { ".png", ".jpg" };

	final static String WALLPAPER = "Bing-wallpaper";

	public ImageDownloader() {
	}

	/**
	 * get relative file path.
	 */
	@Override
	public String getLocalDir(String pic) {

		String basePath = this.getLocalDefault();
		log.debug("getLocalDefault basePath->" + basePath);
		if (pic == null) {
			return null;

		} else if (pic.contains("*") || pic.contains("?") || pic.contains(":") || pic.contains("/")) {
			String fileExt = null;
			if (pic.contains(".")) {
				fileExt = pic.substring(pic.lastIndexOf("."));

				if (!this.isAccepted(fileExt))
					fileExt = FILE_TYPE[1];

			} else {
				fileExt = FILE_TYPE[0];
			}
			String fname = pic.substring(pic.lastIndexOf("/"));
			fname = getRegularFlileName(fname);
			log.info("fileName =\t" + fname);
			return new File(basePath, fname).getAbsolutePath();

		} else {
			String str[] = pic.split("/");
			String name = str[str.length - 1];
			name = getRegularFlileName(name);
			return new File(basePath, name).getAbsolutePath();
		}
	}

	private String getRegularFlileName(String name) {
		List<String> strList = new ArrayList<String>();
		String str = name + "";
		log.info(str);
		String ragex = "[^\";\\=]+.jpg";
		Pattern p = Pattern.compile(ragex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String s = m.group();
			if (s.length() > 0) {
				strList.add(s);
				log.debug("-->" + s + "|");
			}
		}
		return strList.get(0);
	}

	@Override
	public List<String> getFileType() {
		return null;
	}

	/**
	 * Check fileExt can accepted.
	 */
	private boolean isAccepted(String fileExt) {
		boolean isAccepted = false;
		for (String ft : FILE_TYPE) {
			if (ft.equals(fileExt)) {
				isAccepted = true;
			}
		}
		return isAccepted;
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
}
