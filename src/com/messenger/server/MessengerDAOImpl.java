package com.messenger.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.messenger.client.FindId;
import com.messenger.client.FindPass;
import com.messenger.common.FriendsDTO;
import com.messenger.common.MemberDTO;

public class MessengerDAOImpl extends JDialog implements MessengerDAO {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public MessengerDAOImpl() throws SQLException {
		conn = DataBaseUtil.getConnection();
	}

	// 멤버 가져오기
	@Override
	public MemberDTO getMember(String id) {

		MemberDTO member = new MemberDTO();

		try {
			conn = DataBaseUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select id,passwd,name,alias,loc,sex,age,birth,phone ").append("from member ")
					.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();
			member.setId(rs.getString("id"));
			member.setPasswd(rs.getString("passwd"));
			member.setName(rs.getString("name"));
			member.setAlias(rs.getString("alias"));
			member.setLoc(rs.getString("loc"));
			member.setSex(rs.getString("sex"));
			member.setAge(rs.getString("age"));
			member.setBirth(rs.getString("birth"));
			member.setPhone(rs.getString("phone"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		System.out.println(member);
		return member;

	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pstmt = null;
		rs = null;
		conn = null;
	}

	
	public int insertMember(MemberDTO member) throws DuplicateException {

		if (isExitstMember(member.getId())) {
			throw new DuplicateException();
		}

		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member ").append("(id,passwd,name,alias,loc,sex,age,birth,phone) ")
				.append("values (?,?,?,?,?,?,?,?,?)");
		try {

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAlias());
			pstmt.setString(5, member.getLoc());
			pstmt.setString(6, member.getSex());
			pstmt.setString(7, member.getAge());
			pstmt.setString(8, member.getBirth());
			pstmt.setString(9, member.getPhone());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			DataBaseUtil.printSQLException(e, "int insertMember(MemberDTO member)");
		} finally {
			DataBaseUtil.close(conn, pstmt, rs);
		}

		return cnt;
	}

	
	@Override
	public String findId(String name, String phone) {

		String id = null;

		MemberDTO member = new MemberDTO();

		try {
			conn = DataBaseUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select id ").append("from member ").append("where name = ? and phone = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();

			rs.next();
			member.setId(rs.getString("id"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		if (member.getId() == null) {
			JOptionPane.showMessageDialog(null, "찾는 아이디가 없습니다..", "알림", JOptionPane.INFORMATION_MESSAGE);
			new FindId();
		} else {
			JOptionPane.showMessageDialog(null, "아이디를 확인하였습니다.! \nID : " + member.getId(), "알림",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return id;

	}
	
	@Override
	public String findPasswd(String id, String birth, String phone) {

		String passwd = null;

		MemberDTO member = new MemberDTO();

		try {
			conn = DataBaseUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select passwd ").append("from member ").append("where id = ? and birth = ? and phone = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();

			rs.next();
			member.setPasswd(rs.getString("passwd"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(conn, pstmt, rs);
		}
		if (member.getPasswd() == null) {
			JOptionPane.showMessageDialog(null, "비밀번호를 찾을 수 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			new FindPass();
		} else {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인하였습니다.! \nPassword : " + member.getPasswd(), "알림",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return passwd;
	}

	@Override
	public FriendsDTO getFriends(String myId, String friendId) {
		FriendsDTO friends = null;
		return friends;
	}

	@Override
	public ArrayList<MemberDTO> getFriends(String friendId) {
		ArrayList<MemberDTO> friends = null;
		return friends;
	}

	@Override
	public int updateMember(MemberDTO member) {

		return 0;
	}

	@Override
	public int deleteMember(String id) {

		return 0;
	}

	@Override
	public int insertFriend(FriendsDTO friend) {

		return 0;
	}

	@Override
	public int deleteFriend(FriendsDTO friend) {

		return 0;
	}

	@Override
	public boolean isExitstMember(String id) {
		boolean isExitstMempber = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select id from member where id = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			isExitstMempber = rs.next();
		} catch (SQLException e) {
			DataBaseUtil.close(pstmt, rs);
		}
		return isExitstMempber;
	}

	@Override
	public boolean isExitstFriend(String myId, String friendId) {
		boolean isExitstFriend = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select id from member t1, friends t2 where t2.myId = ? and t2.friendId = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, myId);
			pstmt.setString(2, friendId);
			rs = pstmt.executeQuery();
			isExitstFriend = rs.next();
		} catch (SQLException e) {
			DataBaseUtil.close(pstmt, rs);
		}
		return isExitstFriend;
	}

	@Override
	public int login(String id, String passwd) {
		// boolean b = false;
		MemberDTO member = new MemberDTO();
		int cnt = 0;
		try {
			MessengerDAO dao = new MessengerDAOImpl();
			// b = dao.isExitstMember(id);
			StringBuffer sql = new StringBuffer();
			sql.append("select id,passwd ").append("from member ").append("where id = ? and passwd = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = 1;
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
			}

			// System.out.println(b);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return cnt;
	}

	public ArrayList<MemberDTO> findFriendid(String id) {
		ArrayList<MemberDTO> abc = new ArrayList<MemberDTO>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id,name,alias ").append("from member ").append("where id like ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAlias(rs.getString(3));
				abc.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return abc;
	}
	
	public ArrayList<MemberDTO> findFriendname(String name) {
		ArrayList<MemberDTO> abc = new ArrayList<MemberDTO>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id,name,alias ").append("from member ").append("where name like ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAlias(rs.getString(3));
				abc.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return abc;
	}
	
	public ArrayList<MemberDTO> findFriendalias(String alias) {
		ArrayList<MemberDTO> abc = new ArrayList<MemberDTO>();
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id,name,alias ").append("from member ").append("where alias like ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, alias);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAlias(rs.getString(3));
				abc.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return abc;
	}

	public MemberDTO findFriend_1(String alias) {
		MemberDTO member = new MemberDTO();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id,name,alias ").append("from member ").append("where alias = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, alias);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAlias(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return member;
	}

	public MemberDTO findFriend_2(String name) {
		MemberDTO member = new MemberDTO();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select id,name,alias ").append("from member ").append("where name = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setAlias(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return member;
	}

	@Override
	public ArrayList<MemberDTO> getFriendName(String friendName) {
		return null;
	}

	@Override
	public ArrayList<MemberDTO> findFriend(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
