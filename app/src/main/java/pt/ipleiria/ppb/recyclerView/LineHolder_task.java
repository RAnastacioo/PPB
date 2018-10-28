package pt.ipleiria.ppb.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pt.ipleiria.ppb.R;

class LineHolder_task extends RecyclerView.ViewHolder {

    public TextView taskTitle, taskDescription, taskOrder;

    public LineHolder_task(@NonNull View itemView) {
        super(itemView);

        taskTitle = (TextView) itemView.findViewById(R.id.tasktitle);
        taskDescription = itemView.findViewById(R.id.taskdescription);
        taskOrder = itemView.findViewById(R.id.taskorder);
    }
}
