package mygui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotePad extends JFrame implements ActionListener {

    // Text Area
    JTextArea textArea;

    // Menu Components
    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem;
    JMenuItem cutItem, copyItem, pasteItem, selectAllItem;

    // File chooser
    JFileChooser fileChooser;

    // Constructor (OOP concept)
    public NotePad() {
        setTitle("Simple Notepad - Java OOP Made by Laiba  :) ");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize components
        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        fileChooser = new JFileChooser();

        createMenu();

        setVisible(true);
    }

    // Method to create menu (OOP modularity)
    private void createMenu() {
        menuBar = new JMenuBar();

        
        // File Menu
        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save As");
        exitItem = new JMenuItem("Exit");

        // Add action listeners
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit Menu
        editMenu = new JMenu("Edit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        selectAllItem = new JMenuItem("Select All");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectAllItem);

        // Add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);
    }

    // Event handling (core OOP concept)
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New":
                textArea.setText("");
                break;

            case "Open":
                openFile();
                break;

            case "Save":
                saveFile();
                break;

            case "Save As":
                saveFileAs();
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Cut":
                textArea.cut();
                break;

            case "Copy":
                textArea.copy();
                break;

            case "Paste":
                textArea.paste();
                break;

            case "Select All":
                textArea.selectAll();
                break;
        }
    }

    // File operations (OOP methods)

    private void openFile() {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file");
            }
        }
    }

    private void saveFile() {
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file");
            }
        }
    }

    private void saveFileAs() {
        saveFile(); // reuse method (OOP reuse)
    }

    // Main method (entry point)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotePad());
    }
}

