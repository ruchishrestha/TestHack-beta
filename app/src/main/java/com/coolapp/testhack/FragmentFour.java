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
public class FragmentFour extends Fragment {

    EditText titles,add,contact,desc;
    Button set_loc,save;
    String title,add1, add2;
    long Contact;
    String Desc;
    double lat,log;
    Fragment mapfgment;
    List<Address> geocodeMatches = null;
    String Address1;
    String Address2;
    String State;
    String Zipcode;
    String Country;
    public FragmentFour() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.medical, container,
                false);
        titles=(EditText)view.findViewById(R.id.name);
        add=(EditText)view.findViewById(R.id.add);
        contact=(EditText)view.findViewById(R.id.cntnum);
        desc=(EditText)view.findViewById(R.id.medsup_desc);
        set_loc=(Button)view.findViewById(R.id.set_loc);
        save=(Button)view.findViewById(R.id.save);

        set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                mapfgment = new FragmentOne();
                args.putInt("bull", 3);
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
                title = titles.getText().toString();
                add1 = add.getText().toString();
                add2 =""+Address1+" "+Address2+" "+State;
                Contact = Long.parseLong(contact.getText().toString());
                Desc =desc.getText().toString();


                MedSupDescTable MedSupDesc = new MedSupDescTable(title, add1,add2,Contact,Desc,lat,log);
                new AsyncMedSupEntry().execute(MedSupDesc);
            }
        });

        return view;
    }

    protected class AsyncMedSupEntry extends
            AsyncTask<MedSupDescTable, Void, Void> {

        @Override
        protected Void doInBackground(MedSupDescTable... params) {

            RestAPI api = new RestAPI();

            try {

                api.CreateNewEntry3(params[0].gettitles(), params[0].getadd1(), params[0].getadd2(),
                        params[0].getContact(), params[0].getDesc(),
                        params[0].getLat(), params[0].getLog());

            } catch (Exception e) {
               // Log.d("AsyncMedSupEntry", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getActivity(),"Successful",Toast.LENGTH_LONG).show();
        }

    }
}
