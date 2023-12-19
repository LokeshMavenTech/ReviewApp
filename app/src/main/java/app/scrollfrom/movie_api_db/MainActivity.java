package app.scrollfrom.movie_api_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.scrollfrom.movie_api_db.Adapters.HomeRecyclerAdapters;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bnView;
SearchFragment btnSearch;

HomeRecyclerAdapters adapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnView=findViewById(R.id.bnView);
        btnSearch=(SearchFragment) getSupportFragmentManager().findFragmentById(R.id.btnSearch);



        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.popular) {
                 loadFrag(new PopularFragment(),true);

                } else  if (id == R.id.search) {
                      loadFrag(new SearchFragment(),false);
                }

                return true;
        }

        });

        bnView.setSelectedItemId(R.id.popular);
    }
    public void loadFrag(Fragment fragment,boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag)
        ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container,fragment);
        ft.commit();
    }
}