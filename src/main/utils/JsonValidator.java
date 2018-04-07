package main.utils;

import com.google.gson.Gson;

public class JsonValidator implements Validator {

    private String inputString;

    private Gson gson = new Gson();

    public JsonValidator(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public boolean validate() {


        return false;
    }
}
