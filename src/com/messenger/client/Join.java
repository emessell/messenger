package com.messenger.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.messenger.common.MemberDTO;
import com.messenger.server.MessengerDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Join extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private String strsex;
	private MemberDTO dto;

	public Join() {
		setTitle("\uD68C\uC6D0\uAC00\uC785");
		setBounds(100, 100, 378, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(25, 12, 308, 337);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\uC544\uC774\uB514(\uBA54\uC77C\uC8FC\uC18C)");
		label.setBounds(12, 17, 125, 15);
		panel.add(label);

		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label_1.setBounds(12, 49, 57, 15);
		panel.add(label_1);

		JLabel label_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		label_2.setBounds(12, 81, 106, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\uC774\uB984");
		label_3.setBounds(12, 113, 57, 15);
		panel.add(label_3);

		JLabel label_4 = new JLabel("\uBCC4\uBA85");
		label_4.setBounds(12, 145, 57, 15);
		panel.add(label_4);

		JLabel label_5 = new JLabel("\uB098\uC774");
		label_5.setBounds(12, 241, 57, 15);
		panel.add(label_5);

		JLabel label_6 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		label_6.setBounds(12, 273, 57, 15);
		panel.add(label_6);

		JLabel label_7 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_7.setBounds(12, 305, 57, 15);
		panel.add(label_7);

		JLabel label_8 = new JLabel("\uC9C0\uC5ED");
		label_8.setBounds(12, 177, 57, 15);
		panel.add(label_8);

		JLabel label_9 = new JLabel("\uC131\uBCC4");
		label_9.setBounds(12, 209, 57, 15);
		panel.add(label_9);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(164, 12, 116, 21);
		panel.add(textField);

		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(164, 44, 116, 21);
		panel.add(textField_1);

		textField_2 = new JPasswordField();
		textField_2.setColumns(10);
		textField_2.setBounds(164, 76, 116, 21);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(164, 108, 116, 21);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(164, 140, 116, 21);
		panel.add(textField_4);

		String[] item = { "����", "����", "����", "�뱸", "�λ�", "���", "û��", "����", "���", "��õ" };
		JComboBox comboBox = new JComboBox(item);
		comboBox.setBounds(188, 172, 65, 21);
		panel.add(comboBox);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton radioButton = new JRadioButton("��");
		bg.add(radioButton);
		radioButton.setBounds(182, 203, 42, 23);
		panel.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("��");
		bg.add(radioButton_1);
		radioButton_1.setBounds(228, 203, 42, 23);
		panel.add(radioButton_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(164, 236, 116, 21);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(164, 268, 116, 21);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(164, 300, 116, 21);
		panel.add(textField_7);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\uAC00\uC785");
				okButton.addActionListener(new ActionListener() {
					boolean flag = false;

					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == okButton) {
							if (radioButton.isSelected()) {
								strsex = "��";
							} else if (radioButton_1.isSelected()) {
								strsex = "��";
							} else {
								
							}

							// ��ȿ�� üũ

							String p = new String("\\w+@\\w+\\.\\w+(\\.\\w+)?");
							String p1 = new String("^(?=.*?[a-z])(?=.*?[0-9]).{6,16}$");
							String p2 = new String("[1]?[1-9]?[1-9]");
							String p3 = new String("\\d{6}");
							String p4 = new String("(02|031|032|033|041|042|043|044|051|052|053|054|055|"
									+ "061|062|063|064|010|011|016|017|018|019)\\d{7,8}");
							String p5 = new String("^[��-�R]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$");

							if (e.getSource() == okButton) { // Ŭ������ ��
								if (textField.getText().trim().equals("")) { // ���̵�
									textField.requestFocus();
									JOptionPane.showMessageDialog(null, "[���̵�] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p, textField.getText().trim())) {
									JOptionPane.showMessageDialog(null, "�ùٸ� ���̵� ������ �ƴմϴ�.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (String.valueOf(textField_1.getPassword()).trim().equals("")) { // ��й�ȣ
									textField_1.requestFocus();
									JOptionPane.showMessageDialog(null, "[��й�ȣ] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p1, String.valueOf(textField_1.getPassword()).trim())) {
									JOptionPane.showMessageDialog(null, "�ùٸ� ��й�ȣ ������ �ƴմϴ�.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (String.valueOf(textField_2.getPassword()).trim().equals("")) { // ��й�ȣ Ȯ��
									textField_2.requestFocus();
									JOptionPane.showMessageDialog(null, "[��й�ȣ Ȯ��] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!String.valueOf(textField_1.getPassword()).trim()
										.equals(String.valueOf(textField_2.getPassword()).trim())) {
									JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (textField_3.getText().trim().equals("")) { // �̸�
									textField_3.requestFocus();
									JOptionPane.showMessageDialog(null, "[�̸�] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p5, String.valueOf(textField_3.getText()).trim())) {
									JOptionPane.showMessageDialog(null, "�ùٸ� �̸� ������ �ƴմϴ�.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (comboBox.getSelectedIndex() == 0) { // ����
									comboBox.requestFocus();
									JOptionPane.showMessageDialog(null, "[����] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!radioButton.isSelected()&&!radioButton_1.isSelected()) { // ����
									JOptionPane.showMessageDialog(null, "[����] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (textField_5.getText().trim().equals("")) { // ����
									textField_5.requestFocus();
									JOptionPane.showMessageDialog(null, "[����] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p2, textField_5.getText())) {
									JOptionPane.showMessageDialog(null, "���̸� 1~199������ ������ �Է����ּ���.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (textField_6.getText().trim().equals("")) { // �������
									textField_6.requestFocus();
									JOptionPane.showMessageDialog(null, "[�������] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p3, textField_6.getText())) {
									JOptionPane.showMessageDialog(null, "��������� 6�ڸ� ���� �Է����ּ���.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (textField_7.getText().trim().equals("")) { // ��ȭ��ȣ
									textField_7.requestFocus();
									JOptionPane.showMessageDialog(null, "[��ȭ��ȣ] �ʼ����� ����!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else if (!Pattern.matches(p4, textField_7.getText().trim())) {
									JOptionPane.showMessageDialog(null, "�ùٸ� ��ȭ��ȣ ������ �ƴմϴ�.", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									dto = new MemberDTO(textField.getText(), // ���̵�
											String.valueOf(textField_1.getPassword()), // ��й�ȣ
											textField_3.getText(), // �̸�
											textField_4.getText(), // ����
											(String) comboBox.getSelectedItem(), // ����
											strsex, // ����
											textField_5.getText(), // ����
											textField_6.getText(), // �������
											textField_7.getText()); // ��ȭ��ȣ

									System.out.println(dto);
									try {
										MessengerDAOImpl dao = new MessengerDAOImpl();
										dao.insertMember(dto);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!", "�˸�",
											JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("���");
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
}
