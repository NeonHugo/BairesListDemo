package br.com.nmsystems.baireslistdemo.server;

public interface GetCardsListServerDataContract {

    /**
     * Interface for Getting the CardList
     * @param startDate - initial date for the search range
     * @param endDate - final date for the search range
     * @param includeSuggested - flag for suggested info also
     */
    void getCardsList(String startDate, String endDate, boolean includeSuggested);

}
