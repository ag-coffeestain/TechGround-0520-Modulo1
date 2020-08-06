package utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class DataUtils {

    public static String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return String.format("%s@testemail.com", saltStr);
    }

    public static String getAmountFromCurrency(String text){
          return text.replace("€","")
                  .replace("$", "")
                  .replace("L","");
    }

    @Test
    public void getDolar(){

        Assert.assertEquals(getAmountFromCurrency("$231.23"), "231.23");

    }

    @Test
    public void getEuro(){

        Assert.assertEquals(getAmountFromCurrency("231.23€"), "231.23");

    }

    @Test
    public void testRandome(){

        System.out.println(getRandomEmail());

    }
}
