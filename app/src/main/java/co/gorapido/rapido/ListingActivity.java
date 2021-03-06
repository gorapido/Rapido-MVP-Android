package co.gorapido.rapido;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by rapido on 6/16/15.
 */
public class ListingActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_frame);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fm = getFragmentManager();
        CompanyListFragment fragment = new CompanyListFragment();
        Intent i = getIntent();
        String currentCategory = i.getStringExtra(CompanyListFragment.CATEGORY);
        Bundle args = new Bundle();
        args.putString(CompanyListFragment.CATEGORY, currentCategory);
        fragment.setArguments(args);
        fm.beginTransaction().replace(R.id.frame_layout_list, fragment).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_default, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_logout:
                ParseHelper.logout();
                Intent i = new Intent(this, SignInActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
