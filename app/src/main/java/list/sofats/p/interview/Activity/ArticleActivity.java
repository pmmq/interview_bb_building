package list.sofats.p.interview.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;


import list.sofats.p.interview.Fragment.ArticleDetailFragment;
import list.sofats.p.interview.R;
import list.sofats.p.interview.Util.Keys;

/**
 * Created by P on 29/12/2558.
 */
public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extra = getIntent().getExtras();
        String id = extra.getString(Keys.INTENT_ID_KEY);

        setContentView(R.layout.fragment_contrainer_layout);
        ArticleDetailFragment articlefragment = ArticleDetailFragment.getInstance(id);
        addFragment(articlefragment);
    }

    public void addFragment(Fragment newFragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container_layout, newFragment);
        transaction.commit();
    }
}
