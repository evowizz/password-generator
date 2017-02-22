package com.evo.passwordgenerator.fragments;

import android.content.Context;

import com.evo.passwordgenerator.R;

class Pass_Num {

    private int charNum;
    private String generatedPasswordChar;
    private String generatedPassword;

    private String[] passwordChar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",};

    public Pass_Num(int charNum) {
        this.charNum = charNum;

    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }


    public String randomChar(Context context) {

        if (charNum < 1) {
            return context.getResources().getString(R.string.value_char_limit_text);

        } if (charNum > 100) {
            return context.getResources().getString(R.string.value_char_limit_text);

        }

        else {
            generatedPassword= "";
            for (int i = 0; i < charNum; i++) {
                int randomC = (int) (Math.random() * passwordChar.length-1) + 1;
                generatedPasswordChar = passwordChar[randomC];
                generatedPassword+=generatedPasswordChar;

            }
            return generatedPassword;
        }
    }

}

