import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame {

    private JTextField decimalInput;
    private JTextField binaryOutput;
    private JTextField hexOutput;
    private JButton convertButton;
    private JButton convert2Button;
    private JButton convert3Button;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        decimalInput = new JTextField(10);
        binaryOutput = new JTextField(20);
        hexOutput = new JTextField(25);
        convertButton = new JButton("Decimal a: ");
        convert2Button = new JButton("Maquina a: ");
        convert3Button = new JButton("Binario a: ");

        add(new JLabel("Decimal:"));
        add(decimalInput);
        add(new JLabel("Binario:"));
        add(binaryOutput);
        add(new JLabel("Hexadecimal (Little Endian):"));
        add(hexOutput);
        add(convertButton);
        add(convert2Button);
        add(convert3Button);


        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        int decimal = Integer.parseInt(decimalInput.getText());
                        String binario = Integer.toBinaryString(decimal);
                        binaryOutput.setText(binario);
                        String hexadecimal = Integer.toHexString(decimal);
                        String littleEndianHex = toLittleEndian(hexadecimal);
                        hexOutput.setText(littleEndianHex);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido");
                    }
            }
        });
        convert2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String maquina = hexOutput.getText();
                    String hex = toBigEndian(maquina);
                    int decimal = Integer.parseInt(hex, 16);
                    decimalInput.setText(String.valueOf(decimal));
                    String binario = Integer.toBinaryString(decimal);
                    binaryOutput.setText(binario);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido");
                }
            }
        });
        convert3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String binario = binaryOutput.getText();
                    int decimal = Integer.parseInt(binario,2);
                    decimalInput.setText(String.valueOf(decimal));
                    String hexa = Integer.toHexString(decimal);
                    String littleEndian = toLittleEndian(hexa);
                    hexOutput.setText(littleEndian);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido");
                }
            }
        });
    }

    private String toLittleEndian(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        StringBuilder littleEndian = new StringBuilder();
        for (int i = hex.length() - 2; i >= 0; i -= 2) {
            littleEndian.append(hex, i, i + 2);
        }
        return littleEndian.toString();
    }

    private String toBigEndian(String little) {
        StringBuilder bigEndian = new StringBuilder();
        for(int i=0; i<little.length(); i+=2) {
            bigEndian.insert(0, little.substring(i,i+2));
        }
        return bigEndian.toString();
    }

    public static void main(String[] args) {
        Calculadora ventana = new Calculadora();
        ventana.setVisible(true);
    }
}

