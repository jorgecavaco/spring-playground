package com.example.demo.repositories;

import com.example.demo.entity.User;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  private SessionFactory getSessionFactory() {
    return entityManagerFactory.unwrap(SessionFactory.class);
  }

  @Override
  public User getUserByUsername(String username) {
    Query<User> query = getSessionFactory().getCurrentSession()
        .createQuery("SELECT u FROM User u where u.username=:username", User.class);
    query.setParameter("username", username);

    return query.uniqueResult();
  }
}