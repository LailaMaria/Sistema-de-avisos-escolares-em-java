package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Aluno;

public class AlunoDAO {
    public void create(Aluno p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("INSERT INTO aluno (usuario, senha, nome, turma)VALUES(?,?,?,?)");
            stmt.setString(1,p.getUsuario());
            stmt.setString(2,p.getSenha());
            stmt.setString(3,p.getNome());
            stmt.setString(4,p.getTurma());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public boolean checkLogin(String usuario, String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno WHERE usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()){
               check = true; 
                 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
    
    public List<Aluno> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Aluno atrib = new Aluno();
                atrib.setId(rs.getInt("id"));
                atrib.setSenha(rs.getString("senha"));
                atrib.setUsuario(rs.getString("usuario"));
                atrib.setNome(rs.getString("nome"));
                atrib.setTurma(rs.getString("turma"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
    
    public void update(Aluno p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE aluno SET usuario = ?, senha = ? WHERE id=?");
            
            stmt.setString(1, p.getUsuario());
            stmt.setString(2, p.getSenha());
            stmt.setInt(3, p.getId());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    
    public void update1(Aluno p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE aluno SET usuario = ?, senha = ? WHERE usuario=?");
            
            stmt.setString(1, p.getUsuario());
            stmt.setString(2, p.getSenha());
            stmt.setString(3, p.getUsuario());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void delete(Aluno p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("DELETE FROM aluno WHERE id=?");
            
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<Aluno> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Aluno atrib = new Aluno();
                atrib.setId(rs.getInt("id"));
                atrib.setSenha(rs.getString("senha"));
                atrib.setUsuario(rs.getString("usuario"));
                atrib.setNome(rs.getString("nome"));
                atrib.setTurma(rs.getString("turma"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
    
    
    }



