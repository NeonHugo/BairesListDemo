package br.com.nmsystems.baireslistdemo.server;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.model.Card;
import br.com.nmsystems.baireslistdemo.model.Trans_Env;
import br.com.nmsystems.baireslistdemo.ui.CardsListContract;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;

public class GetCardsListServerData implements GetCardsListServerDataContract {

    private CardsListContract.I_View mView;


    public GetCardsListServerData(CardsListContract.I_View mView) {
        this.mView = mView;
    }

    @Override
    public void getCardsList(String startDate, String endDarte, boolean includeSuggested) {

        Gson gson = new Gson();

        Trans_Env env = new Trans_Env();
        env.setStartDate(startDate);
        env.setEndDarte(endDarte);
        env.setIncludeSuggested(includeSuggested);

        new SyncTask().execute(gson.toJson(env));
    }

    private class SyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {

            String resultado = null;

            try {
                resultado = ToolBox.connWebService(
                        "https://webservices.vividseats.com/rest/mobile/v1/home/cards",
                        "{\n" +
                                "\"startDate\": \"2018-07-19\",\n" +
                                "\"endDate\": \"2018-07-25\",\n" +
                                "\"includeSuggested\": \"true\"\n" +
                                "}"
                );
            } catch (Exception e) {
                return e.toString();
            }

            return resultado;
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

                    item.put("image", cardsClass.get(i).getImage());
                    item.put("toplabel", cardsClass.get(i).getTopLabel());
                    item.put("middlelabel", cardsClass.get(i).getMiddleLabel());
                    item.put("bottomlabel", cardsClass.get(i).getBottomLabel());
                    item.put("love", "0");

                    cards.add(item);
                }

                mView.loadCardList(cards);

            } catch (Exception e) {
                mView.onFailure(results);
            }
        }
    }
}
