package id.sch.smktelkom_mlg.afinal.xirpl2071330.healthycare;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Ayu Indraswari on 03/04/2018.
 */

public class Halaman1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout DLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DLayout = findViewById(R.id.drawerLayout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DLayout, R.string.open, R.string.close);
        DLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new masukFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new masukFragment()).commit();
                break;
            case R.id.navpp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new FragmentPp()).commit();
                break;
            case R.id.navpengobatan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new PengobatanFragment()).commit();
                break;
            case R.id.navpencegahan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new PencegahanFragment()).commit();
                break;
            case R.id.navdev:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new BlankFragment()).commit();
                break;
        }
        DLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (DLayout.isDrawerOpen(GravityCompat.START)) {
            DLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

