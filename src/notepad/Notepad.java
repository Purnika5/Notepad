 package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    String text;
    JButton emojiButton;

    public Notepad() {
        setTitle("Notepad");
        setLayout(new BorderLayout());

        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/ions/notes.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();

        // File menu
        JMenu file = new JMenu("File");
        file.setFont(new Font("ARIAL", Font.PLAIN, 14));
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newdoc);
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(open);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(save);
        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(print);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        file.add(exit);
        menubar.add(file);

        // Edit menu
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("ARIAL", Font.PLAIN, 14));
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(paste);
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cut);
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(selectAll);
        menubar.add(edit);

        // About menu
        JMenu helpmenu = new JMenu("About");
        helpmenu.setFont(new Font("ARIAL", Font.PLAIN, 14));
        JMenuItem help = new JMenuItem("About");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help.addActionListener(this);
        helpmenu.add(help);
        menubar.add(helpmenu);

        // Emoji button
        emojiButton = new JButton("😊 Emojis");
        emojiButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        emojiButton.setFocusPainted(false);
        emojiButton.setBorderPainted(false);
        emojiButton.setContentAreaFilled(false);
        emojiButton.addActionListener(e -> showEmojiPopup());
        menubar.add(emojiButton);

        // Theme menu
        JMenu themeMenu = new JMenu("Theme");
        themeMenu.setFont(new Font("ARIAL", Font.PLAIN, 14));
        JMenuItem lightTheme = new JMenuItem("Light Mode");
        JMenuItem darkTheme = new JMenuItem("Dark Mode");
        lightTheme.addActionListener(e -> setTheme("light"));
        darkTheme.addActionListener(e -> setTheme("dark"));
        themeMenu.add(lightTheme);
        themeMenu.add(darkTheme);
        menubar.add(themeMenu);

        setJMenuBar(menubar);

        // Text area
        area = new JTextArea();
        area.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);

        // Default light theme
        setTheme("light");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void showEmojiPopup() {
        JPopupMenu emojiPopup = new JPopupMenu();
        JPanel emojiGrid = new JPanel(new GridLayout(10, 10, 4, 4));

        String[] emojis = {
            "😀", "😁", "😂", "🤣", "😃", "😄", "😅", "😆", "😉", "😊",
            "😋", "😎", "😍", "😘", "🥰", "😗", "😙", "😚", "🙂", "🤗",
            "🤩", "🤔", "🤨", "😐", "😶", "🙄", "😏", "😣", "😥", "😮",
            "🤐", "😯", "😪", "😫", "😴", "😌", "😛", "😜", "😝", "🤤",
            "😒", "😓", "😭", "😢", "😤", "😠", "😡", "😷", "🤒", "🤕",
            "👍", "👎", "👏", "🙏", "💪", "🤝", "👀", "👂", "🧠", "🦾",
            "❤️", "💔", "💖", "💗", "💘", "💕", "💞", "💓", "💙", "💚",
            "🎉", "🎊", "✨", "🔥", "💯", "✅", "❌", "⚠️", "📌", "📎",
            "📚", "📖", "📝", "📅", "📷", "🎥", "🎧", "🖥️", "⌨️", "💡",
            "🔒", "🔓", "🔑", "🔍", "📞", "☎️", "💬", "🗨️", "📢", "🔔"
        };

        for (String emoji : emojis) {
            JButton emojiBtn = new JButton(emoji);
            emojiBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            emojiBtn.setMargin(new Insets(1, 1, 1, 1));
            emojiBtn.addActionListener(e -> {
                area.insert(emoji, area.getCaretPosition());
                emojiPopup.setVisible(false);
            });
            emojiGrid.add(emojiBtn);
        }

        emojiPopup.setLayout(new BorderLayout());
        emojiPopup.add(emojiGrid, BorderLayout.CENTER);
        emojiPopup.show(emojiButton, 0, emojiButton.getHeight());
    }

    private void setTheme(String mode) {
    if (mode.equals("dark")) {
        area.setBackground(Color.decode("#2B2B2B"));
        area.setForeground(Color.WHITE);
        area.setCaretColor(Color.WHITE);
        area.setSelectedTextColor(Color.BLACK);
        area.setSelectionColor(Color.LIGHT_GRAY);
        getJMenuBar().setBackground(Color.DARK_GRAY);

        // Set all menu item foregrounds to white
        for (int i = 0; i < getJMenuBar().getMenuCount(); i++) {
            JMenu menu = getJMenuBar().getMenu(i);
            if (menu != null) {
                menu.setForeground(Color.WHITE);
                for (int j = 0; j < menu.getItemCount(); j++) {
                    JMenuItem item = menu.getItem(j);
                    if (item != null) item.setForeground(Color.BLACK); // keep submenu items readable on white popup
                }
            }
        }
    } else {
        area.setBackground(Color.WHITE);
        area.setForeground(Color.BLACK);
        area.setCaretColor(Color.BLACK);
        area.setSelectedTextColor(Color.WHITE);
        area.setSelectionColor(Color.GRAY);
        getJMenuBar().setBackground(Color.WHITE);

        // Set all menu item foregrounds to black
        for (int i = 0; i < getJMenuBar().getMenuCount(); i++) {
            JMenu menu = getJMenuBar().getMenu(i);
            if (menu != null) {
                menu.setForeground(Color.BLACK);
                for (int j = 0; j < menu.getItemCount(); j++) {
                    JMenuItem item = menu.getItem(j);
                    if (item != null) item.setForeground(Color.BLACK);
                }
            }
        }
    }

    SwingUtilities.updateComponentTreeUI(this);
}


    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {
            case "New": area.setText(""); break;
            case "Open":
                JFileChooser chooser = new JFileChooser();
                chooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                chooser.addChoosableFileFilter(restrict);
                int action = chooser.showOpenDialog(this);
                if (action == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        area.setText("");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            area.append(line + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "Save":
                JFileChooser saveas = new JFileChooser();
                saveas.setApproveButtonText("Save");
                int saveAction = saveas.showOpenDialog(this);
                if (saveAction != JFileChooser.APPROVE_OPTION) return;
                File filename = new File(saveas.getSelectedFile() + ".txt");
                try (BufferedWriter outFile = new BufferedWriter(new FileWriter(filename))) {
                    area.write(outFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Print":
                try {
                    area.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Exit": System.exit(0); break;
            case "Copy": text = area.getSelectedText(); break;
            case "Paste": area.insert(text, area.getCaretPosition()); break;
            case "Cut":
                text = area.getSelectedText();
                area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
                break;
            case "Select All": area.selectAll(); break;
            case "About": new About().setVisible(true); break;
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
