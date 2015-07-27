package com.hacket.filemanager.bean;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public class FileInfo {

    private String filename;
    private String fileurl;
    private boolean isFoolder;
    private boolean isImgType;
    private boolean isRootRir;

    public FileInfo() {
    }

    public FileInfo(String filename, String fileurl, boolean isFoolder, boolean isImgType, boolean isRootRir) {
        this.filename = filename;
        this.fileurl = fileurl;
        this.isFoolder = isFoolder;
        this.isImgType = isImgType;
        this.isRootRir = isRootRir;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public boolean isFoolder() {
        return isFoolder;
    }

    public boolean isImgType() {
        return isImgType;
    }

    public boolean isRootRir() {
        return isRootRir;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public void setIsFoolder(boolean isFoolder) {
        this.isFoolder = isFoolder;
    }

    public void setIsImgType(boolean isImgType) {
        this.isImgType = isImgType;
    }

    public void setIsRootRir(boolean isRootRir) {
        this.isRootRir = isRootRir;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", isFoolder=" + isFoolder +
                ", isImgType=" + isImgType +
                ", isRootRir=" + isRootRir +
                '}';
    }
}
