package br.com.nmsystems.baireslistdemo.ui;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.util.HMAux;

public interface CardsListContract {

    interface I_View {

        void loadCardList(ArrayList<HMAux> cards);

    }

    interface I_Presenter {

        void getCardsList(String startDate, String endDarte, boolean includeSuggested);
    }
}
