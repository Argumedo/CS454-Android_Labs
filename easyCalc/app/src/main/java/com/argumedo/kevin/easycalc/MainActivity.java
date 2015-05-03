package com.argumedo.kevin.easycalc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends ActionBarActivity {
    private Button zero,one,two,three,four,five,six,seven,eight,nine;
    private Button divide,multiply,subtract,add,dot,equals,delete, neg;
    private TextView display;

    boolean pow = false;
    String powNum = "";
    double result = 0;
    String operation = "";
    String num = "";
    boolean isNeg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        operation = intent.getStringExtra("operation");
        result = intent.getDoubleExtra("result", 0);
        num = intent.getStringExtra("num");
        isNeg = intent.getBooleanExtra("isNeg", false);
        pow = intent.getBooleanExtra("pow", pow);
        powNum = intent.getStringExtra("powNum");

        if(num == null)
        {
            num = "";
        }
        if(powNum == null)
        {
            powNum = "";
        }
        if(operation == null)
        {
            operation = "";
        }

        if(pow)
        {
            displayPow();
        }
        else if(num.length() > 0)
        {
            displayNum();
        }
        else
        {
            displayResult();
        }

        zero = (Button) findViewById(R.id.zero);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(0);
            }
        });

        one = (Button) findViewById(R.id.one);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(1);
            }
        });

        two = (Button) findViewById(R.id.two);

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(2);
            }
        });

        three = (Button) findViewById(R.id.three);

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(3);
            }
        });

        four = (Button) findViewById(R.id.four);

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(4);
            }
        });

        five = (Button) findViewById(R.id.five);

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(5);
            }
        });

        six = (Button) findViewById(R.id.six);

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(6);
            }
        });

        seven = (Button) findViewById(R.id.seven);

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(7);
            }
        });

        eight = (Button) findViewById(R.id.eight);

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(8);
            }
        });

        nine = (Button) findViewById(R.id.nine);

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClick(9);
            }
        });

        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num.length() > 0)
                {
                    if(operation.length() > 0)
                    {
                        calc(operation);
                    }
                    else
                    {
                        result = Double.parseDouble(num);
                    }
                }

                operation = "+";
                num = "";
                displayResult();
            }
        });

        divide = (Button) findViewById(R.id.divide);

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num.length() > 0)
                {
                    if(operation.length() > 0)
                    {
                        calc(operation);
                    }
                    else
                    {
                        result = Double.parseDouble(num);
                    }
                }


                operation = "÷";

                num = "";
                displayResult();
            }
        });

        multiply = (Button) findViewById(R.id.multiply);

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num.length() > 0)
                {
                    if(operation.length() > 0)
                    {
                        calc(operation);
                    }
                    else
                    {
                        result = Double.parseDouble(num);
                    }
                }
                operation = "x";
                num = "";
                displayResult();
            }
        });

        subtract = (Button) findViewById(R.id.subtract);

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num.length() > 0)
                {
                    if(operation.length() > 0)
                    {
                        calc(operation);
                    }
                    else
                    {
                        result = Double.parseDouble(num);
                    }
                }
                operation = "-";
                num = "";
                displayResult();
            }
        });

        dot = (Button) findViewById(R.id.dot);

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!num.contains("."))
                {
                    if(num.length() == 0)
                    {
                        num += "0.";
                    }
                    else
                    {
                        num += ".";
                    }
                }

                displayNum();
            }
        });

        equals = (Button) findViewById(R.id.equals);

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pow)
                {
                    if(num.length() > 0)
                    {
                        String tNum = num;
                        num = "";
                        num += Math.pow(Double.parseDouble(tNum), Double.parseDouble(powNum));
                    }
                    else
                    {
                        result = Math.pow(result, Double.parseDouble(powNum));
                    }
                    pow = false;
                    powNum ="";
                }

                if(operation.length() > 0)
                {
                    calc(operation);
                }
                else
                {
                    if(result == 0 && num.length()>0 && !isNeg)
                    {
                        result = Double.parseDouble(num);
                    }
                    else if(result == 0 && num.length()>1)
                    {
                        result = Double.parseDouble(num);
                    }

                    num = "";
                }

                displayResult();
            }
        });

        delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                displayResult();
            }
        });

        neg = (Button) findViewById(R.id.neg);

        neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isNeg)
                {
                    if(num.length() == 0 &&  result == 0)
                    {
                        num += "-";
                    }
                    else if(num.length() > 0)
                    {
                        num = "-" + num;
                        displayNum();
                    }
                    else
                    {
                        result = -1 * result;
                        displayResult();
                    }
                    isNeg = true;
                }
                else
                {
                    if(num.length() == 0 && result == 0)
                    {
                        num += "-";
                        result = -1*result;
                        displayResult();
                    }
                    else if(num.length() > 0)
                    {
                        num = num.substring(1, num.length());
                        displayNum();
                    }
                    else
                    {
                        result = -1*result;
                        displayResult();
                    }
                    isNeg = false;
                }

            }
        });
    }

    public void displayNum()
    {
        display = (TextView) findViewById(R.id.display);
        display.setText(num);
    }
    public void displayResult()
    {
        String t = "" + result;
        display = (TextView) findViewById(R.id.display);
        display.setText(t);
    }
    public void displayPow()
    {
        display = (TextView) findViewById(R.id.display);
        display.setText(powNum);
    }
    public void delete()
    {
        result = 0;
        operation = "";
        num = "";
        isNeg = false;
        pow = false;
        powNum = "";
    }

    public void numClick(int t)
    {
        if(pow)
        {
            powNum += t;
            displayPow();
        }
        else
        {
            num += t;
            displayNum();
        }
    }


    //calculates the number if possible
    public void calc(String op)
    {
        int l = 0;
        try
        {
            if(pow)
            {
                if(num.length() > 0)
                {
                    String tNum = num;
                    num = "";
                    num += Math.pow(Double.parseDouble(tNum), Double.parseDouble(powNum));
                }
                else
                {
                    result = Math.pow(result, Double.parseDouble(powNum));
                }
                pow = false;
                powNum = "";
            }

            if(operation.equals("x"))
            {
                result *= Double.parseDouble(num);
            }
            else if(operation.equals("÷"))
            {
                result /= Double.parseDouble(num);
            }
            else if(operation.equals("+")) {
                result += Double.parseDouble(num);
            }
            else if(operation.equals("-")) {
                result -= Double.parseDouble(num);
            }
            num = "";
            operation = "";
            result = round(result, 5);
            displayResult();
        }
        catch(NumberFormatException E)
        {
            num = "Error";
            displayNum();
            operation = "";
        }

    }

    //rounds to 5 decimal places
    public double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void openAdvanced()
    {

        Intent intent = new Intent(MainActivity.this,Advanced.class);

        intent.putExtra("operation", operation);
        intent.putExtra("result", result);
        intent.putExtra("num", num);
        intent.putExtra("isNeg", isNeg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.advanced:
                openAdvanced();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
