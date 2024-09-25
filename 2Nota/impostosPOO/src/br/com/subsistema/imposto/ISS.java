package br.com.subsistema.imposto;

import br.com.subsistema.model.Item;
import br.com.subsistema.model.Servico;

public class ISS implements Imposto{
    @Override
    public double calcular(Item item) {
        if(item instanceof Servico){
            return 0.22*item.getValor();
        }
        System.out.println("Imposto não disponível para não-serviços");
        return 0;
    }
}
