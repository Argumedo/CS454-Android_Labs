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
import java.math.BigInteger;
import java.math.RoundingMode;

public class Advanced extends ActionBarActivity {
    private Button sin,cos,tan,imaginary,ln,log,pi,e,percent,factorial;
    private Button root, power, openP, closeP,delete;
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
        setContentView(R.layout.advanced);

        Intent intent = getIntent();
        operation = intent.getStringExtra("operation");
        result = intent.getDoubleExtra("result", 0);
        num = intent.getStringExtra("num");
        isNeg = intent.getBooleanExtra("isNeg", false);

        if(num.length() > 0)
        {
            displayNum();
        }
        else
        {
            displayResult();
        }

        sin = (Button) findViewById(R.id.sin);

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length() > 0 )
                {
                    Double t = Math.sin(Double.parseDouble(num));
                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.sin(result),5);
                    }
                }
                num = "";
                displayResult();
            }
        });

        cos = (Button) findViewById(R.id.cos);

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length() > 0)
                {
                    Double t = Math.cos(Double.parseDouble(num));

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.cos(result),5);
                    }
                }
                num = "";
                displayResult();
            }
        });

        tan = (Button) findViewById(R.id.tan);

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length()>0)
                {
                    Double t = Math.tan(Double.parseDouble(num));

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.tan(result),5);
                    }
                }
                num = "";
                displayResult();

            }
        });

        imaginary = (Button) findViewById(R.id.imaginary);

        imaginary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = "Error";
                displayNum();
                num = "";

            }
        });

        ln = (Button) findViewById(R.id.ln);

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length()>0)
                {
                    Double t = Math.log(Double.parseDouble(num));

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.log(result),5);
                    }
                }
                num = "";
                displayResult();

            }
        });

        log = (Button) findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length() >0)
                {
                    Double t = Math.log10(Double.parseDouble(num));

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.log10(result),5);
                    }
                }
                num = "";
                displayResult();

            }
        });

        pi = (Button) findViewById(R.id.pi);

        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.length() == 0)
                {
                    result = round(Math.PI,5);
                }
                else
                {
                    calc(Math.PI);
                }
                displayResult();
            }
        });

        e = (Button) findViewById(R.id.e);

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.length() == 0)
                {
                    result = round(Math.E,5);
                }
                else
                {
                    calc(Math.E);
                }
                displayResult();

            }
        });

        percent = (Button) findViewById(R.id.percent);

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(convert() && num.length() >0)
                {
                    Double t = Double.parseDouble(num)/100;

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result /= 100;
                    }
                }
                num = "";

                displayResult();

            }
        });

        factorial = (Button) findViewById(R.id.factorial);

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(convert() && num.length() >0)
                {
                    double t = 1;
                    for(double i = 1; i <= Double.parseDouble(num); i++)
                    {
                        t *= i;
                    }

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    double t = 1;
                    for(double i = 1; i <= result; i++)
                    {
                        t *= i;
                    }

                    if(operation.length() == 0)
                    {
                        result = t;
                    }
                }
                num = "";

                displayResult();


            }
        });

        root = (Button) findViewById(R.id.root);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convert() && num.length() >0)
                {
                    Double t = Math.sqrt(Double.parseDouble(num));

                    if(operation.length() > 0)
                    {
                        calc(t);
                    }
                    else
                    {
                        result = round(t,5);
                    }
                }
                else
                {
                    if(operation.length() == 0)
                    {
                        result = round(Math.sqrt(result), 5);
                    }
                }
                num = "";

                displayResult();
            }
        });

        power = (Button) findViewById(R.id.power);

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pow = true;
                displayNum();
            }
        });

        openP = (Button) findViewById(R.id.openP);

        openP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        closeP = (Button) findViewById(R.id.closeP);

        closeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void delete()
    {
        result = 0;
        operation = "";
        num = "";
        isNeg = false;
        displayResult();
    }

     public boolean convert()
     {
         try
         {
             Double.parseDouble(num);
             return true;
         }
         catch(NumberFormatException E)
         {
             return false;
         }
     }

    //calculates the number if possible
    public void calc(double t)
    {
        try
        {
            if(operation.equals("x"))
            {
                result *= t;
            }
            else if(operation.equals("÷"))
            {
                result /= t;
            }
            else if(operation.equals("+"))
            {
                result += t;
            }
            else if(operation.equals("-"))
            {
                result -= t;
            }
            else if(operation.equals("pow"))
            {
                result = Math.pow(result, Double.parseDouble(num));
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

    public double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void openNormal()
    {
        Intent intent = new Intent(Advanced.this,MainActivity.class);
        intent.putExtra("operation", operation);
        intent.putExtra("result", result);
        intent.putExtra("num", num);
        intent.putExtra("isNeg", isNeg);
        intent.putExtra("pow", pow);
        intent.putExtra("powNum", powNum);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.normal:
                openNormal();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
