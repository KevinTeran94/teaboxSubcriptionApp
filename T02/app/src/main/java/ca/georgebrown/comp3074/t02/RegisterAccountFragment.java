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
public class RegisterAccountFragment extends Fragment {


    public RegisterAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_account, container, false);

        final DbHelper dbHelp = new DbHelper(getContext());

        Button cancelBtn = rootView.findViewById(R.id.cancelBtnRegister);
        Button registerBtn = rootView.findViewById(R.id.registerBtn);

        final EditText firstName = rootView.findViewById(R.id.firstNameRegister);
        final EditText lastName = rootView.findViewById(R.id.lastNameRegister);
        final EditText email = rootView.findViewById((R.id.emailRegister));
        final EditText password = rootView.findViewById((R.id.passwordRegister));

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelp.insertUser(firstName.getText().toString(),
                        lastName.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        "customer")){
                    Log.d("DB", "Insert user "+firstName.getText().toString()+" log from if statement in fragment");
                }
                fm.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

}
