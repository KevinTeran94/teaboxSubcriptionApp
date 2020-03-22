package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminCustomerProfileFragment extends Fragment {


    public AdminCustomerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_admin_customer_profile, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());

        final Bundle bundle = getArguments();
        final User user = (User) bundle.getSerializable("selected_user");

        Button backBtn = rootView.findViewById(R.id.adminCustomerProfile_backBtn);
        TextView firstName = rootView.findViewById(R.id.adminCustomerProfile_FirstName_value);
        TextView lastName = rootView.findViewById(R.id.adminCustomerProfile_LastName_value);
        TextView address = rootView.findViewById(R.id.adminCustomerProfile_Address_value);
        TextView city = rootView.findViewById(R.id.adminCustomerProfile_City_value);
        TextView province = rootView.findViewById(R.id.adminCustomerProfile_Province_value);
        TextView postalCode = rootView.findViewById(R.id.adminCustomerProfile_PostalCode_value);
        TextView phone = rootView.findViewById(R.id.adminCustomerProfile_Phone_value);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        province.setText(user.getProvince());
        postalCode.setText(user.getPostalCode());
        phone.setText(user.getPhone());

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminCustomerListFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
