package br.com.nmsystems.baireslistdemo.server;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.model.Card;
import br.com.nmsystems.baireslistdemo.model.Trans_Env;
import br.com.nmsystems.baireslistdemo.ui.CardsListContract;
import br.com.nmsystems.baireslistdemo.util.Constants;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;

/**
 * Old Method. Not Used Anymore. Used on the beginning of the development to acess WebService
 * Easily replaced for GetCardsListServerDataRetrofit. Both Implement the same Contract.
 */
public class GetCardsListServerDataCWB implements GetCardsListServerDataContract {

    public static final String BASE_URL = "https://webservices.vividseats.com/rest/mobile/v1/home/cards";

    private CardsListContract.I_View mView;


    public GetCardsListServerDataCWB(CardsListContract.I_View mView) {
        this.mView = mView;
    }

    @Override
    public void getCardsList(String startDate, String endDarte, boolean includeSuggested) {

        Gson gson = new Gson();

        Trans_Env env = new Trans_Env();
        env.setStartDate(startDate);
        env.setEndDate(endDarte);
        env.setIncludeSuggested(String.valueOf(includeSuggested));

        new SyncTask().execute(gson.toJson(env));
    }

    private class SyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... strings) {

            String results = null;

            try {
                results = ToolBox.connWebService(
                        BASE_URL,
                        strings[0]
                );
            } catch (Exception e) {
                return e.toString();
            }

            return results;
        }

        @Override
        protected void onPostExecute(String results) {
            try {
                Gson gson = new Gson();

                ArrayList<Card> cardsClass = gson.fromJson(
                        results,
                        new TypeToken<ArrayList<Card>>() {
                        }.getType()
                );

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

            } catch (Exception e) {
                mView.onFailure(results);
            }
        }
    }
}
