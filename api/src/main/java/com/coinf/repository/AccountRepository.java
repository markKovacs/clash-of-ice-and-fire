package com.coinf.repository;

import com.coinf.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account getByUserName(String username);

}
