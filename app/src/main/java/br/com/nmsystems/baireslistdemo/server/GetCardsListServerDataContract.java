package br.com.nmsystems.baireslistdemo.server;

public interface GetCardsListServerDataContract {

    void getCardsList(String startDate, String endDarte, boolean includeSuggested);

}
