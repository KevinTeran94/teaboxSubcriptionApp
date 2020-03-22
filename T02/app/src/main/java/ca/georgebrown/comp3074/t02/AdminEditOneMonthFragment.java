package ca.georgebrown.comp3074.t02;


import android.database.sqlite.SQLiteDatabase;
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
public class AdminEditOneMonthFragment extends Fragment {


    public AdminEditOneMonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_edit_one_month, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());
        final SQLiteDatabase db = dbHelp.getReadableDatabase();
        final SubscriptionPlanPage s = dbHelp.getSubscriptionPlanPage(db, 1);


        Button confirmBtn = rootView.findViewById(R.id.admin_edit_1month_confirmBtn);
        Button cancelBtn = rootView.findViewById(R.id.admin_edit_1month_cancelBtn);
        final EditText newPrice = rootView.findViewById(R.id.admin_edit_1month_price);
        final EditText newProductOverview1 = rootView.findViewById(R.id.admin_edit_1month_productOverview1);
        final EditText newProductOverview2 = rootView.findViewById(R.id.admin_edit_1month_productOverview2);
        final EditText newProductOverview3 = rootView.findViewById(R.id.admin_edit_1month_productOverview3);

        newPrice.setText(String.valueOf(s.getPrice()));
        newProductOverview1.setText(s.getPage_description_1());
        newProductOverview2.setText(s.getPage_description_2());
        newProductOverview3.setText(s.getPage_description_3());



        final FragmentManager fm = getActivity().getSupportFragmentManager();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new AdminOneMonthFragment()).addToBackStack(null).commit();

            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.setPrice(Float.parseFloat(newPrice.getText().toString()));
                s.setPage_description_1(newProductOverview1.getText().toString());
                s.setPage_description_2(newProductOverview2.getText().toString());
                s.setPage_description_3(newProductOverview3.getText().toString());

                dbHelp.updateSubPlan(s);
                fm.beginTransaction().replace(R.id.fragment_container, new AdminOneMonthFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
