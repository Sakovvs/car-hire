package entity;

public class Cars {
	private int id_car;
	private String marka;
	private String model;
	private String nomer;
	private Double price;
	private String status;

	public Cars(int id_car, String marka, String model, String nomer, Double price, String status) {
		this.id_car = id_car;
		this.marka = marka;
		this.model = model;
		this.nomer = nomer;
		this.price = price;
		this.status = status;
	}
	public Cars(int id_car) {
		this.id_car = id_car;
		}

	public Cars() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_car;
		result = prime * result + ((marka == null) ? 0 : marka.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((nomer == null) ? 0 : nomer.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Cars other = (Cars) obj;
		if (id_car != other.id_car)
			return false;
		if (marka == null) {
			if (other.marka != null)
				return false;
		} else if (!marka.equals(other.marka))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (nomer == null) {
			if (other.nomer != null)
				return false;
		} else if (!nomer.equals(other.nomer))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
		return "Cars [id_car=" + id_car + ", marka=" + marka + ", model=" + model + ", nomer=" + nomer + ", price="
				+ price + ", status=" + status + "]";
	}

	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNomer() {
		return nomer;
	}

	public void setNomer(String nomer) {
		this.nomer = nomer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
