package com.example.ilia.exercise_four.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.fragments.ListFragment;
import com.example.ilia.exercise_four.fragments.ViewPagerFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,IConnectFragmentWithActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frg_list, new ListFragment(),"1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frg_pager, new ViewPagerFragment(),"2").commit();
        frameLayout=(FrameLayout)findViewById(R.id.frg_pager);
        frameLayout.setVisibility(View.VISIBLE);
        frameLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frg_pager:
                Toast.makeText(this,"������",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemSelected(int selectedItem) {
        ISetCurrentItem iSetCurrentItem=(ISetCurrentItem)getSupportFragmentManager().findFragmentByTag("2");
        iSetCurrentItem.setCurrentItem(selectedItem);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
