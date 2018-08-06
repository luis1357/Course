package com.example.admin.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    public List<String> m_item;
    private List<String> m_path;
    public ArrayList<Integer> m_selectedItem;

    Context m_context;
    Boolean m_isRoot;

    class ViewHolder
    {
        CheckBox m_cbChek;
        ImageView m_ivIcon;
        TextView m_tvFileName;
        TextView m_tvDate;
    }

    private int setFileImageType(File m_file)
    {
        int m_lastIndex = m_file.getAbsolutePath().lastIndexOf(".");
        String m_filePath = m_file.getAbsolutePath();

        if (m_file.isDirectory())
        {
            return R.drawable.folder_open;
        }
        else
        {
            if (m_filePath.substring(m_lastIndex).equalsIgnoreCase(".png"))
            {
                return R.drawable.image;
            }
            else if (m_filePath.substring(m_lastIndex).equalsIgnoreCase(".jpg"))
            {
                return R.drawable.image;
            }
            else
            {
                return R.drawable.file;
            }
        }
    }

    String getLastDate(int p_pos)
    {
        File m_file = new File(m_path.get(p_pos));
        SimpleDateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return m_dateFormat.format(m_file.lastModified());
    }

    public ListAdapter(Context p_context, List<String> p_item,
                       List<String> p_path, Boolean p_isRoot)
    {
        m_context = p_context;
        m_item = p_item;
        m_path = p_path;
        m_selectedItem = new ArrayList<Integer>();
        m_isRoot = p_isRoot;
    }

    @Override
    public int getCount()
    {
        return m_item.size();
    }

    @Override
    public Object getItem(int position)
    {
        return m_item.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int p_position, View p_convertView,
                        ViewGroup p_parent)
    {
        View m_View = null;
        ViewHolder m_viewHolder = null;

        if (p_convertView == null)
        {
            LayoutInflater m_inflater = LayoutInflater.from(m_context);
            m_View = m_inflater.inflate(R.layout.file_item, null);

            m_viewHolder = new ViewHolder();

            m_viewHolder.m_tvFileName = (TextView) m_View.findViewById(R.id.lr_tvFileName);
            m_viewHolder.m_tvDate = (TextView) m_View.findViewById(R.id.lr_tvDate);
            m_viewHolder.m_ivIcon = (ImageView) m_View.findViewById(R.id.lr_ivFileIcon);
            m_viewHolder.m_cbChek = (CheckBox) m_View.findViewById(R.id.lr_cbCheck);

            m_View.setTag(m_viewHolder);
        }
        else
        {
            m_View = p_convertView;
            m_viewHolder = ((ViewHolder) m_View.getTag());
        }

        if (!m_isRoot && p_position == 0)
        {
            m_viewHolder.m_cbChek.setVisibility(View.INVISIBLE);
        }

        m_viewHolder.m_tvFileName.setText(m_item.get(p_position));
        m_viewHolder.m_ivIcon.setImageResource(setFileImageType(new File(m_path.get(p_position))));
        m_viewHolder.m_tvDate.setText(getLastDate(p_position));
        m_viewHolder.m_cbChek
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                    {
                        if (b)
                        {
                            m_selectedItem.add(p_position);
                        }
                        else
                        {
                            m_selectedItem.remove(m_selectedItem.indexOf(p_position));
                        }
                    }
                });

        return m_View;
    }


}
