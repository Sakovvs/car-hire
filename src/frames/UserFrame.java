package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DaoOrders;
import db.DB;
import entity.Orders;

public class UserFrame extends JFrame{
	private JPanel panel, panelCars, panelOrders;
	private Table tableCars, tableOrders;
	private JScrollPane scrollCars, scrollOrders;
	private JTabbedPane pane;
	private JButton add,pay, backLoginFrame;
	
	private DB db;
	private String login_user;
	
	public UserFrame(DB db, String login_user) {
		this.db=db;
		this.login_user=login_user;
		setTitle("Hi "+ login_user);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		initComponents();
		action();
		
		setVisible(true);
	}

	private void action() {
		backLoginFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame(db);
				dispose();

			}
		});
		
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableCars.getSelectedRow()!=-1) {
					new Data(db,login_user,Integer.valueOf(String.valueOf(tableCars.getValueAt(tableCars.getSelectedRow(), 0))));
				}else {
					JOptionPane.showMessageDialog(panelCars, "select car");
				}
				
			}
		});
		
		pay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableOrders.getSelectedRow()!=-1) {
					if(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 7)).equals("yes")==true) {
						if(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 10)).equals("activ")==true) {
							if(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 9)).equals("no")==true) {
								DaoOrders order=new DaoOrders(db);
								try {
									order.pay(new Orders(Integer.valueOf(String.valueOf(tableOrders.getValueAt(tableOrders.getSelectedRow(), 0)))));
									updateuserFrame();
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(panelCars, "ваша заказ оплачен");

							}

						}else {
							JOptionPane.showMessageDialog(panelCars, "ваша заказ закрыт");

						}

					}else{
						JOptionPane.showMessageDialog(panelCars, "ваша заявка не подтверждена");
					}
				}else {
					JOptionPane.showMessageDialog(panelCars, "выберите заказ для оплаты");
				}
			}
		});
		
		
		
		
		
		
		
		
	}

	private void initComponents() {
		panel=new JPanel(null);
		panelCars=new JPanel(null);
		panelOrders=new JPanel(null);
		
		pane=new JTabbedPane();
		
		try {
			tableCars=new Table(db.query("SELECT * FROM cars WHERE status='not blok'"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tableOrders=new Table(db.query("SELECT * FROM orders WHERE login_user='" + login_user + "'"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollCars=new JScrollPane(tableCars);
		scrollOrders=new JScrollPane(tableOrders);
		
		add=new JButton("Заказать");
		pay=new JButton("Оплатить");
		backLoginFrame=new JButton("назад");
		
		panel.add(pane);
		pane.setBounds(20, 20, 600, 400);
		panel.add(backLoginFrame);
		backLoginFrame.setBounds(20, 440, 600, 30);
		
		pane.add("Cars", panelCars);
		pane.add("Orders", panelOrders);
		
		panelCars.add(scrollCars);
		scrollCars.setBounds(10, 10, 550, 200);
		panelCars.add(add);
		add.setBounds(10, 220, 550, 30);
		
		panelOrders.add(scrollOrders);
		scrollOrders.setBounds(10, 10, 550, 200);
		panelOrders.add(pay);
		pay.setBounds(10, 220, 550, 30);
		
		add(panel);
		
	}
	public void updateuserFrame() {
		panel.removeAll();
		
		
		panelCars=new JPanel(null);
		panelOrders=new JPanel(null);
		
		pane=new JTabbedPane();
		
		try {
			tableCars=new Table(db.query("SELECT * FROM cars WHERE status='not blok'"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tableOrders=new Table(db.query("SELECT * FROM orders WHERE login_user='" + login_user + "'"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollCars=new JScrollPane(tableCars);
		scrollOrders=new JScrollPane(tableOrders);
		
		add=new JButton("Заказать");
		pay=new JButton("Оплатить");
		backLoginFrame=new JButton("назад");
		
		panel.add(pane);
		pane.setBounds(20, 20, 600, 400);
		panel.add(backLoginFrame);
		backLoginFrame.setBounds(20, 440, 600, 30);
		
		pane.add("Cars", panelCars);
		pane.add("Orders", panelOrders);
		
		panelCars.add(scrollCars);
		scrollCars.setBounds(10, 10, 550, 200);
		panelCars.add(add);
		add.setBounds(10, 220, 550, 30);
		
		panelOrders.add(scrollOrders);
		scrollOrders.setBounds(10, 10, 550, 200);
		panelOrders.add(pay);
		pay.setBounds(10, 220, 550, 30);
		
		panel.updateUI();
		action();
	}
}
