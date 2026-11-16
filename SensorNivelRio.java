import java.util.ArrayList;
import java.util.List;

public class SensorNivelRio extends ComponenteTerritorial implements Sensor {

    private List<Double> leituras;

    public SensorNivelRio(String id, String nome, String regiao) {
        super(id, nome, regiao);
        this.leituras = new ArrayList<>();
    }

    @Override
    public double lerValor() {
        if (!this.estaAtivo()) {
            System.out.println("Sensor " + this.getId() + " está inativo. Leitura não realizada.");
            return 0;
        }
        double nivelMinimo = 0.5;
        double nivelMaximo = 5.0;
        double valor = nivelMinimo + Math.random() * (nivelMaximo - nivelMinimo);
        this.leituras.add(valor);
        return valor;
    }

    @Override
    public boolean estaFuncionando() {
        return this.estaAtivo();
    }

    @Override
    public String getTipo() {
        return "Nível do Rio";
    }

    @Override
    public String getUnidade() {
        return "m";
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