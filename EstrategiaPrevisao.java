import java.util.List;

public abstract class EstrategiaPrevisao {

    private String nome;
    private int minimoLeituras;

    public EstrategiaPrevisao(String nome, int minimoLeituras) {
        this.setNome(nome);
        this.setMinimoLeituras(minimoLeituras);
    }

    public abstract double prever(List<Double> leituras);

    public boolean temLeiturasSuficientes(List<Double> leituras) {
        return leituras.size() >= this.minimoLeituras;
    }

    public double calcularMedia(List<Double> leituras, int inicio, int fim) {
        double soma = 0;
        int posicao = inicio;
        while (posicao < fim) {
            soma = soma + leituras.get(posicao);
            posicao = posicao + 1;
        }
        return soma / (fim - inicio);
    }

    public String descrever() {
        return this.nome + " (mínimo de " + this.minimoLeituras + " leituras)";
    }

    public String getNome() {
        return this.nome;
    }

    public int getMinimoLeituras() {
        return this.minimoLeituras;
    }

    private void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Erro de Validação: o nome da estratégia é inválido!");
        }
    }

    private void setMinimoLeituras(int minimoLeituras) {
        if (minimoLeituras > 0) {
            this.minimoLeituras = minimoLeituras;
        } else {
            System.out.println("Erro de Validação: o mínimo de leituras deve ser maior que zero!");
        }
    }
}