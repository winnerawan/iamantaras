package com.mantambakberas.iamantaras.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.config.ApiInterface;
import com.mantambakberas.iamantaras.config.AppConfig;
import com.mantambakberas.iamantaras.config.AppController;
import com.mantambakberas.iamantaras.helper.CircledNetworkImageView;
import com.mantambakberas.iamantaras.helper.SQLiteHandler;
import com.mantambakberas.iamantaras.model.MyInfo;
import com.mantambakberas.iamantaras.response.MyInfoResponse;
import com.transitionseverywhere.Slide;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private SQLiteHandler db;
    String name,email,foto,info,profesi,penghargaan,pelatihan,minat,rekomendasi,api_key;
    List<MyInfo> myInfo;
    @Bind(R.id.foto) CircledNetworkImageView fotoView;
    @Bind(R.id.name) TextView nameView;
    @Bind(R.id.info) TextView infoView;
    @Bind(R.id.email) TextView emailView;
    @Bind(R.id.profesi) TextView profesiView;
    @Bind(R.id.pelatihan) TextView pelatihanView;
    @Bind(R.id.penghargaan) TextView penghargaanView;
    @Bind(R.id.referensi) TextView referensiView;
    @Bind(R.id.minat) TextView minatView;
    @Bind(R.id.act_setelan) TextView act_setelan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        }

        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        name = user.get("name");
        email = user.get("email");
        api_key = user.get("api_key");
/*
        Bundle bundle = getIntent().getExtras();
        foto = bundle.getString("foto");
        info = bundle.getString("info");
        profesi = bundle.getString("profesi");
        */
        nameView.setText(name);
        emailView.setText(email);
        getMyInformation();
        //infoView.setText(info);
        //fotoView.setImageUrl(foto, imageLoader);

        act_setelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, SetelanActivity.class);
                startActivity(i);

            }
        });
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
                if(myInfoResponse.getMyInfo().size()==0) {
                    fotoView.setImageUrl(AppConfig.DEFAULT_FOTO, imageLoader);
                    infoView.setText("");
                    profesiView.setText(getResources().getString(R.string.profesi_null));
                    referensiView.setText(getResources().getString(R.string.profesi_null));
                    pelatihanView.setText(getResources().getString(R.string.profesi_null));
                    penghargaanView.setText(getResources().getString(R.string.profesi_null));
                    minatView.setText(getResources().getString(R.string.profesi_null));
                } else {
                fotoView.setImageUrl(myInfo.get(0).getFoto().toString(), imageLoader);
                infoView.setText(myInfo.get(0).getJurusan()+" - "
                        +myInfo.get(0).getAngkatan());
                profesi = myInfo.get(0).getProfesi();
                rekomendasi = myInfo.get(0).getReferensiRekomendasi();
                penghargaan = myInfo.get(0).getPenghargaan();
                minat = myInfo.get(0).getMinatProfesi();
                pelatihan = myInfo.get(0).getKeahlian();
                    if (profesi.equals("")) {
                        profesiView.setText(getResources().getString(R.string.profesi_null));
                    } else {
                        profesiView.setText(profesi);
                    }
                    if (rekomendasi.equals("")) {
                        referensiView.setText(getResources().getString(R.string.profesi_null));
                    } else {
                        referensiView.setText(rekomendasi);
                    } if (penghargaan.equals("")) {
                        penghargaanView.setText(getResources().getString(R.string.profesi_null));
                    } else {
                        penghargaanView.setText(penghargaan);
                    } if (pelatihan.equals("")) {
                        pelatihanView.setText(getResources().getString(R.string.profesi_null));
                    } else {
                        pelatihanView.setText(pelatihan);
                    } if (minat.equals("")) {
                        minatView.setText(getResources().getString(R.string.profesi_null));
                    } else {
                        minatView.setText(minat);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "ERR " + error.toString());
            }
        });
    }
}
