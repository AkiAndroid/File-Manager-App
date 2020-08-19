package com.example.mydirectory;

import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class dnameAdapter extends BaseAdapter {


    private Context mContext;

    public dnameAdapter(Context mContext, List<String> dnames) {
        this.mContext = mContext;
        this.dnames = dnames;
    }

    private List<String> dnames;

    @Override
    public int getCount() {
        return dnames.size();
    }

    @Override
    public Object getItem(int i) {
        return dnames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        List<String> fileList=new ArrayList<String>();


        View view1= View.inflate(mContext,R.layout.listviewexpandable,null);
        TextView dname= view1.findViewById(R.id.dNames);

        ImageView arrow = view1.findViewById(R.id.arrow);
        final ListView myDirectory= (ListView) view1.findViewById(R.id.dNameList);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myDirectory.getVisibility() == View.VISIBLE)
                {
                    myDirectory.setVisibility(View.GONE);
                    ((ImageView)view).setImageDrawable(mContext.getDrawable(R.drawable.arrow_right));
                }
            }
        });

        dname.setText(dnames.get(i));

        File[] files = new File(dnames.get(i)).listFiles();
        fileList.clear();
        if (files != null) {
            for (File file : files) {
                fileList.add(file.getPath());
            }
        }



        dnameAdapter directoryList=new dnameAdapter(mContext,fileList);
        myDirectory.setAdapter(directoryList);

        myDirectory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("sdfhks","otha");



                ListView listView = view.findViewById(R.id.dNameList);
                ImageView Arrow = view.findViewById(R.id.arrow);
                if (listView.getVisibility() == View.GONE)
                {
                    listView.setVisibility(View.VISIBLE);
                    Arrow.setImageDrawable(mContext.getDrawable(R.drawable.arrow_down));
                }
                else
                {
                    listView.setVisibility(View.GONE);
                    Arrow.setImageDrawable(mContext.getDrawable(R.drawable.arrow_right));
                }
            }
        });

        return view1;


    }
}
