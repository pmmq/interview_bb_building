package list.sofats.p.interview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;

import list.sofats.p.interview.Adapter.ArticleAdapter;
import list.sofats.p.interview.Async.ArticleAsync;
import list.sofats.p.interview.Manager.ApplicationManager;
import list.sofats.p.interview.Models.ArticleData;
import list.sofats.p.interview.Models.ArticleList;
import list.sofats.p.interview.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);

        ArticleAsync.getArticleList(new Response.Listener<ArticleData>() {
            @Override
            public void onResponse(ArticleData response) {
                articleAdapter = new ArticleAdapter(response.getData() , MainActivity.this);
                rv.setAdapter(articleAdapter);
            }
        });
    }
}
