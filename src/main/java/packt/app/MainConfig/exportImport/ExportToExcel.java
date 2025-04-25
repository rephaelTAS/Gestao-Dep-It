package packt.app.MainConfig.exportImport;


import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import packt.app.MainConfig.modules.*;
import packt.app.MainConfig.notificacao.Notificacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class ExportToExcel {
    Notificacao notificacao = new Notificacao();

    public String escolherCaminhoArquivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        return (file != null) ? file.getAbsolutePath() : null;
    }

    // Método genérico para criar estilo de data
    private CellStyle createDateCellStyle(Workbook workbook) {
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        return dateCellStyle;
    }

    // Método genérico para salvar workbook
    private void saveWorkbook(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            notificacao.showSuccess("Arquivo Excel salvo em: " + filePath);
        } catch (IOException e) {
            notificacao.showError("Erro ao salvar o arquivo Excel: " + e.getMessage());
            throw e;
        } finally {
            workbook.close();
        }
    }

    // Exportar Module_CentroRecido
    public void exportarCentroRecidoParaExcel(ObservableList<Module_CentroRecido> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Centro Recido");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Tipo Equipamento", "Marca", "Modelo",
                "Número Série", "Data Entrada", "Operador", "Função",
                "Local/Sala", "Departamento", "Observações"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        CellStyle dateStyle = createDateCellStyle(workbook);

        int rowNum = 1;
        for (Module_CentroRecido item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getModelo());
            row.createCell(4).setCellValue(item.getNumSerie());

            Cell dataCell = row.createCell(5);
            if (item.getDataEntrada() != null) {
                dataCell.setCellValue(item.getDataEntrada());
                dataCell.setCellStyle(dateStyle);
            }

            row.createCell(6).setCellValue(item.getOperador());
            row.createCell(7).setCellValue(item.getFuncao());
            row.createCell(8).setCellValue(item.getLocalSala());
            row.createCell(9).setCellValue(item.getDepartamento());
            row.createCell(10).setCellValue(item.getObs());
        }

        saveWorkbook(workbook, filePath);
    }

    // Exportar Module_ComDefeitos
    public void exportarComDefeitosParaExcel(ObservableList<Module_ComDefeitos> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Equip. com Defeitos");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Tipo Equipamento", "Marca", "Número Série",
                "Data Entrada", "Última Verificação", "Operador", "Função",
                "Local/Sala", "Departamento", "Observações"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        CellStyle dateStyle = createDateCellStyle(workbook);

        int rowNum = 1;
        for (Module_ComDefeitos item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getNumSerie());

            Cell dataEntradaCell = row.createCell(4);
            if (item.getDataEntrada() != null) {
                dataEntradaCell.setCellValue(item.getDataEntrada());
                dataEntradaCell.setCellStyle(dateStyle);
            }

            Cell dataVerifCell = row.createCell(5);
            if (item.getUltimaVerificacao() != null) {
                dataVerifCell.setCellValue(item.getUltimaVerificacao());
                dataVerifCell.setCellStyle(dateStyle);
            }

            row.createCell(6).setCellValue(item.getOperador());
            row.createCell(7).setCellValue(item.getFuncao());
            row.createCell(8).setCellValue(item.getLocalSala());
            row.createCell(9).setCellValue(item.getDepartamento());
            row.createCell(10).setCellValue(item.getObs());
        }

        saveWorkbook(workbook, filePath);
    }

    // Exportar Module_Funcionario
    public void exportarFuncionariosParaExcel(ObservableList<Module_Funcionario> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Funcionários");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Nome", "Função", "Localização", "Departamento"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Module_Funcionario item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getNome());
            row.createCell(2).setCellValue(item.getFuncao());
            row.createCell(3).setCellValue(item.getLocalizacao());
            row.createCell(4).setCellValue(item.getDepartamento());
        }

        saveWorkbook(workbook, filePath);
    }

    // Exportar Module_TonerStock
    public void exportarTonerStockParaExcel(ObservableList<Module_TonerStock> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Estoque de Toner");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Toner", "Marca", "Cor", "Impressora",
                "Unidades", "Status", "Operador", "Função", "Localização", "Departamento"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Module_TonerStock item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getToner());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getCor());
            row.createCell(4).setCellValue(item.getImpressora());
            row.createCell(5).setCellValue(item.getUnidade());
            row.createCell(6).setCellValue(item.getStatus());
            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getFuncao());
            row.createCell(9).setCellValue(item.getLocalizacao());
            row.createCell(10).setCellValue(item.getDepartamento());
        }

        saveWorkbook(workbook, filePath);
    }

    // Exportar Module_UsuToner
    public void exportarUsuTonerParaExcel(ObservableList<Module_UsuToner> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Uso de Toner");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Toner", "Marca", "Cor", "Impressora",
                "Unidades", "Data Uso", "Operador", "Função", "Localização", "Departamento", "Observações"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        CellStyle dateStyle = createDateCellStyle(workbook);

        int rowNum = 1;
        for (Module_UsuToner item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getToner());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getCor());
            row.createCell(4).setCellValue(item.getImpressora());
            row.createCell(5).setCellValue(item.getUnidade());

            Cell dataCell = row.createCell(6);
            if (item.getData() != null && !item.getData().isEmpty()) {
                try {
                    dataCell.setCellValue(LocalDate.parse(item.getData()));
                    dataCell.setCellStyle(dateStyle);
                } catch (Exception e) {
                    dataCell.setCellValue(item.getData());
                }
            }

            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getFuncao());
            row.createCell(9).setCellValue(item.getLocalizacao());
            row.createCell(10).setCellValue(item.getDepartamento());
            row.createCell(11).setCellValue(item.getObs());
        }

        saveWorkbook(workbook, filePath);
    }

    // Exportar Module_Wifi
    public void exportarWifiParaExcel(ObservableList<Module_Wifi> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Equip. Wi-Fi");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Tipo Equipamento", "Marca", "Modelo", "Quantidade",
                "Data Entrada", "Data Verificação", "Operador", "Função", "Status",
                "Situação", "Observações"
        };

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        CellStyle dateStyle = createDateCellStyle(workbook);

        int rowNum = 1;
        for (Module_Wifi item : dados) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getModelo());
            row.createCell(4).setCellValue(item.getQuantidade());

            Cell dataEntradaCell = row.createCell(5);
            if (item.getDataEntrada() != null) {
                dataEntradaCell.setCellValue(item.getDataEntrada());
                dataEntradaCell.setCellStyle(dateStyle);
            }

            Cell dataVerifCell = row.createCell(6);
            if (item.getDataVerificacao() != null) {
                dataVerifCell.setCellValue(item.getDataVerificacao());
                dataVerifCell.setCellStyle(dateStyle);
            }

            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getFuncao());
            row.createCell(9).setCellValue(item.getStatus());
            row.createCell(10).setCellValue(item.getSituacaoEquipamento());
            row.createCell(11).setCellValue(item.getObs());
        }

        saveWorkbook(workbook, filePath);
    }

    // Método para exportar Module_WifiStock
    public void exportarWifiStockParaExcel(ObservableList<Module_WifiStock> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Estoque Wi-Fi");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Tipo Equipamento", "Marca", "Modelo",
                "Quantidade", "Data Entrada", "Última Verificação",
                "Operador", "Situação Equipamento", "Observações"
        };

        // Criar células de cabeçalho
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Estilo para datas
        CellStyle dateStyle = createDateCellStyle(workbook);

        // Preencher dados
        int rowNum = 1;
        for (Module_WifiStock item : dados) {
            Row row = sheet.createRow(rowNum++);

            // Dados básicos
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getModelo());
            row.createCell(4).setCellValue(item.getQuantidade());

            // Tratamento de datas
            Cell dataEntradaCell = row.createCell(5);
            if (item.getDataEntrada() != null) {
                dataEntradaCell.setCellValue(item.getDataEntrada());
                dataEntradaCell.setCellStyle(dateStyle);
            }

            Cell dataVerificacaoCell = row.createCell(6);
            if (item.getUltimaVerificacao() != null) {
                dataVerificacaoCell.setCellValue(item.getUltimaVerificacao());
                dataVerificacaoCell.setCellStyle(dateStyle);
            }

            // Demais campos
            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getSituacaoEquipamento());
            row.createCell(9).setCellValue(item.getObs());
        }

        // Auto-ajuste das colunas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Salvar arquivo
        saveWorkbook(workbook, filePath);
    }

    // Método para exportar Module_Inventario
    public void exportarInventarioParaExcel(ObservableList<Module_Inventario> dados, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Inventário");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Código Dep.", "Tipo Equipamento", "Marca", "Modelo",
                "Número Série", "Data Entrada Serviço", "Última Verificação",
                "Operador", "Função", "Localização", "Departamento",
                "Status", "Situação Equipamento", "Observações"
        };

        // Criar células de cabeçalho
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Estilo para datas
        CellStyle dateStyle = createDateCellStyle(workbook);

        // Preencher dados
        int rowNum = 1;
        for (Module_Inventario item : dados) {
            Row row = sheet.createRow(rowNum++);

            // Dados básicos
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getModelo());
            row.createCell(4).setCellValue(item.getNum_serie());

            // Tratamento de datas
            Cell dataEntradaCell = row.createCell(5);
            if (item.getDataEntradaServico() != null) {
                dataEntradaCell.setCellValue(item.getDataEntradaServico());
                dataEntradaCell.setCellStyle(dateStyle);
            }

            Cell dataVerificacaoCell = row.createCell(6);
            if (item.getUltimaVerificacao() != null) {
                dataVerificacaoCell.setCellValue(item.getUltimaVerificacao());
                dataVerificacaoCell.setCellStyle(dateStyle);
            }

            // Demais campos
            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getFuncao());
            row.createCell(9).setCellValue(item.getLocalizacao());
            row.createCell(10).setCellValue(item.getDepartamento());
            row.createCell(11).setCellValue(item.getStatus());
            row.createCell(12).setCellValue(item.getSituacaoEquipamento());
            row.createCell(13).setCellValue(item.getObs());
        }

        // Auto-ajuste das colunas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Salvar arquivo
        saveWorkbook(workbook, filePath);
    }

}