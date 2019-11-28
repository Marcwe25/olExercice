package marcwe.component.repository;

public interface WithdrawalProjection {

	TransactionDeposit getTransaction();
	
	public interface TransactionDeposit{
		long getWithdrawal();
	}


}
