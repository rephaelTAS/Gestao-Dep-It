package packt.app.MainConfig.controlers.outher.editar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import packt.app.MainConfig.modules.Module_InventarioStock;
import packt.app.MainConfig.notificacao.Notificacao;

public class EditarInventarioStock {
    // Campos do formulário
    @FXML private TextField line_cod_dep;
    @FXML private TextField line_tipo_equipamento;
    @FXML private TextField line_marca;
    @FXML private TextField line_quantidade;
    @FXML private DatePicker line_data_entrada;
    @FXML private DatePicker line_ultima_verificacao;
    @FXML private TextField line_operador;
    @FXML private TextField line_funcao;
    @FXML private TextField line_local;
    @FXML private TextField line_departamento;
    @FXML private ComboBox<String> line_situacao;
    @FXML private TextField line_obs;

    private Boolean itemEdited = false;
    private String itemSelecionado = "";
    // Botões
    @FXML private Button btn_enviar;
    @FXML private Button btn_limpar;

    private Module_InventarioStock itemAtual;
    private final Notificacao notificacao = new Notificacao();
    private Stage dialogStage;

    @FXML
    private void initialize() {
        configurarComboboxes();
        configurarBotoes();
    }

    private void configurarComboboxes() {
        line_situacao.getItems().addAll("Novo", "Usado", "Avariado", "Em Manutenção");
    }

    private void configurarBotoes() {
        btn_enviar.setOnAction(e -> salvarAlteracoes());
        btn_limpar.setOnAction(e -> limparCampos());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setItem(Module_InventarioStock item) {
        this.itemAtual = item;
        carregarDados();
    }

    private void carregarDados() {
        if (itemAtual != null) {
            itemSelecionado = itemAtual.getCodDep();
            line_cod_dep.setText(itemAtual.getCodDep());
            line_tipo_equipamento.setText(itemAtual.getTipoEquipamento());
            line_marca.setText(itemAtual.getMarca());
            line_quantidade.setText(String.valueOf(itemAtual.getQuantidade()));
            line_data_entrada.setValue(itemAtual.getDataEntradaServico());
            line_ultima_verificacao.setValue(itemAtual.getUltimaVerificacao());
            line_operador.setText(itemAtual.getOperador());
            line_funcao.setText(itemAtual.getFuncao());
            line_local.setText(itemAtual.getLocalizacao());
            line_departamento.setText(itemAtual.getDepartamento());
            line_situacao.setValue(itemAtual.getSituacaoEquipamento());
            line_obs.setText(itemAtual.getObs());
        }
    }

    private void salvarAlteracoes() {
        if (validarCampos()) {
            atualizarItem();
            try {
                // Aqui você deve chamar o método de atualização no banco de dados
                itemAtual.atualizar_invetStock(itemSelecionado);
                notificacao.showSuccess("Item atualizado com sucesso!");
                fecharJanela();
            } catch (Exception e) {
                notificacao.showError("Erro ao atualizar item: " + e.getMessage());
            }
        }
    }

    private boolean validarCampos() {
        if (line_cod_dep.getText().isEmpty()) {
            notificacao.showWarning("O campo Código do Departamento é obrigatório!");
            return false;
        }
        if (line_quantidade.getText().isEmpty()) {
            notificacao.showWarning("O campo Quantidade é obrigatório!");
            return false;
        }
        // Adicione outras validações conforme necessário
        return true;
    }

    private void atualizarItem() {
        itemAtual.setCodDep(line_cod_dep.getText());
        itemAtual.setTipoEquipamento(line_tipo_equipamento.getText());
        itemAtual.setMarca(line_marca.getText());
        itemAtual.setQuantidade(Integer.parseInt(line_quantidade.getText()));
        itemAtual.setDataEntradaServico(line_data_entrada.getValue());
        itemAtual.setUltimaVerificacao(line_ultima_verificacao.getValue());
        itemAtual.setOperador(line_operador.getText());
        itemAtual.setFuncao(line_funcao.getText());
        itemAtual.setLocalizacao(line_local.getText());
        itemAtual.setDepartamento(line_departamento.getText());
        itemAtual.setSituacaoEquipamento(line_situacao.getValue());
        itemAtual.setObs(line_obs.getText());
    }

    private void limparCampos() {
        line_cod_dep.clear();
        line_tipo_equipamento.clear();
        line_marca.clear();
        line_quantidade.clear();
        line_data_entrada.setValue(null);
        line_ultima_verificacao.setValue(null);
        line_operador.clear();
        line_funcao.clear();
        line_local.clear();
        line_departamento.clear();
        line_situacao.setValue(null);
        line_obs.clear();
    }

    private void fecharJanela() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }

    public boolean isItemEdited() {
        return itemEdited;
    }
}