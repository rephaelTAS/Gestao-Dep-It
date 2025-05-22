package packt.app.MainConfig.modules;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import packt.database.DB_UsuToner;

public class Module_UsuToner {

    private final SimpleStringProperty codDep;
    private final SimpleStringProperty idProdut; // Adicionando o campo idProdut
    private final SimpleStringProperty toner;
    private final SimpleStringProperty marca;
    private final SimpleStringProperty cor;
    private final SimpleStringProperty impressora;
    private final SimpleIntegerProperty unidade;
    private final SimpleStringProperty data; // Usando String para data. Se for LocalDate, ajuste.
    private final SimpleStringProperty operador;
    private final SimpleStringProperty funcao;
    private final SimpleStringProperty localizacao;
    private final SimpleStringProperty departamento;
    private final SimpleStringProperty Obs;

    DB_UsuToner usuToner = new DB_UsuToner();

    // Construtor
    public Module_UsuToner(String codDep, String idProdut, String toner, String marca, String cor, String impressora, Integer unidade, String data, String operador, String funcao, String localizacao, String departamento, String Obs) {
        this.codDep = new SimpleStringProperty(codDep);
        this.idProdut = new SimpleStringProperty(idProdut); // Inicializando idProdut
        this.toner = new SimpleStringProperty(toner);
        this.marca = new SimpleStringProperty(marca);
        this.cor = new SimpleStringProperty(cor);
        this.impressora = new SimpleStringProperty(impressora);
        this.unidade = new SimpleIntegerProperty(unidade);
        this.data = new SimpleStringProperty(data);
        this.operador = new SimpleStringProperty(operador);
        this.funcao = new SimpleStringProperty(funcao);
        this.localizacao = new SimpleStringProperty(localizacao);
        this.departamento = new SimpleStringProperty(departamento);
        this.Obs = new SimpleStringProperty(Obs);
    }

    // Getters e Setters para cada propriedade

    public String getCodDep() {
        return codDep.get();
    }

    public void setCodDep(String codDep) {
        this.codDep.set(codDep);
    }

    public SimpleStringProperty codDepProperty() {
        return codDep;
    }

    public String getIdProdut() {
        return idProdut.get(); // Getter para idProdut
    }

    public void setIdProdut(String idProdut) {
        this.idProdut.set(idProdut); // Setter para idProdut
    }

    public SimpleStringProperty idProdutProperty() {
        return idProdut; // Property para idProdut
    }

    public String getToner() {
        return toner.get();
    }

    public void setToner(String toner) {
        this.toner.set(toner);
    }

    public SimpleStringProperty tonerProperty() {
        return toner;
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public String getCor() {
        return cor.get();
    }

    public void setCor(String cor) {
        this.cor.set(cor);
    }

    public SimpleStringProperty corProperty() {
        return cor;
    }

    public String getImpressora() {
        return impressora.get();
    }

    public void setImpressora(String impressora) {
        this.impressora.set(impressora);
    }

    public SimpleStringProperty impressoraProperty() {
        return impressora;
    }

    public Integer getUnidade() {
        return unidade.get();
    }

    public void setUnidade(Integer unidade) {
        this.unidade.set(unidade);
    }

    public SimpleIntegerProperty unidadeProperty() {
        return unidade;
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public String getOperador() {
        return operador.get();
    }

    public void setOperador(String operador) {
        this.operador.set(operador);
    }

    public SimpleStringProperty operadorProperty() {
        return operador;
    }

    public String getFuncao() {
        return funcao.get();
    }

    public void setFuncao(String funcao) {
        this.funcao.set(funcao);
    }

    public SimpleStringProperty funcaoProperty() {
        return funcao;
    }

    public String getLocalizacao() {
        return localizacao.get();
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao.set(localizacao);
    }

    public SimpleStringProperty localizacaoProperty() {
        return localizacao;
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public void setDepartamento(String departamento) {
        this.departamento.set(departamento);
    }

    public SimpleStringProperty departamentoProperty() {
        return departamento;
    }

    public String getObs() {
        return Obs.get();
    }

    public void setObs(String obs) {
        this.Obs.set(obs);
    }

    public SimpleStringProperty obsProperty() {
        return Obs;
    }

    public void cadastrar_dados() {
        usuToner.createToner(this);
    }
}
