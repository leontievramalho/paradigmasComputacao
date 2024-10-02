import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SemThreadsCalibracao {
    private Path path;
    private int resultSum;
    private int count;

    public SemThreadsCalibracao(Path path){
        this.path = path;
    }

    public void calibrate() throws IOException {
        List<String> calibrations = Files.readAllLines(path);

        resultSum = 0;
        count = 0;

        for (String line: calibrations) {
            resultSum += Calibracao.valorCalibracao(line);
            count++;
        }

        System.out.println("A soma dos valores é: " + resultSum);
        System.out.println("Total de linhas: " + count);


    }
}
