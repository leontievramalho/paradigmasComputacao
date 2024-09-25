public class Main {
    public static void main(String[] args){
        Produto cerveja = new Produto("CERVEJA LAGER HEINEKEN", 6.0, 35.0, "78936683");
        Produto toddynho = new Produto("ACHOCOLATADO TODDYNHO", 2.5, 20.0, "29382983");
        String etiqueta = GeradorEtiqueta.gerarEtiqueta(toddynho);
        System.out.println(etiqueta);
    }
}
