package pt.ipleiria.ppb;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.recyclerView.LineAdapter_game_Share;

public class ShareActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LineAdapter_game_Share mAdapter;
    private Context mContext = ShareActivity.this;
    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icon);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
            }
        }

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
        if (toShareGames.isEmpty()) {
            Snackbar.make(view, "It is necessary to select some game", Snackbar.LENGTH_LONG).show();

        } else {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls();
            Gson gson = builder.create();
            String toShareGamesJson = gson.toJson(toShareGames);

            String fileName = "toShareGamesJson.txt";
            File textFile = new File(Environment.getExternalStorageDirectory(), fileName);
            writeFile(toShareGamesJson, textFile);
            String path = textFile.getAbsolutePath();


            if (!path.isEmpty()) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Sharing games in Json \n PPB-PEDDY PAPER BUILDER 2018");
                sendIntent.setType("text/*");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "PPB-GameShare");
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                sendIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(ShareActivity.this,getString(R.string.file_provider_authority),textFile));  // android 8
                //sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + path));
                startActivity(Intent.createChooser(sendIntent, "Send email..."));
            }
        }
    }

    private boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("State", "Yes, it is writable!");
            return true;
        } else {
            return false;
        }
    }

    public void writeFile(String data, File textFile) {
        if (isExternalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String path = textFile.getAbsolutePath();

            try {
                FileOutputStream fos = new FileOutputStream(textFile);
                fos.write(data.getBytes());
                fos.close();
                Snackbar.make(getCurrentFocus(), "File Saved.", Snackbar.LENGTH_SHORT).show();
               // return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Snackbar.make(getCurrentFocus(), "Cannot Write to External Storage.", Snackbar.LENGTH_SHORT).show();
        }
        //return "";
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
