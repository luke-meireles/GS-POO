import java.util.List;

public class PrevisaoComparacao extends EstrategiaPrevisao {

    private int tamanhoPeriodo;

    public PrevisaoComparacao(int tamanhoPeriodo) {
        super("Comparação de Períodos", tamanhoPeriodo * 2);
        this.setTamanhoPeriodo(tamanhoPeriodo);
    }

    @Override
    public double prever(List<Double> leituras) {
        int fimAtual = leituras.size();
        int inicioAtual = fimAtual - this.tamanhoPeriodo;
        int inicioAnterior = inicioAtual - this.tamanhoPeriodo;

        double mediaAtual = this.calcularMedia(leituras, inicioAtual, fimAtual);
        double mediaAnterior = this.calcularMedia(leituras, inicioAnterior, inicioAtual);
        double variacao = mediaAtual - mediaAnterior;
        double previsao = mediaAtual + variacao;

        if (previsao < 0) {
            return 0;
        }
        return previsao;
    }

    @Override
    public String descrever() {
        return super.descrever() + " | período: " + this.tamanhoPeriodo + " leituras";
    }

    public int getTamanhoPeriodo() {
        return this.tamanhoPeriodo;
    }

    private void setTamanhoPeriodo(int tamanhoPeriodo) {
        if (tamanhoPeriodo > 0) {
            this.tamanhoPeriodo = tamanhoPeriodo;
        } else {
            System.out.println("Erro de Validação: o tamanho do período deve ser maior que zero!");
        }
    }
}