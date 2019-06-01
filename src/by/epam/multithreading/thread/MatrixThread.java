package by.epam.multithreading.thread;

import by.epam.multithreading.entity.SquareMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MatrixThread extends Thread {

    public static final int COUNT_THREAD;
    private int number;
    private static int index;
    private Lock lock = new ReentrantLock();
    private static CreatorThreadCount creator;
    private CyclicBarrier barrier;
    private static final Logger LOGGER = LogManager.getLogger();



    static {
        creator = new CreatorThreadCount();
        COUNT_THREAD = creator.createThreadCount();
        LOGGER.debug("Threads was created. Count is " + COUNT_THREAD);
    }

    public MatrixThread(CyclicBarrier barrier){
        number = (int)(Math.random() * 9 + 1);
        LOGGER.info("Nimber " + this.getName() + " is : " + number);
        this.barrier = barrier;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run(){
        SquareMatrix matrix = SquareMatrix.getMatrix();

        while (index < matrix.getSize()){
            lock.lock();
            matrix.setElement(index, index++, number);
            LOGGER.debug("Number matrix was changed. New number is " + number);

            try {
                barrier.await(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                LOGGER.warn("Exception : " + e);
            } finally {
                lock.unlock();
            }
        }
    }
}


