package com.github.springboothibernate.repository;

import com.github.springboothibernate.model.Player;
import com.github.springboothibernate.model.Team;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

  List<Player> findByTeamId(long teamId);

  @Query(value = "Select p from Player as p where p.num = ?1")
  List<Player> findByNum(int num);

  Player findByNumAndTeam(int num, Team team);

  Player findFirstByNameEndingWith(String name);

}
