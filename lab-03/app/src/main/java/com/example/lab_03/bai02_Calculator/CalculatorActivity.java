package com.example.lab_03.bai02_Calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_03.R;
import com.example.lab_03.bai02_Calculator.core.CalculatorCore;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvExpression, tvResult;
    private final CalculatorCore calc = new CalculatorCore(); // core xử lý logic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai02_activity_calculator);
        setTitle("Máy tính cơ bản");

        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);

        /* ==================== NHÓM NÚT SỐ VÀ DẤU CHẤM ==================== */
        int[] digits = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        };
        for (int id : digits) {
            Button b = findViewById(id);
            // Khi bấm số hoặc dấu ., nối chuỗi vào biểu thức
            b.setOnClickListener(v -> {
                calc.append(b.getText().toString());
                updateExpression();
            });
        }

        /* ==================== NHÓM TOÁN TỬ NHỊ PHÂN ==================== */
        bindOp(R.id.btnAdd, "+");
        bindOp(R.id.btnSub, "-");
        bindOp(R.id.btnMul, "×");
        bindOp(R.id.btnDiv, "/");

        /* ==================== XÓA 1 KÝ TỰ ==================== */
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            calc.deleteLast();
            updateExpression();
        });

        /* ==================== XÓA TOÀN BỘ (AC) ==================== */
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            calc.clear();
            updateExpression();
            tvResult.setText("");
        });

        /* ==================== NÚT +/- (ĐẢO DẤU) ==================== */
        // - Nếu đang trống: thêm "(-" để bắt đầu số âm
        // - Nếu vừa mở "(-": bấm lại để hủy
        // - Nếu sau toán tử: thêm "(-" để chuẩn bị nhập số âm
        // - Nếu đang có toán hạng: toggle bọc âm quanh nó
        findViewById(R.id.btnNegate).setOnClickListener(v -> {
            calc.toggleSignSmart();
            updateExpression();
        });

        /* ==================== NÚT % (PHẦN TRĂM) ==================== */
        // - Không áp dụng khi đang ở trạng thái "(-"
        // - Áp dụng như hậu tố (vd: 25% -> (25/100))
        findViewById(R.id.btnPercent).setOnClickListener(v -> {
            if (calc.endsWithOpenNeg()) return; // đang mở "(-" thì bỏ qua
            calc.applyPercentSmart();
            updateExpression();
        });

        /* ==================== NÚT "=" (TÍNH KẾT QUẢ) ==================== */
        // - Tự động đóng ngoặc còn thiếu (vd: 1+(-3 -> 1+(-3))
        // - Hiển thị lại biểu thức đã hoàn chỉnh
        // - Mở rộng x% thành (x/100)
        // - Đánh giá kết quả và giữ lại biểu thức hợp lệ
        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            try {
                String raw = calc.getExpression();
                String fixed = calc.autoCloseParentheses(raw);     // tự đóng ngoặc lửng
                tvExpression.setText(fixed);                       // hiển thị lại
                String expanded = calc.expandPercent(fixed);       // chuyển % -> (/100)
                double val = calc.evaluate(expanded);              // tính giá trị
                tvResult.setText(calc.trimDouble(val));            // hiển thị kết quả
                calc.setExpression(fixed);                         // lưu lại biểu thức hợp lệ
            } catch (Exception e) {
                tvResult.setText("Lỗi");
            }
        });

        /* ==================== QUAY VỀ MÀN CHÍNH ==================== */
        Button back = findViewById(R.id.btnBack);
        if (back != null) back.setOnClickListener(v -> finish());
    }

    /* ==================== HÀM HỖ TRỢ GẮN TOÁN TỬ ==================== */
    private void bindOp(int id, String op) {
        findViewById(id).setOnClickListener(v -> {
            calc.appendOperator(op);
            updateExpression();
        });
    }

    /* ==================== CẬP NHẬT HIỂN THỊ BIỂU THỨC ==================== */
    private void updateExpression() {
        tvExpression.setText(calc.getExpression());
    }
}
