/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import model.DAO.hibernate.BairroHibernate;
import model.DAO.hibernate.PacienteHibernate;
import model.POJO.Bairro;
import model.POJO.Endereco;
import model.POJO.Paciente;
import model.POJO.PostoSaude;
import model.POJO.Usuario;
import org.hibernate.Session;

/**
 *
 * @author herik
 */
public class NewClass {

    public static void main(String[] args) throws SQLException {
        PacienteHibernate ph = new PacienteHibernate();
        Date d = new Date(2017,11,06);
        Paciente p = new Paciente("thest", "100", new java.sql.Date(d.getTime()), new Usuario("test", "test"), new Endereco(new Bairro("Test"), "2"), new PostoSaude("Casa", new Bairro("ldkgçdf")));
        ph.insert(p);
        /*Connection conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/saudegaranhuns", "root", "root");
        System.out.println("Conectado!");
        
        conexao.close();*/
    }
}
