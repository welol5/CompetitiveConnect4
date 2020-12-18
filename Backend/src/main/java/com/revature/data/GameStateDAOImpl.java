package com.revature.data;

import com.revature.beans.GameState;
import com.revature.utils.HibernateUtil;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GameStateDAOImpl implements GameStateDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();
    @Override
    public GameState add(GameState gameState) {

        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(gameState);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
        return gameState;
    }

    @Override
    public GameState getById(Integer id) {
        Session s = hu.getSession();
        GameState g = s.get(GameState.class, id);
        s.close();
        return g;
    }

    @Override
    public List<GameState> getAll() {
        Session s = hu.getSession();
        String query = "from GameState";
        Query<GameState> g = s.createQuery(query, GameState.class);
        List<GameState> gameStateList = new ArrayList<>();
        gameStateList = g.getResultList();
        s.close();

        return gameStateList;
    }

    @Override
    public void update(GameState gameState) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(gameState);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(GameState gameState) {

    }
}
