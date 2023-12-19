package app.scrollfrom.movie_api_db;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.scrollfrom.movie_api_db.Adapters.HomeRecyclerAdapters;
import app.scrollfrom.movie_api_db.Listeners.OnMovieClickListener;
import app.scrollfrom.movie_api_db.Listeners.OnSearchApiListeners;
import app.scrollfrom.movie_api_db.Models.SearchApiResponse;
import app.scrollfrom.movie_api_db.Models.SearchArrayObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements OnMovieClickListener {
    private RecyclerView recyclerView;
    private SearchView searchView;
    RequestManager manager;
    ProgressDialog dialog;
    HomeRecyclerAdapters adapters;
    public SearchFragment() {

    }
    ///add by me
    public void onMovieClick(SearchArrayObject movie) {
        // Handle movie click event
        // You can implement this method to perform some action when a movie is clicked
    }
//////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.btnSearch);
        ///add by me
        dialog=new ProgressDialog(getContext());
        manager=new RequestManager(getContext());

        // Set up RecyclerView and adapter as needed

        // Set up search functionality
        setupSearch();

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Please Wait...");
                dialog.show();
                manager.searchMovies(listeners,query);
                // Handle the search query submission (e.g., perform a search)
                //performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle the search query text change (e.g., update search suggestions)
                // You can choose to perform a search here as well for real-time results
                return true;
            }
        });
    }
    ////add by me
private final OnSearchApiListeners listeners=new OnSearchApiListeners() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(requireContext(), "No data avalabe", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        private void showResult(SearchApiResponse response) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
            adapters =new HomeRecyclerAdapters(getContext(),response.getResults(),SearchFragment.this);
            recyclerView.setAdapter(adapters);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(requireContext(), "An Error Occurred!!", Toast.LENGTH_SHORT).show();
        }
    };
    ///////////////////
    private void performSearch(String query) {
        // Implement your search logic here
        // Update the RecyclerView with search results
        // For example:
        Toast.makeText(requireContext(), "Searching for: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClicked(String id) {
        Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show();

    }
}
