package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
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

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


      createOrderSummary();

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
    

    private void createOrderSummary() {
        EditText editor = (EditText) findViewById(R.id.userName);
        String customerName = editor.getText().toString();
        String orderSubject = "JustJava order for " + customerName;
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.creamBox)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocBox)).isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = "Name: " + customerName;
        priceMessage += "\n" + getString(R.string.add_cream) + " " + hasWhippedCream;
        priceMessage += "\n" + getString(R.string.add_choco) + " " + hasChocolate;
        priceMessage += "\n" + getString(R.string.howMany) + " " + quantity;
        priceMessage += "\n" + getString(R.string.total) + " " + "$" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        orderEmail(orderSubject, priceMessage);


    }

    /*
    *  This method creates the intent to send the order to an email app
    *  */

    public void orderEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if(quantity > 100) {
            quantity = 100;
        }
        displayQuantity(quantity);

    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if(quantity < 1) {
            quantity = 1;
        }
        displayQuantity(quantity);

    }




}
