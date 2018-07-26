package br.com.nmsystems.baireslistdemo.server;

import java.util.List;
import java.util.Map;

import br.com.nmsystems.baireslistdemo.model.Card;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetCardsListServerDataRetrofitServer {

    /**
     * Interface for acessing the WebService provided by the client
     * @param body
     * @return
     */
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "X-Mobile-Platform: Android;charset=utf-8"
    })
    @POST("rest/mobile/v1/home/cards")
    Call<List<Card>> cards(@Body Map<String, String> body);

}
