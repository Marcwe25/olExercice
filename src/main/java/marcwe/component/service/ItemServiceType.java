package marcwe.component.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javassist.NotFoundException;
import marcwe.component.entity.Item;
import marcwe.component.entity.Transaction;

public interface ItemServiceType {

	Page<Item> findAll(Pageable pageable);

	Optional<Item> findById(long id);

	Item save(Item item);

	void deleteById(long id) throws NotFoundException;

	Item update(Item item) throws NotFoundException;

	List<Item> findAll();

	Long getDeposit(long id) throws NotFoundException ;

	Long getWithdrawal(long itemId) throws NotFoundException ;

	Transaction getTransaction(long itemNumber) throws NotFoundException ;

}
