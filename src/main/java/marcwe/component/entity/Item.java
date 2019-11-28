package marcwe.component.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApiModel(description = "item class file for database")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Item {

	@ApiModelProperty(notes = "unique number identifyng the ressource")
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	public long number;
	@ApiModelProperty(notes = "unique number identifyng the ressource")
	public String name;
	@ApiModelProperty(notes = "name of the the ressource")
	public long amount;
	@ApiModelProperty(notes = "amount the ressource in inventory")
	public String inventoryCode;
	@ApiModelProperty(notes = "code identifyng the ressource in the inventory")
	@Embedded
	public Transaction transaction = new Transaction();

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Item [number=" + number + ", name=" + name + ", amount=" + amount + ", inventoryCode=" + inventoryCode
				+ "]";
	}
	
	
	
}
