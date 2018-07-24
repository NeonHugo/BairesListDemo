package br.com.nmsystems.baireslistdemo.server;

import java.util.List;
import java.util.Map;

import br.com.nmsystems.baireslistdemo.model.Card;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetCardsListServerDataRetrofitServer {

    @Headers({
            "Content-Type: application/json",
            "X-Mobile-Platform: Android"
    })
    @POST("rest/mobile/v1/home/cards")
    Call<List<Card>> cards(@Body Map<String, String> body);

}
