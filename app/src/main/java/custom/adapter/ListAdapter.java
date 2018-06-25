package custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yurii.alieksieiev.learenglish.R;

import java.util.ArrayList;

import word.Word;

public class ListAdapter extends BaseAdapter
{
    private ArrayList<Word> arrayList;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, ArrayList<Word> arrayList)
    {
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Word getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if(v == null)
            v = layoutInflater.inflate(R.layout.item_word,null);

        TextView word = v.findViewById(R.id.item_tv_word);
        TextView translation = v.findViewById(R.id.item_tv_translation);

        word.setText(getItem(position).getWord());
        translation.setText(getItem(position).getTranslation());

        return v;
    }
}
