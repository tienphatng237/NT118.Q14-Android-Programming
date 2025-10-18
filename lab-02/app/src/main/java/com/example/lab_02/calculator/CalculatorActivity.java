package com.example.lab_02.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_02.R;

import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvExpression, tvResult;
    private StringBuilder currentExpression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("Máy tính cơ bản");

        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnDot
        };

        // Gán sự kiện click cho các nút số & toán tử
        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                currentExpression.append(btn.getText());
                tvExpression.setText(currentExpression.toString());
            });
        }

        // Nút AC (xóa)
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> {
            currentExpression.setLength(0);
            tvExpression.setText("");
            tvResult.setText("");
        });

        // Nút =
        Button btnEqual = findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(v -> {
            try {
                double result = evaluateExpression(currentExpression.toString());
                tvResult.setText(String.valueOf(result));
            } catch (Exception e) {
                tvResult.setText("Lỗi");
            }
        });
    }

    /**
     * Hàm tính toán biểu thức cơ bản (+ - * /)
     */
    private double evaluateExpression(String expr) {
        expr = expr.replaceAll("\\s+", "");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (c == '+' || c == '-' || c == '×' || c == '/' || c == '*') {
                while (!operations.isEmpty() && precedence(operations.peek()) >= precedence(c)) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operations.pop();
                    numbers.push(applyOperation(a, b, op));
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operations.pop();
            numbers.push(applyOperation(a, b, op));
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '×' || op == '/') return 2;
        return 0;
    }

    private double applyOperation(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*':
            case '×': return a * b;
            case '/': return b != 0 ? a / b : 0;
            default: return 0;
        }
    }
}
