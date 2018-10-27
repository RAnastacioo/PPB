package pt.ipleiria.ppb.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pt.ipleiria.ppb.MainActivity;
import pt.ipleiria.ppb.R;
import pt.ipleiria.ppb.SearchActivity;

public class LineHolder_game extends RecyclerView.ViewHolder {

    public TextView gameTitle, gameDescription;
    public ImageButton imageBtnDelete, imageBtnEdit;


    public LineHolder_game(@NonNull View itemView) {
        super(itemView);

        gameTitle = (TextView) itemView.findViewById(R.id.gametitle);
        gameDescription = (TextView) itemView.findViewById(R.id.gamedescription);

    }

}
