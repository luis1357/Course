package com.yeah.ruisu.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CelebrityListAdapter extends ArrayAdapter<Celebrity>
{
    List<Celebrity> celebrityList = new ArrayList<>();

    public CelebrityListAdapter(@NonNull Context context, int resource,
                                List<Celebrity> InCelebrityList)
    {
        super(context, resource, InCelebrityList);
        celebrityList = InCelebrityList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent)
    {
        Celebrity CelebrityTemp = celebrityList.get(position);
        ViewHolder MyVwHldr;

        if(convertView == null)
        {
            MyVwHldr = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.celebrity_list_item, null);

            MyVwHldr.Name = convertView.findViewById(R.id.tvCelebName);
            MyVwHldr.Age = convertView.findViewById(R.id.tvCelebAge);
            MyVwHldr.Weight = convertView.findViewById(R.id.tvCelebWeight);

            convertView.setTag(MyVwHldr);
        }
        else
        {
            MyVwHldr = (ViewHolder) convertView.getTag();
        }

        MyVwHldr.Name.setText(CelebrityTemp.name);
        MyVwHldr.Age.setText(String.valueOf(CelebrityTemp.age));
        MyVwHldr.Weight.setText(String.valueOf(CelebrityTemp.weight));

        return convertView;
    }

    public static class ViewHolder
    {
        TextView Name, Age, Weight;
        
    }
}
