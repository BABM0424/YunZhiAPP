package com.fengjinliu.myapplication777.Activity.View.Mine;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengjinliu.myapplication777.R;
import com.fengjinliu.myapplication777.entity.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fengjinliu.myapplication777.vo.*;//使用CourseAndTeacherVo来显示更多数据

public class MyAbility extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义的两个textview来查看是否成功传入数据

    static BigInteger user_id= new BigInteger("3");

    /*
    //这里是你们用的Message的变量
    List<Ability_file> abilityList=new ArrayList<Ability_file>();
    //捕获course..vo Message的handler。
    Handler abilityHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    abilityList=objectMapper.readValue(a,new TypeReference<List<Ability_file>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //好了现在获取到了courselist了。你们下面就可以给你们的空间填充你们想要的数据了
                //textview12.setText(messageList.toString());
            }
            else if(msg.what==2)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(MyAbility.this);
                builder.setMessage("暂时没有完成相关能力评价。");
                builder.show();
            }
        }
    };

    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getMyOwnabilityurl="ablility/";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_ability);

        //调用参数。通过handler将参数获取后赋值给两个list
        getMyOwnAbility(originurl+getMyOwnabilityurl+user_id);

    }

    //获取所有课程的URL实现子线程。用courseHandler获取数据
    public void getMyOwnAbility(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(s);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(4000);
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readline = "";
                        while ((readline = bufferedReader.readLine()) != null) {
                            stringBuffer.append(readline);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        connection.disconnect();
                        //记录日志文件。在logcat控制台查看。
                        Log.d("TAG", stringBuffer.toString());
                        if(stringBuffer.toString().equals("[]")){
                            Message msg=Message.obtain();
                            msg.what=2;
                            abilityHandler.sendMessage(msg);
                        }
                        else{// Thread.sleep(10000);
                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = stringBuffer;
                            abilityHandler.sendMessage(msg);
                        }
                    } else {
                        Log.d("TAG", "URLconnection error");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }
*/
}
