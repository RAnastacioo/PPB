package pt.ipleiria.ppb;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.model.Task;

public class TaskActivity extends AppCompatActivity {

    private SingletonPPB PPB;
    private Game game;
    private Task task;
    private boolean editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        editing = false;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icon);

        PPB = SingletonPPB.getInstance();

        Intent i = getIntent();
        EditText etTitle = findViewById(R.id.task_Title);
        EditText etDescription = findViewById(R.id.task_Description);
        EditText etValue = findViewById(R.id.task_value);
        TextView etId = findViewById(R.id.task_id);
        TextView etOrder = findViewById(R.id.task_order);
        Button btnAddtask = findViewById(R.id.btn_add_task);


        if (i.getStringExtra("id_viewTask") != null) {
            String idgame = i.getStringExtra("id_viewTask");
            task = PPB.containsIDTask(idgame);

            etTitle.setHint(task.getTitle());
            etTitle.setEnabled(false);
            etDescription.setHint(task.getDescription());
            etDescription.setEnabled(false);
            etValue.setHint("" + task.getValue());
            etValue.setEnabled(false);
            etId.setText("" + task.getId());
            etOrder.setText("" + task.getOrder());

            btnAddtask.setVisibility(View.INVISIBLE);
        }

        if (i.getStringExtra("id_addTask") != null) {
            String id = i.getStringExtra("id_addTask");
            game = PPB.containsID(id);

            TextView textid = findViewById(R.id.id);
            textid.setVisibility(View.INVISIBLE);
            TextView task_id = findViewById(R.id.task_id);
            task_id.setVisibility(View.INVISIBLE);
            TextView order = findViewById(R.id.order);
            order.setVisibility(View.INVISIBLE);
            TextView task_order = findViewById(R.id.task_order);
            task_order.setVisibility(View.INVISIBLE);
        }

        if (i.getStringExtra("id_EditTask") != null && i.getStringExtra("id_EditTaskGame") != null) {
            editing = true;
            String idgame = i.getStringExtra("id_EditTaskGame");
            game = PPB.containsID(idgame);
            String idtask = i.getStringExtra("id_EditTask");
            task = PPB.containsID(idtask, game);


            etTitle.setText(task.getTitle());
            etDescription.setText(task.getDescription());
            etValue.setText("" + task.getValue());
            etId.setText("" + task.getId());
            etOrder.setText("" + task.getOrder());

            btnAddtask.setText("Edit Task");

        }


    }

    public void onClick_btn_add_task(View view) {

        EditText etTitle = findViewById(R.id.task_Title);
        EditText etDescription = findViewById(R.id.task_Description);
        EditText etValue = findViewById(R.id.task_value);

        if (etTitle.getText().toString().isEmpty()) {
            etTitle.setError("");
        }
        if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError("");
        }
        if (etValue.getText().toString().isEmpty()) {
            etValue.setError("");
        }

        if (!etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty() && !etValue.getText().toString().isEmpty()) {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            String strValue = etValue.getText().toString();
            int value = Integer.parseInt(strValue.trim());

            if (editing && task != null) {

                task.setTitle(title);
                task.setDescription(description);
                task.setValue(value);

                Snackbar.make(view, "Edit Task Complete", Snackbar.LENGTH_LONG).show();

                editing = false;
            } else {
                // criar task
                Task task = new Task(title, description, value);
                task.setOrder(game.getTasks().size() + 1);
                game.getTasks().add(task);

                Snackbar.make(view, "Add Task Complete", Snackbar.LENGTH_LONG).show();
            }
            // Check if no view has focus:  // use remove keyboard front view
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            finish();
        }
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
}
