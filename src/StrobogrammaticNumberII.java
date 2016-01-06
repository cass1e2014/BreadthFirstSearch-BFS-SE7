import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180
 * degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * For example, Given n = 2, return ["11","69","88","96"].
 * 
 * @author cassie9082
 * 
 */
public class StrobogrammaticNumberII {
	//**找所有的可能性，用深度优先搜索
	//实现从中间插入，用StringBuilder在length/2的位置insert
	//从中间每次插入两个对称的字符，之前插入的就被挤到两边去了
	/*
	 * 一些要记住的corner case：
	 * 如果是第一个字符，即-- 临时字符串为空时进行插入，不能插入0，因为没有0开头的数字
	 * 如果n=1的话，第一个字符可以是0
	 * 如果只剩下一个待插入的字符，这个时候不能插入'6'或者'9'
	 * （需要一个flag来判断是否只剩下一个待插入的字符）
	 */
	
	public static List<String> findStrobogrammatic(int n) {
		char[] table = {'0','1','6','9','8'};
		List<String> result = new ArrayList<String>();
		build(n, "", result, table);
		return result;
	}
	
	public static void build(int n, String tmp, List<String> result, char[] table){
		if(tmp.length() == n){
			result.add(tmp);
			return;
		}
		
		boolean last = n - tmp.length() == 1;
		for(int i = 0; i < table.length; i++){
			char c = table[i];
			//首位不能为0，末位不能为6活9
			if((n != 1 && tmp.length() == 0 && c == '0') || (last && (c == '6' || c == '9'))){
				continue;
			}
			StringBuilder newTmp = new StringBuilder(tmp);
			insertFromMiddle(last, c, newTmp);
			build(n, newTmp.toString(), result, table);
		}
	}
	
	public static void insertFromMiddle(boolean last, char c, StringBuilder newTmp){
		if(c == '6'){
			newTmp.insert(newTmp.length()/2, "69");
		}else if(c == '9'){
			newTmp.insert(newTmp.length()/2, "96");
		}else{
			newTmp.insert(newTmp.length()/2, last ? c : ""+c+c);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(findStrobogrammatic(3));
	}

}
