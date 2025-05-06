package packt.app.MainConfig.modules;

import java.time.LocalDate;

public class Module_WifiStock {

    private String codDep;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private int quantidade;
    private LocalDate dataEntrada;
    private LocalDate ultimaVerificacao;
    private String operador;
    private String situacaoEquipamento;
    private String obs;

    // Construtor
    public void module_WifiStock(String codDep, String tipoEquipamento, String marca, String modelo, int quantidade, LocalDate dataEntrada, LocalDate ultimaVerificacao, String operador, String situacaoEquipamento, String obs) {
        this.codDep = codDep;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.ultimaVerificacao = ultimaVerificacao;
        this.operador = operador;
        this.situacaoEquipamento = situacaoEquipamento;
        this.obs = obs;
    }

    // Getters e Setters
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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