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
    
    public void drawKineticPattern(){
        // Keep as Declarative as possible
    }
    
    public String currentRadioButton(){
        //some sort of windowListener
    }
    
    public Color drawingColor(){
        return Color.black;   
    }    
    
    public int drawingDelay(){
        return 1;
    }
    
    public int historyCount(){
        return 1;
    }
    
    public void drawLine(int startX, int startY, int endX, int endY){
    
    }
        
    public void drawRectangle(int x, int y, int height, int width, boolean fill){
        
    }
      
    public void drawOval(int x, int y, int height, int width, boolean fill){
        
    }
    
    public void clear(){
    }
    
}
