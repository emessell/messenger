package com.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MServer extends JFrame {

	private static final MServer mserver = new MServer();
	// 쓰레드pool생성
	ExecutorService executorService;
	ServerSocket serVerSocket;
	List<Client> connections = new Vector<Client>();
	final String IP = "localhost";
	final int PORT = 5500;
	JTextArea ta1;

	private MServer() {
		setTitle("메신저 모니터링 서버");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("파일");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("저장");
		menu.add(menuItem);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel p1 = new JPanel();
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(new BorderLayout(0, 0));

		ta1 = new JTextArea();
		ta1.setEditable(false);
		p1.add(ta1, BorderLayout.CENTER);
		JScrollPane js = new JScrollPane(ta1);
		p1.add(js);

		JPanel p2 = new JPanel();
		contentPane.add(p2, BorderLayout.SOUTH);

		JButton btn1 = new JButton("Server Start");
		p2.add(btn1);
		setVisible(true);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object object = e.getSource();
				if (object == btn1) {
					if (btn1.getText().equals("Server Start")) {
						btn1.setText("Server Stop");
						MServer.this.startServer();

					} else if (btn1.getText().equals("Server Stop")) {
						btn1.setText("Server Start");
						MServer.this.stopServer();
					}
				}
			}
		});
	}

	void startServer() {
		// 코어수만큼 쓰레드풀 생성
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		try {
			serVerSocket = new ServerSocket();
			serVerSocket.bind(new InetSocketAddress(IP, PORT));
		} catch (Exception e) {
			if (!serVerSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		// 작업생성
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("[서버 시작]");
				ta1.append("[서버 시작]" + "\r\n");
				while (true) {
					try {
						Socket socket = serVerSocket.accept();
						String message = "[연결수락: " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]";
						System.out.println(message + "\r\n");
						ta1.append(message);
						Client client = new Client(socket);
						connections.add(client);
						// System.out.println("[연결개수: " + connections.size()+ " ]");
						ta1.append("[연결개수: " + connections.size() + " ]" + "\r\n");
					} catch (IOException e) {
						if (!serVerSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		// 작업을 작업큐에 등록
		executorService.submit(runnable);
	}

	void stopServer() {
		Iterator<Client> iterator = connections.iterator();

		try {
			// client 접속해제
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}

			if (serVerSocket != null && !serVerSocket.isClosed()) {
				serVerSocket.close();
			}

			if (executorService != null && !executorService.isShutdown()) {
				executorService.shutdownNow();
			}
			System.out.println("[서버 멈춤]");
			ta1.append("[서버 멈춤]" + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Client {
		Socket socket;

		Client(Socket socket) {
			this.socket = socket;
			receive();
		}

		// 접속된 clinet에 정보 전송
		void receive() {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					byte[] byteArr = new byte[10];
					try {
						while (true) {
							InputStream inputstream = socket.getInputStream();

							// client가 비정상 종료를 했을 경우 IOException발생
							int readByteCount = inputstream.read(byteArr);
							// client가 정상적으로 Socket의 close()를 호출했을 경우
							if (readByteCount == -1) {
								throw new IOException();
							}
							;

							String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]";
							ta1.append(message + "\r\n");
							System.out.println(message);

							String data = new String(byteArr, 0, readByteCount, "UTF-8");
							for (Client client : connections) {
								client.send(data);
							}
						}
					} catch (IOException e) {
						connections.remove(Client.this);
						String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]";
						ta1.append(message + "\r\n");
						System.out.println(message);
						try {
							socket.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			executorService.submit(runnable);
		}

		// 접속된 clinet로 부터 정보 수신
		void send(String data) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputstream = socket.getOutputStream();
						outputstream.write(byteArr);
						outputstream.flush();
						System.out.println("클라이언트로 부터 수신됨");
					} catch (IOException e) {
						try {
							String message = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
									+ Thread.currentThread().getName() + "]";
							ta1.append(message + "\r\n");
							System.out.println(message);
							socket.close();
						} catch (IOException e2) {
						}
					}
				}
			};
			executorService.submit(runnable);
		}
	}

	public static MServer getInstance() {
		return mserver;
	}

	public static void main(String[] args) {
		MServer.getInstance();
	}
}
