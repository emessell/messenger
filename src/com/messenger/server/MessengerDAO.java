package com.messenger.server;

import java.util.ArrayList;

import com.messenger.common.FriendsDTO;
import com.messenger.common.MemberDTO;

public interface MessengerDAO {
	
	boolean isExitstMember(String id);
	
	boolean isExitstFriend(String myId, String friendId);

	int insertMember(MemberDTO member) throws DuplicateException;
	
	MemberDTO getMember(String id);
	
	int updateMember(MemberDTO member);
	
	int deleteMember(String id);

	String findId(String name, String phone);

	String findPasswd(String id, String birth, String phone);

	int insertFriend(FriendsDTO friend);
	
	int deleteFriend(FriendsDTO friend);
	
	FriendsDTO getFriends(String myId, String friendId);

	ArrayList<MemberDTO> getFriends(String friendId);
	
	int login(String id,String passwd);
	
	ArrayList<MemberDTO> findFriend(String id);
	
	ArrayList<MemberDTO> getFriendName(String friendName);

}