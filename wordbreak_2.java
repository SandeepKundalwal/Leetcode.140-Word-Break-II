class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        //jagged array to maintain direct lookup of words at particular indexes in the string.
        List<String>[] lookUpTable = new ArrayList[s.length()];       
        for(String word : wordDict){
            Z_Algorithm(s, word, lookUpTable);
        }      
        List<String>[] memoTable = new ArrayList[s.length()];        
        return wordBreakUtility(0, lookUpTable, memoTable);
    }
    
    public List<String> wordBreakUtility(int i, List<String>[] lookUpTable, List<String>[] memoTable){
        if(memoTable[i] != null){
            return memoTable[i];
        }
        
        List<String> result = new ArrayList<>();
        List<String> previousWords;
        if(lookUpTable[i] != null){
            for(String word : lookUpTable[i]){
                if(i + word.length() == lookUpTable.length){
                    result.add(word);
                } else if(lookUpTable[i + word.length()] != null){
                    previousWords = wordBreakUtility(i + word.length(), lookUpTable, memoTable);
                    if(previousWords.size() > 0){
                        for(String preWords : previousWords){
                            result.add(word + " " + preWords);
                        }
                    }
                }
            }
        }
        memoTable[i] = result;
        return result;
    }
    
    public void Z_Algorithm(String s, String word, List<String>[] lookUpTable){
        //generating pattern + "$" + text
        String inputString = new String(word + "$" + s);
        //creating Z-Values Table
        int[] ZTable = createZTable(inputString);
        
        //finding the occurrences of the word in the given string. Mapping them to the index in the lookUpTable where they start in the original string.
        for(int i = 0; i < ZTable.length; i++){
            if(ZTable[i] == word.length()){
                //to counter the excessive indices due to the inclusion of "word" + "$" in the inputString, we subtract the "length of word + 1, to get the correct start index of the word in string"
                int index = i - (word.length() + 1);
                if(lookUpTable[index] == null){
                    lookUpTable[index] = new ArrayList<>();
                }
                lookUpTable[index].add(word);
            }
        }        
    }
    
    public int[] createZTable(String inputString){
        int[] ZTable = new int[inputString.length()];
        int left = 0, right = 0;
        
        for(int k = 1; k < inputString.length(); k++){
            if(k > right){
                left = right = k;
                while(right < inputString.length() && inputString.charAt(right) == inputString.charAt(right - left)){
                    right++;
                }
                ZTable[k] = right - left;
                right--;
            } else {
                //working inside Z-Box
                int k1 = k - left;
                if(ZTable[k1] < right - k + 1){
                    ZTable[k] = ZTable[k1];
                } else {
                    left = k;
                    while(right < inputString.length() && inputString.charAt(right) == inputString.charAt(right - left)){
                        right++;
                    }
                    ZTable[k] = right - left;
                    right--;
                }
            }
        }
        return ZTable;      
    }
}
