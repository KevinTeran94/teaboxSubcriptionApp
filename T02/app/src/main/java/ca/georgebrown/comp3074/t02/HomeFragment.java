package ca.georgebrown.comp3074.t02;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button oneMonth = rootView.findViewById(R.id.oneMonth);
        Button threeMonth = rootView.findViewById(R.id.threeMonth);
        Button sixMonth = rootView.findViewById(R.id.sixMonth);

//        TextView toolbarUsernameTxt = rootView.findViewById(R.id.toolbar_usernameTxt);
        TextView txt = rootView.findViewById(R.id.homePage_usernameTxt);



        DbHelper dbHelp = new DbHelper(getContext());

//        The following two lines were to used the getUser method
//        User u = dbHelp.getUser("1234","123");
//        Log.d("user object", u + " object is null");

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        // the following code is added for testing purposes
        // commented out after first run, otherwise these items will be added to the database again

        //New content for 3 plans
//
//        dbHelp.insertSubcriptionPlanPage("1-Month Plan", (float) 19.99,
//                "The box will have 3 fresh blends with 1oz of loose leaf tea, total of 3oz. Enough tea for 30+ infusions (10 cups of each type).",
//                "The blends sent in the box are curated from black, green, oolong, white, rooibos, fruit, and herbal varieties.",
//                "Steeping instructions and detailed ingredients list on each package",
//                1);
//
//        dbHelp.insertSubcriptionPlanPage("3-Month Plan", (float) 59.99,
//                "The box will have 3 fresh blends with 1oz of loose leaf tea, total of 3oz. Enough tea for 30+ infusions (10 cups of each type).",
//                "The blends sent in the box are curated from black, green, oolong, white, rooibos, fruit, and herbal varieties.",
//                "Steeping instructions and detailed ingredients list on each package",
//                1);
//
//        dbHelp.insertSubcriptionPlanPage("6-Month Plan", (float) 109.99,
//                "The box will have 3 fresh blends with 1oz of loose leaf tea, total of 3oz. Enough tea for 30+ infusions (10 cups of each type).",
//                        "The blends sent in the box are curated from black, green, oolong, white, rooibos, fruit, and herbal varieties.",
//                "Steeping instructions and detailed ingredients list on each package",
//                1);
//
//
//        // ****************************** Insert admin on first run then comment out the code ************************************
//        dbHelp.insertUser("admin","adminLast","admin@admin.ca","admin","admin");
        //*************************************** Insert Product on first run the comment out the code****************************//
//        dbHelp.insertProduct("Mango Strawberry Black Tea", 1000, (float)19.99);
//        dbHelp.insertProduct("Fruits And Nuts Tisane", 5000, (float)29.99);
//        dbHelp.insertProduct("Spring Chinary Black Tea", 500, (float)39.99);
//        dbHelp.insertProduct("Nilgiri Jasmine Black Tea", 1000, (float)17.49);
//        dbHelp.insertProduct("Rose Mint White Tea", 2000, (float)28.49);
//        dbHelp.insertProduct("Orange Pineapple Black Tea", 1500, (float)36.49);
//        dbHelp.insertProduct("Paan Rose Green Tea", 1100, (float)18.99);
//        dbHelp.insertProduct("Pure Matcha Green Tea", 2500, (float)30.99);

        ArrayList<Product> p = dbHelp.getAllProduct();
        for(Product product : p){
            Log.d("Product arraylist test","\nid: "+product.getId()+"\nTea Name: "+product.getTeaName()+
                    "\nQuantity: "+product.getQuantity()+"\nUnit Price: "+product.getUnitPrice());
        }

//        Order order = dbHelp.getOrder(2);
//        Log.d("checking if order made", ""+order.getOrderId()+" customer id "+order.getCustomerId()+
//                " subplanId"+ order.getSubscriptionPlanPageId()+" Order Date "+order.getOrderDate()+" price "+order.getOrderPrice());


        if(SaveSharedPreferences.getRole(getContext()).equals("admin")){
            fm.beginTransaction().replace(R.id.fragment_container, new AdminHomeFragment()).addToBackStack(null).commit();
        }else{
            if(SaveSharedPreferences.getFirstName(getContext()).length() == 0) {
                Toast.makeText(getContext(),"Welcome to our app!",Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(getContext(),"Welcome "+SaveSharedPreferences.getFirstName(getContext())
//                        +"!",Toast.LENGTH_SHORT).show();
                //toolbarUsernameTxt.setText("Welcome "+SaveSharedPreferences.getFirstName(getContext()));
                txt.setText("Welcome "+SaveSharedPreferences.getFirstName(getContext()));
            }
        }

        oneMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm.beginTransaction().replace(R.id.fragment_container, new OneMonthFragment()).addToBackStack(null).commit();
            }
        });

        threeMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new ThreeMonthFragment()).addToBackStack(null).commit();
            }
        });

        sixMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new SixMonthFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
