package com.instantappsample.featureshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * The 'entrance' activity for the cacti shop feature, which serves as the container
 * for the fragment.
 */
public class CactiShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacti_shop);

        // Create a new fragment to hold the RecyclerView, adapter, and data
        if (savedInstanceState == null) {
            ViewGroup fragmentContainer = (ViewGroup) findViewById(R.id.activity_cacti_shop_container);
            if (fragmentContainer != null) {
                Fragment fragment = CactiShopFragment.newInstance();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(fragmentContainer.getId(), fragment,
                        fragment.getClass().getName());
                fragmentTransaction.commit();
            }
        }

        // Set the main title for this fragment
        setTitle(R.string.action_bar_title);
    }
}
