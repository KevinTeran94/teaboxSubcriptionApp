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
public class OrderDetailsFragment extends Fragment {


    public OrderDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_details, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());
        final User user = dbHelp.getUser(SaveSharedPreferences.getEmail(getContext()));


        final Bundle bundle = getArguments();
        final Order order = (Order) bundle.getSerializable("selected_order");
        final SubscriptionPlanPage s = dbHelp.getSubsciptionPlanPageById(order.getSubscriptionPlanPageId());

        Button backBtn = rootView.findViewById(R.id.orderDetails_BackBtn);
        TextView orderNumber = rootView.findViewById(R.id.orderDetails_orderNumber);
        TextView orderDate = rootView.findViewById(R.id.orderDetails_orderDate);
        TextView customerName = rootView.findViewById(R.id.orderDetails_customer);
        TextView shippingName = rootView.findViewById(R.id.orderDetails_fullname);
        TextView shippingAddress = rootView.findViewById(R.id.orderDetails_fullAddress);
        TextView phone = rootView.findViewById(R.id.orderDetails__phone);
        TextView subPlanName = rootView.findViewById(R.id.orderDetails_subPlanName);

        orderNumber.setText(String.valueOf(order.getOrderId()));
        orderDate.setText(order.getOrderDate());
        customerName.setText(user.getFirstName()+" "+user.getLastName());
        shippingName.setText(user.getFirstName()+" "+user.getLastName());
        shippingAddress.setText(user.getAddress()+"\n"+user.getCity()+", "+user.getProvince()+" "+user.getPostalCode());
        phone.setText(user.getPhone());
        subPlanName.setText(s.getPage_name());

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new OrderHistoryFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
