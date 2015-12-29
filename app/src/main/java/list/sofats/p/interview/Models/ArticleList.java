package list.sofats.p.interview.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import list.sofats.p.interview.Async.BaseAsync;
import list.sofats.p.interview.Manager.ApplicationManager;
import list.sofats.p.interview.Util.Config;

/**
 * Created by P on 28/12/2558.
 */
public class ArticleList {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAdded() {
        return added;
    }

    public String getPriority() {
        return priority;
    }

    public String getPlatform() {
        return platform;
    }

    public String getCuration() {
        return curation;
    }

    public String getCuration_filename() {
        return curation_filename;
    }

    public List<Article> getArticles() {
        return articles;
    }

    private String id;
    private String name;
    private String type;
    private String added;
    private String priority;
    private String platform;
    private String curation;
    private String curation_filename;
    private List<Article> articles;
    public class Article{
        private String id;
        private String date;
        private String title;
        private String image;
        private String short_description;
        private String type;
        private String content;
        private Bitmap image_bitmap;
        private Coordinate coordinates;
        public String getId() {
            return id;
        }

        public Coordinate getCoordinates() {
            return coordinates;
        }

        public String getDate() {
            return date;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getShort_description() {
            return short_description;
        }

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        public Bitmap getImage_bitmap(final ImageLoader.ImageListener im) {

            if(image_bitmap == null){
                image_bitmap =(Bitmap) BitmapFactory.decodeResource(ApplicationManager.getInstance().getResources() , android.support.v7.appcompat.R.drawable.abc_tab_indicator_material);

                BaseAsync.LoadImage(Config.URL + image, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            image_bitmap = response.getBitmap();
                        im.onResponse(response,isImmediate);
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }

            return image_bitmap;
        }

        public class Coordinate{
            String lat;
            @SerializedName("long")
            String longtitude;

            public String getLat() {
                return lat;
            }

            public String getLong() {
                return longtitude;
            }
        }
    }

}
