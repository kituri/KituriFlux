package com.flux.kituri.app.data;

import java.util.List;

/**
 * Created by kirijin on 2016/4/12.
 */

public class MeizhiData extends Entry {

    private boolean error;

    private List<Meizhi> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return this.error;
    }

    public void setResults(List<Meizhi> results) {
        this.results = results;
    }

    public List<Meizhi> getResults() {
        return this.results;
    }



}
