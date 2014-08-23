package com.goEuro.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Position suggestion
 * <p/>
 * This class contains only fields required for CSV export
 * <p/>
 * Created by Abhishek Ghosh on 23/08/14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionSuggestion {

    private long _id;

    private String name;

    private String type;

    @JsonProperty("geo_position")
    private Position position;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
