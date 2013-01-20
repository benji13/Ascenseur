package ascenseur;

//Source du code "SplashDemo" : http://docs.oracle.com/javase/tutorial/uiswing/misc/splashscreen.html


import java.awt.*;
import java.awt.event.*;
 
public class SplashInitial extends Frame{
    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"Chargement" , "Patientez", "M²B²T"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString(comps[(frame/5)%3]+"...", 120, 150);
    }
    public SplashInitial() {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<100; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(40);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
    }	
}
