package db;

import java.sql.SQLException;

import dao.DaoCars;
import dao.DaoInfo;
import dao.DaoOrders;
import dao.DaoUsers;
import entity.Info;
import entity.Orders;
import entity.Users;
import entity.Cars;

public class WorkDB {
	public static void createDB(String url, String nameDB, String login, String pass) throws SQLException {
		DB db = new DB(url, "", login, pass);
		
		db.update("CREATE DATABASE " + nameDB);		
		db.update("USE " + nameDB);	
		
		System.out.println("\nБаза данных " + nameDB + " успешно создана!!!");
		
		db.update("CREATE TABLE users (id_user INT NOT NULL UNIQUE AUTO_INCREMENT," + 
				" login VARCHAR(20) NOT NULL UNIQUE," + 
				" password VARCHAR(20) NOT NULL," + 
				" rol INT(1) DEFAULT 2," + 
				" status VARCHAR(8) DEFAULT 'not blok'," + 
				" PRIMARY KEY(id_user));");
				
		DaoUsers user = new DaoUsers(db);
		user.insert(new Users(1, "admin", "admin", 1, "not blok"));
		user.insert(new Users(2, "user1", "111", 2, "not blok"));
		user.insert(new Users(3, "user2", "222", 2, "blok"));
		user.insert(new Users(5, "user3", "333", 2, "not blok"));
		
			
		db.update("CREATE TABLE info (id_info INT NOT NULL UNIQUE AUTO_INCREMENT," + 
				" id_user INT NOT NULL," + 
				" surname VARCHAR(20) DEFAULT ''," + 
				" name VARCHAR(20) DEFAULT ''," + 
				" passport VARCHAR(9) DEFAULT ''," + 
				" tel VARCHAR(13) DEFAULT ''," + 
				" PRIMARY KEY(id_info)," + 
				" FOREIGN KEY(id_user) REFERENCES users(id_user));");
		
		DaoInfo info = new DaoInfo(db);
		info.insert(new Info(1, 1, "admin", "admin", "mp1234565", "+375291234567"));
		info.insert(new Info(2, 5, "Ivanov", "Ivan", "mp6543213", "+375441234567"));
		info.insert(new Info(3, 2, "Petrov", "Petr", "mp6152434", "+375251235467"));
		
		
		
		db.update("CREATE TABLE cars (id_car INT NOT NULL UNIQUE AUTO_INCREMENT," + 
				" marka VARCHAR(20) DEFAULT ''," + 
				" model VARCHAR(20) DEFAULT ''," + 
				" nomer VARCHAR(9) DEFAULT ''," + 
				" price FLOAT(7,2) DEFAULT 30.00," + 
				" status VARCHAR(8) DEFAULT 'not blok'," + 
				" PRIMARY KEY(id_car));");
		
		DaoCars car = new DaoCars(db);
		car.insert(new Cars(1, "Ford", "Kuga","8888 KK-7", 50.00, " blok"));
		car.insert(new Cars(2, "Ford", "Kuga","7777 KK-7", 50.00, "not blok"));
		
		db.update("CREATE TABLE orders (id_order INT NOT NULL UNIQUE AUTO_INCREMENT," + 
				" id_user INT NOT NULL," + 
				" login_user VARCHAR(20) NOT NULL," + 
				" id_car INT NOT NULL," + 
				" data_start VARCHAR(10) NOT NULL," + 
				" data_finish VARCHAR(10) NOT NULL," + 
				" cost FLOAT(8,2) NOT NULL," + 
				" status VARCHAR(3) DEFAULT 'no'," + 
				" pay VARCHAR(3) DEFAULT 'no'," + 
				" damage VARCHAR(20) DEFAULT 'no'," + 
				" del_status VARCHAR(8) DEFAULT 'activ'," + 
				" PRIMARY KEY(id_order)," + 
				" FOREIGN KEY(id_user) REFERENCES users(id_user)," + 
				" FOREIGN KEY(id_car) REFERENCES cars(id_car));");
		DaoOrders order = new DaoOrders(db);
		order.insert(new Orders(1, 2,"user1", 1,  "01.01.2019", "10.01.2019", 500.00, "no", "no","no", "activ"));
		order.insert(new Orders(2, 3,"user3", 2,  "01.01.2019", "20.01.2019", 1000.00, "no", "no","no", "activ"));
		
		
		System.out.println("\n\n------------------Users------------------");
		db.ShowTable(db.query("SELECT * FROM users"));
		
		System.out.println("\n\n------------------Info------------------");
		db.ShowTable(db.query("SELECT * FROM info"));
		
		System.out.println("\n\n------------------Cars------------------");
		db.ShowTable(db.query("SELECT * FROM cars"));
		
		System.out.println("\n\n------------------Orders------------------");
		db.ShowTable(db.query("SELECT * FROM orders"));	
	}
	
	
	public static void deleteDB(String url, String nameDB, String login, String pass) throws SQLException {
		DB db = new DB(url, nameDB, login, pass);
		db.update("DROP DATABASE " + nameDB);
		System.out.println("\nБаза данных " + nameDB + " успешно удалена!!!");
	}

}
