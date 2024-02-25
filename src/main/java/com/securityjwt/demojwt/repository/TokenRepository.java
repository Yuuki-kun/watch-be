package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    //get all the valid tokens for specific account
    @Query("""
    select t from Token t inner join Account u on t.account.id = u.id 
    where u.id = :accountId and (t.expired=false or t.revoked=false)
    """)
    List<Token> findAllValidTokensByAccount(Long accountId);

    Optional<Token> findByToken(String token);
}
