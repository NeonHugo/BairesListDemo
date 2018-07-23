package br.com.nmsystems.baireslistdemo.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import br.com.nmsystems.baireslistdemo.model.Card;
import br.com.nmsystems.baireslistdemo.util.HMAux;

public class CardsListPresenter implements CardsListContract.I_Presenter {

    private CardsListContract.I_View mView;

    public CardsListPresenter(CardsListContract.I_View mView) {
        this.mView = mView;
    }

    @Override
    public void getCardsList(String startDate, String endDarte, boolean includeSuggested) {

        Gson gson = new Gson();

        ArrayList<Card> cardsClass = gson.fromJson(
                "[\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Comic Con\",\n" +
                        "        \"middleLabel\": \"San Diego Convention Center - San Diego, CA\",\n" +
                        "        \"bottomLabel\": \"Thu, Jul 19 - Sun, Jul 22\",\n" +
                        "        \"eventCount\": 5,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/theater/comic-con.jpg\",\n" +
                        "        \"targetId\": 13809,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 13809,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532015940000,\n" +
                        "        \"rank\": 310\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Chicago Cubs\",\n" +
                        "        \"middleLabel\": \"Wrigley Field - Chicago, IL\",\n" +
                        "        \"bottomLabel\": \"Thu, Jul 19 - Wed, Jul 25\",\n" +
                        "        \"eventCount\": 8,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/mlb/chicago-cubs.jpg\",\n" +
                        "        \"targetId\": 162,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 162,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532041500000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Zac Brown Band\",\n" +
                        "        \"middleLabel\": \"BB&T Pavilion - Camden, NJ\",\n" +
                        "        \"bottomLabel\": \"Thu, Jul 19 - Fri, Jul 20\",\n" +
                        "        \"eventCount\": 2,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/zac-brown-band.jpg\",\n" +
                        "        \"targetId\": 9202,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 9202,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532044800000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Radiohead\",\n" +
                        "        \"middleLabel\": \"Scotiabank Arena - Toronto, ON\",\n" +
                        "        \"bottomLabel\": \"Thu, Jul 19 - Fri, Jul 20\",\n" +
                        "        \"eventCount\": 2,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/radiohead.jpg\",\n" +
                        "        \"targetId\": 1230,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 1230,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532046600000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Taylor Swift\",\n" +
                        "        \"middleLabel\": \"MetLife Stadium - East Rutherford, NJ\",\n" +
                        "        \"bottomLabel\": \"Fri, Jul 20 - Sun, Jul 22\",\n" +
                        "        \"eventCount\": 3,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/taylor-swift.jpg\",\n" +
                        "        \"targetId\": 12245,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 12245,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532131200000,\n" +
                        "        \"rank\": 330\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Texas Rangers\",\n" +
                        "        \"middleLabel\": \"Globe Life Park in Arlington - Arlington, TX\",\n" +
                        "        \"bottomLabel\": \"Fri, Jul 20 - Wed, Jul 25\",\n" +
                        "        \"eventCount\": 6,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/mlb/texas-rangers.jpg\",\n" +
                        "        \"targetId\": 853,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 853,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532131500000,\n" +
                        "        \"rank\": 310\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"New York Yankees\",\n" +
                        "        \"middleLabel\": \"Yankee Stadium - Bronx, NY\",\n" +
                        "        \"bottomLabel\": \"Fri, Jul 20 - Sun, Jul 22\",\n" +
                        "        \"eventCount\": 3,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/mlb/new-york-yankees.jpg\",\n" +
                        "        \"targetId\": 607,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 607,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532131500000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"The Eagles\",\n" +
                        "        \"middleLabel\": \"TD Garden - Boston, MA\",\n" +
                        "        \"bottomLabel\": \"Fri, Jul 20 - Sat, Jul 21\",\n" +
                        "        \"eventCount\": 2,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/eagles.jpg\",\n" +
                        "        \"targetId\": 2533,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 2533,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532134800000,\n" +
                        "        \"rank\": 330\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Monsta X\",\n" +
                        "        \"middleLabel\": \"Rosemont Theatre - Rosemont, IL\",\n" +
                        "        \"bottomLabel\": \"Fri, Jul 20, 8:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"http://a.vsstatic.com/mobile/app/category/rap.jpg\",\n" +
                        "        \"targetId\": 2641856,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 58439,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532134800000,\n" +
                        "        \"rank\": 310\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Kenny Chesney\",\n" +
                        "        \"middleLabel\": \"Busch Stadium - Saint Louis, MO\",\n" +
                        "        \"bottomLabel\": \"Sat, Jul 21, 5:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/kenny-chesney.jpg\",\n" +
                        "        \"targetId\": 2499877,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 1440,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532210400000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Journey\",\n" +
                        "        \"middleLabel\": \"Coors Field - Denver, CO\",\n" +
                        "        \"bottomLabel\": \"Sat, Jul 21, 6:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/journey.jpg\",\n" +
                        "        \"targetId\": 2590991,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 422,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532214000000,\n" +
                        "        \"rank\": 330\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Imagine Dragons\",\n" +
                        "        \"middleLabel\": \"L.A. Forum - Inglewood, CA\",\n" +
                        "        \"bottomLabel\": \"Sat, Jul 21 - Sun, Jul 22\",\n" +
                        "        \"eventCount\": 2,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/imagine-dragons.jpg\",\n" +
                        "        \"targetId\": 30345,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 30345,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532217600000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Shania Twain\",\n" +
                        "        \"middleLabel\": \"Bridgestone Arena - Nashville, TN\",\n" +
                        "        \"bottomLabel\": \"Sat, Jul 21, 7:30PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/shania-twain.jpg\",\n" +
                        "        \"targetId\": 2539198,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 781,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532219400000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"International Champions Cup\",\n" +
                        "        \"middleLabel\": \"Bank of America Stadium - Charlotte, NC\",\n" +
                        "        \"bottomLabel\": \"Sun, Jul 22, 4:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/sports/international-champions-cup.jpg\",\n" +
                        "        \"targetId\": 2686243,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 32523,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532293200000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Britney Spears\",\n" +
                        "        \"middleLabel\": \"Radio City Music Hall - New York, NY\",\n" +
                        "        \"bottomLabel\": \"Mon, Jul 23 - Tue, Jul 24\",\n" +
                        "        \"eventCount\": 2,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/britney-spears.jpg\",\n" +
                        "        \"targetId\": 118,\n" +
                        "        \"targetType\": \"PERFORMER\",\n" +
                        "        \"entityId\": 118,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532394000000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Luke Bryan\",\n" +
                        "        \"middleLabel\": \"Harveys Outdoor Arena - Lake Tahoe NV - Stateline, NV\",\n" +
                        "        \"bottomLabel\": \"Wed, Jul 25, 7:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/luke-bryan.jpg\",\n" +
                        "        \"targetId\": 2634629,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 10202,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532563200000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Beyonce\",\n" +
                        "        \"middleLabel\": \"FirstEnergy Stadium Cleveland - Cleveland, OH\",\n" +
                        "        \"bottomLabel\": \"Wed, Jul 25, 7:30PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/beyonce.jpg\",\n" +
                        "        \"targetId\": 2650076,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 4987,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532565000000,\n" +
                        "        \"rank\": 330\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Eric Church\",\n" +
                        "        \"middleLabel\": \"Cheyenne Frontier Days - Cheyenne, WY\",\n" +
                        "        \"bottomLabel\": \"Wed, Jul 25, 8:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/eric-church.jpg\",\n" +
                        "        \"targetId\": 2555811,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 9420,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532566800000,\n" +
                        "        \"rank\": 310\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Foo Fighters\",\n" +
                        "        \"middleLabel\": \"Blossom Music Center - Cuyahoga Falls, OH\",\n" +
                        "        \"bottomLabel\": \"Wed, Jul 25, 8:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/foo-fighters.jpg\",\n" +
                        "        \"targetId\": 2502959,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 991,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532566800000,\n" +
                        "        \"rank\": 320\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"topLabel\": \"Bruno Mars\",\n" +
                        "        \"middleLabel\": \"Park Theater at Park MGM - Las Vegas, NV\",\n" +
                        "        \"bottomLabel\": \"Wed, Jul 25, 9:00PM\",\n" +
                        "        \"eventCount\": 1,\n" +
                        "        \"image\": \"https://a.vsstatic.com/mobile/app/concerts/bruno-mars.jpg\",\n" +
                        "        \"targetId\": 2671364,\n" +
                        "        \"targetType\": \"EVENT\",\n" +
                        "        \"entityId\": 24388,\n" +
                        "        \"entityType\": \"PERFORMER\",\n" +
                        "        \"startDate\": 1532570400000,\n" +
                        "        \"rank\": 330\n" +
                        "    }\n" +
                        "]",
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
    }
}
