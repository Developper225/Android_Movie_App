/**
 * Created with IntelliJ IDEA.
 * User: Georges Amewe KASSI
 * Date: 01/04/2019
 * Time: 01:37
 */

package com.example.movie_info.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import java.util.List;
import com.example.movie_info.R;
import com.example.movie_info.adapter.MoviesAdapter;
import com.example.movie_info.model.Movie;
import com.example.movie_info.model.MoviesResponse;
import com.example.movie_info.rest.ApiClient;
import com.example.movie_info.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovies extends AppCompatActivity {

    private static final String TAG = com.example.movie_info.activity.MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "279354dca50756db7e10dec6e8b4a401";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Oups, il te faut obtenir une clé pour l'API auprès de TmDb!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.activity_list_movies, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}