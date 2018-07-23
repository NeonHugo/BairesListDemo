package br.com.nmsystems.baireslistdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.R;
import br.com.nmsystems.baireslistdemo.adapter.CardAdapter;
import br.com.nmsystems.baireslistdemo.server.GetCardsListServerData;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;
import br.com.nmsystems.libmaster.EditTextSearch;

public class CardsList extends AppCompatActivity implements CardsListContract.I_View {

    private Context context;


    private LinearLayout ll_error;
    private LinearLayout ll_content;
    private LinearLayout ll_empty;

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
        ll_error = findViewById(R.id.ll_error);
        ll_content = findViewById(R.id.ll_content);
        ll_empty = findViewById(R.id.ll_empty);
        //
        ll_error.setVisibility(View.GONE);
        ll_content.setVisibility(View.GONE);
        ll_empty.setVisibility(View.GONE);
        //
        et_search = findViewById(R.id.et_search);
        rv_cards = findViewById(R.id.rv_cards);
        rv_cards.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new CardsListPresenter(new GetCardsListServerData(this));

        mPresenter.getCardsList(ToolBox.sDays(null, 0), ToolBox.sDays(null, 10), true);
    }

    private void initActions() {
    }

    @Override
    public void loadCardList(ArrayList<HMAux> cards) {
        adapterCards = new CardAdapter(cards, Glide.with(this));
        rv_cards.setAdapter(adapterCards);
        //
        et_search.setOnReportTextChangeListener(new EditTextSearch.IEditTextSearchChangeText() {
            @Override
            public void reportTextChange(String text) {
                adapterCards.getFilter().filter(text);
            }
        });

        rv_cards.setAdapter(adapterCards);
        //
        if (adapterCards.getItemCount() == 0) {
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
        } else {
            ll_empty.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }
        //
        ll_error.setVisibility(View.GONE);
        //
        et_search.setOnReportTextChangeListener(new EditTextSearch.IEditTextSearchChangeText() {
            @Override
            public void reportTextChange(String text) {
                adapterCards.getFilter().filter(text);
            }
        });

    }

    @Override
    public void onFailure(String error) {
        ll_error.setVisibility(View.VISIBLE);
        ll_content.setVisibility(View.GONE);
        ll_empty.setVisibility(View.GONE);
    }
}
