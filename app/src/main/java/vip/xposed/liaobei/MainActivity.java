package vip.xposed.liaobei;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import vip.xposed.liaobei.Utils.SettingUtil;


public class MainActivity extends AppCompatActivity {
    EditText et_token;
    Button bt_save_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnListener();

    }

    private void setOnListener() {
        et_token = findViewById(R.id.et_token);
        bt_save_config = findViewById(R.id.bt_save_config);

        et_token.setText(new SettingUtil(getApplicationContext()).getToken());


        bt_save_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SettingUtil(getApplicationContext()).setToken(et_token.getText().toString());
                Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG).show();
            }
        });
    }


}
