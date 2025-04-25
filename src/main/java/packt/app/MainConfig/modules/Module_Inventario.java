package packt.app.MainConfig.modules;

import java.time.LocalDate;

import packt.database.DB_HistoUtilizacao;
import packt.database.DB_Inventario;

public class Module_Inventario {
    private String codDep;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private String num_serie;
    private LocalDate dataEntradaServico;
    private LocalDate ultimaVerificacao;
    private String operador;
    private String funcao;
    private String localizacao;
    private String departamento;
    private String status;
    private String situacaoEquipamento;
    private String obs;

    public void module_Inventario(String codDep, String tipoEquipamento, String marca, String modelo, String num_serie, LocalDate dataEntradaServico, LocalDate ultimaVerificacao, String operador, String funcao, String localizacao, String departamento, String status, String situacaoEquipamento, String obs) {
        this.codDep = codDep;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.num_serie = num_serie;
        this.dataEntradaServico = dataEntradaServico;
        this.ultimaVerificacao = ultimaVerificacao;
        this.operador = operador;
        this.funcao = funcao;
        this.localizacao = localizacao;
        this.departamento = departamento;
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

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
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

    public void cadastrar_inventario(){
        DB_Inventario database = new DB_Inventario();
        database.inserir_DadosInventario(this);
    }

    public void cadastrar_historico(){
        DB_HistoUtilizacao database = new DB_HistoUtilizacao();
        database.inserir_historico(this);
    }

    public void atualizar_inventario() {
        DB_Inventario database = new DB_Inventario();
        database.atualizarInventario(this);
    }
}
