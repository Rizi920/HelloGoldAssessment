package com.example.hellogold.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hellogold.R;
import com.example.hellogold.models.signUp.SignUpResponse;
import com.example.hellogold.network.ApiClient;
import com.example.hellogold.network.ApiServices;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ApiServices apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = ApiClient.registerUser().create(ApiServices.class);

        Button signUp = findViewById(R.id.btn_sign_up);
        signUp.setOnClickListener(v -> {
            EditText email = findViewById(R.id.et_email);
            EditText data = findViewById(R.id.et_data_signup);
            CheckBox termsAndConditions = findViewById(R.id.checkBox);

            if (isValidEmailAddress(email.getText().toString())) {

            } else {
                email.setError("Please Enter a valid Email Address");
                requestFocus(email);
                return;
            }
            if (isValidData(data.getText().toString())) {

            } else {
                data.setError("Data field con not be empty");
                requestFocus(data);
                return;
            }
            if (termsAndConditions.isChecked()) {

            } else {
                makeToastMessage("Please Agree to terms And Conditions to continue");
                return;
            }

            registerUser(email.getText().toString(), data.getText().toString(), termsAndConditions.isChecked());
        });
    }

    private Boolean isValidData(String data) {
        if (data.isEmpty()) {
            return false;
        } else {
        }
        return true;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private void requestFocus(View view) {
        InputMethodManager imgr = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
        view.requestFocus();
    }

    private void makeToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void registerUser(String email, String data, Boolean tnc) {
        String uuid = UUID.randomUUID().toString();
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        apiInterface.insertUser(email, uuid, data, tnc).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.code() == 200) {
                    mProgressDialog.dismiss();
                    makeToastMessage("Registration Successful");
                    Intent myIntent = new Intent(MainActivity.this, DashBoardActivity.class);
                    myIntent.putExtra("email", email);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.this.startActivity(myIntent);

                } else {
                    mProgressDialog.dismiss();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("User Already Registered, Please Use a different Email Address")
                            .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("Error", t.toString());
                mProgressDialog.dismiss();

            }
        });
    }

}