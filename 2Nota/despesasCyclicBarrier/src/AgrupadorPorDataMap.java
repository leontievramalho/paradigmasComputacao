import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AgrupadorPorDataMap implements Runnable {
    private String path;
    private Map<String, Long> dadosAgrupados;
    private CyclicBarrier cyclicBarrier;
    private int linhaInicial;
    private int linhaFinal;

    public AgrupadorPorDataMap(String path,
                               int linhaInicial,
                               int linhaFinal,
                               CyclicBarrier cyclicBarrier,
                               Map dadosAgrupados) {
        this.dadosAgrupados = dadosAgrupados;
        this.path = path;
        this.cyclicBarrier = cyclicBarrier;
        if (linhaInicial < 0) {
            this.linhaInicial = 0;
        } else {
            this.linhaInicial = linhaInicial;
        }
        this.linhaFinal = linhaFinal;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            int countLinhas = 1;
            Long somaDataAnterior = 0L;
            String dataAnterior = "";
            String linha = "";

            for(int i = 1; i<linhaInicial; i++){
                bufferedReader.readLine();
            }

            boolean ultimaDataIgualDataAnterior = false;

            while (countLinhas-1 <= (linhaFinal-linhaInicial)) {
                linha = bufferedReader.readLine();
                countLinhas++;
                if (linha == null) {
                    break;
                }
                if (linha.contains("DATA")) {
                    continue;
                }
                String[] colunas = linha.split(",", 3);
                String data = colunas[0];
                long valor = Long.parseLong(colunas[1].replace(".", ""));
                if (somaDataAnterior == 0L || data.equals(dataAnterior)) {
                    dataAnterior = data;
                    somaDataAnterior += valor;
                    ultimaDataIgualDataAnterior = true;
                } else {
                    ultimaDataIgualDataAnterior = false;
                    if (dadosAgrupados.containsKey(dataAnterior)) {
                        somaDataAnterior += dadosAgrupados.get(dataAnterior);
                    }
                    dadosAgrupados.put(dataAnterior, somaDataAnterior);
                    dataAnterior = data;
                    somaDataAnterior = valor;
                }
            }
            if(!ultimaDataIgualDataAnterior && linha!=null){
                String[] colunas = linha.split(",", 3);
                String data = colunas[0];
                long valor = Long.parseLong(colunas[1].replace(".", ""));
                dataAnterior = data;
                somaDataAnterior = valor;
            }
            if(dadosAgrupados.containsKey(dataAnterior)){
                somaDataAnterior += dadosAgrupados.get(dataAnterior);
            }
            dadosAgrupados.put(dataAnterior, somaDataAnterior);

            bufferedReader.close();
            cyclicBarrier.await();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Long> getDadosAgrupados() {
        return dadosAgrupados;
    }
}
