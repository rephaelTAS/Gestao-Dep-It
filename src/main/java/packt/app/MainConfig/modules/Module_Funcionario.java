package packt.app.MainConfig.modules;

import packt.database.DB_Funcionario;

import java.util.List;

public class Module_Funcionario {
    private String codDep;
    private String nome;
    private String funcao;
    private String local;
    private String departamento;
    private byte[] foto;

    DB_Funcionario funcionario = new DB_Funcionario();

    // Construtor correto
    public void module_Funcionario(String codDep, String nome, String funcao, String local, String departamento, byte[] foto) {
        this.codDep = codDep;
        this.nome = nome;
        this.funcao = funcao;
        this.local = local;
        this.departamento = departamento;
        this.foto = foto;
    }

    // Getters e Setters
    public String getCodDep() { return codDep; }
    public void setCodDep(String codDep) { this.codDep = codDep; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }


    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }


}
