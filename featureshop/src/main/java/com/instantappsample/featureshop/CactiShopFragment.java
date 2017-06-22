package com.instantappsample.featureshop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.instantapps.InstantApps;
import com.instantappsample.featureshop.model.CactiShopItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The fragment for the cacti shop feature, which handles the action bar items, and
 * holds the <code>RecyclerView</code>, adapter, and data that is sent to it.
 */
public class CactiShopFragment extends Fragment {

    private static final String TAG = CactiShopFragment.class.getSimpleName();

    public static CactiShopFragment newInstance() {
        CactiShopFragment fragment = new CactiShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cacti_shop, container, false);

        setHasOptionsMenu(true);

        // Set up the RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_cacti_list_rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up the data.  Note that data for a RecyclerView usually comes from a service or data
        // tier, but for the sake of example this is just sample information.
        List<CactiShopItem> cactiShopItems = new ArrayList<>();
        cactiShopItems.add(
                new CactiShopItem(com.instantappsample.cacticollector.R.drawable.cactus1,
                        getString(com.instantappsample.cacticollector.R.string.cactus_1_title),
                        4,
                        true,
                        new BigDecimal(5.99)));
        cactiShopItems.add(
                new CactiShopItem(com.instantappsample.cacticollector.R.drawable.cactus2,
                        getString(com.instantappsample.cacticollector.R.string.cactus_2_title),
                        2,
                        true,
                        new BigDecimal(9.99)));
        cactiShopItems.add(
                new CactiShopItem(com.instantappsample.cacticollector.R.drawable.cactus3,
                        getString(com.instantappsample.cacticollector.R.string.cactus_3_title),
                        5,
                        false,
                        new BigDecimal(13.99)));
        cactiShopItems.add(
                new CactiShopItem(com.instantappsample.cacticollector.R.drawable.cactus4,
                        getString(com.instantappsample.cacticollector.R.string.cactus_4_title),
                        4,
                        true,
                        new BigDecimal(11.99)));
        cactiShopItems.add(
                new CactiShopItem(com.instantappsample.cacticollector.R.drawable.cactus5,
                        getString(com.instantappsample.cacticollector.R.string.cactus_5_title),
                        5,
                        false,
                        new BigDecimal(10.99)));

        CactiShopRecyclerAdapter adapter = new CactiShopRecyclerAdapter(cactiShopItems);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreateOptionsMenu");
        }

        // Adds items to the action bar
        inflater.inflate(R.menu.action_done, menu);

        // Only show the "install" option for the instant app
        if (InstantApps.isInstantApp(getContext())) {
            inflater.inflate(com.instantappsample.cacticollector.R.menu.action_install, menu);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            String confirmMessage = getString(R.string.action_done_message);
            showConfirmDialog(confirmMessage);
        }
        else if (item.getItemId() == com.instantappsample.cacticollector.R.id.action_install) {
            String confirmMessage = getString(com.instantappsample.cacticollector.R.string.action_install_message);
            showConfirmDialog(confirmMessage);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showConfirmDialog(String confirmMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(confirmMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
