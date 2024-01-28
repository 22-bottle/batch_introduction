package com.example.SpringBatchTutorial.job.DbDataReadWrite.core.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

    

}
