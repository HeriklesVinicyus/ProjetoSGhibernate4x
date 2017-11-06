/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernate;

import java.util.List;
import model.DAO.interfaces.AtendenteDAO;
import model.POJO.Atendente;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class AtendenteHibernate implements AtendenteDAO {

    @Override
    public void insert(Atendente o) {
        Session session = HibernateUtil.getSession();
        BairroHibernate bh = new BairroHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        try {
            bh.insert(o.getBairro());
            uh.insert(o.getUsuario());
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Atendente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Atendente o) {
        Session session = HibernateUtil.getSession();
        BairroHibernate bh = new BairroHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        
        try {
            bh.update(o.getBairro());
            uh.update(o.getUsuario());
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Atendente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Atendente o) {
        Session session = HibernateUtil.getSession();
        BairroHibernate bh = new BairroHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            bh.delete(o.getBairro());
            uh.delete(o.getUsuario());
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Atendente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Atendente read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Atendente) session.get(Atendente.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Atendente. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Atendente> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Atendente> lista = session.createQuery("from " + Atendente.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Atendentes. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
