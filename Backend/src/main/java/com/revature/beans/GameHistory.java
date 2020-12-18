package com.revature.beans;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name="game_history")
public class GameHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="date_played")
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name="winning_player_id")
    private Person winner;
    @OneToOne
    @JoinColumn(name="game_state_id")
    private GameState game;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    public GameState getGame() {
        return game;
    }

    public void setGame(GameState game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameHistory oldGame = (GameHistory) o;
        return id == oldGame.id && Objects.equals(timestamp, oldGame.timestamp) && Objects.equals(winner, oldGame.winner) && Objects.equals(game, oldGame.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, winner, game);
    }

    @Override
    public String toString() {
        return "OldGame{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", winner=" + winner +
                ", game=" + game +
                '}';
    }
}
