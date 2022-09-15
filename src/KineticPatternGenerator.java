import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class KineticPatternGenerator {
    private JFrame frame;
    private JPanel buttonPanel, canvasPanel;
    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    private String lastClicked = "";

    KineticPatternGenerator(String name, String[] radioButtons){
        //create frame + buttons
        frame = new JFrame(name);

        /*
        	Defines a JPanel with a specialized size routine
         */

        buttonPanel = new JPanel(){@Override
            public Dimension getPreferredSize() {
                return new Dimension(500,100);
            }
        };


        /*
        	Defines a JPanel with specialized size and paint routines
         */
        canvasPanel = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(500,500);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        /*
            Adds the buttons to the frame
         */
        for(int i = 0; i<radioButtons.length; i++){
            JRadioButton temp = new JRadioButton(radioButtons[i]);
            temp.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(((JRadioButton)e.getSource()).isSelected()) //casts Object to RadioButton and checks state
                        lastClicked = e.getActionCommand();
                }
            }
            );
            buttonPanel.add(temp);
        }

        /*
            Gets the panels to line up justified vertically
         */
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        frame.add(buttonPanel, constraints);
        constraints.gridy = 1;
        frame.add(canvasPanel, constraints);

        /*
        	Packs and spawns the frame
         */
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        drawKineticPattern();
    }
    
    public static void main(String[] args){
        new KineticPatternGenerator("Test  test test", new String[]{"t1", "t2",  "t3", "t5", "t6"});
    }


    /*
        Specialize this method
     */
    public void drawKineticPattern(){
        drawOval(50,50,400,400, true);
    }
    
    public String currentRadioButton(){
        return lastClicked;
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
        Graphics2D g = image.createGraphics();
        g.setColor(drawingColor());
        g.drawLine(startX, startY, endX, endY);
        g.dispose();
    }

    public void drawRectangle(int x, int y, int width, int height, boolean fill){
        Graphics2D g = image.createGraphics();
        g.setColor(drawingColor());
        if(fill)
            g.fillRect(x,y,width,height);
        else
            g.drawRect(x,y,width,height);
        g.dispose();
    }

    public void drawOval(int x, int y, int width, int height, boolean fill){
        Graphics2D g = image.createGraphics(); //creates a Graphics2D object from the BufferedImage
        g.setColor(drawingColor()); //preemtively sets the drawing color

        if(fill)
            g.fillOval(x,y,width,height);
        else
            g.drawOval(x,y,width,height);

        g.dispose(); //disposes of the Graphics2D object for better performance
    }
    
    public void clear(){
    }
    
}
