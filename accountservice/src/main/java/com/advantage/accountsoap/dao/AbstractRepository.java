package com.advantage.accountsoap.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A super class for repositories, with the common repository behavior implementation.
 */
public abstract class AbstractRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}