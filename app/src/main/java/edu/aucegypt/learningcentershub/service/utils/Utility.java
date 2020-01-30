package edu.aucegypt.learningcentershub.service.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utility {

    public static String formatdouble(double d)
    {
        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(d);
    }
}
