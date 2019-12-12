package Orondo.Styling;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * 
 * @author Raul Alzate
 */
public class Styler {
    
    /**
     * Agrega una transicion de opacidad a un VBox.
     * Meramente visual, no afecta la logica.
     * @param v 
     */
    public static void AddFadingAnimation_onHover(VBox v){
        FadeTransition ft = new FadeTransition();
        ft.setNode(v);
        ft.setDuration(new Duration(100));
        ft.setFromValue(1.0);
        ft.setToValue(0.8);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        v.setOnMouseEntered((MouseEvent me) -> ft.play());
    }
    
    public static void SetTextFieldAsNumeric(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    textField.setText(oldValue);
                }
            }
        });
    }
}

/*
TextField_Costo.lengthProperty().addListener(new ChangeListener<Number>(){
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		  if (newValue.intValue() > oldValue.intValue()) {
			  char ch = TextField_Costo.getText().charAt(oldValue.intValue());
			  // Check if the new character is the number or other's
			  if (!(ch >= '0' && ch <= '9' )) {
				   // if it's not number then just setText to previous one
				   TextField_Costo.setText(TextField_Costo.getText().substring(0,TextField_Costo.getText().length()-1)); 
			  }
                  }
        }
        });
    }
*/

