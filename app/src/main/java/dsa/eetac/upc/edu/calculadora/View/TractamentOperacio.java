package dsa.eetac.upc.edu.calculadora.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dsa.eetac.upc.edu.calculadora.Model.Operation;
import dsa.eetac.upc.edu.calculadora.R;

/**
 * Created by root on 27/05/17.
 */

public class TractamentOperacio extends AppCompatActivity implements View.OnClickListener {

    private EditText selectedOperation;
    private Button editButton;
    private Button deleteButton;
    private Operation operation;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractamentoperacio);
        editButton = (Button) findViewById(R.id.editButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        selectedOperation = (EditText) findViewById(R.id.selectedOperation);
        intent = getIntent();
        operation = intent.getParcelableExtra("data");
        setResult(12,intent);
        selectedOperation.setText(stringifyOperation(operation));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editButton:
                finish();
            case R.id.deleteButton:
                intent.putExtra("int", operation.getId());
                setResult(20, intent);
                finish();
        }
    }

    public String stringifyOperation(Operation operation) {
        StringBuffer sb = new StringBuffer();
        sb.append(operation.getId()).append(": ").append(operation.getFirst()).append(" ").append(operation.getTypeSymbol()).append(" ").append(operation.getSecond()).append(" = ").append(operation.getResult());
        return sb.toString();
    }

}
