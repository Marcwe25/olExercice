package marcwe.component.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	
	long deposit;
	long withdrawal;
	
	public long getDeposit() {
		return deposit;
	}
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	public long getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(long withdrawal) {
		this.withdrawal = withdrawal;
	}
	
	

}
