package com.evo.passwordgenerator.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.webkit.WebView;

import com.evo.passwordgenerator.R;

public class LicenseDialog extends DialogFragment {

    public static LicenseDialog newInstance() {
        return new LicenseDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        WebView view = (WebView) LayoutInflater.from(getActivity()).inflate(R.layout.dialog_licenses, null);
        view.loadUrl("file:///android_asset/license.html");
        return new AlertDialog.Builder(getActivity(), R.style.AlertDialog)
                .setTitle(getString(R.string.license))
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}