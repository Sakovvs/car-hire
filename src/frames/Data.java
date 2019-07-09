package frames;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DaoCars;
import dao.DaoOrders;
import db.DB;
import entity.Cars;
import entity.Orders;

public class Data extends JFrame {
	private DB db;
	private String login_user;
	private int id_car;
	private JPanel panel;
	private JLabel date1,date2;
	private JTextField text1,text2;
	private JButton ok,back;
	

	public Data(DB db, String login_user, int id_car )  {
		this.db = db;
		this.login_user = login_user;
		this.id_car = id_car;
		this.setTitle("Даты заказа");
		setSize(280, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		
		initComponents();
		action();
		
		setVisible(true);
		
	}


	private void action() {
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id_user;
				double price, cost;
				String data_start, data_finish;
				if(text1.getText().equals("")==true) {
					text1.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else {
					text1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				if(text2.getText().equals("")==true) {
					text2.setBorder(BorderFactory.createLineBorder(Color.RED));
				}else {
					text2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}
				
				
				
				if(text1.getText().equals("")!=true&&text2.getText().equals("")!=true) {
					try {
						ResultSet rs=db.query("SELECT * FROM users WHERE login='"+login_user+"'");
						
						if(rs.next()==true) {
							id_user=rs.getInt("id_user");
							 rs=db.query("SELECT * FROM cars WHERE id_car="+id_car);
							 if(rs.next()==true) {
								 price=rs.getDouble("price");
								 data_start=text1.getText();
								 data_finish=text2.getText();
								 
								 int a1,a2;
								 a1=((int)(data_start.charAt(0)-'0'))*10+((int)(data_start.charAt(0)-'0'));
								 a2=((int)(data_finish.charAt(0)-'0'))*10+((int)(data_finish.charAt(0)-'0'));
								 
								 cost=(a2-a1+1)*price;
								 JOptionPane.showMessageDialog(panel, "стоимость вышего заказа:"+cost);
								 DaoOrders order=new DaoOrders(db);
								 order.insert(new Orders( id_user,login_user,id_car,data_start,data_finish,cost,"no","no","no", "activ"));
								 JOptionPane.showMessageDialog(panel, "Ваша заявка отправлена, ожидайте подтверждения");
								 
								 DaoCars car=new DaoCars(db);
								 car.delete(new Cars(id_car));
								 
								 new UserFrame(db, login_user);
								 dispose();
								 
							 }else {
								 
							 }

						}else {
							
						}
					} catch (SQLException e1) {
					JOptionPane.showMessageDialog(panel, "Error!");
					}
				}
							
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserFrame(db,login_user);
				dispose();
				
			}
		});
		
	}


	private void initComponents() {
		panel=new JPanel();
		date1=new JLabel("с какой даты(хх.хх.хххх");
		date2=new JLabel("по какую дату(хх.хх.хххх");
		text1 =new JTextField("",20);
		text2 =new JTextField("",20);
		
		ok=new JButton("Ok");
		back=new JButton("Back");
		
		panel.add(date1);
		panel.add(text1);
		panel.add(date2);
		panel.add(text2);
		panel.add(ok);
		panel.add(back);
		
		add(panel);
	}
	
	

}
