package com.evo.passwordgenerator.activities;

import android.os.Bundle;

import com.evo.passwordgenerator.BuildConfig;
import com.heinrichreimersoftware.androidissuereporter.IssueReporterActivity;
import com.heinrichreimersoftware.androidissuereporter.model.github.ExtraInfo;
import com.heinrichreimersoftware.androidissuereporter.model.github.GithubTarget;

public class GHReporter extends IssueReporterActivity {

    @Override
    public GithubTarget getTarget() {
        return new GithubTarget("EvoWizz", "password-generator");
    }

    @Override
    public String getGuestToken() {
        return BuildConfig.API_KEY;
    }
}