package packt.database;


import packt.app.MainConfig.controlers.reports.TimeReport.MovimentacaoEquipamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class DB_Relatorios {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<MovimentacaoEquipamento> obterMovimentacoesPorPeriodo(String periodo, String dataInicio, String dataFim) {
        // Simulação de dados - na implementação real, isso viria do banco de dados
        List<MovimentacaoEquipamento> movimentacoes = new ArrayList<>();
        Random random = new Random();

        LocalDate inicio = dataInicio != null ? LocalDate.parse(dataInicio, DATE_FORMATTER) : LocalDate.now().minusDays(7);
        LocalDate fim = dataFim != null ? LocalDate.parse(dataFim, DATE_FORMATTER) : LocalDate.now();

        // Gerar dados aleatórios para demonstração
        for (LocalDate date = inicio; !date.isAfter(fim); date = date.plusDays(1)) {
            int qtdMovimentacoes = random.nextInt(5);

            for (int i = 0; i < qtdMovimentacoes; i++) {
                String tipo = random.nextBoolean() ? "ENTRADA" : "SAÍDA";
                String status = random.nextBoolean() ? "DISPONÍVEL" :
                        random.nextBoolean() ? "EM USO" : "MANUTENÇÃO";

                movimentacoes.add(new MovimentacaoEquipamento(
                        date.format(DATE_FORMATTER),
                        tipo,
                        "Equipamento " + (random.nextInt(50) + 1),
                        "Usuário " + (random.nextInt(20) + 1),
                        status
                ));
            }
        }

        return movimentacoes;
    }
}