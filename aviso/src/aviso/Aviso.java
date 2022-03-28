package aviso;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import model.dao.AlunoDAO;
import model.dao.ServidorDAO;

public class Aviso extends JFrame implements ActionListener{
    public static String variavel = null;
    JLabel lUsu, lSenha;
    JTextField tfUsu;
    JPasswordField tfSenha;
    JButton bOk, bsair;
    JRadioButton rbA, rbS;
    ButtonGroup bg;
    JMenu mEditar, mAjuda;
    JMenuItem miLimpar, miCont, miOq;
    JMenuBar barra;
   
    
public Aviso() {
    
    ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\dd.png");
    JLabel label = new JLabel(m);
    add(label);
    
    setLayout(new FlowLayout());
    
        lUsu = new JLabel("Usuário: ");
        add (lUsu);
        tfUsu = new JTextField(20);
        add (tfUsu);

        lSenha = new JLabel("  Senha: ");
        add (lSenha);
        tfSenha = new JPasswordField(20);
        add (tfSenha);

        rbA = new JRadioButton("Aluno");
        add(rbA);
        rbS = new JRadioButton("Servidor");
        add(rbS);
        bg = new ButtonGroup();
        bg.add(rbA);
        bg.add(rbS);

        bOk = new JButton("Entrar");
        add (bOk);
        bOk.addActionListener(this);

        bsair = new JButton("Sair");
        add (bsair);
        bsair.addActionListener(this);

        
        miCont = new JMenuItem("Conteúdo da Janela");
        miCont.setMnemonic('C');
        miCont.addActionListener(this);

        miOq = new JMenuItem("Sobre");
        miOq.setMnemonic('O');
        miOq.addActionListener(this);

        mAjuda = new JMenu("Ajuda");
        mAjuda.add(miCont);
        mAjuda.add(miOq);
        
        miLimpar = new JMenuItem("Limpar");
        miLimpar.setMnemonic('L');
        miLimpar.addActionListener(this);
        mEditar = new JMenu("Editar");
        mEditar.add(miLimpar);

        barra = new JMenuBar();
        barra.add(mAjuda);
        barra.add(mEditar);
        setJMenuBar(barra);  


        setTitle("SISAE - Sistema de Avisos Escolares");
        setSize(350,400);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setLocationRelativeTo(null);
    
}


public void actionPerformed(ActionEvent e){
    if (e.getSource()==miLimpar){
        tfUsu.setText("");
        tfSenha.setText("");
        bg.clearSelection();
            
    }else 
        if (e.getSource() == bsair){
            switch (JOptionPane.showConfirmDialog(null, "Realmente deseja sair?")){
                case 0:
                    System.out.println("");
                    System.exit(0);
                break;
                case 1:
                    System.out.println("");
                break;
            }
            
        }else 
            if (e.getSource() == miCont){
                ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\ajuda.png");
                JOptionPane.showMessageDialog(null,
                        "Para acessar sua conta, insira no campo 'Usuário' seu número de matrícula, \n" +
                        "previamente cadastrado no sistema, e preencha o campo 'Senha' com sua \n"
                      + "respectiva palavra-chave. Selecione dentre os módulos 'Aluno' e 'Servidor' \n"
                      + "qual você irá usar para acessar o SISAE." ,
                        "Ajuda", JOptionPane.INFORMATION_MESSAGE, m); 
        
        }else 
            if (e.getSource() == miOq){
                ImageIcon m = new ImageIcon("C:\\Users\\Lindomar\\Desktop\\image\\sobre.png");
                JOptionPane.showMessageDialog(null, "O software presente visa facilitar o acesso de estudantes aos frequentes comunicados \n do "
                        + "Instituto Federal do Maranhão. Dessa forma, o SISAE detém uma melhora no que diz \n respeito "
                        + "à disseminação de informes no âmbito do IFMA -  Campus Imperatriz para que \n assim possa-se evitar "
                        + "mal-entendidos, poupar o tempo gasto na divulgação oral de \n informe, dentre outros problemas relacionados à comunicação.",
                        "Ajuda", JOptionPane.INFORMATION_MESSAGE, m);
            
    }else    
        if(tfSenha.getText().equals("")==true || tfUsu.getText().equals("")==true){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", 2);
    }else
        if(rbA.isSelected() == false && rbS.isSelected() == false ){
            JOptionPane.showMessageDialog(null,"Selecione uma opção", "Aviso", 2);
    }else
        if(rbA.isSelected()){
            AlunoDAO dao = new AlunoDAO();
                if(dao.checkLogin(tfUsu.getText(), tfSenha.getText()) && tfSenha.getText().equals("123")==true){
                                
                    AltSenha p = new AltSenha();
                    p.user.setText(String.valueOf(tfUsu.getText()));
                    this.dispose();
                }else{ 
          
        if(dao.checkLogin(tfUsu.getText(), tfSenha.getText())){
            new Visu().setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
        }}
    
        }else
        if(rbS.isSelected()){
            ServidorDAO dao = new ServidorDAO();
        
        if(dao.checkLogin(tfUsu.getText(), tfSenha.getText())){
            new AddAviso().setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
        }
    
}
}
    public static void main(String[] args) {
        Aviso s = new Aviso();
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        
    }

    
}
    


