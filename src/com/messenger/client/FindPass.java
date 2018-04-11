package com.messenger.client;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.messenger.common.MemberDTO;
import com.messenger.server.MessengerDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FindPass extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private MemberDTO dto;

	public FindPass() {
		setTitle("비밀번호 찾기");
		setBounds(100, 100, 292, 267);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 276, 187);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 252, 161);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(35, 32, 57, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_1.setBounds(35, 79, 57, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setBounds(35, 126, 57, 15);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(104, 29, 116, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(104, 76, 116, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(104, 123, 116, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 180, 276, 49);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("비밀번호 찾기");
				okButton.setBounds(54, 10, 118, 23);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							if (e.getSource() == okButton) { //버튼 클릭
								if (textField.getText().trim().equals("")) { // 이름 입력
									textField.requestFocus();
									JOptionPane.showMessageDialog(null, "아이디 필수정보 누락!", "알림",
											JOptionPane.INFORMATION_MESSAGE);
									}
								else if(textField_1.getText().trim().equals("")) {
										textField_1.requestFocus();
										JOptionPane.showMessageDialog(null, "생년월일 필수정보 누락!", "알림",
												JOptionPane.INFORMATION_MESSAGE);
									}
								else if(textField_2.getText().trim().equals("")) {
									textField_2.requestFocus();
									JOptionPane.showMessageDialog(null, "전화번호 필수정보 누락!", "알림",
											JOptionPane.INFORMATION_MESSAGE);
								}else {
										dto = new MemberDTO();
										dto.setId(textField.getText());
										dto.setBirth(textField_1.getText());
										dto.setPhone(textField_2.getText());
										
										MessengerDAOImpl dao;
										try {
											dao = new MessengerDAOImpl();
											dao.findPasswd(dto.getId(), dto.getBirth(), dto.getPhone());
										} catch (SQLException e1) {
											e1.printStackTrace();
										}
										
										dispose();
									}
						}
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setBounds(184, 10, 69, 23);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
