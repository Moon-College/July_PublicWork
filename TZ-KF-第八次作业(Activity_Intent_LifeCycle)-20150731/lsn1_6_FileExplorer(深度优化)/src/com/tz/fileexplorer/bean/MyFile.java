package com.tz.fileexplorer.bean;

import java.io.File;

public class MyFile {

	private File file;
	private String name;

	public MyFile(File file) {
		super();
		this.file = file;
	}

	public MyFile(File file, String name) {
		super();
		this.file = file;
		this.name = name;
	}

	public String getName() {
		if (name == null || "".equals(name)) {
			name = file.getName();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}
	
	public String getPath() {
		return file.getAbsolutePath();
	}

	public boolean isIcon() {
		String fileName = file.getName();
		String exName = null;
		try {
			exName = fileName.substring(fileName.lastIndexOf(".") + 1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "png".equals(exName) || "jpg".equals(exName) || "jpeg".equals(exName);
	}
}
