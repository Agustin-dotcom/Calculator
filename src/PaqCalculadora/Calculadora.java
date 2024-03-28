package PaqCalculadora;
/**
 * PROBLEMAS:
 * 1. Cuando hacemos 7.2/2 con botones virtuales, me da Undefined Result
 * 2.Cuando le doy a Delete teniendo Undefined Result en el textField, me da error
 */

import AliquoteSequence.AliquotSequence;
import MisExcepciones.NaNException;
import TaylorMethod.Taylor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.StringReader;

// MANEJO DE EVENTOS EN UNA GUI EN JAVA.

public class Calculadora extends JFrame implements ActionListener,KeyListener {
    private final JButton aliquoteButton = new JButton("aliquote");
    private final JCheckBox checkBox = new JCheckBox("scientific");
    private final JButton pi = new JButton("PI");
    private final JButton cos = new JButton("sin()");
    private final JTextField t=new JTextField();
    private final JButton[] botones={new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4"),
                       new JButton("5"),new JButton("6"),new JButton("7"),new JButton("8"),
                       new JButton("9"),new JButton("0"),new JButton("."),new JButton("+"),
                       new JButton("-"),new JButton("*"),new JButton("/"),new JButton("=")};
    private final JButton bdel=new JButton("Delete");
    private final JButton bclr=new JButton("Clear");
    private double a;
    private double b;
    private double resultado;
    private int operador;
    private StringBuilder stringBuilder=new StringBuilder();
    private final JFrame f = new JFrame("Calculadora");

    public Calculadora(){
        a=b=resultado=operador=0;

        t.setBounds(30, 40, 280, 30);
        f.add(t);
        int indice=0;
        for (int x = 40; x <= 250; x = x + 70) {
            for (int y = 100; y <= 310; y = y + 70) {
                botones[indice].setBounds(x, y, 50, 40);
                f.add(botones[indice]);
                indice++;
            }
        }
        bdel.setBounds(60, 380, 100, 40);
        bclr.setBounds(180, 380, 100, 40);
        checkBox.setBounds(100,420,100,40);
        f.add(checkBox);
        f.add(bdel);
        f.add(bclr);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(350, 500);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int reply=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?");
                if(reply==JOptionPane.YES_OPTION){//solo salimos si se aprieta la opcion yes
                    System.exit(0);
                }
            }
        });
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Por defecto se usa HIDE_ON_CLOSE y el programa sigue activo en memoria
        f.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/PaqCalculadora/icon.png");
        f.setIconImage(icon);
        f.setLocationRelativeTo(null); //se posiciona en el centro de la pantalla
        // AÑADE LOS LISTENERS
        for(int i=0;i<botones.length;++i){
            botones[i].addActionListener(this);
        }
        this.aliquoteButton.addActionListener(this);
        this.bclr.addActionListener(this);
        this.bdel.addActionListener(this);
        this.checkBox.addActionListener(this);
        this.cos.addActionListener(this);
        this.pi.addActionListener(this);
        t.addKeyListener(this);
        //a ver... nop
        //t.requestFocus();
