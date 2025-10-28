package com.example.lab_03.bai02_Calculator.core;

import java.util.Stack;

public class CalculatorCore {

    private final StringBuilder expr = new StringBuilder(); // lưu biểu thức hiện tại

    public String getExpression() { return expr.toString(); }
    public void setExpression(String s) { expr.setLength(0); expr.append(s); }
    public void clear() { expr.setLength(0); }
    public void append(String s) { expr.append(s); }
    public void deleteLast() { if (expr.length() > 0) expr.deleteCharAt(expr.length() - 1); }

    /* ==================== HÀM TIỆN ÍCH ==================== */
    private char last() { return expr.charAt(expr.length() - 1); }
    public boolean endsWithOpenNeg() { return expr.length() >= 2 && expr.substring(expr.length()-2).equals("(-"); }
    private boolean isOperator(char c) { return c=='+'||c=='-'||c=='×'||c=='/'||c=='*'; }
    private String normalizeMulDiv(String op) { return op.equals("×") ? "*" : op; }

    /* ==================== THÊM TOÁN TỬ (+, -, ×, /) ==================== */
    public void appendOperator(String op) {
        if (expr.length() == 0) return;
        if (isOperator(last())) expr.setCharAt(expr.length() - 1, normalizeMulDiv(op).charAt(0));
        else expr.append(normalizeMulDiv(op));
    }

    /* ==================== XỬ LÝ TOÁN HẠNG ÂM DƯƠNG (+/-) ==================== */
    public void toggleSignSmart() {
        if (expr.length() == 0) {        // Nếu rỗng → bắt đầu số âm đầu tiên
            expr.append("(-");
            return;
        }

        // 1️⃣ Nếu kết thúc bằng "(-" → toggle off (xóa đi)
        if (endsWithOpenNeg()) {
            expr.delete(expr.length()-2, expr.length());
            return;
        }

        // 2️⃣ Nếu ký tự cuối là toán tử hoặc '(' → mở ngoặc âm "(-"
        char last = last();
        if (isOperator(last) || last == '(') {
            expr.append("(-");
            return;
        }

        // 3️⃣ Ngược lại: đã có toán hạng cuối → toggle bọc âm quanh nó
        int[] span = findLastOperandSpan(expr.toString());
        if (span[0] == -1) {
            expr.append("(-");
            return;
        }

        String term = expr.substring(span[0], span[1] + 1);
        if (term.startsWith("(-") && term.endsWith(")")) {
            // Nếu đã là dạng (-x) → bỏ bọc âm
            expr.replace(span[0], span[1] + 1, term.substring(2, term.length() - 1));
        } else if (term.startsWith("(") && term.endsWith(")")) {
            // Nếu đã có ngoặc → thêm dấu - ngay sau '('
            expr.replace(span[0], span[1] + 1, "(-" + term.substring(1));
        } else {
            // Nếu chưa có gì → bọc âm chuẩn (-x)
            expr.replace(span[0], span[1] + 1, "(-" + term + ")");
        }
    }

    /* ==================== XỬ LÝ PHẦN TRĂM (%) ==================== */
    public void applyPercentSmart() {
        if (expr.length() == 0) return;
        if (isOperator(last())) expr.deleteCharAt(expr.length() - 1);

        int[] span = findLastOperandSpan(expr.toString());
        if (span[0] == -1) return;

        String term = expr.substring(span[0], span[1] + 1);

        // Nếu là số âm chưa bọc ngoặc → thêm ngoặc
        if (isPlainLeadingNegative(expr.toString(), span[0], term)) {
            term = "(" + term + ")";
        }

        // Nếu đã có % rồi → bọc thêm một lớp (xử lý % chồng)
        if (term.endsWith("%")) expr.replace(span[0], span[1] + 1, "(" + term + ")%");
        else expr.replace(span[0], span[1] + 1, term + "%");
    }

    private boolean isPlainLeadingNegative(String s, int start, String term) {
        if (term.isEmpty() || term.charAt(0) != '-') return false;
        if (start > 0) {
            char prev = s.charAt(start - 1);
            return isOperator(prev) || prev == '(';
        }
        return true;
    }

    /* ==================== TÌM TOÁN HẠNG CUỐI ==================== */
    private int[] findLastOperandSpan(String s) {
        if (s.isEmpty()) return new int[]{-1,-1};
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == '%') i--;
        if (i < 0) return new int[]{-1,-1};

        if (s.charAt(i) == ')') {
            int bal = 0, j = i;
            for (; j >= 0; j--) {
                char c = s.charAt(j);
                if (c == ')') bal++;
                else if (c == '(') bal--;
                if (bal == 0) break;
            }
            if (j < 0) return new int[]{-1,-1};
            return new int[]{j, i};
        }

