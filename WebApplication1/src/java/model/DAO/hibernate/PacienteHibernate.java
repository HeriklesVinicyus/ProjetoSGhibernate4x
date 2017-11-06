/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.hibernate;

import java.util.List;
import model.DAO.interfaces.PacienteDAO;
import model.POJO.Paciente;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class PacienteHibernate implements PacienteDAO {

    @Override
    public void insert(Paciente o) {
        Session session = HibernateUtil.getSession();
        PostoSaudeHibernate ph = new PostoSaudeHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        EnderecoHibernate eh = new EnderecoHibernate();
        try {
            ph.insert(o.getPostoSaude());
            uh.insert(o.getUsuario());
            eh.insert(o.getEndereco());
            
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Paciente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Paciente o) {
        Session session = HibernateUtil.getSession();
        PostoSaudeHibernate ph = new PostoSaudeHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        EnderecoHibernate eh = new EnderecoHibernate();
        try {
            ph.update(o.getPostoSaude());
            uh.update(o.getUsuario());
            eh.update(o.getEndereco());
            
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Paciente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Paciente o) {
        Session session = HibernateUtil.getSession();
        PostoSaudeHibernate ph = new PostoSaudeHibernate();
        UsuarioHibernate uh = new UsuarioHibernate();
        EnderecoHibernate eh = new EnderecoHibernate();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            ph.delete(o.getPostoSaude());
            uh.delete(o.getUsuario());
            eh.delete(o.getEndereco());
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Paciente. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Paciente read(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Paciente) session.get(Paciente.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Paciente. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Paciente> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Paciente> lista = session.createQuery("from " + Paciente.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Pacientes. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

}
