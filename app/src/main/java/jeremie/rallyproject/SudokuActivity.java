package jeremie.rallyproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SudokuActivity extends Activity {

    GridView gridView;
    TextView selected;
    int selectedRow;
    int selectedCol;
    SudokuGrid gridModel;
    Button[] buttons;
    boolean firstClick = true;
    int[] startingNumbers;
    ScoreCounter count = new ScoreCounter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sudoku);

        Intent intent = getIntent();
        count = new ScoreCounter();
        count.setCount(intent.getIntExtra("Score",-1));

        buttons = new Button[7];
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);

        gridView = (GridView) findViewById(R.id.gridView1);
        selected = null;
        selectedCol = 0;
        selectedRow = 0;
        gridModel = new SudokuGrid();
        /*gridModel.getGrid()[0][1].setDigit(3);
        gridModel.getGrid()[0][2].setDigit(5);
        gridModel.getGrid()[1][1].setDigit(2);
        gridModel.getGrid()[1][4].setDigit(5);
        gridModel.getGrid()[1][5].setDigit(4);
        gridModel.getGrid()[2][2].setDigit(2);
        gridModel.getGrid()[2][3].setDigit(4);
        gridModel.getGrid()[2][5].setDigit(5);
        gridModel.getGrid()[3][0].setDigit(5);
        gridModel.getGrid()[3][2].setDigit(3);
        gridModel.getGrid()[3][3].setDigit(2);
        gridModel.getGrid()[4][0].setDigit(2);
        gridModel.getGrid()[4][1].setDigit(6);
        gridModel.getGrid()[4][4].setDigit(1);
        gridModel.getGrid()[5][3].setDigit(6);
        gridModel.getGrid()[5][4].setDigit(4);*/
        startingNumbers = new int[]{
                0, 0, 0, 5, 2, 0,
                3, 2, 0, 0, 6, 0,
                5, 0, 2, 3, 0, 0,
                0, 0, 4, 2, 0, 6,
                0, 5, 0, 0, 1, 4,
                0, 4, 5, 0, 0, 0};
        for(int i=0; i<startingNumbers.length;i++){
            gridModel.getGrid()[getColFromPos(i)][getRowFromPos(i)].setDigit(startingNumbers[i]);
        }

        String[] entries = new String[36];
        int index = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                entries[index] = gridModel.getGrid()[j][i].printDigit();
                index++;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, entries);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(startingNumbers[position] == 0) {
                    selected = (TextView) v;
                    selectedCol = getColFromPos(position);
                    selectedRow = getRowFromPos(position);
                    int[] validMoves = gridModel.getValidMoves(selectedRow, selectedCol);
                    for (int i = 0; i < 6; i++) {
                        if (validMoves[i] == 0) {
                            buttons[i].setClickable(false);
                            buttons[i].setText("");
                        } else {
                            buttons[i].setClickable(true);
                            buttons[i].setText("" + (i + 1));
                        }
                    }
                    if (firstClick) {
                        for (int i = 0; i < gridView.getChildCount(); i++) {
                            TextView child = (TextView) gridView.getChildAt(i);
                            if (!child.getText().equals("_")) {
                                child.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                            }
                        }
                        firstClick = false;
                    }
                }
                if(selected != null)
                    selected.setSelected(true);
                if(gridModel.isFinished()){
                    Toast.makeText(getApplicationContext(),
                            "Congratulations, you won!", Toast.LENGTH_SHORT).show();
                    mystery();
                }

            }
        });


        buttons[0].setOnClickListener(new MyOnClickListener(1));
        buttons[1].setOnClickListener(new MyOnClickListener(2));
        buttons[2].setOnClickListener(new MyOnClickListener(3));
        buttons[3].setOnClickListener(new MyOnClickListener(4));
        buttons[4].setOnClickListener(new MyOnClickListener(5));
        buttons[5].setOnClickListener(new MyOnClickListener(6));
        buttons[6].setOnClickListener(new MyOnClickListener(0));

    }

    public int getColFromPos(int pos){
        return pos%6;
    }

    public int getRowFromPos(int pos){
        return pos/6;
    }

    class MyOnClickListener implements Button.OnClickListener {
        int digit;

        public MyOnClickListener(int digit){
            super();
            this.digit = digit;
        }

        @Override
        public void onClick(View v){
            gridModel.getGrid()[selectedCol][selectedRow].setDigit(digit);
            if(digit == 0)
                selected.setText("_");
            else
                selected.setText("" + digit);
            if(gridModel.isFinished()){
                Toast.makeText(getApplicationContext(),
                        "Congratulations, you won!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void mystery(){
        count.increment();
        Intent intent = new Intent(this, MysteryActivity.class);
        int score = count.totalScore();
        intent.putExtra("Score",score);
        startActivity(intent);

    }

}