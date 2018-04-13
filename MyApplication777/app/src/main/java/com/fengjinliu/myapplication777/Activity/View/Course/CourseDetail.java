package com.fengjinliu.myapplication777.Activity.View.Course;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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

public class CourseDetail extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义的两个textview来查看是否成功传入数据

    private TextView textview12;

    static BigInteger user_id= new BigInteger("1");
    static BigInteger class_id=new BigInteger("1");
    //这里是你们用的course..vo的变量
    List<PeriodInfoVo> periodvoList=new ArrayList<PeriodInfoVo>();
    //捕获course..vo Message的handler。
    Handler periodvoHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    periodvoList=objectMapper.readValue(a,new TypeReference<List<PeriodInfoVo>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //好了现在获取到了courselist了。你们下面就可以给你们的空间填充你们想要的数据了
                textview12.setText(periodvoList.toString());

            }
        }
    };
    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getCourseDetailurl="course/";
    @Override
    /*
    OnClick在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
        textview12=(TextView)findViewById((R.id.textView12));

        //调用参数。通过handler将参数获取后赋值给两个list
        getCourseDetail(originurl+getCourseDetailurl+class_id);

    }

    //获取所有课程的URL实现子线程。用courseHandler获取数据
    public void getCourseDetail(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(s+"/period/?user_id="+user_id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200) {
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
                        // Thread.sleep(10000);
                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = stringBuffer;
                        periodvoHandler.sendMessage(msg);
                    } else {
                        Log.d("TAG", "error");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }

}
