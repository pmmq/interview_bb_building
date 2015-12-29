package list.sofats.p.interview.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;
import java.util.zip.Inflater;

import list.sofats.p.interview.Activity.ArticleActivity;
import list.sofats.p.interview.Fragment.ArticleDetailFragment;
import list.sofats.p.interview.Holder.ArticleHolder;
import list.sofats.p.interview.Manager.ApplicationManager;
import list.sofats.p.interview.Models.ArticleList;
import list.sofats.p.interview.R;
import list.sofats.p.interview.Util.Keys;

/**
 * Created by P on 29/12/2558.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {

    ArticleList articleList;
    List<ArticleList.Article> articles;
    AppCompatActivity currentActivity;
    public ArticleAdapter(ArticleList al ,AppCompatActivity fm){
        articleList = al;
        articles = articleList.getArticles();
        currentActivity = fm;
    }


    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(ApplicationManager.getInstance().getApplicationContext()).inflate(R.layout.article_adapter,parent,false);

        ArticleHolder ah = new ArticleHolder(rootview);

        return ah;
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, final int position) {
        holder.description.setText(articles.get(position).getShort_description());
        holder.title.setText(articles.get(position).getTitle());

        if(articles.get(position).getImage() == ""){
            holder.image.setVisibility(View.GONE);
        }else{
            holder.image.setVisibility(View.VISIBLE);
            articles.get(position).getImage_bitmap(new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.image.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent = new Intent(currentActivity , ArticleActivity.class);
                mainintent.putExtra(Keys.INTENT_ID_KEY , articles.get(position).getId());
                currentActivity.startActivity(mainintent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
