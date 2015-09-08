package com.tz.fileexplorer.bean;

import java.io.File;

import android.graphics.Bitmap;

public class BaseFile extends File {

	private static final long serialVersionUID = -7525597748514099010L;
	private boolean isIcon;
	private Bitmap icon;
	private String path;
	private String name;
	private boolean isEmpty;

	public BaseFile() {
		this("");
	}

	public BaseFile(String path) {
		super(path);
	}

	public BaseFile(String name, String path, Bitmap icon, boolean isIcon) {
		super(path);
		this.name = name;
		this.path = path;
		this.icon = icon;
		this.isIcon = isIcon;
	}

	public boolean isIcon() {
		return isIcon;
	}

	public void setIcon(boolean isIcon) {
		this.isIcon = isIcon;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "myfile [name=" + name + " path=" + path + " icon=" + icon
				+ " isIcon=" + isIcon + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isIcon ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseFile other = (BaseFile) obj;
		if (isIcon != other.isIcon)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	public boolean isEmpty() {
		if(isFile()){
			return false;
		}
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public String getPathExceptName(){
		String s = getPath();
		int index = s.lastIndexOf(File.separator)+1;
		return s.substring(0, index);
	}

}
