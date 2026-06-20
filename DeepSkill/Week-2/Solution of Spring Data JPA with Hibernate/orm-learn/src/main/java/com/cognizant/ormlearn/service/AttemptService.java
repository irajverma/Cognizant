package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional(readOnly = true)
    public Attempt getAttempt(int userId, int attemptId) {
        // Fetch attempt and user details
        return attemptRepository.getAttemptWithQuestions(userId, attemptId).orElse(null);
    }

    // =========================================================================
    // TODO: Hands-on 3 - Full HQL Attempt Details Grid
    // =========================================================================
    // I attempted to map and fetch the attempt details including questions and options
    // in a single HQL query but ran into MultipleBagFetchException / LazyInitializationException.
    // I could not print the formatted options score grid because of session-closed issues.
    // I left this method as an explaining stub.
    // =========================================================================
    public void printAttemptDetails(int userId, int attemptId) {
        System.out.println("==================================================");
        System.out.println("Attempting to fetch details for User ID: " + userId + ", Attempt ID: " + attemptId);
        
        Attempt attempt = getAttempt(userId, attemptId);
        if (attempt == null) {
            System.out.println("No attempt found!");
            return;
        }

        System.out.println("User Name: " + attempt.getUser().getName());
        System.out.println("Attempt Date: " + attempt.getDate());
        System.out.println("\n[TODO: Hands-on 3 Incomplete]");
        System.out.println("I wasn't able to print the nested questions and options grid here.");
        System.out.println("Every time I accessed attemptQuestion.getAttemptOptions(), Hibernate threw:");
        System.out.println("  'org.hibernate.LazyInitializationException: could not initialize proxy - no Session'");
        System.out.println("And adding 'fetch' to all joins caused MultipleBagFetchException.");
        System.out.println("Need to ask the SME for the correct design pattern to load deep nested collections.");
        System.out.println("==================================================");
    }
}
