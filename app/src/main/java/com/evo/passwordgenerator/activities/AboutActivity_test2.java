package com.evo.passwordgenerator.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.ThemeSingleton;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.evo.passwordgenerator.BuildConfig;
import com.evo.passwordgenerator.R;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;

import com.evo.passwordgenerator.dialogs.ChangelogDialog;
import com.evo.passwordgenerator.dialogs.LibrariesDialog;
import com.evo.passwordgenerator.dialogs.LicenseDialog;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import static com.evo.passwordgenerator.R.color.colorChangelog;
import static com.evo.passwordgenerator.R.color.colorEmail;
import static com.evo.passwordgenerator.R.color.colorGooglePlus;
import static com.evo.passwordgenerator.R.color.colorIcon;
import static com.evo.passwordgenerator.R.color.colorInstagram;
import static com.evo.passwordgenerator.R.color.colorLibraries;
import static com.evo.passwordgenerator.R.color.colorRate;
import static com.evo.passwordgenerator.R.color.colorReporter;
import static com.evo.passwordgenerator.R.color.colorTranslate;
import static com.evo.passwordgenerator.R.color.colorTranslators;
import static com.evo.passwordgenerator.R.color.colorTwitter;
import static com.evo.passwordgenerator.R.color.colorVersion;


public class AboutActivity_test2 extends MaterialAboutActivity {

    private static final int MAX_CLICKS = 7;
    String versionname = BuildConfig.VERSION_NAME;
    int versioncode = BuildConfig.VERSION_CODE;
    private int ntvc;

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        updateRecentTasksUi();
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .icon(R.mipmap.ic_launcher)
                .build());


        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.version)
                .subText(versionname + " " + "(" + versioncode + ")")
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .color(ContextCompat.getColor(c, colorVersion))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        if (++ntvc == MAX_CLICKS) {
                            ntvc = 0;
                            Snackbar snackbar = Snackbar.make(((AboutActivity_test2) c).findViewById(R.id.mal_material_about_activity_coordinator_layout), "Congrats! You found me :D", Snackbar.LENGTH_LONG)
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
                    }
                })
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.changelog)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, colorChangelog))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        showChangelog();
                    }
                })
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.rate_title)
                .subText(R.string.rate)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .color(ContextCompat.getColor(c, colorRate))
                        .sizeDp(20))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intentrate = new Intent(Intent.ACTION_VIEW);
                        intentrate.setData(Uri.parse("https://labs.xda-developers.com/store/app/com.evo.passwordgenerator"));
                        c.startActivity(intentrate);
                    }
                })
                .build());

        final String[] translators = new String[5];
        translators[0] = "Vojtechh (" + getString(R.string.czech) + " " + getString(R.string.translators) + ")";
        translators[1] = "BBB";
        translators[2] = "CCC";
        translators[3] = "DDD";
        translators[4] = "EEE";

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.translators)
                .subText(R.string.list_all_translators)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_flag)
                        .color(ContextCompat.getColor(c, colorTranslators))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        MaterialDialog.Builder builder = new MaterialDialog.Builder(c)
                                .title(R.string.translators)
                                .items(translators)
                                .positiveText(android.R.string.ok)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                })
                                .positiveColorRes(R.color.colorAccent)
                                .autoDismiss(false);

                        MaterialDialog dialog = builder.build();
                        dialog.show();
                    }
                })
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.libraries)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_bookmark)
                        .color(ContextCompat.getColor(c, colorLibraries))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        displayLibrariesDialogFragment();
                    }
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.developer);
        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("EvoWizz")
                .subText(R.string.dev_subtext)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        displayLicenseDialogFragment();
                    }
                })
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.emailtext)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(c, colorEmail))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intentemail = new Intent(Intent.ACTION_SEND);
                        intentemail.setType("message/rfc822");
                        intentemail.putExtra(Intent.EXTRA_EMAIL, new String[]{"EvoWizzFR@gmail.com"});
                        c.startActivity(intentemail);
                    }
                })
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.followmeT)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_twitter)
                        .color(ContextCompat.getColor(c, colorTwitter))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intenttwitter = new Intent(Intent.ACTION_VIEW);
                        intenttwitter.setData(Uri.parse("https://twitter.com/EvoWizz"));
                        c.startActivity(intenttwitter);
                    }
                })
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.followmeG)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_google_plus)
                        .color(ContextCompat.getColor(c, colorGooglePlus))
                        .sizeDp(22))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intentgoogleplus = new Intent(Intent.ACTION_VIEW);
                        intentgoogleplus.setData(Uri.parse("https://plus.google.com/+EvoWizz"));
                        c.startActivity(intentgoogleplus);
                    }
                })
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.followmeI)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_instagram)
                        .color(ContextCompat.getColor(c, colorInstagram))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intentinstagram = new Intent(Intent.ACTION_VIEW);
                        intentinstagram.setData(Uri.parse("https://www.instagram.com/EvoWizz/"));
                        c.startActivity(intentinstagram);
                    }
                })
                .build());

        MaterialAboutCard.Builder supdevCardBuilder = new MaterialAboutCard.Builder();
        supdevCardBuilder.title(R.string.sup_dev);
        supdevCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        supdevCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.issue_reporter_title)
                .subText(R.string.issue_reporter)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_bug_report)
                        .color(ContextCompat.getColor(c, colorReporter))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent reporter = new Intent(c, GHReporter.class);
                        c.startActivity(reporter);
                    }
                })
                .build());

        supdevCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.translate_title)
                .subText(R.string.translate)
                .icon(new IconicsDrawable(c)
                        .icon(GoogleMaterial.Icon.gmd_translate)
                        .color(ContextCompat.getColor(c, colorTranslate))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent reporter = new Intent(c, GHReporter.class);
                        c.startActivity(reporter);
                    }
                })
                .build());


        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), supdevCardBuilder.build());
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }

    public void showChangelog() {
        int accentColor = ThemeSingleton.get().widgetColor;
        if (accentColor == 0)
            accentColor = ContextCompat.getColor(this, R.color.colorAccent);
        ChangelogDialog.create(false, accentColor)
                .show(getSupportFragmentManager(), "changelog");
    }

    private void displayLibrariesDialogFragment() {
        LibrariesDialog dialog = LibrariesDialog.newInstance();
        dialog.show(getFragmentManager(), "LibrariesDialog");
    }

    private void displayLicenseDialogFragment() {
        LicenseDialog dialog = LicenseDialog.newInstance();
        dialog.show(getFragmentManager(), "LicenseDialog");
    }

    private void updateRecentTasksUi() {
        final ActivityManager.TaskDescription taskDescription =
                new ActivityManager.TaskDescription(
                        getString(R.string.app_name) + " - " + getString(R.string.about),
                        null,
                        ContextCompat.getColor(this, R.color.colorAccent));

        setTaskDescription(taskDescription);
    }

}
