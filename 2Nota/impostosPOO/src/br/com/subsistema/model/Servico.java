package br.com.subsistema.model;

public class Servico extends Item{

    public Servico(){}

    @Override
    public String toString() {
        return "Servico{" +
                "codigo=" + getCodigo() +
                ", descricao='" + getDescricao() + '\'' +
                ", valor=" + getValor() +
                ", impostoCalculado=" + getImpostoCalculado() +
                ", total=" + getTotal() +
                '}';
    }
}
