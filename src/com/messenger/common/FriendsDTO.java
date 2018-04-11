package com.messenger.common;

public class FriendsDTO {
	
	private String myid, friendid;

	
	public FriendsDTO() {}
	public FriendsDTO(String myid, String friendid) {
		super();
		this.myid = myid;
		this.friendid = friendid;
	}
	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getFriendid() {
		return friendid;
	}
	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}
	@Override
	public String toString() {
		return "FriendsDTO [myid=" + myid + ", friendid=" + friendid + "]";
	}

}
