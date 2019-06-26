package com.revature.model;

public class ToTransaction {
	
	private long id;
	private long daccno;
	private Float bal;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDaccno() {
		return daccno;
	}
	public void setDaccno(long daccno) {
		this.daccno = daccno;
	}
	public Float getBal() {
		return bal;
	}
	public void setBal(Float bal) {
		this.bal = bal;
	}
	public ToTransaction(long id, long daccno, Float bal) {
		super();
		this.id = id;
		this.daccno = daccno;
		this.bal = bal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bal == null) ? 0 : bal.hashCode());
		result = prime * result + (int) (daccno ^ (daccno >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ToTransaction other = (ToTransaction) obj;
		if (bal == null) {
			if (other.bal != null)
				return false;
		} else if (!bal.equals(other.bal))
			return false;
		if (daccno != other.daccno)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ToTransaction [id=" + id + ", daccno=" + daccno + ", bal=" + bal + "]";
	}
}