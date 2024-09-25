package br.com.subsistema.imposto;

import br.com.subsistema.model.Item;
import br.com.subsistema.model.Produto;
import br.com.subsistema.model.Servico;

public class PIS implements Imposto{

    @Override
    public double calcular(Item item) {
        if(item instanceof Produto){
            return 0.0065*item.getValor();
        }
        System.out.println("Imposto indisponivel para não-produtos");
        return 0;
    }
}
