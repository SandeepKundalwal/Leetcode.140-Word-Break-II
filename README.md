# Leetcode.140-Word-Break-II
Word Break II Solution using Z-Algorithm


The crucial part to this solution is the generation of lookUpTable that will later help in generating the required result. Once you get the general idea of how to built the lookUpTable, the rest is pretty straight-forward.

How the lookUpTable will look like -

string = catsanddog
wordDict = ["cats","cat","and","sand","dog"]

lookUpTable - 
                0. [cat, cats]
                1. [null]
                2. [null]
                3. [sand]
                4. [and]
                5. [null]
                6. [null]
                7. [dog]
                8. [null]
                9. [null]

The indexes of the lookUpTable corresponds to the indexes where that particular word appears in the string. The only thing remaining now, is to iterate the lookUpTable and combine words one by one to get the final result.
