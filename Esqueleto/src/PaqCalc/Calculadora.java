package PaqCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MANEJO DE EVENTOS EN UNA GUI EN JAVA.

public class Calculadora implements ActionListener {

    private final JTextField t=new JTextField();
    private final JButton[] botones={new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4"),
                       new JButton("5"),new JButton("6"),new JButton("7"),new JButton("8"),
                       new JButton("9"),new JButton("0"),new JButton("."),new JButton("+"),
                       new JButton("-"),new JButton("*"),new JButton("/"),new JButton("=")
                       };
    private final JButton bdel=new JButton("Delete");
    private final JButton bclr=new JButton("Clear");
    private double a;
    private double b;
    private double resultado;
    private int operador;


    public Calculadora() {

        a=b=resultado=operador=0;

        JFrame f = new JFrame("Calculadora");
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
        f.add(bdel);
        f.add(bclr);



        f.setLayout(null);
        f.setVisible(true);
        f.setSize(350, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Por defecto se usa HIDE_ON_CLOSE y el programa sigue activo en memoria
        f.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        f.setIconImage(icon);
        f.setLocationRelativeTo(null); //se posiciona en el centro de la pantalla

        // AÑADE LOS LISTENERS

    }
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

      t.requestFocus(); // Sitúo el foco de nuevo en la caja de texto.


    }

    private void Operar() {
        b = Double.parseDouble(t.getText());

        // REALIZAR LA OPERACIÓN OPORTUNA EN FUNCIÓN DEL VALOR DE LA VARIABLE operador
        // Y MOSTRAR EL VALOR DE LA VARIABLE EN LA CAJA DE TEXTO

    }

}

// APARTADO 2. HACER QUE CUANDO SE PULSE LA TECLA Intro SE REALICE EL CÁLCULO USANDO LA INTERFACE KeyListener

// APARTADO 3. HACER QUE AL PULSAR EL "Aspa" SITUADA EN LA PARTE SUPERIOR DERECHA DE LA VENTANA
//             EL PROGRAMA NOS PREGUNTE SI ESTAMOS SEGUROS DE SALIR DE LA APLICACIÓN O NO.


