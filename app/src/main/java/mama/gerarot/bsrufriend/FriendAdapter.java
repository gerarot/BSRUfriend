package mama.gerarot.bsrufriend;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Gerarot-PC on 17/2/2560.
 */

public class FriendAdapter extends BaseAdapter{

    private Context context;
    private String[] iconStrings, nameStrings;
    private TextView textView;
    private ImageView imageView;

    public FriendAdapter(Context context, String[] iconStrings, String[] nameStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameStrings = nameStrings;
    }

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_listview, parent, false);

        //Bind Widget
        textView = (TextView) view.findViewById(R.id.textView3);
        imageView = (ImageView) view.findViewById(R.id.imageView11);

        //ShowView
        textView.setText(nameStrings[position]);
        Picasso.with(context).load(iconStrings[position]).into(imageView);

        return view;
    }
}   //Main Class
