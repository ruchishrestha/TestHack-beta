package com.coolapp.testhack;

import android.app.Fragment;
import android.app.FragmentManager;
import com.google.android.gms.maps.MapsInitializer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    Button save_btn;
    Button set_loc;
    Fragment mapfgment;

    public FragmentTwo()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
        setHasOptionsMenu(true);
        loc_det=(EditText) view.findViewById(R.id.loc_det);
        status=(RadioGroup) view.findViewById(R.id.status);
        save_btn=(Button) view.findViewById(R.id.save_loc);
        set_loc=(Button)view.findViewById(R.id.set_loc);
        loc_det.setEnabled(false);
        status.setEnabled(false);
        save_btn.setEnabled(false);

        set_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapfgment=new FragmentOne();
                FragmentManager frgmanager=getFragmentManager();
                frgmanager.beginTransaction().replace(R.id.content_frame, mapfgment).commit();
            }
        });

        return view;
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
           /* case R.id.activity_menu_item:
                // Not implemented here
                return false;*/
            case R.id.Cool:
                // Do Fragment menu item stuff here
                //Toast.makeText(getActivity(),"Message", Toast.LENGTH_SHORT).show();

                return true;
            default:
                break;
        }

        return false;
    }



}
