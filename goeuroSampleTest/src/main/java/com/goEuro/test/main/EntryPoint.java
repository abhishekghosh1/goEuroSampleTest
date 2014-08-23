package com.goEuro.test.main;

import com.goEuro.test.dto.PositionSuggestion;
import com.goEuro.test.exception.PositionSuggestionException;
import com.goEuro.test.service.PositionSuggestionService;

import java.util.List;

/**
 * GoEuroSampleTest Application starts from here
 * <p/>
 * You can check README.md for the usage of this application
 * <p/>
 * Created by Abhishek Ghosh on 23/08/14.
 */
public class EntryPoint {

    public static void main(String[] args) {
        // If Argument length is not provided it should exit from the program
        if (args.length != 1) {
            System.err.println("You Need to provide a city name as a input text to run this program");
            System.exit(1);
        }

        getPositionData(args[0]);
    }

    public static String getPositionData(String arg) {
        String requestString = arg;

        /* PositionSuggestionService will actually call the rest service.
         * From the main program we are using the serviceEndpoint. I am trying to follow ServiceLocater pattern to develop this program
         */
        PositionSuggestionService service = new PositionSuggestionService();
        service.setEndpoint("http://api.goeuro.com/api/v2/position/suggest/en/");

        try {
            List<PositionSuggestion> suggestions = service.getPositionSuggestions(requestString);

            String csv = service.exportAsCSV(suggestions);

            System.out.print(csv);
            return csv;
        } catch (PositionSuggestionException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
            return null;
        } finally {
            // Shutdown the Rest Service call
            service.shutdown();
        }
    }
}
