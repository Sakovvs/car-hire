package frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.DaoOrders;
import dao.DaoUsers;
import db.DB;
import entity.Orders;
import entity.Users;

public class AdminFrame extends JFrame {
	private JPanel panel;
	private JLabel lab1, lab2, lab3, lab4;
	private Table tableUsers, tableCars, tableInfo, tableOrders;
	private JScrollPane scrollUsers, scrollCars, scrollOrders, scrollInfo;
	private JButton add1, update1, info, rol, status1, delete1, add2, update2, status2, delete2, add3, update3, status3,
			pay, del_status, delete3, backLoginFrame;
	private DB db;
	private String login_admin;
	private JMenuBar menu;
	private JMenu file, about;
	private JMenuItem open, save, exit, aboutt;
	private JFileChooser chooser;

	JComboBox myComboBox = new JComboBox();

	public AdminFrame(DB db, String login_admin) {
		this.db = db;
		this.login_admin = login_admin;
		setTitle("Hi " + login_admin);
		setSize(700, 900);
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

		try {
			tableUsers = new Table(db.query("SELECT * FROM users"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tableInfo = new Table(db.query("SELECT * FROM info"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			tableCars = new Table(db.query("SELECT * FROM cars"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tableOrders = new Table(db.query("SELECT * FROM orders"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scrollUsers = new JScrollPane(tableUsers);
		scrollUsers.setPreferredSize(new Dimension(680, 140));

		scrollCars = new JScrollPane(tableCars);
		scrollCars.setPreferredSize(new Dimension(680, 140));

		scrollOrders = new JScrollPane(tableOrders);
		scrollOrders.setPreferredSize(new Dimension(680, 140));

		scrollInfo = new JScrollPane(tableInfo);
		scrollInfo.setPreferredSize(new Dimension(680, 140));

		add1 = new JButton("add");
		update1 = new JButton("update");
		info = new JButton("info");
		rol = new JButton("rol");
		status1 = new JButton("status");
		delete1 = new JButton("delete");
		add2 = new JButton("add");
		update2 = new JButton("update");
		status2 = new JButton("status");
		delete2 = new JButton("delete");
		add3 = new JButton("add");
		update3 = new JButton("update");
		status3 = new JButton("status");
		pay = new JButton("pay");
		del_status = new JButton("del_status");
		delete3 = new JButton("delete");

		backLoginFrame = new JButton("backLoginFrame");
		backLoginFrame.setPreferredSize(new Dimension(250, 30));

		lab1 = new JLabel("Users");
		lab1.setPreferredSize(new Dimension(680, 30));
		lab2 = new JLabel("Cars");
		lab2.setPreferredSize(new Dimension(680, 30));
		lab3 = new JLabel("Orders");
		lab3.setPreferredSize(new Dimension(680, 30));
		lab4 = new JLabel("Info");
		lab4.setPreferredSize(new Dimension(680, 30));

		panel.add(lab1);
		panel.add(scrollUsers);
		panel.add(add1);
		panel.add(update1);
		panel.add(info);
		panel.add(rol);
		panel.add(status1);
		panel.add(delete1);

		panel.add(lab2);
		panel.add(scrollCars);
		panel.add(add2);
		panel.add(update2);
		panel.add(status2);
		panel.add(delete2);

		panel.add(lab3);
		panel.add(scrollOrders);
		panel.add(add3);
		panel.add(update3);
		panel.add(status3);
		panel.add(pay);
		panel.add(del_status);
		panel.add(delete3);

		panel.add(lab4);
		panel.add(scrollInfo);

		panel.add(backLoginFrame);

		menu = new JMenuBar();
		file = new JMenu("File");
		about = new JMenu("About");

		menu.add(file);
		menu.add(about);

		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		aboutt = new JMenuItem("About");

		menu.add(file);
		menu.add(about);

		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);

		about.add(aboutt);

		setJMenuBar(menu);// размещаем меню на фрейм
		chooser = new JFileChooser();

		add(panel);

	}

	private void action() {
		aboutt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(panel,
						"всю эту хрень сделал Саковец Владимир за время прохождения \"курса основы программирования на Java\" в \"Центре обучающих технологий\"");
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(panel, "Buy");
				dispose();
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					chooser.showSaveDialog(panel);
					FileWriter fw = new FileWriter(chooser.getSelectedFile());
					StringBuilder sbd = new StringBuilder();

					for (int i = 0; i < tableUsers.getColumnCount(); i++) {
						sbd.append(tableUsers.getColumnName(i)).append(";");
					}
					sbd.append("\r\n");

					for (int i = 0; i < tableUsers.getRowCount(); i++) {
						for (int j = 0; j < tableUsers.getColumnCount(); j++) {
							sbd.append(tableUsers.getValueAt(i, j)).append(";");
						}
						sbd.append("\r\n");
					}
					fw.write(sbd.toString());
					JOptionPane.showMessageDialog(panel, "Данные сохранены");
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chooser.showOpenDialog(panel);
					FileReader fr = new FileReader(chooser.getSelectedFile());
					BufferedReader br = new BufferedReader(fr);
					String line = null;
					StringBuffer data = new StringBuffer();
					do {
						line = br.readLine();
						if (line != null) {
							data.append("\n");
							data.append(line).append("\n");
						}
					} while (line != null);
					JOptionPane.showMessageDialog(panel, data);
					br.close();
					fr.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		delete3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoOrders order = new DaoOrders(db);

				try {
					if (tableOrders.getSelectedRow() != -1) {

						order.delete(new Orders(Integer
								.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
						JOptionPane.showMessageDialog(panel, "Выбранный заказ удален", "",
								JOptionPane.INFORMATION_MESSAGE);
						updateAdminFrame();

					} else {
						JOptionPane.showMessageDialog(panel, "выберите заказ для удаления", "",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(panel, "Невозможно удалить", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		status3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoOrders order = new DaoOrders(db);

				if (tableOrders.getSelectedRow() != -1) {
					if (String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 7)).equals("no")) {
						try {
							order.statusYes(new Orders(Integer
									.valueOf(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 0)))));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(panel, "невозможно изменить статус", "",
									JOptionPane.ERROR_MESSAGE);

						}
					} else {
						try {
							order.statusNo(new Orders(Integer
									.valueOf(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 0)))));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					updateAdminFrame();
				} else {
					JOptionPane.showMessageDialog(panel, "выберите заказ", "", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		tableUsers.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// update по клавише enter
					DaoUsers user = new DaoUsers(db);
					try {
						user.update(new Users(
								Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0))),
								String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)),
								String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2)),
								Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3))),
								String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 4))));
						updateAdminFrame();
					} catch (NumberFormatException | SQLException e1) {
						JOptionPane.showMessageDialog(panel, "невозможно отредактировать", "",
								JOptionPane.ERROR_MESSAGE);

					}
				}

