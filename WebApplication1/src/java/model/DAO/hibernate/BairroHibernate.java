/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernate;

import java.util.List;
import model.DAO.interfaces.BairroDAO;
import model.POJO.Bairro;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class BairroHibernate implements BairroDAO {

    @Override
    public void insert(Bairro o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Bairro o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Bairro o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Bairro read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Bairro) session.get(Bairro.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Bairro. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Bairro> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Bairro> lista = session.createQuery("from " + Bairro.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Endereço. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
