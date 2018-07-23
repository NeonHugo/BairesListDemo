package br.com.nmsystems.baireslistdemo.adapter;

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
import br.com.nmsystems.baireslistdemo.util.HMAux;
import br.com.nmsystems.baireslistdemo.util.ToolBox;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    public static final String TYPE = "type";

    public static final int DEFAULT = 1;
    public static final int SECOUND_OPTION = 2;

    private List<HMAux> data;
    private ArrayList<HMAux> data_filtered;

    private ValueFilter valueFilter;

    private RequestManager mGlide;

    public CardAdapter(List<HMAux> data, RequestManager mGlide) {
        this.data = data;
        this.data_filtered = new ArrayList<>();
        this.data_filtered.addAll(data);
        //
        this.mGlide = mGlide;
        //
        getFilter();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewModel;

        switch (viewType) {
            case DEFAULT:
                viewModel = inflater.inflate(R.layout.cell, parent, false);
                viewHolder = new DefaultVH(viewModel);
                break;
            case SECOUND_OPTION:
            default:
                viewModel = inflater.inflate(R.layout.cell, parent, false);
                viewHolder = new DefaultVH(viewModel);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case DEFAULT:
                processDefault(holder, position);
                break;
            case SECOUND_OPTION:
                break;
            default:
                processDefault(holder, position);
                break;
        }
    }

    private void processDefault(RecyclerView.ViewHolder holder, int position) {
        HMAux item = data_filtered.get(position);
        //
        DefaultVH defaultVH = (DefaultVH) holder;
        //
        defaultVH.mLove.setOnCheckedChangeListener(null);
        defaultVH.mLove.setTag(position);
        if (item.get("love").equals("1")){
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
                if (checked){
                    mPosAux.put("love", "1");
                } else {
                    mPosAux.put("love", "0");
                }
            }
        });
        //
        mGlide.load(item.get("image"))
                .into(defaultVH.mImage);

        defaultVH.mTopLabel.setText(item.get("toplabel"));
        defaultVH.mMiddleLabel.setText(ToolBox.getSafeSubstring(ToolBox.getBreakNewLine(item.get("middlelabel")), 30));
        defaultVH.mBottomLabel.setText(item.get("bottomlabel"));
    }

    @Override
    public int getItemViewType(int position) {
        HMAux item = data_filtered.get(position);

        try {
            switch (item.get(TYPE)) {
                case "1":
                    return DEFAULT;
                case "2":
                    return SECOUND_OPTION;
                default:
                    return DEFAULT;
            }
        } catch (Exception e) {
            return DEFAULT;
        }
    }

    @Override
    public int getItemCount() {
        return data_filtered.size();
    }

    /**
     * Default ViewHolder
     */
    protected class DefaultVH extends RecyclerView.ViewHolder {
        CheckBox mLove;
        ImageView mImage;
        TextView mTopLabel;
        TextView mMiddleLabel;
        TextView mBottomLabel;

        public DefaultVH(View itemView) {
            super(itemView);

            mLove = itemView.findViewById(R.id.cell_love);
            mImage = itemView.findViewById(R.id.cell_image);
            mTopLabel = itemView.findViewById(R.id.cell_toplabel);
            mMiddleLabel = itemView.findViewById(R.id.cell_middellabel);
            mBottomLabel = itemView.findViewById(R.id.cell_bottomlabel);
        }
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            try {
                if (constraint != null && constraint.length() > 0) {
                    ArrayList<HMAux> filterList = new ArrayList<HMAux>();
                    constraint = ToolBox.AccentMapper(constraint.toString());
                    //
                    for (int i = 0; i < data.size(); i++) {
                        String mKey_S_1 = ToolBox.AccentMapper(data.get(i).get("toplabel").toLowerCase());
                        String mKey_S_2 = ToolBox.AccentMapper(data.get(i).get("middlelabel").toLowerCase());
                        if (mKey_S_1.contains(constraint.toString().toLowerCase()) ||
                                mKey_S_2.contains(constraint.toString().toLowerCase())
                                ) {

                            filterList.add(data.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = data.size();
                    results.values = data;
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
