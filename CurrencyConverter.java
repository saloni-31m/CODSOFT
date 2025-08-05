package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class CurrencyConverter extends JFrame {
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField;
    JLabel resultLabel;

    String[] currencies = { "USD", "INR", "EUR", "GBP", "AUD", "CAD", "JPY", "CNY" };

    public CurrencyConverter() {
        setTitle("CURRENCY CONVERTER");
        setSize(350, 300);
        setLayout(new GridLayout(8, 1, 10, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);
        amountField = new JTextField();
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Converted amount will appear here", JLabel.CENTER);

        add(new JLabel("Select From Currency:"));
        add(fromCurrency);
        add(new JLabel("Select To Currency:"));
        add(toCurrency);
        add(new JLabel("Enter the amount:"));
        add(amountField);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        setVisible(true);
    }

    void convertCurrency() {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + from;
            URL url = new URL(urlStr);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            int code = request.getResponseCode();
            if (code == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                JSONObject json = new JSONObject(content.toString());

                
                double rate = json.getJSONObject("rates").getDouble(to);
                double convertedAmount = amount * rate;

                resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, from, convertedAmount, to));
            } else {
                resultLabel.setText("API Error: " + code);
            }

        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}
