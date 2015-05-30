package com.coolapp.testhack;

import android.app.Fragment;
import android.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;


/**
 * Created by Home-PC on 5/21/2015.
 */
public class FragmentTwo extends Fragment {

    Bundle args;
    EditText loc_det;
    RadioGroup status;
    RadioButton safe, unsafe;
    Button save_btn;
    Button set_loc;
    CheckBox relief;
    Fragment mapfgment;
    double lt;
    double lg;
    int bolclick;
    String loc_det1,loc_name1,relief1;
    List<Address> geocodeMatches = null;
    String Address1;
    String Address2;
    String State;
    String Zipcode;
    String Country;

    public FragmentTwo()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.locentry,container, false);

        loc_det=(EditText) view.findViewById(R.id.loc_det);
        save_btn=(Button) view.findViewById(R.id.save_loc);
        set_loc=(Button)view.findViewById(R.id.set_loc);
        safe=(RadioButton)view.findViewById(R.id.safe);
        unsafe=(RadioButton)view.findViewById(R.id.unsafe);
        relief=(CheckBox) view.findViewById(R.id.relief);
        bolclick=0;
        relief1="None";


        //loc_det.setEnabled(false);
        //status.setEnabled(false);
        //save_btn.setEnabled(false);

        set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                mapfgment = new FragmentOne();
                args.putInt("bull", 1);
                mapfgment.setArguments(args);
                FragmentManager frgmanager = getFragmentManager();
                frgmanager.beginTransaction().replace(R.id.content_frame, mapfgment).commit();
            }
        });
        lt=getArguments().getDouble("lat");
        lg=getArguments().getDouble("long");

        try {
            geocodeMatches =
                    new Geocoder(getActivity()).getFromLocation(lt,lg,1);
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
        Toast.makeText(getActivity(),""+State,Toast.LENGTH_LONG).show();

        relief.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    relief1 = "Relief";
                    safe.setChecked(true);
                    bolclick = 1;
                    safe.setEnabled(false);
                    unsafe.setEnabled(false);
                } else {
                    relief1 = "None";
                    safe.setEnabled(true);
                    unsafe.setEnabled(true);
                }

            }
        });

        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolclick = 1;
                Toast.makeText(getActivity(), "Safe", Toast.LENGTH_LONG).show();
            }
        });
        unsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bolclick = 0;
                Toast.makeText(getActivity(), "Unsafe", Toast.LENGTH_LONG).show();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loc_det1 = loc_det.getText().toString();
                loc_name1 =""+Address1+" "+Address2+" "+State;
                LocDescTable LocDesc = new LocDescTable(loc_name1, lt, lg, loc_det1, bolclick, relief1);
                new AsyncLocationEntry().execute(LocDesc);
            }
        });

        return view;

    }

    protected class AsyncLocationEntry extends
            AsyncTask<LocDescTable, Void, Void> {

        @Override
        protected Void doInBackground(LocDescTable... params) {

            RestAPI api = new RestAPI();

            try {

                api.CreateNewEntry1(params[0].getLoc_name(),
                        params[0].getLat(), params[0].getLog(),
                        params[0].getLoc_det(),params[0].getbol(),params[0].getrelief());

            } catch (Exception e) {
               // Log.d("AsyncLocationEntry", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getActivity(),"Successful",Toast.LENGTH_LONG).show();
        }

    }

}
