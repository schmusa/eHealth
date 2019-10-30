package de.haukesomm.healthdemo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import de.haukesomm.healthdemo.R;
import de.haukesomm.healthdemo.privacy.PrivacyMode;
import de.haukesomm.healthdemo.privacy.PrivacyModeView;

public class PrivacySetupActivity extends AppCompatActivity {

    private PrivacyModeView mPrivacyModeView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_privacy);

        initToolbar();
        initPrivacyMode();
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_policy_privacy_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initPrivacyMode() {
        mPrivacyModeView = findViewById(R.id.activity_policy_privacy_mode);
        updatePrivacyMode();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_policy_privacy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_policy_privacy_menuAction_finish:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PrivacyModeView.REQUEST_SELECT_PRIVACY_MODE:
                updatePrivacyMode();
                break;
        }
    }


    // TODO Make this method part of the View itself
    @Deprecated
    public void updatePrivacyMode() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int id = prefs
                .getInt(getString(R.string.pref_int_privacy_lastModeID), PrivacyMode.UNKNOWN.getID());
        PrivacyMode mode = PrivacyMode.fromID(id);
        mPrivacyModeView.setMode(mode);
    }
}
