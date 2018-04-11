package com.messenger.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class Chatting extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;

	Socket socket;
	private JTextArea ta1;

	public Chatting() {
		launchFrame();
		setVisible(true);
	}

	public void launchFrame() {
		setTitle("대화창");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel p1 = new JPanel();
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(new BorderLayout(0, 0));

		ta1 = new JTextArea();
		p1.add(ta1);
		JScrollPane js = new JScrollPane(ta1);
		p1.add(js);

		JPanel p2 = new JPanel();
		contentPane.add(p2, BorderLayout.SOUTH);

		tf1 = new JTextField();
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == Event.ENTER) {
					send(tf1.getText()+"\n");
					tf1.setText("");
				}
			}
		});

		p2.add(tf1);
		tf1.setColumns(31);

		JButton btn1 = new JButton("전송");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(tf1.getText()+"\n\r");
				tf1.setText("");

			}
		});
		p2.add(btn1);
		tf1.requestFocus();
		ta1.setEditable(false);
		setResizable(false);
	}
	void send(String data) {
		ta1.append(data);
	}
}
