package by.epam.multithreading;

import by.epam.multithreading.entity.SquareMatrix;
import by.epam.multithreading.thread.MatrixThread;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args)  {

        SquareMatrix matrix = SquareMatrix.getMatrix();
        LOGGER.debug("\n" + matrix + " was initialized");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(MatrixThread.COUNT_THREAD);
        ExecutorService executorService = Executors.newFixedThreadPool(MatrixThread.COUNT_THREAD);

        for(int i = 0; i < MatrixThread.COUNT_THREAD; i++) {
            executorService.execute(new MatrixThread(cyclicBarrier));
        }
        executorService.shutdown();
        try {
            LOGGER.debug("\n" + matrix + " was updated");
            executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted exception : " + e);
        }
    }

}
