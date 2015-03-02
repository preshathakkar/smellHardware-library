/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smellInterface;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sanselan.ImageReadException;
import smcImage.SmcImage;
import smell.SmellAccessor;

/**
 *
 * @author presha
 */
public class SmellInterface {
String smell;
    public static BufferedReader input;
public static OutputStream output;
public static synchronized void writeData(String data) {
System.out.println("Sent: " + data);
try {
output.write(data.getBytes());
} catch (Exception e) {
System.out.println("could not write to port");
}
}
    /**
     * @param args the command line arguments
     */
    public void openWithSmell(File SmcFile) {
        
        try
{
            // TODO code application logic here
            SmellAccessor sa = new SmellAccessor();
            String SmcId = SmcImage.getSmellFromSmc(SmcFile);
            Integer SmcPin =  sa.getPinBySmellId(SmcId);
            smell = SmcPin.toString();
            try
    {
    SerialClass obj = new SerialClass();
    int c=0;
    obj.initialize();
    input = SerialClass.input;
    output = SerialClass.output;
    InputStreamReader Ir = new InputStreamReader(System.in);
    BufferedReader Br = new BufferedReader(Ir);

    Thread t=new Thread() {
    public void run() {

    writeData(smell);} 
    //catch (InterruptedException ie){}
    };

    t.start();
    System.out.println("Started");

    obj.close();
    }
    catch(Exception e){}
    }
catch(ImageReadException ex){Logger.getLogger(SmellInterface.class.getName()).log(Level.SEVERE, null, ex);
}       catch (IOException ex) {
            Logger.getLogger(SmellInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
