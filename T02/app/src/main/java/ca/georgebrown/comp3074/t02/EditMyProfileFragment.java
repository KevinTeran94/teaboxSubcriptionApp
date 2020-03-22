package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditMyProfileFragment extends Fragment {


    public EditMyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_edit_my_profile, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());
        final User user = dbHelp.getUser(SaveSharedPreferences.getEmail(getContext()));

        final EditText firstName = rootView.findViewById(R.id.editMyProfile_firstName);
        final EditText lastName = rootView.findViewById(R.id.editMyProfile_lastName);
        final EditText address = rootView.findViewById(R.id.editMyProfile_address);
        final EditText city = rootView.findViewById(R.id.editMyProfile_city);
        final EditText province = rootView.findViewById(R.id.editMyProfile_province);
        final EditText postalCode = rootView.findViewById(R.id.editMyProfile_postalCode);
        final EditText phone = rootView.findViewById(R.id.editMyProfile_phone);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        province.setText(user.getProvince());
        postalCode.setText(user.getPostalCode());
        phone.setText(user.getPhone());

        Button confirmBtn = rootView.findViewById(R.id.editMyProfile_ConfirmButton);
        Button cancelBtn = rootView.findViewById(R.id.editMyProfile_CancelButton);

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new MyProfileFragment()).addToBackStack(null).commit();

            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setAddress(address.getText().toString());
                user.setCity(city.getText().toString());
                user.setProvince(province.getText().toString());
                user.setPostalCode(postalCode.getText().toString());
                user.setPhone(phone.getText().toString());

                dbHelp.updateUser(user);

                fm.beginTransaction().replace(R.id.fragment_container, new MyProfileFragment()).addToBackStack(null).commit();

            }
        });


        return rootView;
    }

}
