package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends Fragment {


    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_home, container, false);

        Button oneMonth = rootView.findViewById(R.id.AdminHomeOneMonth);
        Button threeMonth = rootView.findViewById(R.id.AdminHomeThreeMonth);
        Button sixMonth = rootView.findViewById(R.id.AdminHomeSixMonth);
        TextView txt = rootView.findViewById(R.id.admin_homePage_usernameTxt);

        txt.setText("Welcome Admin,");

        DbHelper dbHelp = new DbHelper(getContext());

        Toast.makeText(getContext(),"Admin Home Fragment!",Toast.LENGTH_SHORT).show();

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        oneMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm.beginTransaction().replace(R.id.fragment_container, new AdminOneMonthFragment()).addToBackStack(null).commit();
            }
        });

        threeMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminThreeMonthFragment()).addToBackStack(null).commit();
            }
        });

        sixMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminSixMonthFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}
