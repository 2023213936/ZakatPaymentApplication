package com.example.zakatpaymentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvValueGold, tvZakatPayable,tvTotalZakat, viewValueGold, viewTypeGold, viewZakatPayable, viewTotalZakat;
    EditText etWeightGold, etCurrentGold;
    RadioButton btnWear, btnKeep;
    Button btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tvValueGold = findViewById(R.id.tvValueGold);
        tvZakatPayable = findViewById(R.id.tvZakatPayable);
        tvTotalZakat = findViewById(R.id.tvTotalZakat);
        viewValueGold = findViewById(R.id.viewValueGold);
        viewTypeGold = findViewById(R.id.viewTypeGold);
        viewZakatPayable = findViewById(R.id.viewZakatPayable);
        viewTotalZakat = findViewById(R.id.viewTotalZakat);
        etWeightGold = findViewById(R.id.etWeightGold);
        etCurrentGold = findViewById(R.id.etCurrentGold);
        btnWear = findViewById(R.id.btnWear);
        btnKeep = findViewById(R.id.btnKeep);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weight,value,uruf, totalGoldValue, zakatableValue, result;

                try {
                    weight = Double.parseDouble(etWeightGold.getText().toString());
                    value = Double.parseDouble(etCurrentGold.getText().toString());

                    if (btnKeep.isChecked()) {
                        uruf = 85.0;
                    } else if (btnWear.isChecked()) {
                        uruf = 200.0;
                    }else{
                        tvTotalZakat.setText("Please choose Type of Gold");
                        return;
                    }

                    totalGoldValue = weight * value;

                    if(weight>=uruf) {
                        zakatableValue = (weight-uruf) * value;
                        result = zakatableValue * 0.025;
                    }else{
                        zakatableValue = 0.0;
                        result = 0.0;
                    }
                    tvValueGold.setText(String.format("RM %.2f", totalGoldValue));
                    tvZakatPayable.setText(String.format("RM %.2f", zakatableValue));
                    tvTotalZakat.setText(String.format("RM %.2f", result));

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please fill in the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeightGold.setText("");
                etCurrentGold.setText("");
                tvValueGold.setText("RM 00.00");
                tvZakatPayable.setText("RM 00.00");
                tvTotalZakat.setText("RM 00.00");
                btnKeep.setChecked(false);
                btnWear.setChecked(false);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;

        }else if (selected == R.id.about_menu){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;

        }else if(selected == R.id.settings){
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}