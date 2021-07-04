package io.github.mikolajbartela.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldConstraint extends PlainDocument {

    public JTextFieldConstraint() {
        super();
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if (isNumber(str)) {
            if (str.equals("0")) return;
            if ((getLength() + str.length()) <= 1) {
                super.insertString(offset, str, attr);
            }
        }
    }

    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
}