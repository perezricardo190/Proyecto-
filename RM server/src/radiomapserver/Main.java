
package radiomapserver;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final RadioMapServer Login = new RadioMapServer();
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login.setVisible(true);
            }
        });
    }
}
