package packt.app.MainConfig.controlers.outher.editar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.notificacao.Notificacao;

public class EditarInventario {
    // Campos do formulário
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

    private String itemSelecionado = "";

    // Botões
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private boolean itemEdited;

    // Dados e estado
    private Module_Inventario itemAtual;
    private boolean dadosSalvos = false;
    private final Notificacao notificacao = new Notificacao();
    private Stage dialogStage;

    @FXML
    private void initialize() {
        configurarComboboxes();
        configurarBotoes();

        codDep.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });
    }

    private void configurarComboboxes() {
        statusCombo.getItems().addAll("Em Funcionamento", "Ligado", "Desligado");
        situacaoCombo.getItems().addAll("Novo Equipamento", "Bom Estado", "Com Defeitos", "Avariado");
    }

    private void configurarBotoes() {
        btnSalvar.setOnAction(e -> salvarAlteracoes());
        btnCancelar.setOnAction(e -> cancelar());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setItem(Module_Inventario item) {
        this.itemAtual = item;
        carregarDados();
    }

    private void carregarDados() {
        if (itemAtual != null) {
            itemSelecionado = itemAtual.getCodDep();
            codDep.setText(itemAtual.getCodDep());
            tipoEqui.setText(itemAtual.getTipoEquipamento());
            marca.setText(itemAtual.getMarca());
            modelo.setText(itemAtual.getModelo());
            numSerie.setText(itemAtual.getNum_serie());
            dataEntrada.setValue(itemAtual.getDataEntradaServico());
            dataVerificacao.setValue(itemAtual.getUltimaVerificacao());
            operador.setText(itemAtual.getOperador());
            funcao.setText(itemAtual.getFuncao());
            localSala.setText(itemAtual.getLocalizacao());
            departamento.setText(itemAtual.getDepartamento());
            statusCombo.setValue(itemAtual.getStatus());
            situacaoCombo.setValue(itemAtual.getSituacaoEquipamento());
            obs.setText(itemAtual.getObs());
        }
    }

    private void salvarAlteracoes() {
        if (validarCampos()) {
            atualizarItem();
            try {
                itemAtual.atualizar_inventario(itemSelecionado);
                dadosSalvos = true;
                notificacao.showSuccess("Item atualizado com sucesso!");
                fecharJanela();
            } catch (Exception e) {
                notificacao.showError("Erro ao atualizar item: " + e.getMessage());
            }
        }
    }

    private boolean validarCampos() {
        // Implemente a validação dos campos conforme necessário
        if (codDep.getText().isEmpty()) {
            notificacao.showWarning("O campo Código do Departamento é obrigatório!");
            return false;
        }
        // Adicione outras validações conforme necessário
        return true;
    }

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

    private void cancelar() {
        dadosSalvos = false;
        fecharJanela();
    }

    private void fecharJanela() {
        if (dialogStage != null) {
            dialogStage.close();
        } else {
            ((Stage) btnCancelar.getScene().getWindow()).close();
        }
    }

    public boolean foiSalvo() {
        return dadosSalvos;
    }

    public Module_Inventario getItemAtualizado() {
        return itemAtual;
    }

    public boolean isItemEdited() {
        return itemEdited;
    }

    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = codDep.getText();

        Filtro_codDep filtro = new Filtro_codDep();


        if (codigoDep.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

        }else {
            filtro.filtrar_codDep(codigoDep);
            operador.setText(filtro.getOperador());
            funcao.setText(filtro.getFuncao());
            localSala.setText(filtro.getLocalSala());
            departamento.setText(filtro.getDepartamento());
        }

    }
}