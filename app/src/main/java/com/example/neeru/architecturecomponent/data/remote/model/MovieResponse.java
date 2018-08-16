package com.example.neeru.architecturecomponent.data.remote.model;

import java.util.ArrayList;

public class MovieResponse {

    private ArrayList<ResultsBean> results;

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

}
