package pt.ipleiria.ppb;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.model.Task;
import pt.ipleiria.ppb.recyclerView.LineAdapter_task;


public class GameActivity extends AppCompatActivity {

    private SingletonPPB PPB;
    private RecyclerView recyclerView;
    private LineAdapter_task mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icon);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task task = new Task(1,2,"sdasd","adad",1);
                SingletonPPB.getInstance().getTasks().add(task);
                mAdapter.updateFullList();

                Snackbar.make(view, "Add Task", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        setupRecycler();
        mAdapter.updateFullList();

        View v =  findViewById(R.id.include_gametask);
        v.setVisibility(View.VISIBLE); // esconder layout include View.INVISIBLE |View.VISIBLE
        View vfab =  findViewById(R.id.add_task);
        vfab.setVisibility(View.VISIBLE);
        View vbtn =  findViewById(R.id.btn_add_game);
        vbtn.setVisibility(View.VISIBLE);


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
    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter_task(new ArrayList<>(0));
        recyclerView.setAdapter(mAdapter);


        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void onClick_btn_add_game(View view) {

         EditText etTitle = findViewById(R.id.game_Title);
         EditText etDescription = findViewById(R.id.game_Description);
         EditText etAuthor = findViewById(R.id.game_Author);
         EditText etDuration = findViewById(R.id.game_Duration);

        if (etTitle.getText().toString().isEmpty()) {
            etTitle.setError(getString(R.string.Invalid_Title));
        }
        if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError(getString(R.string.Invalid_Description));
        }
        if (etAuthor.getText().toString().isEmpty()) {
            etAuthor.setError(getString(R.string.Invalid_Author_Name));
        }
        if (etDuration.getText().toString().isEmpty()) {
            etDuration.setError(getString(R.string.Invalid_Time_Duration));
        }
        if(!etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty() && !etAuthor.getText().toString().isEmpty() && !etDuration.getText().toString().isEmpty()){
            // obter o texto do title
            String title = etTitle.getText().toString();
            // obter o texto do descricao
            String description = etDescription.getText().toString();
            // obter o texto do Author
            String Author = etAuthor.getText().toString();
            // obter o duracao minutos
            String durationText = etDuration.getText().toString();
            int duration = Integer.parseInt(durationText.trim());

            // criar game
            PPB = SingletonPPB.getInstance();
            Game game = new Game(title, description, Author, duration);
            PPB.getGames().add(game);

            // Check if no view has focus:  // use remove keyboard front view
             view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }


                Snackbar.make(view, "Add Game Complete", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

}
