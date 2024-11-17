import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.util.Scanner;

public class Repl {
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Repl.class.getName());
    boolean loop = true;

    logger.log(Level.INFO,
      "\n5A Conti Mattia 18/11/2024\n"
      );
    Scanner scanner = new Scanner(System.in);
    while (loop) {
      logger.log(Level.INFO,
      "\n1) Generate RSA keys (public + private);" +
      "\n2) Cript a text;" +
      "\n3) Decript a text;" +
      "\n4) Exit;" +
      "\nSelect what to do:"
      );
      
      String option;
      while (true) {
        option = scanner.nextLine();
        if (LibRSA.isInt(option)) {
          if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= 4) {
            break;
          } else {
            logger.log(Level.INFO,"Insert a valueble option (1 and 4)");
          }
        } else {
          logger.log(Level.INFO,"Please insert a number");
        }
      }

      int selectedOption = Integer.parseInt(option);
      
      BigInteger n;
      BigInteger e;
      BigInteger d;
      String givenN;
      String givenE;
      String givenD;

      switch(selectedOption) {
        case 1:
          BigInteger[] keys = LibRSA.keyNumbersGenerator();
          n = keys[0];
          e = keys[1];
          d = keys[2];
          logger.log(Level.INFO,
          "\nPublic Key: (n: "+ n + " , e: " + e + ")" +
          "\nPrivare Key: (n: "+ n + " , d: " + d + ")"
          );
        break;
        case 2:
          logger.log(Level.INFO,"Insert the message:");
          String encriptedMessage = scanner.nextLine();
          while (true) {
            logger.log(Level.INFO,"Insert n");
            givenN = scanner.next();
            if (LibRSA.isInt(option)) {
              break;
            } else {
              logger.log(Level.INFO,"Please insert a number");
            }
          }
          while (true) {
            logger.log(Level.INFO,"Insert e or d");
            givenE = scanner.next();
            if (LibRSA.isInt(option)) {
              break;
            } else {
              logger.log(Level.INFO,"Please insert a number");
            }
          }
          logger.log(Level.INFO, "" +
          "\nEncripted message: " + encriptedMessage +
          "\nCripted message: " + LibRSA.textEncript(encriptedMessage, BigInteger.valueOf(Integer.parseInt(givenN)), BigInteger.valueOf(Integer.parseInt(givenE)))
          );
        break;
        case 3:
          logger.log(Level.INFO,"Insert the message:");
          String criptedMessage = scanner.nextLine();
          while (true) {
            logger.log(Level.INFO,"Insert n");
            givenN = scanner.next();
            if (LibRSA.isInt(option)) {
              break;
            } else {
              logger.log(Level.INFO,"Please insert a number");
            }
          }
          while (true) {
            logger.log(Level.INFO,"Insert e or d");
            givenD = scanner.next();
            if (LibRSA.isInt(option)) {
              break;
            } else {
              logger.log(Level.INFO,"Please insert a number");
            }
          }
          logger.log(Level.INFO, "" +
          "\nCripted message: " + criptedMessage +
          "\nEncripted message: " + LibRSA.textDecript(criptedMessage, BigInteger.valueOf(Integer.parseInt(givenN)), BigInteger.valueOf(Integer.parseInt(givenD))));
        break;
        case 4:
          loop = false;
        break;
      }
    }
    scanner.close();
  }    
}