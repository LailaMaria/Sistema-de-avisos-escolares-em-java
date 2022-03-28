package aviso;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.bean.CadAviso;
import model.dao.AvisoDAO;

public class AddAviso extends JFrame implements ActionListener{
    
    JLabel lTit, lCont, ldata;
    JTextArea area;
    JButton bAdd, bsair, bexcluir, batu, b, bcad, bbusca;
    JTextField nome, cont, data, busca;
    JTable table;
    JScrollPane scroll;
    DefaultTableModel modelo;
    
    public AddAviso() {
   
    ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\duv.png");
    JLabel label = new JLabel(m);
    add(label);
    
    setLayout(new FlowLayout());
    
        lTit = new JLabel("Título do aviso: ");
        add (lTit);
        nome = new JTextField(35);
        add (nome);

        lCont = new JLabel("Conteúdo: ");
        add (lCont);
        cont = new JTextField(37);
        add (cont);
        
        ldata = new JLabel("Data do Aviso:");
        add (ldata);
        data = new JTextField(10);
        add (data);
        
        busca = new JTextField(17);
        add (busca);
        bbusca = new JButton("Buscar");
        add (bbusca);
        bbusca.addActionListener(this);
        
        
        String[] colunas = 
        new String[]{"","Data","Título","Conteúdo"};
       
        String[][] dados = new String[][]{{}};
        table = new JTable(dados,colunas);
        modelo = new DefaultTableModel(dados , colunas );
        table.setModel(modelo);
                
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        
        
        scroll = new JScrollPane();   
        scroll.setPreferredSize(new java.awt.Dimension(500, 260));
        scroll.setViewportView(table);
        add(scroll);
        
        
       
        readTable();
        
        bAdd = new JButton("Adicionar");
        add (bAdd);
        bAdd.addActionListener(this);
        
        b = new JButton("Editar");
        add (b);
        b.addActionListener(this);

        batu = new JButton("Atualizar");
        add (batu);
        batu.addActionListener(this);

        bexcluir = new JButton("Excluir");
        add (bexcluir);
        bexcluir.addActionListener(this);
        
        bcad = new JButton("Cadastro");
        add (bcad);
        bcad.addActionListener(this);
        
        bsair = new JButton("Sair");
        add (bsair);
        bsair.addActionListener(this); 
    
        setTitle("SISAE - Sistema de Avisos Escolares");
        setSize(560,690);
        setVisible(true); 
        this.setLocationRelativeTo(null);

      
    }
    
    public void readTable(){
        modelo.setNumRows(0);
        AvisoDAO pdao = new AvisoDAO();
            for(CadAviso a: pdao.read()){
            modelo.addRow(new Object[]{
               a.getId(),
               a.getData(),
               a.getTitulo(),
               a.getConteudo()
            });
            }}
    
    public void readTableForDesc(String desc){
        modelo.setNumRows(0);
        AvisoDAO pdao = new AvisoDAO();
            
        for(CadAviso a: pdao.readForDesc(desc)){
            modelo.addRow(new Object[]{
               a.getId(),
                a.getData(),
               a.getTitulo(),
               a.getConteudo()
               
               
            });
            }}
    
    
    public void KeyReleased(){
        if(table.getSelectedRow() != -1){
                
            
            data.setText(table.getValueAt(table.getSelectedRow(),1).toString());
            nome.setText(table.getValueAt(table.getSelectedRow(),2).toString());
            cont.setText(table.getValueAt(table.getSelectedRow(),3).toString());
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==bbusca){
             
            readTableForDesc(busca.getText());
             
    }else
        if(e.getSource() == bcad){
            new CadAluno().setVisible(true);
            this.dispose();
            
        }else
            
        if(e.getSource() == batu){
            if(nome.getText().equals("")==true || cont.getText().equals("")==true 
                    || data.getText().equals("")==true) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
                }else
                
                if(table.getSelectedRow() != -1){
                CadAviso p = new CadAviso();
                AvisoDAO dao = new AvisoDAO();           
               
                
                String dia = data.getText().substring(0,2);
                String mes = data.getText().substring(3,5);
                String ano = data.getText().substring(6);
                String d = ano+"/"+mes+"/"+dia;   
                
                p.setData(d);
                p.setTitulo(nome.getText());
                p.setConteudo(cont.getText());
                p.setId((int) table.getValueAt(table.getSelectedRow(), 0));
                dao.update(p);          
                
                data.setText("");
                nome.setText("");
                cont.setText("");
                readTable();
            }
        
        }else
            
        if(e.getSource() == b){
            KeyReleased();
        }else
        
        if(e.getSource() == bsair){
            System.exit(0);
        }else    
       
        if(e.getSource() == bAdd){      
            if(nome.getText().equals("")==true || cont.getText().equals("")==true
                    || data.getText().equals("")==true){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
                }else
            if(e.getSource() == bAdd){
                
            
            CadAviso p = new CadAviso();
            AvisoDAO dao = new AvisoDAO();           
            
           
            String dia = data.getText().substring(0,2);
            String mes = data.getText().substring(3,5);
            String ano = data.getText().substring(6);
            String d = ano+"/"+mes+"/"+dia;   
                
            p.setData(d);
            p.setTitulo(nome.getText());
            p.setConteudo(cont.getText());
            dao.create(p);          
            readTable();
            
            data.setText("");
            nome.setText("");
            cont.setText("");
           
        }else 
            if(e.getSource()==bexcluir){
                if(table.getSelectedRow() != -1){
                    CadAviso p = new CadAviso();
                    AvisoDAO dao = new AvisoDAO();

                    p.setId((int) table.getValueAt(table.getSelectedRow(),0));
                    dao.delete(p);
                    readTable();
                    
                    data.setText("");
                    nome.setText("");
                    cont.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha");
        }
            
               
            
            }
    }
}
}
        
    
    
    
    
  
    

