package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.CadAviso;


public class AvisoDAO {
    
    public void create(CadAviso p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("INSERT INTO cadastro (titulo, conteudo, data)VALUES(?,?,?)");
            stmt.setString(1,p.getTitulo());
            stmt.setString(2,p.getConteudo());
            stmt.setString(3,p.getData());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<CadAviso> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CadAviso> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cadastro");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                CadAviso atrib = new CadAviso();
                atrib.setId(rs.getInt("id"));
                atrib.setTitulo(rs.getString("titulo"));
                atrib.setConteudo(rs.getString("conteudo"));
                atrib.setData(rs.getString("data"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
            
    public void update(CadAviso p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE cadastro SET titulo = ?, conteudo = ?, data = ? WHERE id=?");
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getConteudo());
            stmt.setString(3, p.getData());
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void delete(CadAviso p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("DELETE FROM cadastro WHERE id=?");
            
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<CadAviso> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CadAviso> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cadastro WHERE titulo LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                CadAviso atrib = new CadAviso();
                atrib.setId(rs.getInt("id"));
                atrib.setTitulo(rs.getString("titulo"));
                atrib.setConteudo(rs.getString("conteudo"));
                atrib.setData(rs.getString("data"));
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

