package com.example.neeru.architecturecomponent.ui.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.neeru.architecturecomponent.R;
import com.example.neeru.architecturecomponent.data.remote.model.ResultsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.neeru.architecturecomponent.utils.Helper.makeTextViewResizable;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<ResultsBean> movieResponseList;
    private Context mContext;


    public void addItems(List<ResultsBean> movieList) {
        movieResponseList.addAll(movieList);
        notifyDataSetChanged();
    }

    public MoviesAdapter(List<ResultsBean> movieResponseList) {
        this.movieResponseList = movieResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieTitle.setText(movieResponseList.get(position).getTitle());
        holder.movieDiscription.setText(movieResponseList.get(position).getOverview());
        makeTextViewResizable(holder.movieDiscription, 3, "See More", true);
        holder.ratingBar.setRating((float) (movieResponseList.get(position).getVote_average() / 2));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return movieResponseList == null ? 0 : movieResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_discription)
        TextView movieDiscription;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
