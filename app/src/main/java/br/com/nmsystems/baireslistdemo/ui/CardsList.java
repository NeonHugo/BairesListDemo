package br.com.nmsystems.baireslistdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.R;
import br.com.nmsystems.baireslistdemo.adapter.CardAdapter;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.libmaster.EditTextSearch;

public class CardsList extends AppCompatActivity implements CardsListContract.I_View {

    private Context context;

    private EditTextSearch et_search;
    private RecyclerView rv_cards;
    private CardAdapter adapterCards;

    private CardsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardslist);

        initVars(savedInstanceState);
        initActions();
    }

    private void initVars(Bundle savedInstanceState) {
        context = getBaseContext();
        //
        et_search = findViewById(R.id.et_search);
        rv_cards = findViewById(R.id.rv_cards);
        rv_cards.setLayoutManager(new LinearLayoutManager(this));
        //
        mPresenter = new CardsListPresenter(this);
        //
        mPresenter.getCardsList("2018-07-22", "2018-07-30", true);
    }

    private void initActions() {
        et_search.setOnReportTextChangeListener(new EditTextSearch.IEditTextSearchChangeText() {
            @Override
            public void reportTextChange(String text) {
                adapterCards.getFilter().filter(text);
            }
        });
    }

    @Override
    public void loadCardList(ArrayList<HMAux> cards) {
        adapterCards = new CardAdapter(cards, Glide.with(this));
        //
        rv_cards.setAdapter(adapterCards);
    }
}
