package com.fengjinliu.myapplication777.Activity.View.School;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengjinliu.myapplication777.R;
import com.fengjinliu.myapplication777.entity.*;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SchoolComment extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义textview来查看是否成功传入数据
    private TextView textview6;

    static int topic_id =1;
    //你们传来的topic_id

    //这里是你们用的topic的变量
    List<Comment> commentlist=new ArrayList<Comment>();
    //捕获course Message的handler。
    Handler commentHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    commentlist=objectMapper.readValue(a,new TypeReference<List<Comment>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textview6.setText(commentlist.get(0).getContent());
            }
        }
    };
    //发帖post请求。type此处为在校友圈发帖。type恒定为0

    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getCommentByTopicIdurl="comment/"+topic_id;

    @Override
    /*
    OnCreate在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_comment);
        textview6=(TextView)findViewById((R.id.textView6));
        getCommentByTopicId(originurl+getCommentByTopicIdurl);
    }

    //获取所有课程的URL实现子线程。用courseHandler获取数据
    public void getCommentByTopicId(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==202){
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
                        commentHandler.sendMessage(msg);
                    }
                    else{
                        Log.d("TAG","url request error");
                        Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj="request error";
                        commentHandler.sendMessage(msg);
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
