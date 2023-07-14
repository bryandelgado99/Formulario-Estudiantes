import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

public class Fomulario extends JFrame implements ItemListener {
    private JPanel rootPanel;
    private JLabel codeLabel;
    private JLabel ccLabel;
    private JLabel nameLabel;
    private JLabel lastname_Label;
    private JButton cargarDatosButton;
    private JButton guardarDatosButton;
    private JTextField codeInput;
    private JTextField ccInput;
    private JTextField nameInput;
    private JTextField lastInput;
    private JLabel signLabel;

    /*Combo Box Variables -------*/
    private JComboBox <String> signoBox;
    private JComboBox<String> dayBox;
    private JComboBox<String> mesBox;
    private JComboBox<String> yearBox;
    private JLabel sateLabel;
    /*---------------------------*/
    private JLabel colorLabel;
    private JLabel civilLabel;
    private JCheckBox redChbox;
    private JCheckBox greenChBox;
    private JCheckBox noneChbox;
    private JRadioButton yesCButton;
    private JRadioButton noCButton;
    private JButton backButton;
    private JButton fowardButton;

    /*Variables y arrays externos a Jframe*/
    private int items = 0;
    String filepath ="datos.txt";
    ArrayList<Estudiante> people = new ArrayList<Estudiante>();

    /*Variables y elementos añadidos para combo boxes*/
    //String [] signos = {"Aries", "Leo", "Cáncer", "Virgo", "Escorpio"};
    /*-----------------------------------------------*/

    public Fomulario() {
        /*Signo Combo Box*/
        signoBox.addItem("Aries");
        signoBox.addItem("Leo");
        signoBox.addItem("Escorpio");
        signoBox.addItem("Cáncer");
        signoBox.addItem("Libra");
        signoBox.addItemListener(this);

        /*----FechaBox----*/
        dayBox.addItem("1");
        dayBox.addItem("2");
        dayBox.addItem("3");
        dayBox.addItem("4");
        dayBox.addItem("5");
        dayBox.addItem("6");
        dayBox.addItem("7");
        dayBox.addItem("8");
        dayBox.addItem("9");
        dayBox.addItem("10");
        dayBox.addItemListener(this);

        mesBox.addItem("Enero");
        mesBox.addItem("Febrero");
        mesBox.addItem("Marzo");
        mesBox.addItem("Abril");
        mesBox.addItem("Mayo");
        mesBox.addItem("Junio");
        mesBox.addItem("Julio");
        mesBox.addItem("Agosto");
        mesBox.addItem("Septiembre");
        mesBox.addItem("Octubre");
        mesBox.addItem("Noviembre");
        mesBox.addItem("Diciembre");
        mesBox.addItemListener(this);

        yearBox.addItem("1999");
        yearBox.addItem("2000");
        yearBox.addItem("2001");
        yearBox.addItem("2002");
        yearBox.addItem("2003");
        yearBox.addItemListener(this);
        /*-----------------------------------------*/

        guardarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Instanciamos el objeto*/
                Estudiante person = new Estudiante(nameInput.getText(), lastInput.getText(), ccInput.getText(), codeInput.getText());

                for (int i = 0; i <= items; i = i + 1) {
                    people.add(person);
                }

                try (FileOutputStream fileOutputStream = new FileOutputStream(filepath);
                     ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
                    for (int i = 0; i < people.size(); i++) {
                        if ((people.get(i++).equals(people.get(i - 1)))) break;
                        else {
                            outputStream.writeObject(people.get(i).getNombres());
                            outputStream.writeObject(people.get(i).getApellidos());
                            outputStream.writeObject(people.get(i).getCedula());
                            outputStream.writeObject(people.get(i).getCodigo());
                        }
                    }
                } catch (IOException exception) {
                    throw new RuntimeException(new Exception(exception));
                }
                nameInput.setText("");
                lastInput.setText("");
                ccInput.setText("");
                codeInput.setText("");

            }
        });
        cargarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileInputStream fileInputStream = new FileInputStream(filepath);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    for (Estudiante person : people) {
                        nameInput.setText(person.getNombres());
                        lastInput.setText(person.getApellidos());
                        ccInput.setText(person.getCedula());
                        codeInput.setText(person.getCodigo());
                    }
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Estudiantes");
        frame.setContentPane(new Fomulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,550);
        frame.setResizable(false);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == signoBox){
            String seleccion = (String) signoBox.getSelectedItem();
            setTitle(seleccion);
        }
    }
}