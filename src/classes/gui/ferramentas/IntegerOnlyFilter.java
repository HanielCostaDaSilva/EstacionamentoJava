package classes.gui.ferramentas; 
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class IntegerOnlyFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);

        if (isInteger(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
            throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, string);

        if (isInteger(sb.toString())) {
            super.replace(fb, offset, length, string, attr);
        }
    }

    private boolean isInteger(String text) {
        if (text.isEmpty()) {
            return true;
        }

        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}