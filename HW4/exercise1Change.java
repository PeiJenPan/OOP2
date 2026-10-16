package HW4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exercise1Change {

    static JFrame frm = new JFrame("骰子模擬器");
    static JLabel lab1 = new JLabel("已擲 0 次，總和 0，平均 0.00");
    static JLabel lab2 = new JLabel("  ");
    static JButton btn = new JButton("擲骰子");

    static int count = 0;
    static int sum = 0;

    public static void main(String args[]) {

        frm.setSize(400, 320);
        frm.setLayout(new BorderLayout());

        // 上方顯示擲骰次數、總和、平均
        lab1.setHorizontalAlignment(JLabel.CENTER);
        lab1.setFont(new Font("微軟正黑體", Font.BOLD, 18));

        // 中間顯示骰子點數
        lab2.setHorizontalAlignment(JLabel.CENTER);
        lab2.setFont(new Font("Arial", Font.BOLD, 60));

        // 下方按鈕
        btn.setFont(new Font("微軟正黑體", Font.BOLD, 20));

        frm.add(lab1, BorderLayout.NORTH);
        frm.add(lab2, BorderLayout.CENTER);
        frm.add(btn, BorderLayout.SOUTH);

        // 加入事件監聽器
        btn.addActionListener(new ActLis());

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

    // 事件監聽器
    static class ActLis implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            // 隨機產生 1~6 點
            int num = (int)(Math.random() * 6) + 1;

            // 計算次數、總和、平均
            count++;
            sum = sum + num;

            double avg = (double)sum / count;

            // 更新骰子點數
            lab2.setText("" + num);

            // 根據點數改變顏色
            if (num == 6) {
                lab2.setForeground(Color.GREEN);
            } else if (num == 1) {
                lab2.setForeground(Color.RED);
            } else {
                lab2.setForeground(Color.BLACK);
            }

            // 更新上方統計資料
            lab1.setText("已擲 " + count + " 次，總和 " + sum
                    + "，平均 " + String.format("%.2f", avg));
        }
    }
}
