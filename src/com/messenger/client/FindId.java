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

public class FindId extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private MemberDTO dto;
	

	public FindId() {
		setTitle("\uC544\uC774\uB514 \uCC3E\uAE30");
		setBounds(100, 100, 278, 183);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 262, 102);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\uC774\uB984");
			lblNewLabel.setBounds(35, 28, 57, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(115, 25, 116, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\uC804\uD654\uBC88\uD638");
			lblNewLabel_1.setBounds(35, 69, 57, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(115, 66, 116, 21);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 104, 262, 41);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("아이디 찾기");
				okButton.setBounds(55, 5, 110, 23);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == okButton) { //버튼 클릭
							if (textField.getText().trim().equals("")) { // 이름 입력
								textField.requestFocus();
								JOptionPane.showMessageDialog(null, "이름 필수정보 누락!", "알림",
										JOptionPane.INFORMATION_MESSAGE);
								}
							else if(textField_1.getText().trim().equals("")) {
									textField_1.requestFocus();
									JOptionPane.showMessageDialog(null, "전화번호 필수정보 누락!", "알림",
											JOptionPane.INFORMATION_MESSAGE);
								}else {
									dto = new MemberDTO();
									dto.setName(textField.getText());
									dto.setPhone(textField_1.getText());
									
									MessengerDAOImpl dao;
									try {
										dao = new MessengerDAOImpl();
										dao.findId(dto.getName(),dto.getPhone());
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
				cancelButton.setBounds(177, 5, 69, 23);
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
