package com.suse.test.calculator.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button
            button0,//数字0
            button1,//数字1
            button2,//数字2
            button3,//数字3
            button4,//数字4
            button5,//数字5
            button6,//数字6
            button7,//数字7
            button8,//数字8
            button9,//数字9
            buttonPoint,//小数点
            buttonadd,//加
            buttonminus,//减
            buttonmultiply,//乘
            buttondivide,//除
            buttondelete,//单个删除
            buttonclear,//清空
            buttonequal;////等号

    private EditText et_t, et_b;
    private String result = "";
    private boolean flag = false;
    private int mark = 0;
    private String str, num1 = "", num2 = "", sign = "";
    private double num3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inibtiew();
        bindListener();
    }

    private void bindListener() {
        et_t.setOnClickListener(this);
        et_b.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonminus.setOnClickListener(this);
        buttonmultiply.setOnClickListener(this);
        buttondivide.setOnClickListener(this);
        buttondelete.setOnClickListener(this);
        buttonclear.setOnClickListener(this);
        buttonequal.setOnClickListener(this);
    }

    private void inibtiew() {
        et_t = (EditText) findViewById(R.id.et_t);
        et_b = (EditText) findViewById(R.id.et_b);
        button0 = (Button) findViewById(R.id.bt_zero);
        button1 = (Button) findViewById(R.id.bt_1);
        button2 = (Button) findViewById(R.id.bt_2);
        button3 = (Button) findViewById(R.id.bt_3);
        button4 = (Button) findViewById(R.id.bt_4);
        button5 = (Button) findViewById(R.id.bt_5);
        button6 = (Button) findViewById(R.id.bt_6);
        button7 = (Button) findViewById(R.id.bt_7);
        button8 = (Button) findViewById(R.id.bt_8);
        button9 = (Button) findViewById(R.id.bt_9);
        buttonPoint = (Button) findViewById(R.id.bt_point);
        buttonadd = (Button) findViewById(R.id.bt_add);
        buttonminus = (Button) findViewById(R.id.bt_minus);
        buttonmultiply = (Button) findViewById(R.id.bt_multiply);
        buttondivide = (Button) findViewById(R.id.bt_divide);
        buttondelete = (Button) findViewById(R.id.bt_delete);
        buttonclear = (Button) findViewById(R.id.bt_clear);
        buttonequal = (Button) findViewById(R.id.bt_equal);
    }

    @Override
    public void onClick(View view) {
        str = et_t.getText().toString();
        switch (view.getId()) {
            case R.id.bt_zero:
                if (!num1.substring(0).equals("0")) {
                    num1 = num1 + ((Button) view).getText();
                    et_t.setText(num1);
                    flag = true;
                }
                if (!sign.equals("") && !num2.substring(0).equals("0")) {
                    num2 = num2 + ((Button) view).getText();
                    et_t.setText(num1 + sign + num2);
                    flag = true;
                }
                break;
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
                if (mark == 0) {
                    num1 = num1 + ((Button) view).getText();
                    et_t.setText(num1);
                    flag = true;
                } else if (mark == 1) {
                    num2 = num2 + ((Button) view).getText();
                    et_t.setText(num1 + sign + num2);
                    flag = true;
                } else {
                    num2 = num2 + ((Button) view).getText();
                    et_t.setText(num1 + sign + num2);
                    flag = true;
                }
                break;
            case R.id.bt_point:
                if (mark == 0) {
                    if (num1.length() >= 1 && !num1.contains(".")) {
                        num1 = num1 + ((Button) view).getText();
                        et_t.setText(num1);
                    }
                } else if (mark == 1) {
                    if (num2.length() >= 1 && !num2.contains(".")) {
                        num2 = num2 + ((Button) view).getText();
                        et_t.setText(num1 + sign + num2);
                    }
                } else {
                    num1 = String.valueOf(num3);
                    if (!num2.equals("") && !num2.contains(".")) {
                        num2 = num2 + ((Button) view).getText();
                        et_t.setText(num1 + sign + num2);
                    }
                }
                break;
            case R.id.bt_add:
            case R.id.bt_minus:
            case R.id.bt_multiply:
            case R.id.bt_divide:
                if (flag) {
                    mark++;
                    if (mark == 1) {
                        sign = ((Button) view).getText().toString();
                        flag = false;
                        et_t.setText(num1 + sign);
                    }
                    if (mark >= 2) {
                        getResult();
                        num1 = String.valueOf(num3);
                        sign = ((Button) view).getText().toString();
                        et_t.setText(num1 + sign);
                        flag = false;
                    }
                }
                break;
            case R.id.bt_delete:
                if (!result.equals("")) {
                    clear();
                } else if (str.length() >= 1) {
                    if (!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/")) {
                        if (str.length() == 1) {
                            num1 = "";
                            et_t.setText("");
                        } else {
                            num1 = str.substring(0, str.length() - 1);
                            et_t.setText(str.substring(0, str.length() - 1));
                        }
                        flag = false;
                    } else if ((str.substring(str.length() - 1).equals("+"))) {
                        sign = "";
                        flag = true;
                        et_t.setText(str.substring(0, str.length() - 1));
                    } else if ((str.substring(str.length() - 1).equals("-"))) {
                        sign = "";
                        flag = true;
                        et_t.setText(str.substring(0, str.length() - 1));
                    } else if ((str.substring(str.length() - 1).equals("*"))) {
                        sign = "";
                        flag = true;
                        et_t.setText(str.substring(0, str.length() - 1));
                    } else if ((str.substring(str.length() - 1).equals("/"))) {
                        sign = "";
                        flag = true;
                        et_t.setText(str.substring(0, str.length() - 1));
                    } else {
                        if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/")) {
                            int loc;
                            if (str.contains("+")) {
                                loc = str.indexOf("+");
                            } else if (str.contains("-")) {
                                loc = str.indexOf("-");
                            } else if (str.contains("*")) {
                                loc = str.indexOf("*");
                            } else {
                                loc = str.indexOf("/");
                            }
                            num2 = str.substring(loc + 1, str.length() - 2);
                            et_t.setText(str.substring(loc + 1, str.length() - 2));
                        }
                    }
                }
                break;
            case R.id.bt_clear:
                clear();
                break;
            case R.id.bt_equal:
                getResult();
        }
    }

    public void clear() {
        et_t.setText("");
        et_b.setText("");
        num1 = "";
        num2 = "";
        mark = 0;
        sign = "";
        result = "";
        flag = false;
    }

    public void getResult() {
        flag = true;
        if (mark > 0 && num2 != null) {
            if (sign.equals("+")) {
                double x = Double.parseDouble(num1);
                double y = Double.parseDouble(num2);
                num3 = x + y;
                result = String.valueOf(num3);
                et_b.setText("=" + result);
            } else if (sign.equals("-")) {
                double x = Double.parseDouble(num1);
                double y = Double.parseDouble(num2);
                num3 = x - y;
                result = String.valueOf(num3);
                et_b.setText("=" + result);
            } else if (sign.equals("*")) {
                double x = Double.parseDouble(num1);
                double y = Double.parseDouble(num2);
                num3 = x * y;
                result = String.valueOf(num3);
                et_b.setText("=" + result);
            } else if (sign.equals("/")) {

                double x = Double.parseDouble(num1);
                double y = Double.parseDouble(num2);
                if (y != 0) {
                    num3 = x / y;
                    result = String.valueOf(num3);
                    et_b.setText("=" + result);
                }
            } else {
                result = String.valueOf(num1);
                et_b.setText("=" + result);
            }
            num1 = "";
            num2 = "";
            sign = "";
        }
    }
}

