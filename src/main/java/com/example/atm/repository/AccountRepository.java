/**
 * 
 */
package com.example.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.atm.model.Account;

/**
 * @author KALYAN
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByCardNumberAndPin(String cardNumber, Integer pin);
	Account findByCardNumber(String cardNumber);
	Account findByAccountNumber(Long accountNumber);
	Account findByCustomerId(Long customerId);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Account a SET a.balance = a.balance - :balance WHERE a.customerId = :customerId")
	int updateBalance(@Param("balance") Double balance, @Param("customerId") Long customerId);
	
	
}