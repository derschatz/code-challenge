package com.example.searchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.searchapp.ui.main.TypoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final List<String> mTypoList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private TypoAdapter mTypoAdapter;

    @BindView(R.id.rv_typo)
    RecyclerView mTypoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        initList();
        setUpView();
    }

    private void initList() {
        mTypoList.add(" you");
        mTypoList.add("yuo");
        mTypoList.add("probably");
        mTypoList.add("porbalby");
        mTypoList.add("despite");
        mTypoList.add("desptie");
        mTypoList.add("moon");
        mTypoList.add("nmoo");
        mTypoList.add("misspellings");
        mTypoList.add("mpeissngslli");
        mTypoList.add("pale");
        mTypoList.add("ple");
        mTypoList.add("pales");
        mTypoList.add("pale");
        mTypoList.add("pale");
        mTypoList.add("bale");
        mTypoList.add("pale");
        mTypoList.add("bake");
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
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mTypoRecyclerView.setLayoutManager(mLayoutManager);
        mTypoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTypoAdapter = new TypoAdapter(mTypoList);
    }
}
