package packt.app.MainConfig.modules;

import java.time.LocalDate;

public class Module_ComDefeitos {

    private String codDep;
    private String idProdut; // Adicionando o campo idProdut
    private String tipoEquipamento;
    private String marca;
    private String numSerie;
    private LocalDate dataEntrada; // Alterado para LocalDate
    private LocalDate ultimaVerificacao; // Alterado para LocalDate
    private String operador;
    private String funcao;
    private String localSala;
    private String departamento;
    private String obs;

    // Construtor
    public void module_ComDefeitos(String codDep, String idProdut, String tipoEquipamento, String marca, String numSerie, LocalDate dataEntrada, LocalDate ultimaVerificacao, String operador, String funcao, String localSala, String departamento, String obs) {
        this.codDep = codDep;
        this.idProdut = idProdut; // Inicializando idProdut
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.numSerie = numSerie;
        this.dataEntrada = dataEntrada;
        this.ultimaVerificacao = ultimaVerificacao;
        this.operador = operador;
        this.funcao = funcao;
        this.localSala = localSala;
        this.departamento = departamento;
        this.obs = obs;
    }

    // Getters e Setters
    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public String getIdProdut() {
        return idProdut; // Getter para idProdut
    }

    public void setIdProdut(String idProdut) {
        this.idProdut = idProdut; // Setter para idProdut
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getUltimaVerificacao() {
        return ultimaVerificacao;
    }

    public void setUltimaVerificacao(LocalDate ultimaVerificacao) {
        this.ultimaVerificacao = ultimaVerificacao;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLocalSala() {
        return localSala;
    }

    public void setLocalSala(String localSala) {
        this.localSala = localSala;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
