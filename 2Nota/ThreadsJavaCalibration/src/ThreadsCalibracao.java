import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ThreadsCalibracao extends Thread{
    private int initialLine;
    private int finalLine;
    private Path path;
    private int resultSum;
    private int count;

    public ThreadsCalibracao(Path path, int initialLine, int finalLine){
        this.path = path;
        this.initialLine = initialLine;
        this.finalLine = finalLine;
    }

    @Override
    public void run(){
        try {
            List<String> calibrations = Files.readAllLines(path);
            resultSum = 0;
            count = 0;
            for (int i = initialLine-1; i<finalLine; i++) {
                resultSum += Calibracao.valorCalibracao(calibrations.get(i));
                count++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getInitialLine() {
        return initialLine;
    }

    public void setInitialLine(int initialLine) {
        this.initialLine = initialLine;
    }

    public int getFinalLine() {
        return finalLine;
    }

    public void setFinalLine(int finalLine) {
        this.finalLine = finalLine;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getResultSum() {
        return resultSum;
    }

    public void setResultSum(int resultSum) {
        this.resultSum = resultSum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
