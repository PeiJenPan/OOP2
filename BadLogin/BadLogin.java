import javax.swing.*;

public class BadLogin extends JFrame {

    public BadLogin() {

        setTitle("登入");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 帳號標籤與輸入框
        JLabel l1 = new JLabel("帳號：");
        l1.setBounds(30, 25, 60, 25);

        JTextField t1 = new JTextField();
        t1.setBounds(90, 25, 150, 25);

        // 密碼標籤與輸入框
        JLabel l2 = new JLabel("密碼：");
        l2.setBounds(30, 65, 60, 25);

        JPasswordField t2 = new JPasswordField();
        t2.setBounds(90, 65, 150, 25);

        // 登入按鈕
        JButton btn = new JButton("登入");
        btn.setBounds(100, 110, 80, 30);

        // 將元件加入視窗
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(btn);

        // 點擊按鈕後，檢查帳號與密碼
        btn.addActionListener(e -> {

            String account = t1.getText();
            String password = new String(t2.getPassword());

            if (account.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(
                    this, "登入成功"
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, "帳號或密碼錯誤"
                );
            }
        });

        // 按 Enter 也可以登入
        getRootPane().setDefaultButton(btn);

        // 元件設定完成後，再顯示視窗
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new BadLogin();
        });
    }
}