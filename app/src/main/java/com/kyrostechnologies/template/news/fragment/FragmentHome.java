package com.kyrostechnologies.template.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyrostechnologies.template.news.ActivityMain;
import com.kyrostechnologies.template.news.ActivityNewsDetails;
import com.kyrostechnologies.template.news.R;
import com.kyrostechnologies.template.news.adapter.AdapterNewsListWithHeader;
import com.kyrostechnologies.template.news.data.Constant;
import com.kyrostechnologies.template.news.data.GlobalVariable;
import com.kyrostechnologies.template.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private View root_view;
    private GlobalVariable global;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_home, null);
        global = (GlobalVariable) getActivity().getApplication();

        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // prepare tab layout
        initTabLayout();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                displayListNews(getNewsByCategory(tab.getText().toString()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return root_view;
    }

    private void displayListNews(List<News> items){
        AdapterNewsListWithHeader mAdapter = new AdapterNewsListWithHeader(getActivity(), items.get(items.size()-1), items);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterNewsListWithHeader.OnItemClickListener() {
            @Override
            public void onItemClick(View v, News obj, int position) {
                ActivityNewsDetails.navigate((ActivityMain)getActivity(), v.findViewById(R.id.image), obj);
            }
        });
    }

    private void initTabLayout(){
        tabLayout = (TabLayout) root_view.findViewById(R.id.tabs);
        List<String> items_tab = Constant.getHomeCatgeory(getActivity());
        tabLayout.addTab(tabLayout.newTab().setText(items_tab.get(0)), true);

        // display first news
        displayListNews(getNewsByCategory(items_tab.get(0)));

        for (int i=1; i< items_tab.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(items_tab.get(i)));
        }
    }

    private List<News> getNewsByCategory(String category) {
        if(category.equalsIgnoreCase("LATEST")){
            return global.getNewsLatest();
        }else if(category.equalsIgnoreCase("TRENDING")){
            return global.getNewsTrending();
        }else if(category.equalsIgnoreCase("HIGHLIGHT")){
            return global.getNewsHighlight();
        }else if(category.equalsIgnoreCase("POPULAR")){
            return global.getNewsPopular();
        }else if(category.equalsIgnoreCase("MOST VIEW")){
            return global.getNewsMostview();
        }
        return new ArrayList<>();
    }


}
