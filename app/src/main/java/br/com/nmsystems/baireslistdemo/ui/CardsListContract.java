package br.com.nmsystems.baireslistdemo.ui;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.util.HMAux;

public interface CardsListContract {

    interface I_View {

        void loadCardList(ArrayList<HMAux> cards);

        void onFailure(String error);

        void refreshLLStatus(int uiStatus);

    }

    interface I_Presenter {
        void getCardsList(String startDate, String endDarte, boolean includeSuggested);
    }

}
