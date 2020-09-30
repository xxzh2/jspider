package com.ginkgo.jspider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class Downloader implements Duplicate {
	/**
	 * Get default base path.
	 *
	 * @return basePath
	 */
	public String getLocalDefault() {
		String downloaderDefault = null;
		Properties prop = null;
		try {
			prop = load();
			downloaderDefault = prop.getProperty("downloader.default", System.getProperty("user.dir"));
			// System.out.println("Default: " + downloaderDefault);
		} catch (IOException e) {
			log.error(e);
		}
		//
		return downloaderDefault == null ? System.getProperty("user.home") : downloaderDefault;
	}

	public Properties load() throws IOException {
		Properties prop = new Properties();
		InputStream ins = null;
		try {
			ins = this.getClass().getResourceAsStream("/conf.properties");
			prop.load(ins);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
		for (Object key : prop.keySet()) {
			String keyVal = prop.getProperty(key.toString());
			if (keyVal.contains("$")) {
				log.debug(keyVal);
				Pattern p = Pattern.compile("\\$\\{[^\\{\\}]{1,256}\\}");
				log.debug(p.pattern());
				Matcher matcher = p.matcher(keyVal);
				if (matcher.find()) {
					String m = matcher.group();
					log.debug(m.substring(2, m.length() - 1));
					String envKey = m.substring(2, m.length() - 1);
					log.debug(System.getProperty(envKey));
					prop.put(key, keyVal.replace(m, System.getProperty(envKey)));
				}
			}
		}
		return prop;
	}
}
