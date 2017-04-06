package id.co.vileo.my_bus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import id.co.vileo.my_bus.Fragment.HomeFragment;
import id.co.vileo.my_bus.Fragment.MyAccountFragment;
import id.co.vileo.my_bus.Fragment.OrderFragment;
import id.co.vileo.my_bus.Models.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem;

    HomeFragment homeFragment;
    OrderFragment orderFragment;
    MyAccountFragment myAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bottom page
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_order:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_my_account:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                }
        );

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                Log.d("page", "onPageSelected: "+ position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);

    }

    public void setupViewPager(ViewPager upViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        myAccountFragment = new MyAccountFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(orderFragment);
        adapter.addFragment(myAccountFragment);
        upViewPager.setAdapter(adapter);
    }
}
