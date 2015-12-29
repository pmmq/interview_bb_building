package list.sofats.p.interview.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.security.Key;

import list.sofats.p.interview.Activity.ArticleActivity;
import list.sofats.p.interview.Activity.MapActivity;
import list.sofats.p.interview.Async.ArticleAsync;
import list.sofats.p.interview.Models.ArticleDetail;
import list.sofats.p.interview.Models.ArticleList;
import list.sofats.p.interview.R;
import list.sofats.p.interview.Util.Keys;

/**
 * Created by P on 29/12/2558.
 */
public class ArticleDetailFragment extends Fragment {

    public String id;
    Toolbar toolbar;
    TextView title;
    ImageView image;
    TextView content;
    Button mapbutton;
    View rootview;
    static ArticleDetailFragment instance;
    public static  ArticleDetailFragment getInstance(String id){
        if(instance == null){
            instance = new ArticleDetailFragment();
        }
        instance.id = id;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.article_detail_fragment , container,false);
        title = (TextView) rootview.findViewById(R.id.title_textview);
        content = (TextView) rootview.findViewById(R.id.content_textview);
        image = (ImageView) rootview.findViewById(R.id.article_image_imageview);
        toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        mapbutton = (Button) rootview.findViewById(R.id.map_button);

        ((ArticleActivity) getActivity()).setSupportActionBar(toolbar);
        //((ArticleActivity) getActivity()).setActionBar(toolbar);
        ArticleAsync.getArticleData(new Response.Listener<ArticleDetail>() {
            @Override
            public void onResponse(final ArticleDetail response) {
                title.setText(response.getData().getTitle());
                content.setText(Html.fromHtml(response.getData().getContent()));
                response.getData().getImage_bitmap(new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        image.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                mapbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MapActivity.class);
                        intent.putExtra(Keys.LAT,response.getData().getCoordinates().getLat());
                        intent.putExtra(Keys.LONG , response.getData().getCoordinates().getLong());
                        getActivity().startActivity(intent);
                    }
                });

            }
        }, id);

        return rootview;
    }
}
