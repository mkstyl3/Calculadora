package dsa.eetac.upc.edu.calculadora.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import dsa.eetac.upc.edu.calculadora.Model.Operation;
import dsa.eetac.upc.edu.calculadora.R;

/**
 * Created by root on 27/05/17.
 */

public class LlistaOperacions extends AppCompatActivity implements View.OnClickListener {
    private Button deleteAllButton;
    private Button returnButton;
    private ListView operationListView;
    private static ArrayList<Operation> operationList;
    private ArrayList<String> operationStringList;
    private int positionGlobal;
    Intent intent3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llistaoperacions);
        deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
        deleteAllButton.setOnClickListener(this);
        returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);
        intent3 = getIntent();
        operationList = intent3.getParcelableArrayListExtra("data");
        operationStringList = new ArrayList<>();
        buildArrayListofStrings(operationList);
        operationListView = (ListView) findViewById(R.id.operationListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, operationStringList);
        operationListView.setAdapter(arrayAdapter);
        operationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                positionGlobal = position;
                Intent intent2 = new Intent(getBaseContext(), TractamentOperacio.class);
                intent2.putExtra("data", operationList.get(position));
                startActivityForResult(intent2,11);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.returnButton:
                finish();
                break;
            case R.id.deleteAllButton:
                operationList.clear();
                setResult(22, intent3);
                finish();
                break;
        }
    }

    public void buildArrayListofStrings(ArrayList<Operation> operations) {

        for (int i=0; i<operations.size(); i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(operations.get(i).getId()).append(": ").append(operations.get(i).getFirst()).append(" ").append(operations.get(i).getTypeSymbol()).append(" ").append(operations.get(i).getSecond()).append(" = ").append(operations.get(i).getResult());
            operationStringList.add(sb.toString());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case (11) : {
                if (resultCode == 12) {
                    // TODO Extract the data returned from the child Activity.
                    intent3.putExtra("data2", operationList.get(positionGlobal));
                    setResult(13, intent3);
                    finish();
                }
                if (resultCode == 20) {
                    operationList.remove(intent3.getIntExtra("int", 0));
                    setResult(21, intent3);
                    finish();
                }
                break;
            }

        }
    }




}
