package packt.app.MainConfig.modules;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import packt.database.DB_TonerStock;

import java.time.LocalDate;

public class Module_TonerStock {

    private String codDep;
    private String idProdut; // Adicionando o campo idProdut
    private String toner;
    private String marca;
    private String modelo;
    private String cor;
    private String impressora;
    private LocalDate dataUsu;
    private String status;
    private String operador;
    private String funcao;
    private String localizacao;
    private String departamento;
    private String obs;

    DB_TonerStock tonerStock = new DB_TonerStock();


    public void module_TonerStock(String codDep, String idProdut, String toner, String marca, String modelo, String cor, String impressora, LocalDate dataUsu, String status, String operador, String funcao, String localizacao, String departamento, String obs) {
        this.codDep = codDep;
        this.idProdut = idProdut;
        this.toner = toner;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.impressora = impressora;
        this.dataUsu = dataUsu;
        this.status = status;
        this.operador = operador;
        this.funcao = funcao;
        this.localizacao = localizacao;
        this.departamento = departamento;
        this.obs = obs;
    }

    public String getCodDep() {
        return codDep;
    }

    public void setCodDep(String codDep) {
        this.codDep = codDep;
    }

    public String getIdProdut() {
        return idProdut;
    }

    public void setIdProdut(String idProdut) {
        this.idProdut = idProdut;
    }

    public String getToner() {
        return toner;
    }

    public void setToner(String toner) {
        this.toner = toner;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }

    public LocalDate getDataUsu() {
        return dataUsu;
    }

    public void setDataUsu(LocalDate dataUsu) {
        this.dataUsu = dataUsu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void criarDados(){
        tonerStock.createTonerStock(this);
    }
}
