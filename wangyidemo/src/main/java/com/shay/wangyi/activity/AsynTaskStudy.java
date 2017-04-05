package com.shay.wangyi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shay.wangyi.R;

public class AsynTaskStudy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task_study);
        new MyAsynTask(this).execute();
    }
    
    //使用静态内部类

    /**
     * AsyncTask与Handler异步机制对比

     前面文章也分析过Handler了，这里也分析了AsyncTask，现在把他们两拽一起来比较比较。具体如下：

     AsyncTask是对Handler与Thread的封装。

     AsyncTask在代码上比Handler要轻量级别，但实际上比Handler更耗资源，因为AsyncTask底层是一个线程池，
     而Handler仅仅就是发送了一个消息队列。但是，如果异步任务的数据特别庞大，AsyncTask线程池比Handler节省开销，因为Handler需要不停的new Thread执行。

     AsyncTask的实例化只能在主线程，Handler可以随意，只和Looper有关系。


     接触Android比较久的可能都知道，在Android 3.0之前是并没有SerialExecutor这个类的（上面有分析）。
     那些版本的代码是直接创建了指定大小的线程池常量来执行task的。其中MAXIMUM_POOL_SIZE = 128;，
     所以那时候如果我们应用中一个界面需要同时创建的AsyncTask线程大于128（批量获取数据，譬如照片浏览瀑布流一次加载）程序直接就挂了。
     所以当时的AsyncTask因为这个原因臭名昭著。

     回过头来看看现在高版本的AsyncTask，是不是没有这个问题了吧？因为现在是顺序执行的。
     而且更劲爆的是现在的AsyncTask还直接提供了客户化实现Executor接口功能，使用如下方法执行AsyncTask即可使用自定义Executor
     */
    static final class MyAsynTask extends AsyncTask<Void,Integer,Boolean>
    {
        //如上三个泛型参数从左到右含义依次为：
        //1. 在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。
        //2. 后台任务执行时，如果需要在界面上显示当前的进度，则使用这个。
        //3. 当任务执行完毕后，如果需要对结果进行返回，则使用这个。
        private Context context;
        private ProgressDialog progressDialog;
        private int count;
     //最好使用弱引用，先不说了。
        public MyAsynTask(Context context) 
        {
            this.context = context;
        }
        //在后台任务开始执行之间调用，用于进行一些界面上的初始化操作
        @Override
        protected void onPreExecute() 
        {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }
        //这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务
        @Override
        protected Boolean doInBackground(Void... params)
        {
            while (count<100)
            {
                publishProgress(count);
                count += 10;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
            return true;
        }
        //当在后台任务中调用了publishProgress(Progress...)方法后，这个方法就很快会被调用
        @Override
        protected void onProgressUpdate(Integer... values) 
        {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
        //当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用
        @Override
        protected void onPostExecute(Boolean aBoolean) 
        {
            super.onPostExecute(aBoolean);
            if (aBoolean &&progressDialog!=null &&progressDialog.isShowing())
                progressDialog.dismiss();
        }
     //取消异步线程
        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

       
        
    }
}
