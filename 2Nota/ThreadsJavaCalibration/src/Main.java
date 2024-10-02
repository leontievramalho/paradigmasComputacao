

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Sem threads");

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\new_calibration_text.txt");
        long tempoInicial = System.currentTimeMillis();
        SemThreadsCalibracao semThreads = new SemThreadsCalibracao(path);
        semThreads.calibrate();

        long tempoFinal = System.currentTimeMillis();
        double timeSpent = (tempoFinal - tempoInicial) / 1000d;

        System.out.printf("%.3f ms%n", timeSpent);

        System.out.println("Com 8 threads");
        tempoInicial = System.currentTimeMillis();
        ThreadsCalibracao thread1 = new ThreadsCalibracao(path,1,10000);
        ThreadsCalibracao thread2 = new ThreadsCalibracao(path,10001,20000);
        ThreadsCalibracao thread3 = new ThreadsCalibracao(path,20001,30000);
        ThreadsCalibracao thread4 = new ThreadsCalibracao(path,30001,40000);
        ThreadsCalibracao thread5 = new ThreadsCalibracao(path,40001,50000);
        ThreadsCalibracao thread6 = new ThreadsCalibracao(path,50001,60000);
        ThreadsCalibracao thread7 = new ThreadsCalibracao(path,60001,70000);
        ThreadsCalibracao thread8 = new ThreadsCalibracao(path,70001,80000);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("A soma dos valores é: "
                + (thread1.getResultSum()
                + thread2.getResultSum()
                + thread3.getResultSum()
                + thread4.getResultSum()
                + thread5.getResultSum()
                + thread6.getResultSum()
                + thread7.getResultSum()
                + thread8.getResultSum()
                )
        );

        System.out.println("Total de linhas: "
                + (thread1.getCount()
                + thread2.getCount()
                + thread3.getCount()
                + thread4.getCount()
                + thread5.getCount()
                + thread6.getCount()
                + thread7.getCount()
                + thread8.getCount()
                )
        );
        tempoFinal = System.currentTimeMillis();
        timeSpent = (tempoFinal - tempoInicial) / 1000d;
        System.out.printf("%.3f ms%n", timeSpent);

        System.out.println("Com 2 threads");
        tempoInicial = System.currentTimeMillis();
        thread1 = new ThreadsCalibracao(path,1,40000);
        thread2 = new ThreadsCalibracao(path,40001,80000);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("A soma dos valores é: "
                        + (thread1.getResultSum()
                        + thread2.getResultSum()
                )
        );

        System.out.println("Total de linhas: "
                        + (thread1.getCount()
                        + thread2.getCount()
                )
        );
        tempoFinal = System.currentTimeMillis();
        timeSpent = (tempoFinal - tempoInicial) / 1000d;
        System.out.printf("%.3f ms%n", timeSpent);
    }
}