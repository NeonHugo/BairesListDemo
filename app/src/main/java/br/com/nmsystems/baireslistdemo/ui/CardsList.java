package br.com.nmsystems.baireslistdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.R;
import br.com.nmsystems.baireslistdemo.adapter.CardAdapter;
import br.com.nmsystems.baireslistdemo.server.GetCardsListServerDataRetrofit;
import br.com.nmsystems.baireslistdemo.util.Constants;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;
import br.com.nmsystems.baireslistdemo.util.UIStatus;
import br.com.nmsystems.libmaster.EditTextSearch;

public class CardsList extends AppCompatActivity implements CardsListContract.I_View {

    private Context context;

    private TabLayout tabLayout;

    private LinearLayout ll_status;
    private ProgressBar pb_status;
    private ImageView iv_status;
    private TextView tv_status;
    //
    private LinearLayout ll_content;

    private EditTextSearch et_search;
    private RecyclerView rv_cards;
    private CardAdapter adapterCards;

    private CardsListPresenter mPresenter;

    private boolean changeCfg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardslist);

        initVars(savedInstanceState);
        initActions();
    }

    private void initVars(Bundle savedInstanceState) {
        context = getBaseContext();

        tabLayout = findViewById(R.id.tabs);
        ll_status = findViewById(R.id.ll_status);
        pb_status = findViewById(R.id.pb_status);
        iv_status = findViewById(R.id.iv_status);
        tv_status = findViewById(R.id.tv_status);

        ll_content = findViewById(R.id.ll_content);
        et_search = findViewById(R.id.et_search);
        rv_cards = findViewById(R.id.rv_cards);
        rv_cards.setLayoutManager(new LinearLayoutManager(this));

        setupTabIcons();

        //mPresenter = new CardsListPresenter(new GetCardsListServerDataCWB(this));
        mPresenter = new CardsListPresenter(new GetCardsListServerDataRetrofit(this));

        refreshLLStatus(UIStatus.PROGRESS);
        mPresenter.getCardsList(ToolBox.sDays(null, "", 0), ToolBox.sDays(null, "", Constants.DAYS_TO_SEARCH), true);
    }

    private void setupTabIcons() {

        TextView tabSuggested = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabSuggested.setText(R.string.tab_suggested);
        //tabSuggested.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_on, 0, 0);

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setCustomView(tabSuggested);
        tabLayout.addTab(firstTab);

        TextView tabViewed = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabViewed.setText(R.string.tab_viewed);
        //tabViewed.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_off, 0, 0);

        TabLayout.Tab firstSecond = tabLayout.newTab();
        firstSecond.setCustomView(tabViewed);
        //tabLayout.addTab(firstSecond);

        TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab.setText(R.string.tab_favorities);
        //tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_on, 0, 0);

        TabLayout.Tab firstThird = tabLayout.newTab();
        firstThird.setCustomView(tab);
        tabLayout.addTab(firstThird);
    }

    private void initActions() {
        ll_status.setOnClickListener(statusClick);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0){
                    adapterCards.setOption(true);
                } else {
                    adapterCards.setOption(false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void loadCardList(ArrayList<HMAux> cards) {
        adapterCards = new CardAdapter(cards, Glide.with(this));
        adapterCards.setOnItemClickListener(new CardAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position, HMAux item) {
                int i = 10;
            }
        });
        rv_cards.setAdapter(adapterCards);

        if (adapterCards != null && !changeCfg) {
            changeCfg = !changeCfg;

            et_search.setOnReportTextChangeListener(new EditTextSearch.IEditTextSearchChangeText() {
                @Override
                public void reportTextChange(String text) {
                    adapterCards.getFilter().filter(text);
                }
            });
        }

        if (adapterCards.getItemCount() == 0) {
            refreshLLStatus(UIStatus.EMPTY);
        } else {
            refreshLLStatus(UIStatus.OK);
        }

        et_search.setOnReportTextChangeListener(new EditTextSearch.IEditTextSearchChangeText() {
            @Override
            public void reportTextChange(String text) {
                adapterCards.getFilter().filter(text);
            }
        });
    }

    @Override
    public void onFailure(String error) {
        refreshLLStatus(UIStatus.ERROR);
    }

    @Override
    public void refreshLLStatus(int uiStatus) {
        switch (uiStatus) {
            case UIStatus.OK:
                ll_status.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                break;
            case UIStatus.EMPTY:
                ll_status.setVisibility(View.VISIBLE);
                ll_status.setOnClickListener(statusClick);
                pb_status.setVisibility(View.GONE);
                iv_status.setImageResource(R.drawable.ic_empty);
                iv_status.setVisibility(View.VISIBLE);
                tv_status.setText("");

                ll_content.setVisibility(View.GONE);
                break;
            case UIStatus.ERROR:
                ll_status.setVisibility(View.VISIBLE);
                ll_status.setOnClickListener(statusClick);
                pb_status.setVisibility(View.GONE);
                iv_status.setImageResource(R.drawable.ic_sync_alert);
                iv_status.setVisibility(View.VISIBLE);
                tv_status.setText("");

                ll_content.setVisibility(View.GONE);
                break;
            case UIStatus.PROGRESS:
                ll_status.setVisibility(View.VISIBLE);
                ll_status.setOnClickListener(null);
                pb_status.setVisibility(View.VISIBLE);
                iv_status.setVisibility(View.GONE);
                tv_status.setText("");
                ll_content.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private View.OnClickListener statusClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            refreshLLStatus(UIStatus.PROGRESS);

            mPresenter.getCardsList(ToolBox.sDays(null, "", 0), ToolBox.sDays(null, "", Constants.DAYS_TO_SEARCH), true);
        }
    };
}
