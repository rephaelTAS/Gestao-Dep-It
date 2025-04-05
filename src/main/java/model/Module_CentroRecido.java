package model;

import java.time.LocalDate;

public class Module_CentroRecido {

    private String codDep;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private String numSerie;
    private LocalDate dataEntrada; // Alterado para LocalDate
    private String operador;
    private String funcao;
    private String localSala;
    private String departamento;
    private String obs;

    public void module_centroRecido(String codDep, String tipoEquipamento, String marca, String modelo, String numSerie, LocalDate dataEntrada, String operador, String funcao, String localSala, String departamento, String obs) {
        this.codDep = codDep;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.numSerie = numSerie;
        this.dataEntrada = dataEntrada;
        this.operador = operador;
        this.funcao = funcao;
        this.localSala = localSala;
        this.departamento = departamento;
        this.obs = obs;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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