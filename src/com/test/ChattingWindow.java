package com.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class ChattingWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	
	Socket socket;
	private JTextArea ta1;
	
	public ChattingWindow() {
		launchFrame();
		setVisible(true);

		startClient();
		
	}

	public void launchFrame() {
		setTitle("대화창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				if(e.getKeyCode() == Event.ENTER) {			
					send(tf1.getText());
					tf1.setText("");
				}
			}
		});

		p2.add(tf1);
		tf1.setColumns(31);
		
		JButton btn1 = new JButton("전송");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setText("\n\r");
				send(tf1.getText());
				tf1.setText("");
				
			}
		});
		p2.add(btn1);
		tf1.requestFocus();
		ta1.setEditable(false);
		setResizable(false);
	}


	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 5500));
					System.out.println("[연결 완료: " + socket.getRemoteSocketAddress() + "]");
				} catch (Exception e) {
					System.out.println("[서버 통신 안됨]");
					if (!socket.isClosed()) {
						stopClient();
					}
					return;
				}
				receive();
			}
		};
		thread.start();
	}

	void stopClient() {
		try {
			System.out.println("[연결 끊음]");
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	void receive() {
		while (true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();

				// 서버가 비정상적으로 종료했을 경우 IOException 발생
				int readByteCount = inputStream.read(byteArr);

				// 서버가 정상적으로 Socket의 close()를 호출했을 경우
				if (readByteCount == -1) {
					throw new IOException();
				}

				String data = new String(byteArr, 0, readByteCount, "UTF-8");
//				ta1.append("\r\n");
				ta1.append(data);
				System.out.println("[받기 완료] " + data);
			} catch (Exception e) {
				System.out.println("[서버 통신 안됨]");
				stopClient();
				break;
			}
		}
	}

	void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					System.out.println("[보내기 완료]");
				} catch (Exception e) {
					System.out.println("[서버 통신 안됨]");
					stopClient();
				}
			}
		};
		thread.start();
	}

	public static void main(String[] args) {
		new ChattingWindow();
	}
}
