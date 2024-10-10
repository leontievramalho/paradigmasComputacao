import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Sem threads");

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\new_calibration_text.txt");
        long tempoInicial = System.currentTimeMillis();
        SemThreadsCalibracao semThreads = new SemThreadsCalibracao(path);
        semThreads.calibrate();

        long tempoFinal = System.currentTimeMillis();
        double timeSpent = (tempoFinal - tempoInicial) / 1000d;

        System.out.printf("%.3f ms%n", timeSpent);

        System.out.println("\nCom 8 threads");
        tempoInicial = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(8);
        ThreadsCalibracao runnable1 = new ThreadsCalibracao(path,
                1,
                10000,
                latch);
        ThreadsCalibracao runnable2 = new ThreadsCalibracao(path,
                10001,
                20000,
                latch);
        ThreadsCalibracao runnable3 = new ThreadsCalibracao(path,
                20001,
                30000,
                latch);
        ThreadsCalibracao runnable4 = new ThreadsCalibracao(path,
                30001,
                40000,
                latch);
        ThreadsCalibracao runnable5 = new ThreadsCalibracao(path,
                40001,
                50000,
                latch);
        ThreadsCalibracao runnable6 = new ThreadsCalibracao(path,
                50001,
                60000,
                latch);
        ThreadsCalibracao runnable7 = new ThreadsCalibracao(path,
                60001,
                70000,
                latch);
        ThreadsCalibracao runnable8 = new ThreadsCalibracao(path,
                70001,
                80000,
                latch);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        Thread thread6 = new Thread(runnable6);
        Thread thread7 = new Thread(runnable7);
        Thread thread8 = new Thread(runnable8);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        latch.await();

        System.out.println("A soma dos valores é: "
                + (runnable1.getResultSum()
                + runnable2.getResultSum()
                + runnable3.getResultSum()
                + runnable4.getResultSum()
                + runnable5.getResultSum()
                + runnable6.getResultSum()
                + runnable7.getResultSum()
                + runnable8.getResultSum())
        );

        System.out.println("Total de linhas: "
                + (runnable1.getCount()
                + runnable2.getCount()
                + runnable3.getCount()
                + runnable4.getCount()
                + runnable5.getCount()
                + runnable6.getCount()
                + runnable7.getCount()
                + runnable8.getCount()
                )
        );
        tempoFinal = System.currentTimeMillis();
        timeSpent = (tempoFinal - tempoInicial) / 1000d;
        System.out.printf("%.3f ms%n", timeSpent);

        System.out.println("\nCom 2 threads");
        tempoInicial = System.currentTimeMillis();
        latch = new CountDownLatch(2);
        runnable1 = new ThreadsCalibracao(path,
                1,
                40000,
                latch);
        runnable2 = new ThreadsCalibracao(path,
                40001,
                80000,
                latch);

        thread1 = new Thread(runnable1);
        thread2 = new Thread(runnable2);


        thread1.start();
        thread2.start();

        latch.await();

        System.out.println("A soma dos valores é: "
                + (runnable1.getResultSum()
                + runnable2.getResultSum())
        );

        System.out.println("Total de linhas: "
                        + (runnable1.getCount()
                        + runnable2.getCount()
                )
        );
        tempoFinal = System.currentTimeMillis();
        timeSpent = (tempoFinal - tempoInicial) / 1000d;
        System.out.printf("%.3f ms%n", timeSpent);
    }
}