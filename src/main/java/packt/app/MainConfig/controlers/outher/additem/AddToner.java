package packt.app.MainConfig.controlers.outher.additem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import packt.app.MainConfig.filtragem.Filtro_IdProdut;
import packt.app.MainConfig.filtragem.Filtro_codDep;
import packt.app.MainConfig.modules.Module_Inventario;
import packt.app.MainConfig.modules.Module_UsuToner;
import packt.app.MainConfig.notificacao.Notificacao;

import java.time.LocalDate;

public class AddToner {

    @FXML
    private TextField modeloToner;
    @FXML
    private TextField id_produt;
    @FXML
    private TextField codDep;
    @FXML
    private TextField texFil_toner;
    @FXML
    private TextField marca;
    @FXML
    private TextField texFil_cor;
    @FXML
    private TextField texFil_impressora;
    @FXML
    private TextField texFil_unidade;
    @FXML
    private DatePicker data;
    @FXML
    private TextField operador;
    @FXML
    private TextField funcao;
    @FXML
    private TextField localSala;
    @FXML
    private TextField departamento;
    @FXML
    private TextField obs;
    @FXML
    private Button btnEnviar;
    @FXML
    private Button btnLimpar;

    Notificacao notificacao = new Notificacao();
    Module_Inventario inventario = new Module_Inventario();

    public void SalvarDados() {
        // Coleta os dados dos campos
        String id_produtText = id_produt.getText();
        String codigoDep = codDep.getText();
        String tonerText = texFil_toner.getText();
        String marcaText = marca.getText();
        String corText = texFil_cor.getText();
        String impressoraText = texFil_impressora.getText();
        int unidadeValue = Integer.parseInt(texFil_unidade.getText());
        String dataValue = String.valueOf(data.getValue());
        String operadorText = operador.getText();
        String funcaoText = funcao.getText();
        String localSalaText = localSala.getText();
        String departamentoText = departamento.getText();
        String observacoes = obs.getText();

        if (codigoDep.isEmpty() || tonerText.isEmpty() || marcaText.isEmpty() || corText.isEmpty() || impressoraText.isEmpty() || operadorText.isEmpty() || funcaoText.isEmpty() || localSalaText.isEmpty() || departamentoText.isEmpty()) {
            notificacao.showError("Erro: Todos os campos obrigatórios devem ser preenchidos!");
        } else {

            Module_UsuToner moduleUsuToner = new Module_UsuToner(
                    codigoDep,
                    id_produtText,
                    tonerText,
                    marcaText,
                    corText,
                    impressoraText,
                    unidadeValue,
                    dataValue,
                    operadorText,
                    funcaoText,
                    localSalaText,
                    departamentoText,
                    observacoes
            );

            moduleUsuToner.cadastrar_dados();

            inventario.cadastrar_historico();

        }
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

    @FXML
    private void filtrarID_Produt() {
        // Executa ao pressionar Enter
        String idProdutText = id_produt.getText();
        Filtro_IdProdut filtroIdProdut = new Filtro_IdProdut();


        if (idProdutText.isEmpty()) {
            notificacao.showError("Erro O campo Código do Departamento está vazio.");

        } else {
            filtroIdProdut.filtrarPorIdProdut(idProdutText);
            texFil_toner.setText(filtroIdProdut.getTipoEquipamento());
            marca.setText(filtroIdProdut.getMarca());
            modeloToner.setText(filtroIdProdut.getModelo());
        }

    }

    // Método para inicializar o controlador (opcional)
    @FXML
    private void initialize() {

        codDep.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                buscarDadosFuncionario(); // Chama o método para buscar os dados do funcionário
            }
        });

        id_produt.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Verifica se o campo perdeu o foco
                filtrarID_Produt(); // Chama o método para buscar os dados do funcionário
            }
        });


    }
}
