import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaMonitoramento {

    private DadosTerritorio dadosTerritorio;
    private SensorChuva sensorChuva;
    private SensorNivelRio sensorNivelRio;
    private SensorUmidade sensorUmidade;
    private List<Sensor> sensores;
    private List<EstrategiaPrevisao> estrategias;
    private Scanner entrada;

    public SistemaMonitoramento() {
        this.dadosTerritorio = new DadosTerritorio("Vila Ribeirinha", 12500, "CIVIL2026");

        this.sensorChuva = new SensorChuva("SEN-CHU-01", "Pluviômetro da escola", "Vila Ribeirinha - parte alta");
        this.sensorNivelRio = new SensorNivelRio("SEN-RIO-01", "Régua do córrego", "Vila Ribeirinha - ponte");
        this.sensorUmidade = new SensorUmidade("SEN-UMI-01", "Sonda de umidade", "Vila Ribeirinha - área baixa");

        this.sensores = new ArrayList<>();
        this.sensores.add(this.sensorChuva);
        this.sensores.add(this.sensorNivelRio);
        this.sensores.add(this.sensorUmidade);

        this.estrategias = new ArrayList<>();
        this.estrategias.add(new PrevisaoMediaMovel(5));
        this.estrategias.add(new PrevisaoComparacao(3));

        this.entrada = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SistemaMonitoramento sistema = new SistemaMonitoramento();
        sistema.iniciar();
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            this.exibirMenu();
            opcao = this.entrada.nextInt();
            this.entrada.nextLine();

            if (opcao == 1) {
                this.verificarSensores();
            } else if (opcao == 2) {
                this.simularLeituras();
            } else if (opcao == 3) {
                this.rodarPrevisoes();
            } else if (opcao == 4) {
                this.gerenciarDadosTerritorio();
            } else if (opcao == 5) {
                this.exibirStatusCompleto();
            } else if (opcao == 0) {
                System.out.println("Encerrando o monitoramento. Até logo!");
            } else {
                System.out.println("Erro: opção inválida.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n========== TERRITORY PREDICTIVE INSIGHTS ==========");
        System.out.println("Território: " + this.dadosTerritorio.getRegiao());
        System.out.println("1 - Verificar sensores");
        System.out.println("2 - Simular próximas leituras");
        System.out.println("3 - Rodar previsões e alertas");
        System.out.println("4 - Gerenciar dados do território");
        System.out.println("5 - Exibir status completo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void verificarSensores() {
        System.out.println("\n=== STATUS DOS SENSORES ===");
        this.sensorChuva.verificarStatus();
        this.sensorNivelRio.verificarStatus();
        this.sensorUmidade.verificarStatus();
    }

    private void simularLeituras() {
        System.out.print("Quantas rodadas de leitura deseja simular (1 a 24)? ");
        int quantidade = this.entrada.nextInt();
        this.entrada.nextLine();

        if (quantidade < 1 || quantidade > 24) {
            System.out.println("Erro: a quantidade deve ser de 1 a 24.");
            return;
        }

        int rodada = 1;
        while (rodada <= quantidade) {
            System.out.println("\nRodada " + rodada + ":");
            for (Sensor sensor : this.sensores) {
                double valor = sensor.lerValor();
                System.out.println("  " + sensor.getTipo() + ": " + valor + " " + sensor.getUnidade());
            }
            rodada = rodada + 1;
        }
    }

    private void rodarPrevisoes() {
        System.out.println("\n=== PREVISÕES E ALERTAS ===");
        this.verificarAlerta(this.sensorChuva, this.dadosTerritorio.getLimiteChuva());
        this.verificarAlerta(this.sensorNivelRio, this.dadosTerritorio.getLimiteNivelRio());
        this.verificarAlerta(this.sensorUmidade, this.dadosTerritorio.getLimiteUmidade());
    }

    private void verificarAlerta(Sensor sensor, double limite) {
        System.out.println("\n--- " + sensor.getTipo() + " (limite: " + limite + " " + sensor.getUnidade() + ") ---");

        if (!sensor.estaFuncionando()) {
            System.out.println("Sensor inativo: sem dados novos, o sistema não faz previsão.");
            return;
        }

        for (EstrategiaPrevisao estrategia : this.estrategias) {
            if (!estrategia.temLeiturasSuficientes(sensor.getLeituras())) {
                System.out.println(estrategia.getNome() + ": dados insuficientes ("
                        + sensor.getLeituras().size() + " de " + estrategia.getMinimoLeituras()
                        + " leituras). O sistema não faz previsão.");
            } else {
                double previsto = estrategia.prever(sensor.getLeituras());
                String nivel = this.classificarNivel(previsto, limite);
                System.out.println(estrategia.getNome() + ": previsão de " + previsto + " "
                        + sensor.getUnidade() + " -> " + nivel);
                if (!nivel.equals("NORMAL")) {
                    System.out.println("   [" + nivel + "] Motivo: pela " + estrategia.getNome()
                            + ", o sensor de " + sensor.getTipo() + " pode chegar a " + previsto + " "
                            + sensor.getUnidade() + ", e o limite do território é " + limite + " "
                            + sensor.getUnidade() + ".");
                }
            }
        }
    }

    private String classificarNivel(double valorPrevisto, double limite) {
        if (valorPrevisto > limite * 1.3) {
            return "CRÍTICO";
        }
        if (valorPrevisto > limite) {
            return "ALERTA";
        }
        if (valorPrevisto >= limite * 0.8) {
            return "ATENÇÃO";
        }
        return "NORMAL";
    }

    private void gerenciarDadosTerritorio() {
        System.out.println("\n=== GERENCIAR DADOS DO TERRITÓRIO ===");
        System.out.println("1 - Atualizar região");
        System.out.println("2 - Atualizar população");
        System.out.println("3 - Alterar limite de chuva");
        System.out.println("4 - Alterar limite do nível do rio");
        System.out.println("5 - Alterar limite de umidade");
        System.out.print("Escolha: ");
        int opcao = this.entrada.nextInt();
        this.entrada.nextLine();

        if (opcao == 1) {
            System.out.print("Nova região: ");
            String novaRegiao = this.entrada.nextLine();
            this.dadosTerritorio.atualizarRegiao(novaRegiao);
        } else if (opcao == 2) {
            System.out.print("Nova população: ");
            int novaPopulacao = this.entrada.nextInt();
            this.entrada.nextLine();
            this.dadosTerritorio.atualizarPopulacao(novaPopulacao);
        } else if (opcao >= 3 && opcao <= 5) {
            System.out.print("Código de acesso: ");
            String codigo = this.entrada.nextLine();
            System.out.print("Novo limite: ");
            double novoLimite = this.entrada.nextDouble();
            this.entrada.nextLine();
            if (opcao == 3) {
                this.dadosTerritorio.alterarLimiteChuva(codigo, novoLimite);
            } else if (opcao == 4) {
                this.dadosTerritorio.alterarLimiteNivelRio(codigo, novoLimite);
            } else {
                this.dadosTerritorio.alterarLimiteUmidade(codigo, novoLimite);
            }
        } else {
            System.out.println("Erro: opção inválida.");
        }
    }

    private void exibirStatusCompleto() {
        System.out.println("\n=== STATUS COMPLETO ===");
        System.out.println("Região: " + this.dadosTerritorio.getRegiao());
        System.out.println("População: " + this.dadosTerritorio.getPopulacao() + " habitantes");
        System.out.println("Limite de chuva: " + this.dadosTerritorio.getLimiteChuva() + " mm/h");
        System.out.println("Limite do nível do rio: " + this.dadosTerritorio.getLimiteNivelRio() + " m");
        System.out.println("Limite de umidade: " + this.dadosTerritorio.getLimiteUmidade() + " %");
        this.verificarSensores();
        System.out.println("\nEstratégias de previsão:");
        for (EstrategiaPrevisao estrategia : this.estrategias) {
            System.out.println("- " + estrategia.descrever());
        }
    }
}