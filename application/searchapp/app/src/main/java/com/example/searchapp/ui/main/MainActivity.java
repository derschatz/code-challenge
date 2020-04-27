package com.example.searchapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.searchapp.R;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private LinearLayoutManager mLayoutManager;
    private WordAdapter mWordAdapter;

    @BindView(R.id.tb_main)
    Toolbar toolbar;

    @BindView(R.id.rv_word)
    RecyclerView mWordRecyclerView;

    @BindView(R.id.sv_word_search)
    SearchView mSearchView;


    private Observer<? super ArrayList<String>> mWordListUpdateObserver;
    private SearchView.OnQueryTextListener mWordQueryListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        setupToolbar();

        mWordListUpdateObserver = new WordObserver(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getWordMutableLiveData().observe(this, mWordListUpdateObserver);

        mWordQueryListener = new WordQueryListener();
        mSearchView.setOnQueryTextListener(mWordQueryListener);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Search App");;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_email) {
            // todo
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class WordObserver implements Observer<ArrayList<String>> {
        private final Context mContext;

        public WordObserver(Context context) {
            this.mContext = context;
        }

        @Override
        public void onChanged(ArrayList<String> WordList) {
            mLayoutManager = new LinearLayoutManager(mContext);
            mLayoutManager.setOrientation(RecyclerView.VERTICAL);
            mWordRecyclerView.setLayoutManager(mLayoutManager);
            mWordRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mWordAdapter = new WordAdapter(WordList);
            mWordRecyclerView.setAdapter(mWordAdapter);
        }
    }

    private class WordQueryListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            viewModel.filter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }
}
