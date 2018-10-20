package pt.ipleiria.ppb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.recyclerView.LineAdapter_game;

public class MainActivity extends AppCompatActivity {

    private SingletonPPB PPB;
    private RecyclerView recyclerView;
    private LineAdapter_game mAdapter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_addGame:

                    PPB = SingletonPPB.getInstance();
                    Game game1 = new Game("ds","dsds", "er",12);
                    Game game2 = new Game("easd","sdfgg", "sdfsdf23",15);
                    Game game3 = new Game("cc","dsgfdsds", "234",16);
                    PPB.getGames().add(game1);
                    PPB.getGames().add(game2);
                    PPB.getGames().add(game3);
                    mAdapter.updateList(game1);

                    return true;
                case R.id.navigation_search:
                    goGame();
                    return true;
                case R.id.navigation_share:
                    goTask();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        recyclerView = findViewById(R.id.recycler_view);
        setupRecycler();

        PPB = SingletonPPB.getInstance();
        Game game1 = new Game("ds","dsds", "er",12);
        Game game2 = new Game("easd","sdfgg", "sdfsdf23",15);
        Game game3 = new Game("cc","dsgfdsds", "234",16);
        PPB.getGames().add(game1);
        PPB.getGames().add(game2);
        PPB.getGames().add(game3);
        mAdapter.updateList(game1);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.updateFullList();
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter_game(new ArrayList<>(0));
        recyclerView.setAdapter(mAdapter);


        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    private void goGame(){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
    private void goTask(){
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(intent);
    }
    private void goSearch(){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

}

