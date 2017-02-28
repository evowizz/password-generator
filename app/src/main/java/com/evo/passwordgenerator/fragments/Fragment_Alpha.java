package com.evo.passwordgenerator.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ali.androidutility.helper.SharedPreferenceHelper;
import com.evo.passwordgenerator.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class Fragment_Alpha extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private FloatingActionButton generateButton;
    private Button clipbutton;
    private Button clearbutton;
    private int  number=0;
    private TextView generatedPassword;
    private String passwToSave;
    private DiscreteSeekBar charNumber;

    public Fragment_Alpha() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base2, container, false);
        final View clayout = view.findViewById(R.id.clayout);

        generatedPassword = (EditText) view.findViewById(R.id.generatedPassword);
        generatedPassword.setMovementMethod(new ScrollingMovementMethod());
        generateButton = (FloatingActionButton) view.findViewById(R.id.generateButton);
        clipbutton = (Button) view.findViewById(R.id.clipboard_button);
        clearbutton = (Button) view.findViewById(R.id.clear_button);
        charNumber = (DiscreteSeekBar) view.findViewById(R.id.charNumber);
        context = getActivity().getApplicationContext();

        generatedPassword.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    number = charNumber.getProgress();
                    Pass_Alpha pword = new Pass_Alpha(number);
                    passwToSave = pword.randomChar(context);
                    generatedPassword.setText(passwToSave);
                    number = 0;


            }
        });


        clipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(generatedPassword.getText().length() > 0){
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", generatedPassword.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Snackbar snackbarCB = Snackbar.make(clayout, getString(R.string.clipboard), Snackbar.LENGTH_LONG)
                            .setAction(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                    snackbarCB.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarCB.show();
                } else {
                    Snackbar snackbarCBf = Snackbar.make(clayout, getString(R.string.clipboard_empty), Snackbar.LENGTH_LONG)
                            .setAction(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                    snackbarCBf.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarCBf.show();
                }
            }
        });

        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (charNumber.getProgress() > 0 || generatedPassword.getText().length() > 0) {
                    charNumber.setProgress(1);
                    generatedPassword.setText("");
                    hide();
                } else {
                    Snackbar snackbarCB = Snackbar.make(clayout, getString(R.string.valuesnackbarcb), Snackbar.LENGTH_LONG)
                            .setAction(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                    snackbarCB.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarCB.show();

                }
            }
        });

        if (SharedPreferenceHelper.getInt(context, "found", 1) == 1) {
            generateButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    generatedPassword.setText("H3LL0_W0RLD");
                    SharedPreferenceHelper.setInt(context, "found", 2);
                    return false;
                }
            });
        }

        return view;

    }

    private void hide() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        generatedPassword.clearFocus();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
