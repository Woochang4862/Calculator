package com.jeong_woochang.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnc,btng,btnp,btnd,btnx,btnm,btnf,btnb,btnj,btne;
    TextView tv;
    String sik="";
    String id="";
    String tmp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btn0=(Button)findViewById(R.id.btn0);
        btnc=(Button)findViewById(R.id.btnc);
        btng=(Button)findViewById(R.id.btng);
        btnp=(Button)findViewById(R.id.btnp);
        btnd=(Button)findViewById(R.id.btnd);
        btnx=(Button)findViewById(R.id.btnx);
        btnm=(Button)findViewById(R.id.btnm);
        btnf=(Button)findViewById(R.id.btnf);
        btnb=(Button)findViewById(R.id.btnb);
        btnj=(Button)findViewById(R.id.btnj);
        btne=(Button)findViewById(R.id.btne);
        tv=(TextView)findViewById(R.id.tv);

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("0");
                sik="";
            }
        });

        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sik = sik.substring(0, sik.length() - 1);
                    tv.setText(sik);
                }catch(Exception e) {
                    Log.i("","");
                }
            }
        });

    }

    public void onclick(View view) {
        if(tv.getText().toString().equalsIgnoreCase("0"))
            tv.setText("");
        switch (view.getId()){
            case R.id.btn1:
                id="1";
                break;
            case R.id.btn2:
                id="2";
                break;
            case R.id.btn3:
                id="3";
                break;
            case R.id.btn4:
                id="4";
                break;
            case R.id.btn5:
                id="5";
                break;
            case R.id.btn6:
                id="6";
                break;
            case R.id.btn7:
                id="7";
                break;
            case R.id.btn8:
                id="8";
                break;
            case R.id.btn9:
                id="9";
                break;
            case R.id.btn0:
                id="0";
                break;
            case R.id.btng:
                if(sik.indexOf('(')==-1)
                    id="(";
                else
                    id=")";
                break;
            case R.id.btnp:
                id="%";
                break;
            case R.id.btnd:
                id="/";
                break;
            case R.id.btnx:
                id="x";
                break;
            case R.id.btnm:
                id="-";
                break;
            case R.id.btnf:
                id="+";
                break;
            case R.id.btnb:
                id="<-";

                break;
            case R.id.btnj:
                id=".";
                break;
            case R.id.btne:
                try {
                    String result = calculate(sik);
                    sik = "";
                    id = "";
                    tv.setText(result);
                    return;
                }catch (Exception e) {
                    Log.i("", "");
                }
        }
        if(tmp!=null&&(id.indexOf('+')!=-1||id.indexOf('-')!=-1||id.indexOf('x')!=-1||id.indexOf('/')!=-1||id.indexOf('%')!=-1))
            sik=tmp+id;
        else{
            sik+=id;
            tmp=null;
        }
        tv.setText(sik);
    }

    private String calculate(String str) {
        if(str.indexOf('(') != -1){
            int fs = str.indexOf('(');
            int ls = str.lastIndexOf(')');
            String s = calculate(str.substring(fs + 1, ls));
            str = str.substring(0, fs) + s + str.substring(ls+1, str.length());
        }

        double cnt = 0;
        Stack<Double> Stk_Num = new Stack <Double>();
        StringTokenizer ST_Num = new StringTokenizer(str,"+-/x% ");
        StringTokenizer ST_Oper = new StringTokenizer(str,"1234567890. ");

        Stk_Num.push(Double.parseDouble(ST_Num.nextToken()));
        while(ST_Num.hasMoreTokens()){
            char oper = ST_Oper.nextToken().charAt(0);
            String num = ST_Num.nextToken();
            Double a;

            if(oper == 'x'){
                a = Stk_Num.pop();
                a *= Double.parseDouble(num);
                Stk_Num.push(a);
            }
            else if(oper == '/'){
                a = Stk_Num.pop();
                a /= Double.parseDouble(num);
                Stk_Num.push(a);
            }
            else if(oper == '+'){
                System.out.println(Double.parseDouble(num));
                Stk_Num.push(Double.parseDouble(num));
            }
            else if(oper == '-'){
                Stk_Num.push(-1 * (Double.parseDouble(num)));
            }
            else if(oper == '%'){
                a=Stk_Num.pop();
                a=a*Double.parseDouble(num)/100;
                Stk_Num.push(a);
            }
        }

        while(!Stk_Num.isEmpty()){
            cnt += Stk_Num.pop();
        }

        tmp=Double.toString(cnt);
        return Double.toString(cnt);
    }
}
