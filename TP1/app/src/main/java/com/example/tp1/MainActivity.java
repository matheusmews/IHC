package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private TextView textShowResult;

    private EditText editN1, editN2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        textShowResult = findViewById(R.id.textShowResult);

        textShowResult.setVisibility(View.INVISIBLE);

        editN1 = findViewById(R.id.editN1);
        editN2 = findViewById(R.id.editN2);
    }

    public void sum(View view) {
        int num1 = Integer.parseInt(editN1.getText().toString());
        int num2 = Integer.parseInt(editN2.getText().toString());

        textShowResult.setText(Integer.toString(num1+num2));

        textShowResult.setVisibility(View.VISIBLE);
    }
}