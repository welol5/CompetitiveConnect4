package com.revature.beans;

import org.springframework.web.socket.WebSocketSession;

import java.util.Comparator;

public class QueuePosition{
    private Person player;
    private WebSocketSession playerSession;

    public QueuePosition(Person player, WebSocketSession playerSession) {
        this.player = player;
        this.playerSession = playerSession;
    }

    public Person getPlayer() {
        return player;
    }

    public WebSocketSession getPlayerSession() {
        return playerSession;
    }



    public static class RankComparitor implements Comparator<QueuePosition>{

        @Override
        public int compare(QueuePosition qp1, QueuePosition qp2) {
            return qp1.getPlayer().getRank() - qp2.getPlayer().getRank();
        }
    }
}
