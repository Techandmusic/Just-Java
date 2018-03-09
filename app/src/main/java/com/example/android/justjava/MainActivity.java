package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {



        displayMessage(createOrderSummary());


    }

    /**
     * Calculates the price of the order,
     * based on nuber of coffees and toppings added.
     *
     *
     */
    private int calculatePrice(boolean cream, boolean chocolate) {
        int addOns = 0;
        int coffeePrice = 5;
        if(cream == true) {
            addOns += 1;
        }
        if(chocolate == true) {
            addOns += 2;
        }

        int perCup = coffeePrice + addOns;


        return quantity * perCup;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }

    /**
     * This method uses the results of the
     * @userName, @creamBox, and @chocBox views
     * to create an order summary which is passed to
     * the displayMessage method*/
    

    private String createOrderSummary() {
        EditText editor = (EditText) findViewById(R.id.userName);
        String customerName = editor.getText().toString();
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.creamBox)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocBox)).isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = "Name: " + customerName;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd Chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: " + "$" + price;
        priceMessage += "\nThank you!";
        return priceMessage;

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);

    }




}
