/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kebudayaan;
import kebudayaan.dbCRUD;
import gui.Home;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class KEBUDAYAAN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        dbCRUD myObject = new dbCRUD();
        new Home().setVisible(true);

        String field[] = {"id_kebudayaan", "nama_kebudayaan", "deskripsi"};
        String isiField[] = {"1", "Karapan Sapi", "Karapan sapi adalah salah satu upacara adat yang dilakukan masyarakat Madura secara turun-temurun.Upacara ini dilakukan dalam bentuk perlombaan pacuan sapi yang dilakukan pada sebuah pesta rakyat yang dilakukan secara turun-temurun."};
        
        if (myObject.DuplicateKey("kebudayaan", "id_kebudayaan", "1")){
            JOptionPane.showMessageDialog(null, "PK Sudah ada");
        }else{
            myObject.SimpanDinamis("dvd", field, isiField);
        }
    }
    
}
