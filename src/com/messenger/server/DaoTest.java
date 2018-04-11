package com.messenger.server;

import com.messenger.common.MemberDTO;

public class DaoTest {

	public static void main(String[] args) {
		MessengerDAO md = null;
		try {
			md = new MessengerDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MemberDTO m = md.getMember("admin");
		
		m.toString();
		System.out.println("------");
		
		m.getAge();
		m.getName();
		
		
	}

}
