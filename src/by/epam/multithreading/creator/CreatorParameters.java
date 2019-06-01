package by.epam.multithreading.creator;

import by.epam.multithreading.exception.DataWrongException;

import java.util.ArrayList;
import java.util.List;


public class CreatorParameters {

    public static final String SPLIT_REGEX = "\\s+|\t";

    public List<Integer> createParametres (String textFile) throws DataWrongException {
        List<Integer> listParametres = null;

        if(textFile.isEmpty()){
            throw new DataWrongException("Text is null");
        }

        String [] massParametres = textFile.split(SPLIT_REGEX);

        listParametres = new ArrayList<>(massParametres.length);


        for(String x : massParametres){
            listParametres.add(Integer.parseInt(x.trim()));
        }

        return listParametres;
    }
}
