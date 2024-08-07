import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Notepad implements ActionListener {

    public JTextArea area;
    public JFrame frame;
    public String path = "";
    JMenu fileMenu;
    JMenuItem newPage, openFile, save;
    int count;

    public Notepad() {
        frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(new Point(500, 200));
        frame.setTitle("Notepad");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("lightOn.jpg"));
        frame.setLayout(new BorderLayout());

        area = new JTextArea(25, 50);
        area.setFont(new Font("serif", Font.BOLD, 14));
        frame.add(new JScrollPane(area));

        JMenuBar menuContainer = new JMenuBar();
        fileMenu = new JMenu("File");

        newPage = new JMenuItem("New", 'N');
        newPage.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        newPage.addActionListener(e -> {
            count += 1;
            area.setText("");
        });

        openFile = new JMenuItem("Open File", 'O');
        openFile.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        openFile.addActionListener(e -> {
            JFileChooser fopen = new JFileChooser();
            int option = fopen.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                area.setText("");
                try {
                    Scanner scan = new Scanner(new FileReader(fopen.getSelectedFile().getPath()));
                    while (scan.hasNext()) {
                        area.append(scan.nextLine() + "\n");
                    }
                    frame.setTitle(fopen.getSelectedFile().getPath() + "- Editor");
                    path = fopen.getSelectedFile().getPath();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        fileMenu.add(newPage);
        fileMenu.add(openFile);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx(), false));
        save.addActionListener(e -> {
            if (path.equals("")) {
                saveAsFile();
            } else {
                saveFile();
            }
        });

        fileMenu.add(save);

        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK));
        saveAs.addActionListener(e -> saveAsFile());

        fileMenu.add(saveAs);

        JMenuItem pageSetup = new JMenuItem("Page Setup");
        pageSetup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK | ActionEvent.ALT_MASK));
        pageSetup.addActionListener(e -> {
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.pageDialog(pj.defaultPage());
        });
        fileMenu.add(pageSetup);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(e -> {
            PrinterJob pj = PrinterJob.getPrinterJob();
            if (pj.printDialog()) {
                try {
                    pj.print();
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        });
        fileMenu.add(print);

        JMenuItem window = new JMenuItem("New Window");
        window.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK));
        window.addActionListener(e -> new Notepad());
        fileMenu.add(window);

        menuContainer.add(fileMenu);

        frame.setJMenuBar(menuContainer);

        frame.pack();
        frame.setVisible(true);
    }

    public void saveFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            out.write(area.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void saveAsFile() {
        JFileChooser fsave = new JFileChooser();
        int option = fsave.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(fsave.getSelectedFile().getPath()))) {
                out.write(area.getText());
                frame.setTitle(fsave.getSelectedFile().getPath() + "- Editor");
                path = fsave.getSelectedFile().getPath();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Add your action handling code here if needed
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
