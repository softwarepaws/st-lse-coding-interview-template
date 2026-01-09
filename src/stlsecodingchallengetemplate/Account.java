package stlsecodingchallengetemplate;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
	private long accountID;
	private BigDecimal balance;

	public Account() {
		super();
	}

	public Account(long accountID, BigDecimal balance) {
		super();
		this.accountID = accountID;
		this.balance = balance;
	}
	
	public BigDecimal deposit(BigDecimal amount) {
		balance = balance.add(amount);
		return balance;
	}
	
	public BigDecimal withdraw(BigDecimal amount) {
		balance = balance.subtract(amount);
		return balance;
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountID, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountID == other.accountID && Objects.equals(balance, other.balance);
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + "]";
	}

}
