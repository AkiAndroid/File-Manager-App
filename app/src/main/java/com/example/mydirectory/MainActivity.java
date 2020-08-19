package com.example.mydirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> fileList=new ArrayList<String>();
    public  ListView myDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDirectory= (ListView) findViewById(R.id.myDirectory);


        String[] permissionArrays = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int REQUEST_CODE = 101;

        requestPermissions(permissionArrays,REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean openActivityOnce = true;
        boolean openDialogOnce = true;
        if (requestCode == 101 ) {
            boolean isPermitted = false;
            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];

                isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }

            if (isPermitted) {
                File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                ListDir(root);

                }else {
                    finish();
                }
            }

    }

    void ListDir(File f){
        File[] files = f.listFiles();
        fileList.clear();
        if (files != null) {
            for (File file : files) {
                fileList.add(file.getPath());
            }
        }

        dnameAdapter directoryList=new dnameAdapter(this,fileList);
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
                    Arrow.setImageDrawable(getDrawable(R.drawable.arrow_down));
                }
                else
                {
                    listView.setVisibility(View.GONE);
                    Arrow.setImageDrawable(getDrawable(R.drawable.arrow_right));
                }
            }
        });
    }
    }








