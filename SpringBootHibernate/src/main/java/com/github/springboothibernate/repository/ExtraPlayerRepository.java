package com.github.springboothibernate.repository;

import com.github.springboothibernate.model.Player;
import java.util.List;

public interface ExtraPlayerRepository {

  List<Player> getPlayersWithNum(int num);
}
