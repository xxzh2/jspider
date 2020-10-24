/**
 *
 */
package com.ginkgo.jspider.dl;

import java.io.File;
import java.util.List;

/**
 * @author Asparagus
 *
 */
public interface Duplicate {
	
	public final static String SEPARATOR = File.separator;
	
    public String getLocalDir(String pic);

    public String getRealPath(String pic);

    List<String> getFileType();
}
