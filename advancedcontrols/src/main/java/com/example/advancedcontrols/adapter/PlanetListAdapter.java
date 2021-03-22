package com.example.advancedcontrols.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.advancedcontrols.R;
import com.example.advancedcontrols.bean.Planet;
import java.util.ArrayList;

/**
 * @ProjectName : DataStorage
 * @Author : liguangyao
 * @Time : 2021/3/22
 * @Description : 行星的适配器
 */
public class PlanetListAdapter extends BaseAdapter implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private Context mContext;
    private ArrayList<Planet> mPlanetList;

    public PlanetListAdapter(Context context, ArrayList<Planet> planetList) {
         mContext = context;
         mPlanetList = planetList;
    }

    //获取列表项的个数
    @Override
    public int getCount() {
        return mPlanetList.size();
    }

    //获取列表项的数据
    @Override
    public Object getItem(int position) {
        return mPlanetList.get(position);
    }

    //获取列表项的编号
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获取指定位置的列表项视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list , null);
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_desc = convertView.findViewById(R.id.tv_desc);

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        Planet planet = mPlanetList.get(position);
        holder.iv_icon.setImageResource(planet.image);
        holder.tv_name.setText(planet.name);
        holder.tv_desc.setText(planet.desc);

        return convertView;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String s = String.format("你点击了第%d个行星，它的名字是%s" , position+1 , mPlanetList.get(position).name);
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String s = String.format("你点击了第%d个行星，它的名字是%s" , position+1 , mPlanetList.get(position).name);
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
    }

    public final class ViewHolder{

        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
    }
}
