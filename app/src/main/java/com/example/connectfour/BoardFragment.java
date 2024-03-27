package com.example.connectfour;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class BoardFragment extends Fragment {
    private final String GAME_STATE = "gameState";
    private ConnectFourGame mGame;
    private GridLayout mGrid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_board, container, false);

        // Add the same click handler to all grid buttons
        mGrid = parentView.findViewById(R.id.board_grid);

        for (int i = 0; i < mGrid.getChildCount(); i++) {
            Button gridButton = (Button) mGrid.getChildAt(i);
            gridButton.setOnClickListener(this::onButtonClick);
        }

        mGame = new ConnectFourGame();
        if (savedInstanceState == null)
            startGame();
        else{
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }


        return parentView;
    }

    public void onButtonClick(View view) {
        // Find the button's row and col
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.ROW;
        int col = buttonIndex % ConnectFourGame.COL;

        mGame.selectDisc(row, col);
        setDisc();

        if (mGame.isGameOver()) {
            Toast.makeText(this.requireActivity(), "Congratulations! You won!", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() ->{

                mGame.newGame();
                setDisc();
            }, 1250);

        }
    }

    public void startGame(){
        mGame.newGame();
        setDisc();
    }

    public void setDisc(){
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            Button gridButton = (Button) mGrid.getChildAt(i);

            int row = i / ConnectFourGame.COL;
            int col = i % ConnectFourGame.COL;

            Drawable whiteDisc = ContextCompat.getDrawable(getActivity(), R.drawable.circle_white);
            Drawable blueDisc = ContextCompat.getDrawable(getActivity(), R.drawable.circle_yellow);
            Drawable redDisc = ContextCompat.getDrawable(getActivity(), R.drawable.circle_red);

            Drawable wrappedWhiteDisc = DrawableCompat.wrap(whiteDisc);
            Drawable wrappedBlueDisc = DrawableCompat.wrap(blueDisc);
            Drawable wrappedRedDisc = DrawableCompat.wrap(redDisc);

            int discColor = mGame.getDisc(row, col);
            Drawable discDrawable;
            switch (discColor) {
                case ConnectFourGame.BLUE:
                    discDrawable = wrappedBlueDisc;
                    break;
                case ConnectFourGame.RED:
                    discDrawable = wrappedRedDisc;
                    break;
                case ConnectFourGame.EMPTY:
                default:
                    discDrawable = wrappedWhiteDisc;
                    break;

            }
            gridButton.setBackground(discDrawable);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}