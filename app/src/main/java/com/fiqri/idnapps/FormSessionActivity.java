package com.fiqri.idnapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormSessionActivity extends AppCompatActivity {

    //TODO hubunngin dan kenalin
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_session);
        ButterKnife.bind(this);
    }

    //TODO method onClick
    @OnClick(R.id.button)
    public void onViewClicked() {

        String hasil = editText.getText().toString();
        //TODO ketika kosong
        if (TextUtils.isEmpty(hasil)) {
            Toast.makeText(this, "Masih Kosong Kok", Toast.LENGTH_SHORT).show();
        } else {
            //TODO ketika ada stringnya memanggil session
            SessionPreference mSession = new SessionPreference(FormSessionActivity.this);
            //TODO set nama
            mSession.setNama(hasil);
            //TODO pindah activity
            startActivity(new Intent(FormSessionActivity.this, MainActivity.class));
            //TODO menutup activity
            finish();
        }
    }

    public static class SessionPreference {
        //TODO kenalin
        String KEY_NAME = "NAMA";
        String PREF_NAME = "SIMPAN";
        //TODO panggil Sharedpreference
        SharedPreferences mSharedPreferences;
        SharedPreferences.Editor mEditor;

        //TODO konstruktor
        public SessionPreference(Context context) {
            mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        //TODO SET_NAME (POSISI LOGIN)
        public void setNama(String nama) {
            mEditor = mSharedPreferences.edit();
            mEditor.putString(KEY_NAME, nama).apply();
        }

        //TODO GET_NAME (CEK LOGIN)
        public String getNama() {
            return mSharedPreferences.getString(KEY_NAME, null);
        }

        //TODO LOGOUT (MENGHAPUS)
        public void logout() {
            mEditor = mSharedPreferences.edit();
            mEditor.clear().commit();
        }
    }
}
