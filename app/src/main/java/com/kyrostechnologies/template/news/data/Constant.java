package com.kyrostechnologies.template.news.data;

import android.content.Context;
import android.content.res.TypedArray;

import com.kyrostechnologies.template.news.R;
import com.kyrostechnologies.template.news.model.Channel;
import com.kyrostechnologies.template.news.model.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SuppressWarnings("ResourceType")
public class Constant {

    private static Random r = new Random();

    public static List<String> getHomeCatgeory(Context ctx) {
        List<String> items = new ArrayList<>();
        String name_arr[] = ctx.getResources().getStringArray(R.array.home_category);
        for (int i = 0; i < name_arr.length; i++) {
            items.add(name_arr[i]);
        }
        return items;
    }

    public static List<Channel> getChannelData(Context ctx) {
        List<Channel> items = new ArrayList<>();
        String name_arr[] = ctx.getResources().getStringArray(R.array.channel_name);
        String color_arr[] = ctx.getResources().getStringArray(R.array.channel_color);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.channel_icon);
        for (int i = 0; i < name_arr.length; i++) {
            Channel ch = new Channel(name_arr[i], color_arr[i], drw_arr.getResourceId(i, -1));
            items.add(ch);
        }
        return items;
    }

    public static List<News> getNewsPolitics(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_p);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_p);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(0));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getNewsEntertainment(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_e);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_e);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(1));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getNewsScience(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_sc);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_sc);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(2));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getNewsSport(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_sp);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_sp);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(3));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getNewsBusiness(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_b);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_b);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(4));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getNewsTechnology(Context ctx) {
        List<News> items = new ArrayList<>();
        String title_arr[] = ctx.getResources().getStringArray(R.array.news_title_t);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.news_img_t);
        String content = ctx.getString(R.string.very_long_lorem_ipsum);
        for (int i = 0; i < title_arr.length; i++) {
            News n = new News(title_arr[i], getRandomDate(ctx), drw_arr.getResourceId(i, -1), content, getChannelData(ctx).get(5));
            items.add(n);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<News> getAllNews(Context ctx) {
        List<News> items = new ArrayList<>();
        items.addAll(getNewsPolitics(ctx));
        items.addAll(getNewsEntertainment(ctx));
        items.addAll(getNewsScience(ctx));
        items.addAll(getNewsSport(ctx));
        items.addAll(getNewsBusiness(ctx));
        items.addAll(getNewsTechnology(ctx));
        Collections.shuffle(items);
        return items;
    }

    public static String formatTime(long time) {
        // income time
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);

        // current time
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat dateFormat = null;
        if (date.get(Calendar.YEAR) == curDate.get(Calendar.YEAR)) {
            if (date.get(Calendar.DAY_OF_YEAR) == curDate.get(Calendar.DAY_OF_YEAR)) {
                dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
            } else {
                dateFormat = new SimpleDateFormat("MMM d", Locale.US);
            }
        } else {
            dateFormat = new SimpleDateFormat("MMM yyyy", Locale.US);
        }
        return dateFormat.format(time);
    }

    public static String getRandomDate(Context ctx) {
        String date_arr[] = ctx.getResources().getStringArray(R.array.general_date);
        return date_arr[getRandomIndex(0, date_arr.length - 1)];
    }

    private static int getRandomIndex(int min, int max) {
        return r.nextInt(max - min) + min;
    }

}
