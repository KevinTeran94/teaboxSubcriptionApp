package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminProductDetailsFragment extends Fragment {


    public AdminProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_product_details, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());

        final Bundle bundle = getArguments();
        final Product product = (Product) bundle.getSerializable("selected_product");

        Button backBtn = rootView.findViewById(R.id.adminProductDetails_BackBtn);
        TextView teaID = rootView.findViewById(R.id.adminProductDetails_teaId);
        TextView teaName = rootView.findViewById(R.id.adminProductDetails_teaName);
        TextView teaQuantity = rootView.findViewById(R.id.adminProductDetails_teaQuantity);
        TextView teaPrice = rootView.findViewById(R.id.adminProductDetails_teaPrice);

        Log.d("does it exist", "hello "+product.getId());
        teaID.setText(String.valueOf(product.getId()));
        teaName.setText(product.getTeaName());
        teaQuantity.setText(String.valueOf(product.getQuantity())+" units");
        teaPrice.setText(String.valueOf(product.getUnitPrice()));

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminInventoryFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
