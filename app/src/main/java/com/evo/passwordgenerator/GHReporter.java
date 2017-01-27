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
        return "b6e3dcbed43a669649927a4790de0aff28cede52";
    }
}