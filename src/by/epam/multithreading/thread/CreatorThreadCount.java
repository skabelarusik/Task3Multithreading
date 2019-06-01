package by.epam.multithreading.thread;

import by.epam.multithreading.creator.CreatorParameters;
import by.epam.multithreading.exception.DataFileException;
import by.epam.multithreading.exception.DataWrongException;
import by.epam.multithreading.reader.FileDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

class CreatorThreadCount {

    private static final String PATH_FILE = "data/input.txt";
    private static final int DEFAULT_COUNT = 5;
    private FileDataReader reader;
    private CreatorParameters creatorParameters;
    private static final Logger LOGGER = LogManager.getLogger();


    int createThreadCount(){
        int count = DEFAULT_COUNT;
        String param;
        reader = new FileDataReader();
        List<Integer> list = null;

        try {
            creatorParameters = new CreatorParameters();
            param = reader.readDataFile(PATH_FILE);
            list = creatorParameters.createParametres(param);
        } catch (DataWrongException | DataFileException e) {
            LOGGER.error("Wrong data : " + e);
        }

        if(isValidParameters(list)){
            int minSize = list.get(2);
            int maxSize = list.get(3);
            try {
                count = createThreadCount(minSize, maxSize);
            } catch (DataWrongException e) {
                LOGGER.error("Wrong data : " + e);
            }
        }

        return count;
    }

    private int createThreadCount(int min, int max) throws DataWrongException {
        int size = (int)((Math.random() * (max - min + 1) + min));
        return size;
    }

    private boolean isValidParameters (List<Integer> listParam) {
        boolean status = false;

        if(listParam != null && listParam.size() == 4){
            if(listParam.get(2) > 0 && listParam.get(3) > 0 &&
                    listParam.get(2) <= listParam.get(3)){
                status = true;
            }
        }

        return status;
    }
}
