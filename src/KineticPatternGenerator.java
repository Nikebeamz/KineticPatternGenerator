import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;


public class KineticPatternGenerator {
    private JFrame frame;
    private JPanel buttonPanel, canvasPanel;
    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    private String lastClicked = "";
    private LinkedList<Shape> queue = new LinkedList<Shape>();

    KineticPatternGenerator(String name, String[] radioButtons){
        //create frame + buttons
        frame = new JFrame(name);

        /*
        	Defines a JPanel with a specialized size routine
         */

        buttonPanel = new JPanel(){
            @Override
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
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i<radioButtons.length; i++){
            JRadioButton temp = new JRadioButton(radioButtons[i]);
            bg.add(temp);
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

        bg.setSelected(bg.getElements().nextElement().getModel(), true);

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
        var kine = new KineticPatternGenerator("Tester", new String[]{"A", "B", "C"});
    }

    /*
        Specialize this method
     */
    public void drawKineticPattern(){
       //...
    }
    
    public String currentRadioButton(){
        return lastClicked;
    }
    
    public Color drawingColor(){
        return Color.black;
    }
    
    public int drawingDelay(){
        return 1000;
    }
    
    public int historyCount(){
        return 5;
    }
    
    public void drawLine(int startX, int startY, int endX, int endY){
        queue.add(new Line(startX, startY, endX, endY));
        draw();
    }

    public void drawRectangle(int x, int y, int width, int height, boolean fill){
        queue.add(new Rectangle(x,y,width,height,fill));
        draw();
    }

    public void drawOval(int x, int y, int width, int height, boolean fill){
        queue.add(new Oval(x,y,width,height,fill));
        draw();
    }

    public void clear(){
        queue.clear();
        draw();
    }

    private void draw() {
        try {Thread.sleep(drawingDelay());} catch (InterruptedException ignored) {}
        //ensure the queue is correctly sized
        if(queue.size() > historyCount() && historyCount() > 0){
            queue.remove();
        }

        Graphics2D g = image.createGraphics(); //creates a Graphics2D object from the BufferedImage
        Color defaultColor = g.getColor();
        g.fillRect(0,0,500,500); //clear the scene
        g.setColor(drawingColor());

        for (Shape shape : queue) { //for each shape in the queue
            shape.draw(g); //call the specialized draw method
        }

        g.setColor(defaultColor);
        g.dispose(); //disposes of the Graphics2D object for better performance
        canvasPanel.repaint(); //repaints the canvas
    }

    /*
        Boilerplate for adding TTL to drawn shapes
     */

    interface Shape{
        public void draw(Graphics2D g);
        public String toString();

    }

    class Oval implements Shape{

        int x, y, width, height;
        boolean fill;


        public Oval(int x, int y, int width, int height, boolean fill){
        	this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fill = fill;
        }
        public void draw(Graphics2D g){
            if(fill)
                g.fillOval(x,y,width,height);
            else
                g.drawOval(x,y,width,height);
        }

        @Override
        public String toString() {
            return "Oval{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    ", fill=" + fill +
                    '}';
        }
    }

    class Rectangle implements Shape{

        int x, y, width, height;
        boolean fill;

        public Rectangle(int x, int y, int width, int height, boolean fill){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fill = fill;
        }
        public void draw(Graphics2D g){
            if(fill)
                g.fillRect(x,y,width,height);
            else
                g.drawRect(x,y,width,height);
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    ", fill=" + fill +
                    '}';
        }
    }

    class Line implements Shape{
        int startX, startY, endX, endY;

        public Line(int startX, int startY, int endX, int endY){
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public void draw(Graphics2D g){
            g.drawLine(startX, startY, endX, endY);
        }

        @Override
        public String toString() {
            return "Line{" +
                    "startX=" + startX +
                    ", startY=" + startY +
                    ", endX=" + endX +
                    ", endY=" + endY +
                    '}';
        }
    }

}
