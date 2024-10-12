import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class GeradorArquivoResultado implements Runnable{
    Map<String, Long> resultadosAgrupados;
    String pathArquivoDestino;
    Long somatorio;
    int tipoArquivo;

    public GeradorArquivoResultado(Map resultadosAgrupados, String pathArquivoDestino, int tipoArquivo){
        this.tipoArquivo = tipoArquivo;
        this.resultadosAgrupados = resultadosAgrupados;
        this.pathArquivoDestino = pathArquivoDestino;
        this.somatorio = 0L;
    }

    @Override
    public void run() {
        try {
            String cabecalho="";
            String textoResultado="";
            switch (tipoArquivo){
                case -1:
                    cabecalho = "DESPESAS";
                    textoResultado = "despesas";
                    break;
                case 0:
                    cabecalho = "PROVISÃO";
                    textoResultado = "provisões";
                    break;
                case 1:
                    cabecalho = "RECEITA";
                    textoResultado = "receitas";
                    break;
            }
            File novasDespesas = new File(pathArquivoDestino);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathArquivoDestino));;
            if (!novasDespesas.isFile()) {
                novasDespesas.createNewFile();
            }
            bufferedWriter.write("DATA,VALOR,"+cabecalho + '\n');
            Long somaTotal = 0L;
            NumberFormat numberFormat = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.ITALIAN));

            for (String data:resultadosAgrupados.keySet()) {
                somaTotal += resultadosAgrupados.get(data);
                bufferedWriter.write(data+','+ numberFormat.format(resultadosAgrupados.get(data))+','+tipoArquivo+'\n');
            }

            this.somatorio = somaTotal;
            bufferedWriter.write("Soma total de todas as " + textoResultado +" = " + numberFormat.format(somaTotal) +'\n');
            System.out.println("Soma total de todas as " + textoResultado +
                    " = " + numberFormat.format(somaTotal));
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getSomatorio() {
        return somatorio;
    }

    public void setSomatorio(Long somatorio) {
        this.somatorio = somatorio;
    }
}


