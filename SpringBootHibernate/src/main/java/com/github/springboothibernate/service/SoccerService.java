package com.github.springboothibernate.service;

import java.util.List;

public interface SoccerService {
	List<String> getAllTeamPlayers(long teamId);
	void addBarcelonaPlayer(String name, String position, int number);
}
