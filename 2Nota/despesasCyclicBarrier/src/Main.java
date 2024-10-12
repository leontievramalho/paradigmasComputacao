import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

public class Main {
    final static String PATH_DESPESAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\despesas - despesas.csv";
    final static String PATH_PROVISAO = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\provisao - provisao.csv";
    final static String PATH_RECEITAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\receitas - receitas.csv";

    final static String PATH_DESPESAS_AGRUPADAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\despesasAgrupadas.csv";

    final static String PATH_PROVISOES_AGRUPADAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\provisoesAgrupadas.csv";

    final static String PATH_RECEITAS_AGRUPADAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\receitasAgrupadas.csv";

    final static String PATH_TESTE = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\teste.csv";

    final static String PATH_TESTE_AGRUPADAS = "C:\\Users\\leont\\Documents\\Faculdade Ciências da Computação\\" +
            "ParadigmasComputacao\\2Nota\\despesasCyclicBarrier\\src\\relatorios\\testeAgrupadas.csv";
    public static void main(String[] args) throws IOException {
        gerarThreadsAgrupador(PATH_DESPESAS, 3);
        gerarThreadsAgrupador(PATH_PROVISAO, 2);
        gerarThreadsAgrupador(PATH_RECEITAS, 5);
    }

    public static void gerarThreadsAgrupador(String path, int numThreads) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        bufferedReader.readLine();
        String linha = bufferedReader.readLine();
        int tipoArquivo = Integer.parseInt(linha.split(",", 3)[2]);
        String pathResultado ="";
        int numLinhas = 0;
        switch (tipoArquivo){
            case -1:
                pathResultado = PATH_DESPESAS_AGRUPADAS;
                numLinhas = 534;
                break;
            case 0:
                pathResultado = PATH_PROVISOES_AGRUPADAS;
                numLinhas = 90;
                break;
            case 1:
                pathResultado = PATH_RECEITAS_AGRUPADAS;
                numLinhas = 1429;
                break;
            case 2:
                pathResultado = PATH_TESTE_AGRUPADAS;
                numLinhas = 5;
                break;
        }
        Map<String, Long> juntarPorData = Collections.synchronizedMap(new HashMap<>());
        GeradorArquivoResultado resultado= new GeradorArquivoResultado(
                juntarPorData,
                pathResultado,
                tipoArquivo);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(numThreads, resultado);
        int linhasPorThread = (numLinhas/numThreads + 1);
        for(int i = 0; i< numThreads; i++){
            Thread thread = new Thread(new AgrupadorPorDataMap(path,
                    i*linhasPorThread+1,
                    (i+1)*linhasPorThread,
                    cyclicBarrier,
                    juntarPorData));
            thread.start();
        }
    }

    public static int contarDiferentesDatas(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        bufferedReader.readLine();
        int countUniqueDates = 0;
        String linha = bufferedReader.readLine();
        String data;
        String dataAnterior = "";

        while (linha != null) {
            data = linha.split(",", 3)[0];
            if (!data.equals(dataAnterior)) {
                countUniqueDates++;
                dataAnterior = data;
                System.out.println(dataAnterior);
            }
            linha = bufferedReader.readLine();
        }
        return countUniqueDates;

    }
}
