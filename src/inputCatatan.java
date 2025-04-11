import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Hutang{
    String namateman;
    double nominal;
    String tanggalBalik;
    Hutang(String namateman, double nominal, String tanggalBalik){
        this.namateman = namateman;
        this.nominal = nominal;
        this.tanggalBalik = tanggalBalik;
    }
}
public class inputCatatan {
    private JTextField nominal;
    private JTextField namateman;
    private JButton simpanButton;
    public JPanel mainPanel;
    private JTextField tanggalBalik;
    public JScrollPane area;
    public JTextPane tampildata;

    public inputCatatan() {
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nt = namateman.getText().trim();
                String nm = nominal.getText().trim();
                String tb = tanggalBalik.getText().trim();
                if (nt.isEmpty() || nm.isEmpty() || tb.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua form harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    Double.parseDouble(nm);
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, "Nominal harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(simpanButton,"Data Berhasil Ditambah");
                Hutang h = new Hutang(nt,Double.parseDouble(nm),tb);
                simpanKeFile(h);
                namateman.setText("");
                nominal.setText("");
                tanggalBalik.setText("");
                tampilkanData();
            }
        });
    }

    static public void simpanKeFile (Hutang hutang){
        try (FileWriter writer = new FileWriter("dataHutang.txt",true)){
            writer.write(hutang.namateman+" | "+hutang.nominal+" | "+hutang.tanggalBalik+"\n");
        } catch (IOException e){
            System.out.println("Error saat menulis file ke "+e);
        }
    }
    public void tampilkanData() {
        File file = new File("dataHutang.txt");
        if (!file.exists()) {
            tampildata.setText("Belum ada data tersimpan.");
            return;
        }
        StringBuilder isi = new StringBuilder();
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                isi.append(fileScanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            tampildata.setText("File tidak ditemukan.");
            return;
        }
        tampildata.setText(isi.toString());
    }

}
