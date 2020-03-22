package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CancelPlanConfirmationFragment extends Fragment {


    public CancelPlanConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cancel_plan_confirmation, container, false);

        Button confirmBtn = rootView.findViewById(R.id.cancelPlanPage_confirmBtn);
        Button cancelBtn = rootView.findViewById(R.id.cancelPlanPage_cancelBtn);

        final FragmentManager fm = getActivity().getSupportFragmentManager();


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new CancelSubscriptionSuccessFragment()).addToBackStack(null).commit();

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new MyAccountFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
