package ca.georgebrown.comp3074.t02;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        final DbHelper dbHelp = new DbHelper(getContext());

        Button loginBtn = rootView.findViewById(R.id.loginBtn);
        Button createAccountBtn = rootView.findViewById(R.id.createAccountBtn);

        final EditText email = rootView.findViewById(R.id.emailLogin);
        final EditText password = rootView.findViewById(R.id.passwordLogin);

        final FragmentManager fm  = getActivity().getSupportFragmentManager();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = dbHelp.loginUser(email.getText().toString(), password.getText().toString());
                if(user == null){
                    Toast.makeText(getContext(),"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                } else{
                    SaveSharedPreferences.setFirstName(getContext(),user.getFirstName());
                    SaveSharedPreferences.setEmail(getContext(),user.getEmail());
                    SaveSharedPreferences.setRole(getContext(), user.getRole());
                    Log.d("role", "role set in saved preferences: "+SaveSharedPreferences.getRole(getContext()));

                    if(SaveSharedPreferences.getRole(getContext()).equals("admin")){
                        fm.beginTransaction().replace(R.id.fragment_container, new AdminHomeFragment()).addToBackStack(null).commit();
                    } else{
                        fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
                    }

                    //fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                }
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new RegisterAccountFragment()).addToBackStack(null).commit();
            }
        });



        return rootView;
    }

}
