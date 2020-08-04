package hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.*;  // GridPane // Priority // RowConstraints // StackPane
import javafx.stage.Stage;
import java.util.*;

import  javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class puzzle extends Application 
{   
    int khali_row =2;
    int khali_col =2;
    Label l;
    int[] arr = {0,1,2,3,4,5,6,7};
    public static boolean chk(Button[] btn)
    {
        for(int i=0;i<btn.length;i++)
        {
            if(GridPane.getRowIndex(btn[i])==i/3)
                if(GridPane.getColumnIndex(btn[i])==i%3)
                    continue;
                else
                    return false;
            else
                return false;
        }
        return true;
    }
    public static int[] RandomizeArray(int[] array)
    {
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) 
		{
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
		return array;
    }
    public void start(Stage primaryStage) 
    {
        
        GridPane grid = new GridPane();
        grid.setMinSize(300,300);
        grid.setPadding(new Insets(10, 10, 10, 10));
        int numRows = 3 ;
        int numColumns = 3;
        for (int row = 0 ; row < numRows ; row++ )
        {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < numColumns; col++ ) 
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        
        grid.setGridLinesVisible(true);
        
        
        arr = RandomizeArray(arr);
        
        Button[] btn = new Button[8];
        for (int i = 0 ; i < 8 ; i++) 
        {
            btn[i] = createButton(Integer.toString(i+1),grid,btn);
            
            grid.add(btn[i], arr[i] % 3, arr[i]/3); //(0,0)(1,0)(2,0)(0,1)(1,1)(2,1)(0,2)(1,2)(2,2)
        }
        
        
        Button reset = createButton("reset",grid,btn);
        grid.add(reset,0,3);
        l = new Label("Game is on");
        grid.add(l,1,3,2,1);
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        
        Scene scene = new Scene(grid, 300, 250);   
        scene.getStylesheets().add("style1.css");
        primaryStage.setTitle("Puzzle Game");
        primaryStage.setScene(scene);
        
//        System.out.println(GridPane.getRowIndex(btn[2])+" "+GridPane.getColumnIndex(btn[2]));
//        grid.getChildren().remove(btn[0]);
          System.out.println(chk(btn));
        primaryStage.show();
    }
    private Button createButton(String text,GridPane grid,Button[] btnarr)
    {
        Button btn = new Button(text);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btn.setOnAction((e) -> {
                                 if(btn.getText()=="reset")
                                 {
                                     arr = RandomizeArray(arr);
                                     for(int i=0;i<btnarr.length;i++)
                                        grid.getChildren().remove(btnarr[i]);
                                     for(int i=0;i<btnarr.length;i++)
                                        grid.add(btnarr[i], arr[i] % 3, arr[i]/3);
                                     System.out.println("Reset");
                                     l.setText("Game is on");
                                     khali_col=2;
                                    khali_row=2;
                                         
                                 }
                                 else if(((Math.abs(GridPane.getColumnIndex(btn)-khali_col)<=1)&&(Math.abs(GridPane.getRowIndex(btn)-khali_row)==0))||((Math.abs(GridPane.getColumnIndex(btn)-khali_col)==0)&&(Math.abs(GridPane.getRowIndex(btn)-khali_row)<=1)))
                                 {
                                     int temp_r=GridPane.getRowIndex(btn);
                                     int temp_c=GridPane.getColumnIndex(btn);
                                     grid.getChildren().remove(btn);
                                     grid.add(btn, khali_col, khali_row);
                                    khali_col=temp_c;
                                    khali_row=temp_r;
                                 }
                                 if(chk(btnarr))
                                 {
                                     khali_row=10;
                                     khali_col=10;
                                     System.out.println("Win");
                                     l.setText("Game Won");
                                 }
                               }
                               );
        return btn;
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
