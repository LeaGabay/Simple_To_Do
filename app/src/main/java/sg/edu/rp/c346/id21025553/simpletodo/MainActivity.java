package sg.edu.rp.c346.id21025553.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etText;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTasks;
    Spinner spinner;
    ArrayList<String> alTasks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking variables to UI elements
        etText = findViewById(R.id.editTextTasks);

        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);

        lvTasks = findViewById(R.id.lvTasks);

        spinner = findViewById(R.id.spinnerTasks);

        ArrayAdapter aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.add(etText.getText().toString());
                aaTasks.notifyDataSetChanged();
                etText.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                etText.setText("");
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!alTasks.isEmpty()) {
                    int pos = Integer.parseInt(etText.getText().toString());
                    if (pos < alTasks.size() && pos > - 1) {
                        alTasks.remove(pos);
                        aaTasks.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                    etText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etText.setHint(getString(R.string.etAddHint));
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etText.setHint(getString(R.string.etDeleteHint));
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}