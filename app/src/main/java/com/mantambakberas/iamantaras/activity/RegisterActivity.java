package com.mantambakberas.iamantaras.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.config.ApiInterface;
import com.mantambakberas.iamantaras.config.AppConfig;
import com.mantambakberas.iamantaras.response.ApiResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @Bind(R.id.bRegister) Button register;
    @Bind(R.id.txtName) EditText txtName;
    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.txtPassword) EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
            toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = txtName.getText().toString().trim();
                    String email = txtEmail.getText().toString().trim();
                    String pass = txtPassword.getText().toString().toString();
                    if(!name.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
                        registerUser(name, email, pass);
                    } else {
                        Toast.makeText(getApplicationContext(), "Silahkan isi semua form yang ada", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    //register logic
    private void registerUser(final String name, final String email, final String password) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AppConfig.BASE_API_URL).build();
        ApiInterface apiInterface = restAdapter.create(ApiInterface.class);

        apiInterface.register(name, email, password, new Callback<ApiResponse>() {
            @Override
            public void success(ApiResponse apiResponse, Response response) {
                boolean err = apiResponse.getError();
                if (!err) {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    String message_error = apiResponse.getMessage();
                    Toast.makeText(getApplicationContext(), message_error, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        });
    }
    //Fix Bug status bar transluscend
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
