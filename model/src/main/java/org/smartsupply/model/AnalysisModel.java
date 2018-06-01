package org.smartsupply.model;

public class AnalysisModel {

    public String name;
    public Double numbers;

    public AnalysisModel(String name, Double numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNumbers() {
        return numbers;
    }

    public void setNumbers(Double numbers) {
        this.numbers = numbers;
    }
}





