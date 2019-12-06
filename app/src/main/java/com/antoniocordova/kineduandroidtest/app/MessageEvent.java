package com.antoniocordova.kineduandroidtest.app;

public class MessageEvent {
    public class Option {
        public static final int AGE_FILTERED = 0;
    }

    private int option;
    private int ageOption = 0;


    public MessageEvent() { }

    public MessageEvent(int option) {
        this.option = option;
    }

    public MessageEvent(int option, int ageOption) {
        this.option = option;
        this.ageOption = ageOption;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getAgeOption() {
        return ageOption;
    }

    public void setAgeOption(int ageOption) {
        this.ageOption = ageOption;
    }
}
