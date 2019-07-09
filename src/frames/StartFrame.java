package frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.DB;
import db.WorkDB;

public class StartFrame extends JFrame {
	private JPanel panel;
	private JLabel labelUrl, labelNameDB,labelLogin, labelPassword;
	private JTextField url, nameDB, login;
	private JPasswordField password;
	private JButton createDB, deleteDB, connect;
	private DB db;
	
	public StartFrame() {
		setTitle("Prokat-StartFrame");
		setSize(250, 290);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setLayout(null);// ручное позиционирование компонентов
		;
		setResizable(false);
		setIconImage(new ImageIcon("d:/Вова/ДРУГОЕ/топоры/3.jpg").getImage());
		
		initComponents();
		action();
		
		
		
		setVisible(true);
	}

	public StartFrame(DB db) {
		setTitle("Prokat-StartFrame");
		setSize(250, 290);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setLayout(null);// ручное позиционирование компонентов
		;
		setResizable(false);
		setIconImage(new ImageIcon("d:/Вова/ДРУГОЕ/топоры/3.jpg").getImage());
		
		initComponents();
		action();
		
		
		
		setVisible(true);
	}

	
	
	private void initComponents() {
		panel=new JPanel();
		
		//
		
		
		
		
		
		
	//	panel.setLayout(new GridLayout(4, 3));//  таблица 3*2
		labelUrl=new JLabel("url");
		labelNameDB=new JLabel("nameDB");
		labelLogin=new JLabel("login");
		labelPassword=new JLabel("password");
		
		url=new JTextField("jdbc:mysql://127.0.0.1/",20);
		nameDB=new JTextField("prokat",20);
		login =new JTextField("root",20);
		
		password=new JPasswordField("root",20);
		
		createDB=new JButton("CreateDB");
		deleteDB=new JButton("deleteDB");
		connect=new JButton("connect");
		
		panel.add(labelUrl);
		panel.add(url);
		panel.add(labelNameDB);
		panel.add(nameDB);
		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(createDB);
		panel.add(deleteDB);
		panel.add(connect);
		
		add(panel);
	}
	private void action() {
		createDB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					WorkDB.createDB(url.getText(),nameDB.getText(),login.getText(),String.valueOf(password.getPassword()));
					JOptionPane.showMessageDialog(panel, "база данных "+ nameDB.getText()+" успешно создана", "", JOptionPane.INFORMATION_MESSAGE);
				}catch (SQLException e1 ) {
					JOptionPane.showMessageDialog(panel, "база данных "+nameDB.getText()+" уже существует","",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteDB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					WorkDB.deleteDB(url.getText(), nameDB.getText(), login.getText(), String.valueOf(password.getPassword()));
					JOptionPane.showMessageDialog(panel, "База данных " + nameDB.getText() + " Успешно удалена", "", JOptionPane.INFORMATION_MESSAGE);

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(panel, "Базы данных " + nameDB.getText() + " не существует", "", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(nameDB.getText().equals("")!=true) {					
				
				
				try {
					
					DB db = new DB(url.getText(), nameDB.getText(), login.getText(), String.valueOf(password.getPassword()));
					new LoginFrame(db);
					dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(panel, "Ошибка при создании DB", "", JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(panel, "Заполните поле name DB", "", JOptionPane.INFORMATION_MESSAGE);
			}
			}
		});
	}



}
