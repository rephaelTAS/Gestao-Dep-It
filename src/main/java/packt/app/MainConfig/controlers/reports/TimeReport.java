package packt.app.MainConfig.controlers.reports;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import packt.app.MainConfig.notificacao.Notificacao;
import packt.database.DB_Relatorios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TimeReport {

    @FXML private ComboBox<String> cbPeriodo;
    @FXML private DatePicker dpDataInicio;
    @FXML private DatePicker dpDataFim;
    @FXML private Label lblAdicionados;
    @FXML private Label lblRetirados;
    @FXML private Label lblManutencao;
    @FXML private LineChart<String, Number> lineChart;
    @FXML private TableView<MovimentacaoEquipamento> tableView;

    private final Notificacao notificacao = new Notificacao();
    private static final Logger LOGGER = Logger.getLogger(TimeReport.class.getName());
    private final DB_Relatorios dbRelatorios = new DB_Relatorios();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    @FXML
    public void initialize() {
        configurarComponentes();
        carregarDadosIniciais();
    }

    private void configurarComponentes() {
        // Configuração do ComboBox
        cbPeriodo.getItems().addAll("Últimos 7 dias", "Últimos 30 dias", "Este mês", "Mês passado", "Personalizado");

        cbPeriodo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean personalizado = "Personalizado".equals(newVal);
            dpDataInicio.setDisable(!personalizado);
            dpDataFim.setDisable(!personalizado);

            if (!personalizado) {
                atualizarPeriodoPredefinido(newVal);
            }
        });

        // Configuração da TableView
        configurarTableView();

        // Configuração do LineChart
        configurarGrafico();
    }

    private void configurarTableView() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<MovimentacaoEquipamento, String> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<MovimentacaoEquipamento, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<MovimentacaoEquipamento, String> colEquipamento = new TableColumn<>("Equipamento");
        colEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamento"));

        TableColumn<MovimentacaoEquipamento, String> colResponsavel = new TableColumn<>("Responsável");
        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));

        TableColumn<MovimentacaoEquipamento, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.getColumns().setAll(colData, colTipo, colEquipamento, colResponsavel, colStatus);
    }

    private void configurarGrafico() {
        lineChart.setAnimated(true);
        lineChart.setLegendVisible(true);
        lineChart.setCreateSymbols(true);

        // Configurar eixos
        CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();

        xAxis.setLabel("Data");
        yAxis.setLabel("Quantidade");
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
    }

    private void carregarDadosIniciais() {
        cbPeriodo.getSelectionModel().selectFirst();
        gerarRelatorio();
    }

    @FXML
    private void gerarRelatorio() {
        try {
            LocalDate inicio = dpDataInicio.getValue();
            LocalDate fim = dpDataFim.getValue();
            String periodo = cbPeriodo.getValue();

            // Validar período personalizado
            if ("Personalizado".equals(periodo)) {
                if (inicio == null || fim == null) {
                    notificacao.showWarning("Selecione ambas as datas para período personalizado");
                    return;
                }
                if (fim.isBefore(inicio)) {
                    notificacao.showWarning("Data final não pode ser anterior à data inicial");
                    return;
                }
            }

            // Obter dados do banco de dados
            List<MovimentacaoEquipamento> movimentacoes = dbRelatorios.obterMovimentacoesPorPeriodo(
                    periodo,
                    inicio != null ? inicio.format(DATE_FORMATTER) : null,
                    fim != null ? fim.format(DATE_FORMATTER) : null
            );

            // Atualizar interface
            atualizarResumo(movimentacoes);
            atualizarGrafico(movimentacoes);
            atualizarTabela(movimentacoes);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao gerar relatório", e);
            notificacao.showError("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void atualizarPeriodoPredefinido(String periodo) {
        LocalDate hoje = LocalDate.now();

        switch (periodo) {
            case "Últimos 7 dias":
                dpDataInicio.setValue(hoje.minusDays(7));
                dpDataFim.setValue(hoje);
                break;
            case "Últimos 30 dias":
                dpDataInicio.setValue(hoje.minusDays(30));
                dpDataFim.setValue(hoje);
                break;
            case "Este mês":
                dpDataInicio.setValue(hoje.withDayOfMonth(1));
                dpDataFim.setValue(hoje);
                break;
            case "Mês passado":
                LocalDate primeiroDiaMesPassado = hoje.minusMonths(1).withDayOfMonth(1);
                dpDataInicio.setValue(primeiroDiaMesPassado);
                dpDataFim.setValue(primeiroDiaMesPassado.withDayOfMonth(primeiroDiaMesPassado.lengthOfMonth()));
                break;
        }
    }

    private void atualizarResumo(List<MovimentacaoEquipamento> movimentacoes) {
        long adicionados = movimentacoes.stream().filter(m -> "ENTRADA".equals(m.getTipo())).count();
        long retirados = movimentacoes.stream().filter(m -> "SAÍDA".equals(m.getTipo())).count();
        long manutencao = movimentacoes.stream().filter(m -> "MANUTENÇÃO".equals(m.getStatus())).count();

        lblAdicionados.setText(String.valueOf(adicionados));
        lblRetirados.setText(String.valueOf(retirados));
        lblManutencao.setText(String.valueOf(manutencao));
    }

    private void atualizarGrafico(List<MovimentacaoEquipamento> movimentacoes) {
        lineChart.getData().clear();

        // Agrupar movimentações por data
        Map<String, Long> entradasPorData = movimentacoes.stream()
                .filter(m -> "ENTRADA".equals(m.getTipo()))
                .collect(Collectors.groupingBy(
                        MovimentacaoEquipamento::getData,
                        Collectors.counting()
                ));

        Map<String, Long> saidasPorData = movimentacoes.stream()
                .filter(m -> "SAÍDA".equals(m.getTipo()))
                .collect(Collectors.groupingBy(
                        MovimentacaoEquipamento::getData,
                        Collectors.counting()
                ));

        XYChart.Series<String, Number> seriesEntradas = new XYChart.Series<>();
        seriesEntradas.setName("Entradas");
        entradasPorData.forEach((data, count) ->
                seriesEntradas.getData().add(new XYChart.Data<>(data, count)));

        XYChart.Series<String, Number> seriesSaidas = new XYChart.Series<>();
        seriesSaidas.setName("Saídas");
        saidasPorData.forEach((data, count) ->
                seriesSaidas.getData().add(new XYChart.Data<>(data, count)));

        lineChart.getData().addAll(seriesEntradas, seriesSaidas);
    }

    private void atualizarTabela(List<MovimentacaoEquipamento> movimentacoes) {
        ObservableList<MovimentacaoEquipamento> dados = FXCollections.observableArrayList(movimentacoes);
        tableView.setItems(dados);
    }

    @FXML
    private void exportarCSV() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar Relatório CSV");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Arquivos CSV", "*.csv"));

            File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
            if (file != null) {
                try (FileWriter writer = new FileWriter(file)) {
                    // Escrever cabeçalho
                    writer.write("Data,Tipo,Equipamento,Responsável,Status\n");

                    // Escrever dados
                    for (MovimentacaoEquipamento item : tableView.getItems()) {
                        writer.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                                item.getData(),
                                item.getTipo(),
                                item.getEquipamento(),
                                item.getResponsavel(),
                                item.getStatus()));
                    }

                    notificacao.showSuccess("Relatório exportado com sucesso para: " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao exportar CSV", e);
            notificacao.showError("Erro ao exportar CSV: " + e.getMessage());
        }
    }

    @FXML
    private void imprimirRelatorio() {
        notificacao.showInfo("Funcionalidade de impressão será implementada em breve");
        // Implementação real necessitaria da API de impressão do JavaFX
    }

    public static class MovimentacaoEquipamento {
        private final SimpleStringProperty data;
        private final SimpleStringProperty tipo;
        private final SimpleStringProperty equipamento;
        private final SimpleStringProperty responsavel;
        private final SimpleStringProperty status;

        public MovimentacaoEquipamento(String data, String tipo, String equipamento,
                                       String responsavel, String status) {
            this.data = new SimpleStringProperty(data);
            this.tipo = new SimpleStringProperty(tipo);
            this.equipamento = new SimpleStringProperty(equipamento);
            this.responsavel = new SimpleStringProperty(responsavel);
            this.status = new SimpleStringProperty(status);
        }

        // Getters para as propriedades
        public String getData() { return data.get(); }
        public String getTipo() { return tipo.get(); }
        public String getEquipamento() { return equipamento.get(); }
        public String getResponsavel() { return responsavel.get(); }
        public String getStatus() { return status.get(); }

        // Property getters
        public SimpleStringProperty dataProperty() { return data; }
        public SimpleStringProperty tipoProperty() { return tipo; }
        public SimpleStringProperty equipamentoProperty() { return equipamento; }
        public SimpleStringProperty responsavelProperty() { return responsavel; }
        public SimpleStringProperty statusProperty() { return status; }
    }
}