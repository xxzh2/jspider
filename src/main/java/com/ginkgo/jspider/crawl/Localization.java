package com.ginkgo.jspider.crawl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Copy remote File to local Disk.
 *
 * @author Asparagus
 *
 */
public class Localization {
	final static Log log = LogFactory.getLog(Localization.class);

	public Localization(String path, URL url) {
		destPath = path;
		srcUrl = url;
		initFile();
	}

	public Localization(String path, String url) {
		destPath = path;
		try {
			srcUrl = new URL(url);
		} catch (MalformedURLException e) {
			log.error(e);
		}
		initFile();
	}

	public void initFile() {
		if (isValid())
			local = new File(destPath);
		if (local != null && !local.exists()) {
			try {
				if (!local.exists()) {
					if(!local.getParentFile().exists()) {
						local.getParentFile().mkdirs();
					}
					if(local.createNewFile()) {
						log.debug(String.format("Create Newfile: %s", local.getPath()));
					}
				}
				log.debug(String.format("%s", local.getPath()));
				
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

	public Localization(File file, URL url) {
		local = file;
		srcUrl = url;
	}

	public boolean localize() {

		if (local == null) {
			log.debug("Local can't null, error!");
			return false;
		}
		if (!local.exists()) {
			log.info(String.format("Create  parent director: %s", local.getParentFile().getPath()));
			local.getParentFile().mkdirs();
		}
		(new Thread() {

			@Override
			public void run() {
				FileOutputStream fo = null;
				InputStream in = null;
				try {
					fo = new FileOutputStream(local);
					in = getInputStream();
					for (byte b[] = new byte[1]; in.read(b) > 0; fo.write(b))
						;
					fo.flush();

					if(local.createNewFile()) {
						log.info("createNewFile");
					}
					log.debug((new StringBuilder(String.valueOf(local.getPath()))).append(" \u521B\u5EFA!").toString());
				} catch (FileNotFoundException e) {
					log.error(e);
				} catch (IOException e) {
					log.error(e);
				} finally {
					try {
						if (fo != null)
							fo.close();
					} catch (IOException e) {
						log.error(e);
					}
					try {
						if (in != null)
							in.close();
					} catch (IOException e) {
						log.error(e);
					}
				}
			}

		}).start();
		return true;
	}

	protected InputStream getInputStream() throws IOException {
		if (isValid())
			return srcUrl.openStream();
		else
			return null;
	}

	public boolean isValid() {
		if (srcUrl != null) {
			if (destPath != null && destPath.trim().length() != 0)
				return true;
			return local != null;
		} else {
			return false;
		}
	}

	private URL srcUrl;
	private String destPath;
	private File local;

}
