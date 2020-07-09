package com.github.springboothibernate.repository;

import com.github.springboothibernate.model.Player;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class ExtraPlayerRepositoryImpl implements ExtraPlayerRepository {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<Player> getPlayersWithNum(int num) {
    TypedQuery<Player> query = entityManager
        .createQuery("Select p from Player as p where p.num = :value", Player.class)
        .setParameter("value", num);

    return query.getResultList();
  }
}
