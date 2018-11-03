// palindromeRearranging.java
// Created by Michael Oglesby to solve the 'palindromeRearranging' task on CodeSignal
// Task prompt: Given a string, find out if its characters can be rearranged to form a palindrome.

// Method: palindromeRearranging
// Accepts a string as input.
// Returns true if the characters in the string can be rearranged to form a palindrome.
// Otherwise, returns false.
boolean palindromeRearranging(String inputString) {
    char[] inputStringArray = inputString.toCharArray();
    Hashtable<Character, Integer> letterCounts = new Hashtable<Character, Integer>();
    
    // Create hashtable of letter counts
    for (char currChar : inputStringArray) {
        if (letterCounts.containsKey(currChar)) {
            letterCounts.put(currChar, letterCounts.get(currChar)+1);
        } else {
            letterCounts.put(currChar, 1);
        }
    }
    
    // Check letter counts to determine palindrome status
    Set<Character> letters = letterCounts.keySet();
    int oddCounts = 0;
    
    for (Character letter : letters) {
        if ((letterCounts.get(letter) % 2) != 0) {
            if (++oddCounts > 1) {
                return false;
            }
        }
    }
    
    return true;
}
