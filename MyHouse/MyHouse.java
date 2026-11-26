import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyHouse extends JPanel {

    public MyHouse() {
        setPreferredSize(new Dimension(500, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 天空與草地
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 500, 320);
        g.setColor(new Color(76, 163, 70));
        g.fillRect(0, 320, 500, 80);

        // 太陽與八條光芒
        int sunX = 420;
        int sunY = 75;
        g.setColor(Color.YELLOW);
        g.fillOval(sunX - 40, sunY - 40, 80, 80);

        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            int x1 = sunX + (int) Math.round(50 * Math.cos(angle));
            int y1 = sunY + (int) Math.round(50 * Math.sin(angle));
            int x2 = sunX + (int) Math.round(70 * Math.cos(angle));
            int y2 = sunY + (int) Math.round(70 * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // 三個重疊的白色橢圓
        g.setColor(Color.WHITE);
        g.fillOval(60, 54, 75, 52);
        g.fillOval(100, 38, 82, 64);
        g.fillOval(145, 54, 75, 52);

        // 房屋主體
        g.setColor(new Color(255, 226, 177));
        g.fillRect(150, 192, 200, 160);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(150, 192, 200, 160);

        // 用三條線畫屋頂
        g.setColor(new Color(175, 78, 65));
        g.drawLine(130, 192, 250, 96);
        g.drawLine(250, 96, 370, 192);
        g.drawLine(370, 192, 130, 192);

        // 圓角門與門把
        g.setColor(new Color(135, 72, 29));
        g.fillRoundRect(225, 266, 50, 86, 18, 18);
        g.setColor(Color.YELLOW);
        g.fillOval(263, 307, 8, 8);

        // 兩扇十字窗
        drawWindow(g, 170, 224);
        drawWindow(g, 286, 224);

        // 左下角簽名
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        g.drawString("A1133377 潘佩貞", 15, 382);
    }

    private void drawWindow(Graphics g, int x, int y) {
        int size = 44;
        int half = size / 2;

        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, size, size);
        g.drawLine(x + half, y, x + half, y + size);
        g.drawLine(x, y + half, x + size, y + half);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("我的小房子");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new MyHouse());
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}