package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {


    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentManager fm = getActivity().getSupportFragmentManager();
        if(SaveSharedPreferences.getFirstName(getContext()).length() == 0)
        {
            Toast.makeText(getContext(),"Please Login first!",Toast.LENGTH_SHORT).show();
            fm.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();

        }

        View rootview = inflater.inflate(R.layout.fragment_my_account, container, false);

        Button orderHistoryBtn = rootview.findViewById(R.id.orderHistoryBtn);
        Button myProfileBtn = rootview.findViewById(R.id.myProfileBtn);
        Button cancelPlanBtn = rootview.findViewById(R.id.cancelPlanBtn);
        Button signOutBtn = rootview.findViewById(R.id.signOutBtn);

        TextView username = rootview.findViewById(R.id.myAccount_usernameTxt);
        username.setText("Welcome "+SaveSharedPreferences.getFirstName(getContext()));


//        final FragmentManager fm = getActivity().getSupportFragmentManager();

        orderHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new OrderHistoryFragment()).addToBackStack(null).commit();
            }
        });

        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new MyProfileFragment()).addToBackStack(null).commit();
            }
        });

        cancelPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new CancelPlanConfirmationFragment()).addToBackStack(null).commit();
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreferences.clearSharedPreferences(getContext());
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        return rootview;
    }

}
