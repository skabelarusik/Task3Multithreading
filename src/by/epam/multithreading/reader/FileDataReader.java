package by.epam.multithreading.reader;

import by.epam.multithreading.exception.DataFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileDataReader {

    private static final Logger LOGGER = LogManager.getLogger();

    public static String readDataFile(String path) throws DataFileException {
        String listParam;

        if (path == null) {
            LOGGER.fatal("Path " + path + "is null");
            throw new DataFileException("Puth is null : " + path);
        }

        try {
            listParam = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            LOGGER.fatal("Path " + path + "is wrong");
            throw new DataFileException("Wrong puth : " , e);
        }

        return listParam;
    }
}
