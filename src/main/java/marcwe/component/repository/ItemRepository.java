package marcwe.component.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import marcwe.component.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	public Optional<TransactionProjection> findTransactionByNumber(long number);

	public Optional<DepositProjection> findDepositByNumber(long number);

	public Optional<WithdrawalProjection> findWithdrawalByNumber(long number);	
	
}
