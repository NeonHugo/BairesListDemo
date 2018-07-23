package br.com.nmsystems.baireslistdemo.model;

public class Trans_Env {

    private String startDate;
    private String endDarte;
    private boolean includeSuggested;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDarte() {
        return endDarte;
    }

    public void setEndDarte(String endDarte) {
        this.endDarte = endDarte;
    }

    public boolean isIncludeSuggested() {
        return includeSuggested;
    }

    public void setIncludeSuggested(boolean includeSuggested) {
        this.includeSuggested = includeSuggested;
    }
}
