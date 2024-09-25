package br.com.subsistema.model;

public abstract class Item {
    private int codigo;
    private String descricao;
    private double valor;
    private double impostoCalculado;
    private double total;


    public Item(int codigo, String descricao, double valor, double impostoCalculado){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.impostoCalculado = impostoCalculado;
        this.total = valor + impostoCalculado;
    }
    public Item(){
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getImpostoCalculado() {
        return impostoCalculado;
    }

    public void setImpostoCalculado(double impostoCalculado) {
        this.impostoCalculado = impostoCalculado;
        this.total = this.valor + this.impostoCalculado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", impostoCalculado=" + impostoCalculado +
                ", total=" + total +
                '}';
    }
}
