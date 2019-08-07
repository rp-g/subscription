package com.adidas.subscription.repositories;


import com.adidas.subscription.entities.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *  Data Access Object
 *  this interface manages persistence
 */
@Repository
public interface SubscriptionDAO extends CrudRepository<Subscription, Integer> {

}
