package edu.birzeit.advancecardealer.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import edu.birzeit.advancecardealer.R;

public class AddAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
    }



    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}