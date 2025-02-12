package export_import;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Module_Inventario;
import model.Module_InventarioStock;
import notificacao.Notificacao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportToExcel {
    Notificacao notificacao = new Notificacao();

    // Método para abrir o FileChooser e obter o caminho do arquivo
    public String escolherCaminhoArquivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Arquivo Excel");

        // Define o filtro para arquivos Excel
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

        // Abre o diálogo para salvar o arquivo e retorna o arquivo selecionado
        File file = fileChooser.showSaveDialog(stage);
        return (file != null) ? file.getAbsolutePath() : null; // Retorna o caminho ou null se cancelado
    }

    // O método agora aceita uma lista de Module_Inventario como parâmetro
    public void exportarInventParaExcel(List<Module_Inventario> inventarioList, String filePath) {
        // Cria uma nova planilha
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Inventário");

        // Adiciona cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Cod_Dep");
        headerRow.createCell(1).setCellValue("Tipo de Equipamento");
        headerRow.createCell(2).setCellValue("Marca");
        headerRow.createCell(3).setCellValue("Modelo");
        headerRow.createCell(4).setCellValue("Número de Série");
        headerRow.createCell(5).setCellValue("Data de Entrada");
        headerRow.createCell(6).setCellValue("Data de Verificação");
        headerRow.createCell(7).setCellValue("Operador");
        headerRow.createCell(8).setCellValue("Função");
        headerRow.createCell(9).setCellValue("Localização");
        headerRow.createCell(10).setCellValue("Departamento");
        headerRow.createCell(11).setCellValue("Status");
        headerRow.createCell(12).setCellValue("Situação");
        headerRow.createCell(13).setCellValue("Observações");

        // Formato de data
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        // Adiciona os dados
        int rowNum = 1;
        for (Module_Inventario item : inventarioList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getModelo());
            row.createCell(4).setCellValue(item.getNum_serie());

            // Adiciona a data de entrada
            Cell dateEntradaCell = row.createCell(5);
            LocalDate dataEntrada = item.getDataEntradaServico(); // Supondo que este método retorne LocalDate
            if (dataEntrada != null) {
                dateEntradaCell.setCellValue(dataEntrada);
                dateEntradaCell.setCellStyle(dateCellStyle);
            } else {
                dateEntradaCell.setCellValue("Data não disponível");
            }

            // Adiciona a data de verificação
            Cell dateVerificacaoCell = row.createCell(6);
            LocalDate dataVerificacao = item.getUltimaVerificacao(); // Supondo que este método retorne LocalDate
            if (dataVerificacao != null) {
                dateVerificacaoCell.setCellValue(dataVerificacao);
                dateVerificacaoCell.setCellStyle(dateCellStyle); // Corrigido aqui
            } else {
                dateVerificacaoCell.setCellValue("Data não disponível");
            }

            row.createCell(7).setCellValue(item.getOperador());
            row.createCell(8).setCellValue(item.getFuncao());
            row.createCell(9).setCellValue(item.getLocalizacao());
            row.createCell(10).setCellValue(item.getDepartamento());
            row.createCell(11).setCellValue(item.getStatus());
            row.createCell(12).setCellValue(item.getSituacaoEquipamento());
            row.createCell(13).setCellValue(item.getObs());
        }

        // Salva o arquivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel salvo em: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            notificacao.showErrorAlert("Erro ao salvar o arquivo Excel.");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    // O método agora aceita uma lista de Module_InventarioStock como parâmetro
    public void exportarInventStockParaExcel(List<Module_InventarioStock> inventarioListStock, String filePath) {
        // Cria uma nova planilha
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Inventário Stock");

        // Adiciona cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Cod_Dep");
        headerRow.createCell(1).setCellValue("Tipo de Equipamento");
        headerRow.createCell(2).setCellValue("Marca");
        headerRow.createCell(3).setCellValue("Quantidade");
        headerRow.createCell(4).setCellValue("Data de Entrada");
        headerRow.createCell(5).setCellValue("Última Verificação");
        headerRow.createCell(6).setCellValue("Operador");
        headerRow.createCell(7).setCellValue("Função");
        headerRow.createCell(8).setCellValue("Localização / Sala");
        headerRow.createCell(9).setCellValue("Departamento");
        headerRow.createCell(10).setCellValue("Situação do Equipamento");
        headerRow.createCell(11).setCellValue("Observações");

        // Formato de data
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        // Adiciona os dados
        int rowNum = 1;
        for (Module_InventarioStock item : inventarioListStock) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getCodDep());
            row.createCell(1).setCellValue(item.getTipoEquipamento());
            row.createCell(2).setCellValue(item.getMarca());
            row.createCell(3).setCellValue(item.getQuantidade());

            // Adiciona a data de entrada
            Cell dateEntradaCell = row.createCell(4);
            LocalDate dataEntrada = item.getDataEntradaServico(); // Supondo que este método retorne LocalDate
            if (dataEntrada != null) {
                dateEntradaCell.setCellValue(dataEntrada);
                dateEntradaCell.setCellStyle(dateCellStyle);
            } else {
                dateEntradaCell.setCellValue("Data não disponível");
            }

            // Adiciona a data de verificação
            Cell dateVerificacaoCell = row.createCell(5);
            LocalDate dataVerificacao = item.getUltimaVerificacao(); // Supondo que este método retorne LocalDate
            if (dataVerificacao != null) {
                dateVerificacaoCell.setCellValue(dataVerificacao);
                dateVerificacaoCell.setCellStyle(dateCellStyle);
            } else {
                dateVerificacaoCell.setCellValue("Data não disponível");
            }

            row.createCell(6).setCellValue(item.getOperador());
            row.createCell(7).setCellValue(item.getFuncao());
            row.createCell(8).setCellValue(item.getLocalizacao());
            row.createCell(9).setCellValue(item.getDepartamento());
            row.createCell(10).setCellValue(item.getSituacaoEquipamento());
            row.createCell(11).setCellValue(item.getObs());
        }

        // Salva o arquivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Arquivo Excel salvo em: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Se você tiver uma classe de notificação, use-a aqui
            // notificacao.showErrorAlert("Erro ao salvar o arquivo Excel.");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}