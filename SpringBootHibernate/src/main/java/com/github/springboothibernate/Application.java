package com.github.springboothibernate;

import com.github.springboothibernate.model.Team;
import com.github.springboothibernate.repository.PlayerRepository;
import com.github.springboothibernate.repository.TeamRepository;
import com.github.springboothibernate.service.SoccerService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  @Autowired
  SoccerService soccerService;

  @Autowired
  PlayerRepository playerRepository;

  @Autowired
  TeamRepository teamRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void run() {

    soccerService.addBarcelonaPlayer("Xavi Hernandez", "Midfielder", 6);

    List<String> players = soccerService.getAllTeamPlayers(1);
    for (String player : players) {
      System.out.println("Introducing Barca player => " + player);
    }


    Team barcelona = teamRepository.findById(1L).get();
    System.out.println("===========");

    System.out.println(playerRepository.findByNumAndTeam(6, barcelona));
    System.out.println("===========");
    System.out.println(playerRepository.findFirstByNameEndingWith("dez"));
  }
}