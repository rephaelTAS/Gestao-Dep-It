package packt.app.MainConfig.controlers.outher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Dashboard {

    // Título principal
    @FXML private Text mainTitle;

    // Grupo: Inventário
    @FXML private Label printersInUseLabel;
    @FXML private Label miceInUseLabel;
    @FXML private Label keyboardsInUseLabel;
    @FXML private Label monitorsInUseLabel;
    @FXML private Label computersInUseLabel;
    @FXML private Label activeUsersLabel;

    // Grupo: Histórico
    @FXML private Label lastPrinterUsedLabel;
    @FXML private Label mostUsedComputerLabel;
    @FXML private Label totalMovementsLabel;

    // Grupo: Stock
    @FXML private Label printersStockLabel;
    @FXML private Label miceStockLabel;
    @FXML private Label keyboardsStockLabel;
    @FXML private Label monitorsStockLabel;
    @FXML private Label computersStockLabel;

    // Grupo: Avariados
    @FXML private Label brokenPrintersLabel;
    @FXML private Label brokenMonitorsLabel;
    @FXML private Label brokenKeyboardsLabel;
    @FXML private Label underRepairLabel;

    // Grupo: Resíduos
    @FXML private Label discardedThisMonthLabel;
    @FXML private Label recycledLabel;
    @FXML private Label pendingProcessingLabel;

    // Grupo: WiFi
    @FXML private Label wifiInUseLabel;
    @FXML private Label wifiStockLabel;
    @FXML private Label wifiModelsLabel;

    // Grupo: Toner
    @FXML private Label tonerHpUsageLabel;
    @FXML private Label tonerEpsonUsageLabel;
    @FXML private Label nextReplacementLabel;
    @FXML private Label tonerHpStockLabel;
    @FXML private Label tonerEpsonStockLabel;
    @FXML private Label tonerCriticalStockLabel;

    @FXML
    public void initialize() {
        // Aqui você pode carregar os dados iniciais
        updateInventory();
        updateHistory();
        updateStock();
        updateBrokenEquipment();
        updateWasteCenter();
        updateWifiEquipment();
        updateToner();
    }

    // Métodos de atualização organizados por categoria
    public void updateInventory() {
        printersInUseLabel.setText("Impressoras: 15");
        miceInUseLabel.setText("Ratos: 42");
        keyboardsInUseLabel.setText("Teclados: 38");
        monitorsInUseLabel.setText("Monitores: 30");
        computersInUseLabel.setText("Computadores: 25");
        activeUsersLabel.setText("Utentes: 50");
    }

    public void updateHistory() {
        lastPrinterUsedLabel.setText("Última impressora: 12/05");
        mostUsedComputerLabel.setText("Computador mais usado: PC-07");
        totalMovementsLabel.setText("Total movimentos: 128");
    }

    public void updateStock() {
        printersStockLabel.setText("Impressoras: 5");
        miceStockLabel.setText("Ratos: 12");
        keyboardsStockLabel.setText("Teclados: 8");
        monitorsStockLabel.setText("Monitores: 6");
        computersStockLabel.setText("Computadores: 4");
    }

    public void updateBrokenEquipment() {
        brokenPrintersLabel.setText("Impressoras: 2");
        brokenMonitorsLabel.setText("Monitores: 1");
        brokenKeyboardsLabel.setText("Teclados: 3");
        underRepairLabel.setText("Em reparação: 4");
    }

    public void updateWasteCenter() {
        discardedThisMonthLabel.setText("Descarte este mês: 5");
        recycledLabel.setText("Reciclados: 3");
        pendingProcessingLabel.setText("Aguardando processamento: 2");
    }

    public void updateWifiEquipment() {
        wifiInUseLabel.setText("Em uso: 8");
        wifiStockLabel.setText("Em stock: 4");
        wifiModelsLabel.setText("Modelos: TP-Link, Asus");
    }

    public void updateToner() {
        tonerHpUsageLabel.setText("Impressora HP: 3 (40% usado)");
        tonerEpsonUsageLabel.setText("Impressora Epson: 2 (60% usado)");
        nextReplacementLabel.setText("Próxima reposição: 15/06");
        tonerHpStockLabel.setText("HP 1234: 5 unidades");
        tonerEpsonStockLabel.setText("Epson 567: 3 unidades");
        tonerCriticalStockLabel.setText("Stock crítico: Não");
    }

    // Métodos para atualizações individuais (exemplo)
    public void setPrintersInUse(int quantity) {
        printersInUseLabel.setText("Impressoras: " + quantity);
    }

    public void setActiveUsers(int quantity) {
        activeUsersLabel.setText("Utentes: " + quantity);
    }

    // Adicione mais métodos setters conforme necessário
}