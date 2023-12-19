package app.scrollfrom.movie_api_db;

import android.content.Context;
import android.widget.Toast;

import app.scrollfrom.movie_api_db.Listeners.OnSearchApiListeners;
import app.scrollfrom.movie_api_db.Models.SearchApiResponse;
import app.scrollfrom.movie_api_db.Models.Top_rated.TopRatedMovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListeners listeners,String movie_name){
        getMovies getMovies= retrofit.create(RequestManager.getMovies.class);
        Call<TopRatedMovieResponse> call= getMovies.callMovies(movie_name,"e8081252c1d8aacd3605ab700b092c38");
        call.enqueue(new Callback<TopRatedMovieResponse>() {
            @Override
            public void onResponse(Call<TopRatedMovieResponse> call, Response< TopRatedMovieResponse > response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Couldn't fetch Data!!", Toast.LENGTH_SHORT).show();
                   return;
                }


                listeners.onResponse(response.body());
                //listeners.onResponse(response.body(SearchApiResponse));
            }

            @Override
            public void onFailure(Call<TopRatedMovieResponse> call, Throwable t) {
             listeners.onError(t.getMessage());
            }
        });

    }
    public interface getMovies{
        @Headers({
                "Accept: application/json",
                "api_key:e8081252c1d8aacd3605ab700b092c38"

        })
        @GET("3/search/{movie_name}")
        Call<TopRatedMovieResponse> callMovies(
                @Path("movie_name")String movie_name,@Query("api_key") String apiKey
        );
    }
}
