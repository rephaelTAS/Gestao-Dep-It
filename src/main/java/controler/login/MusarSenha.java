package controler.login;


import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.Statement;

public class MusarSenha {
    public void cadastroSenha(ActionEvent e){
        //if (pasword.getText().equals(confi.getText())){
            mudaSenha();
       // }
    }

    public void mudaSenha(){


        String nome = "Rafa";
        String senha = "1234";
        String confiSenha = "1234";

        String insertFiles = " UPDATE usuarios SET senha = '" + senha + "' WHERE nome = '" + nome + "';";



    }
}
