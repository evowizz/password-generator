package com.evo.passwordgenerator;

import android.os.Bundle;

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
        return "1fa7b5489ad70c1670efa5699af08bebc47b4103";
    }
}