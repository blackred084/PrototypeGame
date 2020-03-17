package first;

import javax.swing.JFrame;

public class Window extends JFrame{
    public Window(){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1290,720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
