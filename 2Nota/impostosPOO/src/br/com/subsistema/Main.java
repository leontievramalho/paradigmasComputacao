package br.com.subsistema;

import br.com.subsistema.factory.ImpostoFactory;
import br.com.subsistema.factory.TipoImposto;
import br.com.subsistema.imposto.Imposto;
import br.com.subsistema.imposto.PIS;
import br.com.subsistema.model.Item;
import br.com.subsistema.model.Produto;
import br.com.subsistema.model.Servico;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Imposto icms = ImpostoFactory.getImposto(TipoImposto.TIPOICMS);
        Produto milho = new Produto();
        milho.setCodigo(1);
        milho.setDescricao("Milho");
        milho.setValor(100);
        milho.setIndustrial(false);
        milho.setImpostoCalculado(icms.calcular(milho));

        Imposto ipi = ImpostoFactory.getImposto(TipoImposto.TIPOIPI);
        Produto camisa = new Produto();
        camisa.setCodigo(2);
        camisa.setDescricao("Camisa");
        camisa.setValor(45);
        camisa.setIndustrial(true);
        camisa.setImpostoCalculado(ipi.calcular(camisa));

        Imposto pis = ImpostoFactory.getImposto(TipoImposto.TIPOPIS);
        Produto sapato = new Produto();
        sapato.setCodigo(3);
        sapato.setDescricao("Sapato");
        sapato.setValor(250);
        sapato.setIndustrial(true);
        sapato.setImpostoCalculado(pis.calcular(sapato));

        Imposto iss = ImpostoFactory.getImposto(TipoImposto.TIPOISS);
        Servico faxina = new Servico();
        faxina.setCodigo(4);
        faxina.setDescricao("Faxina");
        faxina.setValor(80);
        faxina.setImpostoCalculado(iss.calcular(faxina));

        List<Item> itens = new ArrayList<>();
        itens.add(milho);
        itens.add(camisa);
        itens.add(sapato);
        itens.add(faxina);

        printItens(itens);
    }

    public static void printItens(List<Item> itens){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        float totalSemImpostos = 0;
        float totalImpostosProdutos = 0;
        float totalImpostosServicos = 0;
        float totalComImpostos = 0;
        List<Produto> produtos = new ArrayList<>();
        List<Servico> servicos = new ArrayList<>();
        for(int i = 0; i<itens.size(); i++){
            Item itemAtual = itens.get(i);
            totalSemImpostos += itemAtual.getValor();
            totalComImpostos += itemAtual.getTotal();
            if(itemAtual instanceof Produto){
                produtos.add( (Produto) itemAtual);
                totalImpostosProdutos += itemAtual.getImpostoCalculado();
            }else if(itemAtual instanceof Servico){
                servicos.add((Servico) itemAtual);
                totalImpostosServicos += itemAtual.getImpostoCalculado();
            }
        }
        System.out.println("Itens: ");
        System.out.println("Produtos: ");
        for (int i = 0; i < produtos.size(); i++){
            System.out.println(produtos.get(i));
        }
        System.out.println("Impostos dos produtos: R$ " + decimalFormat.format(totalImpostosProdutos));
        System.out.println("Serviços: ");
        for (int i = 0; i < servicos.size(); i++){
            System.out.println(servicos.get(i));
        }
        System.out.println("Impostos dos serviços: R$ " + decimalFormat.format(totalImpostosServicos));
        System.out.println("Total dos itens antes dos impostos: R$ " + decimalFormat.format(totalSemImpostos));
        System.out.println("Valor final após impostos: R$ " + decimalFormat.format(totalComImpostos));
    }
}
