package ca.georgebrown.comp3074.t02;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {

    DbHelper dbHelp;
    ArrayList<Order> orderArrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_history, container, false);

        dbHelp = new DbHelper(getContext());
        User user = dbHelp.getUser(SaveSharedPreferences.getEmail(getContext()));

        listView = rootView.findViewById(R.id.customer_orderHistory_listView);

        orderArrayList = new ArrayList<>();
        viewData(user.getCustomerId());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Order order = (Order)adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_order", order);

                OrderDetailsFragment orderDetailsFragment = new OrderDetailsFragment();
                orderDetailsFragment.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, orderDetailsFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void viewData(int userId){
        Cursor cursor = dbHelp.getOrdersByUserId(userId);
        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            int subPlanId = cursor.getInt(1);
            int customerId = cursor.getInt(2);
            String date = cursor.getString(3);
            Float orderPrice = cursor.getFloat(4);

            Order order = new Order(id, subPlanId, customerId, date, orderPrice);
            orderArrayList.add(order);

        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, orderArrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

}
