package com.fengjinliu.myapplication777.Activity.View;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义的两个textview来查看是否成功传入数据
    private TextView textview;
    private TextView textview2;
    private Button button;
    //这里是你们用的school和course的变量
    List<School> schoollist=new ArrayList<School>();
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
                //好了现在获取到了courselist了。你们下面就可以给你们的空间填充你们想要的数据了
                textview.setText(courseList.get(1).getTeaching_progress());

            }
        }
    };
    //捕获school的handler。
    Handler schoolHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what==1){
                String temp=new String (msg.obj.toString());
                try{
                    schoollist=objectMapper.readValue(temp,new TypeReference<List<School>>(){});
                }catch(IOException e){
                    e.printStackTrace();
                }
                //下一行同理
                textview2.setText(schoollist.get(1).getName());
            }
        }
    };
    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getschoolurl="login/school";
    private String getcourseurl="login/course";
    @Override
    /*
    OnClick在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById((R.id.textView));
        textview2=(TextView)findViewById((R.id.textView2));
        button=(Button)findViewById(R.id.button);

        myListener ml=new myListener();
        button.setOnClickListener(ml);
        //调用参数。通过handler将参数获取后赋值给两个list
        GetAllCourse(originurl+getcourseurl);
        GetAllSchool(originurl+getschoolurl);

        //textview2.setText("111111111");
    }

//获取所有课程的URL实现子线程。用courseHandler获取数据
    public void GetAllCourse(final String s){
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
                       // Thread.sleep(10000);
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
    public void GetAllSchool(final String s){
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
                        schoolHandler.sendMessage(msg);
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

    class myListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            textview2.setText(courseList.get(0).getTeaching_progress());
        }
    }

}
