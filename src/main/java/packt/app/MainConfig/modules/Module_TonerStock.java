package packt.app.MainConfig.modules;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Module_TonerStock {

    private final SimpleStringProperty codDep;
    private final SimpleStringProperty toner;
    private final SimpleStringProperty marca;
    private final SimpleStringProperty cor;
    private final SimpleStringProperty impressora;
    private final SimpleIntegerProperty unidade;
    private final SimpleStringProperty status;
    private final SimpleStringProperty operador;
    private final SimpleStringProperty funcao;
    private final SimpleStringProperty localizacao;
    private final SimpleStringProperty departamento;
    private final SimpleStringProperty obs;

    // Construtor
    public Module_TonerStock(String codDep, String toner, String marca, String cor, String impressora, int unidade, String status, String operador, String funcao, String localizacao, String departamento, String obs) {
        this.codDep = new SimpleStringProperty(codDep);
        this.toner = new SimpleStringProperty(toner);
        this.marca = new SimpleStringProperty(marca);
        this.cor = new SimpleStringProperty(cor);
        this.impressora = new SimpleStringProperty(impressora);
        this.unidade = new SimpleIntegerProperty(unidade);
        this.status = new SimpleStringProperty(status);
        this.operador = new SimpleStringProperty(operador);
        this.funcao = new SimpleStringProperty(funcao);
        this.localizacao = new SimpleStringProperty(localizacao);
        this.departamento = new SimpleStringProperty(departamento);
        this.obs = new SimpleStringProperty(obs);
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

    public int getUnidade() {
        return unidade.get();
    }

    public void setUnidade(int unidade) {
        this.unidade.set(unidade);
    }

    public SimpleIntegerProperty unidadeProperty() {
        return unidade;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
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
        return obs.get();
    }

    public void setObs(String obs){this.obs.set(obs);}

    public SimpleStringProperty obsProperty() {
        return obs;
    }
}