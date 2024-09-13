import java.util.*;

public class Main {
    public static void main(String[] agrs){
        List<HashMap<String, Object>> supermarket = new ArrayList<>();
        List<HashMap<String, Object>> cart = new ArrayList<>();

        HashMap<String, Object> finasterida = registerItem("01", "finasterida", 20.75f);
        HashMap<String, Object> garrafa = registerItem("02", "garrafa", 31.2f);
        HashMap<String, Object> escova = registerItem("03", "escova", 8.7f);
        HashMap<String, Object> sabonete = registerItem("04", "sabonete", 15.0f);
        HashMap<String, Object> papelHigienico = registerItem("05", "papel higiênico", 20.0f);

        supermarket.add(finasterida);
        supermarket.add(garrafa);
        supermarket.add(escova);
        supermarket.add(sabonete);
        supermarket.add(papelHigienico);

        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        while(opcao != 0){
            System.out.println("Bem-vindo ao seu carrinho, o que deseja fazer? digite 0 para sair");
            showMenu();
            System.out.println("Sua opcao: ");
            opcao = scanner.nextInt();
            if(opcao == 0){
                break;
            }
            switch(opcao){
                case 1:
                    chooseItemFromMarket(cart, supermarket);
                    break;
                case 2:
                    addAdditionOnCart(cart);
                    break;
                case 3:
                    addDiscountOnCart(cart);
                    break;
                case 4:
                    addGeralAdditionOnCart(cart);
                    break;
                case 5:
                    addGeralDiscountOnCart(cart);
                    break;
                case 6:
                    finalizeSale(cart);
                    break;
                default:
                    System.out.println("Opção inválida!");

            }
        }


        /*
        addItem(cart,finasterida);
        addItem(cart, garrafa);
        System.out.println(cart);
        addDiscount(cart, "01", 2.50f);
        System.out.println(cart);
        addAddition(cart, "01", 7.50f);
        System.out.println(cart);
        System.out.println(totalDiscount(cart));
        System.out.println("Acrescimo de 30 no total");
        geralAddition(cart, 30f);
        System.out.println(cart);
        System.out.println(totalAddition(cart));
        finalizeSale(cart);

         */
    }

    static void showMenu(){
        String menu[] = {
            "Inserir item ao carrinho",
            "Acréscimo de item",
            "Desconto de item",
            "Acréscimo total",
            "Desconto total",
            "Finalizar venda"
        };

        for(int i = 0; i < menu.length; i++){
            System.out.println((i+1) + " - " + menu[i]);
        }
    }

    static HashMap registerItem(String code, String description, Float total){
        HashMap<String, Object> item = HashMap.newHashMap(5);
        item.put("code", code);
        item.put("description", description);
        item.put("addition", 0f);
        item.put("discount", 0f);
        item.put("total", total);
        return item;
    }
    static void addItem(List<HashMap<String, Object>> cart, HashMap<String, Object> item){
        cart.add(item);
    }

    static void chooseItemFromMarket(List<HashMap<String, Object>> cart, List<HashMap<String, Object>> supermarket){
        Scanner scanner = new Scanner(System.in);
        int escolha = -1;
        while(escolha < 0 || escolha > supermarket.size()){
            System.out.println("Qual item deseja adicionar ao carrinho? digite 0 para sair ");
            for(int i = 0; i<supermarket.size(); i++){
                System.out.println(supermarket.get(i).get("code") + " - " + supermarket.get(i));
            }
            escolha = scanner.nextInt();
            if(escolha >= 1 && escolha <= supermarket.size()){
                addItem(cart, supermarket.get(escolha-1));
            }else if(escolha == 0){
                break;
            }else{
                System.out.println("Opção indisponivel");
            }
        }
    }

    static HashMap<String, Object> chooseItemFromCart(List<HashMap<String, Object>> cart){
        Scanner scanner = new Scanner(System.in);
        int escolha = -1;
        HashMap<String, Object> itemEscolhido = null;
        while(escolha < 0 || itemEscolhido == null){
            System.out.println("Selecione o item do carrinho, digite 0 para sair: ");
            for(int i = 0; i<cart.size(); i++){
                System.out.println(cart.get(i).get("code") + " - " + cart.get(i));
            }
            escolha = scanner.nextInt();
            if(escolha >= 1){
                itemEscolhido = findByCode(cart, "0"+escolha);
            }else if(escolha == 0){
                break;
            }
            if(itemEscolhido == null){
                System.out.println("Opção indisponivel");
            }
        }
        return itemEscolhido;
    }

