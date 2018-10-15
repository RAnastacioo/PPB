package pt.ipleiria.ppb.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pt.ipleiria.ppb.R;

class LineHolder_task extends RecyclerView.ViewHolder {

    public TextView taskTitle,taskDescription;
    public ImageButton imageBtnDelete;

   


    public LineHolder_task(@NonNull View itemView) {
        super(itemView);

        taskTitle = (TextView) itemView.findViewById(R.id.tasktitle);
        taskDescription = itemView.findViewById(R.id.taskdescription);
        imageBtnDelete = (ImageButton) itemView.findViewById(R.id.imageBtnDelete);
        

    }
}
