package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    // Attempted HQL query to join multiple tables (Hands-on 3 in Document 3)
    // Left incomplete because fetching multi-level collections (Attempt -> AttemptQuestion -> AttemptOption)
    // throws MultipleBagFetchException if both are fetch-joined.
    @Query("SELECT a FROM Attempt a " +
           "LEFT JOIN FETCH a.user u " +
           "LEFT JOIN FETCH a.attemptQuestions aq " +
           "WHERE u.id = :userId AND a.id = :attemptId")
    Optional<Attempt> getAttemptWithQuestions(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
