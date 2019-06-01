package by.epam.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SquareMatrix {

    private static int size;
    private static int [][] matrixElement;
    private static Lock lock = new ReentrantLock();
    private static CreatorSquareMatrix creator;
    private static SquareMatrix matrix;
    private static final Logger LOGGER = LogManager.getLogger();

    private SquareMatrix(int size){
        this.size = size;
        matrixElement = new int[size][size];
        LOGGER.debug("Matrix was created with size : " + size);
    }

    public static SquareMatrix getMatrix() {
        lock.lock();
        try{
            if(matrix == null){
                creator = new CreatorSquareMatrix();
                matrix = new SquareMatrix(creator.createMatrixSize());
                creator.fillingMatrix(matrix);
            }
        }finally {
            lock.unlock();
        }
        return matrix;
    }

    public  int getSize() {
        return size;
    }

    public int[][] getMatrixElement() {
        return matrixElement;
    }

    public boolean setElement(int i, int j, int value) {
           boolean status = false;
            if(i < size && j < size && i >= 0 && j >= 0){
                matrixElement[i][j] = value;
                status = true;
            }
            return status;
    }

    public int getElement(int i, int j){
        int result = -1;

        if(i < size && j < size && i >= 0 && j >= 0){
            result = matrixElement[i][j];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                stringBuilder.append(matrixElement[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
