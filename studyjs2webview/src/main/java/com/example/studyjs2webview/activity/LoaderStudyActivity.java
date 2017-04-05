package com.example.studyjs2webview.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.studyjs2webview.R;

public class LoaderStudyActivity extends AppCompatActivity implements TextWatcher, LoaderManager.LoaderCallbacks<Cursor> {

    private EditText editText = null;

    private ListView listView = null;
    private SimpleCursorAdapter adapter;

    private final static int CURSOR_LOAD_ID = 1;

    //Bundle的名称
    public static final String FILTER = "filter";
    private LoaderManager lm;
    private String filter;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_study);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(this);
        //获取ListView
        listView = (ListView) findViewById(R.id.listView);
        initListViewAdapter();
        listView.setAdapter(adapter);

        //默认查询全部联系人
        bundle = new Bundle();
        bundle.putString(FILTER, null);
        //默认activity和fragment都带有的
        lm = getSupportLoaderManager();
        lm.initLoader(CURSOR_LOAD_ID, bundle, this);
    }

    private void initListViewAdapter() {
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[]{android.R.id.text1, android.R.id.text1}, 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //得到查询的关键字
        filter = editText.getText().toString().trim();
        bundle = new Bundle();
        bundle.putString(FILTER, filter);
        if (lm==null)
            lm = getSupportLoaderManager();
        lm.restartLoader(CURSOR_LOAD_ID, bundle, this);
    }

    //callback实现的三个方法
    //根据不同id，管理不同的loader
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) 
    {
        Uri uri;
        String filter = bundle == null ? null : bundle.getString(FILTER);
        if (filter!=null)
        {
            //根据用户指定的filter过滤
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter));
            
        }else 
        {
            //显示全部
            uri = ContactsContract.Contacts.CONTENT_URI;
        }
        //获取所有的集合
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.CONTACT_STATUS
        };
        //查询
        String selection= "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND "+
                "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND "+
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        //返回去
        return new CursorLoader(this, uri, projection, selection, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) 
    {
        Log.d("结果", data.toString());
        //采集数据？
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) 
    {
    //重新复制？？
        adapter.swapCursor(null);
    }

  /*  private EditText editText = null;

    private ListView listView = null;

    private SimpleCursorAdapter adapter = null;


    private final int CURSOR_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_study);
        //绑定编辑框的文本变化事件
        editText = (EditText)findViewById(R.id.editText);
       *//* editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });*//*
        editText.addTextChangedListener(this);

        //获取ListView
        listView = (ListView)findViewById(R.id.listView);

        //创建Adapter
        //为了兼容性，此处创建android.support.v4.widget.SimpleCursorAdapter的实例
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        listView.setAdapter(adapter);

        //查询全部联系人
        Bundle args = new Bundle();
        args.putString("filter", null);

        //LoaderManager lm = getLoaderManager();
        //为了兼容Android 3.0以前的系统，我们此处调用
        //android.support.v4.app.FragmentActivity的getSupportLoaderManager()方法
        //得到的是android.support.v4.app.LoaderManager的实例，而非android.app.LoaderManager
        LoaderManager lm = getSupportLoaderManager();
        lm.initLoader(CURSOR_LOADER_ID, args, this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String filter = editText.getText().toString();
        Bundle args = new Bundle();
        args.putString("filter", filter);

        //LoaderManager lm = getLoaderManager();
        //为了兼容Android 3.0以前的系统，我们此处调用
        //android.support.v4.app.FragmentActivity的getSupportLoaderManager()方法
        //得到的是android.support.v4.app.LoaderManager的实例，而非android.app.LoaderManager
        LoaderManager lm = getSupportLoaderManager();
        lm.restartLoader(CURSOR_LOADER_ID, args, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri;

        String filter = args != null ? args.getString("filter") : null;

        if(filter != null){
            //根据用户指定的filter过滤显示
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(filter));
        }else{
            //显示全部
            uri = ContactsContract.Contacts.CONTENT_URI;
        }

        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.CONTACT_STATUS
        };

        String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND "+
                "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND "+
                "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";

        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        //为了兼容Android 3.0以前的系统，此处返回的是一个android.support.v4.content.CursorLoader实例,
        //而非android.content.CursorLoader的实例
        return new CursorLoader(this, uri, projection, selection, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }*/
}
