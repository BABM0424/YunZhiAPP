package com.fengjinliu.myapplication777.Activity.View.School;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengjinliu.myapplication777.R;
import com.fengjinliu.myapplication777.entity.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SchoolHomepage extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义textview来查看是否成功传入数据
    private TextView textview3;
    private TextView textview4;

    static int school_id =1;
    //这里是你们用的teacher和course的变量
    List<User> teacherlist=new ArrayList<User>();
    List<Course> courseList=new ArrayList<Course>();
    //捕获course Message的handler。
    Handler courseHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    courseList=objectMapper.readValue(a,new TypeReference<List<Course>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textview3.setText(courseList.get(1).getTeaching_progress());
            }
        }
    };
    //捕获school的handler。
    Handler teacherHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what==1){
                String temp=new String (msg.obj.toString());
                try{
                    teacherlist=objectMapper.readValue(temp,new TypeReference<List<User>>(){});
                }catch(IOException e){
                    e.printStackTrace();
                }
                //下一行同理
                textview4.setText(teacherlist.get(1).getName());
            }
        }
    };
    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getcoursebyschool="school/"+school_id+"/course";
    private String getteacherbyschool="school/"+school_id+"/teacher";
    @Override
    /*
    OnClick在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_homepage);
        textview3=(TextView)findViewById((R.id.textView3));
        textview4=(TextView)findViewById((R.id.textView4));
        getcoursebyschoolId(originurl+getcoursebyschool);
        getteacherbyschoolId(originurl+getteacherbyschool);
    }

    //获取所有课程的URL实现子线程。用courseHandler获取数据
    public void getcoursebyschoolId(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==200){
                        InputStream inputStream=connection.getInputStream();
                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer=new StringBuffer();
                        String readline="";
                        while((readline=bufferedReader.readLine())!=null){
                            stringBuffer.append(readline);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        connection.disconnect();
                        //记录日志文件。在logcat控制台查看。
                        Log.d("TAG",stringBuffer.toString());
                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj=stringBuffer;
                        courseHandler.sendMessage(msg);
                    }
                    else{
                        Log.d("TAG","error");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }
    //获取所有学校的URL实现子线程，用schoolHandler捕获数据
    public void getteacherbyschoolId(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==200){
                        InputStream inputStream=connection.getInputStream();
                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer=new StringBuffer();
                        String readline="";
                        while((readline=bufferedReader.readLine())!=null){
                            stringBuffer.append(readline);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        connection.disconnect();
                        Log.d("TAG",stringBuffer.toString());
                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj=stringBuffer;
                        teacherHandler.sendMessage(msg);
                    }
                    else{
                        Log.d("TAG","error");
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
