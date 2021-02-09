package com.revature.data;

import com.revature.beans.GameState;
import com.revature.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStateDAO extends JpaRepository<GameState, Long> {

}
