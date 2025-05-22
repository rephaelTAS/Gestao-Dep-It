package packt.app.MainConfig.controlers.outher.additem;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import packt.app.MainConfig.modules.New_Produt;
import packt.app.MainConfig.notificacao.Notificacao;

public class CriarProdutos {

    @FXML
    private TextField idProdut, tipoEquipamento, marca, modelo, quantia, Obs;

    @FXML
    private DatePicker dataEntrada;

    @FXML
    private Button btn_enviar, btn_limpar;

    private New_Produt produtoAtual;

    Notificacao notificacao = new Notificacao();

    @FXML
    public void initialize() {
        btn_enviar.setOnAction(event -> adicionarProduto());
        btn_limpar.setOnAction(event -> limparCampos());
    }

    private void adicionarProduto() {
        try {
            produtoAtual = new New_Produt(
                    idProdut.getText(),
                    tipoEquipamento.getText(),
                    marca.getText(),
                    modelo.getText(),
                    Integer.parseInt(quantia.getText()),
                    dataEntrada.getValue(),
                    Obs.getText()
            );

            produtoAtual.adicionarProduto();
            notificacao.showSuccess("Sucesso" + "Produto adicionado com sucesso!");

        } catch (Exception e) {
            notificacao.showError("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    private void limparCampos() {
        idProdut.clear();
        tipoEquipamento.clear();
        marca.clear();
        modelo.clear();
        quantia.clear();
        dataEntrada.setValue(null);
        Obs.clear();
        produtoAtual = null;
    }

    // Exibe os dados do produto atual nos campos
    public void visualizarProduto() {
        if (produtoAtual != null) {
            idProdut.setText(produtoAtual.getIdProdut());
            tipoEquipamento.setText(produtoAtual.getTipoEquipamento());
            marca.setText(produtoAtual.getMarca());
            modelo.setText(produtoAtual.getModelo());
            quantia.setText(String.valueOf(produtoAtual.getQuantia()));
            dataEntrada.setValue(produtoAtual.getDataEntrada());
            Obs.setText(produtoAtual.getObs());
        } else {
            notificacao.showError("Nenhum produto carregado.");
        }
    }



}
