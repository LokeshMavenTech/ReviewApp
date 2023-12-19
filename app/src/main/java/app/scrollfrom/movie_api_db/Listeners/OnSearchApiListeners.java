package app.scrollfrom.movie_api_db.Listeners;

import app.scrollfrom.movie_api_db.Models.SearchApiResponse;

public interface OnSearchApiListeners {
    void onResponse(SearchApiResponse response);
    void onError(String message);

}
