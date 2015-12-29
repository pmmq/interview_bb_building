package list.sofats.p.interview.Holder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import list.sofats.p.interview.R;

/**
 * Created by P on 29/12/2558.
 */
public class ArticleHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public TextView description;
    public LinearLayout layout;
    public ArticleHolder(View rootview){
        super(rootview);

        image = (ImageView) rootview.findViewById(R.id.articleimage);
        title = (TextView) rootview.findViewById(R.id.title_textview);
        description = (TextView) rootview.findViewById(R.id.short_description_textview);
        layout = (LinearLayout) rootview.findViewById(R.id.layout);
    }
}
