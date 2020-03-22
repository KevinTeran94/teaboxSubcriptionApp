package ca.georgebrown.comp3074.t02;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminOrderListFragment extends Fragment {

    DbHelper dbHelp;
    ArrayList<Order> orderArrayList;
    ArrayAdapter arrayAdapter;
    ListView orderListView;
    SQLiteDatabase db;

    public AdminOrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_order_list, container, false);
        dbHelp = new DbHelper(getContext());
        db = dbHelp.getReadableDatabase();

        orderListView = rootView.findViewById(R.id.adminOrderList_orderListView);

        orderArrayList = new ArrayList<>();
        viewData();

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Order order = (Order)adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_order", order);

                AdminOrderDetailsFragment adminOrderDetailsFragment = new AdminOrderDetailsFragment();
                adminOrderDetailsFragment.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, adminOrderDetailsFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void viewData(){
        Cursor cursor = dbHelp.getAllOrders();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int subPlanId = cursor.getInt(1);
            int customerId = cursor.getInt(2);
            String date = cursor.getString(3);
            Float orderPrice = cursor.getFloat(4);

            Order order = new Order(id, subPlanId, customerId, date, orderPrice);
            orderArrayList.add(order);

        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, orderArrayList);
        orderListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

}
