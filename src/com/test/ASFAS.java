package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ASFAS extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ASFAS frame = new ASFAS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ASFAS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 253);
		contentPane = new JPanel();
		Point mainFrameLocation = new Point(0, 0);
	    Point mouseClickedLocation = new Point(0, 0);
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getLocationOnScreen().x - mouseClickedLocation.x,
		                e.getLocationOnScreen().y - mouseClickedLocation.y);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		textField_1 = new JTextField();
		textField_1.setBounds(126, 202, 116, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(126, 158, 116, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\emess\\Downloads\\beach.jpeg"));
		lblNewLabel.setBounds(0, 0, 389, 253);
		contentPane.add(lblNewLabel);
	}
}
