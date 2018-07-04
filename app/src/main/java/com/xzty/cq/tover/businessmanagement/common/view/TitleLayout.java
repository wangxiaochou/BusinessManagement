package com.xzty.cq.tover.businessmanagement.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;


/**
 * Created by xxb6 on 2018/3/6.
 * explain 标题基类
 */

public class TitleLayout extends Toolbar {
    public TitleLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.toolbar, this);
        TextView toolbarlogout = (TextView) findViewById(R.id.tv_toolbar_logout);
       /* toolbarlogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = context.getSharedPreferences("spfile", Context.MODE_PRIVATE).edit();
                edit.putString(Constant.SPFilekey.USERNAME,"").commit();
                edit.putString(Constant.SPFilekey.PASSWORD,"").commit();
                edit.putStringSet(Constant.SPFilekey.RULE,null).commit();
               // JPushInterface.deleteAlias(context,1111);
                ActivityCollector.finishToLogin();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.fileList();

            }
        });*/


    }
}
