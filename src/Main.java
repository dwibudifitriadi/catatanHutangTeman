import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        inputCatatan ict = new inputCatatan();
        JFrame frame = new JFrame();
        frame.setTitle("Catatan Hutang Teman");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(ict.mainPanel);
        ict.area.setViewportView(ict.tampildata);
        frame.setVisible(true);
        ict.tampilkanData();
    }
}
