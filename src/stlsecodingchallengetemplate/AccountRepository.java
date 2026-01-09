package stlsecodingchallengetemplate;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
	private List<Account> accounts;

	public AccountRepository(List<Account> accounts) {
		super();
		this.accounts = accounts;
	}
	
	public Optional<Account> getAccountByID(long accountID) {
		return accounts.stream().filter(a -> a.getAccountID() == accountID).findFirst();
	}
	
}
