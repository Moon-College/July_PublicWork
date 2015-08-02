package com.tz.file.bean;


public class FileBean {
	private String name;//ÎÄ¼þÃû³Æ
	private int imageId;//
	private String path;
	private boolean isImage;
	public FileBean(String name, int imageId, String path, boolean isImage) {
		super();
		this.name = name;
		this.imageId = imageId;
		this.path = path;
		this.isImage = isImage;
	}
	public FileBean() {
		super();
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getImageId()
	{
		return imageId;
	}
	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public boolean isImage()
	{
		return isImage;
	}
	public void setImage(boolean isImage)
	{
		this.isImage = isImage;
	}

	
	
	
	
}
