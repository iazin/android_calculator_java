package com.iazin.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private TextView resultTextView;
    private ListView historyListView;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> historyList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.input_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        historyListView = findViewById(R.id.history_list_view);

        historyList = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(historyAdapter);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        inputEditText.append(button.getText());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        inputEditText.append(button.getText());
    }

    public void onFunctionClick(View view) {
        Button button = (Button) view;
        inputEditText.append(button.getText() + "(");
    }

    public void calculate(View view) {
        String input = inputEditText.getText().toString();
        try {
            double result = evaluateExpression(input);
            resultTextView.setText(String.valueOf(result));
            addToHistory(input, result);
        } catch (Exception e) {
            resultTextView.setText("Error in expression");
        }
    }

    private void addToHistory(String expression, double result) {
        historyList.add(expression + " = " + result);
        historyAdapter.notifyDataSetChanged();
    }

    private double evaluateExpression(String expression) {
        Expression exp = new ExpressionBuilder(expression).build();
        return exp.evaluate();
    }

    public void onClearClick(View view) {
        inputEditText.setText("");
        resultTextView.setText("");
    }
}

