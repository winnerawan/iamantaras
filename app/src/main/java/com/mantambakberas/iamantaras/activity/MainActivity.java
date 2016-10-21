package com.mantambakberas.iamantaras.activity;

import android.content.Intent;
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
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.adapter.ListUsersAdapter;
import com.mantambakberas.iamantaras.config.ApiInterface;
import com.mantambakberas.iamantaras.config.AppConfig;
import com.mantambakberas.iamantaras.config.AppController;
import com.mantambakberas.iamantaras.helper.CircledNetworkImageView;
import com.mantambakberas.iamantaras.helper.SQLiteHandler;
import com.mantambakberas.iamantaras.helper.SessionManager;
import com.mantambakberas.iamantaras.model.MyInfo;
import com.mantambakberas.iamantaras.model.Users;
import com.mantambakberas.iamantaras.response.MyInfoResponse;
import com.mantambakberas.iamantaras.response.UsersResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private DrawerLayout mDrawerLayout;
    private SQLiteHandler db;
    private SessionManager session;
    public List<MyInfo> myInfo;
    String api_key,name,email;
    List<Users> users;
    RecyclerView recyclerView;

    // update & share
    @Bind(R.id.share) TextView act_share;
    @Bind(R.id.updateProfile) TextView act_profile;

    //Navigation action
    @Bind(R.id.name) TextView nameView;
    @Bind(R.id.email) TextView emailView;
    @Bind(R.id.foto) CircledNetworkImageView fotoView;

    @Bind(R.id.logout) TextView act_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

            session = new SessionManager(getApplicationContext());
            db = new SQLiteHandler(getApplicationContext());

            if (!session.isLoggedIn()) {
                logoutUser();
            }

            HashMap<String, String> user = db.getUserDetails();
            api_key = user.get("api_key");
            name = user.get("name");
            email = user.get("email");
            Log.e(TAG, "api_key = "+api_key);

            getListAllUsers();
            getMyInformation();
            nameView.setText(name);
            emailView.setText(email);

            act_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent prof = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(prof);
                }
            });

            act_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iShare = new Intent(Intent.ACTION_SEND);
                    iShare.setType("text/plain");
                    iShare.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name_caps));
                    String share = "\n"+getResources().getString(R.string.app_full_name_caps)+"\n";
                    share = share + "https://play.google.com/store/apps/details?id=com.mantambakberas.iamantaras";
                    iShare.putExtra(Intent.EXTRA_TEXT, share);
                    startActivity(Intent.createChooser(iShare, "Share..."));
                }
            });

            //action logout
            act_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logoutUser();
                }
            });
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

    private void getMyInformation() {
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
        apiInterface.getmyinfo(new Callback<MyInfoResponse>() {
            @Override
            public void success(MyInfoResponse myInfoResponse, Response response) {
                myInfo = myInfoResponse.getMyInfo();
                String default_foto = AppConfig.BASE_API_URL+"/api/v1/default-foto.png";
                if (myInfoResponse.getMyInfo().size()!=0) {
                    String foto = myInfo.get(0).getFoto().toString();
                    fotoView.setImageUrl(foto, imageLoader);
                } else {
                    fotoView.setImageUrl(default_foto, imageLoader);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "ERR "+error.toString());
            }
        });
    }
    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        // kembali ke login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
