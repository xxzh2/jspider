package com.ginkgo.jspider.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlUtils {
	/**
	 * 合并URL, 去除首尾重合的部分；
	 * @param a1
	 * @param b1
	 * @return
	 */
	public static String mergeJoin(String a1, String b1) {
		List<String> a = new ArrayList<String>(Arrays.asList(a1.split("/")));
		List<String> b = new ArrayList<String>(Arrays.asList(b1.split("/")));
		b.remove("");
		for (int i = 1; i < Math.min(a.size(), b.size()); i++) {
			List<String> sa = a.subList(a.size() - i, a.size());
			List<String> sb = b.subList(0, i);
			if (Arrays.equals(sa.toArray(), sb.toArray())) {
				sa.removeAll(sb);
				if (sa.size() == 0) {
					a.addAll(b);
					break;
				}
			}
		}
		return String.join("/", a);
	}
}
