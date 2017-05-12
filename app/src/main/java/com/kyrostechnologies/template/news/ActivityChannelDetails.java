package com.kyrostechnologies.template.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kyrostechnologies.template.news.adapter.AdapterNewsListWithHeader;
import com.kyrostechnologies.template.news.data.Constant;
import com.kyrostechnologies.template.news.data.GlobalVariable;
import com.kyrostechnologies.template.news.data.Tools;
import com.kyrostechnologies.template.news.model.Channel;
import com.kyrostechnologies.template.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class ActivityChannelDetails extends AppCompatActivity {
    public static final String EXTRA_OBJCT = "com.app.sample.news.CHANNEL";

    // give preparation animation activity transition
    public static void navigate(AppCompatActivity activity, View transitionImage, Channel obj) {
        Intent intent = new Intent(activity, ActivityChannelDetails.class);
        intent.putExtra(EXTRA_OBJCT, obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_OBJCT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private Toolbar toolbar;
    private ActionBar actionBar;
    private AdapterNewsListWithHeader mAdapter;
    private Channel channel;
    private GlobalVariable global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_details);

        // animation transition
        ViewCompat.setTransitionName(findViewById(android.R.id.content), EXTRA_OBJCT);

        // get extra object
        channel = (Channel) getIntent().getSerializableExtra(EXTRA_OBJCT);

        initToolbar();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String chnnl = channel.getName();
        List<Channel> channels = Constant.getChannelData(this);
        List<News>  list_news  = new ArrayList<>();
        global = (GlobalVariable) getApplication();

        if(chnnl.equalsIgnoreCase(channels.get(0).getName())){       // politics
            list_news =  global.getNewsPolitics();

        }else if(chnnl.equalsIgnoreCase(channels.get(1).getName())){ // Entertainment
            list_news =  global.getNewsEntertainment();

        }else if(chnnl.equalsIgnoreCase(channels.get(2).getName())){ // Science
            list_news =  global.getNewsScience();

        }else if(chnnl.equalsIgnoreCase(channels.get(3).getName())){ // Sport
            list_news =  global.getNewsSport();

        }else if(chnnl.equalsIgnoreCase(channels.get(4).getName())){ // Business
            list_news =  global.getNewsBusiness();

        }else if(chnnl.equalsIgnoreCase(channels.get(5).getName())){ // Technology
            list_news =  global.getNewsTechnology();
        }

        //set data and list adapter
        mAdapter = new AdapterNewsListWithHeader(this, list_news.get(list_news.size()-1), list_news);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdapterNewsListWithHeader.OnItemClickListener() {
            @Override
            public void onItemClick(View v, News obj, int position) {
                ActivityNewsDetails.navigate(ActivityChannelDetails.this, v.findViewById(R.id.image), obj);
            }
        });

        // for system bar in lollipop
        Tools.systemBarLolipop(this);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(channel.getName());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
