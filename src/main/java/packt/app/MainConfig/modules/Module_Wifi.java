package packt.app.MainConfig.modules;

import java.time.LocalDate;

public class Module_Wifi {
    private String codDep;
    private String IdProduct;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private int quantidade; // Campo para quantidade
    private LocalDate dataEntrada; // Pode ser null
    private LocalDate dataVerificacao; // Pode ser null
    private String operador;
    private String funcao;
    private String status;
    private String situacaoEquipamento;
    private String obs;

    public void module_Wifi(String codDep, String IdProduct, String tipoEquipamento, String marca, String modelo, int quantidade, LocalDate dataEntrada, LocalDate dataVerificacao, String operador, String funcao, String status, String situacaoEquipamento, String obs) {
        this.codDep = codDep;
        this.IdProduct = IdProduct;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.dataVerificacao = dataVerificacao;
        this.operador = operador;
        this.funcao = funcao;
        this.status = status;
        this.situacaoEquipamento = situacaoEquipamento;
        this.obs = obs;
    }

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public String getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(String idProduct) {
        IdProduct = idProduct;
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

    public LocalDate getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(LocalDate dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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