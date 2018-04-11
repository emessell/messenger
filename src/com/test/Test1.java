package com.test;

import java.sql.SQLException;

import org.junit.Test;

import com.messenger.server.MessengerDAO;
import com.messenger.server.MessengerDAOImpl;

public class Test1 {
	@Test
	public void test1() {
		Boolean b = null;
		try {
			MessengerDAO mdao = new MessengerDAOImpl();
			
			b = mdao.isExitstMember("asdf");
			System.out.println(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
