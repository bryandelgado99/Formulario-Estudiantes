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
    private JComboBox dayBox;
    private JComboBox mesBox;
    private JComboBox yearBox;
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

    /*-----------------------------------------------*/


    /*Acciones del panel*/
    public Fomulario() {
        /*Creamos acciones de los combo box*/
        signoBox = new JComboBox();
        signoBox.addItem("Acuario");
        signoBox.addItem("Aries");
        signoBox.addItem("Cáncer");
        signoBox.addItem("Capricornio");
        signoBox.addItem("Escorpio");
        signoBox.setSelectedItem("Acuario");


        guardarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            /*Instanciamos el objeto*/
                Estudiante person = new Estudiante(nameInput.getText(), lastInput.getText(), ccInput.getText(), codeInput.getText());

                for(int i=0; i<=items; i=i+1){
                    people.add(person);
                }

                try(FileOutputStream fileOutputStream = new FileOutputStream(filepath);
                    ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream))
                {
                    for (int i=0; i<people.size(); i++){
                        if((people.get(i++).equals(people.get(i-1)))) break;
                        else {
                            outputStream.writeObject(people.get(i).getNombres());
                            outputStream.writeObject(people.get(i).getApellidos());
                            outputStream.writeObject(people.get(i).getCedula());
                            outputStream.writeObject(people.get(i).getCodigo());
                        }
                    }
                }catch (IOException exception) {
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
                try (FileInputStream fileInputStream=new FileInputStream(filepath);
                     ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream))
                {
                    for (Estudiante person:people){
                        nameInput.setText(person.getNombres());
                        lastInput.setText(person.getApellidos());
                        ccInput.setText(person.getCedula());
                        codeInput.setText(person.getCodigo());
                    }
                }catch (IOException exception){
                    throw new RuntimeException(exception);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    }
}
