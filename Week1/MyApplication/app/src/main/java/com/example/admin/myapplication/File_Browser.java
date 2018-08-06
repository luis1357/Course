package com.example.admin.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class File_Browser extends Activity {

    private String m_root = Environment.getExternalStorageDirectory().getPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file__browser);
    }

    //get directories and files from selected path
    public void getDirFromRoot(String p_rootPath)
    {

        ArrayList<String> m_item = new ArrayList<String>();
        Boolean m_isRoot = true;
        ArrayList<String> m_path = new ArrayList<String>();
        ArrayList<String> m_files = new ArrayList<String>();
        ArrayList<String> m_filesPath=new ArrayList<String>();
        File m_file = new File(p_rootPath);
        File[] m_filesArray = m_file.listFiles();
        String m_curDir = p_rootPath;

        if(!p_rootPath.equals(m_root))
        {
            m_item.add("../");
            m_path.add(m_file.getParent());
            m_isRoot=false;
        }

        //sorting file list in alphabetical order
        Arrays.sort(m_filesArray);
        for(int i=0; i < m_filesArray.length; i++)
        {
            File file = m_filesArray[i];
            if(file.isDirectory())
            {
                m_item.add(file.getName());
                m_path.add(file.getPath());
            }
            else
            {
                m_files.add(file.getName());
                m_filesPath.add(file.getPath());
            }
        }
        for(String m_AddFile:m_files)
        {
            m_item.add(m_AddFile);
        }
        for(String m_AddPath:m_filesPath)
        {
            m_path.add(m_AddPath);
        }
        ListAdapter m_listAdapter = new ListAdapter(this,m_item,m_path,m_isRoot);
        /*m_RootList.setAdapter(m_listAdapter);
        m_RootList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                File m_isFile=new File(m_path.get(position));
                if(m_isFile.isDirectory())
                {
                    getDirFromRoot(m_isFile.toString());
                }
                else
                {
                    Toast.makeText(RootListActivity.this, "This is File", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

}
