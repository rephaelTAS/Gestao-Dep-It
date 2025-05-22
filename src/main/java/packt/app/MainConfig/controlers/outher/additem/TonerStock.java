package packt.app.MainConfig.controlers.outher.additem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import packt.app.MainConfig.filtragem.Filtro_IdProdut;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.modules.New_Produt;
import packt.app.MainConfig.notificacao.Notificacao;

import java.util.List;
import java.util.stream.Collectors;

public class TonerStock {
    @FXML private Button btnLimpar;
    @FXML private ComboBox<String> combo_id_produt;
    @FXML private TextField codDep;
    @FXML private TextField texFil_toner;
    @FXML private TextField marca;
    @FXML private TextField modeloToner;
    @FXML private TextField texFil_cor;
    @FXML private TextField texFil_impressora;
    @FXML private TextField texFil_unidade;
    @FXML private TextField operador;
    @FXML private TextField funcao;
    @FXML private TextField localSala;
    @FXML private TextField departamento;
    @FXML private TextField obs;
    @FXML private Button btnEnviar;

    Notificacao notificacao = new Notificacao();

    Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();


    public void salvarDados(){
        String codDepValue = codDep.getText();
        String idProdutValue = combo_id_produt.getValue();
        String tonerValue = texFil_toner.getText();
        String marcaValue = marca.getText();
        String modeloValue = modeloToner.getText();
        String corValue = texFil_cor.getText();
        String impressoraValue = texFil_impressora.getText();
        String unidadeValue = texFil_unidade.getText();
        String operadorValue = operador.getText();
        String funcaoValue = funcao.getText();
        String localSalaValue = localSala.getText();
        String departamentoValue = departamento.getText();
        String obsValue = obs.getText();
    }
    @FXML
    private void buscarDadosFuncionario() {
        // Executa ao pressionar Enter
        String codigoDep = codDep.getText();

        Filtro_codDep filtro = new Filtro_codDep();
        Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();


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

    @FXML
    private void filtrarID_Produt() {
        // Executa ao pressionar Enter
        String idProdutText = combo_id_produt.getValue();


        if (idProdutText.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

        }else {
            filtroIdProdut.filtrarPorIdProdut(idProdutText);
            texFil_toner.setText(filtroIdProdut.getTipoEquipamento());
            marca.setText(filtroIdProdut.getMarca());
            modeloToner.setText(filtroIdProdut.getModelo());
        }

    }

    public void setCombo_id_produt() {
        List<New_Produt> listaProdutos = filtroIdProdut.buscarProduto();

        if (listaProdutos != null && !listaProdutos.isEmpty()) {
            // Extrai apenas os valores da coluna que você quer (ex: idProdut, nome, etc)
            List<String> valoresColuna = listaProdutos.stream()
                    .map(produto -> produto.getIdProdut()) // ou .getNome(), .getCodigo(), etc
                    .collect(Collectors.toList());

            // Carrega todos os valores no ComboBox
            combo_id_produt.getItems().setAll(valoresColuna);

            // Opcional: Seleciona o primeiro item automaticamente
            combo_id_produt.getSelectionModel().selectFirst();
        } else {
            combo_id_produt.getItems().clear(); // Limpa se não houver dados
        }
    }

    private void LimparCampos(){

        codDep.clear();
        combo_id_produt.getSelectionModel().clearSelection();
        texFil_toner.clear();
        marca.clear();
        modeloToner.clear();
        texFil_cor.clear();
        texFil_impressora.clear();
        texFil_unidade.clear();
        operador.clear();
        funcao.clear();
        localSala.clear();
        departamento.clear();
        obs.clear();

    }

    @FXML
    private void initialize() {
        // Adiciona um listener para o campo codDep
        codDep.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });

        setCombo_id_produt();
        combo_id_produt.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                filtrarID_Produt(); // Executa quando o valor muda
            }
        });

        combo_id_produt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // Ganhou foco
                setCombo_id_produt();
            }
        });

        btnLimpar.setOnAction(event -> LimparCampos());

    }
}
