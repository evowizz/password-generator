package com.evo.passwordgenerator;

class Passw {

    private int charNum;
    private String generatedPasswordChar;
    private String generatedPassword;

    private String[] passwordChar = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"};

    public Passw(int charNum) {
        this.charNum = charNum;

    }

    public String getGeneratedPassword() {
        return generatedPassword;
    }


    public String randomChar() {

        if (charNum < 1) {
            return "Please, use a value between 1 and 100";

        } if (charNum > 100) {
            return "Please, use a value between 1 and 100";

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

