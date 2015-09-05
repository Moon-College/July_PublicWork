package com.tz.fileexplorer.utils;

import java.io.File;
import java.text.Collator;
import java.util.Comparator;

/**
 * 
 * @author wztscau
 * @since 2015Äê9ÔÂ5ÈÕ
 *
 */
public class FileCompartor implements Comparator<File> {

	@Override
	public int compare(File lhs, File rhs) {
		String s1 = lhs.getAbsolutePath();
		String s2 = rhs.getAbsolutePath();
		return Collator.getInstance().compare(s1, s2);
	}

}
