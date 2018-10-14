package pt.ipleiria.ppb.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pt.ipleiria.ppb.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView gameTitle;
    public ImageButton imageBtnDelete;


    public LineHolder(@NonNull View itemView) {
        super(itemView);

        gameTitle = (TextView) itemView.findViewById(R.id.gameTitle);
        imageBtnDelete = (ImageButton) itemView.findViewById(R.id.imageBtnDelete);

    }
}
