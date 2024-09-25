import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GeradorEtiqueta {

    public static String gerarEtiqueta(Produto produto){
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        return "^XA\n" +
                "^CF0,60\n" +
                "^FO50,50^FD" + produto.getDescricao() + "^FS\n" +
                "^CFA,45\n" +
                "^FO50,200^FDLata          "+ "R$ " + decimalFormat.format(produto.getPrecoLata()) + "^FS\n" +
                "^FO50,280^FDCaixa         "+ "R$ " + decimalFormat.format(produto.getPrecoCaixa())  + "^FS\n" +
                "^BY5,2,270\n" +
                "^FO100,450^BC^FD" + produto.getCodigoBarras() + "^FS\n"+
                "^XZ";
    }
}
