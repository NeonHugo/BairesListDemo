package br.com.nmsystems.baireslistdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import br.com.nmsystems.baireslistdemo.R;
import br.com.nmsystems.baireslistdemo.util.Constants;
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    /**
     * support for multiple item types
     */
    public static final String TYPE = "type";

    public static final int DEFAULT = 1;
    public static final int SECOND_OPTION = 2;

    /**
     * data is a collection of HMAux(HashMap<String,String>). Allow restriction of properties and the use of multiple data sources
     * on the same collection.
     */
    private List<HMAux> data;

    /**
     * filtered data to be used.
     */
    private ArrayList<HMAux> data_filtered;

    /**
     *
     */
    private ValueFilter valueFilter;

    /**
     * Responsible for asynchronously downloading the images from the server.
     */
    private RequestManager mGlide;

    /**
     * Flag for filtering the favorities (false = no filter / true = filter)
     */
    private boolean option = false;

    /**
     * cache for the text used in the filter
     */
    private String selection = "";

    public boolean getOption() {
        return option;
    }

    /**
     * Interface for on Item click implementation. Returns the position and de HMAux referenced by the parameter position
     */
    public interface ItemClickListener {

        void onItemClick(int position, HMAux item);
    }

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * setter for favorites filter using the cache text (selection) if available
     *
     * @param option (true = filter favorites / false = no filter.
     */
    public void setOption(boolean option) {
        this.option = option;
        //
        getFilter().filter(selection);
    }


    /**
     * Constructor defining the raw and filtered data, the request manager and the filter to be used
     *
     * @param data   lists of raw data
     * @param mGlide RequestManager for download image asynchronously
     */
    public CardAdapter(List<HMAux> data, RequestManager mGlide, Context context) {
        this.data = data;
        this.data_filtered = new ArrayList<>();
        this.data_filtered.addAll(data);
        //
        this.mGlide = mGlide;
        //
        this.context = context;
        //
        getFilter();
    }

    /**
     * Defines the viewHolder to be used. Support multiple holder in the same collection.
     *
     * @param parent
     * @param viewType
     * @return Type of holder to be used.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewModel;

        /**
         * Using only one holder but is prepared for multiples holders
         */
        switch (viewType) {
            case DEFAULT:
                viewModel = inflater.inflate(R.layout.cell, parent, false);
                viewHolder = new DefaultVH(viewModel);
                break;
            case SECOND_OPTION:
            default:
                viewModel = inflater.inflate(R.layout.cell, parent, false);
                viewHolder = new DefaultVH(viewModel);
                break;
        }

        return viewHolder;
    }

    /**
     * Binds the specific viewHolder and set its actions.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case DEFAULT:
                processDefault(holder, position);
                break;
            case SECOND_OPTION:
                break;
            default:
                processDefault(holder, position);
                break;
        }
    }

    /**
     * Binds the default ViewHolder. Reprograms onCheckedChanged on the favority checkbox
     *
     * @param holder
     * @param position
     */
    private void processDefault(RecyclerView.ViewHolder holder, int position) {
        HMAux item = data_filtered.get(position);

        DefaultVH defaultVH = (DefaultVH) holder;

        defaultVH.mLove.setOnCheckedChangeListener(null);
        defaultVH.mLove.setTag(position);

        if (item.get(Constants.LOVE).equals(context.getString(R.string.favorite_true))) {
            defaultVH.mLove.setChecked(true);
        } else {
            defaultVH.mLove.setChecked(false);
        }

        defaultVH.mLove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                int mPosition = (int) compoundButton.getTag();
                //
                HMAux mPosAux = data_filtered.get(mPosition);
                //
                if (checked) {
                    mPosAux.put(Constants.LOVE, context.getString(R.string.favorite_true));
                } else {
                    mPosAux.put(Constants.LOVE, context.getString(R.string.favorite_false));
                }
            }
        });

        mGlide.load(item.get(Constants.IMAGE))
                .into(defaultVH.mImage);

        defaultVH.mTopLabel.setText(item.get(Constants.TOPLABEL));
        defaultVH.mMiddleLabel.setText(
                ToolBox.getSafeSubstring(
                        ToolBox.getBreakNewLine(item.get(Constants.MIDDLELABEL)),
                        Constants.STRING_MAX_SIZE
                )
        );
        defaultVH.mBottomLabel.setText(item.get(Constants.BOTTOMLABEL));
        defaultVH.mEventCount.setText(item.get(Constants.EVENTCOUNT) + " events >");
    }

    /**
     * Defines the Type of Holder to be used according to the parameter item reference by the parameter position
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        HMAux item = data_filtered.get(position);

        try {
            switch (Integer.parseInt(item.get(TYPE))) {
                case 1:
                    return DEFAULT;
                case 2:
                    return SECOND_OPTION;
                default:
                    return DEFAULT;
            }
        } catch (Exception e) {
            return DEFAULT;
        }
    }

    /**
     * How many rows are in my collection after the filter has been applied
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return data_filtered.size();
    }

    /**
     * Default ViewHolder. Implements onClickListener for handling item selection
     */
    protected class DefaultVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox mLove;
        ImageView mImage;
        TextView mTopLabel;
        TextView mMiddleLabel;
        TextView mBottomLabel;
        TextView mEventCount;

        public DefaultVH(View itemView) {
            super(itemView);

            mLove = itemView.findViewById(R.id.cell_love);
            mImage = itemView.findViewById(R.id.cell_image);
            mTopLabel = itemView.findViewById(R.id.cell_toplabel);
            mMiddleLabel = itemView.findViewById(R.id.cell_middellabel);
            mBottomLabel = itemView.findViewById(R.id.cell_bottomlabel);
            mEventCount = itemView.findViewById(R.id.cell_eventcount);

            mImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                HMAux item = data_filtered.get(position);

                itemClickListener.onItemClick(getAdapterPosition(), item);
            }
        }
    }

    /**
     * Initializes the filter.
     *
     * @return
     */
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

    /**
     * Filters data using the text search field value and option(favorite).
     * The Text Search is implemented on (TopLabel/MiddleLabel)
     * Option is implemented on (LOVED)
     */
    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            selection = (String) constraint;

            try {
                ArrayList<HMAux> filterList = new ArrayList<HMAux>();

                if ((constraint != null && constraint.length() > 0)) {
                    constraint = ToolBox.AccentMapper(constraint.toString());

                    for (int i = 0; i < data.size(); i++) {
                        String mKey_S_1 = ToolBox.AccentMapper(data.get(i).get(Constants.TOPLABEL).toLowerCase());
                        String mKey_S_2 = ToolBox.AccentMapper(data.get(i).get(Constants.MIDDLELABEL).toLowerCase());
                        String mKey_Love = data.get(i).get(Constants.LOVE);

                        if (mKey_S_1.contains(constraint.toString().toLowerCase()) ||
                                mKey_S_2.contains(constraint.toString().toLowerCase())
                                ) {
                            if (option) {
                                if (mKey_Love.equals(context.getString(R.string.favorite_true))) {
                                    filterList.add(data.get(i));
                                }

                            } else {
                                filterList.add(data.get(i));
                            }
                        } else {
                        }
                    }

                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    if (option) {
                        for (int i = 0; i < data.size(); i++) {
                            String mKey_Love = data.get(i).get(Constants.LOVE);

                            if (option && mKey_Love.equals(context.getString(R.string.favorite_true))) {
                                filterList.add(data.get(i));
                            }
                        }
                        results.count = filterList.size();
                        results.values = filterList;
                    } else {
                        results.count = data.size();
                        results.values = data;
                    }
                }
            } catch (Exception e) {
                results.count = data.size();
                results.values = data;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data_filtered = (ArrayList<HMAux>) results.values;

            notifyDataSetChanged();
        }
    }

}
