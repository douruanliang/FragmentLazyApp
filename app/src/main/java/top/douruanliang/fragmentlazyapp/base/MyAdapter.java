package top.douruanliang.fragmentlazyapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import top.douruanliang.fragmentlazyapp.R;


/**
 * 作者：dourl on 2018/5/27 20:45
 * 邮箱：douruanliang@sina.com
 */
public class MyAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;

    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    /**
     * 将数据绑到ViewHolde里面
     * @param holder
     * @param position
     *
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       ViewHolder vh =(ViewHolder)holder;
        vh.Title.setText((String) listItem.get(position).get("ItemTitle"));
        vh.Text.setText((String) listItem.get(position).get("ItemText"));
    }


    /**
     * 把ViewHodler榜定到item 布局上
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_cell_layout,null));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Title, Text;
        public ViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Itemtitle);
            Text = itemView.findViewById(R.id.Itemtext);
        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }
    }
}
