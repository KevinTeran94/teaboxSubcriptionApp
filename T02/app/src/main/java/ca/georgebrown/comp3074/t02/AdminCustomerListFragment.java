package ca.georgebrown.comp3074.t02;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminCustomerListFragment extends Fragment {

    DbHelper dbHelp;
    ArrayList<User> customerArrayList;
    ArrayAdapter arrayAdapter;
    ListView customerListView;

    public AdminCustomerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_customer_list, container, false);

        dbHelp = new DbHelper(getContext());

        customerListView = rootView.findViewById(R.id.adminCustomerList_customerListView);

        customerArrayList = new ArrayList<>();
        viewData();

        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final User user = (User) adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_user", user);

                AdminCustomerProfileFragment adminCustomerProfileFragment = new AdminCustomerProfileFragment();
                adminCustomerProfileFragment.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, adminCustomerProfileFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void viewData(){
        Cursor cursor = dbHelp.getAllUsers();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email2 = cursor.getString(3);
            String password2 = cursor.getString(4);
            String address = cursor.getString(5);
            String city = cursor.getString(6);
            String province = cursor.getString(7);
            String postalCode = cursor.getString(8);
            String phone = cursor.getString(9);
            String role = cursor.getString(10);
            String cardHolderName = cursor.getString(11);
            int cardNumber = cursor.getInt(12);
            String expiryDate = cursor.getString(13);
            int cvv = cursor.getInt(14);


            User user = new User(id, firstName, lastName, email2, password2, address, city, province,
                    postalCode, phone, role, cardHolderName, cardNumber, expiryDate, cvv);
            customerArrayList.add(user);

        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, customerArrayList);
        customerListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

}
