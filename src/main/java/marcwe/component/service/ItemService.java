package marcwe.component.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import marcwe.component.entity.Item;
import marcwe.component.entity.Transaction;
import marcwe.component.repository.DepositProjection;
import marcwe.component.repository.ItemRepository;
import marcwe.component.repository.TransactionProjection;
import marcwe.component.repository.WithdrawalProjection;

@Service
public class ItemService implements ItemServiceType {

	@Autowired
	public ItemRepository itemRepository;

	@Override
	public Page<Item> findAll(Pageable pageable) {
		 Page<Item> items = itemRepository.findAll(pageable);
		 return items;
	}

	@Override
	public Optional<Item> findById(long id) {
		return itemRepository.findById(id);
	}

	@Override
	public Item save(Item item) {
		itemRepository.save(item);
		return itemRepository.save(item);
	}

	@Override
	public void deleteById(long id) throws NotFoundException {
		if(itemRepository.existsById(id)) {
			itemRepository.deleteById(id);
		}else{
			throw new NotFoundException(Long.toString(id));
		};
		
	}

	@Override
	public Item update(Item item) throws NotFoundException {
		Optional<Item> itemToUpdate = itemRepository.findById(item.getNumber());
		return itemToUpdate
			.map(itemFound->{
				BeanUtils.copyProperties(item, itemFound);
				itemRepository.save(itemFound);
				return itemFound;})
			.orElseThrow(()->new NotFoundException(item.toString()));
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Long getDeposit(long itemId) throws NotFoundException {
		Optional<DepositProjection> deposit = itemRepository.findDepositByNumber(itemId);
		return deposit
			.map(t->t.getTransaction().getDeposit())
			.orElseThrow(()->new NotFoundException(Long.toString(itemId)));
	}

	@Override
	public Long getWithdrawal(long itemId)  throws NotFoundException {
		Optional<WithdrawalProjection> withdrawal = itemRepository.findWithdrawalByNumber(itemId);
		return withdrawal
			.map(t->t.getTransaction().getWithdrawal())
			.orElseThrow(()->new NotFoundException(Long.toString(itemId)));
	}

	@Override
	public Transaction getTransaction(long itemId) throws NotFoundException {
		Optional<TransactionProjection> transaction = itemRepository.findTransactionByNumber(itemId);
		return transaction
			.map(t->t.getTransaction())
			.orElseThrow(()->new NotFoundException(Long.toString(itemId)));
	}
}
