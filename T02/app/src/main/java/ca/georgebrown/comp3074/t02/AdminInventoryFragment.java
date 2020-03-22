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
public class AdminInventoryFragment extends Fragment {

    DbHelper dbHelp;
    ArrayList<Product> productArrayList;
    ArrayAdapter arrayAdapter;
    ListView productListView;

    public AdminInventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_admin_inventory, container, false);
        dbHelp = new DbHelper(getContext());

        productListView = rootView.findViewById(R.id.adminInventory_listView);

        productArrayList = new ArrayList<>();
        viewData();

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Product product = (Product) adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_product", product);

                AdminProductDetailsFragment adminProductDetailsFragment = new AdminProductDetailsFragment();
                adminProductDetailsFragment.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, adminProductDetailsFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void viewData(){
        Cursor cursor = dbHelp.getAllProducts();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String teaName = cursor.getString(1);
            int quantity = cursor.getInt(2);
            Float unitPrice = cursor.getFloat(3);

            Product product = new Product(id, teaName, quantity, unitPrice);
            productArrayList.add(product);

        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, productArrayList);
        productListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

}