    static HashMap<String, Object> findByCode(List<HashMap<String, Object>> cart, String code){
        for(int i = 0; i < cart.size(); i++) {
            if (cart.get(i).get("code").equals(code)) {
                return cart.get(i);
            }
        }
        return null;
    }

    static void addDiscount(List<HashMap<String, Object>> cart, String code, Float price){
        HashMap<String, Object> item = findByCode(cart, code);
        Float newTotal = (Float) item.get("total") - price;
        Float newDiscount = (Float) item.get("discount") + price;

        if(newTotal < 0f){
            System.out.println("Desconto muito alto, valor do produto reduzido para 0");
            newTotal = 0f;
        }
        item.replace("discount", newDiscount);
        item.replace("total", newTotal);
        System.out.println("Disconto feito com sucesso!");
    }

    static void addAdditionOnCart(List<HashMap<String, Object>> cart){
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Object> item = chooseItemFromCart(cart);
        System.out.println("Qual valor do acréscimo? R$ ");
        Float price = scanner.nextFloat();
        addAddition(cart, (String) item.get("code"), price);
    }

    static void addDiscountOnCart(List<HashMap<String, Object>> cart){
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Object> item = chooseItemFromCart(cart);
        System.out.println("Qual valor do disconto? R$ ");
        Float price = scanner.nextFloat();
        addDiscount(cart, (String) item.get("code"), price);
    }

    static void addAddition(List<HashMap<String, Object>> cart, String code, Float price){
        HashMap<String, Object> item = findByCode(cart, code);
        Float newTotal = (Float) item.get("total") + price;
        Float newAddition = (Float) item.get("addition") + price;
        item.replace("addition", newAddition);
        item.replace("total", newTotal);
        System.out.println("Acrescimo adicionado com sucesso!");
    }

    static Float totalAddition(List<HashMap<String, Object>> cart){
        Float additions = 0f;
        for(int i = 0; i<cart.size(); i++){
            additions += (Float) cart.get(i).get("addition");
        }
        return additions;
    }

    static Float totalDiscount(List<HashMap<String, Object>> cart){
        Float discounts = 0f;
        for(int i = 0; i<cart.size(); i++){
            discounts += (Float) cart.get(i).get("discount");
        }
        return discounts;
    }

    static void geralAddition(List<HashMap<String, Object>> cart, Float price){
        int qtdItems = cart.size();
        Float addition = price/qtdItems;
        for(int i = 0; i<qtdItems; i++){
            addAddition(cart, (String) cart.get(i).get("code"), addition);
        }
    }

    static void addGeralAdditionOnCart(List<HashMap<String, Object>> cart){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual valor do acréscimo? R$ ");
        Float price = scanner.nextFloat();
        geralAddition(cart, price);
        System.out.println("Acrescimo geral adicionado.");
    }

    static void addGeralDiscountOnCart(List<HashMap<String, Object>> cart){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual valor do disconto? R$ ");
        Float price = scanner.nextFloat();
        geralDiscount(cart, price);
        System.out.println("Disconto geral aplicado.");
    }

    static void geralDiscount(List<HashMap<String, Object>> cart, Float price){
        int qtdItems = cart.size();
        Float discount = price/qtdItems;
        for(int i = 0; i<qtdItems; i++){
            addDiscount(cart, (String) cart.get(i).get("code"), discount);
        }
    }

    static void finalizeSale(List<HashMap<String, Object>> cart){
        Float valorFinal = 0f;
        System.out.println("Itens no carrinho: ");
        for(int i = 0; i< cart.size(); i++){
            System.out.println(cart.get(i));
            valorFinal += (Float) cart.get(i).get("total");
        }
        Float discontoTotal = totalDiscount(cart);
        Float acrescimoTotal = totalAddition(cart);
        System.out.println("Valor total sem descontos ou acréscimos: R$ " + (valorFinal + discontoTotal - acrescimoTotal));
        System.out.println("Total de acresimos: R$" + acrescimoTotal);
        System.out.println("Total de descontos: R$" + discontoTotal);
        System.out.println("Valor final: R$ " + valorFinal);
        cart.clear();
    }
}
