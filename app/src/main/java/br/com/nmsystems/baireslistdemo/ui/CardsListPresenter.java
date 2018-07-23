package br.com.nmsystems.baireslistdemo.ui;

import br.com.nmsystems.baireslistdemo.server.GetCardsListServerDataContract;

public class CardsListPresenter implements CardsListContract.I_Presenter {

    private GetCardsListServerDataContract mServer;

    public CardsListPresenter(GetCardsListServerDataContract mServer) {
        this.mServer = mServer;
    }

    @Override
    public void getCardsList(String startDate, String endDarte, boolean includeSuggested) {
        mServer.getCardsList(startDate, endDarte, includeSuggested);
    }

}
