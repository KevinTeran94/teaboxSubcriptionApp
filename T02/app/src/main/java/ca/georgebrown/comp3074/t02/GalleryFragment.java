package ca.georgebrown.comp3074.t02;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_gallery, container, false);

        DbHelper dbHelp = new DbHelper(getContext());
        SQLiteDatabase db = dbHelp.getReadableDatabase();

        TextView teaName1 = rootView.findViewById(R.id.gallery_tea1_name);
        TextView teaDescription1 = rootView.findViewById(R.id.gallery_tea1_description);


        return rootView;
    }

}
