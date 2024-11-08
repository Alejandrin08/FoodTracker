package com.fei.solveithomeapi.repository;

import com.fei.solveithomeapi.dto.AccountDTO;
import com.fei.solveithomeapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
