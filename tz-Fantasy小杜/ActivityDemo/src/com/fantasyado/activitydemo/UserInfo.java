package com.fantasyado.activitydemo;

import android.os.Parcel;
import android.os.Parcelable;

/**  
 * created at:2015年8月3日 下午2:12:21  
 * project name:ActivityDemo  
 * @author Fantasy ado  
 * @version 1.0  
 * @since JDK 1.7  
 * File name:UserInfo.java  
 * description:  
 */

public class UserInfo implements Parcelable {
	
	private String username;
	private String password;
//	private UserInfo mData;
	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password
				+ "]";
	}
	 
	public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
      //  out.writeInt(mData);
        out.writeString(username);
        out.writeString(password);
    }

    public static final Parcelable.Creator<UserInfo> CREATOR
            = new Parcelable.Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    
    private UserInfo(Parcel in) {
       // mData = in.readInt();
    	username = in.readString();
    	password = in.readString();
    }

 

}
