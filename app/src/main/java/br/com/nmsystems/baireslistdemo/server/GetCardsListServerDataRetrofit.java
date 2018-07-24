package br.com.nmsystems.baireslistdemo.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.nmsystems.baireslistdemo.model.Card;
import br.com.nmsystems.baireslistdemo.ui.CardsListContract;
import br.com.nmsystems.baireslistdemo.util.Constants;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCardsListServerDataRetrofit implements GetCardsListServerDataContract {

    private CardsListContract.I_View mView;

    public GetCardsListServerDataRetrofit(CardsListContract.I_View mView) {
        this.mView = mView;
    }

    @Override
    public void getCardsList(String startDate, String endDarte, boolean includeSuggested) {

        GetCardsListServerDataRetrofitServer service =
                RetrofitInstance.getRetrofitInstance().create(GetCardsListServerDataRetrofitServer.class);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("startDate", startDate);
        requestBody.put("endDate", endDarte);
        requestBody.put("includeSuggested", String.valueOf(includeSuggested));

        Call<List<Card>> call = service.cards(requestBody);

        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.body() != null) {
                    ArrayList<Card> cardsClass = (ArrayList<Card>) response.body();

                    ArrayList<HMAux> cards = new ArrayList<>();
                    for (int i = 0; i < cardsClass.size(); i++) {
                        HMAux item = new HMAux();

                        item.put(Constants.IMAGE, cardsClass.get(i).getImage());
                        item.put(Constants.TOPLABEL, cardsClass.get(i).getTopLabel());
                        item.put(Constants.MIDDLELABEL, cardsClass.get(i).getMiddleLabel());
                        item.put(Constants.BOTTOMLABEL, cardsClass.get(i).getBottomLabel());
                        item.put(Constants.EVENTCOUNT, String.valueOf(cardsClass.get(i).getEventCount()));
                        item.put(Constants.LOVE, "0");

                        cards.add(item);
                    }

                    mView.loadCardList(cards);
                } else {
                    mView.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                mView.onFailure(t.getMessage());
            }
        });

    }
}