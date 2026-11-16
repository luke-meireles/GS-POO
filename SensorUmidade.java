import java.util.ArrayList;
import java.util.List;

public class SensorUmidade extends ComponenteTerritorial implements Sensor {

    private List<Double> leituras;

    public SensorUmidade(String id, String nome, String regiao) {
        super(id, nome, regiao);
        this.leituras = new ArrayList<>();
    }

    @Override
    public double lerValor() {
        if (!this.estaAtivo()) {
            System.out.println("Sensor " + this.getId() + " está inativo. Leitura não realizada.");
            return 0;
        }
        double umidadeMinima = 30;
        double umidadeMaxima = 100;
        double valor = umidadeMinima + Math.random() * (umidadeMaxima - umidadeMinima);
        this.leituras.add(valor);
        return valor;
    }

    @Override
    public boolean estaFuncionando() {
        return this.estaAtivo();
    }

    @Override
    public String getTipo() {
        return "Umidade do Solo";
    }

    @Override
    public String getUnidade() {
        return "%";
    }

    @Override
    public boolean passouDoLimite(double limite) {
        if (this.leituras.size() == 0) {
            return false;
        }
        double ultimaLeitura = this.leituras.get(this.leituras.size() - 1);
        return ultimaLeitura > limite;
    }

    @Override
    public List<Double> getLeituras() {
        return this.leituras;
    }

    @Override
    public void verificarStatus() {
        if (this.leituras.size() == 0) {
            System.out.println(this.getId() + " | " + this.getTipo() + " | " + this.getStatus() + " | nenhuma leitura ainda");
            return;
        }
        double ultimaLeitura = this.leituras.get(this.leituras.size() - 1);
        System.out.println(this.getId() + " | " + this.getTipo() + " | " + this.getStatus()
                + " | última leitura: " + ultimaLeitura + " " + this.getUnidade());
    }
}