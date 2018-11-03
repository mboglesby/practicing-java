// reverseParentheses.java
// Created by Michael Oglesby to solve the 'reverseParentheses' task on CodeSignal
// Task prompt: You have a string s that consists of English letters, punctuation marks, whitespace characters, 
//    and brackets. It is guaranteed that the parentheses in s form a regular bracket sequence. Your task is to 
//    reverse the strings contained in each pair of matching parentheses, starting from the innermost pair. The 
//    results string should not contain any parentheses.

String reverseParentheses(String s) {
     List<Integer> openParenthesisPositions = new ArrayList<Integer>();
     List<Integer> closeParenthesisPositions = new ArrayList<Integer>();
     char[] sCharArray = s.toCharArray();
     
     // Create lists of ( and ) positions
     for (int i=0; i<sCharArray.length; i++) {
          char currentChar = sCharArray[i];
                    
          if (currentChar == '(') {
               openParenthesisPositions.add(i);
          } else if (currentChar == ')') {
               closeParenthesisPositions.add(i);
          }
     }
     
     // Handle array with no parentheses
     if (openParenthesisPositions.size() == 0) {
          return s;
     }
     
     // Perform reversing
     for (int i=(openParenthesisPositions.size()-1); i>=0; i--) {
          // Determine ( position
          int openParenthesisPosition = openParenthesisPositions.get(i);
          
          // Determine ) position
          int closeParenthesisPosition = 0;
          for (Integer x : closeParenthesisPositions) {
               if (x > openParenthesisPosition) {
                    closeParenthesisPosition = x;
                    closeParenthesisPositions.remove(x);
                    break;
               }
          }
          
          // Debug text
          //System.out.println("i=" + i);
          //System.out.println("( at " + openParenthesisPosition);
          //System.out.println(") at " + closeParenthesisPosition);
          
          // Reverse sub-array within ()
          int tempCharArrayLength = closeParenthesisPosition-openParenthesisPosition-1;
          char[] tempCharArray = new char[tempCharArrayLength];
          System.arraycopy(sCharArray, openParenthesisPosition+1, tempCharArray, 0, tempCharArrayLength);
          tempCharArray = reverseArray(tempCharArray);
          //System.out.println(Arrays.toString(tempCharArray)); // Debug text
          System.arraycopy(tempCharArray, 0, sCharArray, openParenthesisPosition+1, tempCharArrayLength);
          
          // Debug text
          //System.out.println(Arrays.toString(sCharArray));
     }
     
     int numRemoved = 0;
     
     // Handle case of last character being a ( or )
     if ((sCharArray[sCharArray.length-1] == '(') || (sCharArray[sCharArray.length-1] == ')')) {
          sCharArray[sCharArray.length-1] = ' ';
          numRemoved++;
     }
     
     // Remove parentheses
     for (int i=0; i<sCharArray.length; i++) {
          if ((sCharArray[i] == '(') || (sCharArray[i] == ')')) {
               System.arraycopy(sCharArray, i+1, sCharArray, i, sCharArray.length-i-1);
               numRemoved++;
               
               // Debug text
               System.out.println("i=" + i + ": " + Arrays.toString(sCharArray) + "; rem=" + numRemoved);
               
               i--;
          }        
     }
     
     // Remove extra characters
     sCharArray = Arrays.copyOf(sCharArray, sCharArray.length-numRemoved);
     
     // Debug text
     //System.out.println("truncated: " + Arrays.toString(sCharArray));
     
     // Return array converted to string
     return new String(sCharArray);
}

// Method: reverseArray
// Accepts a char array as input.
// Reverses the chars in the char array.
// Returns the resulting reversed char array.
char[] reverseArray(char[] arr) {
     char[] reversedArr = new char[arr.length];
     
     for (int i=0, j=(arr.length-1); i<arr.length; i++, j--) {
          reversedArr[i] = arr[j];
     }
     
     return reversedArr;
}
