package br.com.subsistema.imposto;

import br.com.subsistema.model.Item;
import br.com.subsistema.model.Produto;

public class IPI implements Imposto{
    @Override
    public double calcular(Item item) {
        if(item instanceof Produto) {
            Produto produto = (Produto) item;
            if (produto.isIndustrial()) {
                return 0.12 * produto.getValor();
            }
            return 0;
        }
        System.out.println("Imposto indisponivel para não-produtos");
        return 0;
    }
}
