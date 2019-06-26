package com.revature.model;

public class FromTransaction {
	
	private long id;
	private long saccno;
	private Float bal;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSaccno() {
		return saccno;
	}
	public void setSaccno(long saccno) {
		this.saccno = saccno;
	}
	public Float getBal() {
		return bal;
	}
	public void setBal(Float bal) {
		this.bal = bal;
	}
	public FromTransaction(long id, long saccno, Float bal) {
		super();
		this.id = id;
		this.saccno = saccno;
		this.bal = bal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bal == null) ? 0 : bal.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (saccno ^ (saccno >>> 32));
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
		FromTransaction other = (FromTransaction) obj;
		if (bal == null) {
			if (other.bal != null)
				return false;
		} else if (!bal.equals(other.bal))
			return false;
		if (id != other.id)
			return false;
		if (saccno != other.saccno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FromTransaction [id=" + id + ", saccno=" + saccno + ", bal=" + bal + "]";
	}
}