import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameSettings extends JPanel implements Runnable, KeyListener
{
    //player size and starting position
    private int startingX = 550;
    private int startingY = 350;
    private int playerSize = 16;
    private int scale = 3;
    private int playerSpeed = 6;

    //screen size
    private int tileSize = playerSize * scale;
    private int maxScreenCol = 24;
    private int maxScreenRow = 16;
    private int screenWidth = maxScreenCol * tileSize;
    private int screenHeight = maxScreenRow * tileSize;

    private Timer timer;
    private boolean[] keys;

    private Rectangle player;

    public GameSettings()
    {
        this.setFocusable(Boolean.TRUE);
        this.addKeyListener(this);
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        timer = new Timer(10, e -> run());
        timer.start();
        keys = new boolean[256];


    }

    /**
     * The main Game loop
     */
    public void run()
    {
        update();

        repaint();
    }

    public void update()
    {
        if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP])
        {
            startingY -= playerSpeed;
        }
        if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN])
        {
            startingY += playerSpeed;
        }
        if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT])
        {
            startingX -= playerSpeed;
        }
        if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT])
        {
            startingX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g)
    {
        player = new Rectangle(startingX,startingY,tileSize,tileSize);

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fill(player);



    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent)
    {
        keys[keyEvent.getKeyCode()] = false;
    }
}
