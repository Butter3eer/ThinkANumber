package com.example.thinkanumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewNumber;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonSubmit;
    private Button buttonEasy;
    private Button buttonHard;
    private ImageView imageViewHeart1;
    private ImageView imageViewHeart2;
    private ImageView imageViewHeart3;
    private ImageView imageViewHeart4;
    private ImageView imageViewHeart5;
    private int life;
    private int randomNumber;
    private int guess;
    private AlertDialog.Builder alertGameOver;
    private AlertDialog.Builder alertDifficulty;
    private Boolean easyMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonEasy.setOnClickListener(view -> {
            alertDifficulty.show();
        });

        buttonHard.setOnClickListener(view -> {
            alertDifficulty.show();
        });

        buttonPlus.setOnClickListener(view -> {
            if (easyMode) {
                if (guess == 10) {
                    Toast.makeText(this, "Nem lehet 10-nél nagyobb!", Toast.LENGTH_SHORT).show();
                }
                else {
                    guess++;
                    textViewNumber.setText(String.valueOf(guess));
                }
            }
            else {
                if (guess == 20) {
                    Toast.makeText(this, "Nem lehet 20-nál nagyobb!", Toast.LENGTH_SHORT).show();
                }
                else {
                    guess++;
                    textViewNumber.setText(String.valueOf(guess));
                }
            }

        });

        buttonSubmit.setOnClickListener(view -> {
            if (easyMode) {
                if (guess > randomNumber) {

                    Toast.makeText(this, "A gondolt szám kisebb.", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModifyEasy();

                } else if (guess < randomNumber) {

                    Toast.makeText(this, "A gondolt szám nagyobb.", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModifyEasy();

                } else {

                    Toast.makeText(this, "Kitaláltad a számot!", Toast.LENGTH_SHORT).show();
                    alertGameOver.setTitle("Gratulálok nyertél!").create();
                    alertGameOver.show();
                }
            } else {
                if (guess > randomNumber) {

                    Toast.makeText(this, "A gondolt szám kisebb.", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModifyHard();

                } else if (guess < randomNumber) {

                    Toast.makeText(this, "A gondolt szám nagyobb.", Toast.LENGTH_SHORT).show();
                    life--;
                    imageViewModifyHard();

                } else {

                    Toast.makeText(this, "Kitaláltad a számot!", Toast.LENGTH_SHORT).show();
                    alertGameOver.setTitle("Gratulálok nyertél!").create();
                    alertGameOver.show();
                }
            }

        });

        buttonMinus.setOnClickListener(view -> {
            if (guess == 0) {
                Toast.makeText(this, "Nem lehet negatív!", Toast.LENGTH_SHORT).show();
            }
            else {
                guess--;
                textViewNumber.setText(String.valueOf(guess));
            }
        });

    }

    public void imageViewModifyHard() {
        switch (life) {
            case 2:
                imageViewHeart4.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 1:
                imageViewHeart3.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 0:
                imageViewHeart2.setImageResource(R.drawable.baseline_favorite_border_24);
                alertGameOver.show();
                break;
        }
    }

    public void imageViewModifyEasy() {
        switch (life) {
            case 4:
                imageViewHeart5.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 3:
                imageViewHeart4.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 2:
                imageViewHeart3.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 1:
                imageViewHeart2.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 0:
                imageViewHeart1.setImageResource(R.drawable.baseline_favorite_border_24);
                alertGameOver.show();
                break;
        }
    }

    public void init() {
        textViewNumber = findViewById(R.id.textViewNumber);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonEasy = findViewById(R.id.buttonEasy);
        buttonHard = findViewById(R.id.buttonHard);
        imageViewHeart1 = findViewById(R.id.imageViewHeart1);
        imageViewHeart2 = findViewById(R.id.imageViewHeart2);
        imageViewHeart3 = findViewById(R.id.imageViewHeart3);
        imageViewHeart4 = findViewById(R.id.imageViewHeart4);
        imageViewHeart5 = findViewById(R.id.imageViewHeart5);
        life = 3;
        guess = 0;
        easyMode = true;

        Random rnd = new Random();
        randomNumber = rnd.nextInt(11);

        alertGameOver = new AlertDialog.Builder(this);
        alertGameOver.setTitle("Játék vége")
                .setMessage("Újra akarod kezdeni?")
                .setNegativeButton("Igen", (dialogInterface, i) -> {
                    newGame();
                })
                .setPositiveButton("Nem", (dialogInterface, i) -> {
                    finish();
                })
                .setCancelable(false)
                .create();

        alertDifficulty = new AlertDialog.Builder(this);
        alertDifficulty.setTitle("Nehézség")
                .setMessage("Biztos szeretnél nehézséget változtatni?")
                .setNegativeButton("Igen", (dialogInterface, i) -> {
                    if (easyMode) {
                        easyMode = false;
                    } else {
                        easyMode = true;
                    }

                    newGame();
                })
                .setPositiveButton("Nem", (dialogInterface, i) -> {

                })
                .setCancelable(false)
                .create();
    }

    public void newGame() {
        if (!easyMode) {
            life = 3;
            guess = 0;
            Random rnd = new Random();
            randomNumber = rnd.nextInt(11);
            imageViewHeart2.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart3.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart4.setImageResource(R.drawable.baseline_favorite_24);
            textViewNumber.setText(String.valueOf(guess));
            imageViewHeart1.setVisibility(View.INVISIBLE);
            imageViewHeart5.setVisibility(View.INVISIBLE);
        }
        else {
            life = 5;
            guess = 0;
            Random rnd = new Random();
            randomNumber = rnd.nextInt(21);
            imageViewHeart1.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart2.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart3.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart4.setImageResource(R.drawable.baseline_favorite_24);
            imageViewHeart5.setImageResource(R.drawable.baseline_favorite_24);
            textViewNumber.setText(String.valueOf(guess));
            imageViewHeart1.setVisibility(View.VISIBLE);
            imageViewHeart5.setVisibility(View.VISIBLE);
        }
    }
}