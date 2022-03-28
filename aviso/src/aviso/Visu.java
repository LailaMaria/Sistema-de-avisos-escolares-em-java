package aviso;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.bean.CadAviso;
import model.dao.AvisoDAO;


public class Visu extends JFrame implements ActionListener{
    JLabel lAviso, lrec;
    JButton bAt, bSair,be, bvoltar, balt,bbusca;
    JTable tabela;
    JScrollPane scroll;
    DefaultTableModel model;
    JTextField busca;
    
    
    
    public Visu() {
        ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\duv.png");
        JLabel label = new JLabel(m);
        add(label);
        setLayout(new FlowLayout());
    
        

        lrec = new JLabel("");
        add(lrec);
      
        busca = new JTextField(36);
        add (busca);
        bbusca = new JButton("Buscar");
        add (bbusca);
        bbusca.addActionListener(this);
        
        lAviso = new JLabel("Avisos:");
        add (lAviso);
        
            String[] colunas = 
            new String[]{"","Data","Título","Conteúdo"};

            String[][] dados = new String[][]{{}};

            tabela = new JTable(dados,colunas);
            model = new DefaultTableModel(dados , colunas );

            tabela.setModel(model);
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(2);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
            
            scroll = new JScrollPane();   
            scroll.setPreferredSize(new java.awt.Dimension(480, 300));
            scroll.setViewportView(tabela);
            add(scroll);
        
            model.setNumRows(0);
            AvisoDAO pdao = new AvisoDAO();
                for(CadAviso p: pdao.read()){
                    model.addRow(new Object[]{
                    p.getId(),
                    p.getData(),
                    p.getTitulo(),
                    p.getConteudo()
                    });
                }
        
        be = new JButton("Expandir");
        add (be);
        be.addActionListener(this); 
        
        balt = new JButton("Alterar senha");
        add (balt);
        balt.addActionListener(this);
        
        bvoltar = new JButton("Voltar");
        add (bvoltar);
        bvoltar.addActionListener(this); 
   
        bSair = new JButton("Sair");
        add (bSair);
        bSair.addActionListener(this); 
    
        setTitle("SISAE - Sistema de Avisos Escolares");
        setSize(510,680);
        setVisible(true);  
        this.setLocationRelativeTo(null);
        
   }

     public void readTableForDesc(String desc){
        model.setNumRows(0);
        AvisoDAO pdao = new AvisoDAO();
            
        for(CadAviso a: pdao.readForDesc(desc)){
            model.addRow(new Object[]{
               a.getId(),
                a.getData(),
               a.getTitulo(),
               a.getConteudo()
               
               
            });
            }}
    
   
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bbusca){
             
            readTableForDesc(busca.getText());
             
    }else
        
        if(e.getSource() == bSair){
            this.dispose();
        }else
             if(e.getSource()==be){
                 
                              
                    int coluna = tabela.getSelectedColumn();
                    int linha = tabela.getSelectedRow();
                    String valor = tabela.getValueAt(linha, coluna).toString();
                    
                    
		valor +=" "+ "\n" + ""+tabela.getValueAt(linha,3);
		ImageIcon s = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\AVISOO.png");
		JOptionPane.showMessageDialog(null,valor, "Aviso", JOptionPane.INFORMATION_MESSAGE, s);
                
        }else
            if(e.getSource() == bvoltar){
                new Aviso().setVisible(true);
                this.dispose();
        }else
            if(e.getSource() == balt){
                new AltSenha().setVisible(true);
                this.dispose();    
            }
    }
}
