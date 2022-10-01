package org.taehyeon.welcome_pet_khackathon.Experience;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.taehyeon.welcome_pet_khackathon.R;

import java.util.ArrayList;


public class experience_Fragment extends Fragment {

    private ArrayList<Experience> ExperienceList = new ArrayList<>();
    ArrayList<Experience> search_list = new ArrayList<>();
    private RecyclerView recyclerView;
    Experience_Adapter mAdapter;
    EditText SearchET;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExperienceList.add(new Experience("배변훈련이 안됐을 때"));
        ExperienceList.add(new Experience("반려견이 아플 때"));
        ExperienceList.add(new Experience("전문가가 말하는 반려견의 문제행동"));
        ExperienceList.add(new Experience("낯선사람에게 짖을 때 방법"));
        ExperienceList.add(new Experience("간식때문에 밥을 안먹는다면?"));
        ExperienceList.add(new Experience("예방접종 시기 알아보기"));
        ExperienceList.add(new Experience("유기견에 대한 오해"));
        ExperienceList.add(new Experience("밤마다 짖는 반려견은 어떤 생각일까?"));
        ExperienceList.add(new Experience("반려견이 지나치게 활발할 때"));


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_experience_, container, false);

        SearchET = (EditText) v.findViewById(R.id.editSearch);
        SearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = SearchET.getText().toString();
                //searchFilter(searchText);
                search_list.clear();

                if(searchText.equals("")) {
                    mAdapter.setItems(ExperienceList);
                }else {
                    for(int a=0; a< ExperienceList.size(); a++) {
                        if (ExperienceList.get(a).getProblem().toLowerCase().contains(searchText.toLowerCase())) {
                            search_list.add(ExperienceList.get(a));
                        }
                        mAdapter.setItems(search_list);
                    }
                }

            }
        });


        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        mAdapter = new Experience_Adapter(ExperienceList);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return v;
    }

}