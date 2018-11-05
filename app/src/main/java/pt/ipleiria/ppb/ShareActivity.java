package pt.ipleiria.ppb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.recyclerView.LineAdapter_game_Share;

public class ShareActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LineAdapter_game_Share mAdapter;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icon);


        recyclerView = findViewById(R.id.recycler_view);
        setupRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.upbar, menu);
        return true;
    }

    public void onClick_action_return(MenuItem item) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.updateFullList();
    }

    private void setupRecycler() {
        //RecyclerView  recyclerView1 = findViewById(R.id.recycler_view);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter_game_Share(new ArrayList<>(0));
        recyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
    @Override
    protected void onPause() {
        super.onPause();

        for (Game g : SingletonPPB.getInstance().getGames()) {
            if (g.isSelected()) {
                g.setSelected(false);
            }
        }

    }

    public void onClickbtn_share(View view) {
        ArrayList<Game> toShareGames = new ArrayList<>();
        for (Game g : SingletonPPB.getInstance().getGames()) {
            if (g.isSelected()) {
                g.setSelected(false);
                toShareGames.add(g);
            }
        }
        Gson gson = new Gson();
        String toShareGamesJson = gson.toJson(toShareGames);
//        if (!toShareGames.isEmpty()) {
//            try {
//                FileOutputStream fileOutputStream =
//                        openFileOutput("sharedGames.bin", Context.MODE_PRIVATE);
//                ObjectOutputStream objectOutputStream =
//                        new ObjectOutputStream(fileOutputStream);
//                objectOutputStream.writeObject(toShareGames);
//                objectOutputStream.close();
//                fileOutputStream.close();
//                Toast.makeText(ShareActivity.this, "Game save to internal storage.", Toast.LENGTH_LONG).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(ShareActivity.this, "Could not write Game to internal storage.", Toast.LENGTH_LONG).show();
//            }
//
//            mAdapter.notifyDataSetChanged();
//        } else {
//            Toast.makeText(ShareActivity.this, "You must select at least one game to share!", Toast.LENGTH_LONG).show();
//        }
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, toShareGamesJson);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
