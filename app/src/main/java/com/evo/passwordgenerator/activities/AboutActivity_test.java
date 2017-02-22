package com.evo.passwordgenerator.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.afollestad.materialdialogs.internal.ThemeSingleton;
import com.evo.passwordgenerator.BuildConfig;
import com.evo.passwordgenerator.R;
import com.evo.passwordgenerator.dialogs.ChangelogDialog;
import com.evo.passwordgenerator.dialogs.LibrariesDialog;
import com.evo.passwordgenerator.dialogs.LicenseDialog;

public class AboutActivity_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.license:
                displayLicenseDialogFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayLicenseDialogFragment() {
        LicenseDialog dialog = LicenseDialog.newInstance();
        dialog.show(getFragmentManager(), "LicenseDialog");
    }

    public static class PreferencesFragment extends PreferenceFragment {

        String versionname = R.string.versionname + " " + BuildConfig.VERSION_NAME;
        private static final int value2 = 7;
        private int value;
        private Context context;


        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            context = getActivity().getApplicationContext();

            Preference IssueReporter = findPreference("issuereporter");
            Preference Translate = findPreference("translate");
            Preference Rate = findPreference("rate");
            Preference Dev = findPreference("developer");
            Preference Devtwitter = findPreference("devtwitter");
            Preference Devgoogleplus = findPreference("devgoogleplus");
            Preference Devemail = findPreference("devemail");
            Preference Devinstagram = findPreference("devinstagram");
            Preference Libraries = findPreference("libraries");


            //Support dev preference cat
            //BugReporter Pref
            IssueReporter.setIcon(R.drawable.about_screen_reporter);
            Intent reporter = new Intent(getActivity(), GHReporter.class);
            IssueReporter.setIntent(reporter);

            //Translate Pref
            Translate.setIcon(R.drawable.about_screen_translate);
            Intent intenttranslate = new Intent(Intent.ACTION_VIEW);
            intenttranslate.setData(Uri.parse("https://twitter.com/EvoWizz"));
            Translate.setIntent(intenttranslate);

            //Rate Pref
            Rate.setIcon(R.drawable.about_screen_rate);
            Intent intentrate = new Intent(Intent.ACTION_VIEW);
            intentrate.setData(Uri.parse("https://about:blank"));
            Rate.setIntent(intentrate);

            //Dev preference cat
            //Dev preference
            Dev.setIcon(R.drawable.evo_logo);
            Dev.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (++value == value2) {
                        value = 0;
                        Snackbar snackbar = Snackbar.make(getActivity().getWindow().getDecorView().getRootView().findViewById(R.id.coordinatorLayout), "Congrats! You found me :D", Snackbar.LENGTH_LONG)
                                .setAction(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                });

                        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
                        snackbar.setDuration(3000);
                        snackbar.show();
                    } else {
                    }
                    return true;
                }
            });

            //Devemail preference
            Devemail.setIcon(R.drawable.about_screen_email);
            Intent intentemail = new Intent(Intent.ACTION_SEND);
            intentemail.setType("message/rfc822");
            intentemail.putExtra(Intent.EXTRA_EMAIL, new String[]{"EvoWizzFR@gmail.com"});
            Devemail.setIntent(intentemail);

            //Devtwitter preference
            Devtwitter.setIcon(R.drawable.about_screen_twitter);
            Intent intenttwitter = new Intent(Intent.ACTION_VIEW);
            intenttwitter.setData(Uri.parse("https://twitter.com/EvoWizz"));
            Devtwitter.setIntent(intenttwitter);

            //Devgoogleplus preference
            Devgoogleplus.setIcon(R.drawable.about_screen_googleplus);
            Intent intentgoogleplus = new Intent(Intent.ACTION_VIEW);
            intentgoogleplus.setData(Uri.parse("https://plus.google.com/+EvoWizz"));
            Devgoogleplus.setIntent(intentgoogleplus);

            //Devinstagram preference
            Devinstagram.setIcon(R.drawable.about_screen_instagram);
            Intent intentinstagram = new Intent(Intent.ACTION_VIEW);
            intentinstagram.setData(Uri.parse("https://www.instagram.com/EvoWizz/"));
            Devinstagram.setIntent(intentinstagram);

            //Credits pref cat
            //Librairies pref
            Libraries.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    displayLibrariesDialogFragment();
                    return true;
                }
            });

        }



        private void displayLibrariesDialogFragment() {
            LibrariesDialog dialog = LibrariesDialog.newInstance();
            dialog.show(getFragmentManager(), "LibrariesDialog");
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // remove dividers
            View rootView = getView();
            ListView list = (ListView) rootView.findViewById(android.R.id.list);
            list.setDivider(null);

        }


    }



}