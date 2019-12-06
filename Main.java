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

  public static Integer zerothPosition(FileReader inputFile){
    try {
      BufferedReader reader = new BufferedReader(inputFile);
      String line = reader.readLine();

      List<Integer> inputList = setupIntcodeList(line);

      for(int j = 0; j < inputList.size(); j+=4){
        inputList.set(1, 80);
        inputList.set(2, 18);
        int opCode = inputList.get(j);

        if(opCode == STOP){
          return inputList.get(0);
        }

        int input1 = inputList.get(inputList.get(j+1));
        int input2 = inputList.get(inputList.get(j+2));
        int outputPositionValue = inputList.get(j+3);
        
        switch(opCode){
          case ADD:
          {
            inputList.set(outputPositionValue, input1 + input2);
            break;
          }
          case MULTIPLY: 
          {
            inputList.set(outputPositionValue, input1 * input2);
            break;
          }
          case STOP:
          {
            for(Integer i : inputList){
              System.out.print(""+i+",");
            }
            return inputList.get(0);
          }
        }
      }
    } catch (IOException ex){
      ex.getMessage();
    }     
    return null;
  }

  private static List<Integer> setupIntcodeList(String line){
    List<Integer> intCodeList = new ArrayList<>();
    String[] input = line.split(",");

    for(String ints : input){
      intCodeList.add(Integer.valueOf(ints));
    }
    return intCodeList;
  }
}
