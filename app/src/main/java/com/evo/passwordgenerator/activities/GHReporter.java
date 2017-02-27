package com.evo.passwordgenerator.activities;

import com.heinrichreimersoftware.androidissuereporter.IssueReporterActivity;
import com.heinrichreimersoftware.androidissuereporter.model.github.GithubTarget;

public class GHReporter extends IssueReporterActivity {

    @Override
    public GithubTarget getTarget() {
        return new GithubTarget("EvoWizz", "password-generator");
    }

    @Override
    public String getGuestToken() {
        return "";
//        return BuildConfig.API_KEY;
    }
}