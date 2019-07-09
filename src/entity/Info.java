package entity;

public class Info {
	private int id_info;
	private int id_user;
	private String surname;
	private String name;
	private String passport;
	private String tel;
	
	public Info(int id_info, int id_user, String surname, String name, String passport, String tel) {
		this.id_info = id_info;
		this.id_user = id_user;
		this.surname = surname;
		this.name = name;
		this.passport = passport;
		this.tel = tel;
	}
	
	public Info() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_info;
		result = prime * result + id_user;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Info other = (Info) obj;
		if (id_info != other.id_info)
			return false;
		if (id_user != other.id_user)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Info [id_info=" + id_info + ", id_user=" + id_user + ", surname=" + surname + ", name=" + name
				+ ", passport=" + passport + ", tel=" + tel + "]";
	}

	public int getId_info() {
		return id_info;
	}

	public void setId_info(int id_info) {
		this.id_info = id_info;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
	
	

}
