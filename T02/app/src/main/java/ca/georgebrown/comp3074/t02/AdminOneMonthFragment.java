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


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminOneMonthFragment extends Fragment {


    public AdminOneMonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_one_month, container, false);

        DbHelper dbHelp = new DbHelper(getContext());
        SQLiteDatabase db = dbHelp.getReadableDatabase();

        Button editBtn = rootView.findViewById(R.id.admin_1month_editBtn);
        TextView txtPageName = rootView.findViewById(R.id.admin_1month_planName);
        TextView txtPrice = rootView.findViewById(R.id.admin_1month_price);
        TextView txtProductOverview1 = rootView.findViewById(R.id.admin_1month_productOverview1);
        TextView txtProductOverview2 = rootView.findViewById(R.id.admin_1month_productOverview2);
        TextView txtProductOverview3 = rootView.findViewById(R.id.admin_1month_productOverview3);


        SubscriptionPlanPage s = dbHelp.getSubscriptionPlanPage(db, 1);

        txtPageName.setText(s.getPage_name());
        String price_text = "Price: $"+s.getPrice();
        txtPrice.setText(price_text);
        txtProductOverview1.setText(s.getPage_description_1());
        txtProductOverview2.setText(s.getPage_description_2());
        txtProductOverview3.setText(s.getPage_description_3());

        final FragmentManager fm = getActivity().getSupportFragmentManager();


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminEditOneMonthFragment()).addToBackStack(null).commit();

            }
        });

        return rootView;
    }

}
