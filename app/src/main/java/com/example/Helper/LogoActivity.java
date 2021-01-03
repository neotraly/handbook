package com.example.Helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;


public class LogoActivity extends MainActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);


        TextView msg_txt = findViewById(R.id.txt_msg);
        final Button logo_btn = findViewById(R.id.logo_btn);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                msg_txt.setText("Вы можете использовать отпечаток пальца");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                msg_txt.setText("Устройство не поддерживает данную технологию");
                logo_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                msg_txt.setText("Отпечаток пальца в данный момент не доступен");
                logo_btn.setVisibility(View.GONE);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                msg_txt.setText("Ваше устройсто не имеет активных отпечатков пальца. Добавьте их через настройки устройства");
                logo_btn.setVisibility(View.GONE);
                break;

        }


        Executor executor = ContextCompat.getMainExecutor(this);

        final BiometricPrompt biometricPrompt = new BiometricPrompt(LogoActivity.this,
                executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(getApplicationContext(), "Вход подтвержден!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Вход в систему")
                .setDescription("Введите отпечаток пальца для входа в систему")
                .setNegativeButtonText("Cancel").build();

        biometricPrompt.authenticate(promptInfo);
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
