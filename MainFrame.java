import java.awt.Cursor;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JOptionPane;

class MainFrame extends JFrame {

    private Container container;
    private JRadioButton array, string, oneDimension, twoDimension, threeDimension;
    private ButtonGroup buttonGroup1, buttonGroup2;
    private JLabel label1, label2;
    private JScrollPane scrollPane1, scrollPane2;
    private JButton clearButton, runButton;
    private JTextArea textArea1, textArea2;

    private int[] selectedValue = new int[2];

    private MainFrame() {
        initContainer();
        initRadioButton();
        initLabel();
        initTextArea();
        initButton();
    }

    private void initContainer() {
        container = this.getContentPane();
        container.setLayout(null);
    }

    private void initRadioButton() {

        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();

        array = new JRadioButton("Array");
        array.setBounds(20, 20, 70, 20);
        array.setSelected(true);
        container.add(array);

        string = new JRadioButton("String");
        string.setBounds(20, 50, 70, 20);
        container.add(string);

        buttonGroup1.add(array);
        buttonGroup1.add(string);

        oneDimension = new JRadioButton("1D");
        oneDimension.setBounds(150, 20, 50, 20);
        oneDimension.setSelected(true);
        container.add(oneDimension);

        twoDimension = new JRadioButton("2D");
        twoDimension.setBounds(150, 50, 50, 20);
        container.add(twoDimension);

        threeDimension = new JRadioButton("3D");
        threeDimension.setBounds(150, 80, 50, 20);
        container.add(threeDimension);

        buttonGroup2.add(oneDimension);
        buttonGroup2.add(twoDimension);
        buttonGroup2.add(threeDimension);

        LRadioButton1 listener1 = new LRadioButton1();
        LRadioButton2 listener2 = new LRadioButton2();

        array.addActionListener(listener1);
        string.addActionListener(listener1);
        oneDimension.addActionListener(listener2);
        twoDimension.addActionListener(listener2);
        threeDimension.addActionListener(listener2);
    }

    private void initLabel() {
        label1 = new JLabel("Enter Testcase");
        label1.setBounds(20, 120, 150, 20);
        container.add(label1);

        label2 = new JLabel("Output");
        label2.setBounds(20, 350, 100, 20);
        container.add(label2);
    }

    private void initTextArea() {
        textArea1 = new JTextArea();
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        scrollPane1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(20, 150, 560, 180);
        container.add(scrollPane1);


        textArea2 = new JTextArea();
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);

        scrollPane2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(20, 380, 560, 180);
        container.add(scrollPane2);

    }

    private void initButton() {
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(500, 580, 80, 20);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(clearButton);

        runButton = new JButton("RUN");
        runButton.setBounds(400, 580, 80, 20);
        runButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(runButton);

        runButton.addActionListener(new LRunButton());
        clearButton.addActionListener(new LClearButton());
    }

    String getFromatedText(String s) {
        if (selectedValue[0] == 0) {
            if (selectedValue[1] == 0) {
                return s.substring(1, s.length() - 1).replaceAll("[,]+", " ");
            } else if (selectedValue[1] == 1) {
                s = s.substring(2, s.length() - 1);
                String res = "";
                int pos = s.indexOf("]"), cur = 0;
                while (pos != -1) {
                    res = res.concat(s.substring(cur, pos).replaceAll("[,]+", " ").concat("\n"));
                    cur = pos + 3;
                    pos = s.indexOf("]", cur);
                }
                return res;
            } else {
                JOptionPane.showMessageDialog(null, "its a dummy :3, Inshaallah I will work with it", "Title", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (selectedValue[1] == 0) {
                return s.substring(1, s.length() - 1);
            } else if (selectedValue[1] == 1) {
                JOptionPane.showMessageDialog(null, "What the heck is 2D string?!", "Title", JOptionPane.QUESTION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "What the heck is 3D string?!", "Title", JOptionPane.QUESTION_MESSAGE);
            }
        }
        return "";
    }

    class LRadioButton1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == array)
                selectedValue[0] = 0;
            else
                selectedValue[0] = 1;
        }
    }

    class LRadioButton2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == oneDimension)
                selectedValue[1] = 0;
            else if (e.getSource() == twoDimension)
                selectedValue[1] = 1;
            else
                selectedValue[1] = 2;
        }
    }

    class LRunButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea2.setText(getFromatedText(textArea1.getText()));
            textArea2.requestFocus();
            textArea2.select(0, textArea2.getText().length());
        }
    }

    class LClearButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea2.setText("");
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Leetcode Helper");
        mainFrame.setBounds(200, 150, 600, 700);
    }
}
