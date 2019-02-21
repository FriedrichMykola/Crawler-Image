package com.example.business.friedrich.kuzan.crawlerimage.ui.show_data;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.business.friedrich.kuzan.crawlerimage.R;
import com.example.business.friedrich.kuzan.crawlerimage.model.ParsedData;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<ParsedData> mParsedData;

    public RecyclerViewAdapter() {
        this.mParsedData = new ArrayList<>();
    }

    public RecyclerViewAdapter(ArrayList<ParsedData> mParsedData) {
        this.mParsedData = mParsedData;
    }

    public void setArrayList(ArrayList<ParsedData> parsedData) {
        mParsedData.clear();
        mParsedData.addAll(parsedData);
        notifyDataSetChanged();
    }

    public ArrayList<ParsedData> getmParsedData() {
        return mParsedData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                    .inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ParsedData parsedData = mParsedData.get(i);
        String item = "Id: " + parsedData.getmId()
                        + "\nURL: " + parsedData.getmURL()
                        + "\nКількість тегів: " + parsedData.getmNumberTags()
                        + "\nЧас: " + parsedData.getmTime() + "c.";
        viewHolder.mTextView.setText(item);
        viewHolder.mTextView.setOnClickListener(v -> {
            viewHolder.goToWebsite(mParsedData.get(i).mURL);
        });
    }

    @Override
    public int getItemCount() {
        return mParsedData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.text_item);
        }

        public void goToWebsite(String url) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            itemView.getContext().startActivity(browserIntent);
        }
    }
}
