package com.messenger.client;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import com.messenger.common.MemberDTO;
import com.messenger.server.MessengerDAOImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	private MemberDTO dto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public Login() {
		setTitle("\uB85C\uADF8\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 394);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("img/beach.jpeg"));
		lblNewLabel.setBounds(36, 10, 363, 212);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(63, 234, 57, 15);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(132, 231, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(63, 270, 57, 15);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JPasswordField();
		textField_1.setBounds(132, 267, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().trim().equals("")) { // 아이디 입력
					textField.requestFocus();
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
				} else if (String.valueOf(textField_1.getPassword()).trim().equals("")) { // 비밀번호 입력
					textField_1.requestFocus();
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dto = new MemberDTO();
					String id = dto.setId(textField.getText());
					String passwd = dto.setPasswd(String.valueOf(textField_1.getPassword()));

					MessengerDAOImpl dao;
					try {
						dao = new MessengerDAOImpl();
						if (dao.login(id, passwd) == 1) {
							setVisible(false);
							new Friends();
						} else {
							JOptionPane.showMessageDialog(null, "가입된 회원정보가 없습니다.", "알림",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(275, 234, 97, 51);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join();
			}
		});
		btnNewButton_1.setBounds(36, 308, 97, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("ID 찾기");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindId();
			}
		});
		btnNewButton_2.setBounds(169, 308, 97, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("PW 찾기");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindPass();
			}
		});
		btnNewButton_3.setBounds(302, 308, 97, 23);
		contentPane.add(btnNewButton_3);
	}
}
