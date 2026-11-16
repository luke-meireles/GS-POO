import java.util.List;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {

    private int tamanhoJanela;

    public PrevisaoMediaMovel(int tamanhoJanela) {
        super("Média Móvel", tamanhoJanela);
        this.setTamanhoJanela(tamanhoJanela);
    }

    @Override
    public double prever(List<Double> leituras) {
        int fim = leituras.size();
        int inicio = fim - this.tamanhoJanela;
        return this.calcularMedia(leituras, inicio, fim);
    }

    @Override
    public String descrever() {
        return super.descrever() + " | janela: últimas " + this.tamanhoJanela + " leituras";
    }

    public int getTamanhoJanela() {
        return this.tamanhoJanela;
    }

    private void setTamanhoJanela(int tamanhoJanela) {
        if (tamanhoJanela > 0) {
            this.tamanhoJanela = tamanhoJanela;
        } else {
            System.out.println("Erro de Validação: o tamanho da janela deve ser maior que zero!");
        }
    }
}