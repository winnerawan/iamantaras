package com.mantambakberas.iamantaras.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.config.ApiInterface;
import com.mantambakberas.iamantaras.config.AppConfig;
import com.mantambakberas.iamantaras.helper.SQLiteHandler;
import com.mantambakberas.iamantaras.helper.SessionManager;
import com.mantambakberas.iamantaras.response.LoginResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private SQLiteHandler db;
    private SessionManager session;

    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.txtPassword) EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);

        if (session.isLoggedIn()) {
            // User sudah login, langsung ke main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        TextView bRegister = (TextView) findViewById(R.id.register);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });



        Button bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if(!email.isEmpty() && !password.isEmpty()) {
                    checkLogin(email, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Isi email / password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //check login
    private void checkLogin(final String email, final String password) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AppConfig.BASE_API_URL).build();
        ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
        pDialog.setMessage("Loading...");
        pDialog.show();
        apiInterface.login(email, password, new Callback<LoginResponse>() {

            @Override
            public void success(LoginResponse loginResponse, Response response) {
                pDialog.dismiss();
                boolean err = loginResponse.getError();
                if(!err) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    db.addUser(loginResponse.getName(), loginResponse.getEmail(), loginResponse.getApiKey(), loginResponse.getCreatedAt());
                    session.setLogin(true);
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                pDialog.dismiss();
            }
        });
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
