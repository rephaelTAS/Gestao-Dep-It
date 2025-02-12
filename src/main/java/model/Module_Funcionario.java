package model;

public class Module_Funcionario {
    private String codDep;
    private String nome;
    private String funcao;
    private String localizacao;
    private String departamento;

    public Module_Funcionario(String codDep, String nome, String funcao, String localizacao, String departamento) {
        this.codDep = codDep;
        this.nome = nome;
        this.funcao = funcao;
        this.localizacao = localizacao;
        this.departamento = departamento;
    }

    public String getCodDep() { return codDep; }
    public void setCodDep(String codDep) { this.codDep = codDep; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}
