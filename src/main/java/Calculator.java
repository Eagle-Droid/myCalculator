import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        VBox god = new VBox();
        god.setSpacing(16);
        primaryStage.setHeight(360);
        primaryStage.setWidth(350);
        primaryStage.setMaxWidth(350);
        primaryStage.setMaxHeight(360);
        primaryStage.setMinHeight(360);
        primaryStage.setMinWidth(350);
        Label result = new Label("0");
        Label prev = new Label(" ");
        result.setFont(new Font(30));
        HBox row1 = new HBox();
        TextField input = new TextField();
        input.setPrefWidth(80);
        input.setMaxWidth(150);
        Button factorial = new Button(" ! ");
        Button percent = new Button("%");
        Button sqrt = new Button("√");
        Button divide = new Button("÷");
        Button ok = new Button("=");
        Button nr7 = new Button("7");
        Button nr8 = new Button("8");
        Button nr9 = new Button("9");
        Button multiply = new Button("x");
        Button nr4 = new Button("4");
        Button nr5 = new Button("5");
        Button nr6 = new Button("6");
        Button minus = new Button("-");
        Button nr1 = new Button("1");
        Button nr2 = new Button("2");
        Button nr3 = new Button("3");
        Button plus = new Button("+");
        Button dot = new Button(".");
        Button nr0 = new Button("0");
        Button C = new Button("C");
        Button bracketLeft = new Button("(");
        Button bracketRight = new Button(")");
        Button pow = new Button("^");
        row1.getChildren().addAll(factorial, percent, sqrt, divide, C);
        HBox row2 = new HBox();
        row2.getChildren().addAll(nr7, nr8, nr9, multiply);
        HBox row3 = new HBox();
        row3.getChildren().addAll(nr4, nr5, nr6, minus, ok);
        HBox row4 = new HBox();
        row4.getChildren().addAll(nr1, nr2, nr3, plus);
        HBox row5 = new HBox();
        row5.getChildren().addAll(nr0, pow);
        row1.setSpacing(15);
        row2.setSpacing(15);
        row3.setSpacing(15);
        row4.setSpacing(15);
        row5.setSpacing(15);
        god.setSpacing(15);
        god.getChildren().addAll(result, prev, row1, row2, row3, row4, row5);
        final int[] res = {0};
        nr1.setOnMouseClicked(mouseEvent -> number(res, result, 1));
        nr2.setOnMouseClicked(mouseEvent -> number(res, result, 2));
        nr3.setOnMouseClicked(mouseEvent -> number(res, result, 3));
        nr4.setOnMouseClicked(mouseEvent -> number(res, result, 4));
        nr5.setOnMouseClicked(mouseEvent -> number(res, result, 5));
        nr6.setOnMouseClicked(mouseEvent -> number(res, result, 6));
        nr7.setOnMouseClicked(mouseEvent -> number(res, result, 7));
        nr8.setOnMouseClicked(mouseEvent -> number(res, result, 8));
        nr9.setOnMouseClicked(mouseEvent -> number(res, result, 9));
        nr0.setOnMouseClicked(mouseEvent -> number(res, result, 0));

        C.setOnMouseClicked(mouseEvent -> {
            prev.setText("");
            result.setText("0");
        });
        factorial.setOnMouseClicked(mouseEvent -> {
            String resultText = result.getText();
            String prevText = prev.getText();
            prev.setText(prevText + "!");
            int r = Integer.parseInt(resultText);
            if (r == 0) result.setText("1");
            else if (r <= 20 && r >= -20) {
                long aux = 1;
                for (long i = 1; i <= r; i++) {
                    aux = aux * i;
                    result.setText(String.valueOf(aux));
                }
            } else prev.setText("Invalid");
        });
        ok.setOnMouseClicked(mouseEvent -> {
            String resultText = result.getText();
            if (resultText.contains("√") && resultText.lastIndexOf('√') > 1 && resultText.charAt(resultText.length() - 1) != '√') {
                int n = Integer.parseInt(resultText.substring(0, resultText.lastIndexOf('√')), 10);
                int m = Integer.parseInt(resultText.substring( resultText.lastIndexOf('√') + 1, resultText.length()), 10);
                prev.setText(String.valueOf(n * Math.sqrt(m)));
            } else {
                if (resultText.contains("√") && resultText.lastIndexOf('√') == 1 && resultText.length() == 3) {
                    int n = Integer.parseInt(String.valueOf(resultText.charAt(0)));
                    int m = Integer.parseInt(String.valueOf(resultText.charAt(2)));
                    prev.setText(String.valueOf(n * Math.sqrt(m)));
                } else {
                    if (resultText.contains("√") && resultText.lastIndexOf('√') == 1 && resultText.length() > 3) {
                        int n = Integer.parseInt(String.valueOf(resultText.charAt(0)));
                        int m = Integer.parseInt(resultText.substring(resultText.lastIndexOf('√') + 1, resultText.length()), 10);
                        prev.setText(String.valueOf(n * Math.sqrt(m)));
                    }
                }
                if (resultText.lastIndexOf('√') == 0 && resultText.length() == 2) {
                    int n = Integer.parseInt(String.valueOf(resultText.charAt(1)));
                    prev.setText(String.valueOf(Math.sqrt(n)));
                } else {
                    if (resultText.lastIndexOf('√') == 0 && resultText.length() > 2) {
                        int n = Integer.parseInt(resultText.substring(1));
                        prev.setText(String.valueOf(Math.sqrt(n)));
                    }
                }
            }
            if (resultText.contains("+")) {
                int[] num = new int[10];
                int a = 0, b = -1, sum = 0;
                for (int i = 0; i < resultText.length(); i++) {
                    resultText = result.getText();

                    if (resultText.charAt(i) >= '0' && resultText.charAt(i) <= '9' && i < resultText.length() - 1) {
                        a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                        ;
                    } else {
                        if (i == resultText.length() - 1) {
                            a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                            ;
                            b++;
                            num[b] = a;
                            sum = sum + a;
                            a = 0;
                        } else {
                            b++;
                            num[b] = a;
                            sum = sum + a;
                            a = 0;
                        }
                    }
                    prev.setText(String.valueOf(sum));
                }
            }
            if (resultText.contains("-")) {
                int[] num = new int[10];
                int a = 0, b = 0, sum = 0;
                for (int i = 0; i < resultText.length(); i++) {
                    resultText = result.getText();

                    if (resultText.charAt(i) >= '0' && resultText.charAt(i) <= '9' && i < resultText.length() - 1) {
                        a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                        ;
                    } else {
                        if (i == resultText.length() - 1) {
                            a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                            ;
                            b++;
                            num[b] = a;
                            sum = sum - a;
                            a = 0;
                        } else {
                            b++;
                            num[b] = a;
                            sum = sum + a;
                            a = 0;
                        }
                    }
                    prev.setText(String.valueOf(sum));
                }
                prev.setText(String.valueOf(sum));
            }
            if (resultText.contains("x")) {
                int[] num = new int[10];
                int a = 0, b = -1, sum = 1;
                for (int i = 0; i < resultText.length(); i++) {
                    resultText = result.getText();

                    if (resultText.charAt(i) >= '0' && resultText.charAt(i) <= '9' && i < resultText.length() - 1) {
                        a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                        ;
                    } else {
                        if (i == resultText.length() - 1) {
                            a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(i)));
                            ;
                            b++;
                            num[b] = a;
                            sum = sum * a;
                            a = 0;
                        } else {
                            b++;
                            num[b] = a;
                            sum = sum * a;
                            a = 0;
                        }
                    }
                    prev.setText(String.valueOf(sum));
                }
            }
            if (resultText.contains("÷")) {
                int[] num = new int[10];
                int b = -1;
                double sum = 1.0, a = 0;
                for (int i = 0; i < resultText.length(); i++) {
                    resultText = result.getText();

                    if (resultText.charAt(i) >= '0' && resultText.charAt(i) <= '9' && i < resultText.length() - 1) {
                        a = a * 10 + Double.parseDouble(String.valueOf(resultText.charAt(i)));
                        ;
                    } else {
                        if (i == resultText.length() - 1) {
                            a = a * 10 + Double.parseDouble(String.valueOf(resultText.charAt(i)));
                            ;
                            b++;

                            sum = sum * (1 / a);
                            a = 0;
                        } else {
                            b++;

                            sum = sum / (1 / a);
                            a = 0;
                        }
                    }
                    prev.setText(String.valueOf(sum));
                }
            }
            if (resultText.contains("^")) {
                int i = resultText.lastIndexOf('^');
                int a = 0, b = 0;
                if (resultText.length() == 3) {
                    a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(0)));
                    b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(2)));
                }
                if (resultText.length() > 3 && i == 1) {
                    a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(0)));
                    b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(2)));
                    b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(3)));
                } else {
                    if (i == 2 && resultText.length() > 3) {
                        a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(0)));
                        a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(1)));
                        b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(3)));
                    } else {
                        if (i == 2) {
                            a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(0)));
                            a = a * 10 + Integer.parseInt(String.valueOf(resultText.charAt(1)));
                            b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(3)));
                            b = b * 10 + Integer.parseInt(String.valueOf(resultText.charAt(4)));
                        }
                    }
                    double r = Math.pow(a, b);
                    prev.setText(String.valueOf(r));
                }
            }
