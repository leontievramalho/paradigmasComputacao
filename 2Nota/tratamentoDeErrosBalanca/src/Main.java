import enums.TipoBalanca;
import excecoes.SameCodeException;
import excecoes.SameElementOnListException;
import factory.BalancaFactory;
import interfaces.IBalanca;
import models.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        try {
            Produto produto1 = new Produto();
            produto1.setCodigo(276);
            produto1.setDescricao("QUEIJO GRUYERE KG");
            produto1.setTipo("9");
            produto1.setValor(21.99);

            Produto produto2 = new Produto();
            produto2.setCodigo(288);
            produto2.setDescricao("QUEIJO PROVOLETE KG");
            produto2.setTipo("9");
            produto2.setValor(12.29);
            BigDecimal valor2 = new BigDecimal("12.29");

            List<Produto> produtos = new ArrayList<>();
            if(produtos.contains(produto1)){
                throw new SameElementOnListException("O objeto produto1 já se encontra na lista");
            }else if(produtos.contains(produto2)){
                throw new SameElementOnListException("O objeto produto2 já se encontra na lista");
            }
            for(Produto produto : produtos){
                if(produto.getCodigo() == produto1.getCodigo() || produto.getCodigo() == produto2.getCodigo()){
                    throw new SameCodeException("Já existe um produto com o código que você tentou adicionar.");
                }
            }

            produtos.add(produto1);
            produtos.add(produto2);


            IBalanca balancaFilizola = BalancaFactory.getBalanca(TipoBalanca.FINIZOLA_SMART);
            balancaFilizola.exportar(produtos, "C:/Users/seunome/Downloads/teste");

            IBalanca balancaToledo = BalancaFactory.getBalanca(TipoBalanca.TOLEDO_MGV6);
            balancaToledo.exportar(produtos, "C:/Users/seunome/Downloads/teste");

            IBalanca balancaUrano = BalancaFactory.getBalanca(TipoBalanca.URANO_INTEGRA);
            balancaUrano.exportar(produtos, "C:/Users/seunome/Downloads/teste");

            System.out.println("Arquivos gerados com sucesso!");
        }catch (ClassCastException cce){
            System.err.println("Não é possível fazer o casting das classes");
        }catch (SameElementOnListException seole){
            System.err.println("Erro de repetição de objeto na lista: " + seole.getMessage());
        }catch (SameCodeException sce){
            System.err.println("Erro de parametro: " + sce.getMessage());
        }
    }
}

