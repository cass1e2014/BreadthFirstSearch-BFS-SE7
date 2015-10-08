import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

 * @author cassie9082
 *
 */
public class WordLadder {
	public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
		if(beginWord == null || endWord == null || wordDict == null){
			return 0;
		}
		
		if(beginWord.equals(endWord)){
			return 1;
		}
		
		Queue<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		queue.add(beginWord);
		visited.add(beginWord);
		
		int level = 2;
		
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				String tmp = queue.poll();
				for(int j = 0; j < tmp.length(); j++){
					char[] chars = tmp.toCharArray();
					for(char c = 'a'; c <= 'z' ; c++){
						chars[j] = c;
						String nextWord = new String(chars);
						if(nextWord.equals(endWord)){
							return level;
						}
						if(wordDict.contains(nextWord) && !visited.contains(nextWord)){
							queue.add(nextWord);
							visited.add(nextWord);
						}
					}
				}
			}
			level ++;
		}
		return 0;
	}
}
