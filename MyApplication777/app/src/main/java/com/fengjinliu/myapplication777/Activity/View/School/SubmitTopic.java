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


public class SubmitTopic extends AppCompatActivity {

    ObjectMapper objectMapper=new ObjectMapper();
    //定义textview来查看是否成功传入数据
    private TextView textview7;
    private Button button3;
    //url要用的belonging_id和type
    static int school_id =1;//你们传来的school_id
    static int type=1;//学校是1吧？？

    public Topic top=new Topic();

    //发帖post请求。type此处为在校友圈发帖。type恒定为0
    Handler postHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //what=1时post 成功，what=2时表示post失败
            if(msg.what==1){
                AlertDialog.Builder adb=new AlertDialog.Builder(SubmitTopic.this);
                adb.setTitle("SUCCESS");
                adb.setMessage("发表话题成功！");
                adb.show();
            }
            else if(msg.what==2){
                String a=new String(msg.obj.toString());
                AlertDialog.Builder adb=new AlertDialog.Builder(SubmitTopic.this);
                adb.setTitle("Failed");
                adb.setMessage("something is dismissed!");
                adb.show();
            }
        }
    };

    //定义所需url
    private String originurl="http://123.207.117.220:8080/";
    private String postTopicAtSchoolurl="topic/"+type+"/type/"+school_id;

    @Override
    /*
    OnCreate在这里！！
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_topic);
        textview7=(TextView)findViewById((R.id.textView7));
        button3=(Button)findViewById(R.id.button3);
        MYButtonlistenr myButtonlistenr=new MYButtonlistenr();
        button3.setOnClickListener(myButtonlistenr);
    }

    class MYButtonlistenr implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            top.setContent("这节课对我帮助很大hhh");
            top.setBelonging_id(BigInteger.valueOf(1));
            top.setHeading("给老师点赞123");
            top.setUser_id(BigInteger.valueOf(1));
            top.setId(BigInteger.valueOf(7));
            postTopic(originurl+postTopicAtSchoolurl);
        }
    }

    public void postTopic(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL posturl =new URL(s);
                    HttpURLConnection conn=(HttpURLConnection) posturl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                    conn.connect();
                    DataOutputStream dataOutputStream=new DataOutputStream(conn.getOutputStream());
                    String content=objectMapper.writeValueAsString(top);
                    Log.d("TAG",content);
                    dataOutputStream.write(content.getBytes("UTF-8"));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    if(conn.getResponseCode()==202){
                        InputStream inputStream=conn.getInputStream();
                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer=new StringBuffer();
                        String readline="";
                        while((readline=bufferedReader.readLine())!=null){
                            stringBuffer.append(readline);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        conn.disconnect();
                        //记录日志文件。在logcat控制台查看。
                        Log.d("TAG",stringBuffer.toString());
                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj=stringBuffer;
                        postHandler.sendMessage(msg);
                    }
                    else if(conn.getResponseCode()==400){
                        Log.d("TAG","传送JSON格式错误。请确保UTF-8");
                    }
                    else {
                        Log.d("TAG","error");
                        Message msg=Message.obtain();
                        msg.what=2;
                        msg.obj="post error";
                        postHandler.sendMessage(msg);
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
