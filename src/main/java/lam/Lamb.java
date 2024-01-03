package lam;

import org.javakotlin.lambdas.OnOneListener;

public class Lamb {
  public static void me() {
    OnOneListener oneListener = (String message) -> {
      System.out.println("Sin Lambda con  " + message);
    };

    OnOneListener oneListener2 = (String message) -> {
      System.out.println("Con lambda con " + message);
    };
    oneListener.onOne(" Java ");
    oneListener2.onOne(" Java \n");
  }
}
