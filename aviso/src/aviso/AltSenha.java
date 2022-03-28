package aviso;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import model.bean.Aluno;
import model.dao.AlunoDAO;

public class AltSenha extends JFrame implements ActionListener{
    JLabel lUsu, lSenha, usu;
    JPasswordField tfSenha, tfconf;
    JButton bOk;
    JTextField user;

    public AltSenha() {
    
    ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\dd.png");
    JLabel label = new JLabel(m);
    add(label);
    
    setLayout(new FlowLayout());
    
        usu = new JLabel("Nº de matrícula: ");
        add (usu);
        user = new JTextField(19);
        add (user);
    
        lUsu = new JLabel("Nova senha: ");
        add (lUsu);
        tfSenha = new JPasswordField(21);
        add (tfSenha);
        

        lSenha = new JLabel("Confirmar senha: ");
        add (lSenha);
        tfconf = new JPasswordField(18);
        add (tfconf);
        
        bOk = new JButton("Confirmar");
        add (bOk);
        bOk.addActionListener(this);
        
        setTitle("SISAE - Sistema de Avisos Escolares");
        setSize(350,400);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setLocationRelativeTo(null);
    
    
}
    
    public void actionPerformed(ActionEvent e){
        
         
        if (e.getSource()== bOk){
            if(tfSenha.getText().equals("")==true || tfconf.getText().equals("")==true){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
            }else
            
                if(!tfSenha.getText().equals(tfconf.getText())){
                JOptionPane.showMessageDialog(null, "Senhas diferentes");
                }else{
                
                Aluno p = new Aluno();
                AlunoDAO dao = new AlunoDAO();           

                p.setUsuario(user.getText());
                p.setSenha(tfSenha.getText());

                dao.update1(p);          
                
                new Visu().setVisible(true);
                this.dispose();
            
            }
            
        }   
    }
}