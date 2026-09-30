import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exercise1 {

    static JFrame frm = new JFrame("骰子模擬器");
    static JLabel lab1 = new JLabel("已擲 0 次，總和 0，平均 0.00");
    static JLabel lab2 = new JLabel("1");
    static JButton btn = new JButton("擲骰子");

    static int count = 0;
    static int sum = 0;

    public static void main(String args[]) {

        frm.setSize(400,320);
        frm.setLayout(new BorderLayout());

        lab1.setHorizontalAlignment(JLabel.CENTER);
        lab1.setFont(new Font("微軟正黑體", Font.BOLD, 18));

        lab2.setHorizontalAlignment(JLabel.CENTER);
        lab2.setFont(new Font("Arial",Font.BOLD,60));

        btn.setFont(new Font("微軟正黑體", Font.BOLD, 20));

        frm.add(lab1,BorderLayout.NORTH);
        frm.add(lab2,BorderLayout.CENTER);
        frm.add(btn,BorderLayout.SOUTH);

        btn.addActionListener(new ActLis());

        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class ActLis implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            int num = (int)(Math.random()*6)+1;

            count++;
            sum = sum + num;

            double avg = (double)sum/count;

            lab2.setText("" + num);

            if(num == 6)
                lab2.setForeground(Color.GREEN);
            else if(num == 1)
                lab2.setForeground(Color.RED);
            else
                lab2.setForeground(Color.BLACK);

            lab1.setText("已擲 " + count + " 次，總和 " + sum
                    + "，平均 " + String.format("%.2f",avg));
        }
    }
}