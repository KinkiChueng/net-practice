package URIURL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**一个GUI认证程序
 * Created by lasia on 2017/12/5.
 */
public class DialogAuthenticator extends Authenticator {
    private JDialog passwordDialog;
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton okButton = new JButton("ok");
    private JButton canelButten = new JButton("canel");
    private JLabel mainLabel = new JLabel("please enter username and password: ");

    public DialogAuthenticator(String username) {
        this(username, new JFrame());
    }

    public DialogAuthenticator() {
        this("", new JFrame());
    }

    public DialogAuthenticator(JFrame parnet) {
        this("", parnet);
    }

    public DialogAuthenticator(String username, JFrame parent) {
        this.passwordDialog = new JDialog(parent, true);
        Container container = passwordDialog.getContentPane();
        container.setLayout(new GridLayout(4, 1));

        JLabel userLabel = new JLabel("username:");
        JLabel passwordLabel = new JLabel("password:");
        container.add(mainLabel);

        JPanel panel1 = new JPanel();
        panel1.add(userLabel);
        panel1.add(usernameField);
        usernameField.setText(username);
        container.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(passwordLabel);
        panel2.add(passwordField);
        container.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.add(okButton);
        panel3.add(canelButten);
        container.add(panel3);

        passwordDialog.pack();

        ActionListener actionListener = new OKResponse();
        okButton.addActionListener(actionListener);
        usernameField.addActionListener(actionListener);
        passwordField.addActionListener(actionListener);
        canelButten.addActionListener(actionListener);
    }

    private void show() {
        String prompt = this.getRequestingPrompt();
        if (prompt == null) {
            String site = this.getRequestingSite().getHostName();
            String protocol = this.getRequestingProtocol();
            int port = this.getRequestingPort();
            if (site != null & protocol != null) {
                prompt = protocol + "://" + site;
                if (port > 0) prompt += ":" + port;
            } else {
                prompt = "";
            }
        }

        mainLabel.setText("please enter username and password for" + prompt + ":");
        passwordDialog.pack();
        passwordDialog.setVisible(true);
    }

    PasswordAuthentication response = null;

    class OKResponse implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);
            //出于安全原因
            //口令作为char数组返回
            char[] password = passwordField.getPassword();
            String username = usernameField.getText();
            //清除口令，以防再次使用
            passwordField.setText("");
            response = new PasswordAuthentication(username, password);
        }
    }

    class CancelResponse implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);
            //清除口令，以防再次使用
            passwordField.setText("");
            response = null;
        }
    }

    public PasswordAuthentication getPasswordAuthentication() {
        this.show();
        return this.response;
    }
}
