import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

abstract class operation
{
	abstract int doOperation(int value1, int value2);
}

class add extends operation
{
	int doOperation(int value1, int value2)
	{
		return value1 + value2;
	}
}

class subtract extends operation
{
	int doOperation(int value1, int value2)
	{
		return value1 - value2;
	}
}

class multiply extends operation
{
	int doOperation(int value1, int value2)
	{
		return value1 * value2;
	}
}

class divide extends operation
{
	int doOperation(int value1, int value2)
	{
		return value1/value2;
	}
}

class factory
{
	int finalValue;
    int getOperation(String input,int value1, int value2)
	{
    	if(input.equals("*"))
        {
        	operation mul = new multiply();
        	finalValue = mul.doOperation(value1, value2);
        }
        else if(input.equals("+"))
        {
        	operation a = new add();
        	finalValue = a.doOperation(value1, value2);
        }
        else if(input.equals("-"))
        {
        	operation sub = new subtract();
        	finalValue = sub.doOperation(value1, value2);
        }
        else
        {
        	operation div = new divide();
        	finalValue = div.doOperation(value1, value2);
        }
    	return finalValue;
	}
	
}

public class FirstFX extends Application
{
	Label label1;
	Button button1;
	int i = 1;
	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Basic Calculator");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label valueName1 = new Label("First Value:");
		grid.add(valueName1, 0, 1);

		TextField valueTextField1 = new TextField();
		grid.add(valueTextField1, 1, 1);

		Label valueName2 = new Label("Second Value:");
		grid.add(valueName2, 0, 2);

		TextField valueTextField2 = new TextField();
		grid.add(valueTextField2, 1, 2);
		
		Label operation = new Label("Operation (+,-,*, /):");
		grid.add(operation, 0, 3);

		TextField operationField = new TextField();
		grid.add(operationField, 1, 3);
		
		Button btn = new Button("Calculate");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 5);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                int finalValue = 0;
                int value1 = Integer.valueOf(valueTextField1.getText());
                int value2 = Integer.valueOf(valueTextField2.getText());
                String opera = operationField.getText();
                factory fac = new factory();
                finalValue = fac.getOperation(opera, value1, value2);
                
                /*if(opera.equals("*"))
                {
                	operation mul = new multiply();
                	finalValue = mul.doOperation(value1, value2);
                }
                else if(opera.equals("+"))
                {
                	operation a = new add();
                	finalValue = a.doOperation(value1, value2);
                }
                else if(opera.equals("-"))
                {
                	operation sub = new subtract();
                	finalValue = sub.doOperation(value1, value2);
                }
                else
                {
                	operation div = new divide();
                	finalValue = div.doOperation(value1, value2);
                }*/
                actiontarget.setText(Integer.toString(finalValue));
            }
        });

		Scene scene = new Scene(grid, 300, 275);
		stage.setScene(scene);
		stage.show();
	}
}