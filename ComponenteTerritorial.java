public abstract class ComponenteTerritorial {

    private String id;
    private String nome;
    private String regiao;
    private String status;

    public ComponenteTerritorial(String id, String nome, String regiao) {
        this.setId(id);
        this.setNome(nome);
        this.setRegiao(regiao);
        this.setStatus("ATIVO");
    }

    public abstract void verificarStatus();

    public void ativar() {
        this.setStatus("ATIVO");
        System.out.println("Componente " + this.id + " ativado.");
    }

    public void desativar() {
        this.setStatus("INATIVO");
        System.out.println("Componente " + this.id + " desativado.");
    }

    public boolean estaAtivo() {
        return this.status.equals("ATIVO");
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getRegiao() {
        return this.regiao;
    }

    public String getStatus() {
        return this.status;
    }

    private void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        } else {
            System.out.println("Erro de Validação: o id do componente é inválido!");
        }
    }

    private void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Erro de Validação: o nome do componente é inválido!");
        }
    }

    private void setRegiao(String regiao) {
        if (regiao != null && !regiao.trim().isEmpty()) {
            this.regiao = regiao;
        } else {
            System.out.println("Erro de Validação: a região do componente é inválida!");
        }
    }

    private void setStatus(String status) {
        if (status.equals("ATIVO") || status.equals("INATIVO")) {
            this.status = status;
        } else {
            System.out.println("Erro de Validação: status inválido!");
        }
    }
}