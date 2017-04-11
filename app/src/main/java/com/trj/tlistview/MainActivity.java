package com.trj.tlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 这是一个测试的应用
 */
/**
 * 作者： 小童
 * 创建时间： 2017/4/11
 *
 * 知识点：
 *      1.ListView的用法
 *      2.Adapter的用法
 *          ①数组适配器 的用法
 *          ②简单适配器 的用法
 *          ③通用适配器 的用法
 *      3.ListView其它方法的用法
 *      4.菜单的简单使用
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) this.findViewById(R.id.listview);

        assistSetting();

        userArrayAdapter();

    }

//    ListView其它方法的用法
    private void assistSetting(){
//        添加背景色
        listView.setBackgroundColor(Color.parseColor("#bb824654"));
//        添加BitmapDrawable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//        添加ColorDrawable
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        listView.setDivider(colorDrawable);
//        设置分割线的高度
        listView.setDividerHeight(20);
//        添加头部View
//        listView.addHeaderView(new View(this));
//        添加底部View
//        listView.addFooterView(new View(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.shuzu:
                userArrayAdapter();
                break;
            case R.id.jiandan:
                userSimpleAdapter();
                break;
            case R.id.tongyong:
                userBaseAdapter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //    ArrayAdapter的用法（数组适配器）
    private void userArrayAdapter(){
        ArrayList<Person> arrayList = new ArrayList<>();
        Person p ;
        for(int i=1;i<10;i++){
            p = new Person("trj"+(i+10),(i+20),"陕西西安",i%2==0?true:false);
            arrayList.add(p);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
//        ListView关联Adapter
        listView.setAdapter(adapter);

    }

//    SimpleAdapter的用法（简单适配器）
    private void userSimpleAdapter(){
        ArrayList<HashMap<String ,String>> arrayList2 = new ArrayList();
        for(int i=1;i<10;i++){
            HashMap<String, String> hashMap = new HashMap();
            hashMap.put("name","trj"+(i+10));
            hashMap.put("age",(i+20)+"");
            arrayList2.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,arrayList2,android.R.layout.simple_list_item_2,new String[]{"name","age"},new int[]{android.R.id.text1,android.R.id.text2});
        // ListView关联Adapter
        listView.setAdapter(simpleAdapter);
    }

//    BaseAdapter的用法（通用适配器）
    private void userBaseAdapter(){
        ArrayList<Person> arrayList = new ArrayList<>();
        Person p ;
        for(int i=1;i<10;i++){
            p = new Person("trj"+(i+10),(i+20),"陕西西安",i%2==0?true:false);
            arrayList.add(p);
        }
        // BaseAdapter的用法
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,arrayList);
        // ListView关联Adapter
        listView.setAdapter(myBaseAdapter);
    }

    //    自定义的Adapter，需要继承BaseAdapter
    class MyBaseAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Person> arrayList;
        private LayoutInflater inflater;

        public MyBaseAdapter(Context context, ArrayList<Person> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
            inflater = LayoutInflater.from(context);
        }



        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if(convertView==null){
                convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Person p = arrayList.get(position);
            viewHolder.textView1.setText(p.getName());
            viewHolder.textView2.setText(p.toString());
            return convertView;
        }

        class ViewHolder{
            TextView textView1;
            TextView textView2;
            public ViewHolder(View convertView) {
                textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                textView2 = (TextView) convertView.findViewById(android.R.id.text2);
            }
        }



    }


}
