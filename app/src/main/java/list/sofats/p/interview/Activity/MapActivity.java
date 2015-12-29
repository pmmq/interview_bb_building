package list.sofats.p.interview.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import list.sofats.p.interview.R;
import list.sofats.p.interview.Util.Keys;

/**
 * Created by P on 29/12/2558.
 */
public class MapActivity extends FragmentActivity {
    LatLng myPosition;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extra = getIntent().getExtras();

        String latitudestring = extra.getString(Keys.LAT);
        String longtitudestring = extra.getString(Keys.LONG);
        double articlelat = Double.parseDouble(latitudestring);
        double articlelong = Double.parseDouble(longtitudestring);
        setContentView(R.layout.map_activity);

        mMap = ((SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        mMap.setMyLocationEnabled(true);


        mMap.addMarker(new MarkerOptions().position(
                new LatLng(articlelat, articlelong))
                .icon(BitmapDescriptorFactory.fromAsset("heart_icon.png"))
                .title("Location"));


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        Criteria criteria = new Criteria();


        String provider = locationManager.getBestProvider(criteria, true);




        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null) {

            double latitude = location.getLatitude();


            double longitude = location.getLongitude();


            LatLng latLng = new LatLng(latitude, longitude);

            myPosition = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 5);
            //mMap.animateCamera(cameraUpdate);
            mMap.addMarker(new MarkerOptions().position(myPosition).icon(BitmapDescriptorFactory.fromAsset("human_icon.png")).title("Start"));

        }
    }
}
