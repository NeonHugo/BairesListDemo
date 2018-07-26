package br.com.nmsystems.baireslistdemo.ui;

import br.com.nmsystems.baireslistdemo.server.GetCardsListServerDataContract;

public class CardsListPresenter implements CardsListContract.I_Presenter {

    /**
     * Interface for access to the CardList.
     */
    private GetCardsListServerDataContract mServer;

    public CardsListPresenter(GetCardsListServerDataContract mServer) {
        this.mServer = mServer;
    }

    @Override
    public void getCardsList(String startDate, String endDate, boolean includeSuggested) {
        mServer.getCardsList(startDate, endDate, includeSuggested);
    }

}
