import java.awt.*;
import java.awt.event.*;

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATM extends Frame implements ActionListener {

    Label title, balanceLabel;
    TextField amountField;
    Button depositBtn, withdrawBtn, balanceBtn, clearBtn;
    TextArea output;

    Account acc = new Account(45000);

    ATM() {

        setTitle("Digital ATM");
        setSize(450,500);
        setLayout(null);
        setBackground(new Color(28,28,30));

        // Title
        title = new Label("DIGITAL ATM");
        title.setBounds(150,50,200,30);
        title.setFont(new Font("Arial",Font.BOLD,22));
        title.setForeground(Color.WHITE);
        add(title);

        // Balance Card
        balanceLabel = new Label("Account Balance : ₹45000");
        balanceLabel.setBounds(110,100,250,30);
        balanceLabel.setFont(new Font("Arial",Font.BOLD,16));
        balanceLabel.setForeground(new Color(0,200,255));
        add(balanceLabel);

        // Amount input
        Label amount = new Label("Enter Amount");
        amount.setBounds(70,160,120,25);
        amount.setForeground(Color.WHITE);
        add(amount);

        amountField = new TextField();
        amountField.setBounds(200,160,150,25);
        add(amountField);

        // Buttons
        depositBtn = new Button("Deposit");
        depositBtn.setBounds(70,220,120,40);
        depositBtn.setBackground(new Color(0,120,255));
        depositBtn.setForeground(Color.WHITE);

        withdrawBtn = new Button("Withdraw");
        withdrawBtn.setBounds(230,220,120,40);
        withdrawBtn.setBackground(new Color(0,120,255));
        withdrawBtn.setForeground(Color.WHITE);

        balanceBtn = new Button("Check Balance");
        balanceBtn.setBounds(70,280,120,40);
        balanceBtn.setBackground(new Color(60,60,60));
        balanceBtn.setForeground(Color.WHITE);

        clearBtn = new Button("Clear");
        clearBtn.setBounds(230,280,120,40);
        clearBtn.setBackground(new Color(60,60,60));
        clearBtn.setForeground(Color.WHITE);

        add(depositBtn);
        add(withdrawBtn);
        add(balanceBtn);
        add(clearBtn);

        // Output console
        output = new TextArea();
        output.setBounds(70,350,280,80);
        output.setBackground(Color.BLACK);
        output.setForeground(new Color(0,255,150));
        output.setFont(new Font("Monospaced",Font.BOLD,14));
        add(output);

        // Button events
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        // Close window
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        double amount = 0;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch(Exception ex) {}

        if(e.getSource()==depositBtn){
            acc.deposit(amount);
            output.setText("Deposit Successful\nBalance: ₹"+acc.getBalance());
        }

        if(e.getSource()==withdrawBtn){
            acc.withdraw(amount);
            output.setText("Withdraw Successful\nBalance: ₹"+acc.getBalance());
        }

        if(e.getSource()==balanceBtn){
            output.setText("Current Balance : ₹"+acc.getBalance());
        }

        if(e.getSource()==clearBtn){
            amountField.setText("");
            output.setText("");
        }
    }

    public static void main(String[] args) {
        new ATM();
    }
}