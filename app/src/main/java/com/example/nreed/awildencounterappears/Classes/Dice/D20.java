package com.example.nreed.awildencounterappears.Classes.Dice;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nreed on 7/6/2017.
 */

public class D20 {
    static int minNumber = 1;
    static int maxNumber = 20;

    public static int RollD20(int numberOfD20){
        if(numberOfD20 == 0) numberOfD20 = 1;
        int result = 0;
        for(int i = numberOfD20; i !=0; i--) {
            result += ThreadLocalRandom.current().nextInt(minNumber, maxNumber + 1);// +1 so that it is inclusive of the maxNumber
        }
        return result;
    }
}
