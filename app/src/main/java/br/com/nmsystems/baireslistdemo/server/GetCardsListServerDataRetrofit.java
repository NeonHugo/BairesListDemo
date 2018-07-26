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

    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String INCLUDE_SUGGESTED = "includeSuggested";

    public static final String ROW_EMPTY = "0";

    /**
     * interface to access the View
     */
    private CardsListContract.I_View mView;

    public GetCardsListServerDataRetrofit(CardsListContract.I_View mView) {
        this.mView = mView;
    }

    /**
     * Handles WebService Call to retrieve card list
     *
     * @param startDate
     * @param endDate
     * @param includeSuggested
     */
    @Override
    public void getCardsList(String startDate, String endDate, boolean includeSuggested) {

        GetCardsListServerDataRetrofitServer service =
                RetrofitInstance.getRetrofitInstance().create(GetCardsListServerDataRetrofitServer.class);

        /**
         * Parameters for the call
         */
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put(START_DATE, startDate);
        requestBody.put(END_DATE, endDate);
        requestBody.put(INCLUDE_SUGGESTED, String.valueOf(includeSuggested));

        Call<List<Card>> call = service.cards(requestBody);

        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.body() != null) {
                    ArrayList<Card> cardsClass = (ArrayList<Card>) response.body();

                    /**
                     * The Card ArrayList<Card> is recreated to an ArrayList<HMAux> only with the proper field selection
                     * for the RecyclerView
                     */
                    ArrayList<HMAux> cards = new ArrayList<>();
                    for (int i = 0; i < cardsClass.size(); i++) {
                        HMAux item = new HMAux();

                        item.put(Constants.IMAGE, cardsClass.get(i).getImage());
                        item.put(Constants.TOPLABEL, cardsClass.get(i).getTopLabel());
                        item.put(Constants.MIDDLELABEL, cardsClass.get(i).getMiddleLabel());
                        item.put(Constants.BOTTOMLABEL, cardsClass.get(i).getBottomLabel());
                        item.put(Constants.EVENTCOUNT, String.valueOf(cardsClass.get(i).getEventCount()));
                        item.put(Constants.LOVE, ROW_EMPTY);

                        cards.add(item);
                    }

                    /**
                     * Calls view with the info retrieved
                     */
                    mView.loadCardList(cards);
                } else {
                    /**
                     * Calls view with a serious error (for example: connection loss)
                     */
                    mView.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                /**
                 * Calls view with a serious error reported by Retrofit
                 */
                mView.onFailure(t.getMessage());
            }
        });

    }
}
