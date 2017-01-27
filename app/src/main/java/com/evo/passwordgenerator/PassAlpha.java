package com.evo.passwordgenerator;

import android.content.Context;

class PassAlpha {

    private int charNum;
    private String generatedPasswordChar;
    private String generatedPassword;

    private String[] passwordChar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"};

    public PassAlpha(int charNum) {
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

