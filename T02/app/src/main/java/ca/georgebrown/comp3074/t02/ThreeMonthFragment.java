package ca.georgebrown.comp3074.t02;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeMonthFragment extends Fragment {


    public ThreeMonthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_three_month, container, false);
        DbHelper dbHelp = new DbHelper(getContext());
        SQLiteDatabase db = dbHelp.getReadableDatabase();

        TextView txtPageName = rootView.findViewById(R.id.page_name_3_month);
        TextView txtPrice = rootView.findViewById(R.id.page_price_3_month);
        TextView txtProductOverview1 = rootView.findViewById(R.id.page_productOverview1_3_month);
        TextView txtProductOverview2 = rootView.findViewById(R.id.page_productOverview2_3_month);
        TextView txtProductOverview3 = rootView.findViewById(R.id.page_productOverview3_3_month);


        final SubscriptionPlanPage s = dbHelp.getSubscriptionPlanPage(db, 2);

        txtPageName.setText(s.getPage_name());
        String price_text = "Price: $"+s.getPrice();
        txtPrice.setText(price_text);
        txtProductOverview1.setText(s.getPage_description_1());
        txtProductOverview2.setText(s.getPage_description_2());
        txtProductOverview3.setText(s.getPage_description_3());

        Button subscribebtn = rootView.findViewById(R.id.subscribeBtn);
        subscribebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(SaveSharedPreferences.getFirstName(getContext()).length() == 0)
                {
                    Toast.makeText(getContext(),"Please Login first!",Toast.LENGTH_SHORT).show();
                    fm.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();

                } else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("SelectedSubPlanId",s);
                    CheckOutFragment checkOut = new CheckOutFragment();
                    checkOut.setArguments(bundle);
                    fm.beginTransaction().replace(R.id.fragment_container, checkOut).addToBackStack(null).commit();
                }

            }
        });
        return rootView;

    }

}
