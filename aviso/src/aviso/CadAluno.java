package aviso;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.bean.Aluno;
import model.dao.AlunoDAO;

public class CadAluno extends JFrame implements ActionListener{
    
    JLabel lTit, lCont, alu, turma;
    JTextArea area;
    JButton bAdd, bsair, bexcluir, batu, b, bpes;
    JTextField nome, cont, pesq, tfalu, tfturma;
    JTable table;
    JScrollPane scroll;
    DefaultTableModel modelo;
    
    public CadAluno() {
    
    ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\duv.png");
    JLabel label = new JLabel(m);
    add(label);
    
    setLayout(new FlowLayout());
    
        alu = new JLabel("Aluno: ");
        add (alu);
        tfalu = new JTextField(40);
        add (tfalu);

        turma = new JLabel("Turma:  ");
        add (turma);
        tfturma = new JTextField(15);
        add (tfturma);

        lTit = new JLabel(" Matrícula: ");
        add (lTit);
        nome = new JTextField(17);
        add (nome);

        lCont = new JLabel("Senha:  ");
        add (lCont);
        cont = new JTextField(10);
        add (cont);


        pesq = new JTextField(20);
        add (pesq);

        bpes= new JButton(" Buscar ");
        add (bpes);
        bpes.addActionListener(this);

         
        String[] colunas = 
            new String[]{"","Turma", "Aluno","Matrícula"};

        String[][] dados = new String[][]{{}};
              
        table = new JTable(dados,colunas);
        modelo = new DefaultTableModel(dados , colunas );
        table.setModel(modelo);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(220);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        
        scroll = new JScrollPane();   
        scroll.setPreferredSize(new java.awt.Dimension(480, 290));
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
        
        bsair = new JButton("Sair");
        add (bsair);
        bsair.addActionListener(this); 
    
        setTitle("SISAE - Sistema de Avisos Escolares");
        setSize(510,690);
        setVisible(true); 
        this.setLocationRelativeTo(null);           
    
    }
    
    public void readTable(){
        modelo.setNumRows(0);
        AlunoDAO pdao = new AlunoDAO();
            for(Aluno a: pdao.read()){
            modelo.addRow(new Object[]{
               a.getId(),
               a.getTurma(),
               a.getNome(),               
               a.getUsuario(),
               
               
            });
            }}
    
    public void readTableForDesc(String desc){
        modelo.setNumRows(0);
        AlunoDAO pdao = new AlunoDAO();
            
        for(Aluno a: pdao.readForDesc(desc)){
            modelo.addRow(new Object[]{
              a.getId(),
               a.getTurma(),
               a.getNome(),               
               a.getUsuario(),
              
               
            });
            }}
    
    public void KeyReleased(){
        if(table.getSelectedRow() != -1){
            
            tfturma.setText(table.getValueAt(table.getSelectedRow(),1).toString());  
            tfalu.setText(table.getValueAt(table.getSelectedRow(),2).toString());
            nome.setText(table.getValueAt(table.getSelectedRow(),3).toString());
        }
    }
   
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bsair){
            AddAviso p = new AddAviso();
            p.setVisible(true);
            this.dispose();
        }else
        
        if(e.getSource()==bpes){
             
            readTableForDesc(pesq.getText());
             
    }else
        if(e.getSource() == batu){
            
                if(tfalu.getText().equals("")==true || tfturma.getText().equals("")==true
                    || nome.getText().equals("")==true || cont.getText().equals("")==true){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
                }else
                
                if(table.getSelectedRow() != -1){
                Aluno p = new Aluno();
                AlunoDAO dao = new AlunoDAO();           

                p.setNome(tfalu.getText());
                p.setTurma(tfturma.getText());
                p.setUsuario(nome.getText());
                p.setSenha(cont.getText());
                p.setId((int) table.getValueAt(table.getSelectedRow(), 0));
                dao.update(p);          
                
                tfalu.setText("");
                tfturma.setText("");
                nome.setText("");
                cont.setText("");
                readTable();
                
            }
        
        }else
            
        if(e.getSource() == b){
            KeyReleased();
        
        }else    
       
        if(e.getSource() == bAdd){   
            if(tfalu.getText().equals("")==true || tfturma.getText().equals("")==true
                    || nome.getText().equals("")==true || cont.getText().equals("")==true){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
                }else
            if(e.getSource() == bAdd){
                     
            Aluno p = new Aluno();
            AlunoDAO dao = new AlunoDAO();           
            
            p.setNome(tfalu.getText());
            p.setTurma(tfturma.getText());
            p.setUsuario(nome.getText());
            p.setSenha(cont.getText());
            dao.create(p);          
            readTable();
            
            tfalu.setText("");
            tfturma.setText("");
            nome.setText("");
            cont.setText("");
           
        }else 
            
            if(e.getSource()==bexcluir){
                    
                if(table.getSelectedRow() != -1){
                Aluno p = new Aluno();
                AlunoDAO dao = new AlunoDAO();

                p.setId((int) table.getValueAt(table.getSelectedRow(),0));
                dao.delete(p);
                readTable();
                    
                    
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha");
        
        }               
        
    }
}
}
}