package com.fengjinliu.myapplication777.Activity.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengjinliu.myapplication777.Activity.View.Fragment.HomepageFragment;
import com.fengjinliu.myapplication777.Activity.View.Fragment.MineFragment;
import com.fengjinliu.myapplication777.Activity.View.Fragment.MyStudyFragment;
import com.fengjinliu.myapplication777.Activity.View.Fragment.SchoolHomepageFragment;
import com.fengjinliu.myapplication777.Activity.View.School.SchoolHomepage;
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
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private
    ObjectMapper objectMapper=new ObjectMapper();
    private HomepageFragment homepageFragment;
    private SchoolHomepageFragment schoolHomepageFragment;
    private MyStudyFragment myStudyFragment;
    private MineFragment mineFragment;
    private HashMap<String,Object> main_homepage_data;
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    FragmentManager fragmentManager = getFragmentManager();

    private TextView course_introduction1,course_introduction2,course_introduction3 ;
    private ImageButton course_img1,course_img2,course_img3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //选择到“主页”
                   showmainhomepage();
                    return true;
                case R.id.navigation_SchoolMain:
                    //选择到“院校主页”
                    showschoolhomepage();
                    return true;
                case R.id.navigation_MyStudy:
                    //选择到“我的学习”
                    showMyStudy();
                    return true;
                case R.id.navigation_Mine:
                    //选择到“我”
                    showMine();

                    return true;

            }
            return false;
        }
    };
    //这里是你们用的school和course的变量
    List<School> schoollist=new ArrayList<School>();
    List<Course> courseList=new ArrayList<Course>();
    //捕获course Message的handler。

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
        //textview=(TextView)findViewById((R.id.textView));
        //textview2=(TextView)findViewById((R.id.textView2));
       // button=(Button)findViewById(R.id.button);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        //调用参数。通过handler将参数获取后赋值给两个list
        GetAllCourse(originurl+getcourseurl);
        GetAllSchool(originurl+getschoolurl);



        init();
        //textview2.setText("111111111");
    }

    private void init(){

        homepageFragment = new HomepageFragment();
        schoolHomepageFragment = new SchoolHomepageFragment();
        myStudyFragment = new MyStudyFragment();
        mineFragment = new MineFragment();



        fragmentTransaction.add(R.id.main_fragment,homepageFragment)
                .add(R.id.main_fragment,schoolHomepageFragment)
                .add(R.id.main_fragment,myStudyFragment)
                .add(R.id.main_fragment,mineFragment);
        fragmentTransaction.hide(homepageFragment).hide(schoolHomepageFragment).hide(myStudyFragment).hide(mineFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentTransaction.show(homepageFragment);

    }


    private void showmainhomepage(){

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(myStudyFragment).hide(schoolHomepageFragment).hide(mineFragment);
        fragmentTransaction.show(homepageFragment);
        fragmentTransaction.commit();
        get_main_homepage_course();
    }
    private void showschoolhomepage(){
        get_school_homepage_course();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(myStudyFragment).hide(homepageFragment).hide(mineFragment);
        fragmentTransaction.show(schoolHomepageFragment);
        fragmentTransaction.commit();

    }
    private void showMyStudy(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(schoolHomepageFragment).hide(homepageFragment).hide(mineFragment);
        fragmentTransaction.show(myStudyFragment);
        fragmentTransaction.commit();

    }
    private void showMine(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(myStudyFragment).hide(homepageFragment).hide(schoolHomepageFragment);
        fragmentTransaction.show(mineFragment);
        fragmentTransaction.commit();

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
                courseList.get(1).getTeaching_progress();
                course_img1 = (ImageButton)findViewById(R.id.course_image1);
                course_img2 = (ImageButton)findViewById(R.id.course_image2);
                course_img3 = (ImageButton)findViewById(R.id.course_image3);
                course_introduction1 = (TextView)findViewById(R.id.course_text1);
                course_introduction2  = (TextView)findViewById(R.id.course_text2);
                course_introduction3 = (TextView)findViewById(R.id.course_text3);
                if(courseList.isEmpty()==true) {
                    course_introduction1.setText("java从入门到入土");
                    course_introduction2.setText("Android从初学到放弃");
                    course_introduction3.setText("看到我头上的佛光了吗");
                }
                else {
                    course_introduction1.setText(courseList.get(0).getIntroduction()+"\n\n"+courseList.get(0).getName());
                    course_introduction2.setText(courseList.get(1).getIntroduction()+"\n\n"+courseList.get(1).getName());
                    course_introduction3.setText(courseList.get(2).getIntroduction()+"\n\n"+courseList.get(2).getName());
                }
                course_img1.setImageResource(R.mipmap.ic_launcher);
                course_img2.setImageResource(R.mipmap.ic_launcher);
                course_img3.setImageResource(R.mipmap.ic_launcher);


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
                schoollist.get(1).getName();

            }
        }
    };

    private  void get_main_homepage_course()
    {
        course_img1 = (ImageButton)findViewById(R.id.course_image1);
        course_img2 = (ImageButton)findViewById(R.id.course_image2);
        course_img3 = (ImageButton)findViewById(R.id.course_image3);
        course_introduction1 = (TextView)findViewById(R.id.course_text1);
        course_introduction2  = (TextView)findViewById(R.id.course_text2);
        course_introduction3 = (TextView)findViewById(R.id.course_text3);
        if(courseList.isEmpty()==true) {
            course_introduction1.setText("java从入门到入土");
            course_introduction2.setText("Android从初学到放弃");
            course_introduction3.setText("看到我头上的佛光了吗");
        }
        else {
            course_introduction1.setText(courseList.get(0).getIntroduction()+"\n\n"+courseList.get(0).getName());
            course_introduction2.setText(courseList.get(1).getIntroduction()+"\n\n"+courseList.get(1).getName());
            course_introduction3.setText(courseList.get(2).getIntroduction()+"\n\n"+courseList.get(2).getName());
        }
        course_img1.setImageResource(R.mipmap.ic_launcher);
        course_img2.setImageResource(R.mipmap.ic_launcher);
        course_img3.setImageResource(R.mipmap.ic_launcher);
    }

    private  void get_school_homepage_course()
    {
        ImageButton school_course_imag1,school_course_imag2,school_course_imag3;
        TextView school_course_introduction1,school_course_introduction2,school_course_introduction3;
        school_course_imag1 = (ImageButton)findViewById(R.id.school_course_image1);
        school_course_imag2 = (ImageButton)findViewById(R.id.school_course_image2);
        school_course_imag3 = (ImageButton)findViewById(R.id.school_course_image3);
        school_course_introduction1 = (TextView)findViewById(R.id.school_course_text1);
        school_course_introduction2  = (TextView)findViewById(R.id.school_course_text2);
        school_course_introduction3 = (TextView)findViewById(R.id.school_course_text3);
        if(courseList.isEmpty()==true) {
            school_course_introduction1.setText("java从入门到入土");
            school_course_introduction2.setText("Android从初学到放弃");
            school_course_introduction3.setText("看到我头上的佛光了吗");
        }
        else {
            school_course_introduction1.setText(courseList.get(0).getIntroduction()+"\n\n"+courseList.get(0).getName());
            school_course_introduction2.setText(courseList.get(1).getIntroduction()+"\n\n"+courseList.get(1).getName());
            school_course_introduction3.setText(courseList.get(2).getIntroduction()+"\n\n"+courseList.get(2).getName());
        }
        school_course_imag1.setImageResource(R.mipmap.ic_launcher);
        school_course_imag2.setImageResource(R.mipmap.ic_launcher);
        school_course_imag3.setImageResource(R.mipmap.ic_launcher);
    }

}
