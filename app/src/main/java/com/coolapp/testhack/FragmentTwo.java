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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


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
    Fragment mapfgment;
    double lt;
    double lg;
    int bolclick; String loc_det1,loc_name1;

    public FragmentTwo()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_layout_two,container, false);

        loc_det=(EditText) view.findViewById(R.id.loc_det);
        status=(RadioGroup) view.findViewById(R.id.status);
        save_btn=(Button) view.findViewById(R.id.save_loc);
        set_loc=(Button)view.findViewById(R.id.set_loc);
        safe=(RadioButton)view.findViewById(R.id.safe);
        unsafe=(RadioButton)view.findViewById(R.id.unsafe);


        //loc_det.setEnabled(false);
        //status.setEnabled(false);
        //save_btn.setEnabled(false);

        set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                mapfgment = new FragmentOne();
                args.putBoolean("bull", true);
                mapfgment.setArguments(args);
                FragmentManager frgmanager = getFragmentManager();
                frgmanager.beginTransaction().replace(R.id.content_frame, mapfgment).commit();
            }
        });
        lt=getArguments().getDouble("lat");
        lg=getArguments().getDouble("long");
        Toast.makeText(getActivity(),"Lat: "+lt+" Long: "+lg,Toast.LENGTH_LONG).show();
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
                loc_name1 = "Saten";
                LocDescTable LocDesc = new LocDescTable(loc_name1, lt, lg, loc_det1, bolclick);
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
                        params[0].getLoc_det(),params[0].getbol());

            } catch (Exception e) {
                Log.d("AsyncLocationEntry", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getActivity(),loc_det1,Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),"Successful",Toast.LENGTH_LONG).show();
        }

    }

}