				super.keyReleased(e);
			}

		});

		update1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ред вcю табл
				DaoUsers user = new DaoUsers(db);
				try {
					for (int i = 0; i < tableUsers.getRowCount(); i++) {
						user.update(new Users(Integer.valueOf(String.valueOf(tableUsers.getValueAt(i, 0))),
								String.valueOf(tableUsers.getValueAt(i, 1)),
								String.valueOf(tableUsers.getValueAt(i, 2)),
								Integer.valueOf(String.valueOf(tableUsers.getValueAt(i, 3))),
								String.valueOf(tableUsers.getValueAt(i, 4))));
					}
					user.update(new Users(
							Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0))),
							String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)),
							String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2)),
							Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3))),
							String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 4))));
					updateAdminFrame();
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(panel, "невозможно отредактировать", "", JOptionPane.ERROR_MESSAGE);

				}

			}

			/*
			 * // ред выделенную строку DaoUsers user = new DaoUsers(db); try {
			 * user.update(new Users(
			 * Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.
			 * getSelectedRow(), 0))),
			 * String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)),
			 * String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2)),
			 * Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.
			 * getSelectedRow(), 3))),
			 * String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 4))));
			 * updateAdminFrame(); } catch (NumberFormatException | SQLException e1) {
			 * JOptionPane.showMessageDialog(panel, "невозможно отредактировать", "",
			 * JOptionPane.ERROR_MESSAGE);
			 * 
			 * }
			 * 
			 * }
			 */
		});

		add1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoUsers user = new DaoUsers(db);
				try {
					user.insert(new Users("", "", 2, "not blok"));
					updateAdminFrame();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(panel, "невозможно добавить \nлогин занят", "",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		status1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoUsers user = new DaoUsers(db);
				if (String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)).equals(login_admin) != true) {
					if (tableUsers.getSelectedRow() != -1) {
						if (String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 4))
								.equals("not blok") == true) {
							try {
								user.statusBlok(new Users(Integer.valueOf(
										String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							try {
								user.statusNotBlok(new Users(Integer.valueOf(
										String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						updateAdminFrame();
					} else {
						JOptionPane.showMessageDialog(panel, "выберите пользователя", "", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(panel, login_admin + " невозможно заблокировать", "",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		rol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoUsers user = new DaoUsers(db);

				if (String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)).equals(login_admin) != true) {

					if (tableUsers.getSelectedRow() != -1) {
						if (Integer
								.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3))) == 2) {
							try {
								user.rolAdmin(new Users(Integer.valueOf(
										String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							try {
								user.rolUser(new Users(Integer.valueOf(
										String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						updateAdminFrame();
					} else {
						JOptionPane.showMessageDialog(panel, "выберите пользователя", "", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(panel, login_admin + " невозможно поменять роль", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		delete1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DaoUsers user = new DaoUsers(db);
				if (String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1)).equals(login_admin) != true) {
					try {
						if (tableUsers.getSelectedRow() != -1) {

							user.delete(new Users(Integer
									.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)))));
							JOptionPane.showMessageDialog(panel, "Выбранный пользователь удален", "",
									JOptionPane.INFORMATION_MESSAGE);
							updateAdminFrame();

						} else {
							JOptionPane.showMessageDialog(panel, "выберите пользователя для удаления", "",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(panel, "Невозможно удалить", "", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(panel, login_admin + " невозможно удалить", "",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		backLoginFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame(db);
				dispose();

			}
		});

		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableUsers.getSelectedRow() != -1) {
					int id = Integer.valueOf(String.valueOf(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)));
					try {
						ResultSet rs = db.query("SELECT * FROM info WHERE id_user = " + id);
						if (rs.next() == true) {
							StringBuffer sb = new StringBuffer();
							sb.append(rs.getString("surname")).append("\r\n").append(rs.getString("name"))
									.append("\r\n").append(rs.getString("passport")).append("\r\n")
									.append(rs.getString("tel")).append("\r\n");
							JOptionPane.showMessageDialog(panel, sb);
						} else {
							JOptionPane.showMessageDialog(panel, "Подробная информация отсутствует");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(panel, "Выберите пользователя для получения информации");
				}

			}
		});

	}

	private void updateAdminFrame() {
		panel.removeAll();

		try {
			tableUsers = new Table(db.query("SELECT * FROM users"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			tableCars = new Table(db.query("SELECT * FROM cars"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tableOrders = new Table(db.query("SELECT * FROM orders"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scrollUsers = new JScrollPane(tableUsers);
		scrollUsers.setPreferredSize(new Dimension(680, 140));
		scrollCars = new JScrollPane(tableCars);
		scrollCars.setPreferredSize(new Dimension(680, 140));

		scrollOrders = new JScrollPane(tableOrders);
		scrollOrders.setPreferredSize(new Dimension(680, 140));

		add1 = new JButton("add");
		update1 = new JButton("update");
		rol = new JButton("rol");
		status1 = new JButton("status");
		delete1 = new JButton("delete");
		add2 = new JButton("add");
		update2 = new JButton("update");
		status2 = new JButton("status");
		delete2 = new JButton("delete");
		add3 = new JButton("add");
		update3 = new JButton("update");
		status3 = new JButton("status");
		pay = new JButton("pay");
		del_status = new JButton("del_status");
		delete3 = new JButton("delete");

		backLoginFrame = new JButton("backLoginFrame");
		backLoginFrame.setPreferredSize(new Dimension(680, 30));

		panel.add(scrollUsers);
		panel.add(add1);
		panel.add(update1);
		panel.add(rol);
		panel.add(status1);
		panel.add(delete1);

		panel.add(scrollCars);
		panel.add(add2);
		panel.add(update2);
		panel.add(status2);
		panel.add(delete2);

		panel.add(scrollOrders);
		panel.add(add3);
		panel.add(update3);
		panel.add(status3);
		panel.add(pay);
		panel.add(del_status);
		panel.add(delete3);

		panel.add(backLoginFrame);

		panel.updateUI();
		action();

	}

}
