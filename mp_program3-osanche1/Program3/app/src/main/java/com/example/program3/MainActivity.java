package com.example.program3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;

public class MainActivity extends Activity {
    Button negate, zero, decimal, equals, one, two, three, plus, four, five, six, minus, seven,
            eight, nine, multiply, powerOf, clear, delete, divide;
    TextView numberField, result;

    private String holder = "00";
    private String operation = "null";
    private String prevResult = "null";
    private String prevOperand = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        negate=(Button)findViewById(R.id.negate);
        negate.setOnClickListener(bttnListener);

        zero=(Button)findViewById(R.id.zero);
        zero.setOnClickListener(bttnListener);

        decimal=(Button)findViewById(R.id.decimal);
        decimal.setOnClickListener(bttnListener);

        equals=(Button)findViewById(R.id.equals);
        equals.setOnClickListener(bttnListener);

        one=(Button)findViewById(R.id.one);
        one.setOnClickListener(bttnListener);

        two=(Button)findViewById(R.id.two);
        two.setOnClickListener(bttnListener);

        three=(Button)findViewById(R.id.three);
        three.setOnClickListener(bttnListener);

        plus=(Button)findViewById(R.id.plus);
        plus.setOnClickListener(bttnListener);

        four=(Button)findViewById(R.id.four);
        four.setOnClickListener(bttnListener);

        five=(Button)findViewById(R.id.five);
        five.setOnClickListener(bttnListener);

        six=(Button)findViewById(R.id.six);
        six.setOnClickListener(bttnListener);

        minus=(Button)findViewById(R.id.minus);
        minus.setOnClickListener(bttnListener);

        seven=(Button)findViewById(R.id.seven);
        seven.setOnClickListener(bttnListener);

        eight=(Button)findViewById(R.id.eight);
        eight.setOnClickListener(bttnListener);

        nine=(Button)findViewById(R.id.nine);
        nine.setOnClickListener(bttnListener);

        multiply=(Button)findViewById(R.id.multiply);
        multiply.setOnClickListener(bttnListener);

        powerOf=(Button)findViewById(R.id.powerOf);
        powerOf.setOnClickListener(bttnListener);

        clear=(Button)findViewById(R.id.clear);
        clear.setOnClickListener(bttnListener);

        delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(bttnListener);

        divide=(Button)findViewById(R.id.divide);
        divide.setOnClickListener(bttnListener);

        numberField = (TextView)findViewById(R.id.numberView);
        result = (TextView)findViewById(R.id.Result);