// APARTADO 1. HACER QUE EL PROGRAMA RESPONDA ANTE TODOS LOS POSIBLES EVENTOS PROVOCADOS POR
// EL RATÓN EN EL MANEJO DE LA CALCULADORA

    // NOMBRE DE MÉTODO ADECUADO PARA PROCESAR LOS EVENTOS {

       /*
          1. COMPONER EL NÚMERO PARA QUE APAREZCA EN LA CAJA DE TEXTO

          2. SI SE HA PULSADO EL BOTÓN + CONVERTIR A DOBLE EL TEXTO DE LA CAJA Y PONERLA VACÍA.
             FIJAR EL VALOR DE LA VARIABLE OPERADOR

          3. HACER ALGO SIMILAR CON EL RESTO DE OPERADORES

          4. SI SE PULSA EL BOTÓN  =  LLAMAR AL MÉTODO Operar

          5. SI SE PULSA EL BOTÓN Clear BORRAR LA CAJA DE TEXTO

          6. SI SE PULSA EL BOTÓN Delete USAR UN StringBuilder PARA ELIMINAR UN CARÁCTER DE LA SECUENCIA

        */
        // Sitúo el foco de nuevo en la caja de texto.
        t.requestFocus();
    }
    private void operar() {
        // REALIZAR LA OPERACIÓN OPORTUNA EN FUNCIÓN DEL VALOR DE LA VARIABLE operador
        // Y MOSTRAR EL VALOR DE LA VARIABLE EN LA CAJA DE TEXTO

        //Quiero procesar el siguiente tipo de formato: "1+2"
        b=Double.parseDouble(t.getText());
        switch (operador){
            case 1 -> {
                try {
                    resultado = a + b;
                    stringBuilder.delete(0, stringBuilder.length());
                    t.setText(String.valueOf(resultado));
                }catch(ArithmeticException s1){
                    t.setText("Undefined Result");
                }
            }
            case 2 -> {
                try {
                    resultado = a - b;
                    stringBuilder.delete(0, stringBuilder.length());
                    t.setText(String.valueOf(resultado));
                }catch(ArithmeticException k1){
                    t.setText("Undefined Result");
                }
            }
            case 3 -> {
                try {
                    resultado = a * b;
                    stringBuilder.delete(0, stringBuilder.length());
                    t.setText(String.valueOf(resultado));
                }catch(ArithmeticException s){
                    t.setText("Undefined Result");
                }
            }
            case 4 -> {
                try {
                    resultado = a / b;
                    stringBuilder.delete(0, stringBuilder.length());
                    t.setText(String.valueOf(resultado));
                    if(Double.isNaN(resultado)||Double.isInfinite(resultado))  throw new NaNException("hola");
                }catch(NaNException k){
                    t.setText("Undefined Result");
                }
            }
        }
        t.requestFocus();//BIEN
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
        if(e.getSource()==cos){
            Taylor taylor = new Taylor();
            double result = taylor.calculateTaylor(17,Math.toRadians(Double.parseDouble(t.getText())));
            t.setText(String.valueOf(result));
        }
        if(e.getSource()==pi){
            t.setText(String.valueOf(Math.PI));
        }
        if(e.getSource()==checkBox) {
            if (checkBox.isSelected()) {
                f.setSize(350, 560);
                pi.setBounds(5, 460, 100, 50);
                f.add(pi);
                pi.setVisible(true);
                cos.setBounds(115, 460, 100, 50);
                f.add(cos);
                cos.setVisible(true);
                aliquoteButton.setBounds(225,460,100,50);
                f.add(aliquoteButton);
                aliquoteButton.setVisible(true);
                f.revalidate();
                f.repaint();
                f.setLocationRelativeTo(null);
            }
            else{
                f.setSize(350, 500);
                f.remove(pi);
                f.remove(cos);
                f.remove(aliquoteButton);
                f.setLocationRelativeTo(null);
                f.revalidate();
                f.repaint();
            }
        }
        if(e.getSource() == botones[0]){
            stringBuilder.append("1");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[1]){
            stringBuilder.append("2");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[2]){
            stringBuilder.append("3");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[3]){
            stringBuilder.append("4");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[4]){
            stringBuilder.append("5");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[5]){
            stringBuilder.append("6");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[6]){
            stringBuilder.append("7");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[7]){
            stringBuilder.append("8");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[8]){//9
            stringBuilder.append("9");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[9]){//0
            stringBuilder.append("0");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[10]){
            stringBuilder.append(".");
            t.setText(stringBuilder.toString());
        }
        if(e.getSource() == botones[11]){//+
            try{
            a = Double.parseDouble(t.getText());
            stringBuilder.delete(0,stringBuilder.length());
            t.setText("");
            operador = 1;//indicamos que es una suma
            }catch(NumberFormatException f){
                JOptionPane.showMessageDialog(null,"That's not a number.");
            }
        }
        if(e.getSource() == botones[15]){//=
            operar();
        }
        if(e.getSource() == botones[12]){
            try{
            a = Double.parseDouble(t.getText());
            stringBuilder.delete(0,stringBuilder.length());
            t.setText("");
            operador = 2;//indicamos que es una resta
            }catch(NumberFormatException g){
                JOptionPane.showMessageDialog(null,"That's not a number.");
            }
        }
        if(e.getSource() == botones[13]){
            try{
            a = Double.parseDouble(t.getText());
            stringBuilder.delete(0,stringBuilder.length());
            t.setText("");
            operador = 3;//indicamos que es una multi
            }catch(NumberFormatException g){
                JOptionPane.showMessageDialog(null,"That's not a number.");
            }
        }
        if(e.getSource() == botones[14]){
            try{
            a = Double.parseDouble(t.getText());
            stringBuilder.delete(0,stringBuilder.length());
            t.setText("");
            operador = 4;//indicamos que es una divi
            }catch(NumberFormatException g){
                JOptionPane.showMessageDialog(null,"that's not a number.");
            }
        }
        if(e.getSource() == bclr){
            stringBuilder.delete(0,stringBuilder.length());
            t.setText("");
        }
        if(e.getSource() == bdel){
            //queremos pasar de "545" a "54"
            try{
                stringBuilder.deleteCharAt(t.getText().length()-1);
                t.setText(stringBuilder.toString());
            }catch(StringIndexOutOfBoundsException m){
                JOptionPane.showMessageDialog(null,"error");
            }
        }
        if(e.getSource() == aliquoteButton){
            AliquotSequence aliquotSequence1 = new AliquotSequence(Integer.parseInt(t.getText()));
            t.setText(aliquotSequence1.calculate());
        }
        t.requestFocus();//BIEN
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            //System.out.println(e.getKeyCode());
            if(e.getKeyCode()==KeyEvent.VK_ADD){
                a = Double.parseDouble(t.getText());
                stringBuilder.delete(0,stringBuilder.length());
                t.setText(stringBuilder.toString());
                operador = 1;//indicamos que es una suma
                t.requestFocus();
            }
            if(e.getKeyCode()==KeyEvent.VK_SUBTRACT){
                a = Double.parseDouble(t.getText());//recogemos dato (a)
                stringBuilder.delete(0,stringBuilder.length());//limpiamos nuestro string
                t.setText(stringBuilder.toString());
                operador = 2;//indicamos que es una resta
            }
            if(e.getKeyCode()==KeyEvent.VK_MULTIPLY){
                a = Double.parseDouble(t.getText());
                stringBuilder.delete(0,stringBuilder.length());
                t.setText("");
                operador = 3;//indicamos que es una multi
            }
            if(e.getKeyCode()==KeyEvent.VK_DIVIDE){
                a = Double.parseDouble(t.getText());
                stringBuilder.delete(0,stringBuilder.length());
                t.setText("");
                operador = 4;//indicamos que es una divi
            }
            if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                if(t.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"error");
                }else{
                    stringBuilder.delete(0,stringBuilder.length());
                    stringBuilder.append(t.getText());
                    t.setText(stringBuilder.toString());
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                stringBuilder = new StringBuilder(t.getText());
                for(int i=0;i<stringBuilder.length();i++){
                    if(stringBuilder.charAt(i)=='+'||
                            stringBuilder.charAt(i)=='-'||
                            stringBuilder.charAt(i)=='*'||
                            stringBuilder.charAt(i)=='/')
                    {
                        stringBuilder.deleteCharAt(i);
                    }
                }
                b=Double.parseDouble(stringBuilder.toString());
                operar();
            }
            if(e.getKeyCode()==KeyEvent.VK_WINDOWS){
                AliquotSequence aliquoteSequence = new AliquotSequence(Integer.parseInt(t.getText()));
                t.setText(aliquoteSequence.calculate());
            }
        }catch(NumberFormatException h){
            JOptionPane.showMessageDialog(null,"There was an error parsing your number. Try again, please.");
        }
        t.requestFocus();//BIEN
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}

// APARTADO 2. HACER QUE CUANDO SE PULSE LA TECLA Intro SE REALICE EL CÁLCULO USANDO LA INTERFACE KeyListener

// APARTADO 3. HACER QUE AL PULSAR EL "Aspa" SITUADA EN LA PARTE SUPERIOR DERECHA DE LA VENTANA
//             EL PROGRAMA NOS PREGUNTE SI ESTAMOS SEGUROS DE SALIR DE LA APLICACIÓN O NO.


