package cn.hudp.androiddevartnote.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.hudp.androiddevartnote.R;

/**
 * Created by HuDP on 16/5/28.
 */
public class ListAdapter extends BaseAdapter {
    protected Context mContext;
    private List<ListInfo> list;
    private LayoutInflater inflater = null;

    public ListAdapter(Activity mContext, List<ListInfo> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void onDataChange(List<ListInfo> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ListInfo info = list.get(position);
        viewHolder.tvName.setText(info.getShow());
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class clz = info.getActivityClass();
                if (clz != null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, clz);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "未找到" + info.getShow(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvName;
    }
}
