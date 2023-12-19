package app.scrollfrom.movie_api_db.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.scrollfrom.movie_api_db.Listeners.OnMovieClickListener;
import app.scrollfrom.movie_api_db.Models.SearchArrayObject;
import app.scrollfrom.movie_api_db.R;

public class HomeRecyclerAdapters extends RecyclerView.Adapter<HomeViewHolder>{
    private OnMovieClickListener listener;
    Context context;
    List<SearchArrayObject> list;
    //OnMovieClickListener listener;
    private List<String> titles; // Assuming titles are of type String, adjust accordingly

    public HomeRecyclerAdapters(Context context, List<String> titles) {
        this.context = context;
        this.titles = titles;
    }

    // Other methods of your adapter


    public HomeRecyclerAdapters(Context context, List<SearchArrayObject> list, OnMovieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movies_list,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
holder.textView_movie.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getPoster_path()).into(holder.imageView_poster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class HomeViewHolder extends RecyclerView.ViewHolder {
     ImageView imageView_poster;
     TextView textView_movie;
     CardView home_container;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
      imageView_poster = itemView.findViewById(R.id.imageView_poster);
        textView_movie=itemView.findViewById(R.id.textView_movie);
        home_container = itemView.findViewById(R.id.home_container);
    }
}