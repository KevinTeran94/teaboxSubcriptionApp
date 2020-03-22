package ca.georgebrown.comp3074.t02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar_main);
        TextView toolbarTittle = findViewById(R.id.titleText);
        TextView toolbarUsernameTxt = findViewById(R.id.toolbar_usernameTxt);
//        toolbarUsernameTxt.setText("");
        toolbar.setTitle("");
        toolbarTittle.setText("Tea Subscription Box");
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.gallery:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GalleryFragment()).addToBackStack("home").commit();
                break;
            case R.id.myAccount:
                if(SaveSharedPreferences.getRole(getApplicationContext()).equals("admin")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdminMyAccountFragment()).addToBackStack("home").commit();
                } else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyAccountFragment()).addToBackStack("home").commit();
                }
                break;
            case R.id.faq:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FAQsFragment()).addToBackStack("home").commit();
                break;
            case R.id.login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack("home").commit();
                break;
            case R.id.register:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RegisterAccountFragment()).addToBackStack("home").commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;    }
}
