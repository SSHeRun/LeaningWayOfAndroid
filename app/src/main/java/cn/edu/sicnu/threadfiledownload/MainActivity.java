package cn.edu.sicnu.threadfiledownload;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText_url;
    ListView listView;  //heap堆，可以被整个对象所有方法访问
    TextView textView;
    ProgressBar progressBar;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> localImages = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    MyAsyncTask asyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add("http://img2.imgtn.bdimg.com/it/u=500808421,1575925585&fm=200&gp=0.jpg");
        arrayList.add("http://img1.imgtn.bdimg.com/it/u=2883138594,3332343437&fm=27&gp=0.jpg");
        arrayList.add("http://img0.imgtn.bdimg.com/it/u=2779717376,1807907918&fm=200&gp=0.jpg");
        arrayList.add("http://img0.imgtn.bdimg.com/it/u=416447706,3692155938&fm=27&gp=0.jpg");
        arrayList.add("http://img3.imgtn.bdimg.com/it/u=2058241956,1502683125&fm=27&gp=0.jpg");
        arrayList.add("http://img0.imgtn.bdimg.com/it/u=2779717376,1807907918&fm=200&gp=0.jpg");
        arrayList.add("http://img0.imgtn.bdimg.com/it/u=2779717376,1807907918&fm=200&gp=0.jpg");


        editText_url = findViewById(R.id.editText_url);

        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        asyncTask = new MyAsyncTask();
    }

    public void add(View view){
        arrayList.add(editText_url.getText().toString());
        arrayAdapter.notifyDataSetChanged();
    }

    public void download(View view){

        switch (asyncTask.getStatus()){
            case PENDING:
                asyncTask.execute(0);
                break;
            case RUNNING:
                break;
            case FINISHED:
                asyncTask = new MyAsyncTask();
                asyncTask.execute(0);
                break;
        }

    }

    class MyAsyncTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            localImages.clear();
        }


        @Override
        protected Integer doInBackground(Integer... integers) {
            //在另外一个线程里面！！！
            for(int i=0;i<arrayList.size();i++){
                String s = arrayList.get(i);
                try {
                    URL url = new URL(s);  //将其转变为URL类对象
                    URLConnection connection = url.openConnection();  //打开连接

                    int size = connection.getContentLength();  //图片大小

                    publishProgress(0,i+1,size);  //实时更新显示进度  第二个参数：第几个文件   第三个参数：文件大小

                    InputStream inputStream = connection.getInputStream();  //流   读取网页内容
                    byte[] bytes = new byte[100];  //字节数组
                    int len = -1;

                    //准备好外部存储文件
                    File file = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                    String filename = file.getAbsolutePath()+"/"+i+".jpg";  //第i张图片
                    FileOutputStream fileOutputStream = new FileOutputStream(filename);

                    while ((len=inputStream.read(bytes))!=-1){   //read按字节读取
                        fileOutputStream.write(bytes,0,len);
                        Thread.sleep(1);
                        publishProgress(1,len);
                    }

                    fileOutputStream.close();
                    inputStream.close();

                    localImages.add(filename); //动态数组加图片文件

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } //for

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            switch (values[0]){
                case 0:  //准备下载第i+1个文件，刚进入
                    textView.setText("正在下载第"+values[1]+"个文件！！！");
                    progressBar.setMax(values[2]);   //size
                    progressBar.setProgress(0);
                    break;
                case 1:  //正在下载第i+1个文件，进度条增加
                    progressBar.incrementProgressBy(values[1]);  //len
                    break;
            }



        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textView.setText("下载完成！！！");
            progressBar.setProgress(100);
        }
    }

    public void showImages(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("images",localImages);
        startActivity(intent);
    }



}
