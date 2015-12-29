package list.sofats.p.interview.Async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

import list.sofats.p.interview.Manager.ApplicationManager;
import list.sofats.p.interview.Models.Model;
import list.sofats.p.interview.Util.GsonRequest;
import list.sofats.p.interview.Util.LruBitmapCache;

/**
 * Created by P on 28/12/2558.
 */
public class BaseAsync {

    public static void LoadImage(final String url , final ImageLoader.ImageListener imageListener ){

        ImageLoader.ImageCache imageCache = new LruBitmapCache();
        ImageLoader mImageLoader = new ImageLoader(Volley.newRequestQueue(ApplicationManager.getInstance()), imageCache);
        mImageLoader.get(url, imageListener);
    }


    public static<T> void ExcuteRequest(Class<T> tClass ,  String URL , String TAG , HashMap<String , String> params, final Response.Listener<T> pListener ,int pRequestMethod){

        Response.Listener<T> mListener = new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {

                if(((Model)response).getStatus().code.equals("200")){
                    if(pListener!=null) {
                        pListener.onResponse(response);
                    }
                }else{
                    Toast.makeText(ApplicationManager.getInstance().getApplicationContext(),"invalid return code",Toast.LENGTH_LONG);
                }
            }
        };

        Response.ErrorListener mError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        if(pRequestMethod == Request.Method.GET && params != null)
        {
            String paramString = "";
            int index = 0;
            for(HashMap.Entry<String,String> entry : params.entrySet() )
            {
                if(index == 0)
                {
                    paramString += ("?");
                }
                else
                {
                    paramString += ("&");
                }
                paramString += (entry.getKey() + "=" +entry.getValue());
                index++;
            }
            URL+= paramString;
        }

        GsonRequest<T> request = new GsonRequest<T>(pRequestMethod,URL ,tClass ,params , mListener , mError);
        ApplicationManager.getInstance().addToRequestQueue(request,TAG);
    }
}
