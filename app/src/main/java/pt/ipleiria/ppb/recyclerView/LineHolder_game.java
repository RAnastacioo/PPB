package pt.ipleiria.ppb.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pt.ipleiria.ppb.R;

public class LineHolder_game extends RecyclerView.ViewHolder {

    public TextView gameTitle, gameDescription;
    public ImageButton imageBtnDelete,imageBtnEdit;


    public LineHolder_game(@NonNull View itemView) {
        super(itemView);

       gameTitle = (TextView) itemView.findViewById(R.id.gametitle);
       gameDescription = (TextView) itemView.findViewById(R.id.gamedescription);
       imageBtnDelete = (ImageButton) itemView.findViewById(R.id.imageBtnDelete);
       imageBtnEdit =itemView.findViewById(R.id.imageBtnEdit);


    }
}
