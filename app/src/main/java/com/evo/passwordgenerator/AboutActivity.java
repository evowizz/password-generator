package com.evo.passwordgenerator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    public LinearLayout main_view;
    String InstaID = "EvoWizz";
    String email = "EvoWizzFR@gmail.com";
    int xdacolor = Color.parseColor("#D49D2A");
    int mdicolor = Color.parseColor("#2196F3");
    int instacolor = Color.parseColor("#E63A5B");
    int gitlabcolor = Color.parseColor("#FC6D26");
    int emailcolor = Color.parseColor("#DC483C");
    String versionname = BuildConfig.VERSION_NAME;
    private static final int MAX_CLICKS_TO_UNLOCK_EGG = 7;
    private int numTimesVersionClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        LinearLayout mActivityRoot = ((LinearLayout) findViewById(R.id.main_view));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View aboutPage = new AboutPage(this)
                .setImage(R.drawable.ic_generate_black)
                .setDescription(getString(R.string.description))
                .addItem(getVersion())
                .addItem(getLibraries())
                .addGroup(getString(R.string.connect))
                .addItem(getEmailElement())
                .addItem(getTwitterElement())
                .addItem(getInstagramElement())
                .addItem(getxdaElement())
                .addItem(getGitlabElement())
                .addGroup(getString(R.string.thanksto))
                .addItem(getvhelement())
                .addItem(getmdiElement())
                .addItem(getCopyRightsElement())
                .create();

        mActivityRoot.addView(aboutPage, 1);
    }

    Element getVersion() {
        Element versionElement = new Element();
        versionElement.setTitle(getString(R.string.versionname) + " " + versionname);
        versionElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (++numTimesVersionClicked == MAX_CLICKS_TO_UNLOCK_EGG) {
                    numTimesVersionClicked = 0;
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "Congrats! You found me :D", Snackbar.LENGTH_LONG)
                            .setAction(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });

                    snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary1));
                    snackbar.setDuration(3000);
                    snackbar.show();
                } else {
                }

            }
        });
        return versionElement;
    }

    Element getLibraries() {
        Element librariesElement = new Element();
        librariesElement.setTitle(this.getString(R.string.libraries));
        librariesElement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        librariesElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLibrariesDialogFragment();
            }
        });
        return librariesElement;
    }

    Element getEmailElement() {
        Element emailElement = new Element();
        emailElement.setTitle(this.getString(R.string.emailtext));
        emailElement.setIcon(R.drawable.about_screen_email);
        emailElement.setColor(emailcolor);
        emailElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                startActivity(intent);
            }
        });

        return emailElement;
    }

    Element getTwitterElement() {
        Element twitterElement = new Element();
        twitterElement.setTitle(getString(R.string.followmeT));
        twitterElement.setIcon(R.drawable.about_screen_twitter);
        twitterElement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_twitter_color));
        twitterElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/EvoWizz";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return twitterElement;
    }

    Element getxdaElement() {
        Element xdaElement = new Element();
        xdaElement.setTitle("View my other app");
        xdaElement.setIcon(R.drawable.about_screen_xda);
        xdaElement.setColor(xdacolor);
        xdaElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://labs.xda-developers.com/store/app/com.evo.gmslauncher";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return xdaElement;
    }

    Element getInstagramElement() {
        Element instagramElement = new Element();
        instagramElement.setTitle(getString(R.string.followmeI));
        instagramElement.setIcon(R.drawable.about_screen_instagram);
        instagramElement.setColor(instacolor);
        instagramElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://instagram.com/_u/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url + InstaID));
                startActivity(i);
            }
        });
        return instagramElement;
    }

    Element getGitlabElement() {
        Element gitlabElement = new Element();
        gitlabElement.setTitle(getString(R.string.sourcecode));
        gitlabElement.setIcon(R.drawable.about_screen_gitlab);
        gitlabElement.setColor(gitlabcolor);
        gitlabElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://gitlab.com/EvoWizz/password-generator";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        return gitlabElement;
    }


    //Thanksto:

    Element getvhelement() {
        Element vhelement = new Element();
        vhelement.setTitle("Vojtěch Hořánek (@Vojtuv_tweet)");
        vhelement.setIcon(R.drawable.about_screen_twitter);
        vhelement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_twitter_color));
        vhelement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/Vojtuv_tweet";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return vhelement;
    }


    Element getmdiElement() {
        Element mdiElement = new Element();
        mdiElement.setTitle("Materialdesignicons.com");
        mdiElement.setIcon(R.drawable.about_screen_mdi);
        mdiElement.setColor(mdicolor);
        mdiElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "Materialdesignicons.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return mdiElement;
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIcon(R.drawable.ic_copyright);
        copyRightsElement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLicenseDialogFragment();
            }
        });
        return copyRightsElement;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void displayLicenseDialogFragment() {
        LicenseDialog dialog = LicenseDialog.newInstance();
        dialog.show(getFragmentManager(), "LicenseDialog");
    }

    private void displayLibrariesDialogFragment() {
        LibrariesDialog dialog = LibrariesDialog.newInstance();
        dialog.show(getFragmentManager(), "LibrariesDialog");
    }
}