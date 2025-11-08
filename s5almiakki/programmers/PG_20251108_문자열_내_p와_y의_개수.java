
public class PG_20251108_문자열_내_p와_y의_개수 {
    
    class Solution {
        
        boolean solution(String s) {
            int pCount = 0;
            int yCount = 0;
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case 'P': // fall through
                    case 'p':
                        pCount++;
                        break;
                    case 'Y': // fall through
                    case 'y':
                        yCount++;
                        break;
                }
            }
            return pCount == yCount;
        }
        
    }

}
