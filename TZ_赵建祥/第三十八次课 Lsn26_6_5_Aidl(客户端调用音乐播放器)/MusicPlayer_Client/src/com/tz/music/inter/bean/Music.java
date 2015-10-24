package com.tz.music.inter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable{
	public String title;
	public String singer;
	
	public Music() {
	}
	
	public Music(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}
	
	public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(singer);
    }

	public static final Parcelable.Creator<Music> CREATOR
            = new Parcelable.Creator<Music>() {
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
    
    private Music(Parcel in) {
        title = in.readString();
        singer = in.readString();
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", singer=" + singer + "]";
	}
}