//
            result.setText(prev.getText());
            prev.setText(resultText);
        });
        sqrt.setOnMouseClicked(mouseEvent ->

        {
            String resultText = result.getText();
            String prevText = "";
            if (Integer.parseInt(resultText) != 0)
                result.setText(resultText + "√");
            else result.setText("√");

            Integer n = Integer.parseInt(resultText);
            resultText = result.getText();
            if (n < 0) prev.setText("Invalid");


        });
        percent.setOnMouseClicked(mouseEvent ->

        {
            String resultText = result.getText();
            String prevText = "";

        });
        plus.setOnMouseClicked(mouseEvent ->

        {
            String resultText = result.getText();
            String prevText = "";
            result.setText(resultText + "+");
            resultText = result.getText();
        });
        minus.setOnMouseClicked(mouseEvent ->

        {
            String resultText = result.getText();
            String prevText = "";
            if (resultText.charAt(0) == '0') result.setText("-");
            else result.setText(resultText + "-");
            resultText = result.getText();
        });
        multiply.setOnMouseClicked(mouseEvent -> {
            String resultText = result.getText();
            String prevText = "";
            result.setText(resultText + "x");
            resultText = result.getText();
        });
        divide.setOnMouseClicked(mouseEvent -> {
            String resultText = result.getText();
            String prevText = "";
            result.setText(resultText + "÷");
            resultText = result.getText();
        });
        pow.setOnMouseClicked(mouseEvent -> {
            String resultText = result.getText();
            String prevText = "";
            result.setText(resultText + "^");
            resultText = result.getText();
        });
        Scene scene = new Scene(god);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void number(int[] res, Label result, int i) {
        res[0] = i;
        String resultText = result.getText();
        if (resultText.charAt(0) == '0') {
            result.setText(String.valueOf(res[0]));
        } else {
            result.setText(resultText + res[0]);
        }
    }


}

