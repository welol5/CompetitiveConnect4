package com.revature.data;

import com.revature.beans.GameHistory;
import com.revature.beans.GameState;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryDAOImpl implements GameHistoryDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();
    @Override
    public GameHistory add(GameHistory gameHistory) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(gameHistory);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
        return gameHistory;
    }

    @Override
    public GameHistory getById(Integer id) {
        Session s = hu.getSession();
        GameHistory g = s.get(GameHistory.class, id);
        s.close();
        return g;
    }

    @Override
    public List<GameHistory> getAll() {
        Session s = hu.getSession();
        String query = "from GameHistory";
        Query<GameHistory> g = s.createQuery(query, GameHistory.class);
        List<GameHistory> gameHistoryList = new ArrayList<>();
        gameHistoryList = g.getResultList();
        s.close();

        return gameHistoryList;
    }

    @Override
    public void update(GameHistory gameHistory) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(gameHistory);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(GameHistory gameHistory) {

    }
}