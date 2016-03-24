package com.erikle2.headphonetester.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.erikle2.headphonetester.R;
import com.erikle2.headphonetester.ui.fragments.FragmentHandler;
import com.erikle2.headphonetester.ui.views.ITalkToMain;
import com.erikle2.headphonetester.ui.adapters.MyAdapter;
import com.erikle2.headphonetester.model.entities.HeadPhoneTest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ITalkToMain {

    HeadPhoneTest mTest;
    String TITLES[] = {"Earphonetest", "Compare", "About"};
    int ICONS[] = {android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_dialog_map, android.R.drawable.ic_menu_agenda};
    int PROFILE = R.drawable.head;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Navigation drawer list menu
     */
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerview;

    /**
     * Layout holding the navigation view
     */
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerlayout;

    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private ActionBarDrawerToggle mDrawerToggle;

    private FragmentHandler mFragmentHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mFragmentHandler = new FragmentHandler(this);
        //Tillåter ej recyclerview att ändra storlek själv
        mRecyclerview.setHasFixedSize(true);

        mRecyclerAdapter = new MyAdapter(this, TITLES, ICONS, PROFILE);
        mRecyclerview.setAdapter(mRecyclerAdapter);
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        mRecyclerview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = mRecyclerview.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mGestureDetector.onTouchEvent(e)) {
                    int pos = mRecyclerview.getChildAdapterPosition(child);
                    mDrawerlayout.closeDrawers();
                    Toast.makeText(MainActivity.this, "Item clicked " + pos, Toast.LENGTH_LONG);

                    mFragmentHandler.setNaviFragment(pos);

                    return true;

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerview.setLayoutManager(mLayoutManager);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerlayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerlayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


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

    @Override
    public void onNavigationSelected(int position) {

    }

    @Override
    public HeadPhoneTest startNewTest(String name) {
        return mTest = new HeadPhoneTest(name, getResources().getStringArray(R.array.test_titles));
    }

    @Override
    public HeadPhoneTest getTest() {
        return mTest;
    }
}
