package Notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;

public class note implements ActionListener {
	JFrame f;
	Font font;
	JTextArea area;
	JMenuBar bar;
	JMenu file, edit, format, themes, help, style;
	JMenuItem newfile, openfile, savefile, exit, print, undo, redo, cut, copy, paste, selectall, about, italic, bold,
			bolditalic, size, light, dark, gray;
	UndoManager um;

	note() {

		f = new JFrame();
		f.setSize(1400, 800);
		f.setLocationRelativeTo(null);
		f.setTitle("Notepad");
		Image icon = Toolkit.getDefaultToolkit().getImage("/home/bhagyashri/Desktop/Notepad/images/photo_6079862486703649247_x.jpg");
		f.setIconImage(icon);
		f.getContentPane().setBackground(Color.white);

		font = new Font("SANS_SERIF", Font.PLAIN, 19);

		bar = new JMenuBar();

		file = new JMenu("File");
		file.setFont(font);

		newfile = new JMenuItem(" New");
		newfile.setFont(font);
		openfile = new JMenuItem(" Open...");
		openfile.setFont(font);
		savefile = new JMenuItem(" Save");
		savefile.setFont(font);
		print = new JMenuItem(" Print");
		print.setFont(font);
		exit = new JMenuItem(" Exit");
		exit.setFont(font);

		file.add(newfile);
		file.add(openfile);
		file.add(savefile);
		file.add(print);
		file.add(exit);

		bar.add(file);

		edit = new JMenu("Edit");
		edit.setFont(font);

		undo = new JMenuItem(" Undo");
		undo.setFont(font);
		redo = new JMenuItem(" Redo");
		redo.setFont(font);
		cut = new JMenuItem(" Cut");
		cut.setFont(font);
		copy = new JMenuItem(" Copy");
		copy.setFont(font);
		paste = new JMenuItem(" Paste");
		paste.setFont(font);
		selectall = new JMenuItem(" Select All");
		selectall.setFont(font);

		edit.add(undo);
		edit.add(redo);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectall);

		bar.add(edit);

		format = new JMenu("Format");
		format.setFont(font);

		style = new JMenu(" Style");
		style.setFont(font);
		italic = new JMenuItem(" Italic");
		italic.setFont(font);
		bold = new JMenuItem(" Bold");
		bold.setFont(font);
		bolditalic = new JMenuItem(" Bold+Italic");
		bolditalic.setFont(font);

		style.add(italic);
		style.add(bold);
		style.add(bolditalic);

		format.add(style);

		bar.add(format);

		size = new JMenuItem(" Size");
		size.setFont(font);
		format.add(size);
		bar.add(format);

		themes = new JMenu("Themes");
		themes.setFont(font);

		light = new JMenuItem(" Light");
		light.setFont(font);
		dark = new JMenuItem(" Dark");
		dark.setFont(font);
		gray = new JMenuItem(" Gray");
		gray.setFont(font);

		themes.add(dark);
		themes.add(light);
		themes.add(gray);

		bar.add(themes);

		help = new JMenu("Help");
		help.setFont(font);

		about = new JMenuItem(" About");
		about.setFont(font);

		help.add(about);

		bar.add(help);

		f.setJMenuBar(bar);

		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		print.addActionListener(this);
		exit.addActionListener(this);

		undo.addActionListener(this);
		redo.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectall.addActionListener(this);

		italic.addActionListener(this);
		bold.addActionListener(this);
		bolditalic.addActionListener(this);
		size.addActionListener(this);

		light.addActionListener(this);
		dark.addActionListener(this);
		gray.addActionListener(this);

		about.addActionListener(this);

		MenuListener eml = new MenuListener() {
			public void menuSelected(MenuEvent me) {

				if (area.getSelectionStart() == area.getSelectionEnd()) {
					cut.setEnabled(false);
					copy.setEnabled(false);
				} else {
					cut.setEnabled(true);
					copy.setEnabled(true);
				}
			}

			public void menuCanceled(MenuEvent arg0) {
			}

			public void menuDeselected(MenuEvent arg0) {
			}
		};
		edit.addMenuListener(eml);

		area = new JTextArea();
		JScrollPane sc = new JScrollPane(area);
		area.setFont(font);
		sc.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		um = new UndoManager();
		area.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent obj) {

				um.addEdit(obj.getEdit());

			}
		});

		area.setForeground(new Color(105, 101, 102));
		area.setLineWrap(true);

		f.add(sc);

		// short cut keys of JMenuItems
		

		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

	}

	// actions
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int a;

		switch (e.getActionCommand()) {

		case " New":
			area.setText(null);
			break;

		case " Open...":

			a = fc.showOpenDialog(f);
			if (a == JFileChooser.APPROVE_OPTION) {

				File file = fc.getSelectedFile();
				try {
					// Reading the file
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					area.read(br, null);
					br.close();
					area.requestFocus();
				} catch (Exception e1) {
					System.out.print(e1);
				}
			}
			break;

		case " Save":

			fc.setAcceptAllFileFilterUsed(false);

			FileNameExtensionFilter filter = new FileNameExtensionFilter("TextFiles(.txt)", "txt");
			fc.addChoosableFileFilter(filter);

			a = fc.showSaveDialog(f);

			if (a != JFileChooser.APPROVE_OPTION) {
				return;
			}

			else {
				File fn = new File(fc.getSelectedFile() + ".txt");
				f.setTitle("Notepad-" + fn);
				BufferedWriter br;

				try {
					br = new BufferedWriter(new FileWriter(fn));
					area.write(br);
				} catch (Exception e2) {
					System.out.print(e2);
				}
			}
			break;

		case " Print":
			try {
				area.print();
			} catch (Exception e3) {
				System.out.print(e3);
			}
			break;

		case " Exit":
			f.dispose();
			break;

		// Edit menuitems action

		case " Undo":
			try {
				um.undo();
			} catch (Exception ee) {
				System.out.print(ee);
			}
			break;

		case " Redo":
			try {
				um.redo();
			} catch (Exception ee) {
				System.out.print(ee);
			}
			break;

		case (" Cut"):
			area.cut();
			break;

		case (" Copy"):
			area.copy();
			break;

		case " Paste":
			area.paste();
			break;

		case " Select All":
			area.selectAll();
			break;

		case "Italic":
			area.setFont(new Font("SAN_SERIF", Font.ITALIC, 19));
			break;
		case " Bold":
			area.setFont(new Font("SAN_SERIF", Font.BOLD, 19));
			break;
		case " Bold+Italic":
			area.setFont(new Font("SAN_SERIF", Font.ITALIC | Font.BOLD, 19));
			break;

		case " Size":
			String fontsize = JOptionPane.showInputDialog(f, "Font size", JOptionPane.OK_CANCEL_OPTION);

			if (fontsize != null) {
				int changefontsize = Integer.parseInt(fontsize);
				font = new Font("SAN_SERIF", Font.PLAIN, changefontsize);
				area.setFont(font);
			}
			break;

		case " Light":
			area.setBackground(Color.white);
			area.setForeground(new Color(105, 101, 102));
			break;

		case " Dark":
			area.setBackground(Color.BLACK);
			area.setForeground(Color.white);
			break;

		case " Gray":
			area.setBackground(new Color(211, 211, 211));
			area.setForeground(Color.black);
			break;

		case " About":
			new about().setVisible(true);
			break;
		}

	}

	public static void main(String args[]) {

		new note().f.setVisible(true);
	}

}
