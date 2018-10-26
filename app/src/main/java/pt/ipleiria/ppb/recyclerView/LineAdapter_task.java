package pt.ipleiria.ppb.recyclerView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import pt.ipleiria.ppb.R;
import pt.ipleiria.ppb.TaskActivity;
import pt.ipleiria.ppb.model.Game;
import pt.ipleiria.ppb.model.SingletonPPB;
import pt.ipleiria.ppb.model.Task;

public class LineAdapter_task extends RecyclerView.Adapter<LineHolder_task> {

    private ArrayList<Task> mTaks;

    public LineAdapter_task(ArrayList taks) {
        this.mTaks = taks;
    }

    @NonNull
    @Override
    public LineHolder_task onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LineHolder_task(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_task_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder_task lineHolder_taks, int i) {
        final int position = i;
        lineHolder_taks.taskTitle.setText(mTaks.get(i).getTitle());
        lineHolder_taks.taskDescription.setText(mTaks.get(i).getDescription());

        lineHolder_taks.imageBtnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete pos: " + position,
                        Toast.LENGTH_SHORT).show();
                removerItem(position);
            }
        });

        lineHolder_taks.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Edit : " + position ,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), TaskActivity.class);
                v.getContext().startActivity(intent);
                //putExtra
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTaks != null ? mTaks.size() : 0;
    }

    public void updateList(Task task) {
        insertItem(task);
    }

    private void insertItem(Task task) {
        mTaks.add(task);
        notifyItemInserted(getItemCount());
    }
    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        Task task = mTaks.get(position);
        notifyItemChanged(position);
    }
    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        mTaks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTaks.size());
    }


    public void updateFullList(Game game) {
            mTaks = game.getTasks();
            notifyDataSetChanged();

    }
}
