/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kebudayaan;

import java.sql.Driver;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;
//Berfungsi mengambil file laporan yang dibuat dari IReport
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ACER
 */
public class dbCRUD {
    // URL koneksi ke database MySQL, termasuk host, port, dan nama database
    private String jdbcURL = "jdbc:mysql://localhost/2210010282_siti_nurhaliza"; 
    // Pastikan untuk mengganti '2210010282_siti_nurhaliza' dengan nama database yang sesuai

    // Username untuk autentikasi ke database MySQL
    private String username = "root"; 
    // Ganti dengan username MySQL Anda jika berbeda

    // Password untuk autentikasi ke database MySQL
    private String password = ""; 
    // Ganti dengan password MySQL Anda, jika ada

    // Objek DefaultTableModel digunakan untuk mengelola data tabel dalam GUI
    private DefaultTableModel Modelnya; 

    // Objek TableColumn digunakan untuk mengatur properti kolom dalam tabel, seperti lebar kolom
    private TableColumn Kolomnya; 

    
    // Metode untuk mendapatkan koneksi ke database
public Connection getKoneksiDB() throws SQLException {
    try {
        // Register driver MySQL untuk koneksi database
        Driver mysqldriver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(mysqldriver);
        System.out.println("Koneksi DB Berhasil");
    } catch (SQLException e) {
        // Menangkap dan mencetak pesan kesalahan jika koneksi gagal
        System.out.println(e.toString());
    }
    // Mengembalikan objek koneksi ke database
    return DriverManager.getConnection(jdbcURL, username, password);
}

// Mengecek apakah ada duplikasi kunci dalam tabel tertentu
public boolean DuplicateKey(String namaTabel, String primaryKey, String isiData) {
    boolean hasil = false;
    int jumlah = 0;
    String SQL = "SELECT * FROM " + namaTabel + " WHERE " + primaryKey + " = ?";

    try (Connection conn = getKoneksiDB();
         PreparedStatement stmt = conn.prepareStatement(SQL)) {
        // Mengatur nilai parameter untuk SQL
        stmt.setString(1, isiData);
        ResultSet hasilData = stmt.executeQuery();

        // Menghitung jumlah baris hasil query
        while (hasilData.next()) {
            jumlah++;
        }
        hasil = jumlah > 0; // Jika ada duplikasi, hasil = true
        System.out.println("Jumlah duplikasi: " + jumlah);
    } catch (SQLException e) {
        // Menangkap dan mencetak pesan kesalahan jika ada masalah
        System.err.println("Error checking duplicate key: " + e.getMessage());
    }
    return hasil;
}

// Membuat string daftar field untuk query SQL
public String getFieldTabel(String[] FieldTabelnya) {
    StringBuilder hasilnya = new StringBuilder("(");

    for (int i = 0; i < FieldTabelnya.length; i++) {
        hasilnya.append(FieldTabelnya[i]);
        if (i < FieldTabelnya.length - 1) {
            hasilnya.append(", ");
        }
    }

    hasilnya.append(")");
    return hasilnya.toString();
}

// Membuat string nilai untuk query SQL
public String getIsiTabel(String[] IsiTabelnya) {
    StringBuilder hasilnya = new StringBuilder();

    for (int i = 0; i < IsiTabelnya.length; i++) {
        hasilnya.append("'").append(IsiTabelnya[i]).append("'");
        if (i < IsiTabelnya.length - 1) {
            hasilnya.append(", ");
        }
    }

    return "(" + hasilnya + ")";
}

// Menyimpan data ke dalam tabel secara dinamis
public void SimpanDinamis(String NamaTabel, String[] Fieldnya, String[] Isinya) {
    String SQLSave = "INSERT INTO " + NamaTabel + " " + getFieldTabel(Fieldnya) +
                     " VALUES " + getIsiTabel(Isinya);

    try (Connection conn = getKoneksiDB();
         Statement perintah = conn.createStatement()) {
        // Menjalankan query untuk menyimpan data
        perintah.executeUpdate(SQLSave);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
    } catch (SQLException e) {
        // Menampilkan pesan kesalahan jika gagal
        System.err.println("Error saving data: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
    }
}

// Membuat string untuk mengedit field dalam tabel secara dinamis
public String getFieldValueEdit(String[] Field, String[] value) {
    StringBuilder hasil = new StringBuilder();

    for (int i = 0; i < Field.length; i++) {
        hasil.append(Field[i]).append(" = '").append(value[i]).append("'");
        if (i < Field.length - 1) {
            hasil.append(", ");
        }
    }
    return hasil.toString();
}

// Mengubah data dalam tabel secara dinamis
public void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary, String[] Field, String[] Value) {
    String SQLUbah = "UPDATE " + NamaTabel + " SET " + getFieldValueEdit(Field, Value) +
                     " WHERE " + PrimaryKey + " = ?";

    try (Connection conn = getKoneksiDB();
         PreparedStatement stmt = conn.prepareStatement(SQLUbah)) {
        stmt.setString(1, IsiPrimary);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
    } catch (SQLException e) {
        // Menampilkan pesan kesalahan jika gagal
        System.err.println("Error updating data: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
    }
}

// Menghapus data dalam tabel secara dinamis
public void HapusDinamis(String NamaTabel, String PK, String isi) {
    String SQL = "DELETE FROM " + NamaTabel + " WHERE " + PK + " = ?";

    try (Connection conn = getKoneksiDB();
         PreparedStatement stmt = conn.prepareStatement(SQL)) {
        stmt.setString(1, isi);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    } catch (SQLException e) {
        // Menampilkan pesan kesalahan jika gagal
        System.err.println("Error deleting data: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus: " + e.getMessage());
    }
}

// Mengisi data ke dalam tabel untuk ditampilkan
public Object[][] isiTabel(String SQL, int jumlah) {
    Object[][] data = null;
    try {
        Statement perintah = getKoneksiDB().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet dataset = perintah.executeQuery(SQL);
        dataset.last();
        int baris = dataset.getRow();
        dataset.beforeFirst();
        int j = 0;

        data = new Object[baris][jumlah];
        while (dataset.next()) {
            for (int i = 0; i < jumlah; i++) {
                data[j][i] = dataset.getString(i + 1);
            }
            j++;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Debugging jika ada kesalahan
    }
    return data;
}

// Menampilkan data di tabel GUI
public void tampilTabel(JTable Tabelnya, String SQL, String[] Judul) {
    try {
        Tabelnya.setModel(new DefaultTableModel(isiTabel(SQL, Judul.length), Judul));
    } catch (Exception e) {
        System.out.println(e.toString());
    }
}

// Mengatur judul kolom tabel
public void settingJudulTabel(JTable Tabelnya, String[] JudulKolom) {
    try {
        DefaultTableModel Modelnya = new DefaultTableModel();
        Tabelnya.setModel(Modelnya);
        for (String judul : JudulKolom) {
            Modelnya.addColumn(judul);
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
}

// Mengatur lebar kolom tabel
public void settingLebarKolom(JTable Tabelnya, int[] Kolom) {
    try {
        Tabelnya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < Kolom.length; i++) {
            TableColumn Kolomnya = Tabelnya.getColumnModel().getColumn(i);
            Kolomnya.setPreferredWidth(Kolom[i]);
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
}

// Menampilkan laporan menggunakan JasperReports
public void tampilLaporan(String laporanFile, String SQL) throws SQLException {
    try {
        File file = new File(laporanFile);
        JasperDesign jasDes = JRXmlLoader.load(file);

        JRDesignQuery sqlQuery = new JRDesignQuery();
        sqlQuery.setText(SQL);
        jasDes.setQuery(sqlQuery);

        JasperReport JR = JasperCompileManager.compileReport(jasDes);
        JasperPrint JP = JasperFillManager.fillReport(JR, null, getKoneksiDB());
        JasperViewer.viewReport(JP, false);
    } catch (JRException e) {
        // Menampilkan pesan kesalahan jika gagal
        JOptionPane.showMessageDialog(null, e.toString());
    }
}

    
}