        int end = i;
        while (i >= 0 && (Character.isDigit(s.charAt(i)) || s.charAt(i)=='.')) i--;
        if (i >= 0 && s.charAt(i) == '-') {
            boolean unary = (i==0) || isOperator(s.charAt(i-1)) || s.charAt(i-1)=='(';
            if (unary) i--;
        }
        return new int[]{i+1, end};
    }

    /* ==================== TỰ ĐÓNG NGOẶC KHI BẤM "=" ==================== */
    public String autoCloseParentheses(String s) {
        int open = 0, close = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') open++;
            else if (c == ')') close++;
        }
        StringBuilder fixed = new StringBuilder(s);
        while (open > close) { fixed.append(')'); close++; }
        return fixed.toString();
    }

    /* ==================== CHUYỂN % THÀNH /100 ==================== */
    public String expandPercent(String s) {
        s = s.replace('×', '*');
        StringBuilder sb = new StringBuilder(s);
        int pos;
        while ((pos = indexOfPercent(sb)) != -1) {
            int start = findOperandStartForPercent(sb, pos - 1);
            if (start == -1) break;
            String operand = sb.substring(start, pos);
            sb.replace(start, pos + 1, "(" + operand + "/100)");
        }
        return sb.toString();
    }

    private int indexOfPercent(StringBuilder sb) {
        for (int i=0;i<sb.length();i++) if (sb.charAt(i)=='%') return i;
        return -1;
    }

    private int findOperandStartForPercent(CharSequence s, int i) {
        if (i < 0) return -1;
        if (s.charAt(i) == ')') {
            int bal = 0;
            for (int j = i; j >= 0; j--) {
                char c = s.charAt(j);
                if (c == ')') bal++;
                else if (c == '(') bal--;
                if (bal == 0) return j;
            }
            return -1;
        }
        int j = i;
        while (j >= 0 && (Character.isDigit(s.charAt(j)) || s.charAt(j)=='.')) j--;
        if (j >= 0 && s.charAt(j) == '-') {
            if (j==0 || isOperator(s.charAt(j-1)) || s.charAt(j-1)=='(') j--;
        }
        return j+1;
    }

    /* ==================== HÀM EVALUATE (TÍNH GIÁ TRỊ BIỂU THỨC) ==================== */
    public double evaluate(String s) {
        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) continue;

            // Đọc số liên tục (bao gồm .)
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i)=='.'))
                    num.append(s.charAt(i++));
                i--;
                nums.push(Double.parseDouble(num.toString()));
            }
            // Gặp ngoặc mở
            else if (c == '(') {
                ops.push(c);
            }
            // Gặp ngoặc đóng
            else if (c == ')') {
                while (!ops.isEmpty() && ops.peek()!='(') popApply(nums, ops);
                if (!ops.isEmpty() && ops.peek()=='(') ops.pop();
            }
            // Gặp toán tử
            else if (isOpEval(c)) {
                // Xử lý dấu trừ đơn (unary -)
                if (c=='-' && (i==0 || s.charAt(i-1)=='(' || isOpEval(s.charAt(i-1)))) nums.push(0.0);
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) popApply(nums, ops);
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) popApply(nums, ops);
        return nums.isEmpty()?0:nums.pop();
    }

    private boolean isOpEval(char c){ return c=='+'||c=='-'||c=='*'||c=='/'; }
    private int precedence(char op){ return (op=='+'||op=='-')?1:(op=='*'||op=='/')?2:0; }

    /* ==================== ÁP DỤNG TOÁN TỬ ==================== */
    private void popApply(Stack<Double> nums, Stack<Character> ops){
        if (nums.size() < 2) return;
        double b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        switch (op){
            case '+': nums.push(a+b); break;
            case '-': nums.push(a-b); break;
            case '*': nums.push(a*b); break;
            case '/': nums.push(b==0 ? Double.NaN : a/b); break;
        }
    }

    /* ==================== ĐỊNH DẠNG KẾT QUẢ HIỂN THỊ ==================== */
    public String trimDouble(double d){
        if (Double.isNaN(d) || Double.isInfinite(d)) return "Lỗi";
        String s = Double.toString(d);
        if (s.endsWith(".0")) s = s.substring(0, s.length()-2);
        if (s.contains(".")) {
            while (s.endsWith("0")) s = s.substring(0, s.length()-1);
            if (s.endsWith(".")) s = s.substring(0, s.length()-1);
        }
        return s;
    }
}
