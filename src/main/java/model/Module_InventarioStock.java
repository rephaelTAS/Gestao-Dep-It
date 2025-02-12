package model;

import java.time.LocalDate;

public class Module_InventarioStock {
    private int id;
    private String codDep;
    private String tipoEquipamento;
    private String marca;
    private int quantidade;
    private LocalDate dataEntradaServico;
    private LocalDate  ultimaVerificacao;
    private String operador;
    private String funcao;
    private String localizacao;
    private String departamento;
    private String situacaoEquipamento;
    private String obs;

    public Module_InventarioStock(int id, String codDep, String tipoEquipamento, String marca, int quantidade, LocalDate dataEntradaServico, LocalDate ultimaVerificacao, String operador, String funcao, String localizacao, String departamento, String situacaoEquipamento, String obs) {
        this.id = id;
        this.codDep = codDep;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.quantidade = quantidade;
        this.dataEntradaServico = dataEntradaServico;
        this.ultimaVerificacao = ultimaVerificacao;
        this.operador = operador;
        this.funcao = funcao;
        this.localizacao = localizacao;
        this.departamento = departamento;
        this.situacaoEquipamento = situacaoEquipamento;
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataEntradaServico() {
        return dataEntradaServico;
    }

    public void setDataEntradaServico(LocalDate dataEntradaServico) {
        this.dataEntradaServico = dataEntradaServico;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSituacaoEquipamento() {
        return situacaoEquipamento;
    }

    public void setSituacaoEquipamento(String situacaoEquipamento) {
        this.situacaoEquipamento = situacaoEquipamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
