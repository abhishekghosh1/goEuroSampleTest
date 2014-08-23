package com.goEuro.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.goEuro.test.dto.PositionSuggestion;
import com.goEuro.test.exception.PositionSuggestionException;
import com.goEuro.test.util.MessageType;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * PositionSuggestionService calls the webservice and set position dto
 * <p/>
 * Created by Abhishek Ghosh on 23/08/14.
 */
public class PositionSuggestionService {

    public String endpoint;

    /**
     * requestString contains keyword. I am going to concat with the serviceEndpoint and call the
     * webservice for webservice response and transform into dto and returns them
     *
     * @param requestString text query
     * @return list of position suggestion for query
     * @throws PositionSuggestionException if something wrong happens on the way from remote server
     */
    public List<PositionSuggestion> getPositionSuggestions(String requestString) throws PositionSuggestionException {
        Preconditions.checkArgument(requestString != null);

        final ObjectMapper jsonMapper = new ObjectMapper();

        try {
            HttpResponse<String> response = Unirest.get(endpoint + requestString)
                    .header("Accept", MessageType.JSON.getMimeType())
                    .asString();

            return Arrays.asList(jsonMapper.readValue(response.getRawBody(), PositionSuggestion[].class));
        } catch (Exception e) {
            throw new PositionSuggestionException(e.getMessage(), e);
        }
    }

    /**
     * Exports list of dto based on the CSV output configured in the csvWriter
     *
     * @param positionSuggestions Collections  of dto
     * @return CSV output as string
     */
    public String exportAsCSV(Collection<PositionSuggestion> positionSuggestions) {
        Preconditions.checkArgument(positionSuggestions != null);

        final ObjectWriter csvWriter = new CsvMapper().writer(
                CsvSchema.builder()
                        .setUseHeader(true)
                        .addColumn("_id")
                        .addColumn("name")
                        .addColumn("type")
                        .addColumn("latitude")
                        .addColumn("longitude")
                        .build());

        try {
            //Based on the CSV header generating the outputData
            return csvWriter.writeValueAsString(Collections2.filter(Collections2.transform(positionSuggestions, new Function<PositionSuggestion, Object>() {
                @Override
                public Object apply(final PositionSuggestion positionSuggestion) {
                    try {
                        //Iterating PositionSuggestion object and set the CSV value object.
                        return new Object() {
                            public long _id = positionSuggestion.get_id();
                            public String name = positionSuggestion.getName();
                            public String type = positionSuggestion.getType();
                            public double latitude = positionSuggestion.getPosition().getLatitude();
                            public double longitude = positionSuggestion.getPosition().getLongitude();
                        };
                    } catch (Exception e) {
                        // if any error occurred while generating  I am logging the error message and retuning null
                        System.err.print(e.getMessage());
                        return null;
                    }
                }
            }), new Predicate<Object>() {
                @Override
                public boolean apply(Object csvDto) {
                    return csvDto != null;
                }
            }));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Closes Rest Service manually to stop the REST call
     * This method called from EntryPoint to shutdown the application
     */
    public void shutdown() {

        try {
            Unirest.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    /*
     * set the serviceEndpoint
     * @param endpoint set the serviceEndpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
