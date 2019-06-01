package by.epam.multithreading.entity;

import by.epam.multithreading.creator.CreatorParameters;
import by.epam.multithreading.exception.DataFileException;
import by.epam.multithreading.exception.DataWrongException;
import by.epam.multithreading.reader.FileDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

class CreatorSquareMatrix {

    private static final int DEFAULT_SIZE = 10;
    private static final int MIN_MATRIX_ELEMENT = 10;
    private static final int MAX_MATRIX_ELEMENT = 20;
    private static final String PATH_FILE = "data/input.txt";
    private FileDataReader reader;
    private CreatorParameters creatorParameters;
    private static final Logger LOGGER = LogManager.getLogger();

    int createMatrixSize (){
        int size = DEFAULT_SIZE;
        reader = new FileDataReader();
        String param = null;
        List<Integer> list = null;

        try {
            creatorParameters = new CreatorParameters();
            param = reader.readDataFile(PATH_FILE);
            list = creatorParameters.createParametres(param);
        } catch (DataFileException | DataWrongException e) {
            LOGGER.error("Wrong data : " + e);
        }

        if(isValidParameters(list)){
        int minSize = list.get(0);
        int maxSize = list.get(1);
            try {
                size = createMatrixSize(minSize, maxSize);
            } catch (DataWrongException e) {
                LOGGER.error("Wrong data : " + e);
            }
        }

        return size;
    }

    private int createMatrixSize(int min, int max) throws DataWrongException {
        int size = (int)((Math.random() * (max - min + 1) + min));
        return size;
    }

    void fillingMatrix(SquareMatrix matrix) {
        for(int i = 0; i < matrix.getSize(); i++){
            for (int j = 0; j < matrix.getSize(); j++){
                if(i != j){
                    matrix.setElement(i, j, randomElementMatrix());
                }
            }
        }
        LOGGER.info("Matrix was filled");
    }

    private static int randomElementMatrix(){
        int element = (int)((Math.random() * (MAX_MATRIX_ELEMENT - MIN_MATRIX_ELEMENT) + MIN_MATRIX_ELEMENT));

        return element;
    }

    private boolean isValidParameters (List<Integer> listParam) {
        boolean status = false;

        if(listParam != null && listParam.size() == 4){
            if(listParam.get(0) > 0 && listParam.get(1) > 0
                    && listParam.get(0) <= listParam.get(1)){
                status = true;
            }
        }

        return status;
    }
}
