// isIPv4Address.java
// Created by Michael Oglesby to solve the 'isIPv4Address' task on CodeSignal
// Task prompt: Given a string, find out if it satisfies the IPv4 address naming rules.

// Method: isIPv4Address
// Accepts a string as input.
// Returns true if the string is a valid IPv4 Address.
// Otherwise, returns false.
boolean isIPv4Address(String inputString) {
    char[] inputCharArray = inputString.toCharArray();
    List<Integer> fullStopPositions = new ArrayList<Integer>();
    
    // Gather full stop positions
    for (int i=0; i<inputCharArray.length; i++) {
        if (inputCharArray[i] == '.') {
            fullStopPositions.add(i);
        }
    }
    
    // If there are more or less than 3 full stops, then not an IPV4 addr
    if (fullStopPositions.size() != 3) {
        return false;
    }
    
    // Check whether first number is in range [0, 255]
    if (!checkNumber(inputString.substring(0, fullStopPositions.get(0)))) {
        return false;
    }
    
    // Check whether middle 2 numbers are in range [0, 255]
    for (int i=1; i<fullStopPositions.size(); i++) {
        int currFullStop = fullStopPositions.get(i);
        int prevFullStop = fullStopPositions.get(i-1);
        
        if (!checkNumber(inputString.substring(prevFullStop+1, currFullStop))) {
            return false;
        }
    }
    
    // Check whether final number is in range [0, 255]
    if (!checkNumber(inputString.substring(fullStopPositions.get(2)+1))) {
        return false;
    }
    
    return true;
}

// Method: checkNumber
// Accepts a string as input.
// Returns true if the string contains (solely) a number in the range [0, 255].
// Otherwise, returns false.
boolean checkNumber(String str) {
    int num;
    
    if (str.length() == 0) {
        return false;
    }
    
    try {
        num = Integer.parseInt(str);
    } catch (NumberFormatException e) {
        return false;
    }
        
    
    if ((num < 0) || (num > 255)) {
        return false;
    } else {
        return true;
    }
}
