package frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.BorderUIResource;

import dao.DaoUsers;
import db.DB;
import entity.Users;

public class RegistrationFrame extends JFrame {
	private JPanel panel;
	private JLabel labelLogin, labelPassword, labelPassword1;
	private JTextField login;
	private JPasswordField password, password1;
	private JButton ok, back;
	private DB db;

	public RegistrationFrame(DB db) {
		this.db = db;
		setTitle("Prokat-RegistrationFrame");
		setSize(260, 260);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("d:/Вова/ДРУГОЕ/топоры/3.jpg").getImage());

		initComponents();
		action();

		setVisible(true);
	}

	private void initComponents() {
		panel = new JPanel();
		labelLogin = new JLabel("login");
		labelPassword = new JLabel("password");
		labelPassword1 = new JLabel("confirm password");

		login = new JTextField("", 20);

		password = new JPasswordField("", 20);
		password1 = new JPasswordField("", 20);

		ok = new JButton("ok");
		back = new JButton("back");

		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(labelPassword1);
		panel.add(password1);
		panel.add(ok);
		panel.add(back);
		add(panel);

	}

	private void action() {
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame(db);
				dispose();

			}
		});

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (login.getText().equals("") == true) {
					login.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					login.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				if (String.valueOf(password.getPassword()).equals("") == true) {
					password.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				if (String.valueOf(password1.getPassword()).equals("") == true) {
					password1.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					password1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				if (String.valueOf(password.getPassword()).equals("") != true
						&& String.valueOf(password.getPassword())
								.equals(String.valueOf(password1.getPassword())) == true
						&& login.getText().equals("") != true) {
					try {
						ResultSet rs = db.query("SELECT * FROM users WHERE login='" + login.getText() + "'");
						if (rs.next() == true) {
							JOptionPane.showMessageDialog(panel,
									"пользователь с логином " + login.getText() + " уже есть в базе данных", "",
									JOptionPane.ERROR_MESSAGE);
						} else {
							DaoUsers user = new DaoUsers(db);
							user.insert(
									new Users(login.getText(), String.valueOf(password.getPassword()), 2, "not blok"));
							JOptionPane.showMessageDialog(panel, "пользователь добавлен", "",
									JOptionPane.INFORMATION_MESSAGE);
							new LoginFrame(db);
							dispose();

						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					if (String.valueOf(password.getPassword()).equals("") != true && String
							.valueOf(password.getPassword()).equals(String.valueOf(password1.getPassword())) != true) {
						JOptionPane.showMessageDialog(panel, "пароли не совпадают", "",
								JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		});
	}

}
