package entity;

public class Orders {

	private int id_order;
	private int id_user;
	private int id_car;
	private String login_user;
	private String data_start;
	private String data_finish;
	private double cost;
	private String status;
	private String pay;
	private String damage;
	private String del_status;

	public Orders(int id_order, int id_user, String login_user, int id_car, String data_start, String data_finish, double cost,
			String status, String pay, String damage, String del_status) {
		this.id_order = id_order;
		this.id_user = id_user;
		this.login_user=login_user;
		this.id_car = id_car;
		this.data_start = data_start;
		this.data_finish = data_finish;
		this.cost = cost;
		this.status = status;
		this.pay = pay;
		this.damage = damage;
		this.del_status = del_status;
	}
	
	public Orders(int id_user, String login_user, int id_car, String data_start, String data_finish, double cost,
			String status, String pay, String damage, String del_status) {
		this.id_user = id_user;
		this.login_user=login_user;
		this.id_car = id_car;
		this.data_start = data_start;
		this.data_finish = data_finish;
		this.cost = cost;
		this.status = status;
		this.pay = pay;
		this.damage = damage;
		this.del_status = del_status;
	}
	
	public Orders(int id_order) {
		this.id_order = id_order;
		
	}

	public Orders() {

	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((damage == null) ? 0 : damage.hashCode());
		result = prime * result + ((data_finish == null) ? 0 : data_finish.hashCode());
		result = prime * result + ((data_start == null) ? 0 : data_start.hashCode());
		result = prime * result + ((del_status == null) ? 0 : del_status.hashCode());
		result = prime * result + id_car;
		result = prime * result + id_order;
		result = prime * result + id_user;
		result = prime * result + ((login_user == null) ? 0 : login_user.hashCode());
		result = prime * result + ((pay == null) ? 0 : pay.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (damage == null) {
			if (other.damage != null)
				return false;
		} else if (!damage.equals(other.damage))
			return false;
		if (data_finish == null) {
			if (other.data_finish != null)
				return false;
		} else if (!data_finish.equals(other.data_finish))
			return false;
		if (data_start == null) {
			if (other.data_start != null)
				return false;
		} else if (!data_start.equals(other.data_start))
			return false;
		if (del_status == null) {
			if (other.del_status != null)
				return false;
		} else if (!del_status.equals(other.del_status))
			return false;
		if (id_car != other.id_car)
			return false;
		if (id_order != other.id_order)
			return false;
		if (id_user != other.id_user)
			return false;
		if (login_user == null) {
			if (other.login_user != null)
				return false;
		} else if (!login_user.equals(other.login_user))
			return false;
		if (pay == null) {
			if (other.pay != null)
				return false;
		} else if (!pay.equals(other.pay))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Orders [id_order=" + id_order + ", id_user=" + id_user + ", id_car=" + id_car + ", login_user="
				+ login_user + ", data_start=" + data_start + ", data_finish=" + data_finish + ", cost=" + cost
				+ ", status=" + status + ", pay=" + pay + ", damage=" + damage + ", del_status=" + del_status + "]";
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_car() {
		return id_car;
	}
	public void setId_car(int id_car) {
		this.id_car = id_car;
	}
	public String getLogin_user() {
		return login_user;
	}
	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}
	public String getData_start() {
		return data_start;
	}
	public void setData_start(String data_start) {
		this.data_start = data_start;
	}
	public String getData_finish() {
		return data_finish;
	}
	public void setData_finish(String data_finish) {
		this.data_finish = data_finish;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}


}
