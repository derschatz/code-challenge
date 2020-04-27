package com.example.searchapp.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.searchapp.utils.JumbledUtils;
import com.example.searchapp.utils.TypoUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainViewModel extends ViewModel {
    MutableLiveData<ArrayList<String>> mWordLiveData;
    ArrayList<String> mSourceList;
    ArrayList<String> mFilterList;

    public MainViewModel() {
        mWordLiveData = new MutableLiveData<>();
        mSourceList = new ArrayList<>();
        mFilterList = new ArrayList<>();
        init();
    }

    private void init() {
        populateList();
        mWordLiveData.setValue(mFilterList);
    }

    private void populateList() {
        mSourceList.clear();
        mSourceList.add("Apple");
        mSourceList.add("Banana");
        mSourceList.add("Pineapple");
        mSourceList.add("Orange");
        mSourceList.add("Lychee");
        mSourceList.add("Gavava");
        mSourceList.add("Peech");
        mSourceList.add("Melon");
        mSourceList.add("Watermelon");
        mSourceList.add("Papaya");
        mSourceList.add("you");
        mSourceList.add("probably");
        mSourceList.add("despite");
        mSourceList.add("moon");
        mSourceList.add("misspellings");
        mSourceList.add("pale");
        mSourceList.add("pales");
        mSourceList.add("pale");
        mSourceList.add("pale");
        mSourceList.add("pale");

        filter(null);
    }

    MutableLiveData<ArrayList<String>> getWordMutableLiveData() {
        return mWordLiveData;
    }

    void filter(final String query) {
        final ArrayList<String> matches = new ArrayList<>();
        if (!TextUtils.isEmpty(query)) {
            final List<String> aux = mSourceList
                    .stream()
                    .filter(w -> isMatch(w.toLowerCase(), query.toLowerCase()))
                    .collect(Collectors.toCollection(ArrayList::new));
            matches.addAll(aux);
        }
        if (!matches.isEmpty()) {
            mFilterList.clear();
            mFilterList.addAll(matches);
        } else {
            mFilterList.addAll(mSourceList);
        }
    }

    private boolean isMatch(final String word, final String query) {
        return TypoUtils.isTypo(word, query)^JumbledUtils.isJumbled(word, query);
    }
}
