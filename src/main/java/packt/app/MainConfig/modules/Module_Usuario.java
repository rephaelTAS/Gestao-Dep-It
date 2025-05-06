package packt.app.MainConfig.modules;
import packt.database.DB_Usuario;

import java.util.Collections;
import java.util.List;

public class Module_Usuario {

    private int id;
    private String nome;
    private String senha;
    private byte[] imagem;

    DB_Usuario usuario = new DB_Usuario();
    // Construtores


    public void module_Usuario(int id, String nome, String senha, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public List<String> userLogado(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return Collections.emptyList(); // Retorna lista vazia em vez de null
        }

        List<String> dadosUsuario = usuario.buscarUsuarioPorNome(nome.trim());

        if (dadosUsuario == null || dadosUsuario.isEmpty()) {
            return Collections.emptyList();
        }

        return dadosUsuario;
    }

    public List<String> buscaUserLog(){
        return usuario.buscarUsuario();
    }

    public void atualizarUsuario(String nome){
        usuario.atualizarUsuario(nome);
    }

    public void atulizarUser(Module_Usuario user){
        usuario.atualizarUsuario(user);
    }
}