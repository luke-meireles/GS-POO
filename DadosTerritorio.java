public class DadosTerritorio {

    private String regiao;
    private int populacao;
    private String codigoAcesso;
    private double limiteChuva;
    private double limiteNivelRio;
    private double limiteUmidade;

    public DadosTerritorio(String regiao, int populacao, String codigoAcesso) {
        this.setRegiao(regiao);
        this.setPopulacao(populacao);
        this.setCodigoAcesso(codigoAcesso);
        this.setLimiteChuva(30);
        this.setLimiteNivelRio(3.5);
        this.setLimiteUmidade(85);
    }

    public void atualizarRegiao(String novaRegiao) {
        this.setRegiao(novaRegiao);
        System.out.println("Região atual: " + this.regiao);
    }

    public void atualizarPopulacao(int novaPopulacao) {
        this.setPopulacao(novaPopulacao);
        System.out.println("População atual: " + this.populacao + " habitantes");
    }

    public void alterarLimiteChuva(String codigoInformado, double novoLimite) {
        if (!this.validarCodigo(codigoInformado)) {
            System.out.println("Erro: código de acesso incorreto. O limite não foi alterado.");
            return;
        }
        this.setLimiteChuva(novoLimite);
        System.out.println("Limite de chuva atual: " + this.limiteChuva + " mm/h");
    }

    public void alterarLimiteNivelRio(String codigoInformado, double novoLimite) {
        if (!this.validarCodigo(codigoInformado)) {
            System.out.println("Erro: código de acesso incorreto. O limite não foi alterado.");
            return;
        }
        this.setLimiteNivelRio(novoLimite);
        System.out.println("Limite do nível do rio atual: " + this.limiteNivelRio + " m");
    }

    public void alterarLimiteUmidade(String codigoInformado, double novoLimite) {
        if (!this.validarCodigo(codigoInformado)) {
            System.out.println("Erro: código de acesso incorreto. O limite não foi alterado.");
            return;
        }
        this.setLimiteUmidade(novoLimite);
        System.out.println("Limite de umidade atual: " + this.limiteUmidade + " %");
    }

    public String getRegiao() {
        return this.regiao;
    }

    public int getPopulacao() {
        return this.populacao;
    }

    public double getLimiteChuva() {
        return this.limiteChuva;
    }

    public double getLimiteNivelRio() {
        return this.limiteNivelRio;
    }

    public double getLimiteUmidade() {
        return this.limiteUmidade;
    }

    private boolean validarCodigo(String codigoInformado) {
    return this.codigoAcesso != null && this.codigoAcesso.equals(codigoInformado);
    }

    private void setRegiao(String regiao) {
        if (regiao != null && !regiao.trim().isEmpty()) {
            this.regiao = regiao;
        } else {
            System.out.println("Erro de Validação: a região informada é inválida!");
        }
    }

    private void setPopulacao(int populacao) {
        if (populacao > 0) {
            this.populacao = populacao;
        } else {
            System.out.println("Erro de Validação: a população deve ser maior que zero!");
        }
    }

    private void setCodigoAcesso(String codigoAcesso) {
        if (codigoAcesso != null && !codigoAcesso.trim().isEmpty()) {
            this.codigoAcesso = codigoAcesso;
        } else {
            System.out.println("Erro de Validação: o código de acesso é inválido!");
        }
    }

    private void setLimiteChuva(double limiteChuva) {
        if (limiteChuva >= 5 && limiteChuva <= 100) {
            this.limiteChuva = limiteChuva;
        } else {
            System.out.println("Erro de Validação: o limite de chuva deve ficar entre 5 e 100 mm/h!");
        }
    }

    private void setLimiteNivelRio(double limiteNivelRio) {
        if (limiteNivelRio >= 0.5 && limiteNivelRio <= 7.5) {
            this.limiteNivelRio = limiteNivelRio;
        } else {
            System.out.println("Erro de Validação: o limite do nível do rio deve ficar entre 0.5 e 7.5 m!");
        }
    }

    private void setLimiteUmidade(double limiteUmidade) {
        if (limiteUmidade >= 30 && limiteUmidade <= 100) {
            this.limiteUmidade = limiteUmidade;
        } else {
            System.out.println("Erro de Validação: o limite de umidade deve ficar entre 30 e 100 %!");
        }
    }
}