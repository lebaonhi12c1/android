package com.example.lebaonhi_dh51904155.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lebaonhi_dh51904155.ProccessClassesActivity;
import com.example.lebaonhi_dh51904155.R;
import com.example.lebaonhi_dh51904155.UpdateClassesActivity;
import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.model.classes;

import java.util.ArrayList;
import java.util.List;

public class classesAdapter extends BaseAdapter {
    Activity context;
    List<classes> list;

    public classesAdapter(Activity context, List<classes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.class_item,null);
        TextView id = view1.findViewById(R.id.id_classes_item);
        TextView name = view1.findViewById(R.id.name_classes_item);
        Button update = view1.findViewById(R.id.btn_update_item);
        Button remove = view1.findViewById(R.id.btn_remove_item);
        classes classes = list.get(i);
        id.setText(String.valueOf(classes.getId()));
        name.setText(classes.getName());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateClassesActivity.class);
                intent.putExtra("id",classes.getId());
                context.startActivity(intent);
                context.finish();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                khoadao dao = new khoadao(context);
                classes category = list.get(i);
                builder.setTitle("X??c nh???n x??a");
                builder.setMessage("B???n c?? ch???c mu???n x??a danh m???c n??y?");
                builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,int a) {

                        dao.delete(category.getId());
                        Intent intent = new Intent(context, ProccessClassesActivity.class);
                        context.startActivity(intent);
                        context.finish();
                    }
                });
                builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view1;
    }
}
