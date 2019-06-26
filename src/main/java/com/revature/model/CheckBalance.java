package com.revature.model;

public class CheckBalance {
	
	private long id;
	private long accno;
	private float balance;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccno() {
		return accno;
	}
	public void setAccno(long accno) {
		this.accno = accno;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public CheckBalance(long id, long accno, float balance) {
		super();
		this.id = id;
		this.accno = accno;
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accno ^ (accno >>> 32));
		result = prime * result + Float.floatToIntBits(balance);
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
		CheckBalance other = (CheckBalance) obj;
		if (accno != other.accno)
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CheckBalance [id=" + id + ", accno=" + accno + ", balance=" + balance + "]";
	}
	

}
