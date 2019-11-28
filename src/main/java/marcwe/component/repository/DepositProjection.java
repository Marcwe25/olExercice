package marcwe.component.repository;

public interface DepositProjection {

	TransactionDeposit getTransaction();
	
	public interface TransactionDeposit{
		long getDeposit();
	}
}
