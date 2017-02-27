package com.evo.passwordgenerator.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.evo.passwordgenerator.R;
import com.evo.passwordgenerator.activities.SaveActivity;
import com.evo.passwordgenerator.data.PasswordContract;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class Fragment_Num extends Fragment {

    private Context context;
    private EditText charNumber;
    private FloatingActionButton generateButton;
    private Button clipbutton;
    private Button clearbutton;
    private Button savebutton;
    private int  number=0;
    private TextView generatedPassword;
    private String passwToSave;

    public Fragment_Num() {
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
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        final View clayout = view.findViewById(R.id.clayout);

        charNumber = (EditText) view.findViewById(R.id.charNumber);
        generatedPassword = (EditText) view.findViewById(R.id.generatedPassword);
        generatedPassword.setMovementMethod(new ScrollingMovementMethod());
        generateButton = (FloatingActionButton) view.findViewById(R.id.generateButton);
        clipbutton = (Button) view.findViewById(R.id.clipboard_button);
        clearbutton = (Button) view.findViewById(R.id.clear_button);
        savebutton = (Button) view.findViewById(R.id.save_button);
        context = getActivity().getApplicationContext();

        generatedPassword.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (charNumber.getText().length() > 0) {
                    number = Integer.parseInt(charNumber.getText().toString());
                    Pass_Num pword = new Pass_Num(number);
                    passwToSave = pword.randomChar(context);
                    generatedPassword.setText(passwToSave);
                    number = 0;

                } else {
                    Snackbar snackbarNC = Snackbar.make(clayout, getString(R.string.valuesnackbarnc), Snackbar.LENGTH_LONG)
                            .setAction(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                    snackbarNC.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarNC.show();

                }
            }
        });


        clipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (generatedPassword.getText().length() > 0) {
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
                if (charNumber.getText().length() > 0 || generatedPassword.getText().length() > 0) {
                    charNumber.setText("");
                    generatedPassword.setText("");

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

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwToSave.isEmpty()) {

                    ContentValues values = new ContentValues();

                    values.put(PasswordContract.PasswordEntry.COLUMN_PASSWORD, passwToSave);
                    values.put(PasswordContract.PasswordEntry.COLUMN_TIME, System.currentTimeMillis());

                    getActivity().getContentResolver().insert(PasswordContract.PasswordEntry.CONTENT_URI, values);

                    // TODO: Change text here to what you want
                    Snackbar snackbarSave = Snackbar.make(clayout, "Password saved!", Snackbar.LENGTH_LONG)
                            .setAction("Saved", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(getActivity().getApplicationContext(), SaveActivity.class);
                                    startActivity(i);
                                }
                            });
                    snackbarSave.setActionTextColor(getResources().getColor(R.color.colorAccent));
                    snackbarSave.show();
                }
            }
        });

        clayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                charNumber.clearFocus();
                generatedPassword.clearFocus();
            }
        });


        return view;

    }

}
