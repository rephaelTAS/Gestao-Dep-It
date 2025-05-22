package packt.app.MainConfig.controlers.outher.additem;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import packt.app.MainConfig.filtragem.Filtro_IdProdut;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.modules.New_Produt;
import packt.app.MainConfig.notificacao.Notificacao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Inventario {

    @FXML
    private ComboBox<String> combo_id_produt;
    @FXML
    private TextField codDep;

    @FXML
    private TextField tipoEqui;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private TextField numSerie;

    @FXML
    private DatePicker dataEntrada;

    @FXML
    private DatePicker dataVerificacao;

    @FXML
    private TextField operador;

    @FXML
    private TextField funcao;

    @FXML
    private TextField localSala;

    @FXML
    private TextField departamento;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private ComboBox<String> situacaoCombo;

    @FXML
    private TextField obs;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnLimpar;

    Notificacao notificacao = new Notificacao();
    Module_Inventario inventario = new Module_Inventario();
    Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();




    // Método chamado ao clicar no botão "Enviar"
    @FXML
    public void handleEnviar() {
        // Coleta os dados dos campos
        String codigoDep = codDep.getText();
        String Id_Produt = combo_id_produt.getValue();
        String tipoEquipamento = tipoEqui.getText();
        String marcaEquipamento = marca.getText();
        String modeloEquipamento = modelo.getText();
        String numeroSerie = numSerie.getText();
        LocalDate dataEntradaServico = dataEntrada.getValue();
        LocalDate dataUltimaVerificacao = dataVerificacao.getValue();
        String operadorEquipamento = operador.getText();
        String funcaoEquipamento = funcao.getText();
        String localizacaoSala = localSala.getText();
        String departamentoEquipamento = departamento.getText();
        String status = statusCombo.getValue();
        String situacao = situacaoCombo.getValue();
        String observacoes = obs.getText();

        // Validação dos campos obrigatórios
        if (codigoDep.isEmpty() || tipoEquipamento.isEmpty() || marcaEquipamento.isEmpty() || modeloEquipamento.isEmpty() ||
                numeroSerie.isEmpty() || dataEntradaServico == null || operadorEquipamento.isEmpty() || funcaoEquipamento.isEmpty() ||
                localizacaoSala.isEmpty() || departamentoEquipamento.isEmpty() || status == null || situacao == null) {
            notificacao.showError("Erro: Todos os campos obrigatórios devem ser preenchidos!");

        }else {
            definirDados();
            inventario.cadastrar_inventario();
            inventario.cadastrar_historico();
            limparCampos();
        }

    }

    // Método para consultar o banco de dados e preencher os campos
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

    @FXML
    private void filtrarID_Produt() {
        // Executa ao pressionar Enter
        String idProdutText = combo_id_produt.getValue();


        if (idProdutText.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

        }else {
            filtroIdProdut.filtrarPorIdProdut(idProdutText);
            tipoEqui.setText(filtroIdProdut.getTipoEquipamento());
            marca.setText(filtroIdProdut.getMarca());
            modelo.setText(filtroIdProdut.getModelo());
        }

    }

    public void definirDados() {
        // Coleta os dados dos campos
        inventario.setCodDep(codDep.getText());
        inventario.setIdProdut(combo_id_produt.getValue());
        inventario.setTipoEquipamento(tipoEqui.getText());
        inventario.setMarca(marca.getText());
        inventario.setModelo(modelo.getText());
        inventario.setNum_serie(numSerie.getText());
        LocalDate dataEntradaServico = dataEntrada.getValue();
        LocalDate dataUltimaVerificacao = dataVerificacao.getValue();
        inventario.setDataEntradaServico(dataEntradaServico);
        inventario.setUltimaVerificacao(dataUltimaVerificacao);
        inventario.setOperador(operador.getText());
        inventario.setFuncao(funcao.getText());
        inventario.setLocalizacao(localSala.getText());
        inventario.setDepartamento(departamento.getText());
        inventario.setStatus(statusCombo.getValue());
        inventario.setSituacaoEquipamento(situacaoCombo.getValue());
        inventario.setObs(obs.getText());



    }

    // Método para limpar os campos do formulário
    @FXML
    private void limparCampos() {
        codDep.clear();
        tipoEqui.clear();
        marca.clear();
        modelo.clear();
        numSerie.clear();
        dataEntrada.setValue(null);
        dataVerificacao.setValue(null);
        operador.clear();
        funcao.clear();
        localSala.clear();
        departamento.clear();
        statusCombo.getSelectionModel().clearSelection();
        situacaoCombo.getSelectionModel().clearSelection();
        obs.clear();
        setCombo_id_produt();
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


    // Método para inicializar o controlador (opcional)
    @FXML
    private void initialize() {
        // Preenche as opções dos ComboBox
        statusCombo.getItems().addAll("Em Funcionamento", "Ligado", "Desligado", "No Stock");
        situacaoCombo.getItems().addAll("Novo Equipamento", "Bom Estado", "Com Defeitos", "Avariado");

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

        // Configuração do botão "Enviar"
        btnEnviar.setOnAction(event -> handleEnviar());

        // Configuração do botão "Limpar"
        btnLimpar.setOnAction(event -> limparCampos());
    }
}