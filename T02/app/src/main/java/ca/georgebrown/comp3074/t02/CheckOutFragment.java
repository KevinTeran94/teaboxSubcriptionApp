package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment extends Fragment {


    public CheckOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_check_out, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());
        final User user = dbHelp.getUser(SaveSharedPreferences.getEmail(getContext()));

        final Bundle bundle = getArguments();
        final SubscriptionPlanPage s = (SubscriptionPlanPage)bundle.getSerializable("SelectedSubPlanId");

        Button cancelBtn = rootView.findViewById(R.id.checkout_cancelBtn);
        Button placeOrderBtn = rootView.findViewById(R.id.checkout_PlaceOrderBtn);
        final EditText firstName = rootView.findViewById(R.id.checkOut_firstName);
        final EditText lastName = rootView.findViewById(R.id.checkOut_lastName);
        final EditText address = rootView.findViewById(R.id.checkOut_address);
        final EditText address2 = rootView.findViewById(R.id.checkOut_address_2);
        final EditText city = rootView.findViewById(R.id.checkOut_city);
        final EditText province = rootView.findViewById(R.id.checkOut_province);
        final EditText postalCode = rootView.findViewById(R.id.checkOut_postalCode);
        final EditText phone = rootView.findViewById(R.id.checkOut_phone);
        final EditText cardholder = rootView.findViewById(R.id.checkOut_cardHolder);
        final EditText cardNumber = rootView.findViewById(R.id.checkOut_cardNumber);
        final EditText expiryDate = rootView.findViewById(R.id.checkOut_cardExpiryDate);
        final EditText cardCvv = rootView.findViewById(R.id.checkOut_cardCVV);
        final CheckBox checkBox = rootView.findViewById(R.id.checkOut_checkBox_saveCard);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        province.setText(user.getProvince());
        postalCode.setText(user.getPostalCode());
        phone.setText(user.getPhone());
        cardholder.setText(user.getCardHolderName());

        if(user.getCardNumber()==0){
            cardNumber.setText("");
        }else{
            cardNumber.setText(String.valueOf(user.getCardNumber()));
        }

        expiryDate.setText(user.getExpiryDate());

        if(user.getCvv()==0){
            cardCvv.setText("");
        } else{
            cardCvv.setText(String.valueOf(user.getCvv()));
        }

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setAddress(address.getText().toString());
                user.setCity(city.getText().toString());
                user.setProvince(province.getText().toString());
                user.setPostalCode(postalCode.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setCardHolderName(cardholder.getText().toString());
                user.setCardNumber(Integer.valueOf(cardNumber.getText().toString()));
                user.setExpiryDate(expiryDate.getText().toString());
                user.setCvv(Integer.valueOf(cardCvv.getText().toString()));

                //if the box is checked save credit card info otherwise do not
                if(checkBox.isChecked()){
                    dbHelp.updateUserWithCreditCard(user);
                } else{
                    dbHelp.updateUser(user);
                }

                long orderId = dbHelp.createOrder(user.getCustomerId(), s.getId(), s.getPrice());
                Log.d("Order Id",""+orderId);
                Bundle bundle  = new Bundle();
                bundle.putLong("orderId",orderId);

                OrderConfirmationFragment oc = new OrderConfirmationFragment();
                oc.setArguments(bundle);

                fm.beginTransaction().replace(R.id.fragment_container, oc).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
