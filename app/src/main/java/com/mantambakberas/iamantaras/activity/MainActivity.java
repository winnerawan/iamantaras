package com.mantambakberas.iamantaras.activity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.adapter.ListUsersAdapter;
import com.mantambakberas.iamantaras.config.ApiInterface;
import com.mantambakberas.iamantaras.config.AppConfig;
import com.mantambakberas.iamantaras.helper.SQLiteHandler;
import com.mantambakberas.iamantaras.model.Users;
import com.mantambakberas.iamantaras.response.UsersResponse;

import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private SQLiteHandler db;
    String api_key;
    List<Users> users;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), android.R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setTitle("");
            BitmapDrawable toolbar_bg = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.toolbar_bg));
            supportActionBar.setBackgroundDrawable(toolbar_bg);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            //toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
            recyclerView = (RecyclerView) findViewById(R.id.re_list_users);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            db = new SQLiteHandler(getApplicationContext());

            HashMap<String, String> user = db.getUserDetails();
            api_key = user.get("api_key");
            Log.e(TAG, "api_key = "+api_key);

            getListAllUsers();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {
                            case R.id.action_about:
                                //Intent i = new Intent(MainActivity.this, AboutActivity.class);
                                //startActivity(i);
                                return true;

                            case R.id.action_disclaimer:
                                //Intent ii = new Intent(MainActivity.this, DisclaimerActivity.class);
                                //startActivity(ii);
                                return true;

                            case R.id.action_feedback:
                                //Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                //emailIntent.setData(Uri.parse("mailto: admin@wonderwall.biz.id"));
                                //startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                                return true;

                        }
                        // TODO: handle navigation
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_disclaimer) {
        //    Intent i = new Intent(MainActivity.this, AboutActivity.class);
        //    startActivity(i);
         if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    //Fix Bug status bar transluscend, tinggi action bar konyol || gak perlu kalo ada navigation drawer
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void getListAllUsers() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Authorization", api_key);
                        Log.e(TAG, request.toString());
                    }
                })
                .setEndpoint(AppConfig.BASE_API_URL)
                .build();
        ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
        apiInterface.getlistusers(new Callback<UsersResponse>() {
            @Override
            public void success(UsersResponse usersResponse, Response response) {
                users = usersResponse.getUsers();
                recyclerView.setAdapter(new ListUsersAdapter(users, R.layout.list_users, getApplicationContext()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        });
    }
}
