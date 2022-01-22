package data.gui;

import java.io.PrintStream;
import javax.swing.JTextArea;

/**
 * Date: 15/01/22
 * Time: 4:11 pm
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public class TextAreaOutputStream extends PrintStream {

    private final JTextArea text;
    private boolean atLineStart;
    private final String indent;

    public TextAreaOutputStream(JTextArea textArea, PrintStream capturedStream, String indent) {
        super(capturedStream);
        this.text = textArea;
        this.indent = indent;
        this.atLineStart = true;
    }

    public TextAreaOutputStream(JTextArea textArea, PrintStream capturedStream) {
        this(textArea, capturedStream, "");
    }

    private void writeToTextArea(String str) {
        if (text != null) {
            synchronized (text) {
                text.setCaretPosition(text.getDocument().getLength());
                text.append(str);
            }
        }
    }

    private void write(String str) {
        String[] s = str.split("\n", -1);
        if (s.length == 0) {
            return;
        }
        for (int i = 0; i < s.length - 1; i++) {
            writeWithPotentialIndent(s[i]);
            writeWithPotentialIndent("\n");
            atLineStart = true;
        }
        String last = s[s.length - 1];
        if (!last.equals("")) {
            writeWithPotentialIndent(last);
        }
    }

    private void writeWithPotentialIndent(String s) {
        if (atLineStart) {
            writeToTextArea(indent + s);
            atLineStart = false;
        } else {
            writeToTextArea(s);
        }
    }

    private void newLine() {
        write("\n");
    }

    @Override
    public void print(boolean b) {
        synchronized (this) {
            write(String.valueOf(b));
        }
    }

    @Override
    public void print(char c) {
        synchronized (this) {
            write(String.valueOf(c));
        }
    }

    @Override
    public void print(char[] s) {
        synchronized (this) {
            write(String.valueOf(s));
        }
    }

    @Override
    public void print(double d) {
        synchronized (this) {
            write(String.valueOf(d));
        }
    }

    @Override
    public void print(float f) {
        synchronized (this) {
            write(String.valueOf(f));
        }
    }

    @Override
    public void print(int i) {
        synchronized (this) {
            write(String.valueOf(i));
        }
    }

    @Override
    public void print(long l) {
        synchronized (this) {
            write(String.valueOf(l));
        }
    }

    @Override
    public void print(Object o) {
        synchronized (this) {
            write(String.valueOf(o));
        }
    }

    @Override
    public void print(String s) {
        synchronized (this) {
            if (s == null) {
                write("null");
            } else {
                write(s);
            }
        }
    }

    @Override
    public void println() {
        synchronized (this) {
            newLine();
        }
    }

    @Override
    public void println(boolean x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(char x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(long x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(float x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(double x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(char x[]) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        synchronized (this) {
            print(s);
            newLine();
        }
    }
}