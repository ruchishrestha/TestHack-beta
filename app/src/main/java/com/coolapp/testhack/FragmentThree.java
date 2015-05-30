package com.coolapp.testhack;

import android.app.Fragment;
import android.app.FragmentManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.List;

/**
 * Created by Home-PC on 5/21/2015.
 */
public class FragmentThree extends Fragment {
    EditText ownname,ownadd,phone,mobileno,desc,rprice;
    Button set_loc,save;
    double lat,log;
    String ownadd1,ownadd2,ownname1,desc1;
    long phone1,mobileno1,rprice1;
    Fragment mapfgment;
    List<Address> geocodeMatches = null;
    String Address1;
    String Address2;
    String State;
    String Zipcode;
    String Country;

    public FragmentThree() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setloc, container,
                false);
        ownname=(EditText)view.findViewById(R.id.ownname);
        ownadd=(EditText)view.findViewById(R.id.ownadd);
        phone=(EditText)view.findViewById(R.id.ownphn);
        mobileno=(EditText)view.findViewById(R.id.ownmob);
        desc=(EditText)view.findViewById(R.id.rent_desc);
        rprice=(EditText)view.findViewById(R.id.rentprc);
        set_loc=(Button)view.findViewById(R.id.set_loc);
        save=(Button)view.findViewById(R.id.save);

        set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                mapfgment = new FragmentOne();
                args.putInt("bull", 2);
                mapfgment.setArguments(args);
                FragmentManager frgmanager = getFragmentManager();
                frgmanager.beginTransaction().replace(R.id.content_frame, mapfgment).commit();
            }
        });
        lat=getArguments().getDouble("lat");
        log=getArguments().getDouble("long");

        try {
            geocodeMatches =
                    new Geocoder(getActivity()).getFromLocation(lat,log,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty())
        {
            Address1 = geocodeMatches.get(0).getAddressLine(0);
            Address2 = geocodeMatches.get(0).getAddressLine(1);
            State = geocodeMatches.get(0).getAdminArea();
            Zipcode = geocodeMatches.get(0).getPostalCode();
            Country = geocodeMatches.get(0).getCountryName();
        }
        Toast.makeText(getActivity(), "" + State, Toast.LENGTH_LONG).show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownname1 = ownname.getText().toString();
                ownadd1 = ownadd.getText().toString();
                phone1 = Long.parseLong(phone.getText().toString());
                mobileno1 = Long.parseLong(mobileno.getText().toString());
                desc1 =desc.getText().toString();
                rprice1 = Long.parseLong(rprice.getText().toString());
                ownadd2 =""+Address1+" "+Address2+" "+State;
                RentDescTable RentDesc = new RentDescTable(ownname1, ownadd1,ownadd2,lat, log, phone1, mobileno1,desc1,rprice1);
                new AsyncRentalEntry().execute(RentDesc);
            }
        });
        return view;
    }

    protected class AsyncRentalEntry extends
            AsyncTask<RentDescTable, Void, Void> {

        @Override
        protected Void doInBackground(RentDescTable... params) {

            RestAPI api = new RestAPI();

            try {

                api.CreateNewEntry2(params[0].getownname1(),params[0].getOwnadd1(),params[0].getOwnadd2(),
                        params[0].getLat(), params[0].getLog(),
                        params[0].getphone1(),params[0].getmobileno1(),params[0].getdesc1(),params[0].getRprice1());

            } catch (Exception e) {
              //  Log.d("AsyncRentalEntry", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getActivity(),"Successful",Toast.LENGTH_LONG).show();
        }

    }

}
