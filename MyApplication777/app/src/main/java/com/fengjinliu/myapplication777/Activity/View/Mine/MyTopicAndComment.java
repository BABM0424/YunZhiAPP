package com.fengjinliu.myapplication777.Activity.View.Mine;

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


public class MyTopicAndComment extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义textview来查看是否成功传入数据
    //url要用的belonging_id和type
    static BigInteger user_id =new BigInteger("1");

    List<Topic> mytopiclist=new ArrayList<Topic>();
    List<Comment> mycommentlist=new ArrayList<Comment>();

    Handler mytopicHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    mytopiclist=objectMapper.readValue(a,new TypeReference<List<Topic>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //textview5.setText(topiclist.toString());
            }
        }
    };

    Handler mycommentHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                String a=new String(msg.obj.toString());
                try {
                    mycommentlist=objectMapper.readValue(a,new TypeReference<List<Comment>>(){});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //textview5.setText(topiclist.toString());
            }
        }
    };

    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String getTopicOwnurl="topic/?user_id="+user_id;
    private String getCommentOwnurl="comment/?user_id="+user_id;
    @Override
    /*
    OnCreate在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_topic_comment);
        Log.d("TAG","missmiss");
        //textview5=(TextView)findViewById((R.id.textView5));
        //button2=(Button)findViewById(R.id.button2);
        getTopicOwn(originurl+getTopicOwnurl);
        getCommentOwn(originurl+getCommentOwnurl);
    }


    //获取所有课程的URL实现子线程。用courseHandler获取数据
    public void getTopicOwn(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_ACCEPTED){
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
                        if(stringBuffer.toString().equals("[]")){
                            Message msg=Message.obtain();
                            msg.what=2;
                            mycommentHandler.sendMessage(msg);
                        }
                        else{// Thread.sleep(10000);
                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = stringBuffer;
                            mycommentHandler.sendMessage(msg);
                        }
                    }
                    else{
                        Log.d("TAG","URL connection error");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

    public void getCommentOwn(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.connect();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_ACCEPTED){
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
                        if(stringBuffer.toString().equals("[]")){
                            Message msg=Message.obtain();
                            msg.what=2;
                            mytopicHandler.sendMessage(msg);
                        }
                        else{// Thread.sleep(10000);
                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = stringBuffer;
                            mytopicHandler.sendMessage(msg);
                        }
                    }
                    else{
                        Log.d("TAG","URL connection error");
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
