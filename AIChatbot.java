import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AIChatbot extends JFrame {
    private final Color BG = new Color(8, 16, 34);
    private final Color PANEL = new Color(17, 30, 57);
    private final Color PANEL2 = new Color(22, 39, 72);
    private final Color PURPLE = new Color(91, 67, 224);
    private final Color BLUE = new Color(37, 111, 255);
    private final Color TEXT = new Color(239, 244, 255);
    private final Color MUTED = new Color(164, 184, 222);
    private JPanel messages;
    private JTextField input;
    private JScrollPane scroll;

    public AIChatbot() {
        setTitle("Java AI Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 760);
        setMinimumSize(new Dimension(850, 620));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);
        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);
        addWelcomeMessage();
    }

    private JPanel createSidebar() {
        JPanel side = new JPanel(new BorderLayout(0, 18));
        side.setPreferredSize(new Dimension(225, 0));
        side.setBackground(new Color(12, 22, 48));
        side.setBorder(new EmptyBorder(24, 16, 20, 16));

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("🤖  Java AI");
        logo.setFont(new Font("SansSerif", Font.BOLD, 25));
        logo.setForeground(TEXT);
        JLabel logo2 = new JLabel("      Chatbot");
        logo2.setFont(new Font("SansSerif", Font.BOLD, 25));
        logo2.setForeground(new Color(115, 164, 255));
        JLabel tagline = new JLabel("Your Learning Buddy ✦");
        tagline.setForeground(MUTED);
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tagline.setBorder(new EmptyBorder(8, 3, 24, 0));
        top.add(logo); top.add(logo2); top.add(tagline);

        top.add(navButton("▣   Chat", true, () -> addBotMessage("You are already in Chat! Ask me about Java, OOP, ArrayList or study tips.")));
        top.add(Box.createVerticalStrut(8));
        top.add(navButton("</>   Java Basics", false, () -> sendPreset("Java basics")));
        top.add(Box.createVerticalStrut(8));
        top.add(navButton("⚙   OOP Concepts", false, () -> sendPreset("What is OOP?")));
        top.add(Box.createVerticalStrut(8));
        top.add(navButton("♧   Study Tips", false, () -> sendPreset("study tips")));
        top.add(Box.createVerticalStrut(8));
        top.add(navButton("ⓘ   About", false, () -> addBotMessage("I'm a Java Swing educational chatbot. I use rule-based keyword matching, so I work offline and do not connect to a live AI model.")));

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(new Color(35, 45, 119));
        bottom.setBorder(new EmptyBorder(16, 14, 16, 14));
        JLabel rocket = new JLabel("🚀  Keep Learning");
        rocket.setForeground(Color.WHITE); rocket.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel growing = new JLabel("     Keep Growing");
        growing.setForeground(Color.WHITE); growing.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel small = new JLabel("Small steps make"); small.setForeground(new Color(218, 227, 255));
        JLabel small2 = new JLabel("big progress!"); small2.setForeground(new Color(218, 227, 255));
        small.setBorder(new EmptyBorder(16, 0, 0, 0));
        bottom.add(rocket); bottom.add(growing); bottom.add(small); bottom.add(small2);

        side.add(top, BorderLayout.NORTH);
        side.add(bottom, BorderLayout.CENTER);
        JLabel footer = new JLabel("♥ Built with Java Swing ☕");
        footer.setForeground(MUTED); footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setBorder(new EmptyBorder(16, 0, 0, 0));
        side.add(footer, BorderLayout.SOUTH);
        return side;
    }

    private JButton navButton(String text, boolean selected, Runnable action) {
        JButton b = new JButton(text);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        b.setPreferredSize(new Dimension(190, 43));
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFont(new Font("SansSerif", Font.PLAIN, 14));
        b.setForeground(selected ? Color.WHITE : MUTED);
        b.setBackground(selected ? new Color(55, 58, 174) : new Color(12, 22, 48));
        b.setBorder(new EmptyBorder(10, 12, 10, 8));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.addActionListener(e -> action.run());
        return b;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout(0, 12));
        main.setBackground(BG);
        main.setBorder(new EmptyBorder(18, 18, 18, 18));

        JPanel header = new JPanel(new BorderLayout(12, 0));
        header.setBackground(PANEL);
        header.setBorder(new EmptyBorder(16, 18, 16, 18));
        JLabel botIcon = new JLabel("🤖"); botIcon.setFont(new Font("SansSerif", Font.PLAIN, 28));
        JPanel titles = new JPanel(); titles.setOpaque(false); titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Chat with AI Assistant"); title.setForeground(TEXT); title.setFont(new Font("SansSerif", Font.BOLD, 19));
        JLabel sub = new JLabel("Ask anything about Java, OOP, ArrayList and more..."); sub.setForeground(MUTED); sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        titles.add(title); titles.add(Box.createVerticalStrut(5)); titles.add(sub);
        JLabel online = new JLabel("● Online  "); online.setOpaque(true); online.setBackground(new Color(12, 74, 79)); online.setForeground(new Color(82, 242, 190)); online.setBorder(new EmptyBorder(9, 12, 9, 12));
        header.add(botIcon, BorderLayout.WEST); header.add(titles, BorderLayout.CENTER); header.add(online, BorderLayout.EAST);

        messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setBackground(new Color(7, 17, 35));
        messages.setBorder(new EmptyBorder(18, 16, 18, 16));
        scroll = new JScrollPane(messages);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(31, 53, 91)));
        scroll.getViewport().setBackground(new Color(7, 17, 35));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel bottom = new JPanel(new BorderLayout(0, 10)); bottom.setOpaque(false);
        JPanel quick = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); quick.setOpaque(false);
        quick.add(quickButton("⚙ What is OOP?", "What is OOP?"));
        quick.add(quickButton("</> Java Features", "Java features"));
        quick.add(quickButton("▤ ArrayList", "What is ArrayList?"));
        quick.add(quickButton("♧ Study Tips", "study tips"));
        quick.add(quickButton("? Help", "help"));

        JPanel composer = new JPanel(new BorderLayout(10, 0)); composer.setOpaque(false);
        input = new JTextField(); input.setFont(new Font("SansSerif", Font.PLAIN, 15)); input.setForeground(TEXT); input.setCaretColor(Color.WHITE); input.setBackground(PANEL); input.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(39, 63, 109)), new EmptyBorder(13, 14, 13, 14)));
        input.putClientProperty("JTextField.placeholderText", "Type your message...");
        JButton send = new JButton("➤  Send"); send.setFont(new Font("SansSerif", Font.BOLD, 14)); send.setForeground(Color.WHITE); send.setBackground(PURPLE); send.setBorder(new EmptyBorder(12, 20, 12, 20)); send.setFocusPainted(false); send.setOpaque(true);
        send.addActionListener(e -> sendMessage()); input.addActionListener(e -> sendMessage());
        composer.add(input, BorderLayout.CENTER); composer.add(send, BorderLayout.EAST);
        bottom.add(quick, BorderLayout.NORTH); bottom.add(composer, BorderLayout.CENTER);

        main.add(header, BorderLayout.NORTH); main.add(scroll, BorderLayout.CENTER); main.add(bottom, BorderLayout.SOUTH);
        return main;
    }

    private JButton quickButton(String label, String prompt) {
        JButton b = new JButton(label); b.setFont(new Font("SansSerif", Font.PLAIN, 12)); b.setForeground(new Color(220, 229, 255)); b.setBackground(PANEL); b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 76, 135)), new EmptyBorder(9, 12, 9, 12))); b.setFocusPainted(false); b.setOpaque(true); b.addActionListener(e -> sendPreset(prompt)); return b;
    }

    private void addWelcomeMessage() {
        addBotMessage("Hello! 👋\nI'm your Java AI Chatbot. I can help you with:\n• Java programming concepts\n• OOP (Object-Oriented Programming)\n• ArrayList and data structures\n• Study tips and general guidance\n\nType your question, and I'll do my best to help!");
    }

    private void sendPreset(String text) { input.setText(text); sendMessage(); }

    private void sendMessage() {
        String text = input.getText().trim();
        if (text.isEmpty()) return;
        addMessage(text, true);
        input.setText("");
        addBotMessage(getResponse(text));
    }

    private void addBotMessage(String text) { addMessage(text, false); }

    private void addMessage(String text, boolean user) {
        JPanel row = new JPanel(new BorderLayout()); row.setOpaque(false); row.setBorder(new EmptyBorder(5, 0, 9, 0));
        JPanel bubble = new JPanel(); bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS)); bubble.setBackground(user ? new Color(40, 91, 232) : PANEL2); bubble.setBorder(new EmptyBorder(12, 14, 10, 14));
        bubble.setMaximumSize(new Dimension(610, Integer.MAX_VALUE));
        JTextArea body = new JTextArea(text); body.setEditable(false); body.setLineWrap(true); body.setWrapStyleWord(true); body.setOpaque(false); body.setForeground(TEXT); body.setFont(new Font("SansSerif", Font.PLAIN, 14)); body.setFocusable(false); body.setColumns(48); body.setBorder(null);
        body.setSize(new Dimension(500, Short.MAX_VALUE));
        JLabel time = new JLabel(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)) + "  "); time.setForeground(new Color(170, 193, 231)); time.setFont(new Font("SansSerif", Font.PLAIN, 10)); time.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bubble.add(body); bubble.add(Box.createVerticalStrut(5)); bubble.add(time);
        if (user) { row.add(bubble, BorderLayout.EAST); } else {
            JLabel icon = new JLabel("🤖"); icon.setFont(new Font("SansSerif", Font.PLAIN, 21)); icon.setBorder(new EmptyBorder(0, 0, 0, 8)); row.add(icon, BorderLayout.WEST); row.add(bubble, BorderLayout.CENTER);
        }
        messages.add(row); messages.revalidate(); messages.repaint();
        SwingUtilities.invokeLater(() -> scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum()));
    }

    private String getResponse(String message) {
        String m = message.toLowerCase(Locale.ROOT);
        if (containsAny(m, "hello", "hi", "hey", "good morning", "good evening")) return "Hey there! 👋\nWhat would you like to learn today? Try asking about Java, OOP, ArrayList, or loops.";
        if (m.contains("help")) return "Here are some things you can ask me:\n• What is Java?\n• Explain OOP\n• What is ArrayList?\n• Tell me Java features\n• Give me study tips\n\nTip: ask one topic at a time for a clearer answer.";
        if (m.contains("oop") || m.contains("object oriented")) return "OOP means Object-Oriented Programming. It organizes code around objects and classes. The four main principles are:\n• Encapsulation — keep data and methods together\n• Inheritance — reuse features from another class\n• Polymorphism — one interface, different behaviour\n• Abstraction — show essential details and hide complexity";
        if (m.contains("arraylist") || m.contains("array list")) return "ArrayList is a resizable list in Java. Unlike a normal array, it can grow or shrink as items are added or removed. Example:\n\nArrayList<String> names = new ArrayList<>();\nnames.add(\"Varun\");\n\nRemember to import java.util.ArrayList;";
        if (m.contains("feature") || m.contains("why java")) return "Some important Java features are:\n• Object-oriented\n• Platform-independent through the JVM\n• Strong memory management\n• Large standard library\n• Multithreading support\n• Widely used for desktop, web and Android development";
        if (m.contains("java")) return "Java is a high-level, class-based programming language. Java source code is compiled into bytecode, which runs on the Java Virtual Machine (JVM). It is used for desktop, web, Android and enterprise applications.";
        if (m.contains("loop") || m.contains("for loop")) return "Loops repeat a block of code. Common Java loops are:\n• for — useful when you know how many times to repeat\n• while — repeats while a condition is true\n• do-while — runs at least once before checking the condition\n\nExample: for (int i = 1; i <= 5; i++) { System.out.println(i); }";
        if (m.contains("study") || m.contains("tip") || m.contains("learn")) return "Try this simple study routine:\n1. Learn one concept at a time.\n2. Type the code yourself instead of only copying.\n3. Change values and predict the output.\n4. Fix errors before checking a solution.\n5. Revise yesterday's topic for five minutes. Small daily practice adds up! 🚀";
        if (m.contains("thank")) return "You're welcome! 😊 Keep practising and don't worry about making mistakes — debugging is part of programming.";
        if (m.contains("who are you") || m.contains("about")) return "I'm a Java learning chatbot built with Java Swing. I use simple rule-based keyword matching and predefined educational responses. I work offline and am not connected to a live AI model.";
        if (m.contains("variable")) return "A variable stores a value. Example:\nint age = 20;\nString name = \"Varun\";\ndouble marks = 85.5;\n\nThe data type tells Java what kind of value the variable stores.";
        if (m.contains("if else") || m.contains("condition")) return "if-else lets your program choose between paths based on a condition. Example:\nif (marks >= 40) {\n    System.out.println(\"Pass\");\n} else {\n    System.out.println(\"Fail\");\n}";
        return "Good question! I don't have a prepared answer for that yet. Try asking about Java, OOP, ArrayList, loops, variables or study tips. Type 'help' to see examples.\n\nNote: I'm a rule-based educational chatbot, so my answers are limited to topics programmed into me.";
    }

    private boolean containsAny(String text, String... words) { for (String word : words) if (text.contains(word)) return true; return false; }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) { }
        SwingUtilities.invokeLater(() -> new AIChatbot().setVisible(true));
    }
}
