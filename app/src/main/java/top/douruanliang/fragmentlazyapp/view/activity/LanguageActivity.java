package top.douruanliang.fragmentlazyapp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.douruanliang.fragmentlazyapp.R;

/**
 * author: dourl
 * created on: 2018/9/5 上午11:44
 * description:
 */
public class LanguageActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;
    private CheckedItemAdapter mCheckedItemAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selecte);
        ButterKnife.bind(this);

        final String[] codes = getResources().getStringArray(R.array.language_code);
        String[] items = getResources().getStringArray(R.array.language_list);

        mCheckedItemAdapter = new CheckedItemAdapter(this,R.layout.item_language,R.id.tv_langguage,items);
        mListView.setAdapter(mCheckedItemAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {


        public CheckedItemAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull CharSequence[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        private int mSelectPosition = -1;

        public void setSelection(int p) {
            mSelectPosition = p;
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = super.getView(position, convertView, parent);
            return v;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
