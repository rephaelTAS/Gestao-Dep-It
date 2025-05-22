package packt.app.MainConfig.modules;

import java.time.LocalDate;

public class Module_Historico {
    private String codDep;
    private String idPodut;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private String numSerie;
    private LocalDate dataEntrada;
    private LocalDate ultimaVerificacao;
    private LocalDate dataMovimentacao;
    private String operador;
    private String sala;
    private String departamento;
    private String Obs;

    //Construtor

    public void module_Historico(String codDep, String idPodut, String tipoEquipamento, String marca, String modelo, String numSerie, LocalDate dataEntrada, LocalDate ultimaVerificacao, LocalDate dataMovimentacao, String operador, String sala, String departamento, String obs) {
        this.codDep = codDep;
        this.idPodut = idPodut;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.numSerie = numSerie;
        this.dataEntrada = dataEntrada;
        this.ultimaVerificacao = ultimaVerificacao;
        this.dataMovimentacao = dataMovimentacao;
        this.operador = operador;
        this.sala = sala;
        this.departamento = departamento;
        Obs = obs;
    }

    public String getIdPodut() {
        return idPodut;
    }

    public void setIdPodut(String idPodut) {
        this.idPodut = idPodut;
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

    public LocalDate getUltimaVerificacao() {
        return ultimaVerificacao;
    }

    public void setUltimaVerificacao(LocalDate ultimaVerificacao) {
        this.ultimaVerificacao = ultimaVerificacao;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }
}
