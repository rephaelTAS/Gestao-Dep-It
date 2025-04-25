package packt.app.MainConfig.controlers.outher.editar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.notificacao.Notificacao;

public class EditarInventario {

    @FXML private TextField codDep;
    @FXML private TextField tipoEqui;
    @FXML private TextField marca;
    @FXML private TextField modelo;
    @FXML private TextField numSerie;
    @FXML private DatePicker dataEntrada;
    @FXML private DatePicker dataVerificacao;
    @FXML private TextField operador;
    @FXML private TextField funcao;
    @FXML private TextField localSala;
    @FXML private TextField departamento;
    @FXML private ComboBox<String> statusCombo;
    @FXML private ComboBox<String> situacaoCombo;
    @FXML private TextField obs;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private Module_Inventario itemAtual;
    private final Notificacao notificacao = new Notificacao();

    public void inicializarCampos(Module_Inventario item) {
        this.itemAtual = item;

        codDep.setText(item.getCodDep());
        tipoEqui.setText(item.getTipoEquipamento());
        marca.setText(item.getMarca());
        modelo.setText(item.getModelo());
        numSerie.setText(item.getNum_serie());
        dataEntrada.setValue(item.getDataEntradaServico());
        dataVerificacao.setValue(item.getUltimaVerificacao());
        operador.setText(item.getOperador());
        funcao.setText(item.getFuncao());
        localSala.setText(item.getLocalizacao());
        departamento.setText(item.getDepartamento());
        statusCombo.setValue(item.getStatus());
        situacaoCombo.setValue(item.getSituacaoEquipamento());
        obs.setText(item.getObs());
    }

    @FXML
    private void initialize() {
        statusCombo.getItems().addAll("Em Funcionamento", "Ligado", "Desligado");
        situacaoCombo.getItems().addAll("Novo Equipamento", "Bom Estado", "Com Defeitos", "Avariado");

        btnSalvar.setOnAction(e -> salvarAlteracoes());
        btnCancelar.setOnAction(e -> fecharJanela());

        // Configura as opções dos ComboBoxes
        configurarComboboxes();

        // Configura os listeners para detectar alterações
        configurarListeners();

    }




    private boolean dadosAlterados = false;

    /**
     * Inicializa os campos com os dados do item
     */
    public void carregarDados(Module_Inventario item) {
        this.itemAtual = item;

        // Preenche os campos com os valores do item
        codDep.setText(item.getCodDep());
        tipoEqui.setText(item.getTipoEquipamento());
        marca.setText(item.getMarca());
        modelo.setText(item.getModelo());
        numSerie.setText(item.getNum_serie());
        dataEntrada.setValue(item.getDataEntradaServico());
        dataVerificacao.setValue(item.getUltimaVerificacao());
        operador.setText(item.getOperador());
        funcao.setText(item.getFuncao());
        localSala.setText(item.getLocalizacao());
        departamento.setText(item.getDepartamento());
        statusCombo.setValue(item.getStatus());
        situacaoCombo.setValue(item.getSituacaoEquipamento());
        obs.setText(item.getObs());
    }


    private void configurarComboboxes() {
        statusCombo.getItems().addAll("Em Funcionamento", "Ligado", "Desligado");
        situacaoCombo.getItems().addAll("Novo Equipamento", "Bom Estado", "Com Defeitos", "Avariado");
    }

    private void configurarListeners() {
        btnSalvar.setOnAction(e -> salvarAlteracoes());
        btnCancelar.setOnAction(e -> fecharJanela());
    }

    /**
     * Salva as alterações no item
     */
    private void salvarAlteracoes() {

        // Atualiza o item com os novos valores
        atualizarItem();

        try {
            itemAtual.atualizar_inventario();
            dadosAlterados = true;
            notificacao.showSuccess("Item atualizado com sucesso!");
            fecharJanela();
        } catch (Exception e) {
            notificacao.showError("Erro ao atualizar item: " + e.getMessage());
        }
    }

    /**
     * Atualiza o objeto itemAtual com os valores dos campos
     */
    private void atualizarItem() {
        itemAtual.setCodDep(codDep.getText());
        itemAtual.setTipoEquipamento(tipoEqui.getText());
        itemAtual.setMarca(marca.getText());
        itemAtual.setModelo(modelo.getText());
        itemAtual.setNum_serie(numSerie.getText());
        itemAtual.setDataEntradaServico(dataEntrada.getValue());
        itemAtual.setUltimaVerificacao(dataVerificacao.getValue());
        itemAtual.setOperador(operador.getText());
        itemAtual.setFuncao(funcao.getText());
        itemAtual.setLocalizacao(localSala.getText());
        itemAtual.setDepartamento(departamento.getText());
        itemAtual.setStatus(statusCombo.getValue());
        itemAtual.setSituacaoEquipamento(situacaoCombo.getValue());
        itemAtual.setObs(obs.getText());
    }

    /**
     * Valida se todos os campos obrigatórios foram preenchidos
     */


    /**
     * Fecha a janela de edição
     */
    public void fecharJanela() {
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    /**
     * Indica se os dados foram alterados e salvos
     */
    public boolean foramDadosAlterados() {
        return dadosAlterados;
    }

    /**
     * Obtém o item com as alterações
     */
    public Module_Inventario getItemAtualizado() {
        return itemAtual;
    }
}
