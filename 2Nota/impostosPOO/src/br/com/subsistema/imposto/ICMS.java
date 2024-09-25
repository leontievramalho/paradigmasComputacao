package br.com.subsistema.imposto;

import br.com.subsistema.model.Item;
import br.com.subsistema.model.Produto;

public class ICMS implements Imposto{
    @Override
    public double calcular(Item item) {
        if(item instanceof Produto) {
            return 0.18 * item.getValor();
        }
        System.out.println("Imposto indisponivel para não-produtos");
        return 0;
    }
}
