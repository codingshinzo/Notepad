package Notepad;

import java.awt.*;

import javax.swing.*;

public class about extends JFrame {

	JLabel l, l1;

	about() {

		setSize(500, 450);
		setTitle("About Notepad");
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("/home/bhagyashri/Desktop/Notepad/images/th (5).jpeg");
		l = new JLabel(icon);
		l.setBounds(55, 70, 200, 100);

		l1 = new JLabel(
				"<html>Welcome to Notepad,<br>Notepad is JAVA Swing based simple Text Editor and basic text editing program which enables the computer users to create text documents.</html>");
		l1.setBounds(80, 160, 350, 250);
		l1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		add(l);
		add(l1);
	}

}
