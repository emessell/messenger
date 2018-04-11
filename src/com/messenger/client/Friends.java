package com.messenger.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.omg.CORBA.INITIALIZE;

import com.messenger.common.MemberDTO;
import com.messenger.server.MessengerDAOImpl;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;

public class Friends extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel model;
	private String col[] = { "아이디", "이름", "별명" };
	private Object rowData[][];
	private MemberDTO dto;
	private MessengerDAOImpl dao;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private ArrayList<MemberDTO> temp;

	/**
	 * Create the frame.
	 */
	public Friends() {
		setTitle("\uBA54\uC778\uD654\uBA74");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 472, 26);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("로그아웃");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login();
			}
		});

		mnNewMenu.add(mntmNewMenuItem);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 38, 448, 332);
		contentPane.add(tabbedPane);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\uCE5C\uAD6C \uBAA9\uB85D", null, panel_4, null);
		panel_4.setLayout(null);

		JTree tree = new JTree();
		DefaultTreeModel treeModel;
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("친구 목록");
		DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("가족");
		DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("친구");
		DefaultMutableTreeNode root3 = new DefaultMutableTreeNode("회사");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("엄마");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("아빠");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("홍길동");
		DefaultMutableTreeNode child4 = new DefaultMutableTreeNode("사장님");
		DefaultMutableTreeNode child5 = new DefaultMutableTreeNode("팀장님");

		root.add(root1);
		root.add(root2);
		root.add(root3);

		root1.add(child1);
		root1.add(child2);
		root2.add(child3);
		root3.add(child4);
		root3.add(child5);

		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
					if (e.getClickCount() == 2)
						new Chatting();
			}
		};
		tree.addMouseListener(ml);
		tree.setBounds(0, 0, 443, 250);
		panel_4.add(tree);
		
		JButton btnNewButton_1 = new JButton("\uCE5C\uAD6C \uC0AD\uC81C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "알림", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		btnNewButton_1.setBounds(324, 261, 105, 27);
		panel_4.add(btnNewButton_1);

		JPanel panel = new JPanel();
		tabbedPane.addTab("\uCE5C\uAD6C \uCD94\uAC00", null, panel, null);
		panel.setLayout(null);

		JLabel label = new JLabel("친구 ID : ");
		label.setBounds(57, 16, 62, 18);
		panel.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(133, 13, 116, 24);
		panel.add(textField);

		JLabel label_1 = new JLabel("별명 : ");
		label_1.setBounds(57, 50, 62, 18);
		panel.add(label_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(133, 47, 116, 24);
		panel.add(textField_1);

		JLabel label_2 = new JLabel("이름 :");
		label_2.setBounds(57, 84, 62, 18);
		panel.add(label_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 81, 116, 24);
		panel.add(textField_2);

		model = new DefaultTableModel(rowData, col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setBackground(SystemColor.textHighlightText);
		table.setBounds(247, 34, 162, 180);
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 121, 248, 167);
		panel.add(scrollPane);

		JButton btnNewButton = new JButton("ID로 찾기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dd = (DefaultTableModel) table.getModel();
				dd.setNumRows(0);
				if (textField.getText().trim().equals("")) { // 아이디 입력
					textField.requestFocus();
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				 try {
					MessengerDAOImpl dao = new MessengerDAOImpl();
					temp = dao.findFriendid(textField.getText());
					for(int i=0; i<temp.size(); i++) {
						Object [] temporaryObject = {
								temp.get(i).getId(),
								temp.get(i).getName(),
								temp.get(i).getAlias()};
								dd.addRow(temporaryObject);
						}
				 }catch(SQLException e1) {
					 e1.printStackTrace();
				 }
			}
		});
		btnNewButton.setBounds(263, 12, 133, 27);
		panel.add(btnNewButton);

		JButton button = new JButton("\uCE5C\uAD6C \uCD94\uAC00");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "친구추가가 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(292, 197, 105, 27);
		panel.add(button);

		JButton button_3 = new JButton("별명으로 찾기");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dd = (DefaultTableModel) table.getModel();
				dd.setNumRows(0);
				if (textField_1.getText().trim().equals("")) { // 아이디 입력
					textField_1.requestFocus();
					JOptionPane.showMessageDialog(null, "별명을 입력해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				 try {
					MessengerDAOImpl dao = new MessengerDAOImpl();
					temp = dao.findFriendalias(textField_1.getText());
					for(int i=0; i<temp.size(); i++) {
						Object [] temporaryObject = {
								temp.get(i).getId(),
								temp.get(i).getName(),
								temp.get(i).getAlias()};
								dd.addRow(temporaryObject);
						}
				 }catch(SQLException e1) {
					 e1.printStackTrace();
				 }
			}
		});
		button_3.setBounds(263, 46, 133, 27);
		panel.add(button_3);

		JButton button_4 = new JButton("이름으로 찾기");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dd = (DefaultTableModel) table.getModel();
				dd.setNumRows(0);
				if (textField_2.getText().trim().equals("")) { // 아이디 입력
					textField_2.requestFocus();
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				 try {
					MessengerDAOImpl dao = new MessengerDAOImpl();
					temp = dao.findFriendname(textField_2.getText());
					for(int i=0; i<temp.size(); i++) {
						Object [] temporaryObject = {
								temp.get(i).getId(),
								temp.get(i).getName(),
								temp.get(i).getAlias()};
								dd.addRow(temporaryObject);
						}
				 }catch(SQLException e1) {
					 e1.printStackTrace();
				 }
			}
		});
		button_4.setBounds(263, 80, 133, 27);
		panel.add(button_4);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\uC815\uBCF4 \uC218\uC815", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("\uC544\uC774\uB514(\uBA54\uC77C\uC8FC\uC18C)");
		label_3.setBounds(14, 13, 125, 15);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label_4.setBounds(14, 41, 57, 15);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		label_5.setBounds(14, 69, 106, 15);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("\uC774\uB984");
		label_6.setBounds(14, 97, 57, 15);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("\uBCC4\uBA85");
		label_7.setBounds(14, 125, 57, 15);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("\uB098\uC774");
		label_8.setBounds(14, 209, 57, 15);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		label_9.setBounds(14, 237, 57, 15);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_10.setBounds(14, 265, 57, 15);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("\uC9C0\uC5ED");
		label_11.setBounds(14, 153, 57, 15);
		panel_1.add(label_11);
		
		JLabel label_12 = new JLabel("\uC131\uBCC4");
		label_12.setBounds(14, 181, 57, 15);
		panel_1.add(label_12);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(166, 10, 116, 21);
		panel_1.add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(166, 38, 116, 21);
		panel_1.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		passwordField_1.setBounds(166, 66, 116, 21);
		panel_1.add(passwordField_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(166, 94, 116, 21);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(166, 122, 116, 21);
		panel_1.add(textField_5);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setBounds(190, 150, 65, 21);
		panel_1.add(comboBox);
		
		JRadioButton radioButton = new JRadioButton("\uB0A8");
		radioButton.setBounds(184, 177, 42, 23);
		panel_1.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\uC5EC");
		radioButton_1.setBounds(230, 177, 42, 23);
		panel_1.add(radioButton_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(166, 206, 116, 21);
		panel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(166, 234, 116, 21);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(166, 262, 116, 21);
		panel_1.add(textField_8);
		
		JButton btnNewButton_2 = new JButton("\uC815\uBCF4\uC218\uC815");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBounds(312, 231, 105, 27);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		btnNewButton_3.setBounds(312, 259, 105, 27);
		panel_1.add(btnNewButton_3);
		setVisible(true);

	}
}
