# DictionaryLadder
A Word Ladder is the connection between 2 same letter words, such that each transition to a step can only be made by replacing only 1 letter in the source word and the target word is always a meaning word. A good solution has a number of steps equal to the number of places the given words differ in (Hamming Distance). E.g.
Cold -> Cord -> Card -> Ward -> Warm
Approach
The approach was to load the dictionary file into a set and only selecting capitalized words. This allowed for only the dictionary words to be read and ignoring any other sentences and or symbols. Loading these into a set allowed for no repetition amongst the words either.
The dictionary would then be traversed and for each word selected the whole dictionary set’s words would be used to check the lander length between the two. If there is any way of reaching the end word from the start word then its length till the start word is noted and returned by the ladder function as it checks for the words existence in the dictionary after change each character to every other character and checking. The results were then stored in a csv file and a graph made of those. 
Issue
The issue was of the solution being time constraining. After having run it for 2 hours only a fraction of the result was provided. Thereby not a good result set.
Future Improvement
Every word is to be connected to words that varied by it by one character. For example ‘hop’ would be connected to ‘hip’, ‘cop’, ‘how’, ‘top’ etc. These connections would thereby be edges of an interconnected graph each of weightage 1. The words would be the nodes of the graph. One could then apply both breadth first and djikstra to search for a word from the start word. And once found the distance would be written in a file alongside the words. The distance would thus be the sum of the edges.
