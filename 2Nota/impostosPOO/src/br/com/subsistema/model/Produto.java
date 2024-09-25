package br.com.subsistema.model;

public class Produto extends Item{
    private boolean industrial;

    public Produto(){

    }

    public boolean isIndustrial() {
        return industrial;
    }

    public void setIndustrial(boolean industrial) {
        this.industrial = industrial;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + getCodigo() +
                ", descricao='" + getDescricao() + '\'' +
                ", valor=" + getValor() +
                ", impostoCalculado=" + getImpostoCalculado() +
                ", total=" + getTotal() +
                ", industrial=" + isIndustrial() +
                '}';
    }
}
