import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class exercise2 {

    static JFrame frm = new JFrame("單位換算器");

    static String[] types = {"長度", "重量", "溫度"};
    static String[] length = {"公尺", "公分", "英吋", "英尺"};
    static String[] weight = {"公斤", "公克", "磅", "盎司"};
    static String[] temp = {"攝氏", "華氏", "克氏"};

    static JComboBox<String> typeBox = new JComboBox<>(types);
    static JComboBox<String> fromBox = new JComboBox<>(length);
    static JComboBox<String> toBox = new JComboBox<>(length);

    static JTextField txt1 = new JTextField(10);
    static JTextField txt2 = new JTextField(10);

    static JButton btn = new JButton("換算");

    public static void main(String[] args) {

        Color purple = new Color(245, 240, 255);

        frm.setSize(480, 280);
        frm.setLayout(new BorderLayout());

        // NORTH：選擇換算類型
        JPanel p1 = new JPanel();
        p1.setBackground(purple);
        p1.add(new JLabel("換算類型："));
        p1.add(typeBox);
        frm.add(p1, BorderLayout.NORTH);

        // CENTER：輸入數值及選擇單位
        JPanel p2 = new JPanel(new GridLayout(2, 2, 10, 20));
        p2.setBackground(purple);

        JPanel a = new JPanel();
        a.setBackground(purple);
        a.add(new JLabel("來源數值"));
        a.add(txt1);

        JPanel b = new JPanel();
        b.setBackground(purple);
        b.add(new JLabel("換算結果"));
        b.add(txt2);

        JPanel c = new JPanel();
        c.setBackground(purple);
        c.add(fromBox);

        JPanel d = new JPanel();
        d.setBackground(purple);
        d.add(toBox);

        p2.add(a);
        p2.add(b);
        p2.add(c);
        p2.add(d);

        frm.add(p2, BorderLayout.CENTER);

        txt2.setEditable(false);

        // SOUTH：換算按鈕
        JPanel p3 = new JPanel();
        p3.setBackground(purple);

        btn.setBackground(new Color(225, 210, 245));
        btn.setForeground(new Color(70, 40, 100));
        btn.setFocusPainted(false);

        p3.add(btn);
        frm.add(p3, BorderLayout.SOUTH);

        // 切換換算類型
        typeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fromBox.removeAllItems();
                toBox.removeAllItems();

                String[] units;

                if (typeBox.getSelectedIndex() == 0) {
                    units = length;
                } else if (typeBox.getSelectedIndex() == 1) {
                    units = weight;
                } else {
                    units = temp;
                }

                for (int i = 0; i < units.length; i++) {
                    fromBox.addItem(units[i]);
                    toBox.addItem(units[i]);
                }

                txt1.setText("");
                txt2.setText("");
            }
        });

        // 切換來源單位時清除結果
        fromBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt2.setText("");
            }
        });

        // 切換目標單位時清除結果
        toBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txt2.setText("");
            }
        });

        // 按下換算按鈕
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }

    // 單位換算方法
    public static void convert() {

        try {
            double num = Double.parseDouble(txt1.getText());

            // 檢查特殊數值
            if (!Double.isFinite(num)) {
                JOptionPane.showMessageDialog(frm, "請輸入有效的數字！");
                txt2.setText("");
                return;
            }

            int type = typeBox.getSelectedIndex();
            int from = fromBox.getSelectedIndex();
            int to = toBox.getSelectedIndex();

            double result = 0;
            double base = 0;

            // 長度及重量不能為負數
            if ((type == 0 || type == 1) && num < 0) {
                JOptionPane.showMessageDialog(frm, "長度或重量不能是負數！");
                txt2.setText("");
                return;
            }

            if (type == 0) {

                // 長度換算
                double[] v = {1, 0.01, 0.0254, 0.3048};

                base = num * v[from];
                result = base / v[to];

            } else if (type == 1) {

                // 重量換算
                double[] v = {1, 0.001, 0.45359237, 0.028349523125};

                base = num * v[from];
                result = base / v[to];

            } else {

                // 溫度換算：先轉成攝氏
                if (from == 0) {
                    base = num;
                } else if (from == 1) {
                    base = (num - 32) * 5 / 9;
                } else {
                    base = num - 273.15;
                }

                // 檢查絕對零度
                if (base < -273.15) {
                    JOptionPane.showMessageDialog(
                        frm, "溫度不能低於絕對零度！");
                    txt2.setText("");
                    return;
                }

                // 再轉成目標溫度
                if (to == 0) {
                    result = base;
                } else if (to == 1) {
                    result = base * 9 / 5 + 32;
                } else {
                    result = base + 273.15;
                }
            }

            // 避免換算結果超出 double 範圍
            if (!Double.isFinite(result)) {
                JOptionPane.showMessageDialog(frm, "換算結果超出範圍！");
                txt2.setText("");
                return;
            }

            // 最多顯示四位小數
            DecimalFormat df = new DecimalFormat("0.####");
            txt2.setText(df.format(result));

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(frm, "請輸入正確的數字！");
            txt2.setText("");
        }
    }
}