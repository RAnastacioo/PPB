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
import android.widget.EditText;

import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.model.Task;

public class TaskActivity extends AppCompatActivity {

    private SingletonPPB PPB;
    private Game gameAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_icon);

        PPB = SingletonPPB.getInstance();

        Intent i = getIntent();
            String id = i.getStringExtra("id_addTask");
            gameAddTask = PPB.containsID(id);



    }
    public void onClick_btn_add_task(View view) {

        EditText etTitle = findViewById(R.id.task_Title);
        EditText etDescription = findViewById(R.id.task_Description);
        EditText etValue = findViewById(R.id.task_value);
        EditText etOrder = findViewById(R.id.task_order);

        if (etTitle.getText().toString().isEmpty()) {
            etTitle.setError("");
        }
        if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError("");
        }
        if (etValue.getText().toString().isEmpty()) {
            etValue.setError("");
        }
        if (etOrder.getText().toString().isEmpty()) {
            etOrder.setError("");
        }
        if (!etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty() && !etValue.getText().toString().isEmpty() && !etOrder.getText().toString().isEmpty()) {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();
            String strValue = etValue.getText().toString();
            String strOrder = etOrder.getText().toString();
            int value = Integer.parseInt(strValue.trim());
            int order = Integer.parseInt(strOrder.trim());

        // criar task
        Task task = new Task(order,title,description,value);
            gameAddTask.getTasks().add(task);

        Snackbar.make(view, "Add Task Complete", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
