package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMyAccountFragment extends Fragment {


    public AdminMyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_my_account, container, false);

        Button orderListBtn = rootView.findViewById(R.id.admin_orderListBtn);
        Button customerListBtn = rootView.findViewById(R.id.admin_customerListBtn);
        Button inventoryBtn = rootView.findViewById(R.id.admin_inventoryBtn);
        Button signOutBtn = rootView.findViewById(R.id.admin_signOutBtn);


        final FragmentManager fm = getActivity().getSupportFragmentManager();

        orderListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminOrderListFragment()).addToBackStack(null).commit();
            }
        });

        customerListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminCustomerListFragment()).addToBackStack(null).commit();
            }
        });

        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminInventoryFragment()).addToBackStack(null).commit();
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreferences.clearSharedPreferences(getContext());
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
