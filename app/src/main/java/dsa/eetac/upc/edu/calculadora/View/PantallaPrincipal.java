package dsa.eetac.upc.edu.calculadora.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dsa.eetac.upc.edu.calculadora.Controller.Brain;
import dsa.eetac.upc.edu.calculadora.Model.Operation;
import dsa.eetac.upc.edu.calculadora.R;
import android.widget.AdapterView.OnItemSelectedListener;


public class PantallaPrincipal extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener {

    private EditText firstNumber;
    private EditText secondNumber;
    private EditText resultNumber;
    private Button resultButton;
    private Button clearButton;
    private Button historyButton;
    private Spinner spinner;
    private static ArrayList<Operation> operationList = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallaprincipal);

        //Assignarem una variable a cada component de l'entorn gràfic
        firstNumber = (EditText) findViewById(R.id.firstNumber);
        secondNumber = (EditText) findViewById(R.id.secondNumber);
        resultButton = (Button) findViewById(R.id.resultButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        historyButton = (Button) findViewById(R.id.historyButton);
        resultNumber = (EditText) findViewById(R.id.resultNumber);


        //Li assignem un event OnClick a cada botó
        resultButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("+");
        categories.add("-");
        categories.add("*");
        categories.add("/");

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.resultButton:
                if (firstNumber.getText().length() != 0 && secondNumber.getText().length() != 0) {
                    try {
                        int no1 = Integer.parseInt(firstNumber.getText().toString());
                        int no2 = Integer.parseInt(secondNumber.getText().toString());

                        if(spinner.getSelectedItemId() == 0) {
                            Operation operation = new Operation(no1, no2, 0);
                            operation.setResult(Integer.parseInt(Brain.getInstance().getResultInString(operation)));
                            resultNumber.setText(Brain.getInstance().getResultInString(operation));
                            operationList.add(operation);
                        }
                        if(spinner.getSelectedItemId() == 1) {
                            Operation operation = new Operation(no1, no2, 1);
                            resultNumber.setText(Brain.getInstance().getResultInString(operation));
                            operationList.add(operation);
                        }
                        if(spinner.getSelectedItemId() == 2) {
                            Operation operation = new Operation(no1, no2, 2);
                            resultNumber.setText(Brain.getInstance().getResultInString(operation));
                            operationList.add(operation);
                        }
                        if(spinner.getSelectedItemId() == 3) {
                            Operation operation = new Operation(no1, no2, 3 );
                            resultNumber.setText(Brain.getInstance().getResultInString(operation));
                            operationList.add(operation);
                        }
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(),
                                "Please, insert only integer values", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Fill all the gaps", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.clearButton:
                firstNumber.setText("0");
                secondNumber.setText("0");
                resultNumber.setText("0");
                break;
            case R.id.historyButton:
                intent = new Intent(this, LlistaOperacions.class);
                intent.putParcelableArrayListExtra("data", operationList);
                startActivityForResult(intent,10);
            default:
                break;
        }
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            //super.onActivityResult(requestCode, resultCode, intent);
            switch(requestCode) {
                case (10) : {
                    if (resultCode == 13) {
                    // TODO Extract the data returned from the child Activity.
                    Operation operation = intent.getParcelableExtra("data2");
                    firstNumber.setText(operation.getFirst().toString());
                    secondNumber.setText(operation.getSecond().toString());
                    resultNumber.setText(operation.getResult().toString());
                    spinner.setSelection(operation.getType());
                }
                    if (resultCode == 21) {
                        // TODO Extract the data returned from the child Activity.
                        operationList.remove(intent.getIntExtra("int",0));
                        startActivityForResult(intent,10);
                    }
                    if (resultCode == 22) {
                        // TODO Extract the data returned from the child Activity.
                        operationList.clear();
                        startActivityForResult(intent,10);
                    }
                break;
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