        numberField.setText("");
        result.setText("0");


    }

    View.OnClickListener bttnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v){
            DecimalFormat df = new DecimalFormat("#,###,##0.0000");
            if(v == zero && holder.length()<14 && !holder.equals("0")){
                if(holder.equals("00")){
                    holder = "0";
                    setResult(holder);

                }
                else{
                    holder = holder + "0";
                    setResult(holder);
                }
            }

            else if(v == one && holder.length()<14){
                if(holder.equals("00")){
                    holder = "1";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "1";
                    setResult(holder);
                }
                else{
                    holder = holder + "1";
                    setResult(holder);
                }
            }

            else if(v == two && holder.length()<14){
                if(holder.equals("00")){
                    holder = "2";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "2";
                    setResult(holder);
                }
                else{
                    holder = holder + "2";
                    setResult(holder);
                }
            }

            else if(v == three && holder.length()<14){
                if(holder.equals("00")){
                    holder = "3";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "3";
                    setResult(holder);
                }
                else{
                    holder = holder + "3";
                    setResult(holder);
                }
            }

            else if(v == four && holder.length()<14){
                if(holder.equals("00")){
                    holder = "4";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "4";
                    setResult(holder);
                }
                else{
                    holder = holder + "4";
                    setResult(holder);
                }
            }

            else if(v == five && holder.length()<14){
                if(holder.equals("00")){
                    holder = "5";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "5";
                    setResult(holder);
                }
                else{
                    holder = holder + "5";
                    setResult(holder);
                }
            }

            else if(v == six && holder.length()<14){
                if(holder.equals("00")){
                    holder = "6";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "6";
                    setResult(holder);
                }
                else{
                    holder = holder + "6";
                    setResult(holder);
                }
            }

            else if(v == seven && holder.length()<14){
                if(holder.equals("00")){
                    holder = "7";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "7";
                    setResult(holder);
                }
                else{
                    holder = holder + "7";
                    setResult(holder);
                }
            }

            else if(v == eight && holder.length()<14){
                if(holder.equals("00")){
                    holder = "8";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "8";
                    setResult(holder);
                }
                else{
                    holder = holder + "8";
                    setResult(holder);
                }
            }

            else if(v == nine && holder.length()<14){
                if(holder.equals("00")){
                    holder = "9";
                    setResult(holder);
                }
                else if(holder.equals("0")){
                    holder = "9";
                    setResult(holder);
                }
                else{
                    holder = holder + "9";
                    setResult(holder);
                }
            }

            else if(v == decimal && holder.length()<14){
                int isPresent = holder.indexOf('.');
                if(isPresent == -1){
                    if(holder == "00"){
                        holder = "0";
                    }
                    holder = holder + ".";
                    setResult(holder);
                }
            }

            else if(v == negate && !holder.equals("00") && !holder.equals("0")) {
                if (holder.charAt(0) == '-') {
                    holder = holder.substring(1);
                    setResult(holder);
                } else {
                    holder = "-" + holder;
                    setResult(holder);
                }

            }

            else if(v == clear){
                clear();
            }

            else if(v == delete && !holder.equals("00")){
                if(holder.length()==1){
                    result.setText("0");
                    holder = "00";
                }
                else if(holder.length()>1) {
                    if(holder.length()==2 && holder.indexOf('-') != -1){
                        result.setText("0");
                        holder = "00";
                    }
                    else {
                        holder = holder.substring(0, holder.length() - 1);
                        setResult(holder);
                    }
                }
            }

            else if(v==plus || v==divide || v==multiply||v==powerOf||v==minus){
                if(prevResult.equals("null")){
                    if(holder.equals("00") || holder.equals("0")){
                        prevResult = "0";
                    }
                    else{
                        prevResult = holder;
                        holder = "00";
                    }
                    if(v==plus){
                        numberField.setText(prevResult + "+");
                        operation = "+";
                    }
                    else if(v==minus){
                        numberField.setText(prevResult + "-");
                        operation = "-";
                    }
                    else if(v==divide){
                        numberField.setText(prevResult + "/");
                        operation = "/";
                    }
                    else if(v==multiply){
                        numberField.setText(prevResult + "*");
                        operation = "*";
                    }
                    else if(v==powerOf){
                        numberField.setText(prevResult + "^");
                        operation = "^";
                    }
                }
                else if(!prevResult.equals("null") && holder.equals("00")){
                    if(v==plus) {
                        numberField.setText(prevResult + "+");
                        operation = "+";
                    }
                    else if(v==minus) {
                        numberField.setText(prevResult + "-");
                        operation = "-";
                    }
                    else if(v==divide) {
                        numberField.setText(prevResult + "/");
                        operation = "/";
                    }
                    else if(v==multiply) {
                        numberField.setText(prevResult + "*");
                        operation = "*";
                    }
                    else if(v==powerOf) {
                        numberField.setText(prevResult + "^");
                        operation = "^";
                    }
                }
                else if(!prevResult.equals("null") && !holder.equals("00")){
                    prevOperand = holder;
                    double first = Double.parseDouble(prevResult.replaceAll(",", ""));
                    double second = Double.parseDouble(prevOperand.replaceAll(",", ""));
                    double answer = 0;
                    if(operation.equals("+")) {
                        answer = first + second;
                    }
                    else if(operation.equals("-")){
                        answer = first - second;
                    }
                    else if(operation.equals("/")){
                        answer = first / second;
                    }
                    else if(operation.equals("*")){
                        answer = first * second;
                    }
                    else if(operation.equals("^")){
                        answer = Math.pow(first, second);
                    }
                    prevResult = df.format(answer);
                    if(answer%1==0){
                        prevResult= prevResult.substring(0, prevResult.length()-5);
                    }
                    prevOperand = prevResult;
                    result.setText(prevResult);
                    if(v==plus) {
                        operation = "+";
                    }
                    else if(v==minus){
                        operation = "-";
                    }
                    else if(v==divide){
                        operation = "/";
                    }
                    else if(v==multiply){
                        operation = "*";
                    }
                    else if(v==powerOf){
                        operation = "^";
                    }
                    numberField.setText(prevResult + operation);
                    holder = "00";
                }
            }
            else if(v==equals){
                if(prevResult.equals("null") && (holder.equals("00") || holder.equals("0"))){
                    result.setText("0");
                    numberField.setText("0 =");
                    holder = "00";
                    prevResult = "0";
                }
                else if(prevResult.equals("null") && (!holder.equals("00") || !holder.equals("0"))){
                    result.setText(holder);
                    numberField.setText(holder + "=");
                    prevResult = holder;
                    holder = "00";
                }
                else if(!prevResult.equals("null") && holder.equals("00")){
                    double number = Double.parseDouble(prevResult.replaceAll(",", ""));
                    double number2 = Double.parseDouble(prevOperand.replaceAll(",", ""));
                    if(operation == "+"){
                        number = number + number2;
                    }
                    else if(operation == "-"){
                        number = number - number2;
                    }
                    else if(operation == "/"){
                        number = number / number2;
                    }
                    else if(operation == "*"){
                        number = number * number2;
                    }
                    else if(operation == "^"){
                        number = Math.pow(number, number2);
                    }
                    if(!operation.equals("")) {
                        numberField.setText(prevResult + operation + prevOperand + "=");
                    }
                    prevResult = df.format(number);
                    if(number%1==0){
                        prevResult = prevResult.substring(0, prevResult.length()-5);
                    }
                    result.setText(prevResult);
                    holder = "00";
                }
                else if(!prevResult.equals("null") && !holder.equals("00")){
                    //double first = Double.parseDouble(prevResult);
                    prevOperand = holder;
                    holder = "00";
                    double first = Double.parseDouble(prevResult.replaceAll(",", ""));
                    double second = Double.parseDouble(prevOperand.replaceAll(",", ""));
                    numberField.setText(prevResult + operation + prevOperand + "=");
                    double answer = 0;
                    if(operation == "+"){
                        answer = first + second;
                    }
                    else if(operation == "-"){
                        answer = first - second;
                    }
                    else if(operation == "/"){
                        answer = first / second;
                    }
                    else if(operation == "*"){
                        answer = first * second;
                    }
                    else if(operation == "^"){
                        answer = Math.pow(first, second);
                    }
                    prevResult = df.format(answer);
                    if(answer%1==0){
                        prevResult= prevResult.substring(0, prevResult.length()-5);
                    }
                    result.setText(prevResult);
                }


            }
        }

    };

    void clear(){
        holder = "00";
        result.setText("0");
        numberField.setText("");
        prevResult = "null";
        prevOperand = "null";

    }

    void setResult(String holder){  //result field only displays 14 numbers in one row, so we will
        if(holder.length()<=14){    //limit entries to 14 numbers or less.
            result.setText(holder);
        }

    }

}
