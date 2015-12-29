package list.sofats.p.interview.Async;

import com.android.volley.Request;
import com.android.volley.Response;

import list.sofats.p.interview.Models.ArticleData;
import list.sofats.p.interview.Models.ArticleDetail;
import list.sofats.p.interview.Models.ArticleList;
import list.sofats.p.interview.Util.Config;

/**
 * Created by P on 28/12/2558.
 */
public class ArticleAsync extends BaseAsync {

    public static void getArticleList(Response.Listener<ArticleData> articleListListener){
        String URL = Config.URL + "category_sport.json";
        String TAG = "category_sport";
        ExcuteRequest(ArticleData.class,URL,TAG,null,articleListListener,Request.Method.GET);
    }

    public static void getArticleData(Response.Listener<ArticleDetail> articleListListener ,String id ){
        String URL = Config.URL + id + ".json";
        String TAG = "article" + id;
        ExcuteRequest(ArticleDetail.class,URL,TAG,null,articleListListener,Request.Method.GET);
    }

}
