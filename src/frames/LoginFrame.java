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

import db.DB;

public class LoginFrame extends JFrame {
	private JPanel panel;
	private JLabel labelLogin, labelPassword;
	private JTextField login;
	private JPasswordField password;
	private JButton ok, registration, back;
	private DB db;

	public LoginFrame(DB db) {
		this.db = db;
		setTitle("Prokat-LoginFrame");
		setSize(260, 200);
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

		login = new JTextField("admin", 20);

		password = new JPasswordField("admin", 20);

		ok = new JButton("ok");
		registration = new JButton("registration");
		back = new JButton("back");


		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);

		panel.add(ok);
		panel.add(registration);
		panel.add(back);

		add(panel);

	}

	private void action() {
	
			back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new StartFrame(db);
					dispose();

				}
			});
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login.getText().equals("")==true) {
					login.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else {
					login.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				if (String.valueOf(password.getPassword()).equals("")==true) {
					password.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else {
					password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				if(login.getText().equals("")!=true && String.valueOf(password.getPassword()).equals("")!=true) {
					try {
						ResultSet rs=db.query("SELECT *FROM users WHERE login='"+login.getText()+"'");
					
					
						if(rs.next()==true) {
							if(String.valueOf(password.getPassword()).equals(rs.getString("password"))==true) {
								if(rs.getString("status").equals("not blok")==true) {
									if(rs.getInt("rol")==1) {
										//admin
										new AdminFrame(db, login.getText());
										dispose();
									}else {
										//user
										new UserFrame(db, login.getText());
										dispose();
									}
									
								}else {
									JOptionPane.showMessageDialog(panel, "пользователь заблокирован", "",JOptionPane.ERROR_MESSAGE );

								}
								
							}else {
								JOptionPane.showMessageDialog(panel, "пароль не верный", "",JOptionPane.ERROR_MESSAGE );

							}
							
						}else {
							JOptionPane.showMessageDialog(panel, "пользователь с логином "+login.getText()+" не найден", "",JOptionPane.ERROR_MESSAGE );
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(panel, "Ошибка при чтении данных из таблицы users", "",JOptionPane.ERROR_MESSAGE );

					}
				}
				
			}
		});
		
		
		
		
		
		
		

		/*ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (login.getText().equals("") != true && String.valueOf(password.getPassword()).equals("") != true) {
					login.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
					password.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

					try {
						ResultSet rs = db.query("SELECT * FROM users WHERE login = '" + login.getText() + "'");

						
						if (rs.next() == true) {
							if (rs.getString("pass").equals(String.valueOf(password.getPassword())) == true) {
								if (rs.getString("status").equals("not block") == true) {
									if (rs.getInt("rol") == 1) {
										// admin
										new AdminFrame(db, login.getText());
										dispose();
									} else {
										if (rs.getInt("rol") == 2) {
											// user
											new UserFrame(db, login.getText());
											dispose();
										} else {
											JOptionPane.showMessageDialog(panel, "неизвестная роль");
										}
									}
								} else {
									JOptionPane.showMessageDialog(panel,
											"пользователь с логином " + login.getText() + " временно заблокировван");
								}
							} else {
								JOptionPane.showMessageDialog(panel, "пароль неверный");
							}
						} else {
							JOptionPane.showMessageDialog(panel,
									"пользователь с логином " + login.getText() + " в базе отсутсвует");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(panel, "Ошибка при создании rs");
					}
				} else {
					if (login.getText().equals("") == true) {
						login.setBorder(BorderFactory.createLineBorder(Color.RED));
						JOptionPane.showMessageDialog(panel, "Введите login ");
					} else {
						login.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
					}

					if (String.valueOf(password.getPassword()).equals("") == true) {
						password.setBorder(BorderFactory.createLineBorder(Color.RED));
						JOptionPane.showMessageDialog(panel, "Введите пароль");
					} else {
						password.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
					}
				}
			}
		});
*/
		registration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RegistrationFrame(db);
				dispose();

			}
		});
	}
	

}
