package com.example.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogoActivity extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(LogoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
