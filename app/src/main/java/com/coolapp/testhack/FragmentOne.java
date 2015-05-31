package com.coolapp.testhack;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Home-PC on 5/21/2015.
 */
public class FragmentOne extends Fragment {

//Added

    ArrayList<String> data;

    MapView mMapView;
    private GoogleMap googleMap;
    Double lat;
    Double log;
    LatLng loc;
    Marker PointHere;
    MarkerOptions marker;
    int bull;
    Fragment f;

    public FragmentOne(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_layout_one, container,
                false);
        setHasOptionsMenu(true);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        // latitude and longitude


        //My Location
        googleMap.setMyLocationEnabled(true);
        final GoogleMap.OnMyLocationChangeListener myLocationChangeListener=new GoogleMap.OnMyLocationChangeListener(){
            @Override
            public void onMyLocationChange(Location location) {
                loc = new LatLng(location.getLatitude(),location.getLongitude());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc,16.0f));
                marker = new MarkerOptions().position(
                        new LatLng(loc.latitude,
                                loc.longitude)).title("You Are Here!!");
                // Changing marker icon
                marker.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                // adding marker
                PointHere = googleMap.addMarker(marker);
                googleMap.setOnMyLocationChangeListener(null);
            }
        };
        googleMap.setOnMyLocationChangeListener(myLocationChangeListener);

        //Added
        data = new ArrayList<String>();
        bull=getArguments().getInt("bull");
        if(!(bull==0)) {
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    lat = latLng.latitude;
                    log = latLng.longitude;
                    nxtfragment();
                }
            }); }       // create marker

        new AsyncLoadLocDescDetails().execute();
        // Perform any camera updates here
        return v;
    }



    public void nxtfragment(){
        Bundle args=new Bundle();
        switch (bull){
            case 1:f=new FragmentTwo();break;
            case 2:f=new FragmentThree();break;
            case 3:f=new FragmentFour();break;
            default: break;
        }
        //args.putBoolean("bull",true);
        args.putDouble("lat",lat );
        args.putDouble("long",log);
        f.setArguments(args);
        this.getFragmentManager().beginTransaction().replace(R.id.content_frame,f).commit();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.fragment_menu_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.zin:
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                return true;
            case R.id.zout:
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());
                return true;
            case R.id.nview:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.satview:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.hyview:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            default:
                break;
        }

        return false;
    }

    public void addmarkers(String loc_name,double lat1,double log2,String loc_desc,int bol,String relief){
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(lat1,
                        log2));
                if(relief.equals("Relief")){
                    // Changing marker icon
                    marker.icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.health_medical));
                }else{
                    if(bol==1) {// Changing marker icon
                        marker.icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                    else{
                        // Changing marker icon
                        marker.icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.toys_store));

                    }
                }

        // adding marker
        googleMap.addMarker(marker);
    }


    protected class AsyncLoadLocDescDetails extends
            AsyncTask<Void, JSONObject, ArrayList<LocDescTable>> {
        ArrayList<LocDescTable> deptTable = null;

        @Override
        protected ArrayList<LocDescTable> doInBackground(Void... params) {

            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetLocDesc();

                JSONParser parser = new JSONParser();

                deptTable = parser.parseLocDesc(jsonObj);

            } catch (Exception e) {
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return deptTable;
        }

        @Override
        protected void onPostExecute(ArrayList<LocDescTable> result) {

            for (int i = 0; i < result.size(); i++) {
                //data.add(result.get(i).getLat() + " " + result.get(i).getLog());
                addmarkers(result.get(i).getLoc_name(),result.get(i).getLat(), result.get(i).getLog(),result.get(i).getLoc_det(),result.get(i).getbol(),result.get(i).getrelief());
            }
        }
    }
}