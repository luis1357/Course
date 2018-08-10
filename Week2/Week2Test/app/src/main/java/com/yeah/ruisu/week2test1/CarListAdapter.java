package com.yeah.ruisu.week2test1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends ArrayAdapter
{
    List<Car> CarList;

    public CarListAdapter(@NonNull Context context,
                          int resource, List<Car> carList)
    {
        super(context, resource);
        CarList = carList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent)
    {
        Car CelebrityTemp = CarList.get(position);
        ViewHolder MyVwHldr;

        if(convertView == null)
        {
            MyVwHldr = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_item, null);

            MyVwHldr.CarName = convertView.findViewById(R.id.tvCarName);
            MyVwHldr.CarType = convertView.findViewById(R.id.tvCarType);
            MyVwHldr.CarYear = convertView.findViewById(R.id.tvCarYear);

            convertView.setTag(MyVwHldr);
        }
        else
        {
            MyVwHldr = (ViewHolder) convertView.getTag();
        }

        MyVwHldr.CarName.setText(CelebrityTemp.CarName);
        MyVwHldr.CarType.setText(String.valueOf(CelebrityTemp.CarType));
        MyVwHldr.CarYear.setText(String.valueOf(CelebrityTemp.CarYear));

        return convertView;
    }

    public static class ViewHolder
    {
        TextView CarName, CarType, CarYear;

    }
}
