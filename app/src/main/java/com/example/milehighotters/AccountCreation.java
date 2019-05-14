package com.example.milehighotters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
TODO: just input warnings for shit, cuz like, i know you and youre not gonna be doing the shit until much later
TODO: database independent errors: user tries to put admin2 for password and username, password and password cnfirmation text not matching
 */

public class AccountCreation extends AppCompatActivity {



    Button create;
    TextView username;
    TextView password;
    TextView passwordConfirmation;


    User user;

    //alert dialog helper function.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        create = findViewById(R.id.createAccount);
        username = findViewById(R.id.createUsername);
        password = findViewById(R.id.createPassword);
        passwordConfirmation = findViewById(R.id.createConfirm);

        user = User.get(this.getApplicationContext());

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true; // we assume account is valid until proven false.

                // user did not enter ANY entry.

                if (username.getText().toString().length() == 0 &&
                    password.getText().toString().length() == 0 &&
                    passwordConfirmation.getText().toString().length() == 0) {
                    alert("Please register your username and password.");
                    valid = false;
                }

                // if (valid) statements to prevent repeated alerts

                // checking if user decides to log in with admin2 for password and username

                if (valid) {
                    if (username.getText().toString().equals("admin2") && password.getText().toString().equals("admin2")) {
                        alert("There can only be one administrator.");
                        valid = false;
                    }
                }

                //username does not have 3 letters and a number

                if (valid) {
                    int numNums = 0;
                    int numLetters = 0;

                    for (int i = 0; i < username.getText().toString().length(); i++) {
                        char c = username.getText().toString().charAt(i);
                        if (Character.isLetter(c)) {
                            numLetters++;
                        }
                        if (Character.isDigit(c)) {
                            numNums++;
                        }
                    }

                    if (!(numLetters >= 3 || numNums >= 1)) {
                        alert("Username must contain at least 3 letters and 1 number.");
                        valid = false;
                    }
                }

                //todo: isDuplicate(username.getText.toString())


                if (isDuplicate(username.getText().toString())) {
                    alert("The user " + username.getText().toString() + " already exists.");
                    valid = false;
                }

                // no password or confirmation

                if (valid) {
                    if (password.getText().toString().length() == 0 &&
                            passwordConfirmation.getText().toString().length() == 0) {
                        alert("Please enter and confirm your password.");
                        valid = false;
                    }
                }

                //password does not have 3 letters and a number

                if (valid) {
                    int numNums = 0;
                    int numLetters = 0;

                    for (int i = 0; i < password.getText().toString().length(); i++) {
                        char c = password.getText().toString().charAt(i);
                        if (Character.isLetter(c)) {
                            numLetters++;
                        }
                        if (Character.isDigit(c)) {
                            numNums++;
                        }
                    }

                    if (!(numLetters >= 3 || numNums >= 1)) {
                        alert("Password must contain at least 3 letters and 1 number.");
                        valid = false;
                    }
                }

                // if password != confirmation

                if (valid) {
                    if (!password.getText().toString().equals(passwordConfirmation.getText().toString())) {
                        if (passwordConfirmation.getText().toString().length() > 0) {
                            alert("Password and Password Confirmation needs to be the same.");
                        } else {
                            alert("Please confirm your password.");
                        }
                        valid = false;
                    }
                }

                if (valid) {
                    //todo: log transaction
                    //For now, it just leads us to main menu and alerts the user.
                    alert("Account " + username.getText().toString() + " has been created successfully.");

                    UserItem userToAdd = new UserItem();
                    userToAdd.setUsername(username.getText().toString());
                    userToAdd.setPassword(password.getText().toString());
                    userToAdd.setDue(0);
                    user.addUser(userToAdd);

                    Log.i("userItem " + username.getText().toString(), "Successfully created.");

                    //todo: then pass the extra thru my nigro.
                    // log the transaction mon niggeur

                    Intent intent = new Intent(AccountCreation.this, MainMenu.class);
                    startActivity(intent);

                }
            }
        });
    }

    private void alert(String s) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AccountCreation.this);
        final AlertDialog ALERT = alertBuilder.create();

        alertBuilder.setMessage(s + "\n");

        alertBuilder.setNegativeButton("OK.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ALERT.dismiss();
            }
        });

        alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        AlertDialog display = alertBuilder.create();
        display.show();
    }

    private boolean isDuplicate(String username) {
        return user.hasInstanceOf(username);
    }

}
