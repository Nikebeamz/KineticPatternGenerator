import javax.swing.*;

public class KineticPatternGenerator {
    private JFrame frame;
    KineticPatternGenerator(String name, String[] radioButtons){
        //create frame + buttons
        frame = new JFrame(name);
        for(int i = 0; i<radioButtons.length; i++){
            frame.add(new JRadioButton(radioButtons[i]));
        }
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        new KineticPatternGenerator("Test  test test", ["t1", "t2",  "t3"]);
    }
}
