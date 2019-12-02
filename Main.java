import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;

class Main {
  public static final int ADD = 1; 
  public static final int MULTIPLY = 2;
  public static final int STOP = 99;

  public static void main(String[] args) throws FileNotFoundException, IOException{
    System.out.println(zerothPosition(new FileReader("./input.txt")));
  }

  private static Integer zerothPosition(FileReader inputFile){
    try {
      BufferedReader reader = new BufferedReader(inputFile);
      String line = reader.readLine();

      List<Integer> inputList = setupIntcodeList(line);

      inputList.set(1, 12);
      inputList.set(2, 2);

      for(int j = 0; j < inputList.size(); j+=4){
        int opCode = inputList.get(j);
        int input1 = inputList.get(j+1);
        int input2 = inputList.get(j+2);
        int outputPosition = inputList.get(j+3);

        switch(opCode){
          case ADD:
          {
            inputList.set(outputPosition, input1 + input2);
            break;
          }
          case MULTIPLY: 
          {
            inputList.set(outputPosition, input1 * input2);
            break;
          }
          case STOP:
          {
            return inputList.get(0);
          }
        }
      }
      return inputList.get(0);
    } catch (IOException ex){
      ex.getMessage();
    }     
    return null;
  }

  private static List<Integer> setupIntcodeList(String line){
    List<Integer> intCodeList = new ArrayList<>();
    String[] input = line.split(",");
    for(String i : input){
      intCodeList.add(Integer.valueOf(i));
    }
    return intCodeList;
  }
}