package com.example.task1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private double previousValue = 0;
    private String currentInput = "";
    private String currentOperator = "";
    private boolean startNewNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);

        setNumericListeners();
        setOperatorListeners();
        setClearListener();
        setEqualsListener();
    }

    private void setNumericListeners() {
        int[] numericButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            if (startNewNumber) {
                currentInput = button.getText().toString();
                startNewNumber = false;
            } else {
                currentInput += button.getText().toString();
            }
            updateDisplay(currentInput);
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorListeners() {
        int[] operatorButtons = {R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnPow};

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String op = button.getText().toString();
            if (op.equals("num")) op = "^";

            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                if (currentOperator.isEmpty()) {
                    previousValue = value;
                } else {
                    previousValue = calculate(previousValue, value, currentOperator);
                }
                updateDisplay(formatResult(previousValue));
                currentInput = "";
                startNewNumber = true;
            }
            currentOperator = op;
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setClearListener() {
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentInput = "";
            previousValue = 0;
            currentOperator = "";
            startNewNumber = true;
            updateDisplay("0");
        });
    }

    private void setEqualsListener() {
        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (!currentInput.isEmpty() && !currentOperator.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                previousValue = calculate(previousValue, value, currentOperator);
                updateDisplay(formatResult(previousValue));
                currentInput = "";
                currentOperator = "";
                startNewNumber = true;
            }
        });
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    updateDisplay("Error");
                    return 0;
                }
                return a / b;
            case "^": return Math.pow(a, b);
            default: return b;
        }
    }

    private void updateDisplay(String text) {
        display.setText(text);
    }

    private String formatResult(double d) {
        if (display.getText().toString().equals("Error")) return "Error";
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
