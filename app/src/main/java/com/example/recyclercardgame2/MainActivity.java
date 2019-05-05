package com.example.recyclercardgame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.recyclercardgame2.model.CardMatchingGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private CardMatchingGame game = new CardMatchingGame(52);
    TextView textView_score;
    CardGameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restoreGame();
        textView_score = findViewById(R.id.textView);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CardGameAdapter(game);

        recyclerView.setAdapter(adapter);

        adapter.setonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) v.getTag();
                game.chooseCardAtIndex(index);
                adapter.notifyDataSetChanged();
                textView_score.setText("score:"+game.getScore());
            }
        });
    }
    public void reset(View v){
        game.reset();
        textView_score.setText("score:0");
        adapter.notifyDataSetChanged();
    }

    public void showHistory(View v){
        Intent intent = new Intent(this,HistoryActivity.class);
        intent.putStringArrayListExtra("history",game.playHistory);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    @Override
    protected void onPause() {
        super.onPause();

        saveGame();
    }

    public void saveGame(){
        try {
            FileOutputStream fileOutputStream = openFileOutput("game",MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(game);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void restoreGame(){
        try {
            FileInputStream fileInputStream = openFileInput("game");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            game = (CardMatchingGame) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
